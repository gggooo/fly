package cfg.act;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cfg.vo.Bean;
import cfg.vo.Filter;
import cfg.vo.Member;
import cfg.vo.Myinput;
import cfg.vo.Perf;
import cfg.vo.PerfProperty;
import cfg.vo.Property;
import cfg.vo.Quote;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import common.dao.BaseDao;
import common.util.Constant;
import common.util.DateTimeUtils;
import common.util.FileUtil;
import common.util.JspUtil2;
import common.util.LocalConnetionPool;
import common.util.Util;
import common.vo.AsyncTree;
import common.vo.BaseTO;
import common.vo.EasyOut;
import common.vo.FootPerf;
import common.vo.NameValuePair;

public class CfgAction extends ActionSupport implements Preparable {

	private Logger log = Logger.getLogger(CfgAction.class);

	private String PKG;
	private String CLASS_NAME;
	private String BEAN_NAME;
	private String BEAN_DESCR;
	private String EDIT_URL;
	private String ADD_URL;
	private String FIND_URL;
	private String DETAIL_URL;
	private String DETAIL_TITLE;
	private String EDIT_TITLE;
	private String ADD_TITLE;
	private String FIND_TITLE;

	protected BaseDao baseDao;
	protected String isPage;
	private String from;
	private String quote;
	private String msg;
	private int page;
	private int rows;
	protected List<AsyncTree> treeValue = new ArrayList<AsyncTree>();
	protected List<NameValuePair> nl = new ArrayList<NameValuePair>();
	protected String id = "";
	private String ids = "";
	private String order;
	private String sort;

	private EasyOut<BaseTO> coeo;
	private String jsHtml;
	private String bodyHtml;
	private String page_title = "";
	private String exportUrl;
	private int cfg_res;

	protected BaseTO bean = new BaseTO();
	protected Field idField;
	private Field usrctField;
	private Field udField;
	private Field umField;
	private Field cdField;
	private Field cmField;
	private Field enableField;
	Bean oran;
	Perf perf;
	HttpServletRequest request;
	private String endTreeValue;
	private Filter filter;
	String now;
	Member sessionMember;
	Class<? extends BaseTO> clazz;

	@SuppressWarnings("unchecked")
	public void prepare() throws Exception {
		request = (HttpServletRequest) ActionContext.getContext().get(
				StrutsStatics.HTTP_REQUEST);
		oran = (Bean) request.getAttribute("oran");
		perf = (Perf) request.getAttribute(Constant.PERF);
		setFilter((Filter) request.getAttribute("filter"));
		now = DateTimeUtils.formatDate(
				new Timestamp(System.currentTimeMillis()), "yyyy-MM-dd HH:mm");
		sessionMember = (Member) request.getSession().getAttribute(
				Constant.SESSION_MEMBER);
		if (oran != null) {
			PKG = oran.getPkg();
			BEAN_NAME = oran.getBean();
			CLASS_NAME = PKG + ".vo." + BEAN_NAME;
			BEAN_DESCR = oran.getDescr();
			EDIT_URL = Util.editUrl(PKG, BEAN_NAME);
			ADD_URL = Util.addUrl(PKG, BEAN_NAME);
			FIND_URL = Util.findUrl(PKG, BEAN_NAME);
			DETAIL_URL = Util.detailUrl(PKG, BEAN_NAME);
			// DEL_URL = Util.delUrl(PKG, BEAN_NAME);
			DETAIL_TITLE = Util.detailTitle(BEAN_DESCR);
			EDIT_TITLE = Util.editTitle(BEAN_DESCR);
			ADD_TITLE = Util.addTitle(BEAN_DESCR);
			FIND_TITLE = Util.findTitle(BEAN_DESCR);
			clazz = (Class<? extends BaseTO>) Class.forName(CLASS_NAME);
			bean = (BaseTO) clazz.newInstance();
			idField = clazz.getDeclaredField("id");
			String[] fs = { "updateDate", "updateMember", "createDate",
					"createMember", "enable","usrct" };
			Field[] fis = new Field[fs.length];
			for (int i = 0; i < fs.length; i++) {
					fis[i] = Util.findField(clazz, fs[i]);
			}
			udField = fis[0];
			umField = fis[1];
			cdField = fis[2];
			cmField = fis[3];
			setEnableField(fis[4]);
			usrctField=fis[5];
		}
	}

	public String select() throws NoSuchFieldException, IllegalAccessException {
		setNl(Util.findSelectValue(bean, baseDao));
		return SUCCESS;
	}

	public String tree() throws SQLException, NoSuchFieldException,
			IllegalAccessException {
		List<String> endToTopPathIds = Util.endToTopPathIds(endTreeValue, bean,
				baseDao);
		treeValue = Util.findTreeValue(bean, id, baseDao, endToTopPathIds);
		return SUCCESS;
	}

	public String edit() throws SecurityException, IllegalArgumentException,
			ClassNotFoundException, NoSuchFieldException,
			IllegalAccessException, SQLException {
		BaseTO old = null;
		if (Util.notEmptyString(isPage)) {
			setPage_title(EDIT_TITLE);
			if (bean != null) {
				StringBuilder js = new StringBuilder();
				js.append(JspUtil2.findSaveSubmitJs());
				setJsHtml(js.toString());

				DetachedCriteria dc = DetachedCriteria
						.forClass(bean.getClass());
				dc.add(Restrictions.eq(Constant.ID, idField.get(bean)));
				bean = baseDao.findBean(dc);
				if (Util.isDataIn(getFilter(), bean, baseDao)) {
					List<Quote> ql = findQuote();
					setBodyHtml(JspUtil2.findEditForm(EDIT_URL, bean, perf, ql,
							String.valueOf(idField.get(bean)), getIds())
							.toString());
				} else {
					setBodyHtml("您数据过滤是：" + getFilter().descr + "，所以不能编辑此页面。");
				}
			}
		} else {
			if (bean != null) {
				if (Util.isDataIn(getFilter(), bean, baseDao)) {
					boolean isUpded = false;// 是否被更新过
					DetachedCriteria dc = DetachedCriteria.forClass(bean
							.getClass());
					dc.add(Restrictions.eq(Constant.ID, idField.get(bean)));
					old = baseDao.findBean(dc);
					if (udField != null) {
						String oldUd = (String) udField.get(old);
						String ud = (String) udField.get(bean);
						if (Util.notEmptyString(oldUd) && !oldUd.equals(ud)) {
							isUpded = true;
						}
					}
					if (!isUpded) {// 在提取bean到提交bean的这段时间内，bean没有被更新过才会，才会做更新处理
						List<PerfProperty> pps = Util.findPpsById(perf.getId(),"2");//提取需要编辑的PP
						for(PerfProperty pp:pps){
							Property property = pp.getProperty();
							String field = property.getProperty();
							Field declaredField=null;
							int indexOf = field.indexOf(".");
							if (indexOf >= 0) {
								String f1 = field.substring(0, indexOf);//org
								declaredField = clazz.getDeclaredField(f1);//org(Field) in OrgMember.class
								
							}else{
								declaredField=clazz.getDeclaredField(field);
							}
							
							declaredField.set(old, declaredField.get(bean));
						}
						Util.setOTMNull(PKG, BEAN_NAME, old, baseDao);
						if (udField != null)
							udField.set(old, now);
						if (umField != null)
							umField.set(old, sessionMember);
						baseDao.update(old);
						setBodyHtml(Constant.UPDATE_SUCCESS);
						setCfg_res(1);//给继承者提供反馈
					} else {
						setBodyHtml("该数据已经被更新，此次提交不成功，请刷新后重新提交.");
					}
				} else {
					setBodyHtml("您数据过滤是：" + getFilter().descr + "，所以不能编辑此页面。");
				}
			}
		}
		return SUCCESS;
	}	
	
	public String detail() throws Exception {
		setPage_title(DETAIL_TITLE);
		if (bean != null) {
			DetachedCriteria dc = DetachedCriteria.forClass(bean.getClass());
			dc.add(Restrictions.eq(Constant.ID, idField.get(bean)));
			bean = baseDao.findBean(dc);
			buildPropertyShowValue(bean);
			List<Quote> ql = findQuote();
			final StringBuilder findDetailTable = JspUtil2.findDetailTable(
					bean, perf, ql, DETAIL_URL,
					String.valueOf(idField.get(bean)), ids);
			setBodyHtml(findDetailTable.toString());
		}
		return SUCCESS;
	}

	private List<Quote> findQuote() {
		DetachedCriteria dc2 = DetachedCriteria.forClass(Quote.class);
		dc2.add(Restrictions.eq("oranPerf", perf));
		List<Quote> ql = baseDao.find(dc2);
		return ql;
	}

	public String del() throws Exception {
		if (bean != null) {
			// DetachedCriteria dc = DetachedCriteria
			// .forClass(bean.getClass());
			// dc.add(Restrictions.eq(Constant.ID, idField.get(bean)));
			// bean = baseDao.findBean(dc);
			if (Util.isDataIn(getFilter(), bean, baseDao)) {
				if (enableField == null) {
					baseDao.delete(bean);
				} else {
					if (idField.get(bean) != null) {
						BaseTO old = baseDao.get(clazz, String.valueOf(idField.get(bean)));//删除只要传过来的ID即可，若有其他属性传来，不会同步更新
						enableField.set(old, Constant.ENABLE_DEL);
						if (udField != null)
							udField.set(old, now);
						if (umField != null)
							umField.set(old, sessionMember);
						baseDao.update(old);
					}//传过来的beanID为空不考虑
				}
				setMsg(Constant.DEL_SUCCESS);
			} else {
				setMsg(Constant.DEL_ERR + " 您数据过滤是：" + getFilter().descr);
			}
		} else {
			setMsg(Constant.DEL_ERR);
		}
		return SUCCESS;
	}

	public String find() throws IllegalArgumentException, SecurityException,
			ClassNotFoundException, IllegalAccessException,
			NoSuchFieldException, InstantiationException,
			UnsupportedEncodingException {
		if ("1".equals(isPage)) {
			listPage();
			return "page";
		} else if ("2".equals(isPage)) {
			export();
			return "exp";
		} else {
			listData();
			return "data";
		}
	}

	private void listData() throws NoSuchFieldException,
			ClassNotFoundException, IllegalAccessException, SecurityException,
			IllegalArgumentException, InstantiationException {
		setCoeo(new EasyOut<BaseTO>());
		getCoeo().setTotal(baseDao.findCount(buildDc("count")));
		List<BaseTO> cl = baseDao.findWithPage2(buildDc(""), (page - 1) * rows,
				rows);
		for (BaseTO b : cl) {
			buildPropertyShowValue(b);
		}
		getCoeo().setRows(cl);
	}

	/**
	 * 对于Bean的属性，定义了showvalue的，显示其属性值在cfg_cons表中对应的descr描述
	 * 
	 * @param bean
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	private void buildPropertyShowValue(BaseTO bean)
			throws NoSuchFieldException, IllegalAccessException {
		List<PerfProperty> pps = Util.findPpsByList(perf.getId());
		for (PerfProperty pp : pps) {
			final Property property = pp.getProperty();
			String showValue = JspUtil2.propertyShowValue(pp, property);
			if (Util.notEmptyString(showValue)) {
				String propertyName = property.getProperty();
				List<NameValuePair> nvlist = Util.nvlist(baseDao,
						showValue);
				Field pfield = clazz.getDeclaredField(propertyName);
				String value = String.valueOf(pfield.get(bean));
				String name = "";
				for (NameValuePair nv : nvlist) {
					if (nv.getValue().equals(value)) {
						name = nv.getName();
						break;
					}
				}
				pfield.set(bean, name);
			}
		}
	}

	protected DetachedCriteria buildDc(String fg) throws NoSuchFieldException,
			ClassNotFoundException, IllegalAccessException {
		DetachedCriteria dc;
		if (!"count".equals(fg)) {
			if (Util.emptyString(sort)) {
				if (Util.findField(clazz, "ord")!=null) {
					sort = "ord";
					order = "asc";
				} else {
					sort = "id";
					order = "desc";
				}
			}
			dc = Util
					.createRectiDc(sort, order, bean, CLASS_NAME, perf.getId());
		} else {
			dc = Util.createRectiDc(bean, CLASS_NAME, perf.getId());
		}
		if (enableField != null) {
			dc.add(Restrictions.ne(enableField.getName(), Constant.ENABLE_DEL));
		}
		Util.createFilterDc(dc, getFilter(), baseDao);
		return dc;
	}

	private void export() throws NoSuchFieldException, ClassNotFoundException,
			IllegalAccessException, UnsupportedEncodingException {
		List<BaseTO> cl = baseDao.find(buildDc(""));
		for (BaseTO b : cl) {
			buildPropertyShowValue(b);
		}
		InputStream excelStream = JspUtil2
				.drawXls(CLASS_NAME, perf.getId(), cl);
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/")
				+ "upload/";
		String generateWord = Util.generateWord();
		String fileName = generateWord + ".xls";
		setExportUrl(realPath + fileName);
		File file = new File(getExportUrl());
		FileUtil.inputstreamToFile(excelStream, file);
	}

	private void listPage() throws ClassNotFoundException,
			IllegalAccessException, NoSuchFieldException,
			InstantiationException {
		setPage_title(FIND_TITLE);
		StringBuilder body = new StringBuilder();
		StringBuilder plainToolbar = new StringBuilder();
		String quotePropertyId = "";
		Quote qb = null;
		if (Util.notEmptyString(quote)) {
			qb = baseDao.get(Quote.class, quote);
			quotePropertyId = qb.getQuoteProperty().getId();
		}
		plainToolbar = JspUtil2.plainToolbarHtml(from, qb, bean, perf.getId());
		StringBuilder tb = JspUtil2.findToolBar(plainToolbar,
				new StringBuilder(), perf.getId(), bean, quotePropertyId);
		StringBuilder ta = JspUtil2.findListTable();
		body.append(tb).append(ta);
		setBodyHtml(body.toString());
		StringBuilder js = new StringBuilder();
		StringBuilder sear = JspUtil2.findSearJs(FIND_URL, perf.getId());
		StringBuilder searInitJs = JspUtil2.findLaterSearInitJs();
		StringBuilder exportXlsjs = JspUtil2.findexportXlsJs(FIND_URL
				+ "?isPage=2", "", perf.getId());
		js.append(JspUtil2.findClsJs(from, clazz, oran.getDescrProperty()));
		js.append(searInitJs);
		js.append(JspUtil2.findPopJs()).append(sear).append(exportXlsjs);
		setJsHtml(js.toString());
	}

	public String add() throws IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException  {
		Perf perf = (Perf) request.getAttribute(Constant.PERF);
		if (Util.notEmptyString(isPage)) {
			setPage_title(ADD_TITLE);
			StringBuilder js = new StringBuilder();
			js.append(JspUtil2.findSaveSubmitJs());
			js.append(JspUtil2.findPopJs());
			setJsHtml(js.toString());
			StringBuilder body = new StringBuilder();
			String quotePropertyId = "";
			if (Util.notEmptyString(quote)) {
				Quote qb = baseDao.get(Quote.class, quote);
				quotePropertyId = qb.getQuoteProperty().getId();
			}
			String idfs = String.valueOf(idField.get(bean));
			if (Util.notEmptyString(idfs)) {
				// from copy order
				bean = baseDao.get(clazz, idfs);
			}
			body.append(JspUtil2.findAddForm(ADD_URL, bean, perf,
					quotePropertyId));
			setBodyHtml(body.toString());
		} else {
			if (bean != null) {
				Util.setOTMNull(PKG, BEAN_NAME, bean, baseDao);
				if (cdField != null)
					cdField.set(bean, now);
				if (cmField != null)
					cmField.set(bean, sessionMember);
				Util.saveFilter(getFilter(), baseDao, bean);
				if (Util.isDataIn(getFilter(), bean, baseDao)) {
//					idField.set(bean, Util.generateId());
					if(usrctField!=null)
						usrctField.set(bean, Constant.USRCT);
					baseDao.save(bean);
					String beanId = (String) idField.get(bean);
					setBodyHtml(Constant.ADD_SUCCESS + " <a href='" + EDIT_URL
							+ "?isPage=1&bean.id=" + beanId + "'>编辑页面</a>");
					setCfg_res(1);//给继承者提供反馈
				} else {
					setBodyHtml("您数据过滤是：" + getFilter().descr + "，所以不能编辑此页面。");
				}
			}
		}
		return SUCCESS;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public String getJsHtml() {
		return jsHtml;
	}

	public void setJsHtml(String jsHtml) {
		this.jsHtml = jsHtml;
	}

	public String getBodyHtml() {
		return bodyHtml;
	}

	public void setBodyHtml(String bodyHtml) {
		this.bodyHtml = bodyHtml;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public EasyOut<BaseTO> getCoeo() {
		return coeo;
	}

	public void setCoeo(EasyOut<BaseTO> coeo) {
		this.coeo = coeo;
	}

	public void setBean(BaseTO bean) {
		this.bean = bean;
	}

	public BaseTO getBean() {
		return bean;
	}

	public void setPage_title(String page_title) {
		this.page_title = page_title;
	}

	public String getPage_title() {
		return page_title;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getFrom() {
		return from;
	}

	public void setExportUrl(String exportUrl) {
		this.exportUrl = exportUrl;
	}

	public String getExportUrl() {
		return exportUrl;
	}

	public String getIsPage() {
		return isPage;
	}

	public void setIsPage(String isPage) {
		this.isPage = isPage;
	}

	public List<AsyncTree> getTreeValue() {
		return treeValue;
	}

	public void setTreeValue(List<AsyncTree> treeValue) {
		this.treeValue = treeValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public List<NameValuePair> getNl() {
		return nl;
	}

	public void setNl(List<NameValuePair> nl) {
		this.nl = nl;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Field getIdField() {
		return idField;
	}

	public void setIdField(Field idField) {
		this.idField = idField;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getEndTreeValue() {
		return endTreeValue;
	}

	public void setEndTreeValue(String endTreeValue) {
		this.endTreeValue = endTreeValue;
	}

	public Field getEnableField() {
		return enableField;
	}

	public void setEnableField(Field enableField) {
		this.enableField = enableField;
	}

	public int getCfg_res() {
		return cfg_res;
	}

	public void setCfg_res(int cfg_res) {
		this.cfg_res = cfg_res;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

}
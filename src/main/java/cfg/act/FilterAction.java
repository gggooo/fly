package cfg.act;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import cfg.vo.Filter;
import cfg.vo.Perf;
import cfg.vo.Permission;
import cfg.vo.Quote;
import common.vo.AsyncTree;
import common.act.BaseAction;
import common.vo.NameValuePair;
import common.dao.BaseDao;
import common.util.Constant;
import common.util.FileUtil;
import common.util.JspUtil2;
import common.util.Util;
import common.vo.BaseTO;
import common.vo.EasyOut;

public class FilterAction extends BaseAction {

	private Logger log = Logger.getLogger(FilterAction.class);

	private static String PKG = "cfg";
	private static String CLASS_NAME = "cfg.vo.Filter";
	private static String BEAN_NAME = "Filter";
	private static String BEAN_DESCR = "过滤";
	private final static String EDIT_URL = Util.editUrl(PKG, BEAN_NAME);
	private final static String ADD_URL = Util.addUrl(PKG, BEAN_NAME);
	private final static String FIND_URL = Util.findUrl(PKG, BEAN_NAME);
	private final static String DETAIL_URL = Util.detailUrl(PKG, BEAN_NAME);
	private final static String DEL_URL = Util.delUrl(PKG, BEAN_NAME);
	private final static String DETAIL_TITLE = Util.detailTitle(BEAN_DESCR);
	private final static String EDIT_TITLE = Util.editTitle(BEAN_DESCR);
	private final static String ADD_TITLE = Util.addTitle(BEAN_DESCR);
	private final static String FIND_TITLE = Util.findTitle(BEAN_DESCR);

	private BaseDao baseDao;
	private Filter bean = new Filter();
	private String isPage;
	private String from;
	private String quote;
	private String msg;
	private int page;
	private int rows;
	private List<AsyncTree> treeValue;
	private List<NameValuePair> nl;
	private String id = "";
	private String order;
	private String sort;

	private EasyOut<BaseTO> coeo;
	private String jsHtml;
	private String bodyHtml;
	private String page_title = "";
	private String exportUrl;

	public String select() throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		setNl(Util.findSelectValue(bean, baseDao));
		return SUCCESS;
	}

	public String tree() throws SQLException, NoSuchFieldException,
			IllegalAccessException {
		treeValue=new ArrayList<AsyncTree>();
		if(!Util.notEmptyString(id)){
			DetachedCriteria dc = DetachedCriteria.forClass(Permission.class);
			List<Permission> bl = baseDao.find(dc);
			List<String> sl = new ArrayList<String>(); 
			for(Permission b:bl){
				String namespace = b.getNamespace();
				if (Util.notEmptyString(namespace)&&!sl.contains(namespace)) {
					sl.add(namespace);
				}
			}
			for(String s : sl){
				AsyncTree at = new AsyncTree();
				at.setId("pkg:"+s);
				at.setText(s);
				at.setState("closed");
				treeValue.add(at);
			}
		}else if (id.startsWith("pkg:")) {
			DetachedCriteria dc = DetachedCriteria.forClass(Permission.class);
			dc.add(Restrictions.eq("namespace", id.replace("pkg:", ""))).add(Restrictions.eq("type", "2"));
			List<Permission> bl = baseDao.find(dc);
			for(Permission b:bl){
				AsyncTree at = new AsyncTree();
				at.setId("permission2:"+String.valueOf(b.getId()));
				at.setText(b.getDescr());
				at.setState("closed");
				treeValue.add(at);
			}
		}else  if (id.startsWith("permission2:")) {
			DetachedCriteria dc = DetachedCriteria.forClass(Permission.class);
			String beanId = id.replace("permission2:", "");
			Permission p=baseDao.get(Permission.class, Integer.valueOf(beanId));
			dc.add(Restrictions.eq("parent",p));
			List<Permission> bl = baseDao.find(dc);
			for(Permission b:bl){
				AsyncTree at = new AsyncTree();
				at.setId("permission1:"+String.valueOf(b.getId()));
				at.setText(b.getDescr());
				at.setState("closed");
				treeValue.add(at);
			}
		}
		else if (id.startsWith("permission1:")) {
			DetachedCriteria dc = DetachedCriteria.forClass(Filter.class);
			String beanId = id.replace("permission1:", "");
			Permission b=baseDao.get(Permission.class, Integer.valueOf(beanId));
			dc.add(Restrictions.eq("permission", b));
			List<Filter> bl = baseDao.find(dc);
			for(Filter bx:bl){
				AsyncTree at = new AsyncTree();
				at.setId(String.valueOf(bx.getId()));
				at.setText(bx.getDescr());
				at.setState("open");
				treeValue.add(at);
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

	public void setBean(Filter bean) {
		this.bean = bean;
	}

	public Filter getBean() {
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
}
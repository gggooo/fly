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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cfg.vo.Bean;
import cfg.vo.Perf;
import cfg.vo.PerfProperty;
import cfg.vo.Permission;
import cfg.vo.Property;
import cfg.vo.Quote;

import common.act.BaseAction;
import common.dao.BaseDao;
import common.util.Constant;
import common.util.FileUtil;
import common.util.JspUtil2;
import common.util.Util;
import common.vo.AsyncTree;
import common.vo.BaseTO;
import common.vo.EasyOut;
import common.vo.NameValuePair;

public class PerfAction extends BaseAction {

	private Logger log = Logger.getLogger(PerfAction.class);

	private static String PKG = "cfg";
	private static String CLASS_NAME = "cfg.vo.Perf";
	private static String BEAN_NAME = "Perf";
	private static String BEAN_DESCR = "";
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
	private Perf bean = new Perf();
	private String isPage;
	private String from;
	private String quote;
	private int page;
	private int rows;
	private String order;
	private String sort;

	private EasyOut<BaseTO> coeo;
	private String jsHtml;
	private String bodyHtml;
	private String page_title = "";
	private String exportUrl;
	private List<AsyncTree> treeValue = new ArrayList<AsyncTree>();
	private String id = "";
	private String kind = "";
	List<Sub> cl;
	private String layout;

	private Sub exzis(List<Sub> cl2, PerfProperty pp) {
		Sub res = null;
		String oranpid = pp.getProperty().getId();
		for (Sub s : cl2) {
			if (oranpid.equals(s.getCk())) {
				res = s;
				s.setFlag("1");
				break;
			}
		}
		return res;
	}

	public String c_editUnderPerf() {
		if ("1".equals(isPage)) {
			bean = baseDao.get(Perf.class, bean.getId());
			Bean b = bean.getBean();
			DetachedCriteria dc = DetachedCriteria.forClass(Property.class);
			dc.add(Restrictions.eq("bean", b)).addOrder(Order.asc("ord"));
			List<Property> pl = baseDao.find(dc);
			dc = DetachedCriteria.forClass(PerfProperty.class);
			dc.add(Restrictions.eq("perf", bean));
			List<PerfProperty> ppl = baseDao.find(dc);
			StringBuilder bsb = new StringBuilder();
			bsb.append(bean.getDescr()).append(":<p>");
			bsb.append("<form id='df_form' action='cfg_c_Perf_editUnder.sg?isPage=sub' method='POST'>");
			bsb.append("<input type='hidden' name='bean.id' value='")
					.append(bean.getId()).append("'>");
			bsb.append("<table class='horTable' border='1px;' style='width:80%;'>");

			bsb.append("<tr><td>ID</td><td><input type='button' onclick=\"$('input:checkbox').attr('checked','checked');\" value='全选'/></td>"
					+ "<td><input type='button' onclick=\"$('input:radio[value=0]').attr('checked',true)\" value='隐藏'/>"
					+ "<input type='button' onclick=\"$('input:radio[value=1]').attr('checked',true)\" value='显示'/>"
					+ "<input type='button' onclick=\"$('input:radio[value=2]').attr('checked',true)\" value='编辑'/></td><td>links</td><tr>");
			List<NameValuePair> findConstantSel = baseDao
					.findConstantSel("cfg_perfproperty_kind");

			int ind = 0;
			for (Property p : pl) {
				String votype = p.getVotype();
				if (!Util.notEmptyString(votype)) {
					String checked = "";
					bsb.append("<tr>");
					PerfProperty pp = proepertyKind(p, ppl);
					String firstTdVal = "";
					if (pp != null) {
						checked = "checked='checked'";
						firstTdVal = String.valueOf(pp.getId());
					}
					bsb.append("<td>").append(firstTdVal).append("</td>");
					bsb.append("<td><input type='hidden' value='")
							.append(p.getId()).append("' name='cl[")
							.append(ind)
							.append("].id'/><input type='checkbox' ")
							.append(checked).append(" name='cl[").append(ind)
							.append("].ck' value='").append(p.getId())
							.append("'>")
							.append(p.getDescr() + "：" + p.getProperty())
							.append("</td><td>");
					for (NameValuePair nv : findConstantSel) {
						String s = "";
						if (pp != null
								&& pp.getEditshowhide().equals(nv.getValue())) {
							s = "checked='checked'";
						}
						bsb.append("<input type='radio' name='cl[").append(ind)
								.append("].kind' ").append(s)
								.append(" value='").append(nv.getValue())
								.append("'>").append(nv.getName());
					}
					bsb.append(
							"</td><td><a class='easyui-linkbutton' data-options='plain:true'  target='_blank' href='cfg_editProperty.sg?isPage=1&bean.id=")
							.append(p.getId()).append("'>修改Property</a>");
					if (pp != null)
						bsb.append(
								"<a class='easyui-linkbutton' data-options='plain:true'  target='_blank' href='cfg_editPerfProperty.sg?isPage=1&bean.id=")
								.append(pp.getId()).append("'>修改PP</a>");
					bsb.append("</td></tr>");
					ind++;
				}
			}
			bsb.append("<tr><td>LAYOUT</td><td colspan='3'><textarea name='layout' rows='5' style='width:90%'>"
					+ bean.getLayout() + "</textarea></td></tr>");
			bsb.append("<tr><td colspan='4'><a id='df_sub' class='easyui-linkbutton' href='javascript:void(0)'  onclick='javascript:$(\"#df_form\").submit();'>提交</a><span id='df_action_msg'></td></tr></table></form>");
			setBodyHtml(bsb.toString());
			setJsHtml(JspUtil2.findSaveSubmitJs().toString());
			return "page";
		}else if("sub".equals(isPage)){
			bean = baseDao.get(Perf.class, bean.getId());
			DetachedCriteria dc = DetachedCriteria.forClass(PerfProperty.class);
			dc.add(Restrictions.eq("perf", bean));
			List<PerfProperty> oranPpl = baseDao.find(dc);

			for (PerfProperty pp : oranPpl) {

				String editshowhide = pp.getEditshowhide();
				Sub newEsh = exzis(cl, pp);
				if (newEsh != null) {
					String kind2 = newEsh.getKind();
					if (!kind2.equals(editshowhide)) {
						pp.setEditshowhide(kind2);
						baseDao.update(pp);
					}
					// update

				} else {
					// del
					baseDao.delete(pp);
				}
			}

			for (Sub s : cl) {
				if (!"1".equals(s.getFlag())) {
					String ck = s.getCk();
					if (Util.notEmptyString(ck)) {
						Property p = new Property();
						p.setId(ck);
						PerfProperty o = new PerfProperty();
						o.setPerf(bean);
						o.setProperty(p);
						String kind2 = s.getKind();
						if (!Util.notEmptyString(kind2)) {
							kind2 = "0";
						}
						o.setEditshowhide(kind2);
						baseDao.save(o);
					}
				}
			}

			bean.setLayout(layout);
			baseDao.update(bean);

			setBodyHtml(Constant.UPDATE_SUCCESS);
			return "subRes";
		}
		return SUCCESS;
	}

	private PerfProperty proepertyKind(Property p, List<PerfProperty> ppl) {
		// TODO Auto-generated method stub
		for (PerfProperty pp : ppl) {
			Property property = pp.getProperty();
			if (property != null && property.getId().equals(p.getId())) {
				return pp;
			}
		}
		return null;
	}

	public String tree() throws SQLException, SecurityException,
			IllegalArgumentException, NoSuchFieldException,
			IllegalAccessException {
		treeValue.addAll(perfTree(id));
		return SUCCESS;
	}

	private List<AsyncTree> perfTree(String id) {
		List<AsyncTree> res = new ArrayList<AsyncTree>();
		if (!Util.notEmptyString(id)) {
			DetachedCriteria dc = DetachedCriteria.forClass(Permission.class);
			List<Permission> bl = baseDao.find(dc);
			List<String> sl = new ArrayList<String>();
			for (Permission b : bl) {
				String namespace = b.getNamespace();
				if (Util.notEmptyString(namespace) && !sl.contains(namespace)) {
					sl.add(namespace);
				}
			}
			for (String s : sl) {
				AsyncTree at = new AsyncTree();
				at.setId("pkg:" + s);
				at.setText(s);
				at.setState("closed");
				res.add(at);
			}
		} else if (id.startsWith("pkg:")) {
			DetachedCriteria dc = DetachedCriteria.forClass(Permission.class);
			dc.add(Restrictions.eq("namespace", id.replace("pkg:", ""))).add(
					Restrictions.eq("type", "2"));
			List<Permission> bl = baseDao.find(dc);
			for (Permission b : bl) {
				AsyncTree at = new AsyncTree();
				at.setId("permission2:" + String.valueOf(b.getId()));
				at.setText(b.getDescr());
				at.setState("closed");
				res.add(at);
			}
		} else if (id.startsWith("permission2:")) {
			DetachedCriteria dc = DetachedCriteria.forClass(Permission.class);
			String beanId = id.replace("permission2:", "");
			Permission p = baseDao.get(Permission.class,
					Integer.valueOf(beanId));
			dc.add(Restrictions.eq("parent", p));
			List<Permission> bl = baseDao.find(dc);
			for (Permission b : bl) {
				AsyncTree at = new AsyncTree();
				at.setId("permission1:" + String.valueOf(b.getId()));
				at.setText(b.getDescr());
				at.setState("closed");
				res.add(at);
			}
		} else if (id.startsWith("permission1:")) {
			DetachedCriteria dc = DetachedCriteria.forClass(Perf.class);
			String beanId = id.replace("permission1:", "");
			Permission b = baseDao.get(Permission.class,
					Integer.valueOf(beanId));
			dc.add(Restrictions.eq("permission", b));
			List<Perf> bl = baseDao.find(dc);
			for (Perf bx : bl) {
				AsyncTree at = new AsyncTree();
				at.setId(String.valueOf(bx.getId()));
				at.setText(bx.getDescr());
				at.setState("open");
				res.add(at);
			}
		}
		return res;
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

	public void setBean(Perf bean) {
		this.bean = bean;
	}

	public Perf getBean() {
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

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
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

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public List<Sub> getCl() {
		return cl;
	}

	public void setCl(List<Sub> cl) {
		this.cl = cl;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}
}

class Sub {
	String id;
	String ck;
	String kind;
	String flag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCk() {
		return ck;
	}

	public void setCk(String ck) {
		this.ck = ck;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
package cfg.act;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.aspectj.weaver.ast.Or;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cfg.vo.Bean;
import cfg.vo.Org;
import cfg.vo.OrgRole;
import cfg.vo.Perf;
import cfg.vo.Quote;
import common.vo.AsyncTree;
import common.act.BaseAction;
import common.dao.BaseDao;
import common.util.Constant;
import common.util.FileUtil;
import common.util.JspUtil2;
import common.util.Util;
import common.vo.BaseTO;
import common.vo.EasyOut;

public class OrgRoleAction extends BaseAction {

	private Logger log = Logger.getLogger(OrgRoleAction.class);

	private static String PKG = "cfg";
	private static String CLASS_NAME = "cfg.vo.OrgRole";
	private static String BEAN_NAME = "OrgRole";
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
	private OrgRole bean = new OrgRole();
	private String isPage;
	private String from;
	private String quote;
	private int page;
	private int rows;
	private List<AsyncTree> treeValue;
	private String id = "";
	private String order;
	private String sort;

	private EasyOut<BaseTO> coeo;
	private String jsHtml;
	private String bodyHtml;
	private String page_title = "";
	private String exportUrl;

	public String tree() throws SQLException, NoSuchFieldException,
			IllegalAccessException {
		treeValue=new ArrayList<AsyncTree>();
		if(!Util.notEmptyString(id)){
			DetachedCriteria dc = DetachedCriteria.forClass(Org.class);
			List<Org> bl = baseDao.find(dc);
			for(Org b:bl){
				AsyncTree at = new AsyncTree();
				at.setId("org:"+b.getId());
				at.setText(b.getDescr());
				at.setState("closed");
				treeValue.add(at);
			}
		}else if (id.startsWith("org:")) {
			DetachedCriteria dc = DetachedCriteria.forClass(OrgRole.class);
			String replace = id.replace("org:", "");
			Org org=new Org();
			org.setId(replace);
			dc.add(Restrictions.eq("org", org));
			List<OrgRole> bl = baseDao.find(dc);
			for(OrgRole b:bl){
				AsyncTree at = new AsyncTree();
				at.setId(String.valueOf(b.getId()));
				at.setText(b.getRole().getDescr());
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

	public void setBean(OrgRole bean) {
		this.bean = bean;
	}

	public OrgRole getBean() {
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
}
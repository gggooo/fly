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

import cfg.vo.Cons;
import cfg.vo.Perf;
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

public class ConsAction extends CfgAction {

	private Logger log = Logger.getLogger(ConsAction.class);

	private String domain;//params for findConstantSel
	
	
	public String select(){
		setNl(Util.nvlist(baseDao,domain));
		return SUCCESS;
	}


	public String tree() throws SQLException, NoSuchFieldException,
			IllegalAccessException {
		treeValue = Util.findTreeValue(getBean(), getId(), baseDao);
		return SUCCESS;
	}
	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}
}
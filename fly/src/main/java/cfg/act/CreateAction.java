package cfg.act;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Else;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cfg.vo.Bean;
import cfg.vo.Myinput;
import cfg.vo.Perf;
import cfg.vo.PerfProperty;
import cfg.vo.Permission;
import cfg.vo.Property;

import common.act.BaseAction;
import common.dao.BaseDao;
import common.util.Constant;
import common.util.FileUtil;
import common.util.JspUtil2;
import common.util.Util;
import common.vo.NameValuePair;

public class CreateAction extends BaseAction{
	
	private Logger log = Logger.getLogger(CreateAction.class);
	

	private BaseDao baseDao;
	final static String TAB = "\t";
	final static String TAB2 = TAB+TAB;
	final static String TAB3 = TAB+TAB+TAB;
	final static String TAB4 = TAB+TAB+TAB+TAB;
	final String SEPORATOR = System.getProperty("line.separator");
	final String SEPORATOR2 = SEPORATOR+SEPORATOR;
	String beanIdStr;
	private String id;
	static Myinput myinput = new Myinput();
	static {myinput.setId("1");}
	
	public String createPerf(){
		Bean one = baseDao.get(Bean.class, Integer.valueOf(beanIdStr));
		DetachedCriteria ddc=DetachedCriteria.forClass(Permission.class);
		ddc.add(Restrictions.eq("type", "3")).add(Restrictions.eq("namespace", one.getPkg()));
		Permission p3 = baseDao.findBean(ddc);
		if(p3==null){
			p3=new Permission();
			p3.setEnable("1");
			p3.setNamespace(one.getPkg());
			p3.setType("3");
			baseDao.save(p3);
		}
		List<Permission> pl= new ArrayList<Permission>();
		Permission p2=new Permission();
		p2.setNamespace(one.getPkg());
		p2.setDescr(one.getDescr());
		p2.setParent(p3);
		p2.setEnable("1");
		p2.setType("2");
		baseDao.save(p2);
		pl.add(p2);
		Permission pf=new Permission();
		pf.setNamespace(one.getPkg());
		pf.setActionName("find"+one.getBean());
		pf.setDescr(one.getDescr());
		pf.setEnable("1");
		pf.setType("1");
		pf.setParent(p2);
		baseDao.save(pf);
		pl.add(pf);
		Permission pa=new Permission();
		pa.setNamespace(one.getPkg());
		pa.setActionName("add"+one.getBean());
		pa.setDescr("增加"+one.getDescr());
		pa.setEnable("1");
		pa.setType("0");
		pa.setParent(p2);
		baseDao.save(pa);
		pl.add(pa);
		Permission pe=new Permission();
		pe.setNamespace(one.getPkg());
		pe.setActionName("edit"+one.getBean());
		pe.setDescr("修改"+one.getDescr());
		pe.setEnable("1");
		pe.setType("0");
		pe.setParent(p2);
		baseDao.save(pe);
		pl.add(pe);
		Permission pd=new Permission();
		pd.setNamespace(one.getPkg());
		pd.setActionName("detail"+one.getBean());
		pd.setDescr("详细"+one.getDescr());
		pd.setEnable("1");
		pd.setType("0");
		pd.setParent(p2);
		baseDao.save(pd);
		pl.add(pd);
		for(Permission permission:pl){

			String type = permission.getType();
			if("0".equals(type)||"1".equals(type)){
				//对需要的Permission进行添加
				Perf p=new Perf();
				p.setBean(one);
				String descr = permission.getDescr();
				p.setDescr(descr);
				p.setPermission(permission);
				p.setOrd("");
				baseDao.save(p);
				
				//property
//				DetachedCriteria dc = DetachedCriteria.forClass(Property.class);
//				dc.add(Restrictions.eq("bean", one));
//				List<Property> pol = baseDao.find(dc);
//				//find property
//				//set by find detail
//				String actionName = permission.getActionName();
//				if (actionName.startsWith("find")) {
//					for(Property pro:pol){
//						saveFind(p, pro);
//					}
//				}else if(actionName.startsWith("add")||actionName.startsWith("edit")){
//					for(Property pro:pol){
//						String pname = pro.getProperty();
//						if(pname.equals("id")) {
//							PerfProperty pp2=new PerfProperty();
//							pp2.setProperty(pro);
//							pp2.setEditshowhide("0");
//							pp2.setPerf(p);
//							baseDao.save(pp2);
//						}else if(pname.endsWith(".id")){
//							PerfProperty pp2=new PerfProperty();
//							pp2.setProperty(pro);
//							pp2.setEditshowhide("2");
//							pp2.setPerf(p);
//							pp2.setMyinput(myinput);
//							baseDao.save(pp2);
//						}
//						else if (!Util.notEmptyString(pro.getVotype())&&!pname.contains(".")) {
//							PerfProperty pp2=new PerfProperty();
//							pp2.setProperty(pro);
//							pp2.setEditshowhide("2");
//							pp2.setPerf(p);
//							pp2.setMyinput(myinput);
//							baseDao.save(pp2);
//						}
//					}
//				}else if(actionName.startsWith("detail")
//						){
//					for(Property pro:pol){
//						if (!Util.notEmptyString(pro.getVotype())) {
//							PerfProperty pp2=new PerfProperty();
//							pp2.setProperty(pro);
//							pp2.setEditshowhide("1");
//							pp2.setPerf(p);
//							baseDao.save(pp2);
//						}
//					}
//				}
			}
		}
		return SUCCESS;
	}

	private void saveFind(Perf p, Property pro) {
		String pname = pro.getProperty();
		if (pname.equals("id")) {
			
			PerfProperty pp=new PerfProperty();
			pp.setProperty(pro);
			pp.setEditshowhide("1");
			pp.setKind("List");
			pp.setPerf(p);
			baseDao.save(pp);
		}else if(!Util.notEmptyString(pro.getVotype())){
			PerfProperty pp=new PerfProperty();
			pp.setProperty(pro);
			pp.setEditshowhide("1");
			pp.setKind("List");
			pp.setPerf(p);
			baseDao.save(pp);
			if (pname.contains(".id")||!pname.contains(".")) {
				PerfProperty pp2=new PerfProperty();
				pp2.setProperty(pro);
				pp2.setEditshowhide("2");
				pp2.setKind("Search");
				pp2.setPerf(p);
				pp2.setMyinput(myinput);
				baseDao.save(pp2);
			}
		}
	}
	
	public String create() {
		int beanId = Integer.valueOf(beanIdStr);
		Bean bean = baseDao.get(Bean.class, beanId);
		DetachedCriteria dc = DetachedCriteria.forClass(Property.class);
		dc.add(Restrictions.eq("bean", bean));
		List<Property> pl = baseDao.find(dc);
		
		final String tableName = bean.getTableName();
		final String beanName = bean.getBean();
		final String pkg = bean.getPkg();
		final String beanDescr = bean.getDescr();
		final String beanClassName = pkg+".vo."+beanName;
		String beanActionClassName = pkg+".act."+beanName+"Action";
		String beanActionName = beanName+"Action";
		//create table
		StringBuilder tableBuilder = createSQL(pl, tableName,beanName, pkg, beanDescr);
		//create struts
		StringBuilder struts = createStruts(beanName, pkg, beanActionClassName);
		//create java
		StringBuilder vo = createVo(pl, beanName, pkg);
		StringBuilder hib = createHibernate(pl, tableName, beanClassName);
		StringBuilder act = createAction(beanName, pkg, beanDescr,
				beanClassName, beanActionName);

		createFile(tableBuilder, tableName+".sql");
		createFile(struts, beanName+"struts.xml");
		createFile(vo, beanName+".java",pkg+"/vo");
		createFile(hib, beanName+".hbm.xml",pkg+"/vo");
		createFile(act, beanActionName+".java",pkg+"/act");
		
		return SUCCESS;
	}


	private StringBuilder createHibernate(List<Property> pl,
			final String tableName, final String beanClassName) {
		StringBuilder hib=new StringBuilder();
		hib.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
		.append(SEPORATOR).append("<!DOCTYPE hibernate-mapping PUBLIC \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"")
		.append(SEPORATOR).append("\"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">")
		.append(SEPORATOR).append("<hibernate-mapping>")
		.append(SEPORATOR).append(TAB).append("<class name=\"").append(beanClassName)
		.append("\" table=\"").append(tableName).append("\">")
		
		;
		for(Property property: pl){
			final String type = property.getType();
			if (Util.notEmptyString(type)) {
				final String propertyName = property.getProperty();
				if("id".equals(propertyName)){
					hib.append(SEPORATOR).append(TAB).append(TAB)
					.append("<id name=\"id\"><column name=\"id\" /><generator class=\"uuid.hex\" /></id>");
				}else {
					String votype=property.getVotype();
					if (Util.notEmptyString(votype)) {
						hib.append(SEPORATOR).append(TAB).append(TAB)
						.append("<many-to-one name=\"").append(propertyName).append("\" column=\"")
						.append(propertyName).append("\" lazy=\"false\" fetch=\"join\" not-null=\"false\" not-found=\"ignore\"/>");
					}else{
						hib.append(SEPORATOR).append(TAB).append(TAB)
						.append("<property name=\"").append(propertyName).append("\" column=\"")
						.append(propertyName).append("\"/>");
					}
				}
			}
		}
		hib.append(SEPORATOR).append(TAB).append("</class>")
		.append(SEPORATOR).append("</hibernate-mapping>");
		return hib;
	}


	private StringBuilder createVo(List<Property> pl, final String beanName,
			final String pkg) {
		StringBuilder vo=new StringBuilder();
		vo.append("package ").append(pkg).append(".vo;")
		.append(SEPORATOR).append("import common.vo.BaseTO;")
		.append(SEPORATOR).append("public class ").append(beanName).append(" extends BaseTO{");
		for(Property property : pl){
			final String type = property.getType();
			if (Util.notEmptyString(type)) {
				vo.append(SEPORATOR).append(TAB).append("public ");
				String votype=property.getVotype();
				if (!Util.notEmptyString(votype)) {
					if ("int".equals(type)) {
						votype="int";
					}else if ("varchar".equals(type)) {
						votype="String";
					}
				}
				final String propertyName = property.getProperty();
				vo.append(votype).append(" ").append(propertyName);
				if ("String".equals(votype)) {
					vo.append("=\"\"");
				}
				vo.append(";");
				final char charAt = propertyName.charAt(0);
				String c=String.valueOf(charAt);
				vo.append(SEPORATOR).append(TAB).append("public ").append(votype).append(" ")
				.append("get").append(propertyName.replaceFirst(c, c.toUpperCase()))
				.append("(){return this.").append(propertyName).append(";}");
				vo.append(SEPORATOR).append(TAB).append("public void set")
				.append(propertyName.replaceFirst(c, c.toUpperCase()))
				.append("(").append(votype).append(" one){this.").append(propertyName).append("=one;}");
			}
		}
		vo.append(SEPORATOR).append("}");
		return vo;
	}


	private StringBuilder createSQL(List<Property> pl, final String tableName, String beanName, String pkg, String beanDescr) {
		StringBuilder tableBuilder = new StringBuilder();
		tableBuilder.append("SET FOREIGN_KEY_CHECKS=0;").append(SEPORATOR).append("DROP TABLE IF EXISTS `").append(tableName).append("`;")
		.append(SEPORATOR).append("CREATE TABLE `").append(tableName).append("` (");
		for(Property property : pl){
			String propertyName = property.getProperty();
			if(!propertyName.contains(".")){
				tableBuilder.append(SEPORATOR).append("`").append(propertyName).append("` ").append(property.getType())
				.append("(").append(property.getLength()).append(") ");
				if ("id".equals(propertyName)) {
					tableBuilder.append("NOT NULL AUTO_INCREMENT,");
				}else{
					tableBuilder.append("DEFAULT NULL,");
				}
			}
		}
		tableBuilder.append(SEPORATOR).append("PRIMARY KEY (`id`))").append(SEPORATOR).append("ENGINE=InnoDB DEFAULT CHARSET=utf8;");
//		tableBuilder.append(SEPORATOR).append("insert into cfg_bean (bean,descr,isFlow,descrProperty,pkg,tableName) values ('")
//		.append(beanName).append("','").append(beanDescr).append("','0','id','").append(pkg).append("','").append(tableName).append("')");
//		for(Property property : pl){
//			tableBuilder.append(SEPORATOR)
//			.append("insert into cfg_property (bean,property,type,descr,length,votype,myinput,required,valueurl,validType,dftvalue,width,min,max,showvalue,ord) values ('")
//		.append(beanIdStr).append("','").append(property.getProperty()).append("','")
//		.append(property.getType()).append("','").append(property.getDescr()).append("','")
//		.append(property.getLength()).append("','").append(property.getVotype()).append("','")
//		.append(property.getMyinput()==null?null:property.getMyinput().getId()).append("','").append(property.getRequired()).append("','")
//		.append(property.getType()).append("','").append(property.getDescr()).append("','")
//		.append(property.getType()).append("','").append(property.getDescr()).append("','")
//		;
//		}
		return tableBuilder;
	}


	private StringBuilder createStruts(final String beanName, final String pkg,
			String beanAction) {
		StringBuilder struts = new StringBuilder();
		struts.append("<package name=\"").append(pkg).append("_").append(beanName)
		.append("\" extends=\"my-jsonInterceptor\" namespace=\"/").append(pkg)
		.append("\"> ");
		struts.append("<action name=\"select").append(beanName).append("\" class=\"")
		.append(beanAction).append("\"			method=\"select\">			<result type=\"json\">				<param name=\"root\">nl</param>			</result>		</action>");
		struts.append("<action name=\"tree").append(beanName).append("\" class=\"")
		.append(beanAction).append("\"			method=\"tree\">			<result type=\"json\">				<param name=\"root\">treeValue</param>			</result>		</action>");
		struts.append(SEPORATOR).append("<action name=\"find").append(beanName).append("\" class=\"")
		.append(beanAction).append("\" method=\"find\">")
		.append(SEPORATOR).append("<result name=\"page\">/WEB-INF/jsp/common.jsp</result>")
		.append(SEPORATOR).append("<result name=\"data\" type=\"json\"> <param name=\"root\">coeo</param> </result>")
		.append(SEPORATOR).append("<result name=\"exp\" type=\"json\"> <param name=\"root\">exportUrl</param> </result>")
		.append(SEPORATOR).append("</action>");
		String[] fs = {"add","del","edit","detail"};
		for(String f : fs){
			struts.append(SEPORATOR).append("<action name=\"").append(f).append(beanName).append("\" class=\"")
			.append(beanAction).append("\" method=\"").append(f).append("\">")
			.append(SEPORATOR).append("<result>/WEB-INF/jsp/common.jsp</result>")
			.append(SEPORATOR).append("</action>");
		}
		struts.append(SEPORATOR).append("</package>");
		return struts;
	}


	private StringBuilder createAction(final String beanName, final String pkg,
			final String beanDescr, final String beanClassName,
			String beanAction) {
		StringBuilder act=new StringBuilder();
		act.append("package ").append(pkg).append(".act;");
		
		act.append(SEPORATOR).append("import java.io.File;");
		act.append(SEPORATOR).append("import java.io.InputStream;");
		act.append(SEPORATOR).append("import java.io.UnsupportedEncodingException;import java.sql.SQLException;");
		act.append(SEPORATOR).append("import java.util.List;");
		act.append(SEPORATOR).append("import org.apache.log4j.Logger;");
		act.append(SEPORATOR).append("import org.apache.struts2.ServletActionContext;");
		act.append(SEPORATOR).append("import org.hibernate.criterion.DetachedCriteria;");
		act.append(SEPORATOR).append("import org.hibernate.criterion.Restrictions;");
		act.append(SEPORATOR).append("import ").append(beanClassName).append(";");
		act.append(SEPORATOR).append("import cfg.vo.Perf;");
		act.append(SEPORATOR).append("import cfg.vo.Quote;import common.vo.AsyncTree;");
		act.append(SEPORATOR).append("import common.act.BaseAction;import common.vo.NameValuePair;");
		act.append(SEPORATOR).append("import common.dao.BaseDao;");
		act.append(SEPORATOR).append("import common.util.Constant;");
		act.append(SEPORATOR).append("import common.util.FileUtil;");
		act.append(SEPORATOR).append("import common.util.JspUtil2;");
		act.append(SEPORATOR).append("import common.util.Util;");
		act.append(SEPORATOR).append("import common.vo.BaseTO;");
		act.append(SEPORATOR).append("import common.vo.EasyOut;");

		act.append(SEPORATOR2).append("public class ").append(beanAction).append(" extends BaseAction{")
		.append(SEPORATOR2).append(TAB).append("private Logger log = Logger.getLogger(").append(beanAction).append(".class);")
		.append(SEPORATOR2).append(TAB).append("private static String PKG=\"").append(pkg).append("\";")
		.append(SEPORATOR).append(TAB).append("private static String CLASS_NAME=\"").append(beanClassName).append("\";")
		.append(SEPORATOR).append(TAB).append("private static String BEAN_NAME=\"").append(beanName).append("\";")
		.append(SEPORATOR).append(TAB).append("private static String BEAN_DESCR=\"").append("").append("\";")
		.append(SEPORATOR).append(TAB).append("private final static String EDIT_URL = Util.editUrl(PKG,BEAN_NAME);")
		.append(SEPORATOR).append(TAB).append("private final static String ADD_URL = Util.addUrl(PKG,BEAN_NAME);")
		.append(SEPORATOR).append(TAB).append("private final static String FIND_URL = Util.findUrl(PKG,BEAN_NAME);")
		.append(SEPORATOR).append(TAB).append("private final static String DETAIL_URL = Util.detailUrl(PKG,BEAN_NAME);")
		.append(SEPORATOR).append(TAB).append("private final static String DEL_URL = Util.delUrl(PKG,BEAN_NAME);")
		.append(SEPORATOR).append(TAB).append("private final static String DETAIL_TITLE = Util.detailTitle(BEAN_DESCR);")
		.append(SEPORATOR).append(TAB).append("private final static String EDIT_TITLE = Util.editTitle(BEAN_DESCR);")
		.append(SEPORATOR).append(TAB).append("private final static String ADD_TITLE = Util.addTitle(BEAN_DESCR);")
		.append(SEPORATOR).append(TAB).append("private final static String FIND_TITLE = Util.findTitle(BEAN_DESCR);")
		.append(SEPORATOR2).append(TAB).append("private BaseDao baseDao;")
		.append(SEPORATOR).append(TAB).append("private ").append(beanName).append(" bean=new ").append(beanName).append("();")
		.append(SEPORATOR).append(TAB).append("private String isPage;")
		.append(SEPORATOR).append(TAB).append("private String from;")
		.append(SEPORATOR).append(TAB).append("private String quote;private String msg;")
		.append(SEPORATOR).append(TAB).append("private int page;")
		.append(SEPORATOR).append(TAB).append("private int rows;")
		.append(SEPORATOR).append(TAB).append("private List<AsyncTree> treeValue;private List<NameValuePair> nl;")
		.append(SEPORATOR).append(TAB).append("private String id=\"\";")
		.append(SEPORATOR).append(TAB).append("private String order;")
		.append(SEPORATOR).append(TAB).append("private String sort;")
		.append(SEPORATOR2).append(TAB).append("private EasyOut<BaseTO> coeo;")
		.append(SEPORATOR).append(TAB).append("private String jsHtml;")
		.append(SEPORATOR).append(TAB).append("private String bodyHtml;")
		.append(SEPORATOR).append(TAB).append("private String page_title=\"\";")
		.append(SEPORATOR).append(TAB).append("private String exportUrl;");
		
		act.append(SEPORATOR2).append(TAB).append("public String select() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {		setNl(JspUtil2.findSelectValue(bean, baseDao));		return SUCCESS;	}");
		
		act.append(SEPORATOR2).append(TAB).append("public String tree() throws SQLException, NoSuchFieldException, IllegalAccessException {		treeValue = JspUtil2.findTreeValue(bean, id, baseDao);		return SUCCESS;	}");
		
		act.append(SEPORATOR2).append(TAB).append("public String edit() throws SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException{")
		.append(SEPORATOR).append(TAB2).append("Perf perf = (Perf) request.getAttribute(Constant.PERF);")
		.append(SEPORATOR).append(TAB2).append("if(Util.notEmptyString(isPage)){")
		.append(SEPORATOR).append(TAB3).append("setPage_title(EDIT_TITLE);")
		.append(SEPORATOR).append(TAB3).append("if(bean!=null){")
		.append(SEPORATOR).append(TAB4).append("StringBuilder js = new StringBuilder();")
		.append(SEPORATOR).append(TAB4).append("js.append(JspUtil2.findSaveSubmitJs());")
		.append(SEPORATOR).append(TAB4).append("setJsHtml(js.toString());")
		.append(SEPORATOR2).append(TAB4).append("DetachedCriteria dc = DetachedCriteria.forClass(bean.getClass());")
		.append(SEPORATOR).append(TAB4).append("dc.add(Restrictions.eq(Constant.ID, bean.getId()));")
		.append(SEPORATOR).append(TAB4).append("bean = baseDao.findBean(dc);")
		.append(SEPORATOR2).append(TAB4).append("setBodyHtml(JspUtil2.findEditForm(EDIT_URL,bean, perf.getId()).toString());")
		.append(SEPORATOR).append(TAB3).append("}")
		.append(SEPORATOR).append(TAB2).append("}else {if(bean!=null){JspUtil2.setOTMNull(PKG,BEAN_NAME,bean,baseDao);baseDao.update(bean);setBodyHtml(Constant.UPDATE_SUCCESS);}}return SUCCESS;}")
		;
		
		act.append(SEPORATOR2).append(TAB).append("public String detail() throws Exception{		setPage_title(DETAIL_TITLE);		if(bean!=null){			Perf perf = (Perf) request.getAttribute(Constant.PERF);						final Class<?> clazz = bean.getClass();			DetachedCriteria dc = DetachedCriteria.forClass(clazz);			dc.add(Restrictions.eq(Constant.ID, bean.getId()));			bean = baseDao.findBean(dc);						final StringBuilder findDetailTable = JspUtil2.findDetailTable(bean,perf.getId());			DetachedCriteria dc2 = DetachedCriteria.forClass(Quote.class);			dc2.add(Restrictions.eq(\"oranPerf\", perf));			Quote quote = baseDao.findBean(dc2);			StringBuilder res = JspUtil2.quotePage(findDetailTable, quote,bean);			setBodyHtml(res.toString());		}		return SUCCESS;	}");
		
		act.append(SEPORATOR2).append(TAB).append("public String del() {if(bean!=null){baseDao.delete(bean);setMsg(Constant.DEL_SUCCESS);}return SUCCESS;}");
		
		act.append(SEPORATOR2).append(TAB).append("public String find() throws IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException, InstantiationException, UnsupportedEncodingException {" +
				"Perf listperf = (Perf) request.getAttribute(Constant.PERF);" +
				"if(\"1\".equals(isPage)){" +
				"listPage(listperf);return \"page\";" +
				"}else if(\"2\".equals(isPage)){" +
				"export(listperf);return \"exp\";" +
				"}else {listData(listperf);return  \"data\";}}");
		
		act.append(SEPORATOR2).append(TAB).append("private void listData(Perf listperf) throws NoSuchFieldException," +
				"ClassNotFoundException, IllegalAccessException {" +
				"setCoeo(new EasyOut<BaseTO>());" +
				"DetachedCriteria dc = JspUtil2.createRectiDc(bean,CLASS_NAME,listperf.getId());" +
				"getCoeo().setTotal(baseDao.findCount(dc));" +
				"if (Util.emptyString(sort)) {sort = \"id\";order = \"asc\";}" +
				"dc = JspUtil2.createRectiDc(sort, order,bean,CLASS_NAME,listperf.getId());" +
				"List<BaseTO> cl = baseDao.findWithPage2(dc, (page - 1) * rows, rows);" +
				"getCoeo().setRows(cl);" +
				"}");
		
		act.append(SEPORATOR2).append(TAB).append("private void export(Perf listperf) throws NoSuchFieldException," +
				"ClassNotFoundException, IllegalAccessException,UnsupportedEncodingException {" +
				"if (Util.emptyString(sort)) {sort = \"id\";order = \"asc\";}" +
				"DetachedCriteria dc = JspUtil2.createRectiDc(sort, order,bean,CLASS_NAME,listperf.getId());" +
				"List<BaseTO> cl = baseDao.find(dc);" +
				"InputStream excelStream = JspUtil2.drawXls(CLASS_NAME,listperf.getId(),cl);" +
				"String realPath = ServletActionContext.getServletContext().getRealPath(\"/\")+\"upload/\";" +
				"String generateWord = Util.generateWord();" +
				"String fileName = generateWord+\".xls\";" +
				"setExportUrl(realPath+fileName);" +
				"File file = new File(getExportUrl());" +
				"FileUtil.inputstreamToFile(excelStream, file);}");
		
		act.append(SEPORATOR2).append(TAB).append("private void listPage(Perf listperf) throws ClassNotFoundException,			IllegalAccessException, NoSuchFieldException,		InstantiationException {		setPage_title(FIND_TITLE);		StringBuilder body = new StringBuilder();		StringBuilder plainToolbar = new StringBuilder();		plainToolbar = JspUtil2.plainToolbarHtml();		int quotePropertyId=0;		Quote qb=null;		if (Util.notEmptyString(quote)) {			qb = baseDao.get(Quote.class, Integer.valueOf(quote));			quotePropertyId = qb.getQuoteProperty().getId();		}		StringBuilder tb = JspUtil2.findToolBar(plainToolbar,				new StringBuilder(), from, listperf.getId(),bean,quotePropertyId);		StringBuilder ta = JspUtil2.findListTable();		body.append(tb).append(ta);		setBodyHtml(body.toString());		StringBuilder js = new StringBuilder();		StringBuilder sear = JspUtil2.findSearJs(FIND_URL,listperf.getId());		StringBuilder searInitJs = JspUtil2.findLaterSearInitJs();		StringBuilder deljs = JspUtil2.findDelJs(DEL_URL);		StringBuilder addjs = JspUtil2.findAddJs(ADD_URL + Constant.PAGE_PARAM,qb,bean);		StringBuilder detailjs = JspUtil2.findDetailJs(DETAIL_URL);		StringBuilder editjs = JspUtil2.findEditJs(Util.editUrl(PKG, BEAN_NAME) + Constant.PAGE_PARAM);		StringBuilder exportXlsjs = JspUtil2.findexportXlsJs(				Util.findUrl(PKG, BEAN_NAME) + Constant.PAGE_PARAM2,listperf.getId());		if (Util.notEmptyString(from)) {			js.append(JspUtil2.findClsJs(from, CLASS_NAME));		}		js.append(searInitJs);		js.append(JspUtil2.findPopJs()).append(sear).append(editjs)				.append(deljs).append(addjs).append(detailjs)				.append(exportXlsjs);		setJsHtml(js.toString());	}");
		
		act.append(SEPORATOR2).append(TAB).append("public String add() throws Exception {		Perf perf = (Perf) request.getAttribute(Constant.PERF);		if (Util.notEmptyString(isPage)) {			setPage_title(ADD_TITLE);			StringBuilder js = new StringBuilder();			js.append(JspUtil2.findSaveSubmitJs());			js.append(JspUtil2.findPopJs());			setJsHtml(js.toString());			StringBuilder body = new StringBuilder();			 			int quotePropertyId=0;			if (Util.notEmptyString(quote)) {				Quote qb = baseDao.get(Quote.class, Integer.valueOf(quote));				quotePropertyId = qb.getQuoteProperty().getId();			}			body.append(JspUtil2.findAddForm(ADD_URL, bean, perf.getId(),quotePropertyId));			setBodyHtml(body.toString());		} else {			if (bean != null) {				baseDao.save(bean);	setBodyHtml(Constant.ADD_SUCCESS);		}		}		return SUCCESS;	}");
		
		act.append(SEPORATOR2).append(TAB).append("public void setBaseDao(BaseDao baseDao) {this.baseDao = baseDao;}");
		act.append(SEPORATOR2).append(TAB).append("public String getJsHtml() {return jsHtml;}");
		act.append(SEPORATOR2).append(TAB).append("public void setJsHtml(String jsHtml) {this.jsHtml = jsHtml;}");
		act.append(SEPORATOR2).append(TAB).append("public String getBodyHtml() {return bodyHtml;}");
		act.append(SEPORATOR2).append(TAB).append("public void setBodyHtml(String bodyHtml) {this.bodyHtml = bodyHtml;}");
		act.append(SEPORATOR2).append(TAB).append("public int getPage() {return page;}");
		act.append(SEPORATOR2).append(TAB).append("public void setPage(int page) {this.page = page;}");
		act.append(SEPORATOR2).append(TAB).append("public int getRows() {return rows;}");
		act.append(SEPORATOR2).append(TAB).append("public void setRows(int rows) {this.rows = rows;}");
		act.append(SEPORATOR2).append(TAB).append("public String getOrder() {return order;}");
		act.append(SEPORATOR2).append(TAB).append("public void setOrder(String order) {this.order = order;}");
		act.append(SEPORATOR2).append(TAB).append("public String getSort() {return sort;}");
		act.append(SEPORATOR2).append(TAB).append("public void setSort(String sort) {this.sort = sort;}");
		act.append(SEPORATOR2).append(TAB).append("public EasyOut<BaseTO> getCoeo() {return coeo;}");
		act.append(SEPORATOR2).append(TAB).append("public void setCoeo(EasyOut<BaseTO> coeo) {this.coeo = coeo;}");
		act.append(SEPORATOR2).append(TAB).append("public void setBean(").append(beanName).append(" bean) {this.bean = bean;}");
		act.append(SEPORATOR2).append(TAB).append("public ").append(beanName).append(" getBean() {return bean;}");
		act.append(SEPORATOR2).append(TAB).append("public void setPage_title(String page_title) {this.page_title = page_title;}");
		act.append(SEPORATOR2).append(TAB).append("public String getPage_title() {return page_title;}");
		act.append(SEPORATOR2).append(TAB).append("public void setFrom(String from) {this.from = from;}");
		act.append(SEPORATOR2).append(TAB).append("public String getFrom() {return from;}");
		act.append(SEPORATOR2).append(TAB).append("public void setExportUrl(String exportUrl) {this.exportUrl = exportUrl;}");
		act.append(SEPORATOR2).append(TAB).append("public String getExportUrl() {return exportUrl;}");
		act.append(SEPORATOR2).append(TAB).append("public String getIsPage() {return isPage;}");
		act.append(SEPORATOR2).append(TAB).append("public void setIsPage(String isPage) {this.isPage = isPage;}public List<AsyncTree> getTreeValue() {		return treeValue;	}	public void setTreeValue(List<AsyncTree> treeValue) {		this.treeValue = treeValue;	}public String getId() {		return id;	}	public void setId(String id) {		this.id = id;	}")
		.append("public String getQuote() {		return quote;	}	public void setQuote(String quote) {		this.quote = quote;	}")
		.append("public List<NameValuePair> getNl() {		return nl;	}	public void setNl(List<NameValuePair> nl) {		this.nl = nl;	}")
		.append("public String getMsg() {		return msg;	}	public void setMsg(String msg) {		this.msg = msg;	}");
		act.append(SEPORATOR).append("}");
		return act;
	}


	private void createFile(StringBuilder tableBuilder, final String fileName) {
		createFile(tableBuilder, fileName,"");
	}
	private void createFile(StringBuilder tableBuilder, final String fileName,String path) {
		byte tb[] = tableBuilder.toString().getBytes();
		InputStream tbis = new ByteArrayInputStream(tb);
		String realPath = ServletActionContext.getServletContext().getRealPath("/")+"upload/"+path+"/";
		File pathFile=new  File(realPath);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		File file = new File(realPath+fileName);
		FileUtil.inputstreamToFile(tbis, file);
	}


	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	public String getBeanIdStr() {
		return beanIdStr;
	}


	public void setBeanIdStr(String beanIdStr) {
		this.beanIdStr = beanIdStr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	
	
}

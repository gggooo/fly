package cfg.act;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cfg.vo.Bean;
import cfg.vo.Myinput;
import cfg.vo.Perf;
import cfg.vo.Permission;
import cfg.vo.Property;

import common.act.BaseAction;
import common.dao.BaseDao;
import common.util.FileUtil;
import common.util.LocalConnetionPool;
import common.util.Util;
import common.vo.AsyncTree;
import common.vo.BaseTO;
import common.vo.EasyOut;
import common.vo.NameValuePair;

public class BeanAction extends CfgAction{
	
	private Logger log = Logger.getLogger(BeanAction.class);

	final String SEPORATOR = System.getProperty("line.separator");
	final static String TAB = "\t";


	public String add() throws IllegalArgumentException, SecurityException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException {
		super.add();
		if (1==getCfg_res()&&!Util.notEmptyString(isPage)) {
			Property idp = new Property();
			idp.setBean((Bean) getBean());
			idp.setDescr("ID");
			idp.setProperty("id");
			idp.setType("int");
			idp.setLength("11");
			baseDao.save(idp);

			Property enabledp = new Property();
			enabledp.setBean((Bean) getBean());
			enabledp.setDescr("有效?");
			enabledp.setProperty("enable");
			enabledp.setType("varchar");
			enabledp.setLength("2");
			Myinput radio = new Myinput();
			radio.setId("15");
			enabledp.setMyinput(radio);
			enabledp.setDftvalue("1");
			enabledp.setShowvalue("enable");
			enabledp.setWidth("50");
			enabledp.setOrd(90);
			baseDao.save(enabledp);

			Property cddp = new Property((Bean) getBean(), "createDate",
					"varchar", "创建日期", "20", 90, "100");
			baseDao.save(cddp);

			Property cmdp = new Property((Bean) getBean(), "createMember",
					"int", "创建人", "11", 90, "50");
			cmdp.setVotype("cfg.vo.Member");
			baseDao.save(cmdp);

			Property cmiddp = new Property((Bean) getBean(), "createMember.id",
					"", "创建人ID", "", 90, "80");
			baseDao.save(cmiddp);
			Property cmdescrdp = new Property((Bean) getBean(),
					"createMember.descr", "", "创建人描述", "", 90, "80");
			baseDao.save(cmdescrdp);

			Property uddp = new Property((Bean) getBean(), "updateDate",
					"varchar", "更新日期", "20", 90, "100");
			baseDao.save(uddp);

			Property umdp = new Property((Bean) getBean(), "updateMember",
					"int", "更新人", "11", 90, "50");
			umdp.setVotype("cfg.vo.Member");
			baseDao.save(umdp);

			Property umiddp = new Property((Bean) getBean(), "updateMember.id",
					"", "更新人ID", "", 90, "80");
			baseDao.save(umiddp);
			Property umdescrdp = new Property((Bean) getBean(),
					"updateMember.descr", "", "更新人描述", "", 90, "80");
			baseDao.save(umdescrdp);
			int ind = 0;
			while (ind < 10) {
				Property wdp = new Property();
				wdp.setBean((Bean) getBean());
				wdp.setDescr("扩展" + ind);
				wdp.setProperty("ws" + ind);
				wdp.setType("varchar");
				wdp.setLength("255");
				wdp.setOrd(99);
				baseDao.save(wdp);
				ind++;
			}

			Property usrct = new Property((Bean) getBean(), "usrct",
					"varchar", "用户端", "4", 99, "");
			baseDao.save(usrct);
		}
		return SUCCESS;
	}
	
	
	public String cfg_c_select_votype(){
		nl=new ArrayList<NameValuePair>();
		DetachedCriteria dc = DetachedCriteria.forClass(Bean.class);
		List<Bean> bl=baseDao.find(dc);
		for(Bean b :bl){
			String className = b.getPkg()+".vo."+b.getBean();
			nl.add(new NameValuePair(b.getDescr()+" "+className, className));
		}
		return SUCCESS;
	}
	
	public String c_bean_createDb() throws SQLException, IllegalArgumentException, IllegalAccessException {
		if(bean!=null){
			bean = baseDao.get(Bean.class, ((Bean)bean).getId());
			DetachedCriteria dc = DetachedCriteria.forClass(Property.class);
			dc.add(Restrictions.eq("bean", bean));
			dc.addOrder(Order.asc("ord"));
			List<Property> pl=baseDao.find(dc);
			
			String tableName =  ((Bean)bean).getTableName();
			StringBuilder createSQL = createSQL(pl,tableName);
			Connection con = LocalConnetionPool.getConnection();
			PreparedStatement ps = con.prepareStatement("SET FOREIGN_KEY_CHECKS=0;");
			ps = con.prepareStatement("DROP TABLE IF EXISTS `"+tableName+"`");
			ps.execute();
			ps = con.prepareStatement(createSQL.toString());
			ps.execute();
			ps.close();
			con.close();
			setBodyHtml("ok"+createSQL);
		}
		return SUCCESS;
	}
	public String c_bean_createVo2() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if(getBean()!=null){
			DetachedCriteria dc = DetachedCriteria.forClass(Property.class);
			dc.add(Restrictions.eq("bean", getBean()));
			dc.addOrder(Order.asc("ord"));
			List<Property> pl=baseDao.find(dc);
			setBean(baseDao.get(Bean.class,  ((Bean)bean).getId()));
			
			String pkg = ((Bean)getBean()).getPkg();
			String beanName = ((Bean)getBean()).getBean();
			String tableName = ((Bean)getBean()).getTableName();
			String voFileName=beanName+".java";
			StringBuilder voFileContent = createVo(pl,beanName,pkg);
			//创建文件，并返回文件名
			String sorceFolder = Util.getPorjectPath()+"/WEB-INF/src/";
			String classesFolder = Util.getPorjectPath()+"/WEB-INF/classes/";
			String voFileNameWithPath = createFile2(voFileContent, voFileName,sorceFolder + pkg + "/vo/");
			// compile下面开始编译这个Store.java
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager fileMgr = compiler.getStandardFileManager(
					null, null, null);
			Iterable units = fileMgr.getJavaFileObjects(voFileNameWithPath);
			Iterable<String> options = Arrays.asList("-sourcepath",sorceFolder,"-d",classesFolder);
			CompilationTask t = compiler.getTask(null, fileMgr, null, options,
					null, units);
			 boolean result = t.call(); 
		       if (result) { 
		          System.out.println("Compile succeeded!"); 
		       }  else {
		          System.out.println("Compile failed!"); 
		       }
			fileMgr.close();
//			URL[] urls = new URL[] {new URL("file:/"+Util.getPorjectPath()+"/WEB-INF/classes/")};
//			 URLClassLoader url = new URLClassLoader(urls); 
//			 Class clazz = url.loadClass(pkg+"/vo/"+beanName);  
//	            //实例化对象  
//	            clazz.newInstance();  
			
			
			StringBuilder hib = createHibernate(pl, tableName, pkg+".vo."+beanName);
			createFile2(hib, beanName+".hbm.xml",classesFolder+pkg+"/vo/");
			setBodyHtml("ok");
		}
		return SUCCESS;
	}

	public String c_bean_createVo() throws IllegalArgumentException, IllegalAccessException {
		if(getBean()!=null){
			DetachedCriteria dc = DetachedCriteria.forClass(Property.class);
			dc.add(Restrictions.eq("bean", getBean()));
			dc.addOrder(Order.asc("ord")).addOrder(Order.asc("id"));
			List<Property> pl=baseDao.find(dc);
			setBean(baseDao.get(Bean.class,  ((Bean)bean).getId()));
			;
			
			String pkg = ((Bean)getBean()).getPkg();
			String beanName = ((Bean)getBean()).getBean();
			String tableName = ((Bean)getBean()).getTableName();
			String voFile=beanName+".java";
			createFile(createVo(pl,beanName,pkg), voFile,pkg+"/vo");
			StringBuilder hib = createHibernate(pl, tableName, pkg+".vo."+beanName);
			createFile(hib, beanName+".hbm.xml",pkg+"/vo");
			setBodyHtml("ok");
		}
		return SUCCESS;
	}
	

	private String createFile2(StringBuilder tableBuilder, final String fileName,String path) {
		byte tb[] = tableBuilder.toString().getBytes();
		InputStream tbis = new ByteArrayInputStream(tb);
		File pathFile=new  File(path);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		File file = new File(path+fileName);
		FileUtil.inputstreamToFile(tbis, file);
		return path+fileName;
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
					}else if ("text".equals(type)) {
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
	
	public String c_bean_createPermission() throws IllegalArgumentException, IllegalAccessException {
		if(getBean()!=null){
			Bean one = (Bean) baseDao.get(clazz, (String)idField.get(getBean()));
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
			Permission pdel=new Permission();
			pdel.setNamespace(one.getPkg());
			pdel.setActionName("del"+one.getBean());
			pdel.setDescr("删除"+one.getDescr());
			pdel.setEnable("1");
			pdel.setType("0");
			pdel.setParent(p2);
			baseDao.save(pdel);
			Permission p_tree=new Permission();
			p_tree.setNamespace(one.getPkg());
			p_tree.setActionName("tree"+one.getBean());
			p_tree.setDescr("树_"+one.getDescr());
			p_tree.setEnable("1");
			p_tree.setType("0");
			p_tree.setParent(p2);
			baseDao.save(p_tree);
			Permission p_select=new Permission();
			p_select.setNamespace(one.getPkg());
			p_select.setActionName("select"+one.getBean());
			p_select.setDescr("选择_"+one.getDescr());
			p_select.setEnable("1");
			p_select.setType("0");
			p_select.setParent(p2);
			baseDao.save(p_select);
			
			
			//添加Permission对应的Perf
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
				}
			}
			
			setBodyHtml("ok");
		}
		return SUCCESS;
	}
	private StringBuilder createSQL(List<Property> pl, final String tableName) {
		StringBuilder tableBuilder = new StringBuilder();
		tableBuilder.append("CREATE TABLE `").append(tableName).append("` (");
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
		if(!Util.notEmptyList(pl)){
			tableBuilder.append("`id` int(11) NOT NULL AUTO_INCREMENT,");
		}
		tableBuilder.append(SEPORATOR).append("PRIMARY KEY (`id`))").append(SEPORATOR).append("ENGINE=InnoDB DEFAULT CHARSET=utf8");
		return tableBuilder;
	}
	private StringBuilder createSQL2(List<Property> pl, final String tableName) {
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
		return tableBuilder;
	}

	
	
	public String del() {
		if(getBean()!=null){
			DetachedCriteria dc = DetachedCriteria.forClass(Property.class);
			dc.add(Restrictions.eq("bean", getBean()));
			List<Property> pl=baseDao.find(dc);
			
			for(Property p:pl){
				baseDao.delete(p);
			}
			baseDao.delete(getBean());
			
		}
		return SUCCESS;
	}
	
	public String tree() throws SQLException, SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		treeValue.addAll(beanTree(id));
		return SUCCESS;
	}

	private List<AsyncTree> beanTree(String id) {
		List<AsyncTree> res=new ArrayList<AsyncTree>();
		if(!Util.notEmptyString(id)){
			DetachedCriteria dc = DetachedCriteria.forClass(Bean.class);
			List<Bean> bl = baseDao.find(dc);
			List<String> sl = new ArrayList<String>(); 
			for(Bean b:bl){
				if (!sl.contains(b.getPkg())) {
					sl.add(b.getPkg());
				}
			}
			for(String s : sl){
				AsyncTree at = new AsyncTree();
				at.setId("pkg:"+s);
				at.setText(s);
				at.setState("open");
				at.setChildren(beanTree("pkg:"+s));
				res.add(at);
			}
		}else if (id.startsWith("pkg:")) {
			DetachedCriteria dc = DetachedCriteria.forClass(Bean.class);
			dc.add(Restrictions.eq("pkg", id.replace("pkg:", "")));
			List<Bean> bl = baseDao.find(dc);
			for(Bean b:bl){
				AsyncTree at = new AsyncTree();
				at.setId(String.valueOf(b.getId()));
				at.setText(b.getBean()+":"+b.getDescr());
				at.setState("open");
				res.add(at);
			}
		}
		return res;
	}

}

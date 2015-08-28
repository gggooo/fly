package cfg.act;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cfg.vo.Bean;
import cfg.vo.FilterProperty;
import cfg.vo.Myinput;
import cfg.vo.PerfProperty;
import cfg.vo.Property;

import common.act.BaseAction;
import common.dao.BaseDao;
import common.util.Constant;
import common.util.LocalConnetionPool;
import common.util.Util;
import common.vo.AsyncTree;
import common.vo.BaseTO;
import common.vo.EasyOut;

public class PropertyAction extends CfgAction {

	private Logger log = Logger.getLogger(PropertyAction.class);

	public String edit() throws SecurityException, IllegalArgumentException,
			ClassNotFoundException, NoSuchFieldException,
			IllegalAccessException, SQLException {
		DetachedCriteria dc = DetachedCriteria.forClass(bean.getClass());
		dc.add(Restrictions.eq(Constant.ID, idField.get(bean)));
		Property old = baseDao.findBean(dc);
		super.edit();
		// 相应修改数据库的表中的字段
		if (1==getCfg_res()&&!Util.notEmptyString(isPage)) {
			Property oldp = (Property) old;
			String lengthOld = oldp.getLength();
			String propertyNameOld = oldp.getProperty();
			String typeOld = oldp.getType();
			Property newp = (Property) bean;
			String lengthNew = newp.getLength();
			String propertyNameNew = newp.getProperty();
			String typeNew = newp.getType();
			if (lengthOld == null)
				lengthOld = "";
			if (typeOld == null)
				typeOld = "";
			if (propertyNameOld.equals(propertyNameNew)) {
				if (!lengthOld.equals(lengthNew) || !typeOld.equals(typeNew)) {
					// alter table 表名 modify 列名 新类型 新参数【修改列类型】
					// 例：alter table test modify gender char(4) not null
					// default '';
					Connection con = null;
					PreparedStatement ps = null;
					try {
						con = LocalConnetionPool.getConnection();
						Bean beanBean = baseDao.get(Bean.class, oldp.getBean()
								.getId());
						if (!Util.notEmptyString(lengthNew)) {
							lengthNew = "0";
						}
						String sql = "alter table " + beanBean.getTableName()
								+ " modify " + propertyNameOld + " " + typeNew
								+ "(" + lengthNew + ")";
						ps = con.prepareStatement(sql);
						ps.execute();
					} finally {
						ps.close();
						con.close();
					}
				}

			} else {
				// alter table 表名 change 旧列名 新列名 新类型 新参数【修改列名和列类型】
				// 例：alter table test change pid uid int unsigned not null
				// default 0;
				Connection con = null;
				PreparedStatement ps = null;
				try {
					con = LocalConnetionPool.getConnection();
					Bean beanBean = baseDao.get(Bean.class, oldp.getBean()
							.getId());
					String sql = "alter table " + beanBean.getTableName()
							+ " change " + propertyNameOld + " "
							+ propertyNameNew + " " + typeNew + "(" + lengthNew
							+ ")";
					ps = con.prepareStatement(sql);
					ps.execute();
				} finally {
					ps.close();
					con.close();
				}
			}

		}

		return SUCCESS;
	}

	public String add_() throws IllegalArgumentException, SecurityException,
			ClassNotFoundException, IllegalAccessException,
			NoSuchFieldException, SQLException {
		super.add();
		if (1==getCfg_res()&&!Util.notEmptyString(isPage)) {
			Property newp = (Property) bean;
			if (newp.getProperty().indexOf(".") < 0) {
				String lengthNew = newp.getLength();
				String propertyNameNew = newp.getProperty();
				String typeNew = newp.getType();
				// ①alter table 表名 add 列名 列类型 列参数【加的列在表的最后面】
				// 例：alter table test add username char(20) not null default
				// '';
				// alter table test add birth date not null default
				// '0000-00-00';

				// ②alter table 表名 add 列名 列类型 列参数 after 某列【把新列加在某列后面】
				// 例：alter table test add gender char(1) not null default ''
				// after username;

				// ③alter table 表名 add 列名 列类型 列参数 first【把新列加在最前面】
				// 例：alter table test add pid int not null default 0 first;
				Connection con = null;
				PreparedStatement ps = null;
				try {
					con = LocalConnetionPool.getConnection();
					Bean beanBean = baseDao.get(Bean.class, newp.getBean()
							.getId());
					String sql = "alter table " + beanBean.getTableName()
							+ " add " + propertyNameNew + " " + typeNew + "("
							+ lengthNew + ")";
					ps = con.prepareStatement(sql);
					ps.execute();
				} catch (SQLException e) {

				} finally {
					ps.close();
					con.close();
				}
			}
		}
		return SUCCESS;
	}

	public String del() throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		if (bean != null) {
			bean = baseDao.get(clazz, String.valueOf(idField.get(bean)));
			Property p = (Property) bean;
			String propertyName = p.getProperty();
			Bean beanBean = baseDao.get(Bean.class, p.getBean().getId());

			// del perfproeprty
			delProperty(baseDao, bean);

			// if(propertyName.indexOf(".")<0){
			// Connection con = null;
			// PreparedStatement ps = null;
			// try{
			// con = LocalConnetionPool.getConnection();
			// String sql =
			// "alter table "+beanBean.getTableName()+" drop "+propertyName;
			// ps = con.prepareStatement(sql);
			// ps.execute();
			// }finally{
			// ps.close();
			// con.close();
			// }
			// setMsg(Constant.DEL_SUCCESS+"数据表中的相应字段已删除！");
			// }
			setMsg(Constant.DEL_SUCCESS);
		} else {
			setMsg(Constant.DEL_ERR);
		}
		return SUCCESS;
	}

	private void delProperty(BaseDao baseDao, BaseTO bean) {
		DetachedCriteria dc = DetachedCriteria.forClass(PerfProperty.class);
		dc.add(Restrictions.eq("property", bean));
		List<PerfProperty> ppl = baseDao.find(dc);
		for (PerfProperty pp : ppl) {
			baseDao.delete(pp);
		}
		dc = DetachedCriteria.forClass(FilterProperty.class);
		dc.add(Restrictions.eq("property", bean));
		List<FilterProperty> fpl = baseDao.find(dc);
		for (FilterProperty fp : fpl) {
			baseDao.delete(fp);
		}
		baseDao.delete(bean);
	}

	public String tree() throws SQLException, SecurityException,
			IllegalArgumentException, NoSuchFieldException,
			IllegalAccessException {
		treeValue = new ArrayList<AsyncTree>();
		if (!Util.notEmptyString(id)) {
			DetachedCriteria dc = DetachedCriteria.forClass(Bean.class);
			List<Bean> bl = baseDao.find(dc);
			List<String> sl = new ArrayList<String>();
			for (Bean b : bl) {
				if (!sl.contains(b.getPkg())) {
					sl.add(b.getPkg());
				}
			}
			for (String s : sl) {
				AsyncTree at = new AsyncTree();
				at.setId("pkg:" + s);
				at.setText(s);
				at.setState("closed");
				treeValue.add(at);
			}
		} else if (id.startsWith("pkg:")) {
			DetachedCriteria dc = DetachedCriteria.forClass(Bean.class);
			dc.add(Restrictions.eq("pkg", id.replace("pkg:", "")));
			List<Bean> bl = baseDao.find(dc);
			for (Bean b : bl) {
				AsyncTree at = new AsyncTree();
				at.setId("bean:" + String.valueOf(b.getId()));
				at.setText(b.getBean() + ":" + b.getDescr());
				at.setState("closed");
				treeValue.add(at);
			}
		} else if (id.startsWith("bean:")) {
			DetachedCriteria dc = DetachedCriteria.forClass(Property.class);
			String beanId = id.replace("bean:", "");
			Bean b = baseDao.get(Bean.class, Integer.valueOf(beanId));
			dc.add(Restrictions.eq("bean", b));
			List<Property> bl = baseDao.find(dc);
			for (Property bx : bl) {
				AsyncTree at = new AsyncTree();
				at.setId(String.valueOf(bx.getId()));
				at.setText(bx.getProperty() + ":" + bx.getDescr());
				at.setState("open");
				treeValue.add(at);
			}
		}
		return SUCCESS;
	}

}
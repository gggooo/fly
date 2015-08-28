package common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cfg.vo.Cons;

import common.vo.BaseTO;
import common.vo.NameValuePair;


public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {
	
	private Logger log = Logger.getLogger(BaseDaoImpl.class);
	/**
	 * 保存实例
	 */
	public void save(BaseTO oTO) {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
//		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
//		org.hibernate.classic.Session openSession = sessionFactory.openSession();
//		openSession.beginTransaction()
		hibernateTemplate.save(oTO);
	}
	

	public void saveMany(List<? extends BaseTO> ol) {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int i = 0;
        for (BaseTO o : ol) {
            if (session.contains(o)) {
                continue;
            } else {
                session.save(o);
                i++;
            }

            if (i % 2000 == 0) {
                session.getTransaction().commit();
                session.flush();
                session.clear();
                session.beginTransaction();
            }

        }

        session.getTransaction().commit();
        session.close();
	}

	/**
	 * 修改实例
	 */
	public void update(BaseTO oTO) {
		getHibernateTemplate().update(oTO);
	}

	/**
	 * 保存或修改实便
	 */
	public void saveOrUpdate(BaseTO oTO) {
		getHibernateTemplate().saveOrUpdate(oTO);
	}

	/**
	 * 删除实例
	 */
	public <T> void delete(T oTO) {
		getHibernateTemplate().delete(oTO);
	}

	/**
	 * 取得丿ت实例
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> clazz, Serializable id) {
		return (T) getHibernateTemplate().get(clazz, id);
	}

	/**
	 * 查找承܉实例
	 * 
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class clazz) {
		return getHibernateTemplate().find("from " + clazz.getName());
	}

	/**
	 * hql语句查询,不带参数
	 * 
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql) {
		return getHibernateTemplate().find(hql);
	}

	/**
	 * hql语句查询,取得第一条查询结枿
	 * 
	 * @param <T>
	 */
	@SuppressWarnings( { "unchecked" })
	public <T> T findBean(String hql) {
		T result = null;
		List<T> list = getHibernateTemplate().find(hql);
		if (list != null && !list.isEmpty()) {
			result = list.get(0);
		}
		return result;
	}

	/**
	 * hql语句查询,取得第一条查询结枿
	 * 
	 * @param <T>
	 */
	@SuppressWarnings( { "unchecked" })
	public <T> T findBean(String hql, Object[] parameter) {
		T result = null;
		List<T> list = getHibernateTemplate().find(hql, parameter);
		if (list != null && !list.isEmpty()) {
			result = list.get(0);
		}
		return result;
	}

	/**
	 * hql语句查询,带一个参敿
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql, Object parameter) {
		return getHibernateTemplate().find(hql, parameter);
	}

	/**
	 * hql语句查询,带妅个参敿
	 * 
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql, Object[] parameter) {
		return getHibernateTemplate().find(hql, parameter);
	}

	/**
	 * 命名查询,不带参数
	 */
	@SuppressWarnings("unchecked")
	public List findByNamedQuery(String queryName) {
		return getHibernateTemplate().findByNamedQuery(queryName);
	}

	/**
	 * 命名查询,带一个参敿
	 */
	@SuppressWarnings("unchecked")
	public List findByNamedQuery(String queryName, Object parameter) {
		return getHibernateTemplate().findByNamedQuery(queryName, parameter);
	}

	/**
	 * 命名查询,带妅个参敿
	 */
	@SuppressWarnings("unchecked")
	public List findByNamedQuery(String queryName, Object[] parameter) {
		return getHibernateTemplate().findByNamedQuery(queryName, parameter);
	}

	/**
	 * DetachedCriteria方式查询
	 * 
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> find(final DetachedCriteria dc) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria = dc.getExecutableCriteria(session);
				return criteria.list();
			}
		});
	}

	/**
	 * DetachedCriteria方式查询
	 * 
	 * @param <T>
	 */
	public int findCount(final DetachedCriteria dc) {
		Object execute = getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = dc.getExecutableCriteria(session);
						criteria.setProjection(Projections.rowCount());
						return criteria.uniqueResult();
					}
				});
		if (execute == null) {
			return 0;
		}
		return Integer.valueOf(String.valueOf(execute));
	}

	/**
	 * DetachedCriteria方式查询
	 * 
	 * @param <T>
	 */
	public int findMax(final DetachedCriteria dc, final String property) {
		Object execute = getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = dc.getExecutableCriteria(session);
						criteria.setProjection(Projections.max(property));
						return criteria.uniqueResult();
					}
				});
		if (execute == null) {
			return 0;
		}
		return (Integer) execute;
	}	
	@SuppressWarnings("unchecked")
	public <T> List<T> findWithPage2(final DetachedCriteria dc,final int first,final int rows) {
			List<T> findByCriteria = getHibernateTemplate().findByCriteria(dc, first, rows);
			return findByCriteria;
	}

	/**
	 * 执行没有返回值的SQL语句
	 */
	public void executeSQL(final String sql, final Object[] param) {
		try {
			getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					Query query = session.createQuery(sql);
					if (param != null) {
						for (int i = 0; i < param.length; i++) {
							query.setParameter(i, param[i]);
						}
					}
					query.executeUpdate();
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 执行没有返回值的SQL语句
	 */
	public <T> List<T> querySQL(final String sql, final Object[] param) {
		try {
			getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					Query query = session.createQuery(sql);
					if (param != null) {
						for (int i = 0; i < param.length; i++) {
							query.setParameter(i, param[i]);
						}
					}
					return query.list();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public ClassMetadata getClassMetadata(final Class entityClass) {
		return (ClassMetadata) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						SessionFactory sessionFactory = session
								.getSessionFactory();
						ClassMetadata classMetadata = sessionFactory
								.getClassMetadata(entityClass);
						return classMetadata;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public <T> T findBean(final DetachedCriteria dc) {
		return (T) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria = dc.getExecutableCriteria(session);
				List<T> list = criteria.list();
				if (list != null && list.size() > 0) {
					return list.get(0);
				}
				return null;
			}
		});
	}

	public <T> void deleteById(Class<T> clazz, int id) {
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		dc.add(Restrictions.eq("id", id));
		delete(findBean(dc));

	}

	public String getValue(String name) {
		return name;
	}



	public List<NameValuePair> findConstant(int domainId) {
		List<NameValuePair> res = new ArrayList<NameValuePair>();
		DetachedCriteria dc = DetachedCriteria.forClass(Cons.class);
		dc.add(Restrictions.eq("domain", get(cfg.vo.Domain.class, domainId)));
		dc.add(Restrictions.eq("enable", "1"));
		List<Cons> findBean = find(dc);
		for(Cons cons : findBean){
			NameValuePair nv= new NameValuePair();
			res.add(nv);
			nv.setName(cons.getName());
			nv.setValue(cons.getValue());
		}
		return res;
	}

	public List<NameValuePair> findConstantSel(String domain) {
		List<NameValuePair> res = new ArrayList<NameValuePair>();
		DetachedCriteria dc = DetachedCriteria.forClass(cfg.vo.Domain.class);
		dc.add(Restrictions.eq("domain", domain));
		cfg.vo.Domain dom = findBean(dc); 
		dc = DetachedCriteria.forClass(Cons.class);
		dc.add(Restrictions.eq("domain",dom));
		dc.add(Restrictions.eq("enable", "1"));
		dc.addOrder(Order.asc("ord"));
		List<Cons> findBean = find(dc);
		for(Cons cons : findBean){
			NameValuePair nv= new NameValuePair();
			res.add(nv);
			nv.setName(cons.getName());
			nv.setValue(cons.getValue());
		}
		return res;
	}
	
}
package common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import common.vo.BaseTO;
import common.vo.NameValuePair;




/**
 * @author xuchunliang,lichenguang,chenyufei
 * @version 1.0  2010.5.27
 * 
 * 基Dao接口
 */
public interface BaseDao {

	
	/**
	 * 保存实例
	 * 
	 * @param oTO
	 * @throws DaoException
	 */
	public void save(BaseTO oTO);

	public void saveMany(List<? extends BaseTO> ol);
	/**
	 * 修改实例
	 * 
	 * @param oTO
	 * @throws DaoException
	 */
	public void update(BaseTO oTO);

	/**
	 * 保存或修改实例
	 * 
	 * @param oTO
	 * @throws DaoException
	 */
	public void saveOrUpdate(BaseTO oTO);

	/**
	 * 删除实例
	 * 
	 * @param oTO
	 * @throws DaoException
	 */
	public <T> void delete(T oTO);

	/**
	 * 删除实例
	 * 
	 * @param oTO
	 * @throws DaoException
	 */
	public <T> void deleteById(Class<T> clazz, int id);

	/**
	 * 取得一个实例
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public <T> T get(Class<T> clazz, Serializable id);

	/**
	 * 查找所有实例
	 * @param <T>
	 * 
	 * @param clazz
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class clazz);

	/**
	 * hql语句查询,不带参数
	 * @param <T>
	 * 
	 * @param hql
	 * @return
	 * @throws DaoException
	 */
	public <T> List<T> find(String hql);

	/**
	 * hql语句查询,取得第一条查询结果
	 * @param <T>
	 */
	public <T> T findBean(String hql);

	/**
	 * hql语句查询,取得第一条查询结果
	 * @param <T>
	 */
	public <T> T findBean(String hql, Object[] parameter);
	
	/**
	 * hql语句查询,带一个参数
	 * 
	 * @param hql
	 * @param parameter
	 * @return
	 * @throws DaoException
	 */
	public <T> List<T> find(String hql, Object parameter);

	/**
	 * hql语句查询,带多个参数
	 * 
	 * @param hql
	 * @param parameter
	 * @return
	 * @throws DaoException
	 */
	public <T> List<T> find(String hql, Object[] parameter);

	/**
	 * 命名查询,不带参数
	 * 
	 * @param queryName
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public List findByNamedQuery(String queryName);

	/**
	 * 命名查询,带一个参数
	 * 
	 * @param queryName
	 * @param parameter
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public List findByNamedQuery(String queryName, Object parameter);

	/**
	 * 命名查询,带多个参数
	 * 
	 * @param queryName
	 * @param parameter
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public List findByNamedQuery(String queryName, Object[] parameter);
	
	/**
	 * DetachedCriteria方式查询
	 * @param <T>
	 * 
	 * @param dc
	 * @return
	 * @throws DaoException
	 */
	public <T> List<T> find(final DetachedCriteria dc);
	
	/**
	 * DetachedCriteria方式查询
	 * @param <T>
	 * 
	 * @param dc
	 * @return
	 * @throws DaoException
	 */
	public int findCount(final DetachedCriteria dc);
	
	/**
	 * DetachedCriteria方式查询
	 * @param <T>
	 * 
	 * @param dc
	 * @return
	 * @throws DaoException
	 */
	public int findMax(final DetachedCriteria dc, final String property);
	
	/**
	 * DetachedCriteria方式查询
	 * @param <T>
	 * 
	 * @param dc
	 * @return
	 * @throws DaoException
	 */
	public <T> T findBean(final DetachedCriteria dc);
	
	/**
	 * 分页查找列表
	 * 
	 * @param dc
	 * @param page
	 * @return
	 * @throws DaoException
	 */


	/**
	 * 执行没有返回值的SQL语句
	 * 
	 * @param queryName
	 * @param param
	 * @throws DaoException
	 */
	public void executeSQL(final String sql, final Object[] param);

	/**
	 * 执行有返回值的SQL语句
	 * 
	 * @param queryName
	 * @param param
	 * @throws DaoException
	 */
	public <T> List<T> querySQL(final String sql, final Object[] param);
	
	public String getValue(String name);



	public <T> List<T> findWithPage2(DetachedCriteria dc, int i, int rows);

	public List<NameValuePair> findConstant(int domainId);
	
	public List<NameValuePair> findConstantSel(String domain);

}

package dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entity.PageInfo;
import util.HibernateUtils;

public class HibernateDao<T, PK extends Serializable> {
	private Class<T> entityClass = null;
	
	@SuppressWarnings("unchecked")
	public HibernateDao() {
		//1、获取HibernateDao的类对象		
		Class<?> clazz = this.getClass();
		//2、获取带有泛型的父类  HibernateDao<T, PK extends Serializable>
		ParameterizedType type = (ParameterizedType)clazz.getGenericSuperclass();
		//3、获取泛型的所有参数  T, PK extends Serializable
		entityClass =(Class<T>)(type.getActualTypeArguments()[0]);
	}
	public void insert(T entity) {
		getSession().save(entity);
	}

	public void delete(PK id) {
		Object obj = getSession().get(entityClass, id);
		getSession().delete(obj);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	@SuppressWarnings("unchecked")
	public T findById(PK id) {
		return (T)getSession().get(entityClass, id);
	}
	
	public PageInfo<T> findByPage(PageInfo<T> pageInfo ) {
		
		try {
			Session session = HibernateUtils.getSession();
			// 1、总记录数
			Long count = (Long)session.createQuery("select count(*) from " + entityClass.getName())
					.uniqueResult();
			//2、当前页的所有记录
			@SuppressWarnings("unchecked")
			List<T> list = session.createQuery("from " + entityClass.getName())
								.setFirstResult(
										(pageInfo.getPageIndex() - 1) * PageInfo.PAGE_SIZE)
								.setMaxResults(PageInfo.PAGE_SIZE)
								.list();			
			//3、设置总记录数，当前页的所有记录和总页数  到  pageInfo对象中
			pageInfo.setCount(count.intValue());
			pageInfo.setPageList(list);
			int totalPages = (int)(count%PageInfo.PAGE_SIZE==0?count/PageInfo.PAGE_SIZE:count/PageInfo.PAGE_SIZE+1);			
			pageInfo.setTotalPages(totalPages);				
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
	
		return pageInfo;
	}
	public Session getSession() {
		return HibernateUtils.getSession();
	}
	
	public Class<T> getEntityClass() {
		return entityClass;
	}
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
}
 
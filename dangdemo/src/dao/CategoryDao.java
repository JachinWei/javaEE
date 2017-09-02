package dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entity.Book;
import entity.Category;
import util.HibernateUtils;

public class CategoryDao extends HibernateDao<Book, Integer> {
	@SuppressWarnings("unchecked")
	public ArrayList<Category> getList(){
		ArrayList<Category> cateArray = new ArrayList<Category>();
		try {
			Session session = HibernateUtils.getSession();
			//查询语句
			String hql = "from Category";
			cateArray = (ArrayList<Category>)session.createQuery(hql).list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		
		return cateArray;
	}
}

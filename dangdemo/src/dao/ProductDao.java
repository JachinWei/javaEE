package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entity.CategoryCount;
import entity.Product;
import util.HibernateUtils;

public class ProductDao extends HibernateDao<Product, Integer> {
	@SuppressWarnings("unchecked")
	public ArrayList<CategoryCount> getCateList() {
		ArrayList<CategoryCount> returnList = new ArrayList<CategoryCount>();
		try {
			// 开启session
			Session session = HibernateUtils.getSession();
			// 设置hql语句
			// String sql = "select c.id, c.cn_name, count(p.id) from
			// d_d_product p right join d_d_category c on p.category_id = c.id
			// group by c.id";
			// 赋值
			// List<Object[]> list = session.createSQLQuery(sql).list();
			// Iterator<?> it = list.iterator();
			// while(it.hasNext())
			// {
			// Object[] oc = (Object[])it.next();
			// CategoryCount cate = new
			// CategoryCount(Integer.parseInt(oc[0].toString()),
			// oc[1].toString(), Integer.parseInt(oc[2].toString()));
			// returnList.add(cate);
			// }
			String hql = "select p.category.id, p.category.cn_name, count(p.id) from Product p group by p.category.id";
			List<Object[]> list = session.createQuery(hql).list();
			Iterator<?> it = list.iterator();
			while (it.hasNext()) {
				Object[] oc = (Object[]) it.next();
				CategoryCount cate = new CategoryCount(Integer.parseInt(oc[0].toString()), oc[1].toString(),
						Integer.parseInt(oc[2].toString()));
				returnList.add(cate);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return returnList;
	}

	public String getIdByCategory(Integer category_id) {
		StringBuffer strBuf = new StringBuffer();
		// 开启session
		try {
			Session session = HibernateUtils.getSession();
			String sql = "select id from d_d_product where category_id = " + category_id;
			@SuppressWarnings("unchecked")
			List<Object[]> list = session.createSQLQuery(sql).list();
			int i = 0;
			for (; i < list.size() - 1; i++) {
				strBuf.append(list.get(i) + ",");
				System.out.println(list.get(i));
			}
			strBuf.append(list.get(i));
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		
		return strBuf.toString();
	}

}

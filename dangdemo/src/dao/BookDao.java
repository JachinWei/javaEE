package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entity.Book;
import entity.PageInfo;
import util.HibernateUtils;

public class BookDao extends HibernateDao<Book, Integer> {
	public PageInfo<Book> findByPage(PageInfo<Book> pageInfo, Integer category_id) {
		try {
			Session session = HibernateUtils.getSession();
			//查询语句
			String hql, hqlCount;
			Long count;
			if(category_id == null){
				hql = "from Book b" + " join fetch b.product" + " order by b.id asc ";
				hqlCount = "select count(*) from Book";
				// 1、总记录数
				count = (Long) session.createQuery(hqlCount).uniqueResult();
			}else{
				ProductDao productDao = new ProductDao();
				String str =productDao.getIdByCategory(category_id);
				System.out.println("进行sql查询数据");
				hql = "from Book b" 
						+" join fetch b.product" 
						+" where b.product.id in (" 
						+ str
						+ ") order by b.id asc";
				hqlCount = "select count(*) from Book b join fetch b.product"
						+ " where b.product.id in (" + str + ")";
				count = (long) str.split(",").length;
				System.out.println(count);
			}
		
			// 2、设置总记录数
			int totalPages = (int) (count % PageInfo.PAGE_SIZE == 0 ? count / PageInfo.PAGE_SIZE
					: count / PageInfo.PAGE_SIZE + 1);
			if (pageInfo.getPageIndex() > totalPages) {
				pageInfo.setPageIndex(totalPages);
			}
			// 3、当前页的所有记录
			
			@SuppressWarnings("unchecked")
			List<Book> list = session.createQuery(hql)
					.setFirstResult((pageInfo.getPageIndex() - 1) * PageInfo.PAGE_SIZE)
					.setMaxResults(PageInfo.PAGE_SIZE).list();
			// 4、设置当前页的所有记录和总页数 到 pageInfo对象中
			pageInfo.setCount(count.intValue());
			pageInfo.setTotalPages(totalPages);
			pageInfo.setPageList(list);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
		return pageInfo;
	}
}


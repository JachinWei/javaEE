package service;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import dao.BookDao;
import entity.Book;
import entity.PageInfo;
import exception.ServiceException;
public class BookService {
	private static final Logger logger = Logger.getLogger(BookService.class);
	private BookDao bookDao = new BookDao();
	
	//分页查询
	public PageInfo<Book> findByPage(PageInfo<Book> pageInfo, Integer category_id) {			
		try {			
			return bookDao.findByPage(pageInfo, category_id);
		} catch (HibernateException e) {
			logger.error(e);
			throw new ServiceException(e);
		} 
	}
}

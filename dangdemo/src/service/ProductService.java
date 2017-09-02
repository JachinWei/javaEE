package service;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import dao.ProductDao;
import entity.CategoryCount;
import exception.ServiceException;

public class ProductService {
	private static final Logger logger = Logger.getLogger(BookService.class);
	private ProductDao productDao = new ProductDao();

	public ArrayList<CategoryCount> getProductCountByCate() {
		try {		
			return productDao.getCateList();
		} catch (HibernateException e) {
			logger.error(e);
			throw new ServiceException(e);
		} 
	}

}

package service;

import dao.ItemDao;
import dao.ProductDao;
import entity.Item;
import entity.Product;

public class ItemService {
	public boolean saveItem(Integer product_id, Integer user_id){
		if(product_id == null || user_id == null){
			return false;
		}
		ProductDao productDao = new ProductDao();
		Product product = productDao.findById(product_id);
		
		Item item = new Item();
		item.setUser_id(user_id);
		item.setProduct_name(product.getProduct_name());
		item.setDang_price(product.getDang_price());
		item.setProduct_num(1);
		item.setAmount(product.getDang_price());
		
		ItemDao itemDao = new ItemDao();
		itemDao.insert(item);
		return true;
	}
}

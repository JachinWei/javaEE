package web.action;

import com.opensymphony.xwork2.ActionSupport;

import service.ItemService;

public class ProductSaleAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private Integer product_id;
	private Integer user_id;
	
	public String saveItem(){
		ItemService itemService = new ItemService();
		if(itemService.saveItem(product_id, user_id)){
			return "success";
		}else{
			return "failure";
		}
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	
	
	
}

package web.action;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import entity.CategoryType;
import service.CategoryService;

public class MainListAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private ArrayList<CategoryType> cateArray;

	public String getList() {
		try {
			CategoryService cateService = new CategoryService();
			cateArray = cateService.getList();
		} catch (Exception e) {
			return "failure";
		}
		return "success";
	}

	public ArrayList<CategoryType> getCateArray() {
		return cateArray;
	}

	public void setCateArray(ArrayList<CategoryType> cateArray) {
		this.cateArray = cateArray;
	}
	
	
}

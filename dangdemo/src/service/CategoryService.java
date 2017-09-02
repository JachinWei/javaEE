package service;

import java.util.ArrayList;
import java.util.HashMap;

import dao.CategoryDao;
import entity.Category;
import entity.CategoryType;

public class CategoryService {
	public ArrayList<CategoryType> getList(){
		CategoryDao cateDao = new CategoryDao();
		ArrayList<Category> cateArray = cateDao.getList();
		ArrayList<Category> parentArray = new ArrayList<Category>();
		ArrayList<Category> childArray = new ArrayList<Category>();
		for(int i = 1; i< cateArray.size();i++){
			if(cateArray.get(i).getParent_id() == 1){
				parentArray.add(cateArray.get(i));
			}else{
				childArray.add(cateArray.get(i));
			}
		}
		ArrayList<CategoryType> cateTypeArray = new ArrayList<CategoryType>();
		String str = "";
		for(int i =0;i<parentArray.size();i++){
			CategoryType cateType = new CategoryType();
			HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
			Category cate = parentArray.get(i);
			cateType.setParent_name(cate.getCn_name());
			cateType.setHashmap(hashMap);
			for(int j = 0; j < childArray.size();j++){
				str = childArray.get(j).getParent_id().toString();
				if(cate.getCategory_value().indexOf(str)!=-1){
					hashMap.put(childArray.get(j).getCn_name(), childArray.get(j).getId());
					
				}
			}
			cateTypeArray.add(cateType);
		}
		return cateTypeArray;
	}
}

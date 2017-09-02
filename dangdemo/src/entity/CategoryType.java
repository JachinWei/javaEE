package entity;

import java.util.HashMap;

public class CategoryType {
	private String parent_name;
	private HashMap<String, Integer> hashmap;
	public String getParent_name() {
		return parent_name;
	}
	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}
	public HashMap<String, Integer> getHashmap() {
		return hashmap;
	}
	public void setHashmap(HashMap<String, Integer> hashmap) {
		this.hashmap = hashmap;
	}


	
	
}

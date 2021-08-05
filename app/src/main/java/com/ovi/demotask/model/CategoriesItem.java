package com.ovi.demotask.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CategoriesItem{

	@SerializedName("category_name")
	private String categoryName;

	@SerializedName("category_id")
	private String categoryId;

	@SerializedName("subcatg")
	private List<SubcatgItem> subcatg;

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setSubcatg(List<SubcatgItem> subcatg){
		this.subcatg = subcatg;
	}

	public List<SubcatgItem> getSubcatg(){
		return subcatg;
	}

	@Override
 	public String toString(){
		return 
			"CategoriesItem{" + 
			"category_name = '" + categoryName + '\'' + 
			",category_id = '" + categoryId + '\'' + 
			",subcatg = '" + subcatg + '\'' + 
			"}";
		}
}
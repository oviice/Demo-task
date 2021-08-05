package com.ovi.demotask.model;

import com.google.gson.annotations.SerializedName;

public class SubcatgItem{

	@SerializedName("sub_category_id")
	private String subCategoryId;

	@SerializedName("sub_category_name")
	private String subCategoryName;

	public void setSubCategoryId(String subCategoryId){
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryId(){
		return subCategoryId;
	}

	public void setSubCategoryName(String subCategoryName){
		this.subCategoryName = subCategoryName;
	}

	public String getSubCategoryName(){
		return subCategoryName;
	}

	@Override
 	public String toString(){
		return 
			"SubcatgItem{" + 
			"sub_category_id = '" + subCategoryId + '\'' + 
			",sub_category_name = '" + subCategoryName + '\'' + 
			"}";
		}
}
package com.example.fang.walmartproject.data;

import java.util.List;

public class CategoryItemList {
    List<CategoryItem> mCategoryItemList;

    public CategoryItemList(List<CategoryItem> mCategoryItemList) {
        this.mCategoryItemList = mCategoryItemList;
    }

    public List<CategoryItem> getmCategoryItemList() {
        return mCategoryItemList;
    }

    public void setmCategoryItemList(List<CategoryItem> mCategoryItemList) {
        this.mCategoryItemList = mCategoryItemList;
    }

    public int getListSize(){
        return mCategoryItemList.size();
    }

    public CategoryItem getItem(int i){
        return mCategoryItemList.get(i);
    }
}

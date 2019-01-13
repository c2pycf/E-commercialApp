package com.example.fang.walmartproject.data;

public class CategoryItem {
    private String cid;
    private String cname;
    private String cdiscription;
    private String cimageurl;

    public CategoryItem(String cid, String cname, String cdiscription, String cimageurl) {
        this.cid = cid;
        this.cname = cname;
        this.cdiscription = cdiscription;
        this.cimageurl = cimageurl;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCdiscription() {
        return cdiscription;
    }

    public void setCdiscription(String cdiscription) {
        this.cdiscription = cdiscription;
    }

    public String getCimageurl() {
        return cimageurl;
    }

    public void setCimageurl(String cimageurl) {
        this.cimageurl = cimageurl;
    }
}

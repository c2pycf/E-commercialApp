package com.example.fang.walmartproject.data.source;

public class TopSeller {
    String id;
    String name;
    String deal;
    String rating;
    String logo;

    public TopSeller(String id, String name, String deal, String rating, String logo) {
        this.id = id;
        this.name = name;
        this.deal = deal;
        this.rating = rating;
        this.logo = logo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}

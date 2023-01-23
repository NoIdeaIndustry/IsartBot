package com.noideaindustry.isartbot.models;

import java.util.Date;

public class FoodTruck {
    private final String name;
    private final Date date;
    private final String url;

    public FoodTruck(final String name, final Date date, final String url) {
        this.name = name;
        this.date = date;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}

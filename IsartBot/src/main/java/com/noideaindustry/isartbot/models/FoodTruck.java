package com.noideaindustry.isartbot.models;


import java.util.Date;

public class FoodTruck {
    private final Date date;
    private final String name;
    private final String description;
    private final String social;
    private final FoodType type;

    public FoodTruck(final Date date, final String name, final String description, final String social) {
        this.date = date;
        this.name = name;
        this.description = description;
        this.social = social;
        this.type = FoodType.getTypeFromId(this.getDescription());
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getSocial() {
        return social;
    }

    public FoodType getType() {
        return type;
    }
}

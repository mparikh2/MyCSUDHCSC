package com.example.milind.mycsudhcsc.activity;

/**
 * Created by Milind on 12/1/2017.
 */

public class ListItem {

    //private String name, realName, team, firstAppearance, createdBy, publisher, imageUrl, bio;

    private String name, email, phone, office, website, imageUrl;

    public ListItem(String name, String email, String phone, String office, String website, String imageUrl) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.office = office;
        this.website = website;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getOffice() {
        return office;
    }

    public String getWebsite() {
        return website;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    //    public ListItem(String name, String realName, String team, String firstAppearance, String createdBy, String publisher, String imageUrl, String bio) {
//        this.name = name;
//        this.realName = realName;
//        this.team = team;
//        this.firstAppearance = firstAppearance;
//        this.createdBy = createdBy;
//        this.publisher = publisher;
//        this.imageUrl = imageUrl;
//        this.bio = bio;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getRealName() {
//        return realName;
//    }
//
//    public String getTeam() {
//        return team;
//    }
//
//    public String getFirstAppearance() {
//        return firstAppearance;
//    }
//
//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public String getPublisher() {
//        return publisher;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public String getBio() {
//        return bio;
//    }
}

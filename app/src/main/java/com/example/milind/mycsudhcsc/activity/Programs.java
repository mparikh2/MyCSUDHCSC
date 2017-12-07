package com.example.milind.mycsudhcsc.activity;

/**
 * Created by Milind on 12/2/2017.
 */

public class Programs {
    public String id;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Programs() {
    }

    public Programs(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

package com.example.avinash.garlandviewprototype;

import java.util.ArrayList;

/**
 * Created by avinash on 27/12/17.
 */

public class User {

    private static User mInstance= null;

    ArrayList<String> craftsinfo = new ArrayList<String>();
    ArrayList<String> newcraftsinfo = new ArrayList<String>();

    protected User(){}

    public static synchronized User getInstance(){
        if(null == mInstance){
            mInstance = new User();
        }
        return mInstance;
    }

}

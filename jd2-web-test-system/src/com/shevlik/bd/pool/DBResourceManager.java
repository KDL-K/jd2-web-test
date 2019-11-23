package com.shevlik.bd.pool;

import java.util.ResourceBundle;

public class DBResourceManager {
    private ResourceBundle rb;

    public DBResourceManager(){
        rb=ResourceBundle.getBundle("com.shevlik.bd.pool.resources.db");
    }

    public String getValue(String key){
        return rb.getString(key);
    }
}

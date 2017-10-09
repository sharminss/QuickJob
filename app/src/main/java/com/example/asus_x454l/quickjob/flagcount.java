package com.example.asus_x454l.quickjob;

/**
 * Created by ASUS_X454L on 10/8/2017.
 */

public class flagcount {

    public  String flag;
    public  String  email;


    public  String id1;



    public flagcount() {
    }

    public flagcount(String flag, String email, String id1) {
        this.flag = flag;
        this.email = email;
        this.id1 = id1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}

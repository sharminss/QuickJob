package com.example.asus_x454l.quickjob;

/**
 * Created by ASUS_X454L on 10/9/2017.
 */

public class UserInformationNew {


    private   String  id2;
    private String name;
    private String email;
    private String phone_num;
    public String url;
    public UserInformationNew(){

    }
    public UserInformationNew(String id2, String name, String email, String phone_num, String url) {
        this.id2 = id2;
        this.name = name;
        this.email = email;
        this.phone_num = phone_num;
        this.url = url;
    }


    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

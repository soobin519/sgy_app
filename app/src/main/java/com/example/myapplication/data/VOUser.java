package com.example.myapplication.data;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class VOUser implements Serializable {
    private String id;
    private String pw;
    private String phone;
    private Timestamp signup;
    private String auth;

    public VOUser() {
    }

    private String getEncMD5(String txt){
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance("MD5");
            digest.update(txt.getBytes());
            byte[] messageDigest = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String h = Integer.toHexString(0xFF & b);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }


    public VOUser(String id, String pw,  String phone) {
        this.id = id;
        this.pw = pw;
        this.phone = phone;
        this.auth = getEncMD5(this.id).toUpperCase();
    }


    public String getId(){ return this.id; }
    public void setId(String id){ this.id = id; }
    public String getPw(){ return this.pw; }
    public void setPw(String pw){ this.pw = pw; }
    public String getPhone(){return this.phone;}
    public void setPhone(String phone){this.phone = phone;}
    public Timestamp getSignup(){return this.signup;}
    public void setSignup(Timestamp signup){this.signup=signup;}
    public String getAuth(){return this.auth;}
    public void setAuth(String auth){this.auth = auth;}

}
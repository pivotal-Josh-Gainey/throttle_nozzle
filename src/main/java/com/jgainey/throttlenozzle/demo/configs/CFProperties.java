package com.jgainey.throttlenozzle.demo.configs;

public class CFProperties {

    /*
    These values are derived from environmental variables set in manifest.
     */

    private String user = System.getenv("CFUSER");
    private String password = System.getenv("CFPASSWORD");
    private String api = System.getenv("CFAPI");
    private String subscriptionID = System.getenv("SUBCRIPTIONID");


    public String getSubscriptionID() {
        return subscriptionID;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getApi() {
        return api;
    }

}

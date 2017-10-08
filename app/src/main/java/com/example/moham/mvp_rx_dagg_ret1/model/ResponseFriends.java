package com.example.moham.mvp_rx_dagg_ret1.model;

public class ResponseFriends {
    private String call;
    private String last_name;
    private double[] location;
    private String id;
    private String first_name;
    private String email;

    public String getCall() {
        return this.call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public double[] getLocation() {
        return this.location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

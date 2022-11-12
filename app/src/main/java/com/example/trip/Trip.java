package com.example.trip;

public class Trip {
    private long tid;
    private String trip_name;
    private String trip_destination;
    private String trip_date;
    private Boolean trip_risk;
    private String trip_method;
    private String trip_description;
    private String trip_end_date;
    private String trip_budget;


    public Trip (long tid, String trip_name, String trip_destination, String trip_date, Boolean trip_risk,
                 String trip_method, String trip_description, String trip_end_date, String trip_budget){
        this.tid = tid;
        this.trip_name = trip_name;
        this.trip_destination = trip_destination;
        this.trip_date = trip_date;
        this.trip_risk = trip_risk;
        this.trip_method = trip_method;
        this.trip_description = trip_description;
        this.trip_end_date = trip_end_date;
        this.trip_budget = trip_budget;
    }

    public Trip (String trip_name, String trip_destination, String trip_date, Boolean trip_risk,
                 String trip_method, String trip_description, String trip_end_date, String trip_budget){
        this.trip_name = trip_name;
        this.trip_destination = trip_destination;
        this.trip_date = trip_date;
        this.trip_risk = trip_risk;
        this.trip_method = trip_method;
        this.trip_description = trip_description;
        this.trip_end_date = trip_end_date;
        this.trip_budget = trip_budget;
    }

    public long getTid(){
        return tid;
    }

    public void setTid(long tid){
        this.tid = tid;
    }

    public String getTrip_name(){
        return trip_name;
    }

    public void setTrip_name(String trip_name){
        this.trip_name = trip_name;
    }

    public String getTrip_destination(){
        return trip_destination;
    }

    public void setTrip_destination(String trip_description){
        this.trip_destination = trip_destination;
    }


    public String getTrip_date(){
        return trip_date;
    }

    public void setTrip_date(String trip_date){
        this.trip_date = trip_date;
    }

    public Boolean getTrip_risk(){
        return trip_risk;
    }

    public void setTrip_risk(Boolean trip_risk){
        this.trip_risk = trip_risk;
    }

    public String getTrip_method(){
        return trip_method;
    }

    public void setTrip_method(String trip_method){
        this.trip_method = trip_method;
    }

    public String getTrip_description(){
        return trip_description;
    }

    public void setTrip_description(String trip_description){
        this.trip_description = trip_description;
    }

    public String getTrip_enddate() {
        return trip_end_date;
    }

    public void setTrip_end_date(String trip_end_date) {
        this.trip_end_date = trip_end_date;
    }

    public String getTrip_budget(){
        return trip_budget;
    }

    public void setTrip_budget(String trip_budget){
        this.trip_budget = trip_budget;
    }
}
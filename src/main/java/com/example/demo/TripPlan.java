package com.example.demo;

import lombok.Data;

@Data
public class TripPlan {
    public int id;
    public String des;
    public String city;
    public String date;
    public int status;
    public String note;
}

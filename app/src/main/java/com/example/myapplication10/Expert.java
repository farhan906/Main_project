package com.example.myapplication10;


public class Expert {
    private String name;
    private String country;
    private String state;
    private String district;
    private String fieldOfExpertise;

    public Expert(String name, String country, String state, String district, String fieldOfExpertise) {
        this.name = name;
        this.country = country;
        this.state = state;
        this.district = district;
        this.fieldOfExpertise = fieldOfExpertise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFieldOfExpertise() {
        return fieldOfExpertise;
    }

    public void setFieldOfExpertise(String fieldOfExpertise) {
        this.fieldOfExpertise = fieldOfExpertise;
    }
}


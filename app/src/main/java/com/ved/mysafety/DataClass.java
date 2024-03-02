package com.ved.mysafety;

public class DataClass {
    private String dataName;
    private String dataEmail;
    private Integer dataNumber;
    private String dataAdd;
    private String dataImage;
    private String keydiscription;


    public String getDataName() {
        return dataName;
    }

    public String getDataEmail() {
        return dataEmail;
    }

    public Integer getDataNumber() {
        return dataNumber;
    }

    public String getDataAdd() {
        return dataAdd;
    }

    public String getDataImage() {
        return dataImage;
    }

    public String getKeydiscription() {
        return keydiscription;
    }

    public DataClass(String dataName, String dataEmail, Integer dataNumber, String dataAdd, String dataImage, String keydiscription) {
        this.dataName = dataName;
        this.dataEmail = dataEmail;
        this.dataNumber = dataNumber;
        this.dataAdd = dataAdd;
        this.dataImage = dataImage;
        this.keydiscription = keydiscription;
    }
}

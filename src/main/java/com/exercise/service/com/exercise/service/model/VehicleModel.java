package com.exercise.service.com.exercise.service.model;

public class VehicleModel {

    private String vehicleReg;
    private String make;
    private String color;

    public String getVehicleReg() {
        return vehicleReg;
    }

    public void setVehicleReg(String vehicleReg) {
        this.vehicleReg = vehicleReg;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "VehicleModel{" +
                "vehicleReg='" + vehicleReg + '\'' +
                ", make='" + make + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public void setColor(String color) {
        this.color = color;
    }
}

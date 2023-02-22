package service;

import java.util.Calendar;

public class VehicleImpl implements Vehicle {

    private int manufacturerYear;
    private String model;

    public VehicleImpl(int manufacturerYear, String model) {
        this.manufacturerYear = manufacturerYear;
        this.model = model;
    }

    @Override
    public int getAge() {
        return Calendar.getInstance().get(Calendar.YEAR) - this.manufacturerYear;
    }

    @Override
    public String getModel() {
        return this.model;
    }

}

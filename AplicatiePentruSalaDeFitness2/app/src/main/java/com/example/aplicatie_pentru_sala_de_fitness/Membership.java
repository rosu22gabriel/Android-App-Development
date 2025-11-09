package com.example.aplicatie_pentru_sala_de_fitness;

public class Membership {
    private String type; // Basic, Standard, Premium, etc.
    private double price;
    private int durationInMonths;

    public Membership(String type, double price, int durationInMonths) {
        this.type = type;
        this.price = price;
        this.durationInMonths = durationInMonths;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", durationInMonths=" + durationInMonths +
                '}';
    }
}

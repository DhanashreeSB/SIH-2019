package com.example.shyam.fly;

public class FlightBookingDisplayResults {

    public int id;
    public String from_time, to_time, from_city_booking, to_city_booking, stops, total_time,arrival_from,flight_id;
    public String price;
    public int image_booking;

    public FlightBookingDisplayResults(int id, String from_time, String to_time, String from_city_booking, String to_city_booking, String stops, String price, String total_time, int image_booking, String arrival_from, String flight_id) {
        this.id = id;
        this.flight_id = flight_id;
        this.from_time = from_time;
        this.to_time = to_time;
        this.from_city_booking = from_city_booking;
        this.to_city_booking = to_city_booking;
        this.stops = stops;
        this.price = price;
        this.image_booking = image_booking;
        this.total_time = total_time;
        this.arrival_from = arrival_from;
    }

    public String getFlight_id() {
        return flight_id;
    }

    public int getId() {
        return id;
    }

    public String getTotal_time() {
        return total_time;
    }

    public String getFrom_time() {
        return from_time;
    }

    public String getTo_time() {
        return to_time;
    }

    public String getFrom_city_booking() {
        return from_city_booking;
    }

    public String getTo_city_booking() {
        return to_city_booking;
    }

    public String getStops() {
        return stops;
    }

    public String getPrice() {
        return price;
    }

    public int getImage_booking() {
        return image_booking;
    }

    public String getArrival_from()
    {
        return arrival_from;
    }
}

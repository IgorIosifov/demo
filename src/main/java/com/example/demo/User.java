package com.example.demo;

public class User {

    int id;
    String avatar;
    String fullName;
    String status;
    Location location;

    public User(int id, String avatar,String fullName, String role, String city, String country) {
        this.id = id;
        this.avatar = avatar;
        this.fullName = fullName;
        this.status = role;
        this.location = new Location(city, country);
    }

    static class Location {
        String city;
        String country;

        public Location(String city, String country) {
            this.city = city;
            this.country = country;
        }
    }
}

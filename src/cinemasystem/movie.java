/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

/**
 *
 * @author Dell
 */
public class movie {
    private int id;
    private String title, genre, rating, duration, date, time, hall;

    public movie(int id, String title, String genre, String rating, String duration, String date, String time, String hall) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.duration = duration;
        this.date = date;
        this.time = time;
        this.hall = hall;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getRating() {
        return rating;
    }

    public String getDuration() {
        return duration;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getHall() {
        return hall;
    }
    
}

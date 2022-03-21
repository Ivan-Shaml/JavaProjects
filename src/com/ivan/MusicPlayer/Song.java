package com.ivan.MusicPlayer;

public class Song {
    String title;
    double duration;

    public Song(String title, double duration) {
        this.title = title;
        this.duration = duration;
    }

    public Song() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Title: " + this.title + " Duration: " + this.duration;
    }
}

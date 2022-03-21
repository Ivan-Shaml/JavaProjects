package com.ivan.MusicPlayer;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
    String title;
    String artist;
    ArrayList<Song> songs;

    public Album(String title, String artist) {
        this.title = title;
        this.artist = artist;
        this.songs = new ArrayList<Song>();
    }

    public Album(){

    }

    public boolean addSong(String title, double duratuion) {
        if (findSong(title) == null){
            this.songs.add(new Song(title, duratuion));
            System.out.printf("%s successfully added to the list.%n", title);
            return true;
        }
        System.out.printf("Song with title %s already exists in the list.%n", title);
        return false;
    }

    public Song findSong(String title){
        for (Song song : this.songs) {
            if (song.title.equals(title)) {
                return song;
            }
        }
        return null;
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playlist) {
        int index = trackNumber - 1;
        if (index > 0 && index <= this.songs.size()){
            playlist.add(this.songs.get(index));
            return true;
        }
        System.out.printf("This album does not have a song with track number. %d%n", trackNumber);
        return false;
    }

    public  boolean addToPlayList(String title, LinkedList<Song> PlayList){
        for (Song currentSong : this.songs){
            if (currentSong.getTitle().equals(title)){
                PlayList.add(currentSong);
                return true;
            }
        }
        System.out.printf("%s there is no such song in the album.%n", title);
        return false;
    }


}

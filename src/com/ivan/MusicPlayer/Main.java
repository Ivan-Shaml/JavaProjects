package com.ivan.MusicPlayer;

import java.util.*;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<>();

    public static void main(String[] args) {

        Album albumOne = new Album("Back in Black", "AC/DC");

        albumOne.addSong("Hells Bells", 5.12d);
        albumOne.addSong("Giving the dog a bone", 5.17d);
        albumOne.addSong("Back in Black", 4.28d);

        Album albumTwo = new Album("Bad Obsession", "Guns and Roses");

        albumTwo.addSong("Bad Obsession", 6.15d);
        albumTwo.addSong("Patience", 7.35d);

        albums.add(albumOne);
        albums.add(albumTwo);

        LinkedList<Song> playListOne = new LinkedList<>();

        albums.get(0).addToPlayList("Hells Bells", playListOne);
        albums.get(0).addToPlayList("Rock Star", playListOne);
        albums.get(1).addToPlayList("Rocker Queen", playListOne);
        albums.get(1).addToPlayList("Patience", playListOne);

        play(playListOne);
    }

    private static void play(LinkedList<Song> playlist) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false, forward = true;
        ListIterator<Song> listIterator = playlist.listIterator();

        if (playlist.size() == 0) {
            System.out.println("This playlist haven't got any songs.");
        }else {
            System.out.printf("Now playing %s%n", listIterator.next().toString());
            printMenu();
        }

        while(!quit){
            String action = scanner.next().toLowerCase(Locale.ROOT);
            scanner.nextLine();

            switch (action){
                case "a":
                    System.out.println("Playlist completed");
                    quit = true;
                    break;

                case "b":
                    if (!forward){
                        if (listIterator.hasNext()){
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if (listIterator.hasNext()){
                        System.out.printf("Now playing %s%n", listIterator.next().toString());
                    }else {
                        System.out.println("No song available, read to the end of the list");
                        forward = true;
                    }
                    break;
                case "c":
                    if (forward){
                        if (listIterator.hasPrevious()){
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if (listIterator.hasPrevious()){
                        System.out.printf("Now playing %s%n", listIterator.next().toString());
                    }else{
                        System.out.println("This is the first song.");
                        forward = false;
                    }
                    break;

                case "d":
                    if (forward){
                        if (listIterator.hasPrevious()){
                            System.out.printf("Now playing %s%n", listIterator.next().toString());
                        }else {
                            System.out.println("we are at the start of the list.");
                        }
                    }else{
                        if(!listIterator.hasNext()){
                            System.out.printf("Now playing %s%n", listIterator.next().toString());
                            forward = true;
                        }else {
                            System.out.println("The playlist has ended.");
                        }
                    }
                case "e":
                    printList(playlist);
                    break;
                case "f":
                    printMenu();
                    break;
                case "g":
                    if (playlist.size() > 0) {
                        listIterator.remove();
                        if (listIterator.hasNext()){
                            System.out.printf("Now playing %s%n", listIterator.next().toString());
                            forward = true;
                        }else{
                            if (listIterator.hasPrevious()){
                                System.out.printf("Now playing %s%n", listIterator.previous().toString());
                            }
                        }
                    }
            }
        }
    }

    private static void printMenu() {
        System.out.println("Available options: press");
        System.out.println("A - Quit");
        System.out.println("B - Play next song");
        System.out.println("C - Play previous song");
        System.out.println("D - Replay the current song");
        System.out.println("E - List all songs");
        System.out.println("F - print all available options (this menu)");
        System.out.println("G - delete the current song");
    }

    private static void printList(LinkedList<Song> playList) {
        Iterator<Song> iterator = playList.iterator();
        System.out.println("===========");

        while (iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
    }

}

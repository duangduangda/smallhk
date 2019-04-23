package com.smallhk.fp;

/**
 * @description:
 * @author: dean
 * @create: 2019/04/22 17:23
 */
public class Albulumn {

    private String artist;

    private int listener;

    public Albulumn(String artist, int listener) {
        this.artist = artist;
        this.listener = listener;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getListener() {
        return listener;
    }

    public void setListener(int listener) {
        this.listener = listener;
    }

    @Override
    public String toString() {
        return "Albulumn{" +
                "artist='" + artist + '\'' +
                ", listener=" + listener +
                '}';
    }
}

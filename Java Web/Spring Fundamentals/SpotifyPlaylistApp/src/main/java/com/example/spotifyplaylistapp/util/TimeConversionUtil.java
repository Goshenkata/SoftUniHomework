package com.example.spotifyplaylistapp.util;

public class TimeConversionUtil {
    public static String secondsToMinuteAndSeconds(int duration) {
        int minutes = duration / 60;
        int seconds = duration % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
}

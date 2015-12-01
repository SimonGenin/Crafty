package com.mygdx.game.debugging;

/**
 * Some methods to debug in a more efficient way.
 * 3 kinds of printing :
 * Debug
 * Error
 * Info
 *
 * Based on the type, it will change the color of the text printed.
 */
public class Debug {

    public static void e(String error) {
        System.err.println("Error : " + error);
    }

    public static void e(String error, String tag) {
        System.err.println("Error : " + tag + " : " + error);
    }

    public static void d(String debug) {
        System.out.println("Debug : " + debug);
    }

    public static void d(String debug, String tag) {
        System.out.println("Debug : " + tag + " : " + debug);
    }

    public static void i(String info) {
        System.out.println((char)27 + "[35m" + "Info : " + info + (char)27 + "[0m");
    }

    public static void i(String info, String tag) {
        System.out.println((char)27 + "[35m" + "Info : " + tag + " : " + info + (char)27 + "[0m");
    }


}

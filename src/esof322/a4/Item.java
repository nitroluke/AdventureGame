package esof322.a4;

/**
 * Adventure Game Program Code Copyright (c) 1999 James M. Bieman
 *
 * To compile: javac AdventureGame.java To run: java AdventureGame
 *
 * The main routine is AdventureGame.main
 **/

// class Item
/*
 * Todd Beckman
 * Dylan Hills
 * Kalvyn Lu
 * Luke O'Neill
 * Luke Welna
 */

public abstract class Item {

    private String desc;

    Item(String desc){
        this.desc = desc;
    }


    public String getDesc() {
        return desc;
    }

}

package esof322.a4;

import java.util.ArrayList;

/**
 * Adventure Game Program Code Copyright (c) 1999 James M. Bieman
 * <p>
 * To compile: javac AdventureGame.java To run: java AdventureGame
 * <p>
 * The main routine is AdventureGame.main
 * <p>
 * Update August 2010: refactored Vector contents into ArrayList<Item> contents. This gets rid of the use of obsolete Vector and
 * makes it type safe.
 **/

// class Room
/*
 * Todd Beckman
 * Dylan Hills
 * Kalvyn Lu
 * Luke O'Neill
 * Luke Welna
 */

public class Room implements CaveSite, java.io.Serializable{
    protected String desc;
    protected CaveSite[] side = new CaveSite[6];
    protected ArrayList<Item> contents = new ArrayList<Item>();

    public Room(String desc) {
        this.desc = desc;
        side[0] = new Wall();
        side[1] = new Wall();
        side[2] = new Wall();
        side[3] = new Wall();
        side[4] = new Wall();
        side[5] = new Wall();
    }

    public void setSide(int direction, CaveSite m) {
        side[direction] = m;
    }

    public void addItem(Item theItem) {
        contents.add(theItem);
    }

    public void removeItem(Item theItem) {
        contents.remove(theItem);
    }

    public boolean roomEmpty() {
        return contents.isEmpty();
    }

    public Item[] getRoomContents() {
        Item[] contentsArray = new Item[contents.size()];
        contentsArray = contents.toArray(contentsArray);
        return contentsArray;
    }

    public String enter(Player p) {
        p.setLoc(this);
        return "Entered a new room";
    }

    public String exit(int direction, Player p) {
        return side[direction].enter(p);
    }

    public String getDesc() {
        java.util.ListIterator<Item> roomContents = contents.listIterator();
        String contentString = "";
        while (roomContents.hasNext())
            contentString =
                            contentString + (roomContents.next()).getDesc() + " ";

        return desc + '\n' + '\n' +
               "Room Contents: " + contentString + '\n';
    }

}

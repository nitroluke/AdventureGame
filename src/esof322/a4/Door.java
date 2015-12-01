package esof322.a4;

import esof322.a4.Oil;
/**
 * Adventure Game Program Code Copyright (c) 1999 James M. Bieman
 * <p>
 * To compile: javac AdventureGame.java To run: java AdventureGame
 * <p>
 * The main routine is AdventureGame.main
 **/

// class Door
/*
 * Todd Beckman
 * Dylan Hills
 * Kalvyn Lu
 * Luke O'Neill
 * Luke Welna
 */

public class Door implements CaveSite, java.io.Serializable {
    /**
     * In this implementation doors are always locked. A player must have the correct key to get through a door. Doors automatically
     * lock after a player passes through.
     */
    private Key myKey;
    private boolean rusty;
    /**
     * The door's location.
     */
    private CaveSite r1;
    private CaveSite r2;

    /**
     * We can construct a door at the site.
     */
    public Door(CaveSite out, CaveSite in, Key k, boolean rusty) {
        r1 = out;
        r2 = in;
        myKey = k;
        this.rusty = rusty;
    }

    public boolean isRusty(){
        return this.rusty;
    }

    public void lubricate(){
        this.rusty = false;
    }

    /**
     * A player will need the correct key to enter.
     */
    public String enter(Player p) {
        String outputString = "";
        if (isRusty()){
            if (p.showMyThings().contains("Oil")){
                outputString = "You lubricate the door real good and try \n to open it.\n ";
                lubricate();
            } else {
                if (p.haveItem(myKey)){
                    outputString = "You have the correct key, but the door \n is rusted shut.\n";
                } else {
                    outputString += "You don't have the key for that door\n";
                }
            }
        }

        if (!isRusty()){
            if (p.haveItem(myKey)) {
                outputString += "Your key works! The door opens smoothly,\nand slams behind you after you pass through.\n";
                if (p.getLoc() == r1)
                    r2.enter(p);
                else if (p.getLoc() == r2)
                    r1.enter(p);
            } else {
                outputString += "You don't have the key for that door\n";
            }
        }

        return outputString;
    }

}

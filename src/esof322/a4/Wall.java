package esof322.a4;

/**
 * Adventure Game Program Code Copyright (c) 1999 James M. Bieman
 * <p>
 * To compile: javac AdventureGame.java To run: java AdventureGame
 * <p>
 * The main routine is AdventureGame.main
 **/

// class Wall
/*
 * Todd Beckman
 * Dylan Hills
 * Kalvyn Lu
 * Luke O'Neill
 * Luke Welna
 */

public class Wall implements CaveSite, java.io.Serializable {

    public String enter(Player p) {
        return "You can't go that way!!!";
    }

}

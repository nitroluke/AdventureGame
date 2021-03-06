package esof322.a4.concreteLevelProducts;

import esof322.a4.concreteLevelFactories.Level0Factory;

import java.util.ArrayList;
/*
 * Todd Beckman
 * Dylan Hills
 * Kalvyn Lu
 * Luke O'Neill
 * Luke Welna
 */
/**
 * Adventure Game Program Code Copyright (c) 1999 James M. Bieman
 * <p>
 * To compile: javac AdventureGame.java To run: java AdventureGame
 * <p>
 * The main routine is AdventureGame.main
 * <p>
 * Adventure Game Program Code Copyright (c) 1999-2012 James M. Bieman The Adventure game is based on the "Colossal Cave Adventure"
 * originally designed by Will Crowther and implemented by Will Crowther and Don Wood in Fortran in 1975 and 1976.
 * <p>
 * This micro-version is a variant of the original cave system and is implemented in Java with just a few rooms and with a much more
 * limited vocabulary.
 * <p>
 * Updated August 2010, January 2012 - Code is put into package cs314.a2 to match current CS314 coding standards. Updated January
 * 2012 - Renamed as the "Adventure Game"
 * <p>
 * To compile: javac cs314.a2.AdventureGame.java To run: java cs314.a2.AdventureGame
 * <p>
 * The main routine is AdventureGame.main
 * <p>
 * Adventure Game Program Code Copyright (c) 1999-2012 James M. Bieman The Adventure game is based on the "Colossal Cave Adventure"
 * originally designed by Will Crowther and implemented by Will Crowther and Don Wood in Fortran in 1975 and 1976.
 * <p>
 * This micro-version is a variant of the original cave system and is implemented in Java with just a few rooms and with a much more
 * limited vocabulary.
 * <p>
 * Updated August 2010, January 2012 - Code is put into package cs314.a2 to match current CS314 coding standards. Updated January
 * 2012 - Renamed as the "Adventure Game"
 * <p>
 * To compile: javac cs314.a2.AdventureGame.java To run: java cs314.a2.AdventureGame
 * <p>
 * The main routine is AdventureGame.main
 * <p>
 * Adventure Game Program Code Copyright (c) 1999-2012 James M. Bieman The Adventure game is based on the "Colossal Cave Adventure"
 * originally designed by Will Crowther and implemented by Will Crowther and Don Wood in Fortran in 1975 and 1976.
 * <p>
 * This micro-version is a variant of the original cave system and is implemented in Java with just a few rooms and with a much more
 * limited vocabulary.
 * <p>
 * Updated August 2010, January 2012 - Code is put into package cs314.a2 to match current CS314 coding standards. Updated January
 * 2012 - Renamed as the "Adventure Game"
 * <p>
 * To compile: javac cs314.a2.AdventureGame.java To run: java cs314.a2.AdventureGame
 * <p>
 * The main routine is AdventureGame.main
 * <p>
 * Adventure Game Program Code Copyright (c) 1999-2012 James M. Bieman The Adventure game is based on the "Colossal Cave Adventure"
 * originally designed by Will Crowther and implemented by Will Crowther and Don Wood in Fortran in 1975 and 1976.
 * <p>
 * This micro-version is a variant of the original cave system and is implemented in Java with just a few rooms and with a much more
 * limited vocabulary.
 * <p>
 * Updated August 2010, January 2012 - Code is put into package cs314.a2 to match current CS314 coding standards. Updated January
 * 2012 - Renamed as the "Adventure Game"
 * <p>
 * To compile: javac cs314.a2.AdventureGame.java To run: java cs314.a2.AdventureGame
 * <p>
 * The main routine is AdventureGame.main
 **/

/**
 * Adventure Game Program Code Copyright (c) 1999-2012 James M. Bieman The Adventure game is based on the "Colossal Cave Adventure"
 * originally designed by Will Crowther and implemented by Will Crowther and Don Wood in Fortran in 1975 and 1976.
 *
 * This micro-version is a variant of the original cave system and is implemented in Java with just a few rooms and with a much more
 * limited vocabulary.
 *
 * Updated August 2010, January 2012 - Code is put into package cs314.a2 to match current CS314 coding standards. Updated January
 * 2012 - Renamed as the "Adventure Game"
 *
 * To compile: javac cs314.a2.AdventureGame.java To run: java cs314.a2.AdventureGame
 *
 * The main routine is AdventureGame.main
 **/

/**
 * class Adventure: Primary method, createCave, creates the cave system. It eventually be replaced with a more flexible mechanism to
 * support input and output from devices other than an ASCII terminal.
 *
 * Room descriptions are followed by a room identifier, to ease debugging and testing. These would be removed to help confuse the
 * user, which is our ultimate aim.
 *
 * I haven't added all of the room descriptions. They will be done later.
 *
 * In this version all I/O is through standard I/O; I/O is to and from the console.
 */

public class Lvl0Adventure {

    public ArrayList<esof322.a4.Room> createAdventure() {
        ArrayList<esof322.a4.Room> list = new ArrayList<>();

        // The outside:
        Level0Factory lvl0Factory = new Level0Factory();

        esof322.a4.Room outside = lvl0Factory.createRoom("You are standing outside, on the edge of a cliff;\n" +
                                                         " A creek runs alongside the cliff.\n" +
                                                         "a cave opens straight down (outside).");
        list.add(outside);

        // Room 1:
        esof322.a4.Room r1 = lvl0Factory.createRoom("The darkness is pierced by a bright light overhead.\n"
                                                    + "There is a narrow, dark passage to the east (r1).");
        list.add(r1);

        // Connect the outside to Room 1:
        outside.setSide(5, r1);
        r1.setSide(4, outside);

        // Room 2:
        esof322.a4.Room r2 = lvl0Factory.createRoom("You are in a gloomy oval shaped room with grey walls.\n" +
                                                    "There is a dim light to the west, and a narrow\n" +
                                                    "dark hole to the east only about 18 inches high (r2).");
        list.add(r2);
        // Room 3:
        esof322.a4.Room r3 = lvl0Factory.createRoom("You really need your flashlight here. \n" +
                                                    "There is a wide passage that quickly narrows\n"
                                                    + "to the west, a bright opening to the east,\n"
                                                    + "and a deep hole that appears to have no bottom\n"
                                                    + "in the middle of the room (r3).");
        list.add(r3);

        // Connect Rooms 1, 2, & 3:
        r1.setSide(2, r2);
        r2.setSide(3, r1);
        r2.setSide(2, r3);
        r3.setSide(3, r2);

        // Room 4:
        esof322.a4.Room r4 = lvl0Factory.createRoom("There is what looks like a giant grizzly bear\n"
                                                    + "skull in a corner.  A passage leads to the west,\n"
                                                    + "another one to the north, and a slippery route\n"
                                                    + "goes down steeply. You can hear the shrieks of bats (r4).");
        list.add(r4);

        // Room 5:
        esof322.a4.Room r5 = lvl0Factory.createRoom("There is a dim light from above and the shrieks\n"
                                                    + "are clearly coming from a passageway to the east (r5).");
        list.add(r5);

        // Room 6:
        esof322.a4.Room r6 = lvl0Factory.createRoom("The ceiling is full of bats.\n"
                                                    + "You should put your hat on your head (r6).");
        list.add(r6);

        // Room 7:
        esof322.a4.Room r7 = lvl0Factory.createRoom("This room is very damp. There are puddles on the floor\n" +
                                                    "and a steady dripping from above (r7).");
        list.add(r7);

        // Connect rooms 3, 4, 5, 6, & 7.
        r3.setSide(2, r4);
        r3.setSide(5, r5);
        r4.setSide(3, r3);
        r4.setSide(5, r7);
        r5.setSide(4, r3);
        r5.setSide(2, r6);
        r6.setSide(3, r5);
        r7.setSide(4, r4);

        // Room 8:
        esof322.a4.Room r8 = lvl0Factory.createRoom("A lizard scampers past you, or is it a snake?\n" +
                                                    "a narrow passage runs to the east and an even narrower one\n" +
                                                    "runs to the west (r8).");
        list.add(r8);

        // Room 9:
        esof322.a4.Room r9 = lvl0Factory.createRoom("Room r9.");
        list.add(r9);

        // Room 10:
        esof322.a4.Room r10 = lvl0Factory.createRoom("It looks like someone has been here.\n" +
                                                     "There is a pile of candy wrappers on the floor,\n" +
                                                     "and maybe something else. \n" +
                                                     "Wait, there is a trap door on the floor,\n" +
                                                     "but it is locked (r10).");
        list.add(r10);

        // Room 11:
        esof322.a4.Room r11 = lvl0Factory.createRoom("This room is very dark. You can just barely see (r11).");
        esof322.a4.Treasure theTreasure = lvl0Factory.createTreasure("A bag filled with gold bars.");
        r11.addItem(theTreasure);
        list.add(r11);

        // Lets connect them:
        r4.setSide(0, r8);
        r8.setSide(1, r4);
        r8.setSide(3, r9);
        r8.setSide(2, r10);
        r9.setSide(2, r8);
        r10.setSide(3, r8);

        // Create a key and put it in r6:
        esof322.a4.Key theKey = lvl0Factory.createKey("A shiny gold key.");
        r6.addItem(theKey);

        // We add a door between r10 and r11:
        esof322.a4.Door theDoor = lvl0Factory.createDoor(r10, r11, theKey);
        r10.setSide(5, theDoor);
        r11.setSide(4, theDoor);

        return list;

    }
}

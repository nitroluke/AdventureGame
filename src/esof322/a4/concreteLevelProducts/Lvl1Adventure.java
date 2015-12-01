package esof322.a4.concreteLevelProducts;

import esof322.a4.concreteLevelFactories.Level1Factory;
import esof322.a4.*;

import java.util.ArrayList;

public class Lvl1Adventure {

    public ArrayList<Room> createAdventure() {
        ArrayList<Room> list = new ArrayList<>();

        Level1Factory lvl1Factory = new Level1Factory();

        Room outside = lvl1Factory.createRoom("You are standing outside, on the edge of a cliff;\n" +
                                              " A creek runs alongside the cliff.\n" +
                                              "a cave opens straight down (outside).");
        list.add(outside);

        Room r1 = lvl1Factory.createRoom("The darkness is pierced by a bright light overhead.\n"
                                         + "There is a narrow, dark passage to the east (r1).");
        list.add(r1);

        outside.setSide(5, r1);
        r1.setSide(4, outside);


        Room r2 = lvl1Factory.createRoom("You are in a gloomy oval shaped room with grey walls.\n" +
                                         "There is a dim light to the west, and a narrow\n" +
                                         "dark hole to the east only about 18 inches high (r2).");
        list.add(r2);

        Room r3 = lvl1Factory.createRoom("You really need your flashlight here. \n" +
                                         "There is a wide passage that quickly narrows\n"
                                         + "to the west, a bright opening to the east,\n"
                                         + "and a deep hole that appears to have no bottom\n"
                                         + "in the middle of the room (r3).");
        list.add(r3);

        r1.setSide(2, r2);
        r2.setSide(3, r1);
        r2.setSide(2, r3);
        r3.setSide(3, r2);

        Room r4 = lvl1Factory.createRoom("There is what looks like a giant grizzly bear\n"
                                         + "skull in a corner.  A passage leads to the west,\n"
                                         + "another one to the north, and a slippery route\n"
                                         + "goes down steeply. You can hear the shrieks of bats (r4).");
        list.add(r4);

        Room r5 = lvl1Factory.createRoom("There is a dim light from above and the shrieks\n"
                                         + "are clearly coming from a passageway to the east (r5).");
        list.add(r5);

        Room r6 = lvl1Factory.createRoom("The ceiling is full of bats.\n"
                                         + "You should put your hat on your head (r6).");
        list.add(r6);

        Room r7 = lvl1Factory.createRoom("This room is very damp. There are puddles on the floor\n" +
                                         "and a steady dripping from above (r7).");
        list.add(r7);

        r3.setSide(2, r4);
        r3.setSide(5, r5);
        r4.setSide(3, r3);
        r4.setSide(5, r7);
        r5.setSide(4, r3);
        r5.setSide(2, r6);
        r6.setSide(3, r5);
        r7.setSide(4, r4);

        Room r8 = lvl1Factory.createRoom("A lizard scampers past you, or is it a snake?\n" +
                                         "a narrow passage runs to the east and an evin narrower one\n" +
                                         "runs to the west (r8).");
        list.add(r8);

        Room r9 = lvl1Factory.createRoom("Room r9.");
        list.add(r9);

        Room r10 = lvl1Factory.createRoom("It looks like someone has been here.\n" +
                                          "There is a pile of candy wrappers on the floor,\n" +
                                          "and maybe something else. \n" +
                                          "Wait, there is a trap door on the floor marked \n" +
                                          "vault 101, but it is locked (r10).");
        list.add(r10);

        Room r11 = lvl1Factory.createRoom("This room is very dark. You can just barely see (r11).");
        Treasure theTreasure = lvl1Factory.createTreasure("a bobble head.");
        r11.addItem(theTreasure);
        list.add(r11);

        r4.setSide(0, r8);
        r8.setSide(1, r4);
        r8.setSide(3, r9);
        r8.setSide(2, r10);
        r9.setSide(2, r8);
        r10.setSide(3, r8);

        Key theKey = lvl1Factory.createKey("A shiny gold key.");
        r6.addItem(theKey);
        r8.addItem(theKey);

        Oil theOil = lvl1Factory.createOil("Oil.");

        outside.addItem(theOil);
        r2.addItem(theOil);
        r6.addItem(theOil);
        r8.addItem(theOil);
        r10.addItem(theOil);

        Door theDoor = lvl1Factory.createDoor(r10, r11, theKey);
        r10.setSide(5, theDoor);
        r11.setSide(4, theDoor);

        return list;
    }
}

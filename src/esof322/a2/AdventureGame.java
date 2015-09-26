package esof322.a2;

/**
 * Adventure Game Program Code Copyright (c) 1999 James M. Bieman
 *
 * To compile: javac cs314.a2.AdventureGame.java To run: java cs314.a2.AdventureGame The main routine is AdventureGame.main
 *
 * The AdventureGame is a Java implementation of the old text based adventure game from long ago. The design was adapted from one in
 * Gamma, Helm, Johnson, Vlissides (The Gang of Four), "Design Patterns: Elements of Reusable Object-Oriented Software",
 * Addison-Wesley, 1997.
 *
 * To really be consistent with the old game we would need a much larger cave system with a hundred or so rooms, and a more
 * "understanding" user interface.
 *
 * The old game just put you near the cave, displayed the "view" as text, and offered no instructions. If you gave a command that it
 * understood, you could proceed. If your command could not be interpreted, nothing would happen. Rooms were never identified
 * precisely; your only clues came from the descriptions. You would have to remember or create your own map of the cave system to
 * find your way around. Sometimes you could not return exactly the way you came. An exit to the east may not enter the west side of
 * the "adjacent room"; the passage might curve.
 *
 * Perhaps, this implementation can evolve to be closer to the original game, or even go beyond it.
 *
 * Jim Bieman September 1999.
 *
 *
 * /** Adventure Game Program Code Copyright (c) 1999 James M. Bieman Updated August 2010 - Code is put into package cs314.a2 to
 * match current CS314 coding standards. - Obsolete Vector is replaced with ArrayList with type parameters. - Deletion of some
 * unused variables.
 *
 * To compile: javac cs314.a2.AdventureGame.java To run: java cs314.a2.AdventureGame
 *
 * The main routine is AdventureGame.main
 **/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Todd Beckman: convertDirection uses char. This is because it is only ever called from char and is
 * guaranteed to have a length of one. It now returns the value directly instead of storing it first
 * and breaking cases. Bad direction input is now -1 which is much more intuitive than 9999. The
 * input character is forced to be lowercase to eliminate half of the test cases.
 * 
 * Todd Beckman: Move input check out into a separate caller. This involved making the keyboard
 * reader a private field and making a private method to interface with it. This handles both elegant
 * design and prepares the program to receive input from the gui instead of the keyboard. An integer
 * receiver was also made to support integer input rather than first character input.
 * 
 * Kalvyn Lu: Player is now a global variable. Added getPlayer() method
 */
public class AdventureGame {
    private AdventureGameModelFacade model;
    
    /** Keep track of which type of input to look for */
    private boolean usingKeyboard;
    
    /** Create the keyboard to control the game; we only need one */
    private BufferedReader keyboard;
    
    /** Create the input to control the game; we only need one */
    private InputListener listener;
    /**
     * Force the program to wait for user input. Gets the first character of input.
     * @return The user's input or ' ' on failure
     */
    
    Player thePlayer = new Player();
    
    public Player getPlayer(){
        return thePlayer;
    }
    
    private char receiveChar() {
        if (usingKeyboard) {
            try {
                return keyboard.readLine().toLowerCase().charAt(0);
            }
            //  Empty String
            catch (StringIndexOutOfBoundsException e1) {
                return ' ';
            }
            //  Buffered Reader fails
            catch (IOException e) {
                return ' ';
            }
        }
        else {
            try {
                return listener.receive().toLowerCase().charAt(0);
            }
            //  Empty String
            catch (StringIndexOutOfBoundsException e) {
                return ' ';
            }
        }
    }
    /**
     * Force the program to wait for user input. Gets an integer input.
     * @return The user's input or -1 on failure.
     */
    private int receiveInt() {
        String input;
        if (usingKeyboard) {
            try {
                input = keyboard.readLine();
            }
            //  Buffered Reader fails
            catch (IOException e) {
                return -1;
            }
        }
        else {
            input = listener.receive();
        }
        try {
            return Integer.parseInt(input);
        }
        //  String not a number
        catch (NumberFormatException e) {
            return -1;
        }
    }
    /**
     * Our system-wide internal representation of directions is integers. Here, we convert input string directions into integers.
     * Internally, we use integers 0-9 as directions throughout the program. This is a bit of a cludge, but is simpler for now than
     * creating a Direction class. I use this cludge because Java in 1999 did not have an enumerated data type.
     */
    private int convertDirection(char input) {
        switch (input) {
        case 'n':
            return 0;
        case 's':
            return 1;
        case 'e':
            return 2;
        case 'w':
            return 3;
        case 'u':
            return 4;
        case 'd':
            return 5;
        }
        return -1;
    }

    /**
     * choosePickupItem determines the specific item that a player wants to pick up.
     */
    private Item choosePickupItem(Player p) {
        Item[] contentsArray = (p.getLoc()).getRoomContents();
        int theChoice = -1;

        do {
            System.out.println("The room has:");
            for (int i = 0; i < contentsArray.length; i++) {
                System.out.println((i + 1) + ": "
                                   + contentsArray[i].getDesc());
            }
            System.out.print("Enter the number of the item to grab: ");
            theChoice = receiveInt();
            if (theChoice < 0 || theChoice > contentsArray.length)
                System.out.print("That item is not in the room.");
        } while (theChoice < 0 || theChoice > contentsArray.length);

        return contentsArray[theChoice - 1];

    }

    /**
     * chooseDropItem determines the specific item that a player wants to drop
     */
    private int chooseDropItem(Player p) {
        int theChoice = -1;

        do {
            System.out.println("You are carrying: " +
                               p.showMyThings() + '\n');
            System.out.print("Enter the number of the item to drop: ");
            theChoice = receiveInt();
            if (theChoice < 0 || theChoice > p.numItemsCarried())
                System.out.print("Invalid choice.");
        } while (theChoice < 0 || theChoice > p.numItemsCarried());

        return theChoice;
    }
    
    public void startQuest() {
        thePlayer = new Player();
        Adventure theCave = new Adventure();
        Room startRm = theCave.createAdventure();
        thePlayer.setRoom(startRm);

        char key = 'p'; //      p for prepare

        /* The main query user, get command, interpret, execute cycle. */
        while (key != 'q') {
            System.out.println(thePlayer.look());
            System.out.println("You are carrying: " +
                               thePlayer.showMyThings() + '\n');
            /* get next move */
            int direction = -1;

            System.out.println("Which way (n,s,e,w,u,d)," +
                               " or grab (g) or toss (t) an item," +
                               " or quit (q)?" + '\n');
            key = receiveChar();
            System.out.println('\n');
            direction = convertDirection(key);
            if (direction != -1) {
                thePlayer.go(direction);
            }
            //	Grab item
            else if (key == 'g') {
                if (thePlayer.handsFull())
                    System.out.println("Your hands are full.");
                else if ((thePlayer.getLoc()).roomEmpty())
                    System.out.println("The room is empty.");
                else {
                    Item itemToGrab =
                        choosePickupItem(thePlayer);
                    thePlayer.pickUp(itemToGrab);
                    (thePlayer.getLoc()).removeItem(itemToGrab);
                }
            }
            //  Toss Item
            else if (key == 't') {
                if (thePlayer.handsEmpty())
                    System.out.println("You have nothing to drop.");
                else {
                    int itemToToss = chooseDropItem(thePlayer);
                    thePlayer.drop(itemToToss);
                }
            }
        }

    }
    /**
     * Set up a game in which a messaging system will be used
     * @param listener The listener to act as the interface
     */
    public AdventureGame(AdventureGameModelFacade model, InputListener listener) {
        this.model = model;
        this.listener = listener;
        usingKeyboard = false;
    }
    /**
     * Setup a game in which the keyboard will be used
     */
    public AdventureGame() {
        keyboard = new BufferedReader(new InputStreamReader(System.in));
        usingKeyboard = true;
    }

    public static void main(String args[])
        throws IOException {
        System.out.println("Welcome to the Adventure Game,\n"
                           + "which is inspired by an old game called the Colossal Cave Adventure.\n"
                           + "Java implementation Copyright (c) 1999 - 2012 by James M. Bieman\n");
        AdventureGame theGame = new AdventureGame();
        theGame.startQuest();
    }

}

package esof322.a4;

import java.util.ArrayList;
import java.io.FileNotFoundException;

import esof322.a4.Serializer;

/**
 * Adventure Game Program Code Copyright (c) 1999 James M. Bieman
 * <p>
 * To compile: javac cs314.a2.AdventureGame.java To run: java cs314.a2.AdventureGame The main routine is AdventureGame.main
 * <p>
 * The AdventureGame is a Java implementation of the old text based adventure game from long ago. The design was adapted from one in
 * Gamma, Helm, Johnson, Vlissides (The Gang of Four), "Design Patterns: Elements of Reusable Object-Oriented Software",
 * Addison-Wesley, 1997.
 * <p>
 * To really be consistent with the old game we would need a much larger cave system with a hundred or so rooms, and a more
 * "understanding" user interface.
 * <p>
 * The old game just put you near the cave, displayed the "view" as text, and offered no instructions. If you gave a command that it
 * understood, you could proceed. If your command could not be interpreted, nothing would happen. Rooms were never identified
 * precisely; your only clues came from the descriptions. You would have to remember or create your own map of the cave system to
 * find your way around. Sometimes you could not return exactly the way you came. An exit to the east may not enter the west side of
 * the "adjacent room"; the passage might curve.
 * <p>
 * Perhaps, this implementation can evolve to be closer to the original game, or even go beyond it.
 * <p>
 * Jim Bieman September 1999.
 * <p>
 * <p>
 * /** Adventure Game Program Code Copyright (c) 1999 James M. Bieman Updated August 2010 - Code is put into package cs314.a2 to
 * match current CS314 coding standards. - Obsolete Vector is replaced with ArrayList with type parameters. - Deletion of some
 * unused variables.
 * <p>
 * To compile: javac cs314.a2.AdventureGame.java To run: java cs314.a2.AdventureGame
 * <p>
 * The main routine is AdventureGame.main
 **/

/*
 * Todd Beckman
 * Dylan Hills
 * Kalvyn Lu
 * Luke O'Neill
 * Luke Welna
 */
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
 * Dylan Hills: Added model.setView and model.setAction to alter what the GUI displays.
 * 
 * Todd Beckman: Pulled out the prints into separate methods so that everything is printed through the
 * same methods and thus guarantee the same messages are printed (and only once). Also fixed an
 * off-by-one error in grab/drop that was an issue in the provided code.
 */
public class AdventureGame {
    private AdventureGameModelFacade model;

    /**
     * Keep track of which type of input to look for
     */
    private boolean usingKeyboard;

    /**
     * Create the keyboard to control the game; we only need one
     */
    private java.io.BufferedReader keyboard;

    /**
     * Create the input to control the game; we only need one
     */
    private InputListener listener;

    /**
     * The current player
     **/
    private Player thePlayer = new Player();

    /**
     * Gets the player of the game
     **/
    public Player getPlayer() {
        return thePlayer;
    }

    /**
     * Force the program to wait for user input. Gets the first character of input.
     *
     * @return The user's input or ' ' on failure
     */
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
            catch (java.io.IOException e) {
                return ' ';
            }
        } else {
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
     *
     * @return The user's input or -1 on failure.
     */
    private int receiveInt() {
        String input;
        if (usingKeyboard) {
            try {
                input = keyboard.readLine();
            }
            //  Buffered Reader fails
            catch (java.io.IOException e) {
                return -1;
            }
        } else {
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
     * Prints a message to the view slot
     *
     * @param message The message to print
     */
    private void printView(String message) {
        if (usingKeyboard) {
            System.out.println(message);
        } else {
            model.setView(message);
        }
    }

    /**
     * Prints a message to the action slot
     *
     * @param message The message to print
     */
    private void printAction(String message) {
        if (usingKeyboard) {
            System.out.println(message);
        } else {
            model.setAction(message);
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
            String message = "The room has:\n";
            for (int i = 0; i < contentsArray.length; i++) {
                message += (i + 1) + ": "
                           + contentsArray[i].getDesc() + "\n";
            }
            message += "Enter the number of the item to grab: ";
            printView(message);
            theChoice = receiveInt();
            if (theChoice <= 0 || theChoice > contentsArray.length)
                printView("That item is not in the room.");
        } while (theChoice <= 0 || theChoice > contentsArray.length);
        return contentsArray[theChoice - 1];

    }

    /**
     * chooseDropItem determines the specific item that a player wants to drop
     */
    private int chooseDropItem(Player p) {
        int theChoice = -1;
        do {
            String message = "You are carrying: " +
                             p.showMyThings() + '\n';
            message += "Enter the number of the item to drop: ";
            printView(message);
            theChoice = receiveInt();
            if (theChoice <= 0 || theChoice > p.numItemsCarried())
                printView("Invalid choice.");
            else if (p.numItemsCarried() == 0) {
                printView("You have no items to drop.");
            }
        } while (theChoice <= 0 || theChoice > p.numItemsCarried());

        return theChoice;
    }

    public ArrayList<Room> loadGame() {
        ArrayList<Room> rooms;
        String message = "Would you like to load a previous game? (y/n)\n";

        do {
            printView(message);
            char loadGame = receiveChar();

            if (loadGame == 'y') {
                try {
                    printView("trying to load a previous game.");
                    Serializer serializer = new Serializer();
                    rooms = (ArrayList<Room>)serializer.deserialize("Rooms");
                    thePlayer = (Player)serializer.deserialize("Player");
                    printView("Load successful...");
                    break;
                } catch (FileNotFoundException fnf) {
                    message += "There are no previous saves\n";
                }

            }

            if (loadGame == 'n') {
                printView("starting a new game!");
                thePlayer = new Player();
                Adventure theCave = new Adventure();
                rooms = theCave.createAdventure();
                Room startRm = rooms.get(0);
                thePlayer.setRoom(startRm);
                break;
            } else {
                message += "That is not a valid option \n";
            }
        } while (true);

        return rooms;
    }

    public void startQuest() {

        ArrayList rooms = loadGame();
        char key = 'p';

        /* The main query user, get command, interpret, execute cycle. */
        while (key != 'q') {
            printView(thePlayer.look() + "\n\nYou are carrying: " +
                      thePlayer.showMyThings() + '\n');
            /* get next move */
            int direction = -1;
            //  We only care about this in keyboard mode
            if (usingKeyboard) {
                System.out.println("Which way (n,s,e,w,u,d),\n" +
                                   " or grab (g) or toss (t) an item,\n" +
                                   " or quit & save (q)?" + '\n');
            }
            key = receiveChar();
            direction = convertDirection(key);
            if (direction != -1) {
                printAction(thePlayer.go(direction));
            }
            //	Grab item
            else if (key == 'g') {
                if (thePlayer.handsFull()) {
                    printAction("Your hands are full.");
                } else if ((thePlayer.getLoc()).roomEmpty()) {
                    printAction("The room is empty.");
                } else {
                    Item itemToGrab =
                                    choosePickupItem(thePlayer);
                    thePlayer.pickUp(itemToGrab);
                    (thePlayer.getLoc()).removeItem(itemToGrab);
                    printAction("Grabbed the item " + itemToGrab.getDesc());
                }
            }
            //  Toss Item
            else if (key == 't') {
                if (thePlayer.handsEmpty()) {
                    printAction("You have nothing to drop.");
                } else {
                    int itemToToss = chooseDropItem(thePlayer);
                    printAction("Dropped " + thePlayer.drop(itemToToss));
                }
            }
        }
        Serializer serializer = new Serializer();
        serializer.serialize(rooms, "Rooms");
        serializer.serialize(thePlayer, "Player");
        System.exit(0);
    }

    /**
     * Set up a game in which a messaging system will be used
     *
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
        keyboard = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        usingKeyboard = true;
    }

    public static void main(String args[])
                    throws java.io.IOException {
        System.out.println("Welcome to the Adventure Game,\n"
                           + "which is inspired by an old game called the Colossal Cave Adventure.\n"
                           + "Java implementation Copyright (c) 1999 - 2012 by James M. Bieman\n");
        AdventureGame theGame = new AdventureGame();

        theGame.startQuest();
    }

}

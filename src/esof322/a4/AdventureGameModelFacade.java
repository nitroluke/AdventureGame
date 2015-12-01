package esof322.a4;

/*
 * Todd Beckman
 * Dylan Hills
 * Kalvyn Lu
 * Luke O'Neill
 * Luke Welna
 */
/*
 * Todd Beckman: Provided private game field to be initialized at construction
 * Kalvyn Lu: Added game(), drop(), and takeInput() Methods. The game also shows the inventory.
 * Dylan Hills: Added roomView, action, getView(), setView(), getAction(),setAction(). Added AdventureGameView and setGUI().
 */
public class AdventureGameModelFacade {

    // some private fields to reference current location,
    // its description, what I'm carrying, etc.
    //
    // These methods and fields are left as exercises.
    /**
     * The game from which data will be requested
     **/
    private AdventureGame game;
    private AdventureGameView view;
    private String roomView = "";
    private String action = "";

    /**
     * The input mechanism to interface with the game
     **/
    private InputListener listener;

    AdventureGameModelFacade() { // we initialize
        this.listener = new InputListener();
        this.game = new AdventureGame(this, listener);
    }

    //TODO implement newgame?
    public void startQuest() {
        game.startQuest();
    }

    public void goUp() {
        listener.send("u");
    }

    public void goDown() {
        listener.send("d");
    }

    public void goNorth() {
        listener.send("n");
    }

    public void goSouth() {
        listener.send("s");
    }

    public void goEast() {
        listener.send("e");
    }

    public void goWest() {
        listener.send("w");
    }

    public void grab() {
        listener.send("g");
    }

    public void drop() {
        listener.send("t");
    }

    public void quit(){listener.send("q");}

    public void takeInput(String input) {
        listener.send(input);
    }

    public void setGUI(AdventureGameView gui) {
        this.view = gui;
    }

    public void setView(String views) {
        this.roomView = views;
        view.displayCurrentInfo();
    }

    // You need to finish these getView and getItems methods.
    public String getView() {
        return roomView;
    }

    public String getItems() {
        return game.getPlayer().showMyThings();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String a) {
        action = a;
        view.displayCurrentInfo();
    }

    // Surely you will need other methods to deal with
    // picking up and dropping things.

}

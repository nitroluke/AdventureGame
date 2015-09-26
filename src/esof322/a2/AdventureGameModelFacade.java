package esof322.a2;

/*
 * Todd Beckman: Provided private game field to be initialized at construction
 */
public class AdventureGameModelFacade {

    // some private fields to reference current location,
    // its description, what I'm carrying, etc.
    //
    // These methods and fields are left as exercises.
    /** The game from which data will be requested **/
    private AdventureGame game;
    
    /** The input mechanism to interface with the game **/
    private InputListener listener;
    
    AdventureGameModelFacade() { // we initialize
        this.listener = new InputListener();
        this.game = new AdventureGame(this, listener);
    }
    
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

    public void grab(){
        listener.send("g");
    }
    public void drop(){
        listener.send("t");
    }
    public void takeInput(String input){
        listener.send(input);
    }
    
    
    // You need to finish these getView and getItems methods.
    public String getView() {
        return ("My view");
    }

    public String getItems() {
        return game.getPlayer().showMyThings();
    }

    // Surely you will need other methods to deal with
    // picking up and dropping things.

}

package esof322.a2;

/*
 * Todd Beckman: Provided private game field to be initialized at construction
 */
public class AdventureGameModelFacade {

    // some private fields to reference current location,
    // its description, what I'm carrying, etc.
    //
    // These methods and fields are left as exercises.
    private AdventureGame game;
    
    AdventureGameModelFacade() { // we initialize
        this.game = new AdventureGame();
    }

    public void goUp() {}

    public void goDown() {}

    public void goNorth() {}

    public void goSouth() {}

    public void goEast() {}

    public void goWest() {}

    // You need to finish these getView and getItems methods.
    public String getView() {
        return ("My view");
    }

    public String getItems() {
        return ("My items");
    }

    // Surely you will need other methods to deal with
    // picking up and dropping things.

}

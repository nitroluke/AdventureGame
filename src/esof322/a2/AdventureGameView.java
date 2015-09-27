package esof322.a2;

import BreezySwing.GBFrame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/*
 * Todd Beckman: Added startQuest method to invoke the model's startQuest
 * Kalvyn Lu: Added a TextArea for String input. Hooked up grab and drop buttons to the model.
 */
public class AdventureGameView extends GBFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

// Window objects --------------------------------------
    JLabel welcomeLabel =
        addLabel("Welcome to the Adventure Game " +
                 "(inspired by an old game called the Colossal Cave Adventure)." +
                 " Java implementation Copyright (c) 1999-2012 by James M. Bieman",
                 1, 1, 5, 1);

    JLabel viewLable = addLabel("Your View: ", 2, 1, 1, 1);
    JTextArea viewArea = addTextArea("Start", 3, 1, 3, 7);

    JLabel carryingLable = addLabel("You are carying: ", 6, 4, 1, 1);
    JTextArea carryingArea = addTextArea("Nothing", 7, 4, 3, 3);

    JLabel separator1 = addLabel
        ("-----------------------------------------------------------------"
         , 10, 1, 4, 1);

    JLabel choiceLabel = addLabel
        ("Choose a direction, pick-up, or drop an item", 11, 1, 5, 1);

    JButton grabButton = addButton("Grab an item", 12, 5, 1, 1);
    JButton dropButton = addButton("Drop an item", 13, 5, 1, 1);

    JButton northButton = addButton("North", 12, 2, 1, 1);
    JButton southButton = addButton("South", 14, 2, 1, 1);
    JButton eastButton = addButton("East", 13, 3, 1, 1);
    JButton westButton = addButton("West", 13, 1, 1, 1);
    JButton upButton = addButton("Up", 12, 3, 1, 1);
    JButton downButton = addButton("Down", 14, 3, 1, 1);
    
    JTextArea textInput = addTextArea("Input",15,3,1,1);
    JButton textInputButton = addButton("Submit",15,4,1,1);

    AdventureGameModelFacade model;

    // Constructor-----------------------------------------------

    public AdventureGameView() {
        setTitle("Adventure Game");
        model = new AdventureGameModelFacade();

        viewArea.setEditable(false);
        carryingArea.setEditable(false);
    }

    // buttonClicked method--------------------------------------

    public void buttonClicked(JButton buttonObj) {
        if (buttonObj == upButton)
            model.goUp();

        else if (buttonObj == downButton)
            model.goDown();

        else if (buttonObj == northButton)
            model.goNorth();

        else if (buttonObj == southButton)
            model.goSouth();

        else if (buttonObj == eastButton)
            model.goEast();

        else if (buttonObj == westButton)
            model.goWest();

        else if (buttonObj == grabButton)
            grab();
        else if (buttonObj == dropButton)
            drop();
        else if (buttonObj == textInputButton){
            model.takeInput(textInput.getText());
            textInput.replaceRange("", 0, textInput.getText().length());
        }
    }

    // Private methods-------------------------------------------

    public void displayCurrentInfo() {
        viewArea.setText(model.getView());
        carryingArea.setText(model.getItems());
    }

    // Left as an exercise.
    private void grab() {
        // Set up a dialog to talk to the model and
        // determine what items to pick up.
        model.grab();
        //carryingArea.setText(model.getItems());
    }

    // Left as an exercise.
    private void drop() {
        // Set up a dialog to talk to the model and
        // determine what items to pick up.
        model.drop();
    }
    
    public void startQuest() {
    	model.setGUI(this);
        model.startQuest();
    }

    public static void main(String[] args) {
        AdventureGameView view = new AdventureGameView();
        view.setSize(800, 600); /* was 400, 250 */
        view.setVisible(true);
        view.startQuest();
    }
}

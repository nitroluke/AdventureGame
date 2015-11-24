package esof322.a4;

/*
 * Todd Beckman
 * Dylan Hills
 * Kalvyn Lu
 * Luke O'Neill
 * Luke Welna
 */
/*
 * Todd Beckman: Added startQuest method to invoke the model's startQuest
 * Kalvyn Lu: Added a TextArea for String input. Hooked up grab and drop buttons to the model.
 * Dylan Hills: Added an action TextArea to display what the player just did.
 */
public class AdventureGameView extends BreezySwing.GBFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

// Window objects --------------------------------------
    javax.swing.JLabel welcomeLabel =
        addLabel("Welcome to the Adventure Game " +
                 "(inspired by an old game called the Colossal Cave Adventure)." +
                 " Java implementation Copyright (c) 1999-2012 by James M. Bieman",
                 1, 1, 5, 1);

    javax.swing.JLabel viewLabel = addLabel("Your View: ", 2, 1, 1, 1);
    javax.swing.JTextArea viewArea = addTextArea("Start", 3, 1, 3, 7);

    javax.swing.JLabel carryingLabel = addLabel("You are carrying: ", 6, 4, 1, 1);
    javax.swing.JTextArea carryingArea = addTextArea("Nothing", 7, 4, 3, 3);

    javax.swing.JLabel actionLabel = addLabel("Action:", 2, 4, 1, 1);
    javax.swing.JTextArea actionArea = addTextArea("Action", 3, 4, 3, 3);
    javax.swing.JLabel separator1 = addLabel
        ("-----------------------------------------------------------------"
         , 10, 1, 4, 1);

    javax.swing.JLabel choiceLabel = addLabel
        ("Choose a direction, pick-up, or drop an item", 11, 1, 5, 1);

    javax.swing.JButton grabButton = addButton("Grab an item", 12, 5, 1, 1);
    javax.swing.JButton dropButton = addButton("Drop an item", 13, 5, 1, 1);

    javax.swing.JButton northButton = addButton("North", 12, 2, 1, 1);
    javax.swing.JButton southButton = addButton("South", 14, 2, 1, 1);
    javax.swing.JButton eastButton = addButton("East", 13, 3, 1, 1);
    javax.swing.JButton westButton = addButton("West", 13, 1, 1, 1);
    javax.swing.JButton upButton = addButton("Up", 12, 3, 1, 1);
    javax.swing.JButton downButton = addButton("Down", 14, 3, 1, 1);

    javax.swing.JButton quitButton = addButton("QUIT and SAVE", 15, 5, 1, 1);

    javax.swing.JTextArea textInput = addTextArea("1", 15, 3, 1, 1);
    javax.swing.JButton textInputButton = addButton("Submit", 15, 4, 1, 1);

    AdventureGameModelFacade model;

    // Constructor-----------------------------------------------

    public AdventureGameView() {
        setTitle("Adventure Game");
        model = new AdventureGameModelFacade();

        viewArea.setEditable(false);
        carryingArea.setEditable(false);
    }

    // buttonClicked method--------------------------------------

    public void buttonClicked(javax.swing.JButton buttonObj) {
        if (buttonObj == upButton) {
            model.goUp();
        }
        else if (buttonObj == downButton) {
            model.goDown();
        }
        else if (buttonObj == northButton) {
            model.goNorth();
        }
        else if (buttonObj == southButton) {
            model.goSouth();
        }
        else if (buttonObj == eastButton) {
            model.goEast();
        }
        else if (buttonObj == westButton) {
            model.goWest();
        }
        else if (buttonObj == grabButton) {
            grab();
        }
        else if (buttonObj == dropButton) {
            drop();
        }
        else if (buttonObj == textInputButton) {
            model.takeInput(textInput.getText());
            textInput.replaceRange("", 0, textInput.getText().length());
        }
        else if (buttonObj == quitButton){
            quitAndSave();

            }
        }


    // Private methods-------------------------------------------

    public void displayCurrentInfo() {
        viewArea.setText(model.getView());
        carryingArea.setText(model.getItems());
        actionArea.setText(model.getAction());
    }

    // Left as an exercise.
    private void grab() {
        model.grab();
    }

    // Left as an exercise.
    private void drop() {
        model.drop();
    }

    public void quitAndSave(){
        System.out.println(quitButton.getActionCommand());
        System.exit(0);
    }

    public void startQuest() {
    	model.setGUI(this);
        model.startQuest();
    }

    public static void main(String[] args) {
        AdventureGameView view = new AdventureGameView();
        view.setSize(800, 600);
        view.setVisible(true);
        view.startQuest();
    }
}

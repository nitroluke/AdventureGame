package esof322.a4;

import java.io.*;
/**
 * Created by lwelna on 11/21/15.
 */
public class Deserializer {

    public void deserializer() {
        try {
            // Rooms

            FileInputStream fileIn = new FileInputStream("/tmp/employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object e = in.readObject();
            e = (Room)e;
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
    }
}


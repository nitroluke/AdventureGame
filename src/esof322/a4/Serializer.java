package esof322.a4;

import java.io.*;

/**
 * Created by lwelna on 11/21/15.
 */
public class Serializer {

    public void serialize(Object o) {

        try {
            //System.out.println(System.getProperty("user.dir"));
            FileOutputStream fileOut = new FileOutputStream("Team_11/src/tmp/saveData.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(o);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in Team_11/src/tmp/saveData.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}

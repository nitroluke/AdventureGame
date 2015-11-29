package esof322.a4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.io.IOException;
import java.io.File;

/**
 * Created by lwelna on 11/21/15.
 */
public class Serializer {

    public File serialize(Object o, String fileName) {
        try {
            System.out.println("trying to create file:\n Team_11" + File.separator + "saveData" + File.separator + fileName + ".ser\n");
            File f = new File("Team_11" + File.separator + "saveData" + File.separator + fileName + ".ser");
            System.out.println("actually wrote to \n" + f.getAbsoluteFile());

            FileOutputStream fileOut = new FileOutputStream("Team_11" + File.separator +"saveData" + File.separator + fileName + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(o);
            out.close();
            fileOut.close();
            System.out.println(fileName + "'s saved data is written to \n" + f.getAbsoluteFile() + "\n");
            return f;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        }
    }

    public Object deserialize(String fileName) throws FileNotFoundException{
        try {
            FileInputStream fileIn = new FileInputStream("Team_11" + File.separator + "saveData" + File.separator + fileName + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object o = in.readObject();
            in.close();
            fileIn.close();
            return o;
        }  catch (ClassNotFoundException c) {
            System.out.println("class not found");
            c.printStackTrace();
            return null;
        } catch (FileNotFoundException fnf){
            throw fnf;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        }
    }
}

package liracs.view.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aluno
 */
public class Archive {

    /**
     *
     * @author PC
     */
    private boolean active = false;
    private Scanner input;
    private char key;
    private Formatter output;

    /**
     * Creates new form Archive
     */
    public Archive() {
        try {
            File file = new File("Config.txt");
            file.createNewFile();
            input = new Scanner(file);
            while (input.hasNext()) {
                key = input.next().charAt(0);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Archive.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Archive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public char getHotKey() {
        try {
            File file = new File("Config.txt");
            file.createNewFile();
            input = new Scanner(file);
            while (input.hasNext()) {
                key = input.next().charAt(0);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Archive.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Archive.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
    }
    
    public void setHotKey(char keyLida) {
        
        BufferedWriter output = null;
        try {
            File file = new File("Config.txt");
            output = new BufferedWriter(new FileWriter(file));
            output.write(keyLida);
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        
    }
    
}
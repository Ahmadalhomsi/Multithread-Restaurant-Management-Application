/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author xandros
 */
public class TextFiler {

    public void createFile() {
        try {
            File myObj = new File("steps.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void updateFile(String s) {
        try {
            FileWriter myWriter = new FileWriter("steps.txt");
            //myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.write(s);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public PrintStream readTerminal() {
        // Specify the file path for the output
        String outputPath = "steps.txt";

        try {
            // Create a PrintStream to write to the file
            PrintStream fileStream = new PrintStream(new FileOutputStream(outputPath));

            // Redirect System.out and System.err to the file
            System.setOut(fileStream);
            System.setErr(fileStream);

            // Your program logic here
            //System.out.println("This will be written to the file.");

            // Don't forget to close the file stream when done
            //fileStream.close();
            return fileStream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movierating;

/**
 *
 * @author deepakkumar
 */
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class MovieRating {

    /**
     * @param args the command line arguments
     */
    
    String json_input;
    
    MovieRating(String json_filename) {
        json_input = read(json_filename);
        if (!json_input.isEmpty()) {
            System.out.println(json_input);
        }
    }
    
    public final String read(String filename){
        BufferedReader input = null;
        StringBuilder data = new StringBuilder("");
        String line;
        
        try{
            input = new BufferedReader(new FileReader(filename));   
            while((line = input.readLine()) != null) { data.append(line); }
            input.close();
        }catch(IOException e){
            System.err.println("Caught Exception while reading the json file");
            System.exit(1);
        }
        
        return data.toString();
    }
    
    public void write(){
    
    }
    
    public void calculateAverageGenre(){
        
    }
            
    public static void main(String[] args) {
        // TODO code application logic here
        MovieRating mr = new MovieRating(args[0]);
    }
}

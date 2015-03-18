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
import com.fasterxml.jackson.core.*;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.TreeMapper;


public class MovieRating {

    /**
     * @param args the command line arguments
     */

    String json_input;
    String json_file;
    Map<String, Double> rating = null;

    MovieRating(String json_filename) {
        json_file  = json_filename;
        json_input = read(json_filename);
        rating = new HashMap<String, Double>();
    }

    public final String read(String filename) {
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
        TreeMapper mapper = new TreeMapper();
        try{
            JsonNode root = mapper.readTree(json_input);
            //System.out.println(root.get("movies"));
            JsonNode movies = root.get("movies");

            for(JsonNode movie : movies ){
                JsonNode genres = movie.get("genres");
                Double old = 0.0d;
                for(JsonNode genre : genres){
                    if((old = rating.get(genre.toString())) != null){
                        rating.put(genre.toString(), old + movie.get("rating").asDouble());
                    }
                    else{
                        rating.put(genre.toString(), movie.get("rating").asDouble());
                    }
                }
            }

        }catch(Exception e){
            System.out.println("Caught exception while parsing the json file");
        }
    }

    public void publish_ratings(){
        for(Map.Entry<String, Double> m : rating.entrySet()){
            System.out.println("Rating for:" + m.getKey() + "  " + m.getValue());
        }
    }


    public static void main(String[] args) {
        // TODO code application logic here
        MovieRating mr = new MovieRating("/home/deepak/NetBeansProjects/MovieRating/src/movierating/movies.json");
        mr.calculateAverageGenre();
        mr.publish_ratings();
    }
}

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        //10000, 0, 1
        try {
            RandomNumbers test = new RandomNumbers(
                    Integer.parseInt(args[0]),
                    Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]));
            test.closeDataStreams();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        //10000 0 1 - przykładowe parametry programu
        try {
            RandomNumbers test = new RandomNumbers( //Tworzymy obiekt i przekazujemy mu parametry programu
                    Integer.parseInt(args[0]),
                    Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]));     //Większość metod wywołuje się sama przez konstruktor
            test.closeDataStreams(); //Zamykamy strumienie
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}

import java.io.*;
import java.text.NumberFormat;
import java.util.Random;

public class RandomNumbers {
    private final int count;
    private final int mean;
    private final int standardDeviation;
    //Poniżej rzeczy potrzebne do zapisu do plików
    DataOutputStream binaryOutputStream;
    BufferedReader binaryInputStream;
    FileWriter fileWriter;
    PrintWriter textOutputStream;

    public RandomNumbers(int count, int mean, int standardDeviation)throws FileNotFoundException, IOException {
        assert (standardDeviation >= 0); //Upewniamy się, że standardDeviation jest nieujemne
        this.count = count;
        this.mean = mean;
        this.standardDeviation = standardDeviation;
        //Tworzymy rzeczy potrzebne do zapisu do plików
        this.binaryOutputStream = new DataOutputStream(new FileOutputStream("binout.dat"));
        this.binaryInputStream = new BufferedReader(new InputStreamReader(new FileInputStream("binout.dat")));
        this.fileWriter = new FileWriter("textout.txt");
        this.textOutputStream = new PrintWriter(getFileWriter());
        //Generujemy liczby pseudo-losowe
        for (int i = 0; i < getCount(); i++) {
            double randomNumber = generateRandomNumber(); //metoda generująca jedną liczbę losową
            String randomNumberString = format(randomNumber); //formatujemy liczbę, żeby miała ",", a nie "."
            writeLineToBinaryFile(randomNumberString); //Zapisujemy do pliku binarnego
        }
        //Odczytujemy plik binarny i zapisujemy do pliku tekstowego
        for (int i = 0; i < getCount(); i++) {
            String line = readLineFromBinaryFile(); //Odczyt
            writeLineToTextFile(i, line); //Zapis
        }
    }

    public void closeDataStreams() throws IOException{ //Metoda zamykająca rzeczy potrzebne do zapisywania do plików
        getBinaryOutputStream().close();
        getBinaryInputStream().close();
        getFileWriter().close();
        getTextOutputStream().close();
    }

    public void writeLineToBinaryFile(String number) throws IOException{ //Metoda zapisuje wylosowaną liczbę oraz znak końca linii
        getBinaryOutputStream().writeChars(number);
        getBinaryOutputStream().writeChar('\n');
    }

    public String readLineFromBinaryFile() throws IOException { //Odczytaj i zwróć linie z pliku binarnego
        String result;
        result = getBinaryInputStream().readLine();
        return result;
    }

    public void writeLineToTextFile(int line, String number) throws IOException{
        number = number.replaceAll("\u0000", ""); //Zastąp wszystkie znaki "null" pustym znakiem
        getTextOutputStream().print(line + 1); //numer linii
        getTextOutputStream().print("\u0009"); //tabulator
        getTextOutputStream().println(number); //liczba
    }

    public double generateRandomNumber(){ //Wylosuj jedną liczbę pseudolosową z podaną średnią i odchyleniem standardowym
        Random r = new Random();
        Double result = r.nextGaussian()*getStandardDeviation()+getMean();
        return result;
    }

    public String format(double number){
        String result = NumberFormat.getInstance().format(number); //Pobierz z systemu format, który jest używany (czyli u nas polski)
        return result;
    }

    //Pomocnicze gettery
    public int getCount() {
        return count;
    }

    public int getMean() {
        return mean;
    }

    public int getStandardDeviation() {
        return standardDeviation;
    }

    public DataOutputStream getBinaryOutputStream() {
        return binaryOutputStream;
    }

    public BufferedReader getBinaryInputStream() {
        return binaryInputStream;
    }

    public PrintWriter getTextOutputStream() {
        return textOutputStream;
    }

    public FileWriter getFileWriter() {
        return fileWriter;
    }
}

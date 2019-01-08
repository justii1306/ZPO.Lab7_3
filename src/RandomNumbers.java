import java.io.*;
import java.text.NumberFormat;
import java.util.Random;

public class RandomNumbers {
    private final int count;
    private final int mean;
    private final int standardDeviation;
    DataOutputStream binaryOutputStream;
    BufferedReader binaryInputStream;
    FileWriter fileWriter;
    PrintWriter textOutputStream;

    public RandomNumbers(int count, int mean, int standardDeviation)throws FileNotFoundException, IOException {
        assert (standardDeviation >= 0);
        this.count = count;
        this.mean = mean;
        this.standardDeviation = standardDeviation;
        this.binaryOutputStream = new DataOutputStream(new FileOutputStream("binout.dat"));
        this.binaryInputStream = new BufferedReader(new InputStreamReader(new FileInputStream("binout.dat")));
        this.fileWriter = new FileWriter("textout.txt");
        this.textOutputStream = new PrintWriter(getFileWriter());
        for (int i = 0; i < getCount(); i++) {
            double randomNumber = generateRandomNumber();
            String randomNumberString = format(randomNumber);
            writeLineToBinaryFile(randomNumberString);
        }
        for (int i = 0; i < getCount(); i++) {
            String line = readLineFromBinaryFile();
            writeLineToTextFile(i, line);
        }
    }

    public void closeDataStreams() throws IOException{
        getBinaryOutputStream().close();
        getBinaryInputStream().close();
        getFileWriter().close();
        getTextOutputStream().close();
    }

    public void writeLineToBinaryFile(String number) throws IOException{
        getBinaryOutputStream().writeChars(number);
        getBinaryOutputStream().writeChar('\n');
    }

    public String readLineFromBinaryFile() throws IOException {
        String result;
        result = getBinaryInputStream().readLine();
        return result;
    }

    public void writeLineToTextFile(int line, String number) throws IOException{
        number = number.replaceAll("\u0000", "");
        getTextOutputStream().print(line + 1);
        getTextOutputStream().print("\u0009");
        getTextOutputStream().println(number);
    }

    public double generateRandomNumber(){
        Random r = new Random();
        Double result = r.nextGaussian()*getStandardDeviation()+getMean();
        return result;
    }

    public String format(double number){
        String result = NumberFormat.getInstance().format(number);
        return result;
    }

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
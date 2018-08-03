package FuseJet;
import java.io.*;

public class POSFormat {
    public static void main (String[] args){
        try {
            //Ready to modify this part so this can fit
            BufferedReader br = new BufferedReader(new FileReader("./test.out.txt"));

            File file = new File("test.pos.txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);

            String CurrentLine;

            long counter = 1;

            while ((CurrentLine = br.readLine()) != null){
                String[] lineInString = CurrentLine.split(" ");
                for (int i = 0; i < lineInString.length; i++){
                    String[] wordProperty = lineInString[i].split("_");
                    long endCounter = counter + wordProperty[0].length() - 1;
                    fw.write(wordProperty[0] + "  |||  " + "S: " + counter + " " + "E: " + endCounter + "  " + "|||  " + wordProperty[1] + "\n");
                    counter = endCounter;
                }
            }

            fw.close();
        } catch (IOException  e){
            e.printStackTrace();
        }
    }
}

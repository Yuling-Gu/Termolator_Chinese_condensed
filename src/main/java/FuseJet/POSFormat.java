package FuseJet;
import java.io.*;

public class POSFormat {
    public static void main (String[] args){
        try {
            //Ready to modify this part so this can fit
            BufferedReader br = new BufferedReader(new FileReader("./test.out.txt"));

            File posfile = new File("test.pos.txt");
            File tchunkfile = new File("test.tchunk.txt");
            posfile.createNewFile();
            tchunkfile.createNewFile();
            FileWriter posfileWriter = new FileWriter(posfile);
            FileWriter tchunkfileWriter = new FileWriter(tchunkfile);

            String CurrentLine;

            long counter = 1;

            while ((CurrentLine = br.readLine()) != null){
                String[] lineInString = CurrentLine.split(" ");
                for (int i = 0; i < lineInString.length; i++){
                    String[] wordProperty = lineInString[i].split("_");
                    long endCounter = counter + wordProperty[0].length() - 1;
                    posfileWriter.write(wordProperty[0] + "  |||  " + "S: " + counter + " " + "E: " + endCounter + "  " + "|||  " + wordProperty[1] + "\n");
                    //Missing BIO Tag here
                    tchunkfileWriter.write(wordProperty[0] + "  " + wordProperty[0] + "  " + wordProperty[1] + "\n");
                    counter = endCounter;
                }
            }

            posfileWriter.close();
        } catch (IOException  e){
            e.printStackTrace();
        }
    }
}

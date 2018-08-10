package FuseJet;
import java.io.*;

/*
Author: Leizhen Shi
Update Version Date: 08/10/18
*/

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

            int nounCounter = 0;

            while ((CurrentLine = br.readLine()) != null){
                String[] lineInString = CurrentLine.split(" ");
                for (int i = 0; i < lineInString.length; i++){
                    //Generating POS Tag files
                    String[] wordProperty = lineInString[i].split("_");
                    long endCounter = counter + wordProperty[0].length() - 1;
                    posfileWriter.write(wordProperty[0] + "  |||  " + "S: " + counter + " " + "E: " + endCounter + "  " + "|||  " + wordProperty[1] + "\n");
                    //Generating tchunk tag files
                    String nounProperty = wordProperty[1].replaceAll("\\s+","");
                    if (nounProperty == "NN" || nounProperty == "NP"){ //The program won't enter the if statement here. Something is wrong
                        if (nounCounter == 0) {
                            tchunkfileWriter.write(wordProperty[0] + "  " + wordProperty[0] + "  " + wordProperty[1] + "  " + "B" + "\n");
                            nounCounter ++;
                        }
                        else {
                            tchunkfileWriter.write(wordProperty[0] + "  " + wordProperty[0] + "  " + wordProperty[1] + "  " + "I" + "\n");
                        }
                    }
                    else {
                        nounCounter = 0;
                        tchunkfileWriter.write(wordProperty[0] + "  " + wordProperty[0] + "  " + wordProperty[1] + "  " + "O" + "\n");

                    }
                    counter = endCounter;
                }
            }

            posfileWriter.close();
        } catch (IOException  e){
            e.printStackTrace();
        }
    }
}

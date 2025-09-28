import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class SudukoFileWriter {
    
    FileWriter sudukoWriter;
    File solvedSudukoFile;
    String fileName;

    SudukoFileWriter(String fileName){
        this.fileName = fileName;
    }

    /** 
     * this method creates the file with the same name and writess the solved board to it
     * @param outputSudukoBoard
     */
    public void sudukoWriter(int [][] outputSudukoBoard){
        
        while(true){
            try{
                solvedSudukoFile = new File(fileName + ".sol");
                if(solvedSudukoFile.createNewFile()){
                    System.out.println("Solution File Created");
                    break;
                }
                else{
                    solvedSudukoFile.delete();
                    continue;
                }
            } catch(IOException e){
                System.out.println("IO exception occured");
                e.printStackTrace();
            }
        }

        try (PrintWriter writeSukudo = new PrintWriter(new FileWriter(solvedSudukoFile))){
            String border = "---------------------------------";

            if(outputSudukoBoard.length == 4){
                border = "-------------";
            }
            writeSukudo.println(border);
            for(int i = 0; i < outputSudukoBoard.length; i++){
                for(int j = 0; j < outputSudukoBoard[i].length; j++){
                    writeSukudo.print(outputSudukoBoard[i][j]);
                    if(j < outputSudukoBoard[i].length - 1){
                        writeSukudo.print(" | ");
                    }
                }
                writeSukudo.println("\n" + border);
            }
        } catch (IOException e){
            System.out.println("IO exception occured");
            e.printStackTrace();
        }
    }
}

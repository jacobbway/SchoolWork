import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * I totally forgot i cant inherit from multiple classes in java
 * otherwise i was going to make this prettier
 */
public class Suduko_Main{

    public static void main(String args[]){
        String fileName = new String();
        Boolean firstWhileLoopBoolean = true;
        Scanner inKYBD = new Scanner(System.in);
        SudukoSolver suduko1;

        while(firstWhileLoopBoolean){
            try {
                
                System.out.println("Type in name of txt file. Don't include extension!");
                fileName = inKYBD.nextLine();

                FileInputStream inputFileStream = new FileInputStream(fileName + ".txt");
                FileInputStream inputFileStream2 = new FileInputStream(fileName + ".txt");
                System.out.println("File found!");
                
                Scanner inputScanner = new Scanner(inputFileStream);
                Scanner fileVerifyScanner = new Scanner(inputFileStream2);
                SudukoFileVerification fileVerify = new SudukoFileVerification();
                if(!fileVerify.boardFormatVerify(fileVerifyScanner)){
                    continue;
                }

                String boardSize = inputScanner.nextLine();
                suduko1 = new SudukoSolver(boardSize);
                suduko1.fillBoard(inputScanner);
                suduko1.sudukoSolverUtil();
                suduko1.printSudukoBoard();

            } catch (FileNotFoundException fnfe) {
                System.out.println("File Not Found! Try Again!");
                continue;
            }

            SudukoFileWriter outputSudukoFile = new SudukoFileWriter(fileName);
            outputSudukoFile.sudukoWriter(suduko1.getSudukoBoard());
            
            inKYBD.close();
            firstWhileLoopBoolean = false;
        }
    }
}

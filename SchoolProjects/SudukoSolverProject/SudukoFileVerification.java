import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SudukoFileVerification {
    String fourRegex = "4x4";
    String nineRegez = "9x9";
    String cellRegex = "\\d,\\d,\\d";
    Pattern fourSizePattern = Pattern.compile(fourRegex);
    Pattern nineSizePattern = Pattern.compile(nineRegez);
    Pattern cellPositionPattern = Pattern.compile(cellRegex);

    SudukoFileVerification(){}

    /** 
     * this function returns a bool if the contents of the file meet the
     * format requirements. uses regexs and this matching pattern thing i found
     * @param fileInput passing a scanner around to save resources
     * @return boolean
     */
    public boolean boardFormatVerify(Scanner fileInput){
        boolean isValid = true;
        int bSize = 0; //4 is 4x4 9 is 9x9
        String boardSizeString = fileInput.nextLine();
        String cellFormat = "";
        
        Matcher fourMatcher = fourSizePattern.matcher(boardSizeString);
        Matcher nineMatcher = nineSizePattern.matcher(boardSizeString);

        if(fourMatcher.matches()){
            System.out.println("Board size is 4x4");
            bSize = 4;
        }
        else if(nineMatcher.matches()){
            System.out.println("Board size is 9x9");
            bSize = 9;
        }
        else{
            System.out.println("Board Size does not match correct format enter a new input file!");
            isValid = false;
            return isValid;
        }
        while(fileInput.hasNextLine()){
            fileInput.nextLine();
            if(fileInput.hasNextLine()){
                cellFormat = fileInput.nextLine();
                Matcher cellMatcher = cellPositionPattern.matcher(cellFormat);
                if(!cellMatcher.matches()){
                    isValid = false;
                    System.out.println("Format for cell is incorrect. Please use a different file!");
                    break;
                }
                if(!checkNum(cellFormat, bSize)){
                    isValid = false;
                    System.out.println("Coordinates invalid for current board size. Please use a different file!");
                    break;
                }
                 
            }
        }
        return isValid;
    }

    /** 
     * this method exists to make sure the placement range is correct as well as
     * the number to be placed
     * 
     * @param stringToCheck
     * @param boardSize
     * @return boolean
     */
    private boolean checkNum(String stringToCheck, int boardSize){
        boolean validNum = true;
        int num1 = Character.getNumericValue(stringToCheck.charAt(0));
        int num2 = Character.getNumericValue(stringToCheck.charAt(2));
        int num3 = Character.getNumericValue(stringToCheck.charAt(4));

        if((num1 < 0 && num1 > boardSize) || (num2 < 0 && num2 > boardSize) || (num3 < 0 && num3 > boardSize)) {
            validNum = false;
        }
        
        return validNum;
    }

}

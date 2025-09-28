import java.util.Scanner;

class SudukoSolver{
    int[][] sudukoBoard;
    String boardSize;
    int numOfCells, gridSize;
    int[] totalEachNum;
    int totalPlaced = 0;
    

    SudukoSolver(String boardSize){
        this.boardSize = boardSize;
        createBoard();
    }

    /** 
     * @return int[][]
     */
    public int[][] getSudukoBoard(){
        return sudukoBoard;
    }

    private void createBoard(){

        if(boardSize.equalsIgnoreCase("4x4")){
            sudukoBoard = new int[4][4];   
            numOfCells = 16;   
            totalEachNum = new int[4];
            gridSize = 4;
        }
        else if(boardSize.equalsIgnoreCase("9x9")){
            sudukoBoard = new int[9][9];
            numOfCells = 81;
            totalEachNum = new int[9];
            gridSize = 9;
        }
        else{
            System.out.println("Game board size not recognized.");
        }
    }

    /** 
     * this method fills my board with 0's so i can use that as validation
     * method to place future numbers. then it reads in the input file
     * and places those numbers down
     * @param fileInputScanner
     */
    public void fillBoard(Scanner fileInputScanner){
        String currentLine = "";
        int row, col, numToPlace;


        //fills with 0s
        for(int i = 0; i < sudukoBoard.length; i++){
            for(int j = 0; j < sudukoBoard[i].length; j++){
                sudukoBoard[i][j] = 0;
            }
        }
        //fills with file info
        while(fileInputScanner.hasNextLine()){
            fileInputScanner.nextLine();
            if(fileInputScanner.hasNextLine()){
                currentLine = fileInputScanner.nextLine();
                row = Character.getNumericValue(currentLine.charAt(0)) - 1;
                col = Character.getNumericValue(currentLine.charAt(2)) - 1;
                numToPlace = Character.getNumericValue(currentLine.charAt(4));
    
                sudukoBoard[row][col] = numToPlace;
                totalEachNum[numToPlace - 1] += 1;          

            }
        }
    }
    
    /** 
     * this is my method to check if the placement is valid for rows and columns
     * @param numToPlace
     * @param desiredRow
     * @param desiredColumn
     * @return boolean
     */
    private boolean isValidPlacement(int numToPlace, int desiredRow, int desiredColumn){
        boolean isValid = true;
        for(int i = 0; i < gridSize; i++){
            //should be checking the row for number to be placed
            if(sudukoBoard[desiredRow][i] == numToPlace){
                isValid = false;
                break;
            }
        }
        if(isValid){
            for(int i = 0; i < gridSize; i++){
                //should be checking the column for the number to be placed and that its empty which is 0
                if(numToPlace == sudukoBoard[i][desiredColumn]){
                    isValid = false;
                    break;
                }
            }
        }
        //System.out.println("Row/Column: " + isValid);
        return isValid;
    }
    
    /** 
     * checking if placement is valid in subgrids
     * AA AB AC     or  AA AB
     * BA BB BC         BA BB
     * CA CB CC
     * @param numToPlace
     * @param desiredRow
     * @param desiredColumn
     * @return boolean
     */ 
    private boolean isValidSubGrid(int numToPlace, int desiredRow, int desiredColumn){
        boolean isValid = true;
        String whichGrid = "";
        if(numOfCells == 16){

        }
        else if(numOfCells == 81){
          //  System.out.println("inside of == 81");
            if(desiredRow <= 2){
                whichGrid += "A";
            }
            else if(desiredRow > 2 && desiredRow <= 5){
                whichGrid += "B";
            }
            else if(desiredRow > 5 && desiredRow <= 8){
                whichGrid += "C";
            }

            if(desiredColumn <= 2){
                whichGrid += "A";
            }
            else if(desiredColumn > 2 && desiredColumn <= 5){
                whichGrid += "B";
            }
            else if(desiredColumn > 5 && desiredColumn <= 8){
                whichGrid += "C";
            }
            switch (whichGrid) {
                case "AA":

                    for(int i = 0; i < 3; i++){
                        for(int j = 0; j < 3; j++){
                            if(numToPlace == sudukoBoard[i][j]){
                                isValid = false;
                            }
                        }
                    }
                    break;
                case "AB":
                    for(int i = 0; i < 3; i++){
                        for(int j = 3; j < 6; j++){
                            if(numToPlace == sudukoBoard[i][j]){
                                isValid = false;
                            }
                        }
                    }
                    break;
                case "AC":;
                    for(int i = 0; i < 3; i++){
                        for(int j = 6; j < 9; j++){
                            if(numToPlace == sudukoBoard[i][j]){
                                isValid = false;
                            }
                        }
                    }
                    break;
                case "BA":
                    for(int i = 3; i < 6; i++){
                        for(int j = 0; j < 3; j++){
                            if(numToPlace == sudukoBoard[i][j]){
                                isValid = false;
                            }
                        }
                    }
                    break;
                case "BB":
                    for(int i = 3; i < 6; i++){
                        for(int j = 3; j < 6; j++){
                            if(numToPlace == sudukoBoard[i][j]){
                                isValid = false;
                            }
                        }
                    }
                    break;
                case "BC":
                    for(int i = 3; i < 6; i++){
                        for(int j = 6; j < 9; j++){
                            if(numToPlace == sudukoBoard[i][j]){
                                isValid = false;
                            }
                        }
                    }
                    break;
                case "CA":;
                    for(int i = 6; i < 9; i++){
                        for(int j = 0; j < 3; j++){
                            if(numToPlace == sudukoBoard[i][j]){
                                isValid = false;
                            }
                        }
                    }
                    break;
                case "CB":
                    for(int i = 6; i < 9; i++){
                        for(int j = 3; j < 6; j++){
                            if(numToPlace == sudukoBoard[i][j]){
                                isValid = false;
                            }
                        }
                    }
                    break;
                case "CC":
                    for(int i = 6; i < 9; i++){
                        for(int j = 6; j < 9; j++){
                            if(numToPlace == sudukoBoard[i][j]){
                                isValid = false;
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Something went wrong in subgrid check for 9x9...");
            }
        }
        else{
            System.out.println("Size not accounted for.");
        }
        //System.out.println("Subgrid: " + isValid);
        return isValid;
    }

    /**
     * prints my board
     */
    public void printSudukoBoard(){
        String border = "---------------------------------";
        
        if(sudukoBoard.length == 4){
            border = "-------------";
        }
        
        System.out.println(border);
        for(int i = 0; i < sudukoBoard.length; i++){
            for(int j = 0; j < sudukoBoard[i].length; j++){
                System.out.print(sudukoBoard[i][j]);
                if(j < sudukoBoard[i].length - 1){
                    System.out.print(" | ");
                }
            }
            System.out.println("\n" + border);
        }
    }

    /** 
     * solves the board using back tracking, took me ages to make this work
     * at least 6 days of staring went into this. then i realized i could
     * just try to put every number in every space. And then backtrack
     * @return boolean
     */
    public boolean sudukoSolverUtil(){
        for(int row = 0; row < sudukoBoard.length; row++){
            for(int col = 0; col < sudukoBoard[row].length; col++){
                if(sudukoBoard[row][col] == 0){
                    for(int k = 1; k <= 9; k++){
                        if(isValidSubGrid(k, row, col) && isValidPlacement(k, row, col)){
                            sudukoBoard[row][col] = k;

                            if(sudukoSolverUtil()){
                                return true;
                            }

                            sudukoBoard[row][col] = 0;
                        }
                    }
                return false;   
                }
            }
        }
        return true;
    }
}

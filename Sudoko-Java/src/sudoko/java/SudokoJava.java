/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoko.java;

import static java.lang.Math.sqrt;
import java.util.Random;

/**
 *
 * @author jaswanth
 */
public class SudokoJava {
    
    static int gridSize = 4;
    static int subGridSize = (int) sqrt(gridSize);
    static int sudokuGrid[][] = new int[][]
                                {{0,0,0,0},
                                 {0,0,0,0},
                                 {0,0,0,0},
                                 {0,0,0,0}}; //Empty grid, where predefined 
                                             //values can be set.
    static int grid[][] = new int[gridSize][gridSize]; //Grid to be manipulated by solver agent.
    
    static final int firstBox = 0; //Index of first cell in the grid in a row.
    static final int lastBox = 3;  //Index of last cell in the grid in a row.
    static final int minNumber = 1;//Min number to fill the empty cell.
    static final int maxNumber = 4;//Max number to fill the empty cell.
    static final int minNoPredefinedBoxes = 4;//Number of predefined values in sudoko.
    static final int maxNoPredefinedBoxes = 7;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        sudokoGenerator(); //For generating the grid.
 
        for(int i = firstBox; i < gridSize; i++){
            for(int j = firstBox; j < gridSize; j++){
                grid[i][j] = sudokuGrid[i][j];
            }
        }
        
        displayGrid((sudokoSolverAgent(firstBox, firstBox, grid))); //For solving the grid.
    }
    
    public static void sudokoGenerator(){
        // Method for generating sudoku puzzels.
        
        int predefinedRow = 0, predefinedCol = 0, predefinedNum = 0;
        int noPredefinedBoxes;
        
        Random random = new Random();
        
        noPredefinedBoxes = random.nextInt(maxNoPredefinedBoxes - 
                                minNoPredefinedBoxes + 1) + minNoPredefinedBoxes;
        
        for(int i = 1; i <= noPredefinedBoxes; i++ ){
            predefinedRow = random.nextInt(lastBox - firstBox + 1);
            predefinedCol = random.nextInt(lastBox - firstBox + 1);
            predefinedNum = random.nextInt(maxNumber - minNumber + 1) 
                            + minNumber;
            if(sudokuGrid[predefinedRow][predefinedCol] == 0){
                sudokuGrid[predefinedRow][predefinedCol] = predefinedNum;
                if(validMove(predefinedRow,predefinedCol,sudokuGrid)) continue;
                else {
                    sudokuGrid[predefinedRow][predefinedCol] = 0;
                    i--;
                }
            } else i--;
        }
        
        displayGrid(sudokuGrid);
    }
    
    public static int[][] sudokoSolverAgent(int x, int y, int[][] grid){
        // Agent that solves the entire sudoko.
        
        int validX = lastBox, validY = lastBox, flag = 0;
        
        for(int i = lastBox; i >= firstBox; i--){
            for(int j = lastBox; j >= firstBox; j--){
                if(sudokuGrid[i][j] == 0){
                    validX = i; 
                    validY = j; 
                    flag = 1;
                    break;
                }
            }
            if(flag ==1) break;
        }
        
        while((!validMove(validX,validY,grid) || grid[validX][validY] == 0)){
            if(sudokuGrid[x][y]!=0){
                int newX,newY;
                if(y == lastBox){
                    newX = x+1;
                    newY = 0;
                } else { 
                    newX = x; 
                    newY = y+1;
                }
                sudokoSolverAgent(newX,newY,grid);
            } else {
                if(grid[x][y] < maxNumber) {
                    grid[x][y]++; //Incrementing the value in each cell by one.
                    if(validMove(x,y,grid)){
                        int newX,newY;
                        if(y == lastBox){
                            newX = x+1;
                            newY = 0;
                        } else {
                            newX = x; 
                            newY = y+1;
                        }
                        sudokoSolverAgent(newX,newY,grid);
                    }
                } else {
                    //If the incremented value is not valid, then set the value
                    //to zero and move back to the previous cell.
                    grid[x][y]=0; break;
                }
            }
        }
        return grid;
    }
    
    public static boolean validMove(int x, int y, int[][] grid) {
        // Method to check for valid moves.
        
        String cellValues = "";
        for(int i = 0; i < gridSize; i++){
            cellValues += Integer.toString(grid[i][y]); //Check vertical values.
            cellValues += Integer.toString(grid[x][i]); //Check horizontal values.
            cellValues += Integer.toString(grid[(x/subGridSize) * subGridSize +
                        i/subGridSize][(y/subGridSize) * subGridSize + i%subGridSize]); 
                                                    //Check values in the subgrid.
        }
        int count=0, flag=0;
        while ((flag = cellValues.indexOf(Integer.toString(grid[x][y]), flag))!=-1){
            //Counting the number of occurences of the respective grid value.
            flag++;
            count++;
        }
        
        return (count == 3);
    }
    
    public static void displayGrid(int[][] grid) { 
        // Method for displaying the grid.
        
        System.out.println();
        for(int i = 0; i < gridSize; i++){
            for(int j = 0;j < gridSize; j++){
                System.out.print(grid[i][j] + "  ");
            }
            System.out.println();
        }
    }
    
}

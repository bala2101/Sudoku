/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoko.java;

import java.util.Random;

/**
 *
 * @author jaswanth
 */
public class SudokoJava {

    static int sudokuGrid[][] = new int[][]
                                {{0,0,0,0},
                                 {0,0,0,0},
                                 {0,0,0,0},
                                 {0,0,0,0}}; //Empty grid, where predefined 
                                             //values can be set.
    static int grid[][] = new int[4][4]; //Grid to be manipulated by solver agent.
    
    static final int firstBox = 0; //Index of first cell in the grid in a row.
    static final int lastBox = 3;  //Index of last cell in the grid in a row.
    static final int minNumber = 1;//Min number to fill the empty cell.
    static final int maxNumber = 4;//Max number to fill the empty cell.
    static final int noPredefinedBoxes = 5;//Number of predefined values in sudoko.
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //sudokoGenerator(); //For generating the grid.
 
        for(int i =0; i<4;i++){
            for(int j=0;j<4;j++){
                grid[i][j] = sudokuGrid[i][j];
            }
        }
        displayGrid((sudokoSolverAgent(0, 0, grid))); //For solving the grid.
    }
    
    public static void sudokoGenerator(){
        // Method for generating sudoku puzzels.
        
        int predefinedRow = 0, predefinedCol = 0, predefinedNum = 0;
        Random random = new Random();
        for(int i=1; i<=noPredefinedBoxes; i++ ){
            predefinedRow = random.nextInt(lastBox - firstBox + 1);
            predefinedCol = random.nextInt(lastBox - firstBox + 1);
            predefinedNum = random.nextInt(maxNumber - minNumber + 1) 
                            + minNumber;
            if(sudokuGrid[predefinedRow][predefinedCol] == 0){
                sudokuGrid[predefinedRow][predefinedCol] = predefinedNum;
                if(validMove(predefinedRow,predefinedCol,sudokuGrid)){
                    continue;
                }else{sudokuGrid[predefinedRow][predefinedCol] = 0;i--;}
            } else {i--;}
        }
        
        displayGrid(sudokuGrid);
    }
    
    public static int[][] sudokoSolverAgent(int x, int y, int[][] grid){
        // Agent that solves the entire sudoko.
        
        int validX = 3, validY = 3, flag = 0;
        for(int i=3; i>=0; i--){
            for(int j=3; j>=0; j--){
                if(sudokuGrid[i][j] == 0){
                    validX = i; validY = j; flag = 1;
                    break;
                }
            }
            if(flag ==1){break;}
        }
        while((!validMove(validX,validY,grid) || grid[validX][validY] == 0)){
            if(sudokuGrid[x][y]!=0){
                int newX,newY;
                if(y==3){newX = x+1;newY=0;}else{newX=x; newY=y+1;}
                sudokoSolverAgent(newX,newY,grid);
            }else{
                if(grid[x][y] < 4) {
                    grid[x][y]++; //Incrementing the value in each cell by one.
                    if(validMove(x,y,grid)){
                        int newX,newY;
                        if(y==3){newX = x+1;newY=0;}else{newX=x; newY=y+1;}
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
        for(int i=0;i<4;i++){
            cellValues += Integer.toString(grid[i][y]); //Check vertical values.
            cellValues += Integer.toString(grid[x][i]); //Check horizontal values.
            cellValues += Integer.toString(grid[(x/2)*2+i/2][(y/2)*2+i%2]); 
                                                    //Check values in the subgrid.
        }
        int count=0, flag=0;
        while ((flag=cellValues.indexOf(Integer.toString(grid[x][y]), flag))!=-1){
            //Counting the number of occurences of the respective grid value.
            flag++;
            count++;
        }
        
        return (count == 3);
    }
    
    public static void displayGrid(int[][] grid) { 
        // Method for displaying the grid.
        
        System.out.println();
        for(int i =0; i<4;i++){
            for(int j=0;j<4;j++){
                System.out.print(grid[i][j] + "  ");
            }
            System.out.println();
        }
    }
    
}

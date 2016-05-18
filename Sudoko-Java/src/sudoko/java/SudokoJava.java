/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoko.java;

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
    static int grid[][] = new int[4][4]; //Grid to be manipulated.
    
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

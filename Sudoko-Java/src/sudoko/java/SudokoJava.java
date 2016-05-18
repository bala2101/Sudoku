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
        
        for(int i =0; i<4;i++){
            for(int j=0;j<4;j++){
                System.out.print(grid[i][j] + "  ");
            }
            System.out.println();
        }
    }
    
}

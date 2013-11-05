import java.util.*;

public class SudokuPuzzle {
    private int[][] puzzle;
    private int[][] original;
    
    //constructor
    public SudokuPuzzle(int[][] newPuzzle) {
        puzzle = newPuzzle;
        original = (int[][])puzzle.clone();
    }
    
    //returns true if puzzle is complete
    public boolean isSolved() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (puzzle[r][c] == 0) {
                    return false;
                }           
            }
        }
        return true;
    }
    
    //returns true if number is present in other two rows of other
    //two horizontal sub-squares
    public boolean isSolidRow(int row, int number) {
        int rowA;
        int rowB;
        
        if (row % 3 == 0) {
            rowA = row + 1;
            rowB = row + 2;
        } else if (row % 3 == 1) {
            rowA = row - 1;
            rowB = row + 1;
        } else {
            rowA = row - 2;
            rowB = row - 1;
        }
        return getRowContents(rowA).contains(number) && getRowContents(rowB).contains(number);   
    }
    
    //returns true if number is present in other two columns of
    //other two vertical sub-squares
    public boolean isSolidCol(int col, int number) {
        int colA;
        int colB;

        if (col % 3 == 0) {
            colA = col + 1;
            colB = col + 2;
        } else if (col % 3 == 1) {
            colA = col - 1;
            colB = col + 1;
        } else {
            colA = col - 2;
            colB = col - 1;
        }
        return getColContents(colA).contains(number) && getColContents(colB).contains(number); 
    }
    
    //returns list of numbers NOT present in given list   
    public ArrayList<Integer> getPossibles(ArrayList<Integer> contents) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 1; i <= 9; i++) {
            result.add(i);
        }
        for (Integer a : contents) {
            result.remove(a);
        }
        return result;
    }
    
    //returns list of filled in numbers in row
    public ArrayList<Integer> getRowContents(int row) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        for (int i = 0; i < 9; i++) {
            if (puzzle[row][i] != 0) {
                result.add(puzzle[row][i]);
            }
        }
        return result;
    }

    //returns list of filled in numbers in column    
    public ArrayList<Integer> getColContents(int col) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        for (int i = 0; i < 9; i++) {
            if (puzzle[i][col] != 0) {
                result.add(puzzle[i][col]);
            }
        }
        return result;
    }
    
    //returns list of filled in numbers in sub-square
    public ArrayList<Integer> getSubContents(int row, int col) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (puzzle[r][c] != 0) {
                    result.add(puzzle[r][c]); 
                }
            }
        }
        return result;
    }
        
    public int[][] getPuzzle() {
        return puzzle;
    }
    
    public int[][] getOriginal() {
        return original;
    }
    
    public String origString() {
        String result = "";
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                result += original[r][c];
            }
            result += "\n";
        }
        return result;
    }
    
    public String toString() {
        String result = "";
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                result += puzzle[r][c];
            }
            result += "\n";
        }
        return result;
    }
}
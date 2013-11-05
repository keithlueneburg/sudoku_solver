import java.util.*;
import java.io.*;

public class SudokuSolver {
    public static void main(String[] args) {
        SudokuPuzzle puzzle1;
        puzzle1 = new SudokuPuzzle(loadTxtPuzzle("medium.txt"));

        System.out.println(puzzle1.origString());
        
        solve(puzzle1);
        System.out.println(puzzle1);
    }
    
    
    public static void solve(SudokuPuzzle puzzle) {
        long stTime, endTime;
        int counter = 0;
        
        stTime = System.currentTimeMillis();
        
        int[][] p = puzzle.getPuzzle();
        while (!puzzle.isSolved()) {
            counter++;
            
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    if (p[r][c] == 0) {
                        ArrayList<Integer> possibles;
                        ArrayList<Integer> rowPoss = puzzle.getPossibles(puzzle.getRowContents(r));
                        ArrayList<Integer> colPoss = puzzle.getPossibles(puzzle.getColContents(c));
                        ArrayList<Integer> subPoss = puzzle.getPossibles(puzzle.getSubContents(r, c));                    
                         
                        possibles = rowPoss;
                        possibles.retainAll(colPoss);
                        possibles.retainAll(subPoss);
                        //if only 1 poss, do it, else // below for loop //
                        if (possibles.size() == 1) {
                            p[r][c] = possibles.get(0);
                        } else {
                            for (Integer num : possibles) {
                                if (puzzle.isSolidRow(r, num) &&
                                    puzzle.isSolidCol(c, num)) {
                                    p[r][c] = num;
                                }
                            }
                        }
                    }
                }
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("Solved the puzzle in " + (endTime -stTime) + " ms");
        System.out.println(counter + " times through puzzle");
    }
    
    public static int[][] loadTxtPuzzle(String filename) {
        int[][] result = new int[9][9];
        File f;
        Scanner input = null;
        
        try {
            f = new File(filename);
            input = new Scanner(f);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
        
        for (int r = 0; r < 9; r++) {
            String line = input.nextLine();
            for (int c = 0; c < 9; c++) {
                result[r][c] = Character.digit(line.charAt(c), 10);
            }
        }
        return result;
    }
}

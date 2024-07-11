import java.util.*;
import java.io.*;

class Sudoku {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int[][] board = new int[9][9];

        for (int y = 0; y < 9; y++) {
            String line = input.nextLine();
            for (int x = 0; x < 9; x++) {
                if (line.charAt(x) != '.') {
                    board[y][x] = Integer.parseInt("" + line.charAt(x));
                }
            }
        }

        // SOLVE THE PUZZLE HERE
        
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                System.out.print(board[y][x]);
            }
            System.out.println();
        }
    }

    static boolean isValid(int[][] board) {
        // check rows
        for (int y = 0; y < 9; y++) {
            boolean[] seen = new boolean[9];
            for (int x = 0; x < 9; x++) {
                if (board[y][x] == 0) return false;
                if (seen[board[y][x] - 1]) return false;
                seen[board[y][x] - 1] = true;
            }
        }

        // check columns
        for (int x = 0; x < 9; x++) {
            boolean[] seen = new boolean[9];
            for (int y = 0; y < 9; y++) {
                if (board[y][x] == 0) return false;
                if (seen[board[y][x] - 1]) return false;
                seen[board[y][x] - 1] = true;
            }
        }

        // check blocks
        boolean[][][] seen = new boolean[3][3][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
               if (board[y][x] == 0) return false;
               if (seen[y / 3][x / 3][board[y][x] - 1]) return false;
               seen[y / 3][x / 3][board[y][x] - 1] = true;
            }
        }

        return true;
    }
}

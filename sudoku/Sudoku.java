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

        if (solve(board, 0, 0)) { 
            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    System.out.print(board[y][x]);
                }
                System.out.println();
            }
        } else {
            System.out.println("No Solution");
        }
    }

    // Generating guesses "in-place", i.e., the board parameter will be modified
    // by this method, return true if it found a valid solution
    // Otherwise, returns false and *returns board to its original state*
    static boolean solve(int[][] board, int y, int x) {
        if (y == 9) return true;
        if (x == 9) return solve(board, y + 1, 0);
        if (board[y][x] != 0) return solve(board, y, x + 1);

        for (int i = 1; i <= 9; i++) {
            board[y][x] = i;
            if (!isValid(board)) continue;
            if (solve(board, y, x + 1)) return true;
        }
        board[y][x] = 0;
        return false;
    }

    static boolean isValid(int[][] board) {
        // check rows
        for (int y = 0; y < 9; y++) {
            boolean[] seen = new boolean[9];
            for (int x = 0; x < 9; x++) {
                if (board[y][x] == 0) continue;
                if (seen[board[y][x] - 1]) return false;
                seen[board[y][x] - 1] = true;
            }
        }

        // check columns
        for (int x = 0; x < 9; x++) {
            boolean[] seen = new boolean[9];
            for (int y = 0; y < 9; y++) {
                if (board[y][x] == 0) continue;
                if (seen[board[y][x] - 1]) return false;
                seen[board[y][x] - 1] = true;
            }
        }

        // check blocks
        boolean[][][] seen = new boolean[3][3][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
               if (board[y][x] == 0) continue;
               if (seen[y / 3][x / 3][board[y][x] - 1]) return false;
               seen[y / 3][x / 3][board[y][x] - 1] = true;
            }
        }

        return true;
    }
}

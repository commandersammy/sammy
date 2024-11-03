// Name: Samrat KC
// Date: 2024-11-02]
// Description: This program generates and solves Sudoku puzzles. It includes methods to create a fully populated Sudoku grid,
// convert it to a playable puzzle by removing cells, and offers a menu for the user to view the solution, generate a new puzzle, or exit the program.

import java.util.Random;
import java.util.Scanner;

public class SudokuGenerator {

    // Constants
    private static final int SIZE = 9;         // Sudoku grid size
    private static final int EMPTY = 0;        // Value to indicate empty cells in the puzzle
    private static final int CELLS_TO_REMOVE = 41; // Number of cells to remove to create the puzzle

    public static void main(String[] args) {
        // Initial greeting message for the user
        System.out.println("Welcome to the Sudoku Puzzle Generator!");

        // Generate initial puzzle and display options
        // Class variables to store the solution and puzzle grids
        int[][] solutionGrid = createFullSudokuGrid();
        int[][] puzzleGrid = createSudokuPuzzle(solutionGrid);

        // Scanner for menu input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printSudoku(puzzleGrid);
            System.out.println("\nOptions:");
            System.out.println("1. Show solution");
            System.out.println("2. Generate new puzzle");
            System.out.println("3. Quit");

            System.out.print("Enter your choice (1-3): ");
            String choice = scanner.nextLine();

            // Switch case for user menu options
            switch (choice) {
                case "1":
                    System.out.println("\nSolution:");
                    printSudoku(solutionGrid);
                    break;
                case "2":
                    solutionGrid = createFullSudokuGrid();
                    puzzleGrid = createSudokuPuzzle(solutionGrid);
                    System.out.println("\nNew puzzle generated!");
                    break;
                case "3":
                    System.out.println("Thank you for using the Sudoku Puzzle Generator! Goodbye.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }
    }

    /**
     * Generates a fully populated Sudoku grid that follows the rules of Sudoku.
     * @return A 2D array representing a complete Sudoku grid.
     */
    public static int[][] createFullSudokuGrid() {
        int[][] grid = new int[SIZE][SIZE];
        fillGrid(grid);
        return grid;
    }

    /**
     * Recursively fills the grid to create a valid Sudoku solution.
     * @param grid The 2D array representing the Sudoku grid to fill.
     * @return true if the grid is successfully filled, false otherwise.
     */
    private static boolean fillGrid(int[][] grid) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] == EMPTY) {
                    Random random = new Random();
                    int[] numbers = random.ints(1, SIZE + 1).distinct().limit(SIZE).toArray();

                    for (int num : numbers) {
                        if (isValid(grid, num, row, col)) {
                            grid[row][col] = num;
                            if (fillGrid(grid)) {
                                return true;
                            }
                            grid[row][col] = EMPTY;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if placing a number at a given position is valid according to Sudoku rules.
     * @param grid The Sudoku grid.
     * @param num The number to place.
     * @param row The row index.
     * @param col The column index.
     * @return true if the number can be placed, false otherwise.
     */
    private static boolean isValid(int[][] grid, int num, int row, int col) {
        // Check if the number is in the row or column
        for (int i = 0; i < SIZE; i++) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }

        // Check if the number is in the 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (grid[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Creates a Sudoku puzzle by removing numbers from a complete grid.
     * @param solutionGrid A 2D array representing the complete Sudoku grid.
     * @return A 2D array with removed cells to represent the puzzle.
     */
    public static int[][] createSudokuPuzzle(int[][] solutionGrid) {
        int[][] puzzle = new int[SIZE][SIZE];

        // Copy solution grid to puzzle grid
        for (int row = 0; row < SIZE; row++) {
            System.arraycopy(solutionGrid[row], 0, puzzle[row], 0, SIZE);
        }

        // Randomly remove cells
        Random random = new Random();
        int cellsToRemove = CELLS_TO_REMOVE;
        while (cellsToRemove > 0) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);

            if (puzzle[row][col] != EMPTY) {
                puzzle[row][col] = EMPTY;
                cellsToRemove--;
            }
        }

        return puzzle;
    }

    /**
     * Prints the Sudoku grid in a user-friendly format.
     * @param grid A 2D array representing the Sudoku grid.
     */
    public static void printSudoku(int[][] grid) {
        System.out.println("\nSudoku Grid:");
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print((grid[row][col] != EMPTY ? grid[row][col] : " ") + " ");
            }
            System.out.println();
        }
    }
}

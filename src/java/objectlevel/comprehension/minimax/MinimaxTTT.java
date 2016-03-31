/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectlevel.comprehension.minimax;

import java.util.ArrayList;
import java.util.List;
import objectlevel.comprehension.Move;

/**
 *
 * @author jalheart
 */
public class MinimaxTTT {
    private String[][] cells;    
    public MinimaxTTT(String[][] cells,String player){
        this.cells  =cells;
    } 
   /** Get next best move for computer. Return int[2] of {row, col} */   
    public Move getMove() {
      int[] result = this.minimax(2, "O"); // depth, max turn
      return new Move(result[1], result[2]);   // row, col
   }
 
   /** Recursive minimax at level of depth for either maximizing or minimizing player.
       Return int[3] of {score, row, col}  */
    private int[] minimax(int depth, String player) {
      // Generate possible next moves in a List of int[2] of {row, col}.
       List<Move> nextMoves = generateMoves();
 
      // mySeed is maximizing; while oppSeed is minimizing
      int bestScore = (player.equals("O")) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
      int currentScore;
      int bestRow = -1;
      int bestCol = -1;
 
      if (nextMoves.isEmpty() || depth == 0) {
         // Gameover or depth reached, evaluate score
         bestScore = evaluate();
      } else {
         for (Move move : nextMoves) {
            // Try this move for the current "player"
            cells[move.fila][move.columna] = player;
            if (player == "O") {  // mySeed (computer) is maximizing player
                currentScore = minimax(depth - 1, "X")[0];
                if (currentScore > bestScore) {
                    bestScore = currentScore;
                    bestRow   = move.fila;
                    bestCol   = move.columna;
                }
            } else {  // oppSeed is minimizing player
                currentScore = minimax(depth - 1, "O")[0];
                if (currentScore < bestScore) {
                   bestScore = currentScore;
                   bestRow = move.fila;
                   bestCol = move.columna;
                }
            }
            // Undo move
            cells[move.fila][move.columna] = "";
         }
      }
      return new int[] {bestScore, bestRow, bestCol};
   }
 
   /** Find all valid next moves.
       Return List of moves in int[2] of {row, col} or empty list if gameover */
   private List<Move> generateMoves() {
      List<Move> nextMoves = new ArrayList<Move>(); // allocate List
 
      // If gameover, i.e., no next move
      if (hasWon("O") || hasWon("X")) {
         return nextMoves;   // return empty list
      } 
      // Search for empty cells and add to the List
      for (int row = 0; row < 3; ++row) {
         for (int col = 0; col < 3; ++col) {
            if (cells[row][col] == "") {
                nextMoves.add(new Move(row, col));
            }
         }
      }
      return nextMoves;
   }
 
   /** The heuristic evaluation function for the current board
       @Return +100, +10, +1 for EACH 3-, 2-, 1-in-a-line for computer.
               -100, -10, -1 for EACH 3-, 2-, 1-in-a-line for opponent.
               0 otherwise   */
   private int evaluate() {
      int score = 0;
      // Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
      score += evaluateLine(0, 0, 0, 1, 0, 2);  // row 0
      score += evaluateLine(1, 0, 1, 1, 1, 2);  // row 1
      score += evaluateLine(2, 0, 2, 1, 2, 2);  // row 2
      score += evaluateLine(0, 0, 1, 0, 2, 0);  // col 0
      score += evaluateLine(0, 1, 1, 1, 2, 1);  // col 1
      score += evaluateLine(0, 2, 1, 2, 2, 2);  // col 2
      score += evaluateLine(0, 0, 1, 1, 2, 2);  // diagonal
      score += evaluateLine(0, 2, 1, 1, 2, 0);  // alternate diagonal
      return score;
   } 
   /** The heuristic evaluation function for the given line of 3 cells
       @Return +100, +10, +1 for 3-, 2-, 1-in-a-line for computer.
               -100, -10, -1 for 3-, 2-, 1-in-a-line for opponent.
               0 otherwise */
   private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3) {
      int score = 0;
      // First cell
      if (cells[row1][col1] == "O") {
         score = 1;
      } else if (cells[row1][col1] == "X") {
         score = -1;
      }
      // Second cell
      if (cells[row2][col2] == "O") {
         if (score == 1) {   // cell1 is mySeed
            score = 10;
         } else if (score == -1) {  // cell1 is oppSeed
            return 0;
         } else {  // cell1 is empty
            score = 1;
         }
      } else if (cells[row2][col2] == "X") {
         if (score == -1) { // cell1 is oppSeed
            score = -10;
         } else if (score == 1) { // cell1 is mySeed
            return 0;
         } else {  // cell1 is empty
            score = -1;
         }
      }
 
      // Third cell
      if (cells[row3][col3] == "O") {
         if (score > 0) {  // cell1 and/or cell2 is mySeed
            score *= 10;
         } else if (score < 0) {  // cell1 and/or cell2 is oppSeed
            return 0;
         } else {  // cell1 and cell2 are empty
            score = 1;
         }
      } else if (cells[row3][col3] == "X") {
         if (score < 0) {  // cell1 and/or cell2 is oppSeed
            score *= 10;
         } else if (score > 1) {  // cell1 and/or cell2 is mySeed
            return 0;
         } else {  // cell1 and cell2 are empty
            score = -1;
         }
      }
      return score;
   }
 
   private int[] winningPatterns = {
         0b111000000, 0b000111000, 0b000000111, // rows
         0b100100100, 0b010010010, 0b001001001, // cols
         0b100010001, 0b001010100               // diagonals
   };
   /** Returns true if thePlayer wins */
   private boolean hasWon(String thePlayer) {
      int pattern = 0b000000000;  // 9-bit pattern for the 9 cells
      for (int row = 0; row < 3; ++row) {
         for (int col = 0; col < 3; ++col) {
            if (cells[row][col] == thePlayer) {
               pattern |= (1 << (row * 3 + col));
            }
         }
      }
      for (int winningPattern : winningPatterns) {
         if ((pattern & winningPattern) == winningPattern) return true;
      }
      return false;
   }
}
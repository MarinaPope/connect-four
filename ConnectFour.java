/**
 * ConnectFour.java
 * Assignment: Final Project
 * Purpose: To apply what we have learned in APCS
 * throughout the whole year in a fun game.
 * @version 06/16/15
 * @author Marina Pope
 * @author Tabea Frese-Despins
 */
 
import java.util.*;
import java.awt.*;

public class ConnectFour{
   public static void main(String[] args){
      DrawingPanel panel = new DrawingPanel(550,550);
      Graphics g = panel.getGraphics();
      Scanner console = new Scanner(System.in);
      boolean getGame = true;
      displayIntro();
      while(getGame){
         char[][]board = board(g);
         boolean isBlack = true;
         boolean isRed = false;
         int column = 0;
         boolean playersTurn = true;
         boolean rightNum = false;
      
         while(getWinner(board, playersTurn)){
            rightNum = false;
            currentPlayer(isBlack,isRed,g);
            System.out.print("Choose a column to place your disk (1-7): ");
            while(rightNum == false){
               column = (console.nextInt()) -1;
               rightNum = getRightNum(console, rightNum, column, board);
            }
            drawBoard(column, board, isBlack, isRed,g);
            isBlack = !isBlack;
         }
         isWinner(isBlack,isRed);
         System.out.println();
         getGame = playAgain(console, getGame);
      } 
   }      
   
   //Inputs: None
   //Outputs: Intro to the game
   //Purpose: To tell the player what to input
   public static void displayIntro(){
      System.out.println("CONNECT FOUR");
      System.out.println("Please do not type anything besides a number");
      System.out.println();
   }
   
   //Inputs: isBlack, isRed. This tells us whose turn it is
   //Outputs: Displays who has won the game
   //Purpose: To tell the user who has won
   public static void isWinner(boolean isBlack, boolean isRed){
      System.out.println();
      if(isBlack == false){
         System.out.println("Congratulations Black Player!!!");
      }
      else{
         System.out.println("Congratulations Red Player!!!");
      }
   }
   
   //Inputs: Graphics g
   //Outputs: Draws the yellow board background
   //Purpose: To display the Connect Four board,
   // and stores a ' ' in the 2D board array
   public static char[][] board(Graphics g){   
      g.drawLine(0,0,0,427);
      g.drawLine(0,0,500,0);
      g.drawLine(0,427,500,427);
      g.drawLine(500,0,500,427);
      for(int i = 0; i< 6; i++){
         for(int j= 0; j<= 6; j++){
            g.setColor(Color.YELLOW);
            g.fillRect(j*71,i*71,71,71);
            g.setColor(Color.WHITE);
            g.fillOval(j*71,i*71,71,71); 
         }
      }
      char[][] board = new char[6][7];
      for(int j = 0;j <= 6; j++){
         for(int i= 0; i < 6; i++){
            board[i][j] = ' ';
         }
      }
      return board;
   }

   //Inputs: column, board, isBlack, isRed, Graphics
   //Outputs: Colored discs to the board based on whose turn it is
   //Purpose: To display discs in the column that the player typed
   public static void drawBoard(int column, char[][] board, boolean isBlack, boolean isRed,Graphics g){
      char player = ' ';
      if(isBlack == true){
         g.setColor(Color.BLACK);
         player = 'b';
      }
      else{
         g.setColor(Color.RED);
         player = 'r';
      }
      
      int x = 0;
      int row = 5;
      while(board[row-x][column] != ' '){
         x++;
      }
      row = row-x;
      g.fillOval((column * 71),(row * 71), 71,71);
      board[row][column] = player;
   }
   
   //Inputs: isBlack, isRed, Graphics
   //Outputs: Whose turn it is
   //Purpose: To display whose turn it is on the drawing panel
   public static void currentPlayer(boolean isBlack, boolean isRed, Graphics g){
      if(isBlack){
         System.out.println("Black's Turn");
         g.setColor(Color.WHITE);
         g.drawString("Red Disc's Turn",200, 450);
         g.setColor(Color.BLACK);
         g.drawString("Black Disc's Turn", 200, 450);
      }
      else{
         System.out.println("Red's Turn");
         g.setColor(Color.WHITE);
         g.drawString("Black Disc's Turn",200, 450);
         g.setColor(Color.RED);
         g.drawString("Red Disc's Turn",200, 450);
      }
   }

   //Inputs: Scanner, rightNum, column, board
   //Outputs: Asking the player to try again for the correct number
   //Purpose: To have the player type in a number 1-7 in to the console
   public static boolean getRightNum(Scanner console, boolean rightNum, int column,char[][] board){
      if(column >= 0 && column < 7 && board[0][column] == ' '){
         rightNum = true;
      }
      else{
         System.out.print("Try again: ");
      }
      return rightNum;
   }

   //Inputs: board, playersTurn
   //Outputs: A true or false as to if a player has won (either isBlack or isRed will be true/returned)
   //Purpose: After each turn, this method is run to determine if someone
   // has gotten four discs in a row
   public static boolean getWinner(char[][] board, boolean playersTurn){
      int verticalCount = 0;
      int horizontalCount = 0;
      boolean isHorizontal = false;
      boolean isVertical = false;
      for(int i = 0; i <= board[0].length - 1; i++){
         verticalCount = 0;                               
         for(int j = board.length - 1; j > 0; j--){
            if(board[j][i] == board[j-1][i] && board[j][i] != ' '){
               verticalCount++;
            } 
            else {
               verticalCount = 0;
            }
            
            if(verticalCount == 3){
               isVertical = true;
            }
         }
      }
   
      for(int i = board.length - 1; i >= 0; i--){
         for(int j = 0; j < board[0].length-1; j++){
            if(board[i][j] == board[i][j+1] && board[i][j] != ' '){
               horizontalCount++;
            } 
            else{
               horizontalCount = 0;
            }
            if(horizontalCount == 3){
               isHorizontal = true;
            }
         }
      }
      if(isVertical  || isHorizontal){
         playersTurn = false;
      }
      else{
         playersTurn = true;}
      return playersTurn;
   }
   
   public static boolean playAgain(Scanner console, boolean getGame){
      System.out.println("Do you want to play again? YES or NO");
      String answer = console.next();
      if(answer.startsWith("Y") || answer.startsWith("y")){
         System.out.println();
      }
      else{
         getGame = false;
         System.out.print("THE END");
      }
      return getGame;
   }
}


/* SELF ASSESSMENT
Connect4Game class (35 marks)
My class creates references to the Connect 4 Grid and two Connect 4 Players. It asks the user whether he/she would like to play/quit inside a loop. 
If the user decides to play then: 1. Connect4Grid2DArray is created using the Connect4Grid interface, 2. the two players are initialised - 
must specify the type to be ConnectPlayer, and 3. the game starts. In the game, I ask the user where he/she would like to drop the piece. 
I perform checks by calling methods in the Connect4Grid interface. Finally a check is performed to determine a win. 
Comment: [ 25/35] My class creates references to the Connect 4 Grid and the two Connect 4 players.It asks the user if they want to play with another human for against AI
         or to quit the game. The problem for this class that I coludn't debug was that after creating the second player object and printing the board it then asks the user 
         again whether they want to play and against who, for some reason, its like it has somehow gone back into the first while loop.Regardless I still ask the user where
         they would want to drop their piece and determine whether someone has won or not and display the result.  


Connect4Grid interface (10 marks)
I define all 7 methods within this interface.
Comment: [10/10] I define all the methods given in the question within this interface.


Connect4Grid2DArray class (25 marks) 
My class implements the Connect4Grid interface. It creates a grid using a 2D array Implementation of the method to check whether the column to drop the piece is valid. 
It provides as implementation of the method to check whether the column to drop the piece is full. It provides as implementation of the method to drop the piece.  
It provides as implementation of the method to check whether there is a win.
Comment: [25/25] My class implements the methods declared in the Connect4Grid interface. It creates the grid and checks if the column entered by the user is a valid column, 
         right number or whether that column is full, it implements the dropPiece method which places a piece in the desired column and when it hits another piece the piece 
         is placed -1 from the piece hit.I also implemented a method that checks the rows and column for vertical, horizontal and diagonal connect fours.
         
         
ConnectPlayer abstract class (10 marks)
My class provides at least one non-abstract method and at least one abstract method. 
Comment: [8/10] My class provides two abstract methods, one getPiece and a columnToPlace method.


C4HumanPlayer class (10 marks)
My class extends the ConnectPlayer class and overrides the abstract method(s). It provides the Human player functionality.
Comment: [10/10] My class extends the abstract ConnectPlayer class and overrides the two abstract methods there. It provides the Human Player functionally by asking the user which
         column they would like to place their piece continuously until the game is over.
         
         
C4RandomAIPlayer class (10 marks)
My class extends the ConnectPlayer class and overrides the abstract method(s). It provides AI player functionality. 
Comment: [10/10] My class extends the ConnectPlayer abstract class and overrides the methods. It provides the AI functionality by generating a random 
         column number between 1-7 using a random generator.
         
Total Marks out of 100: [88/100]  
*/

import java.util.Scanner;

public class Connect4Game {
	public static final char PLAYER_ONE_PIECE='R';
	public static final char PLAYER_TWO_PIECE='Y';
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connect4Grid2DArray board = new Connect4Grid2DArray();
		Scanner userInput= new Scanner(System.in);
		ConnectPlayer playerOne=null;
		ConnectPlayer playerTwo=null;
		boolean finished=false;
		while(!finished){
			board.emptyGrid();
			String playerOneInput="";
			String playerTwoInput="";
			String gameInstruction;
			while(!playerOneInput.equals("Human")&&!playerOneInput.equals("AI")
					&&!playerOneInput.equals("Quit")&&!playerOneInput.equals("quit")){
				gameInstruction = "Please enter would you like Player One to be a 'Human' or 'AI' player or you can 'Quit':";
				playerOneInput = getString(userInput,gameInstruction);
				if(!playerOneInput.equals("Human")&&!playerOneInput.equals("AI")
						&&!playerOneInput.equals("Quit")&&!playerOneInput.equals("quit")){
					System.out.println("Invalid input. Enter 'Human or 'AI' or 'Quit'.");
				}
				if(playerOneInput.equals("Human")){
					playerOne = new C4HumanPlayer(PLAYER_ONE_PIECE);
				}
				else if(playerOneInput.equals("AI")){
					playerOne = new C4RandomAIPlayer(PLAYER_ONE_PIECE);
				}
				else if(playerOneInput.equals("Quit")||playerOneInput.equals("quit")){
					finished = true;
				}
			}
			while(!playerTwoInput.equals("Human")&&!playerTwoInput.equals("AI")
					&&!playerTwoInput.equals("Quit")&&!playerTwoInput.equals("quit")&&!finished){
				gameInstruction = "Would you like Player Two to be a 'Human' or 'AI' player or you can'Quit' :";
				playerTwoInput = getString(userInput,gameInstruction);
				if(!playerTwoInput.equals("Human")&&!playerTwoInput.equals("AI")){
					System.out.println("Invalid input. Enter 'Human or 'AI' or 'Quit'.");
				}
				if(playerTwoInput.equals("Human")){
					playerTwo = new C4HumanPlayer(PLAYER_TWO_PIECE);
				}
				else if(playerTwoInput.equals("AI")){
					playerTwo = new C4RandomAIPlayer(PLAYER_TWO_PIECE);
				}
				else if(playerTwoInput.equals("Quit")||playerTwoInput.equals("quit")){
					finished = true;
				}
			}
			if(!finished){
				System.out.print(""+board.toString());
				while(!board.didLastPieceConnect4()&&!board.isGridFull()){
					boolean validColumn=false;
					while(!validColumn){
						int playerOneMove = playerOne.columnToPlace();
						if(board.isValidColumn(playerOneMove)){
							validColumn = true;
							board.dropPiece(playerOne, playerOneMove);
							playerOneMove++;
							System.out.println("Player One placed a piece "+playerOne.getPiece()+" in column "+(playerOneMove)+".");
						}
						else{
							System.out.print("Invalid column, please try again.");
						}
					}
					System.out.print(""+board.toString());
					validColumn = false;
					if(!board.didLastPieceConnect4()){
						while(!validColumn){
							int playerTwoMove = playerTwo.columnToPlace();
							if(board.isValidColumn(playerTwoMove)){
								validColumn = true;
								board.dropPiece(playerTwo, playerTwoMove);
								playerTwoMove++;
								System.out.print("Player Two placed a piece "+playerTwo.getPiece()+" in column "+(playerTwoMove)+".");
								if(board.didLastPieceConnect4()){
									System.out.println("PLAYER TWO WON!");
								}
							}
						}
						System.out.print(""+board.toString());
					}
					else{
						System.out.println("PLAYER ONE WON!");
					}
					if(board.isGridFull()&!board.didLastPieceConnect4()){
						System.out.println("DRAW!");
					}
				}
			}
			else if(finished){
				System.out.println("Goodbye!");
			}
		}
	}

	public static String getString(Scanner userInput,String gameInstruction) {
		boolean finished = false;
		while(!finished){
			System.out.println(""+gameInstruction);
			String inputtedText = userInput.nextLine();
			boolean validName = inputtedText.matches("[a-zA-Z]+");
			if (validName){
				finished = true;
				return inputtedText;
			}
			else if (!validName){
				System.out.println("Invalid input.");

			}
		}
		return null;	
	}
		

}



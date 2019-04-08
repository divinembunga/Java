import java.util.Scanner;

public class C4HumanPlayer extends ConnectPlayer {
	private static final int MAX_COLUMN=7;
	private static final int MIN_COLUMN=1;
	char playerPiece;

	C4HumanPlayer(char connect4Piece){
		this.playerPiece=connect4Piece;
		
	}

	@Override
	public char getPiece() {
		// TODO Auto-generated method stub
		return this.playerPiece;
	}

	@Override
	public int columnToPlace() {
		// TODO Auto-generated method stub
		boolean validSlot=false;
		Scanner player=new Scanner(System.in);
		int column=0;
		while(!validSlot){
			System.out.println("Enter the number of your move for"+this.getPiece()+":");
			if(player.hasNextInt()){
				column=player.nextInt();
				if(column>=MIN_COLUMN && column<=MAX_COLUMN){
					validSlot=true;
					return column;
					
				}else{
					System.out.println("Invalid move,must enter a number between 1-7");
				}
			}else{
				System.out.println("Invalid move,must enter a number between 1-7");
			}
		}
		return column;
	}

}

import java.util.Random;

public class C4RandomAIPlayer extends ConnectPlayer {
	char computerPiece;
	
	
	C4RandomAIPlayer(char connect4Piece){
		this.computerPiece=connect4Piece;
		
	}

	@Override
	public char getPiece() {
		// TODO Auto-generated method stub
		return this.computerPiece;
	}

	@Override
	public int columnToPlace() {
		// TODO Auto-generated method stub
		Random generator = new Random();
		int column=generator.nextInt(7);
		return column;
	}

}

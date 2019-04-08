
public class Connect4Grid2DArray implements Connect4Grid{
	public static final int NUMBER_OF_ROWS=6;
	public static final int NUMBER_OF_COLUMNS=7;
	public static final char BLANK=' ';
	public static final String RED_PIECE= "red";
	public static final String YELLOW_PIECE="yellow";
	public char[][]board;
	
	//Constructor
	Connect4Grid2DArray(){
		board= new char[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
		emptyGrid();
	}
	

	@Override
	public void emptyGrid() {
		// TODO Auto-generated method stub
		for(int i=0;i<NUMBER_OF_ROWS;i++){
			for(int j=0;j<NUMBER_OF_COLUMNS;j++){
				board[i][j]=BLANK;
			}
		}
	}






	@Override
	public boolean isValidColumn(int column) {
		if((column>=0) && (column<NUMBER_OF_COLUMNS)){
			if(!isColumnFull(column)){
				return true;
			}
		}
		return false;
	
	}
	


	@Override
	public boolean isColumnFull(int column) {
		// TODO Auto-generated method stub
		for(int row=0;row<NUMBER_OF_ROWS;row++){
			if(board[row][column]!=BLANK){
				return true;
			}
		}
		return false;
	}






	@Override
	public void dropPiece(ConnectPlayer player, int column) {
		// TODO Auto-generated method stub
		
		for(int row=0;row<NUMBER_OF_ROWS;row++){
			if(board[row][column]!=BLANK){
				board[row-1][column]=player.getPiece();
				return;
			}
		}
		board[5][column]=player.getPiece();
		return;
	}



	@Override
	public boolean didLastPieceConnect4() {
		// TODO Auto-generated method stub
		for(int row=0;row<NUMBER_OF_ROWS;row++){
			for(int column=0;column<NUMBER_OF_COLUMNS;column++){
				char connect4Check=board[row][column];
				if(column+3 < NUMBER_OF_COLUMNS && connect4Check ==board[row][column+1] &&
					connect4Check==board[row][column+2] && connect4Check==board[row][column+3]){
					return true;
				}
				if(row+3 <NUMBER_OF_ROWS){
					if(connect4Check ==board[row+1][column] && connect4Check==board[row+2][column] &&
					   connect4Check==board[row+3][column]){
						return true;
					}
					if (column+3 <NUMBER_OF_COLUMNS && connect4Check == board[row+1][column+1] &&
							connect4Check == board[row+2][column+2] && connect4Check == board[row+3][column+3])
					{
						return true;
					}
					if (column-3  >= 0 && connect4Check == board[row+1][column-1] &&
						connect4Check == board[row+2][column-2] && connect4Check == board[row+3][column-3])
					{
						return true;
					}					
				}
				
			}
		}
		return false;
	}






	@Override
	public boolean isGridFull() {
		// TODO Auto-generated method stub
		for(int column=0;column<NUMBER_OF_COLUMNS;column++){
			if(board[0][column]==BLANK){
				return false;
			}
		}
		return true;
		
	}
	
	
	public String toString(){
		String boardGrid="";
		boardGrid+="\n_______________\n";
		for(int row=0;row<NUMBER_OF_ROWS;row++){
			for(int column=0;column<NUMBER_OF_COLUMNS;column++){
				boardGrid+=board[row][column]+"|";
			}
			//boardGrid+="";
			boardGrid+="\n______________\n";
		}
		return boardGrid+=("\n 1 2 3 4 5 6 7 \n");
	}





}

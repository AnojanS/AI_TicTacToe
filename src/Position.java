import java.util.LinkedList;
import java.util.List;

public class Position {
	public char[] board;
	public char turn;
	public int dimension = 3;
	
	//CONSTRUCTOR
	public Position() {
		this.board = "         ".toCharArray(); //array of 9 spaces
		this.turn = 'x';
	}

	//CONSTRUCTOR
	public Position(char[] board, char turn) {
		this.board = board;
		this.turn = turn;
	}
	
	//CONSTRUCTOR
	public Position(String str) {
		this.board = str.toCharArray();
		this.turn = 'x';
	}
	
	//CONSTRUCTOR
	public Position(String str, char turn) {
		this.board = str.toCharArray();
		this.turn = turn;
	}
	
	public String toString() {
		return new String(board);
	}
	
	public Position move(int id) {
		char[] newBoard = board.clone();
		newBoard[id] = turn;
		
		return new Position(newBoard, turn == 'x' ? 'o' : 'x');
		//if turn is x then create Position with o, otherwise use x
	}
	
	public Integer[] possibleMoves() {
		List<Integer> list = new LinkedList<Integer>();
		
		for (int i = 0; i < board.length; i++) {
			if (board[i] == ' ') {
				list.add(i);
			}
		}
		Integer[] array = new Integer[list.size()];
		list.toArray(array);
		
		return array;
	}
	public boolean win_line(char turn, int start, int step) {
		for (int i = 0; i < 3; i++) {
			if (board[start + step*i] != turn) {
				return false;
			}
		}
		return true;
	}
	
	public boolean win(char turn) {
		for (int i = 0; i < dimension; i++) {
			//check for horizontal and vertical victories
			if (win_line(turn, i*dimension, 1) || win_line(turn,i,dimension)) {
				return true;
			}
		}
		//check for diagonal victories
		if (win_line(turn,dimension-1,dimension-1) || win_line(turn,0,dimension+1)){
			return true;
		}
		return false;
	}
	
	public int minimax() {
		if (win('x')) {return 100;}
		if (win('o')) {return -100;}
		if (possibleMoves().length == 0) {return 0;}
		
		Integer mm = null;
		for (Integer i : possibleMoves()) {
			Integer value = move(i).minimax();
			if ((mm == null) || (turn == 'x' && mm < value) || (turn == 'o' && value < mm)) {
				mm = value;
			}
		}
		
		return mm + (turn == 'x' ? -1 : 1) - 1;
		//if turn is x then mm + (-1) -1
	}
	
	public int bestMove() {
		Integer mm = null;
		int best = -1;
		for (Integer i : possibleMoves()) {
			Integer value = move(i).minimax();
			if ((mm == null) || (turn == 'x' && mm < value) || (turn == 'o' && value < mm)) {
				mm = value;
				best = i;
			}
		}
		
		return best;
	}
	
	
}

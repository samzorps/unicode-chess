import java.util.ArrayList;

/**
 * Board object represents the game board. 
 */
public class Board {
    static Piece board[][] = new Piece[8][8];

	static void printBoard() {
		System.out.println("    a   b   c   d   e   f   g   h");


		System.out.println("  ---------------------------------");
		int count = 8;
		for (int i = 0; i < 8; i++) {
			System.out.print(count + " ");
			System.out.print("| ");
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == null) {
					System.out.print("  | ");
				} else {
					System.out.print(board[i][j] + " | ");
				}
			}
			System.out.print(count);
			count--;
			System.out.println();
			System.out.println("  ---------------------------------");
		}
		System.out.println("    a   b   c   d   e   f   g   h");
		System.out.println();
	}
}
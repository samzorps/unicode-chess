import java.util.ArrayList;

/**
 * Board object represents the game board. 
 */
public class Board {
	public static ArrayList<Piece> black = new ArrayList<Piece>();
	public static ArrayList<Piece> white = new ArrayList<Piece>();

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
	/**
	 * startGame() is the method used to initialize the game, print the rules, and begin playing. 
	 */
	static void startGame() {
		System.out.println("How to play Unicode Chess:");
		System.out.println("To move a pawn, type in \"pawn\" followed by the file letter. example: \"pawnC\"");
		System.out.println("For knights, bishops and rooks, put \"Q\" or \"K\" to specify Queen's or King's side piece");
		System.out.println("After the piece name, provide a space and then enter a valid tile. example: \"knightK f3\"");
		System.out.println(
				"Pawns auto-promote to queens. The new queen is referenced by what the pawns file was. example: \"queenH\"");
		System.out.println(
				"To castle, type \"castle\", then a space, and then capital K or Q to specify which side. \"castle Q\"\n");

		// spawn balack pieces
		new Rook(Color.BLACK, "rookQ", 0, 0);
		new Knight(Color.BLACK, "knightQ", 1, 0);
		new Bishop(Color.BLACK, "bishopQ", 2, 0);
		new Queen(Color.BLACK, "queen", 3, 0);
		new King(Color.BLACK, "king", 4, 0);
		new Bishop(Color.BLACK, "bishopK", 5, 0);
		new Knight(Color.BLACK, "knightK", 6, 0);
		new Rook(Color.BLACK, "rookK", 7, 0);

		new Pawn(Color.BLACK, "pawnA", 0, 1);
		new Pawn(Color.BLACK, "pawnB", 1, 1);
		new Pawn(Color.BLACK, "pawnC", 2, 1);
		new Pawn(Color.BLACK, "pawnD", 3, 1);
		new Pawn(Color.BLACK, "pawnE", 4, 1);
		new Pawn(Color.BLACK, "pawnF", 5, 1);
		new Pawn(Color.BLACK, "pawnG", 6, 1);
		new Pawn(Color.BLACK, "pawnH", 7, 1);

		// spawn white pieces
		new Rook(Color.WHITE, "rookQ", 0, 7);
		new Knight(Color.WHITE, "knightQ", 1, 7);
		new Bishop(Color.WHITE, "bishopQ", 2, 7);
		new Queen(Color.WHITE, "queen", 3, 7);
		new King(Color.WHITE, "king", 4, 7);
		new Bishop(Color.WHITE, "bishopK", 5, 7);
		new Knight(Color.WHITE, "knightK", 6, 7);
		new Rook(Color.WHITE, "rookK", 7, 7);

		new Pawn(Color.WHITE, "pawnA", 0, 6);
		new Pawn(Color.WHITE, "pawnB", 1, 6);
		new Pawn(Color.WHITE, "pawnC", 2, 6);
		new Pawn(Color.WHITE, "pawnD", 3, 6);
		new Pawn(Color.WHITE, "pawnE", 4, 6);
		new Pawn(Color.WHITE, "pawnF", 5, 6);
		new Pawn(Color.WHITE, "pawnG", 6, 6);
		new Pawn(Color.WHITE, "pawnH", 7, 6);
	}

	/**
	 * Sets a piece to the desired x, y location on the board
	 * @param x x coordinate
	 * @param y y coordinate 
	 * @param piece piece to be set
	 */
	public static void setPiece(int x, int y, Piece piece) {
		if (piece != null) {
			piece.setX(x);
			piece.setY(y);
		}
		board[y][x] = piece;
	}

	/**
	 * Get and return the piece currently at the x, y location on the board.
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return piece at coordinates x, y
	 */
	public static Piece getPiece(int x, int y) {
		return board[y][x];
	}

	/**
	 * gets a piece object that is being reffered to by a given string. 
	 * ex: knightQ gives queen side knight
	 * 
	 * @param piece string that refrences a certain piece
	 * @param color color of the piece
	 * @return piece object reffered to
	 */
	public static Piece getPiece(String piece, Color color) {

		if (color == Color.WHITE) {

			for (int i = 0; i < white.size(); i++) {
				Piece p = white.get(i);
				if (p.matchID(piece)) {
					return p;
				}
			}
		}

		else if (color == Color.BLACK) {

			for (int i = 0; i < black.size(); i++) {
				Piece p = black.get(i);
				if (p.matchID(piece)) {
					return p;
				}
			}
		}

		return null;

	}
}
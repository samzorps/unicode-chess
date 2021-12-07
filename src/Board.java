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

	/**
	 * returns true if the path from two squares is not blocked by any pieces
	 * 
	 * @param x1 first x coordinate
	 * @param y1 first y coordinate
	 * @param x2 second x coordinate
	 * @param y2 second y coordinate
	 * @return true if there is an unblocked path from (x1, y1) to (x2, y2), otherwise false
	 */
	public static boolean isPathClear(int x1, int y1, int x2, int y2) {
		int xDistance = x2 - x1;
		int yDistance = y2 - y1;
		int xDir = 0;
		int yDir = 0;
		int size = 0;

		if (yDistance < 0) {
			yDir = -1;
		} else if (yDistance > 0) {
			yDir = 1;
		}

		if (xDistance < 0) {
			xDir = -1;
		} else if (xDistance > 0) {
			xDir = 1;
		}

		if (xDistance != 0) {
			size = Math.abs(xDistance) - 1;
		} else {
			size = Math.abs(yDistance) - 1;
		}

		for (int i = 0; i < size; i++) {
			x1 += xDir;
			y1 += yDir;
			if (getPiece(x1, y1) != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Processes a single move 
	 * @param move move string given by the user
	 * @param color color making the move
	 * @return 
	 */
	static int processMove(String move, Color color) {

		String[] splitStr = move.split(" ");
		String piece = splitStr[0];

		if (piece.equals("castle")) {
			King king = (King) getPiece("king", color);
			return king.castle(splitStr[1]);
		}

		// piece to move
		Piece p = getPiece(piece, color);
		if (p == null) {
			System.out.println("invalid piece, please type in piece to move it.");
			return -1;
		}

		if (splitStr.length < 2 || splitStr[1].length() != 2) {
			System.out.println("Invalid Tile please try again");
			return -1;
		}

		String coordinates = splitStr[1];

		// get x, y coordinates (rank, file)
		int rank = 7 - (coordinates.charAt(1) - '1'); 
		int file = coordinates.charAt(0) - 'a'; 

		if (rank < 0 || rank > 7 || file < 0 || file > 7) {
			System.out.println("Invalid Tile please try again");
			return -1;
		}

		Piece other = getPiece(file, rank);
		return p.move(file, rank, other);
	}

	/**
	 * return true if a given color is in check
	 * @param color
	 * @return
	 */
	public static boolean isCheck(Color color) {
		Piece king = getPiece("king", color);

		if (color == Color.WHITE) {
			for (int i = 0; i < black.size(); i++) {
				Piece p = black.get(i);
				if (p.possibleMove(king.getX(), king.getY())) {
					return true;
				}
			}
		}

		else if (color == Color.BLACK) {
			for (int i = 0; i < white.size(); i++) {
				Piece p = white.get(i);
				if (p.possibleMove(king.getX(), king.getY())) {
					return true;
				}
			}
		}

		return false;
	}

	/** return true if there is no possibe moves to get out of check, in other words checkmate.
	 * 
	 * @param color
	 * @return
	 */
	public static boolean isMate(Color color) {

		if (color == Color.WHITE) {
			for (int i = 0; i < white.size(); i++) {
				Piece p = white.get(i);
				if (p.canMove()) {
					return false;
				}
			}
		} else if (color == Color.BLACK) {
			for (int i = 0; i < black.size(); i++) {
				Piece p = black.get(i);
				if (p.canMove()) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * returns true if there is a stalemate, where there are no legal moves one can make
	 * 
	 * @param color color to be checked for a stalemate
	 * @return true if stalemate, false otherwise
	 */
	public static boolean staleMate(Color color) {

		if (isMate(color) == true && isCheck(color) == false) {
			return true;
		}

		return false;

	}

}


public abstract class Piece {
	//use encapsulation
	private final String ID;
	public boolean isFirstMove;
	private final Color color;
	private int x, y;

	/***
	 * General constructor method for a chess piece
	 * @param color
	 * @param ID
	 * @param startX
	 * @param startY
	 */
	public Piece(Color color, String ID, int startX, int startY) {
		this.ID = ID;
		this.color = color;
		this.x = startX;
		this.y = startY;

		if (this.getColor() == Color.WHITE) {
			Board.white.add(this);
		} else if (this.getColor() == Color.BLACK) {
			Board.black.add(this);
		}
		Board.setPiece(x, y, this);
	}

	/**
	 * Accessor method for a piece's ID
	 * @return Piece's ID
	 */
	public String getID() {
		return this.ID;
	}



	/**
	 * Accessor method for a piece's Color
	 * @return Piece's Color
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Accessor method for a piece's x value
	 * @return Piece's x value
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Mutator method for a piece's x value
	 * @param newX
	 */
	void setX(int newX) {
		this.x = newX;
	}

	/**
	 * Accessor method for a piece's x value
	 * @return Piece's y value
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Mutator method for a piece's y value
	 * @param newX
	 */
	void setY(int newY) {
		this.y = newY;
	}

	/**
	 * Method that compares two piece IDs
	 * @param ID string reprenting other piece's ID
	 * @return boolean representing whether or not the IDs are the same
	 */
	public boolean matchID(String ID) {
		return this.ID.equals(ID);
	}

	/**
	 * Method that compares two piece Colors
	 * @param otherPiece - other piece
	 * @return 
	 */
	public boolean sameColor(Piece otherPiece) {
		if (otherPiece == null) {
			return false;
		}
		return (this.color == otherPiece.getColor());
	}



	/**
	 * Abstract method for subclasses to override, determines if can move to certain position
	 * @param x x value to move to
	 * @param y y value to move to
	 * @return boolean representing whether or not (x, y) is a valid place to move
	 */
	public abstract boolean possibleMove(int x, int y);

	/**
	 * 
	 * @param x
	 * @param y
	 * @param other
	 * @return
	 */
	public int move(int x, int y, Piece other) {
		if (!this.possibleMove(x, y)) {
			return -1;
		}
		
		int originX = this.getX();
		int originY = this.getY(); 
		Color color = this.getColor();
		// check if possible move


		if (this.getColor() == Color.WHITE) {
			Board.black.remove(other);
		} else {
			Board.white.remove(other);
		}

		Board.setPiece(originX, originY, null);
		Board.setPiece(x, y, this);

		boolean isFirstMoveOG = this.isFirstMove;
		this.isFirstMove = false;

		if (Board.isCheck(color) == true) {
			if (other != null) {
				if (this.getColor() == Color.WHITE) {
					Board.black.add(other);
				} else {
					Board.white.add(other);
				}
			}
			Board.setPiece(originX, originY, this);
			Board.setPiece(x, y, other);
			this.isFirstMove = isFirstMoveOG;

			return -1;
		}

		// Turn Pawn to Queen if get to the end of the board
		if (this instanceof Pawn) {
			char file = this.getID().charAt(4);
			if (this.getColor() == Color.WHITE && y == 0) {
				Board.setPiece(x, y, null);
				Board.white.remove(this);
				Queen newQueen = new Queen(Color.WHITE, "queen" + file, x, y);
				System.out.println("Pawn promoted to a queen!");
			} else if (this.getColor() == Color.BLACK && y == 7) {
				Board.setPiece(x, y, null);
				Board.black.remove(this);
				Queen newQueen = new Queen(Color.BLACK, "queen" + file, x, y);
				System.out.println("Pawn promoted to a queen!");
			}
		}

		return 0;
	}
	/**
	 * tests a single move of a piece to position x, y
	 * returns true if it is possible, false otherwise
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean testMove(int x, int y) {

		Piece other;
		boolean isFirst = this.isFirstMove;

		int originX = this.getX();
		int originY = this.getY();

		if (x >= 0 && y >= 0 && x <= 7 && y <= 7) {
			other = Board.getPiece(x, y);
			if (this.move(x, y, other) == 0) {
				Board.setPiece(x, y, other);
				Board.setPiece(originX, originY, this);
				isFirstMove = isFirst;
				if (other != null) {
					if (other.getColor() == Color.WHITE) {
						Board.white.add(other);
					} else
						Board.black.add(other);
				}
				return true;
			}
		}
		return false;
	}

	public String nullToString() {
		return "   ";
	}

	/**
	 * abstract methods only to be implemented by the subclasses
	 */
	public abstract String toString();

	public abstract boolean canMove();
}

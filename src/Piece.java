
public abstract class Piece {

	private final Color color;

	private final String ID;

	private int x, y;

	public boolean isFirstMove;

	/***
	 * General constructor method for a chess piece
	 * @param color
	 * @param ID
	 * @param startX
	 * @param startY
	 */
	public Piece(Color color, String ID, int startX, int startY) {
		this.color = color;
		this.ID = ID;
		this.x = startX;
		this.y = startY;

	}

	/**
	 * Accessor method for a piece's ID
	 * @return Piece's ID
	 */
	public String getID() {
		return this.ID;
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
	 * Accessor method for a piece's Color
	 * @return Piece's Color
	 */
	public Color getColor() {
		return this.color;
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
}
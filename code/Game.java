import java.util.Scanner;

public class Game {
	/**
	 * main implements the primary game loop. each iteration of this loop one move is processed, and this loop only
	 * completes once the game is over.
	 *  
	 * @param args default command line args
	 */
	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);

		while (true) {
			Board.startGame();
			Color color;
			int numTurns = 0;

			while (true) {
				Board.printBoard();
				// calculate whos turn it is
				if (numTurns % 2 == 0) color = Color.WHITE; else color = Color.BLACK;

				if (Board.staleMate(color) == true) {
					System.out.println("game over, stalemate!");
					break;
				}
				if (Board.isCheck(color) == true) {
					if (Board.isMate(color) == true) {
						System.out.printf("Checkmate, %s wins! \n", color == Color.WHITE ? "Black" : "White");
						break;
					}
					System.out.printf("%s is in Check! \n", color == Color.WHITE ? "White" : "Black");
				}
				System.out.printf("%s's turn \n", color == Color.WHITE ? "White" : "Black");
				
				// get the next line of input and pass it to processMove
				String move = userInput.nextLine();
				if (move.equals("quit")){
					userInput.close();
					System.exit(0);
				} else if (Board.processMove(move, color) == 0) {
					numTurns++;
				} else {
					System.out.println("illegal move");
				}
			}
			System.out.println("would you like to play again? y/n");
			if (userInput.next().equals("y")) {
				continue;
			} else
				userInput.close();
				System.exit(0);
		}
	}

}
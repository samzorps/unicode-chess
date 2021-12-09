import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Testing {
	/**
	 * This class implements automated testing of our code. All you have to do 
	 * is change the value of the string "testcasefile" to the correct testing file 
	 * to run that test case
	 * 
	 * @param args none
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// CHANGE THE VALUE OF testcasefile TO EDIT THE TESTCASE THAT IS RUN
		String testcasefile = "./testcases/longtest.txt";
		Scanner fileInput = new Scanner(new File(testcasefile));
        Scanner humanInput = new Scanner(System.in);

		while (true) {
			Board.startGame();

			int turns = 0;
			Color color;

			while (true) {
				Board.printBoard();
                // calculate whos turn it is
				if (turns % 2 == 0) {
					color = Color.WHITE;
				} else
					color = Color.BLACK;

				if (Board.staleMate(color) == true) {
					System.out.println("game over, stalemate!");
					break;
				}
				if (Board.isCheck(color) == true) {
					if (Board.isMate(color) == true) {

						System.out.printf("Checkmate, %s wins \n", color == Color.WHITE ? "Black" : "White");
						break;
					}
					System.out.printf("%s is in Check! \n", color == Color.WHITE ? "White" : "Black");
				}

				// move choice
				System.out.printf("%s's turn \n", color == Color.WHITE ? "White" : "Black");
                String move;
                if (fileInput.hasNextLine()){
				    move = fileInput.nextLine();
                } else {
                    move = humanInput.nextLine();
                }
				System.out.println(move);
				// process move
				if (Board.processMove(move, color) == 0) {
					turns++;
				} else {
					System.out.println("illegal move");
				}

			}
			System.out.println("would you like to play again? y/n");
			if (fileInput.next().equals("y")) {
				continue;
			} else
				System.exit(0);
		}
	}

}

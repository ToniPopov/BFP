package Ivan_Margichev_Toni_Popov_GAME_PROJECT;

import java.util.Random;
import java.util.Scanner;

public class Minesweeper implements IGame{

	private Player player;

	private Minesweeper() {

	}

	Minesweeper(Player player) {
		if (player != null) {
			this.player = player;
			System.out.println(player.name);
		}

	}
	
	@Override
	public  void play() {
		System.out.println("Enter a level \n1.Beginer\n2.Middle\n3.Advnced\n4.Custom\n99.Exit");
		Scanner in = new Scanner(System.in);
		int level = in.nextInt();
		int[] levelFind = startGame(level, in);
		int row = levelFind[0];
		int col = levelFind[1];
		String[][] board = new String[row][col];
		boolean[][] boardCheck = new boolean[row][col];
		int countWin = fillBoard(board, levelFind);
		do {
			if (level == 99) {
				break;
			}
			System.out.println("Enter num 1");
			int input = in.nextInt();
			if (input == 99) {
				break;
			}
			System.out.println("Enter num 2");
			int input2 = in.nextInt();
			if (input2 == 99) {
				break;
			}
			printBoard2(board, levelFind[1]);
			boardCheck = check(board, boardCheck, input, input2, 0, countWin);
			if (!boardCheck[0][0] && boardCheck.length < 2) {
				System.out.println("GAME OVER !!! LOOOOSEEERR !!");
				printBoard2(board, levelFind[1]);
				break;
				
			} else if (boardCheck[0][0] && boardCheck.length < 2) {
				System.out.println("YOU WIN !!");
				printBoard2(board, levelFind[1]);
				break;
			}

			printBoard(board, boardCheck, levelFind[1]);
		} while (true);
		in.close();
	}

	// returns a boolean matrix and check the cell from the input
	public static boolean[][] check(String[][] board, boolean[][] check, int row, int col, int count, int countWin) {
		boolean[][] gameOver = { { false } };
		boolean[][] winGame = { { true } };
		if (row >= 0 && row < board.length) {
			if (col >= 0 && col < board[row].length) {
				if (board[row][col].equals(" ") && !check[row][col]) {
					board[row][col] = "_";
					check[row][col] = true;
					// System.out.println(" row "+row + " col "+col);
					check(board, check, row - 1, col, count + 1, countWin);
					check(board, check, row + 1, col, count + 1, countWin);
					check(board, check, row, col + 1, count + 1, countWin);
					check(board, check, row, col - 1, count + 1, countWin);
				} else {
					check[row][col] = true;
				}
				if (board[row][col].equals("*")) {
					System.out.println("BOOM");
					return gameOver;
				}

			}
		}
		int checkWin = endGameCheck(check);
		if (checkWin == countWin) {
			return winGame;
		}
		return check;
	}

	// check if we win the game
	public static int endGameCheck(boolean[][] check) {

		int count = 0;
		for (int i = 0; i < check.length; i++) {
			for (int j = 0; j < check[i].length; j++) {
				if (check[i][j]) {
					count++;
				}
			}
		}
		return count;
	}

	// fill the main board with bombs and calls the method countMines
	public static int fillBoard(String[][] board, int[] rowCol) {
		Random rand = new Random();
		int count = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = " ";
			}
		}
		for (int i = 0; i < rowCol[2]; i++) {
			int randRow = rand.nextInt(rowCol[0]);
			int randCol = rand.nextInt(rowCol[1]);
			if (board[randRow][randCol] == " ") {
				board[randRow][randCol] = "*";

			} else {
				i--;
			}
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (!board[i][j].equals("*")) {
					if ((countMines(board, i, j) + "").equals("0")) {
						board[i][j] = " ";
					} else {
						board[i][j] = countMines(board, i, j) +"";
					}
					count++;
				}
			}
		}
		System.out.println(" COUNT: " + count);
		return count;
	}

	// Prints everything if the check of the value of the cell is true in the
	// matrix
	public static void printBoard(String[][] board, boolean[][] boardCheck, int count) {
		for (int i = 0; i < count; i++) {
			if (i == 0) {
				System.out.print("   " + i % 10 + " ");
			} else {
				System.out.print(i % 10 + " ");
			}
		}
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			if (i == 0) {
				for (int j = 0; j < board[i].length * 2; j++) {
					if (j == 0) {
						System.out.print("  .");
					} else {
						System.out.print("=");
					}
					if (j == board[i].length * 2 - 1) {
						System.out.print(".");
					}
				}
				System.out.println();
			}
			for (int j = 0; j < board[i].length; j++) {
				if (j == 0) {
					if (i > 9) {
						System.out.print(i);
					} else {
						System.out.print(" " + i);
					}
				}
				if (boardCheck[i][j]) {
					if (j == board[i].length - 1) {
						System.out.print("|" + board[i][j] + "|");
					} else {
						System.out.print("|" + board[i][j]);
					}
				} else {
					if (j == board[i].length - 1) {
						System.out.print("| |");
					} else {
						System.out.print("| ");
					}
				}
			}
			System.out.println();
			if (i == board.length - 1) {
				for (int j = 0; j < board[i].length * 2; j++) {
					if (j == 0) {
			 			System.out.print("  .");
					} else {
						System.out.print("=");
					}
					if (j == board[i].length * 2 - 1) {
						System.out.print(".");
					}

				}
				System.out.println();
			}
		}
	}

	// Printing the all board for easier check
	public static void printBoard2(String[][] board, int count) {
		for (int i = 0; i < count; i++) {
			if (i == 0) {
				System.out.print("   " + i % 10 + " ");
			} else {
				System.out.print(i % 10 + " ");
			}
		}
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			if (i == 0) {
				for (int j = 0; j < board[i].length * 2; j++) {
					if (j == 0) {
						System.out.print("  .");
					} else {
						System.out.print("=");
					}
					if (j == board[i].length * 2 - 1) {
						System.out.print(".");
					}
				}
				System.out.println();
			}
			for (int j = 0; j < board[i].length; j++) {
				if (j == 0) {
					if (i > 9) {
						System.out.print(i);
					} else {
						System.out.print(" " + i);
					}
				}

				if (j == board[i].length - 1) {
					System.out.print("|" + board[i][j] + "|");
				} else {
					System.out.print("|" + board[i][j]);
				}

			}
			System.out.println();
			if (i == board.length - 1) {
				for (int j = 0; j < board[i].length * 2; j++) {
					if (j == 0) {
						System.out.print("  .");
					} else {
						System.out.print("=");
					}
					if (j == board[i].length * 2 - 1) {
						System.out.print(".");
					}

				}
				System.out.println();
			}
		}
	}

	// Check the input data and processes them
	public static int[] startGame(int level, Scanner in) {

		int[] rowCol = new int[4];
		switch (level) {
		case 1:
			rowCol[0] = 8;
			rowCol[1] = 8;
			rowCol[2] = 10;
			break;
		case 2:
			rowCol[0] = 16;
			rowCol[1] = 16;
			rowCol[2] = 40;
			break;
		case 3:

			rowCol[0] = 16;
			rowCol[1] = 30;
			rowCol[2] = 99;
			break;
		case 4:
			rowCol[0] = 16;
			rowCol[1] = 30;
			System.out.println("Enter the numbers of mines ");
			rowCol[2] = in.nextInt();
			break;
		case 0:
			break;
		default:
			break;
		}
		return rowCol;
	}

	// counts the mines and returns the number to put in the main String[][]
	// board
	public static int countMines(String[][] board, int index, int jIndex) {
		int count = 0;
		int i = index;
		int j = jIndex;
		int lenI = 0;
		int lenJ = 0;
		if ((i != 0 && i != board.length - 1)) {
			lenI = i + 1;
			i = i - 1;
		} else if (i == 0) {
			lenI = i + 1;
		} else if (i == board.length - 1) {
			i = i - 1;
			lenI = board.length - 1;
		}
		if ((j != 0 && j < board[i].length - 1)) {
			lenJ = j + 1;
			j = j - 1;
		} else if (j == 0) {
			lenJ = j + 1;
		} else if (j == board[i].length - 1) {
			j = j - 1;
			lenJ = board[i].length - 1;
		}
		for (int k = i; k <= lenI; k++) {
			for (int m = j; m <= lenJ; m++) {
				if (board[k][m].equals("*")) {
					count++;
				}
			}
		}
		return count;
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void printBoard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printScore() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkForWin() {
		// TODO Auto-generated method stub
		
	}



	

	public void moves(int row, int col, GamePlayers player) {
		
	}

	@Override
	public void moves(GamePlayers player) {
		// TODO Auto-generated method stub
		
	}

}

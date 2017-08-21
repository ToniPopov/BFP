package Ivan_Margichev_Toni_Popov_GAME_PROJECT;

import java.util.Random;
import java.util.Scanner;

public class Seachess implements IGame {

	protected char[][] matr;
	protected Scanner in;
	protected int row;
	protected int col;
	protected int countEnd = 0;
	private Player player;
	private Bot bot;
	boolean returnBool = false;
	
	

	Seachess(Player player) {
		bot = new Bot();
		if (player != null) {
			this.player = player;
			System.out.println(player.name);
			System.out.println(bot.name);
		}

	}

	@Override
	public void play() {
		Scanner in = new Scanner(System.in);
		System.out.println("Row and Col");
		this.row = in.nextInt();
		this.matr = new char[row][row];
		fillMatr();
		printBoard();
		this.countEnd = row * row;
		while (true) {
			in = new Scanner(System.in);
			System.out.println("Enter a row");
			int row = in.nextInt();
			System.out.println("Enter a column");
			int col = in.nextInt();

			if (moves(row, col, player) || this.countEnd == 0) {
				System.out.println(player.name + " WINS !!! =)");
				break;
			}
			if (botMoves()) {
				System.out.println(bot.name + " WINS !!! =)");
				break;
			}

			System.out.println("Player points " + player.points);
		}
		in.close();

	}

	public void fillMatr() {
		for (int i = 0; i < this.matr.length; i++) {
			for (int j = 0; j < this.matr[i].length; j++) {
				this.matr[i][j] = ' ';
			}
		}

	}

	@Override
	public void printBoard() {
		System.out.print(" ");
		for (int i = 0; i < this.matr[0].length; i++) {
			System.out.print(" " + i % 10);
		}
		System.out.println();
		for (int i = 0; i < this.matr.length; i++) {
			for (int j = 0; j < this.matr.length; j++) {
				// (i % 10 +1) +
				if (j == 0) {
					System.out.print(i + " ");
				}
				if (j == this.matr.length - 1) {
					System.out.print(this.matr[i][j]);
				} else {
					System.out.print(this.matr[i][j] + "|");
				}
			}

			System.out.println();
			if (i != matr.length - 1) {
				System.out.print("  ");
				for (int j = 0; j < 2 * this.matr[i].length - 1; j++) {
					System.out.print("-");
				}
			}
			System.out.println();
		}

	}

	@Override
	public void printScore() {
		// TODO Auto-generated method stub

	}

	public boolean checkForWin(char ch, int row, int col) {
		boolean revDiagonal = true;
		boolean rowCheck = true;
		boolean diagonalCheck = true;
		boolean colCheck = true;
		for (int i = 0; i < this.matr.length; i++) {
			if (this.matr[i][i] != ch) {
				diagonalCheck = false;
			}
			if (this.matr[this.matr.length - 1 - i][i] != ch) {
				revDiagonal = false;
			}
		}

		for (int j = 0; j < this.matr.length; j++) {

			if (this.matr[row][j] != ch) {
				rowCheck = false;
			}
		}
		for (int j = 0; j < this.matr.length; j++) {
			if (this.matr[j][col] != ch) {
				colCheck = false;
			}
		}

		System.out.println(revDiagonal + "\n" + rowCheck + "\n" + diagonalCheck + "\n" + colCheck);

		if (revDiagonal || rowCheck || diagonalCheck || colCheck) {
			return true;
		} else {
			return false;
		}

	}

	boolean botMoves() {
		
		Random rand = new Random();
//		if (this.countEnd == (this.row * this.row - 1)) {
			int row = rand.nextInt(this.matr.length);
			int col = rand.nextInt(this.matr.length);
			if (this.matr[row][col] == ' ') {
				System.out.println("ROW "+row+" COL "+col);
				return returnBool= moves(row, col, this.bot);
			}else{
				botMoves();
			}
			System.out.println(returnBool);

			return returnBool;
//		}

	}

	boolean moves(int row, int col, GamePlayers player) {
		boolean returnBool = false;
		if (row >= 0 && row < this.matr.length) {
			if (col >= 0 && col < this.matr[0].length) {
				if (this.matr[row][col] == ' ') {
					this.matr[row][col] = player.name.charAt(0);
					this.countEnd--;
					printBoard();
					returnBool = checkForWin(player.name.charAt(0), row, col);
				}
			}
		}
		return returnBool;
	}

	@Override
	public void moves(GamePlayers player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkForWin() {
		// TODO Auto-generated method stub

	}

}

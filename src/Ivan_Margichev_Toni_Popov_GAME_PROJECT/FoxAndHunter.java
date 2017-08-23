package Ivan_Margichev_Toni_Popov_GAME_PROJECT;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FoxAndHunter implements IGame {
	protected char[][] matr;
	protected Scanner in;
	protected int row;
	protected int col;
	protected int countEnd = 0;
	private Player player;
	private Bot bot;
	boolean returnBool = false;
	ArrayList<Integer> botFigurePositions = new ArrayList<Integer>();
	int[] playerCurentPlace = new int[2];

	FoxAndHunter(Player player) {
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
		if (row % 2 != 0) {
			++row;
		}
		this.matr = new char[row][row];
		fillMatr();
		printBoard();
		int count = 0;
		while (true) {
			count++;
			moves(in);
			printBoard();
			if (count == 20) {
				break;
			}
		}
		System.out.println("End game!");

		in.close();

	}

	public void fillMatr() {
		for (int i = 0; i < this.matr.length; i++) {
			for (int j = 0; j < this.matr[i].length; j++) {

				if ((i + j) % 2 == 0) {
					this.matr[i][j] = '#';
				} else if (i == 0 && (i + j) % 2 != 0) {
					botFigurePositions.add(i);
					botFigurePositions.add(j);
					this.matr[i][j] = '\u25C9';
				} else {
					this.matr[i][j] = ' ';
				}
			}
		}
		this.matr[this.matr.length - 1][0] = '\u25CF';

		this.playerCurentPlace[0] = this.matr.length - 1;
		this.playerCurentPlace[1] = 0;
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

	boolean moves(Scanner in) {
		System.out.println("1.Move Left && Up");
		System.out.println("2.Move Rigth && Up");
		System.out.println("3.Move Left && Down");
		System.out.println("4.Move Rigth && Down");
		System.out.println("0.Exit");
		int move = in.nextInt();
		if (move >= 0 && move <= 4) {
			int row = this.playerCurentPlace[0];
			int col = this.playerCurentPlace[1];
			switch (move) {
			case 1:
				if (!checkMove(row - 1, col - 1, row, col)) {
					moves(in);
				}
				break;
			case 2:
				if (!checkMove(row - 1, col + 1, row, col)) {
					moves(in);
				}
				break;
			case 3:
				if (!checkMove(row + 1, col - 1, row, col)) {
					moves(in);
				}
				break;
			case 4:
				if (!checkMove(row + 1, col + 1, row, col)) {
					moves(in);
				}
				break;
			case 0:
				break;
			}

		} else {
			moves(in);
		}

		return true;
	}

	public boolean checkMove(int row, int col, int oldRow, int oldCol) {
		boolean returnMethos = false;
		if (row >= 0 && row <= this.matr.length - 1) {
			if (col >= 0 && col <= this.matr.length - 1 && this.matr[row][col] == ' ') {
				this.matr[row][col] = this.matr[oldRow][oldCol];
				this.matr[oldRow][oldCol] = ' ';
				this.playerCurentPlace[0] = row;
				this.playerCurentPlace[1] = col;
				returnMethos = true;
			}
		}
		return returnMethos;
	}

	@Override
	public void moves(GamePlayers player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkForWin() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {

		Player me = new Player("toni");
		FoxAndHunter play = new FoxAndHunter(me);

		play.play();

	}

}

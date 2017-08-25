package Ivan_Margichev_Toni_Popov_GAME_PROJECT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class FoxAndHunter implements IGame {
	protected char[][] matr;
	protected Scanner in;
	protected int row;
	protected int botFigure;
	protected int countEnd = 0;
	private Player player;
	private Bot bot;
	boolean checkWay = false;
	int[] botFigurePositions;
	int[] playerCurentPlace = new int[2];
	private int move;

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
		botFigurePositions = new int[row];
		fillMatr();
		printBoard();
		while (true) {
			if (moves(in)) {
				printBoard();
				break;
			}
			printBoard();
			botCheck();
			printBoard();

		}
		System.out.println("End game!");
		in.close();

	}

	public void fillMatr() {

		for (int i = 0, k = 0; i < this.matr.length; i++) {
			for (int j = 0; j < this.matr[i].length; j++) {

				if ((i + j) % 2 == 0) {
					this.matr[i][j] = '#';
				} else if (i == 0 && (i + j) % 2 != 0) {
					botFigurePositions[k] = i;
					botFigurePositions[++k] = j;
					k++;
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

	public void botCheck() {
		System.out.println("Move " + this.move);
		// if (this.move == 1) {
		if (botFigurePositions[botFigure + 1] - 1 < 0) {
			checkWay = true;
		} else if (botFigurePositions[botFigure + 1] + 1 > this.matr.length - 1) {
			checkWay = false;
		}
		if (checkWay) {
			botMove(botFigurePositions[botFigure] + 1, botFigurePositions[botFigure + 1] + 1,
					botFigurePositions[botFigure], botFigurePositions[botFigure + 1], botFigure);
		} else {
			botMove(botFigurePositions[botFigure] + 1, botFigurePositions[botFigure + 1] - 1,
					botFigurePositions[botFigure], botFigurePositions[botFigure + 1], botFigure);
		}

		if (botFigure == botFigurePositions.length - 2) {
			botFigure = 0;
			checkWay = false;
		} else {
			botFigure += 2;
		}

		// }
		// else if (this.move == 2) {
		// botMove(botFigurePositions.get(2) + 1, botFigurePositions.get(3) + 1,
		// botFigurePositions.get(2),
		// botFigurePositions.get(3), 2);
		// } else if (this.move == 3) {
		// botMove(botFigurePositions.get(4) + 1, botFigurePositions.get(5) + 1,
		// botFigurePositions.get(4),
		// botFigurePositions.get(5), 4);
		// } else if (this.move == 4) {
		// botMove(botFigurePositions.get(6) + 1, botFigurePositions.get(7) - 1,
		// botFigurePositions.get(6),
		// botFigurePositions.get(7), 6);
		// }
		//
	}

	public void botMove(int row, int col, int oldRow, int oldCol, int figureNum) {
		boolean returnMethos = false;
		System.out.println(Arrays.toString(botFigurePositions));
		System.out.println("finum " + figureNum);
		System.out.println("Fig Num "+this.botFigure);
		if (row >= 0 && row <= this.matr.length - 1) {
			if (col >= 0 && col <= this.matr.length - 1) {
				System.out.println("ROW " + row + " Col " + col);
				System.out.println("oldROW " + oldRow + " oldCol " + oldCol);
				System.out.println(this.matr[row][col]);
				if (this.matr[row][col] == ' ') {
					System.out.println("IN FFFF");
					System.out.println(this.matr[oldRow][oldCol]);
					this.matr[row][col] = this.matr[oldRow][oldCol];
					this.matr[oldRow][oldCol] = ' ';
					this.botFigurePositions[figureNum] = row;
					this.botFigurePositions[figureNum + 1] = col;
					System.out.println(Arrays.toString(botFigurePositions));
				} else if (this.matr[row][col] == '\u25CF') {
					System.out.println("IN else iff");
					if((this.matr.length - playerCurentPlace[1]) < this.matr.length/2){
						this.botFigure = figureNum- 2;
					}else {
						this.botFigure  = figureNum + 2;;
					}
					System.out.println("fig 2 " + (figureNum));
					if (checkWay) {
						botMove(botFigurePositions[botFigure] + 1, botFigurePositions[botFigure + 1] + 1,
								botFigurePositions[botFigure], botFigurePositions[botFigure + 1], botFigure);
					} else {
						botMove(botFigurePositions[botFigure] + 1, botFigurePositions[botFigure + 1] - 1,
								botFigurePositions[botFigure], botFigurePositions[botFigure + 1], botFigure);
					}
//					botMove(this.botFigurePositions[this.botFigure] + 1, this.botFigurePositions[this.botFigure] + 1,
//							this.botFigurePositions[this.botFigure], this.botFigurePositions[this.botFigure],
//							figureNum - 2);
				}
				// else if( this.matr[row][col] == '\u25C9'){
				// if(checkWay){
				// checkWay = false;
				// }else if(!checkWay){
				// checkWay = true;
				// }
				// if(this.matr.length - this.playerCurentPlace[1] <
				// this.matr.length/2){
				// botMove(botFigurePositions[0] + 1, botFigurePositions[0 + 1]
				// - 1,
				// botFigurePositions[0], botFigurePositions[0 + 1], 0);
				// }else if(this.matr.length - this.playerCurentPlace[1] <
				// this.matr.length/2){
				// botMove(botFigurePositions[this.matr.length-2] + 1,
				// botFigurePositions[this.matr.length-2 + 1] - 1,
				// botFigurePositions[this.matr.length-2],
				// botFigurePositions[this.matr.length-2 + 1],
				// this.matr.length-2);
				// }
				//
				//// botFigurePositions[botFigure + 1] + 1 > this.matr.length -
				// 1
				// }
				returnMethos = true;
			}
		}

	}

	boolean moves(Scanner in) {
		boolean returnVal = false;
		System.out.println("1.Move Left && Up");
		System.out.println("2.Move Rigth && Up");
		System.out.println("3.Move Left && Down");
		System.out.println("4.Move Rigth && Down");
		System.out.println("0.Exit");
		move = in.nextInt();
		if (move >= 0 && move <= 4) {
			int row = this.playerCurentPlace[0];
			int col = this.playerCurentPlace[1];
			switch (move) {
			case 1:
				if (!checkAndMove(row - 1, col - 1, row, col)) {
					moves(in);
				}
				break;
			case 2:
				if (!checkAndMove(row - 1, col + 1, row, col)) {
					moves(in);
				}
				break;
			case 3:
				if (!checkAndMove(row + 1, col - 1, row, col)) {
					moves(in);
				}
				break;
			case 4:
				if (!checkAndMove(row + 1, col + 1, row, col)) {
					moves(in);
				}
				break;
			case 0:
				returnVal = true;
				break;
			}

		} else {
			moves(in);
		}
		if (playerCurentPlace[0] == 0) {
			returnVal = true;
		}
		return returnVal;
	}

	public boolean checkAndMove(int row, int col, int oldRow, int oldCol) {
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

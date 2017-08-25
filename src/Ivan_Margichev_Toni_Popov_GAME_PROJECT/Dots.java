package Ivan_Margichev_Toni_Popov_GAME_PROJECT;

import java.util.Random;
import java.util.Scanner;

public class Dots implements IGame {

	protected char[][] matr;
	protected Scanner in;
	protected int row;
	protected int col;
	protected int countEnd = 0;
	private Player player;
	private Bot bot;

	private Dots() {

	}

	Dots(Player player) {
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
		System.out.println("Row");
		this.row = in.nextInt();
		System.out.println("Col");
		this.col = in.nextInt();
		this.matr = new char[2 * this.row - 1][2 * this.col - 1];
		fillMatr();
		int finish = ((this.col - 1) * (this.row - 1));
		System.out.println(this.matr.length);
		// this.matr[4][1] = '-';
		// this.matr[4][3] = '-';
		// this.matr[4][5] = '-';
		// // this.matr[4][7] = '-';
		// this.matr[2][7] = '-';
		//
		// this.matr[4][9] = '-';
		// this.matr[4][11] = '-';
		// this.matr[6][1] = '-';
		// this.matr[6][3] = '-';
		// this.matr[6][5] = '-';
		// matr[2][9] = '-';
		// matr[2][9] = '-';
		// matr[6][7] = '-';
		// this.matr[6][9] = '-';
		// this.matr[6][11] = '-';
		// this.matr[3][6] = '|';
		// // this.matr[3][8] = '|';
		// this.matr[1][6] = '|';
		// this.matr[1][8] = '|';
		// this.matr[7][6] = '|';
		// this.matr[7][8] = '|';
		// this.matr[9][6] = '|';
		// this.matr[9][8] = '|';
		// this.matr[10][7] = '-';

		// this.matr[1][0] = '|';
		// this.matr[3][0] = '|';
		// this.matr[5][0] = '|';
		// this.matr[7][0] = '|';
		// this.matr[9][0] = '|';
		// this.matr[11][0] = '|';
		// this.matr[13][0] = '|';
		// this.matr[1][2] = '|';
		// this.matr[3][2] = '|';
		// this.matr[5][2] = '|';
		// this.matr[7][2] = '|';
		// this.matr[9][2] = '|';
		// this.matr[11][2] = '|';
		// this.matr[13][2] = '|';

		// this.matr[0][1] = '-';
		// this.matr[0][3] = '-';
		// this.matr[0][5] = '-';
		// this.matr[0][7] = '-';
		// this.matr[0][9] = '-';
		// this.matr[0][11] = '-';
		// this.matr[0][13] = '-';
		// this.matr[2][1] = '-';
		// this.matr[2][3] = '-';
		// this.matr[2][5] = '-';
		// this.matr[2][7] = '-';
		// this.matr[2][9] = '-';
		// this.matr[2][11] = '-';
		// this.matr[2][13] = '-';

		printBoard();

		int check = 0;
		if (row >= col) {
			check = col * (2 * row - 1) - row;

		} else if (row < col) {
			check = row * (2 * col - 1) - col;
		}
		System.out.println(check);

		while (true) {
			in = new Scanner(System.in);
			System.out.println("Enter a row");
			row = in.nextInt();
			System.out.println("Enter a column");
			col = in.nextInt();
			moves(row, col, this.player);
			printBoard();
			botMove();
			printBoard();
			if (row == 200 || (player.points - bot.points == finish)) {
				break;
			}
			System.out.println("Player points " + player.points);
		}
		in.close();

	}

	public void botMove() {
		boolean breakLoop = false;
		Random rand = new Random();
		for (int row = 1; row < this.matr.length - 1; row += 2) {
			for (int col = 1; col < this.matr[row].length - 1; col += 2) {
				if (this.matr[row][col] == ' ') {
					if (this.matr[row - 1][col] == ('-') && this.matr[row + 1][col] == ('-')
							&& this.matr[row][col - 1] == ('|')) {
						moves(row, col + 1, this.bot);
						breakLoop = true;
						break;
					} else if (this.matr[row - 1][col] == ('-') && this.matr[row + 1][col] == ('-')
							&& this.matr[row][col + 1] == ('|')) {
						moves(row, col - 1, this.bot);
						breakLoop = true;
						break;
					} else if (this.matr[row - 1][col] == ('-') && this.matr[row + 1][col + 1] == ('|')
							&& this.matr[row][col - 1] == ('|')) {
						moves(row + 1, col, this.bot);
						breakLoop = true;
						break;
					} else if (this.matr[row + 1][col] == ('-') && this.matr[row][col + 1] == ('|')
							&& this.matr[row][col - 1] == ('|')) {
						moves(row - 1, col, this.bot);
						breakLoop = true;
						break;
					}

				}
			}
			if (breakLoop) {
				break;
			}
		}
		if (!breakLoop) {
			Done: for (int row = 1; row < this.matr.length - 1; row += 2) {
				for (int col = 1; col < this.matr[row].length - 1; col += 2) {
					if (this.matr[row][col] == ' ') {
						if (this.matr[row + 1][col] == (' ') && this.matr[row][col + 1] == (' ')
								&& this.matr[row][col - 1] == (' ') && this.matr[row - 1][col] == (' ')) {
							int r = rand.nextInt(4) + 1;
							System.out.println(r);
							switch (r) {
							case 1:
								moves(row - 1, col, this.bot);
								breakLoop = true;
								break;
							case 2:
								moves(row, col - 1, this.bot);
								breakLoop = true;
								break;
							case 3:
								moves(row + 1, col, this.bot);
								breakLoop = true;
								break;
							case 4:
								moves(row, col + 1, this.bot);
								breakLoop = true;
								break;
							}
							break Done;
						}
					}
				}
			}
		if(breakLoop){
			return;
		}
			Done: for (int row = 1; row < this.matr.length - 1; row += 2) {
				for (int col = 1; col < this.matr[row].length - 1; col += 2) {
					if (this.matr[row][col] == ' ') {
						if (this.matr[row + 1][col] == (' ') && this.matr[row][col + 1] == (' ')
								&& this.matr[row][col - 1] == (' ') && this.matr[row - 1][col] == (' ')) {
							int r = rand.nextInt(4) + 1;
							System.out.println(r);
							switch (r) {
							case 1:
								moves(row - 1, col, this.bot);
								breakLoop = true;
								break;
							case 2:
								moves(row, col - 1, this.bot);
								breakLoop = true;
								break;
							case 3:
								moves(row + 1, col, this.bot);
								breakLoop = true;
								break;
							case 4:
								moves(row, col + 1, this.bot);
								breakLoop = true;
								break;
							}
							break Done;
						} else if (this.matr[row + 1][col] == ('-') && this.matr[row][col + 1] == (' ')
								&& this.matr[row][col - 1] == (' ') && this.matr[row - 1][col] == (' ')) {
							int r = rand.nextInt(3) + 1;
							System.out.println(r);
							switch (r) {
							case 1:
								moves(row - 1, col, this.bot);
								breakLoop = true;
								break;
							case 2:
								moves(row, col - 1, this.bot);
								breakLoop = true;
								break;
							case 3:
								moves(row, col + 1, this.bot);
								breakLoop = true;
								break;
							}
							break Done;
						} else if (this.matr[row + 1][col] == (' ') && this.matr[row][col + 1] == ('|')
								&& this.matr[row][col - 1] == (' ') && this.matr[row - 1][col] == (' ')) {
							int r = rand.nextInt(3) + 1;
							System.out.println(r);
							switch (r) {
							case 1:
								moves(row - 1, col, this.bot);
								breakLoop = true;
								break;
							case 2:
								moves(row, col - 1, this.bot);
								breakLoop = true;
								break;
							case 3:
								moves(row + 1, col, this.bot);
								breakLoop = true;
								break;

							}
							break Done;
						} else if (this.matr[row + 1][col] == (' ') && this.matr[row][col + 1] == (' ')
								&& this.matr[row][col - 1] == ('|') && this.matr[row - 1][col] == (' ')) {

							int r = rand.nextInt(3) + 1;
							System.out.println(r);
							switch (r) {
							case 1:
								moves(row, col + 1, this.bot);
								breakLoop = true;
								break;
							case 2:
								moves(row - 1, col, this.bot);
								breakLoop = true;
								break;
							case 3:
								moves(row + 1, col, this.bot);
								breakLoop = true;
								break;
							}
							break Done;
						} else if (this.matr[row + 1][col] == (' ') && this.matr[row][col + 1] == (' ')
								&& this.matr[row][col - 1] == (' ') && this.matr[row - 1][col] == ('-')) {

							int r = rand.nextInt(3) + 1;
							System.out.println(r);
							switch (r) {
							case 1:
								moves(row, col + 1, this.bot);
								breakLoop = true;
								break;
							case 2:
								moves(row, col - 1, this.bot);
								breakLoop = true;
								break;
							case 3:
								moves(row + 1, col, this.bot);
								breakLoop = true;
								break;

							}
							break Done;
						} else if (this.matr[row + 1][col] == ('-') && this.matr[row][col + 1] == (' ')
								&& this.matr[row][col - 1] == (' ') && this.matr[row - 1][col] == ('-')) {
							int r = rand.nextInt(2) + 1;
							System.out.println(r);
							switch (r) {
							case 1:
								moves(row, col - 1, this.bot);
								breakLoop = true;
								break;
							case 2:
								moves(row, col + 1, this.bot);
								breakLoop = true;
								break;
							}
							break Done;
						} else if (this.matr[row + 1][col] == (' ') && this.matr[row][col + 1] == ('|')
								&& this.matr[row][col - 1] == ('|') && this.matr[row - 1][col] == (' ')) {
							int r = rand.nextInt(2) + 1;
							System.out.println(r);
							switch (r) {
							case 1:
								moves(row - 1, col, this.bot);
								breakLoop = true;
								break;
							case 2:
								moves(row + 1, col, this.bot);
								breakLoop = true;
								break;
							}
							break Done;
						} else if (this.matr[row + 1][col] == (' ') && this.matr[row][col + 1] == ('|')
								&& this.matr[row][col - 1] == (' ') && this.matr[row - 1][col] == ('-')) {

							int r = rand.nextInt(2) + 1;
							System.out.println(r);
							switch (r) {
							case 1:
								moves(row + 1, col, this.bot);
								breakLoop = true;
								break;
							case 2:
								moves(row, col - 1, this.bot);
								breakLoop = true;
								break;

							}
							break Done;
						} else if (this.matr[row + 1][col] == ('-') && this.matr[row][col + 1] == ('|')
								&& this.matr[row][col - 1] == (' ') && this.matr[row - 1][col] == (' ')) {

							int r = rand.nextInt(2) + 1;
							System.out.println(r);
							switch (r) {
							case 1:
								moves(row, col - 1, this.bot);
								breakLoop = true;
								break;
							case 2:
								moves(row - 1, col, this.bot);
								breakLoop = true;
								break;
							}
							break Done;
						} else if (this.matr[row + 1][col] == (' ') && this.matr[row][col + 1] == (' ')
								&& this.matr[row][col - 1] == ('|') && this.matr[row - 1][col] == ('-')) {

							int r = rand.nextInt(2) + 1;
							System.out.println(r);
							switch (r) {
							case 1:
								moves(row, col + 1, this.bot);
								breakLoop = true;
								break;
							case 2:
								moves(row + 1, col, this.bot);
								breakLoop = true;
								break;
							}
							break Done;
						} else if (this.matr[row + 1][col] == ('-') && this.matr[row][col + 1] == (' ')
								&& this.matr[row][col - 1] == ('|') && this.matr[row - 1][col] == (' ')) {

							int r = rand.nextInt(2) + 1;
							System.out.println(r);
							switch (r) {
							case 1:
								moves(row, col + 1, this.bot);
								breakLoop = true;
								break;
							case 2:
								moves(row - 1, col, this.bot);
								breakLoop = true;
								break;

							}
							break Done;
						}
					}
					if (breakLoop) {
						break Done;
					}
				}
			}
		}

	}

	public void fillMatr() {
		for (int i = 0; i < this.matr.length; i++) {
			for (int j = 0; j < this.matr[i].length; j++) {
				if (j % 2 == 0) {
					this.matr[i][j] = '*';
				} else {
					this.matr[i][j] = ' ';
				}
				if (i % 2 != 0) {
					this.matr[i][j] = ' ';
				}

			}
		}

		// for (int i = 0; i < matr.length; i++) {
		// for (int j = 0; j < matr[i].length; j++) {
		// if (i % 2 == 0 && j % 2 != 0) {
		// Dots.matr[i][j] = '-';
		// Dots.countEnd++;
		// } else if (i % 2 != 0 && j % 2 == 0) {
		// Dots.matr[i][j] = '-';
		// Dots.countEnd++;
		// }
		// }
		// }
		System.out.println("\nCount End: " + this.countEnd + "\n");

	}

	void checkUp(int row, int col, GamePlayers player) {
		System.out.println("up");
		// System.out.println("Row1 " + row + " Col1 " + col + " point1 " +
		// player.points);
		if (col > 0 && row >= 0 && row < this.matr.length - 1 && this.matr[row + 1][col + 1] == '|'
				&& this.matr[row + 1][col - 1] == '|' && this.matr[row + 2][col] == '-'
				&& this.matr[row + 1][col] == (' ')) {
			this.player.points++;
			this.matr[row][col] = '-';
			this.matr[row + 1][col] = player.name.charAt(0);
			System.out.println("Row1 " + row + " Col1 " + col + " point1 " + player.points);
			checkUp(row - 2, col, player);
		}
		if (col > 0 && row >= 0 && row < this.matr.length - 1 && this.matr[row + 1][col + 1] == ' '
				&& this.matr[row + 1][col - 1] == '|' && this.matr[row + 2][col] == '-' && this.matr[row][col] == '-'
				&& this.matr[row + 1][col] == (' ')) {
			checkRigth(row + 1, col + 1, player);
		}
		if (col > 0 && row >= 0 && row < this.matr.length - 1 && this.matr[row + 1][col + 1] == '|'
				&& this.matr[row][col] == '-' && this.matr[row + 2][col] == '-' && this.matr[row + 1][col] == (' ')) {
			System.out.println("INN1");
			checkLeft(row + 1, col - 1, player);
		}
	}

	public void checkDown(int row, int col, GamePlayers player) {
		System.out.println("down");

		if (row > 1 && row < this.matr.length && this.matr[row - 1][col + 1] == '|'
				&& this.matr[row - 1][col - 1] == '|' && this.matr[row - 2][col] == '-'
				&& this.matr[row - 1][col] == (' ')) {
			// down check
			this.matr[row][col] = '-';
			this.player.points++;
			this.matr[row - 1][col] = player.name.charAt(0);
			System.out.println("Row2 " + row + " Col2 " + col + " point2 " + player.points);
			checkDown(row + 2, col, player);
		}
		if (row > 1 && row < this.matr.length && this.matr[row - 1][col + 1] == ' '
				&& this.matr[row - 1][col - 1] == '|' && this.matr[row - 2][col] == '-'
				&& this.matr[row - 1][col] == (' ')) {
			checkRigth(row + 1, col + 1, player);
		}
		if (row > 1 && row < this.matr.length && this.matr[row - 1][col + 1] == '|'
				&& this.matr[row - 1][col - 1] == ' ' && this.matr[row - 2][col] == '-'
				&& this.matr[row - 1][col] == (' ')) {
			checkLeft(row + 1, col - 1, player);
		}
	}

	public void checkRigth(int row, int col, GamePlayers player) {
		System.out.println("rigth");
		// boolean callRec= false;

		if (row > 0 && row < this.matr.length && col > 0 && col < this.matr.length - 2
				&& this.matr[row + 1][col - 1] == '-' && this.matr[row - 1][col - 1] == '-'
				&& this.matr[row][col - 2] == '|' && this.matr[row][col - 1] == (' ')) {
			this.player.points++;
			this.matr[row][col] = '|';
			this.matr[row][col - 1] = player.name.charAt(0);
			System.out.println("Row3 " + row + " Col3 " + col + " point3 " + player.points);
			checkRigth(row, col + 2, player);
		}
		if (row < this.matr.length && row > 0 && col > 1 && col < this.matr.length - 1
				&& this.matr[row + 1][col - 1] == '-' && this.matr[row - 1][col - 1] == ' '
				&& this.matr[row][col - 2] == '|' && this.matr[row][col - 1] == (' ')) {
			checkUp(row - 1, col - 1, player);
		}
		if (col > 1 && col < this.matr.length - 1 && this.matr[row - 1][col - 1] == '-' && this.matr[row][col] == '|'
				&& this.matr[row][col - 2] == '|' && this.matr[row][col - 1] == (' ')) {
			checkDown(row + 1, col - 1, player);
		}
	}

	public void checkLeft(int row, int col, GamePlayers player) {
		System.out.println("left");
		// System.out.println("Row4 " + row + " Col4 " + col + " point4 " +
		// player.points);
		if (col >= 0 && col < this.matr.length - 2 && this.matr[row - 1][col + 1] == '-'
				&& this.matr[row + 1][col + 1] == '-' && this.matr[row][col + 2] == '|'
				&& this.matr[row][col + 1] == (' ')) {
			// down check
			this.matr[row][col] = '|';
			this.player.points++;
			this.matr[row][col + 1] = player.name.charAt(0);
			System.out.println("Row4 " + row + " Col4 " + col + " point4 " + player.points);
			checkLeft(row, col - 2, player);
		}
		if (col >= 0 && col < this.matr.length - 1 && this.matr[row - 1][col + 1] == '-'
				&& this.matr[row + 1][col + 1] == ' ' && this.matr[row][col + 2] == '|'
				&& this.matr[row][col + 1] == (' ')) {
			checkDown(row + 1, col + 1, player);

		}
		if (row > 0 && row < this.matr.length && col >= 0 && col < this.matr.length - 2
				&& this.matr[row - 1][col + 1] == ' ' && this.matr[row + 1][col + 1] == '-'
				&& this.matr[row][col + 2] == '|' && this.matr[row][col + 1] == (' ')) {
			checkUp(row - 1, col + 1, player);
		}
	}

	void distributor(int row, int col, GamePlayers player) {

	}

	void moves(int row, int col, GamePlayers player) {
		// boolean isInIf = false;
		if (row >= 0 && row < this.matr.length) {
			if (col >= 0 && col < this.matr[0].length) {
				if (row % 2 == 0 && col % 2 != 0) {
					System.out.println(player.name + " move");
					System.out.println("Row " + row + " Col " + col + " point " + player.points);
					// this.countEnd++;

					// check up

					checkUp(row, col, player);
					checkDown(row, col, player);
					this.matr[row][col] = '-';
					// checkRigth(row-1, col+1, player);
					// checkLeft(row-1, col-1, player);
				}
				if (row % 2 != 0 && col % 2 == 0) {
					// this.countEnd++;
					System.out.println(player.name + " move");
					System.out.println("Row " + row + " Col " + col + " point " + player.points);
					// check up
					checkRigth(row, col, player);
					checkLeft(row, col, player);
					this.matr[row][col] = '|';
				}
			}
		}
	}

	@Override
	public void printBoard() {
		System.out.print(" ");
		for (int i = 0; i < this.matr[0].length; i++) {
			System.out.print(i % 10);
		}
		System.out.println();
		for (int i = 0; i < this.matr.length; i++) {
			for (int j = 0; j < this.matr[i].length; j++) {
				if (j == 0) {
					System.out.print(i % 10 + "" + this.matr[i][j]);
				} else {
					System.out.print(this.matr[i][j]);
				}
			}
			System.out.println();
		}
	}

	@Override
	public void printScore() {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkForWin() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moves(GamePlayers player) {
		// TODO Auto-generated method stub

	}

}

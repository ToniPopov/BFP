import java.util.Scanner;

public class Dots {

	protected static String[][] matr;
	protected Scanner in;
	protected int row;
	protected int col;
	protected static int countEnd = 0;

	Dots() {

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Row");
		int row = in.nextInt();
		System.out.println("Col");
		int col = in.nextInt();
		matr = new String[2 * row - 1][2 * col - 1];

		Dots dots = new Dots();
		fillMatr(matr);
		matr[4][1] = "-";
		matr[4][3] = "-";
		matr[4][5] = "-";
		matr[4][7] = "-";
		matr[4][9] = "-";
		matr[4][11] = "-";
		matr[6][1] = "-";
		matr[6][3] = "-";
		matr[6][5] = "-";
		// matr[4][7] = "-";
		matr[6][9] = "-";
		matr[6][11] = "-";
		matr[7][6] = "|";
		matr[7][8] = "|";
		matr[9][6] = "|";
		matr[9][8] = "|";
		matr[10][7] = "-";
		printMatr(matr);

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
			dots.moves(row, col);
			printMatr(matr);
			if (row == 200) {
				break;
			}
		}
		in.close();

	}

	static void fillMatr(String[][] matr) {
		for (int i = 0; i < matr.length; i++) {
			for (int j = 0; j < matr[i].length; j++) {
				if (j % 2 == 0) {
					matr[i][j] = "*";
				} else {
					matr[i][j] = " ";
				}
				if (i % 2 != 0) {
					matr[i][j] = " ";
				}

			}
		}

		// for (int i = 0; i < matr.length; i++) {
		// for (int j = 0; j < matr[i].length; j++) {
		// if (i % 2 == 0 && j % 2 != 0) {
		// Dots.matr[i][j] = "-";
		// Dots.countEnd++;
		// } else if (i % 2 != 0 && j % 2 == 0) {
		// Dots.matr[i][j] = "|";
		// Dots.countEnd++;
		// }
		// }
		// }
		//
		System.out.println("\nCount End: " + Dots.countEnd + "\n");

	}

	static void printMatr(String[][] matr) {
		System.out.print(" ");
		for (int i = 0; i < matr[0].length; i++) {
			System.out.print(i % 10);
		}
		System.out.println();
		for (int i = 0; i < matr.length; i++) {
			for (int j = 0; j < matr[i].length; j++) {
				if (j == 0) {
					System.out.print(i % 10 + "" + matr[i][j]);
				} else {
					System.out.print(matr[i][j]);
				}
			}
			System.out.println();
		}
	}

	void moves(int row, int col) {

		if (row >= 0 && row < Dots.matr.length) {
			if (col >= 0 && col < Dots.matr[0].length) {
				if (row % 2 == 0 && col % 2 != 0) {
					Dots.countEnd++;
					Dots.matr[row][col] = "-";
					if (col > 0 && col < Dots.matr[0].length - 1 && row > 1 && row < Dots.matr.length - 2) {
						if (Dots.matr[row + 1][col - 1].equals("|") && Dots.matr[row + 1][col + 1].equals("|")
								&& Dots.matr[row + 2][col].equals(" ") && Dots.matr[row - 2][col].equals("-")) {
							Dots.matr[row + 2][col] = "-";
							System.out.println("Row1 " + row + " Col1 " + col);
							moves(row + 2, col);
							moves(row + 3, col );
						}
						if (Dots.matr[row - 1][col + 1].equals("|") && Dots.matr[row - 1][col - 1].equals("|")
								&& Dots.matr[row - 2][col].equals(" ") && Dots.matr[row + 2][col].equals("-")) {
							Dots.matr[row - 2][col] = "-";
							System.out.println("Row2 " + row + " Col2 " + col);

							moves(row - 2, col);
							moves(row - 3, col );
						}

					}
//					moves(row-1 , col - 1);
//					moves(row-1 , col + 1);
//					moves(row+1 , col + 1);
//					moves(row+1 , col - 1);
				}
				if (row % 2 != 0 && col % 2 == 0) {
					Dots.matr[row][col] = "|";
					Dots.countEnd++;
					if (col > 0 && col < Dots.matr[0].length - 1 && row > 0 && row < Dots.matr.length - 1) {
						if (Dots.matr[row - 1][col + 1].equals("-") && Dots.matr[row + 1][col + 1].equals("-")
								&& Dots.matr[row][col + 2].equals(" ") && Dots.matr[row][col - 2].equals("|")) {
							Dots.matr[row][col + 2] = "|";
							System.out.println("Row3 " + row + " Col3 " + col);

							moves(row, col + 2);
							moves(row , col + 3);
						}
						if (Dots.matr[row + 1][col - 1].equals("-") && Dots.matr[row - 1][col - 1].equals("-")
								&& Dots.matr[row][col - 2].equals(" ") && Dots.matr[row][col + 2].equals("|")) {
							Dots.matr[row][col - 2] = "|";
							System.out.println("Row4 " + row + " Col4 " + col);
							moves(row, col - 2);
							
						}
					}
//					moves(row-1 , col - 1);
//					moves(row-1 , col + 1);
//					moves(row+1 , col + 1);
//					moves(row+1 , col - 1);
				}

				System.out.println("\nCount End: " + Dots.countEnd + "\n");
			}
		}
		// else {
		// moves(row, col);
		// }

	}
}

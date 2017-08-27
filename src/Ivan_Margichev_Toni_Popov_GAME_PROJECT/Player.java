package Ivan_Margichev_Toni_Popov_GAME_PROJECT;

import java.util.Scanner;

public class Player extends GamePlayers {
	private Scanner in;

	Player(String name) {
		super(name);
	}

	protected void chooseGame() {
		in = new Scanner(System.in);

		System.out.println("1.Dots");
		System.out.println("2.Minessweeper");
		System.out.println("3.Sea chess");
		System.out.println("4.Fox and the hunter");
		System.out.println("0.Exit");
		switch (in.nextInt()) {
		case 1:
			Dots dots = new Dots(this);
			dots.play();
			break;
		case 2:
			Minesweeper mine = new Minesweeper(this);
			mine.play();
			break;
		case 3:
			Seachess sea = new Seachess(this);
			sea.play();
			break;
		case 4:
			FoxAndHunter hunter = new FoxAndHunter(this);
			hunter.play();
			break;
		case 0:
			break;
		default:
			System.out.println("Please enter a number between 1 and 4 ");
			chooseGame();
			break;
		}
	}
}

package Ivan_Margichev_Toni_Popov_GAME_PROJECT;

public interface IGame {
	void printBoard();

	void printScore();

	void checkForWin();

	void play();
	
	void moves (GamePlayers player);
	
}

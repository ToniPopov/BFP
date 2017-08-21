package Ivan_Margichev_Toni_Popov_GAME_PROJECT;

public abstract class GamePlayers {
	protected String name;
	protected int points;
	protected int wins;
	protected char symbol;
	
	GamePlayers(String name){
		if(name != null && !name.isEmpty()){
			this.name = name;
		}
	}
}

package dungeonRooms;

import game.EnemyEncounter;
import game.PetCat;
import game.PlayerInfo;

public class Wall extends Room {

	public Wall() {
		super(false);
	}

	@Override
	public void inRoom(PlayerInfo p, PetCat r, EnemyEncounter e) {

		System.out.print("You shouldn't be here...");
		System.out.println("You see " + r.getName() + " running through a crack in the wall");
	}

	@Override
	public String description() {
		return "A stone wall blocks your path. You can't go past it.";
	}

	@Override
	public char getRoomID() {
		// TODO Auto-generated method stub
		return 'x';
	}

}

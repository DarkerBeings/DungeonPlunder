package dungeonRooms;

import game.EnemyEncounter;
import game.PetCat;
import game.PlayerInfo;

public class StartRoom extends Room {

	public StartRoom() {
		super(true);
	}

	@Override
	public void inRoom(PlayerInfo p, PetCat r, EnemyEncounter e) {
		System.out.println("This is where you entered. There is nothing for you to do here.");

	}

	@Override
	public String description() {
		return "Hey, this way is where you entered!";
	}

	@Override
	public char getRoomID() {
		return 'S';
	}

}

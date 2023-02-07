package dungeonRooms;

import game.Dungeon;
import game.EnemyEncounter;
import game.PetCat;
import game.PlayerInfo;

public class TreasureRoom extends Room {

	public TreasureRoom() {
		super(true);
	}

	@Override
	public void inRoom(PlayerInfo p, PetCat r, EnemyEncounter e) {
		if (!super.hasVisited()) {
			Dungeon.treasureRoom(p, 1, "You see a treasure chest in the middle of the room. Hurry and open it!", true);
		} else {
			System.out.println("You've already opened up this treasure chest!");
		}
	}

	@Override
	public String description() {
		String description = "";
		if (super.hasVisited()) {
			description = "There is an opened treasure chest this way";
		} else {
			description = "There is a sparkly object in this direction";
		}
		return description;
	}

	@Override
	public char getRoomID() {
		return '*';
	}

}

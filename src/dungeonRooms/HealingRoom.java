package dungeonRooms;

import game.EnemyEncounter;
import game.PetCat;
import game.PlayerInfo;

public class HealingRoom extends Room {

	public HealingRoom() {
		super(true);
	}

	@Override
	public void inRoom(PlayerInfo p, PetCat r, EnemyEncounter e) {

		if (timesVisited() < 4) {
			System.out.println(
					"You stumble into a Great Fairy Fountain. The Great Fairy graciously restores your health");
			p.restoreHealth(p.getMaxHealth());
		} else {
			System.out.println("You stumble into the Great Fairy's Fountain again.");
			System.out
					.println("It seems her generosity is at it's limit though, as she refuses to restore your health");
		}

	}

	@Override
	public String description() {
		return "A narrow path is this way. It doesn't seem like enemies would go there.";
	}

	@Override
	public char getRoomID() {
		// TODO Auto-generated method stub
		return '%';
	}

}

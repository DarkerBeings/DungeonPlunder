package dungeonRooms;

import enemies.BasicBoss;
import game.EnemyEncounter;
import game.PetCat;
import game.PlayerInfo;

public class BossRoom extends Room {

	BasicBoss b;

	public BossRoom() {
		super(true);

	}

	@Override
	public void inRoom(PlayerInfo p, PetCat r, EnemyEncounter e) {
		/**
		 * The boss isn't assigned to the room until the room is entered. This is
		 * because the floor class doesn't have an instance of EnemyEncounter and I
		 * don't want to make it so it does.
		 */
		if (!super.hasVisited()) {
			b = e.getBoss();
			e.BossBattle(p, b);
		} else {
			System.out.println(b.revisitRoom());
		}
	}

	@Override
	public String description() {
		if (!super.hasVisited())
			return "A horrible foreboding feeling from the room in this direction haunts your spine";
		else
			return "The carcass of a horrible enemy lies this way.";
	}

	@Override
	public char getRoomID() {
		return 'B';
	}

}

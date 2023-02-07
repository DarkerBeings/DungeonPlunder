package dungeonRooms;

import game.EnemyEncounter;
import game.PetCat;
import game.PlayerInfo;

public class Stairs extends Room {

	public Stairs() {
		super(true);
	}

	@Override
	public void inRoom(PlayerInfo p, PetCat r, EnemyEncounter e) {
		System.out.println(
				"You come across a large set of doors, with a massive lock holding it shut. You'll need a big key to open it");
	}

	public boolean moveFloor(PlayerInfo p) {
		boolean move = p.hasBosskey();
		p.useBossKey();
		return move;
	}

	@Override
	public String description() {

		return "You see a set of stairs leading further into the depths ahead";
	}

	@Override
	public char getRoomID() {
		// TODO Auto-generated method stub
		return '#';
	}

}

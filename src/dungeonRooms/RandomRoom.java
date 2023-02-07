package dungeonRooms;

import game.EnemyEncounter;
import game.PetCat;
import game.PlayerInfo;

public class RandomRoom extends Room {

	public RandomRoom() {
		super(true);
	}

	@Override
	public void inRoom(PlayerInfo p, PetCat r, EnemyEncounter e) {
		int chance = super.getRandom().nextInt(100);
		if (chance < 50) {
			e.BattleRoll(p);
		} else if (chance < 60) {
			// Fall into trap!
			int damage = p.getMaxHealth() / 10;
			System.out.println("As you stumble through the dimly lit dungeon, you trip a trap!");
			System.out.println("You take " + damage + " Damage");
			p.takeDamage(damage);
		} else if (chance < 70) {
			System.out.println("You come across a health fruit! 20 HP is restored");
			p.restoreHealth(20);
		} else if (chance < 85) {
			r.seeCat(p);
		} else if (chance < 100) {
			System.out.println("You march on without anything of significance happening.");
		}

	}

	@Override
	public String description() {
		return "The path leads onwards. ";
	}

	@Override
	public char getRoomID() {
		return '.';
	}

}

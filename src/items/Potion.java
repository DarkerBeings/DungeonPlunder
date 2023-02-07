package items;

import enemies.BasicEnemy;
import game.PlayerInfo;

public class Potion extends BasicItem {

	public Potion() {
		super("Potion");
	}

	@Override
	protected void effect(PlayerInfo p) {
		p.restoreHealth(p.getMaxHealth() / 2);
	}

	@Override
	public void printTreasure(boolean flavor) {
		System.out.println("You got a Potion. Use it to restore half of your max health.");
	}

	@Override
	protected String flav() {
		return "You drink the Potion. Half of your health was restored. \nWhat is this made out of anyways? Best not think about it";
	}

	@Override
	protected void effect(PlayerInfo p, BasicEnemy[] e) {
		effect(p);
	}

}

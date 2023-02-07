package items;

import enemies.BasicEnemy;
import game.PlayerInfo;

public class SharpeningStone extends BasicItem {

	public SharpeningStone() {
		super("Sharpening Stone");
	}

	@Override
	protected void effect(PlayerInfo p) {
		p.increaseAttack(2);
	}

	@Override
	public void printTreasure(boolean flavor) {
		System.out.println("You got a Sharpening Stone. You will deal a little more damage to enemies now.");
		if (flavor)
			System.out.println("What kind of weapon are you using anyways?");
	}

	@Override
	protected String flav() {
		return "You sharpen your weapon with the stone. Attack increased by 2.";
	}

	@Override
	protected void effect(PlayerInfo p, BasicEnemy[] e) {
		effect(p);
	}

}

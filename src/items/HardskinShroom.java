package items;

import enemies.BasicEnemy;
import game.PlayerInfo;

public class HardskinShroom extends BasicItem {

	public HardskinShroom() {
		super("Hardskin Mushroom");
	}

	@Override
	protected void effect(PlayerInfo p) {
		p.increaseDefense(1);
		
	}

	@Override
	public void printTreasure(boolean flavor) {
//		System.out.println("You got a Pretty Hairpin. You're not sure if putting it on makes you more pretty or more terrifying");
//		System.out.println("Either way, enemies will deal a little less damage to you when put on.");	
		System.out.println("You found a Hardskin Mushroom. Eating it will.. harden your skin.");
		if (flavor)
			System.out.println("Is the extra defense worth eating a wild mushroom?");
	}

	@Override
	protected String flav() {
		return "You eat the Hardskin Mushroom... \n" + "Huh, it tastes like chicken. Your defense increased by 1.";
		// "You put on the Pretty Hairpin. Defense increased by 1... but is it worth the
		// look?";
	}

	@Override
	protected void effect(PlayerInfo p, BasicEnemy[] e) {
		effect(p);
	}

}

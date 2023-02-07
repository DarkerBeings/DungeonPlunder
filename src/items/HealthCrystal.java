package items;

import enemies.BasicEnemy;
import game.PlayerInfo;

public class HealthCrystal extends BasicItem {

	public HealthCrystal() {
		super("Health Crystal");
	}

	@Override
	protected String flav() {
		return "";
	}

	@Override
	protected void effect(PlayerInfo p) {
		e(p);
	}

	@Override
	protected void effect(PlayerInfo p, BasicEnemy[] e) {
		e(p);
	}

	@Override
	public void printTreasure(boolean flavor) {
		System.out
				.println("You found a Health Crystal. When you break one of these crystals your max health increases.");
	}

	private void e(PlayerInfo p) {
		p.increaseMaxHealth(10);
		System.out.println(
				"The crystal shatters in your hands leaving you feeling... healthier. Your Maximum Health increased by 10");
	}

}

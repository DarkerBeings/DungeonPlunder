package items;

import enemies.BasicEnemy;
import game.Battle;
import game.PlayerInfo;

public class CritKnife extends BasicItem {

	public CritKnife() {
		super("CritKnife");
	}

	@Override
	public void printTreasure(boolean flavor) {
		System.out.println("You got a CritKnife. Use it in battle to instantly kill an enemy.");
	}

	@Override
	protected void effect(PlayerInfo p, BasicEnemy[] list) {
		BasicEnemy e = Battle.findEnemy(list);

		if (e.isBoss()) {
			int damageTaken = (e.getHealth() / 4);
			damageTaken = Math.min(damageTaken, 50);
			System.out.println("Dealt " + damageTaken + " damage");
			e.takeDamage(damageTaken);
			return;
		}

		e.takeDamage(e.getHealth());
		System.out.println(e.getName() + "HP: " + e.getHealth());

	}

	@Override
	protected String flav() {
		return "Critical Hit!";
	}

	@Override
	protected void effect(PlayerInfo p) {
		System.out.println("I dont think you are supposed to be here...");
	}

	@Override
	public void use(PlayerInfo p) {
		System.out.println("You can't use that here!");
	}

}

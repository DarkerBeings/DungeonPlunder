package enemies;

public class KingGoblin extends BasicBoss {

	public KingGoblin() {
		super("King Goblin", 13, 75, 45);
	}

	@Override
	public String BossIntro() {
		return "As you move forward a horrible stench fills the air, You sense something foul ahead. \n"
				+ "At the epicenter of the rancid odor you find the King Goblin sitting on a throne of bones \n"
				+ "He gets up from his throne and readies himself for battle.";
	}

	@Override
	public String BossDeath() {
		return "The false king falls to the floor, writhing in pain. \n "
				+ "When his roars subside you notice the heap of treasure he was guarding, as well as large key for some type of door. \n";
	}

	@Override
	public String revisitRoom() {
		return "You see the rotting corpse of the King Goblin. Best leave it alone.";
	}

}

package enemies;

public abstract class BasicBoss extends BasicEnemy {

	public BasicBoss(String name, int attack, int health, int xp) {
		super(name, attack, health, xp);
	}

	public abstract String BossIntro();

	public abstract String BossDeath();

	@Override
	public boolean isBoss() {
		return true;
	}

	public abstract String revisitRoom();
}

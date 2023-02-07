package enemies;

public abstract class BasicEnemy {

	private int health;

	private String name;

	private int attack;

	private boolean alive;

	private int xp;

	public BasicEnemy(String name, int attack, int health, int xp) {
		this.name = name;
		this.attack = attack;
		this.health = health;
		alive = true;
		this.xp = xp;

	}

	public void takeDamage(int damage) {
		health -= damage;
		health = Math.max(health, 0);
		if (health <= 0) {
			alive = false;
		}
	}

	public int getAttack() {
		return attack;
	}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public boolean isAlive() {
		return alive;
	}

	// Print out name and health
	public String status() {
		String stat = name + ".  " + name + "HP: " + health;
		if (!alive)
			stat += ", It's dead!";
		return stat;
	}

	public boolean isBoss() {
		return false;
	}

	public int getXp() {
		return xp;
	}

}

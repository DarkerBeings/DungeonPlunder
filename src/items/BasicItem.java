package items;

import enemies.BasicEnemy;
import game.PlayerInfo;

public abstract class BasicItem {

	private int number;

	private String name;

	public BasicItem(String name) {
		number = 1;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void add() {
		number++;
	}

	protected void subtract() {
		number--;
	}

	public int getNumber() {
		return number;
	}

	private boolean check() {
		if (number == 0) {
			System.out.println("You don't have any left!");
			return false;
		}
		subtract();
		return true;
		// System.out.println(flav());
	}

	public void use(PlayerInfo p) {
		if (check()) {
			effect(p);
			System.out.println(flav());
		}
	}

	public void battleUse(PlayerInfo p, BasicEnemy[] e) {
		if (check()) {
			effect(p, e);
			System.out.println(flav());

		}
	}

	/**
	 * Flavor text for when an item is used
	 * 
	 * @return flavor text for the item when it is used
	 */
	protected abstract String flav();

	/**
	 * The effect of the item when it is used outside of battle
	 * 
	 * @param p the player using the item
	 */
	protected abstract void effect(PlayerInfo p);

	/**
	 * The effect of the item when it is used inside of a battle
	 * 
	 * @param p the player using the item
	 * @param e the array of enemies the player is fighting
	 */
	protected abstract void effect(PlayerInfo p, BasicEnemy[] e);

	/**
	 * Prints out the name of the item and how many are left
	 */
	public void print() {
		System.out.println(name + "s: " + number);
	}

	public boolean equals(BasicItem b) {
		return (name.equals(b.getName()));
	}

	/**
	 * Prints out the text when you get an item
	 * 
	 * @param flavor option to add more flavor text
	 */
	public abstract void printTreasure(boolean flavor);

}

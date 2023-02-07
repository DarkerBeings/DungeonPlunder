package game;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import dungeonRooms.Floor;
import enemies.BasicEnemy;
import items.BasicItem;

public class PlayerInfo {

	private ArrayList<BasicItem> itemArr;

	private int maxHp;

	private int hp;

	private int attack;

	private int defense;

	private boolean hasBossKey;

	private boolean beatGame;

	private int turnsSinceRested;

	private int experience;

	private int level;

	private Random rand;

	public PlayerInfo() {
		itemArr = new ArrayList<BasicItem>();
		maxHp = 100;
		hp = 100;
		attack = 7;
		defense = 0;
		beatGame = false;
		turnsSinceRested = 5;
		experience = 0;
		level = 1;
		rand = new Random(2);
	}

	public int getHealth() {
		return hp;
	}

	public int getMaxHealth() {
		return maxHp;
	}

	public int getAttack() {
		return attack;
	}

	/**
	 * Used for when the player takes damage.
	 * 
	 * @param damage how much damage the player is taking.
	 */
	public int takeDamage(int damage) {
		int hit = damage - defense;
		hit = Math.max(hit, 1);
		hp -= hit;
		hp = Math.max(hp, 0);
		return hit;
	}

	public void restoreHealth(int amount) {
		hp += amount;
		hp = Math.min(hp, maxHp);
	}

	public int getDefense() {
		return defense;
	}

	public void increaseDefense(int increase) {
		defense += increase;
	}

	public void increaseAttack(int increase) {
		attack += increase;
	}

	public void increaseMaxHealth(int increase) {
		maxHp += 10;
		restoreHealth(10);
	}

	public int turnsSinceRest() {
		return turnsSinceRested;
	}

	public void increaseTurnsSinceRest() {
		turnsSinceRested++;
	}

	public void rested() {
		turnsSinceRested = 0;
	}

	/**
	 * Used to find if the player has beat the game.
	 * 
	 * @return boolean value whether or not the player has beaten the game
	 */
	public boolean hasBeatGame() {
		return beatGame;
	}

	/**
	 * Modifier method for when the player beats the game.
	 */
	public void beatGame() {
		beatGame = true;
	}

	/**
	 * Used to check if a player has a boss key.
	 * 
	 * @return
	 */
	public boolean hasBosskey() {
		return hasBossKey;
	}

	/**
	 * Used when a player uses a boss key when going down stairs.
	 */
	public void useBossKey() {
		hasBossKey = false;
	}

	/**
	 * For when a player get a boss key after beating a boss.
	 */
	public void getBossKey() {
		hasBossKey = true;
	}

	public void addItem(BasicItem item) {
		for (int i = 0; i < itemArr.size(); i++) {
			BasicItem e = itemArr.get(i);
			if (e.equals(item)) {
				e.add();
				return;
			}
		}

		itemArr.add(item);
	}

	public int totalExperience() {
		return experience;
	}

	private int xpToLevelUp() {
		return 5 * level * level;
	}

	private void levelUp() {
		// System.out.println("XP TO LEVEL UP: " + xpToLevelUp());
		while (xpToLevelUp() - experience <= 0) {
			experience = experience - xpToLevelUp();
			level++;
			System.out.println("Level Up! ");

			switch (rand.nextInt(3)) {
			case 0:
				attack += 2;
				System.out.println("Attack + 2");
				break;
			case 1:
				defense += 1;
				System.out.println("Defense + 1");
				break;
			case 2:
				maxHp += 15;
				hp += 15;
				System.out.println("Max Hp + 15");
				break;

			}
		}
	}

	public void getExp(int xp) {
		experience = experience + xp;
		levelUp();
	}

	/**
	 * helper method that displays the player's stats and items in their inventory.
	 * 
	 * @return returns a number that tells another method which option was chosen.
	 */
	private int printInventory() {
		System.out.println("Current Hp: " + hp);
		System.out.println("Max Hp:" + maxHp);
		System.out.println("Current attack power: " + attack);
		System.out.println("Current defense points: " + defense);
		System.out.println("Level: " + level);
		System.out.println("Experience: " + experience);
		System.out.println("Experience to level up: " + (xpToLevelUp()) + " exp");

		if (itemArr.size() == 0)
			System.out.println("Your item bag is empty.");

		int i = 1;
		int sum = 0;
		for (BasicItem e : itemArr) {
			System.out.print("[" + i + "] ");
			e.print();
			i++;
			sum += e.getNumber();
		}

		System.out.println("");
		while (true) {
			try {
				System.out.println("What would you like to do?");
				System.out.println("[1] Use item, [2] View Map, [3] Return");
				int option = Startup.getScan().nextInt();
				System.out.println("----------------------");

				if (option == 1 && sum == 0) {

					System.out.println("Use what item?? Your item bag is empty!");
					return 3;
				}

				return option;

			} catch (InputMismatchException e) {
				Startup.getScan().nextLine();
				System.out.println("That isn't a correct input. Now you gotta start over.");
				System.out.println("");
			} catch (IndexOutOfBoundsException e1) {
				Startup.getScan().nextLine();
				System.out.println("That wasn't a correct option...");
			}
		}

	}

	/**
	 * For accessing inventory when not in battle.
	 * 
	 * @param p the player whose inventory will be displayed
	 */
	public void Inventory(PlayerInfo p, Floor f) {
		// prints out the items currently in the player's inventory that are usable
		// also prints out current hp and stats

		// add in use item function
		try {
			int option = printInventory();

			if (option == 1) {

				System.out.println("Which item do you want to use?");
				option = Startup.getScan().nextInt();
				System.out.println("----------------------");

				itemArr.get(option - 1).use(p);
			} else if (option == 2) {
				f.printMap();
			}

			else
				return;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Invalid Index");
		}
	}

	/**
	 * For accessing inventory during a battle. Necessary because some items require
	 * an enemy to be used on, and don't work outside of battle.
	 * 
	 * @param p     The player whose inventory will be displayed
	 * @param enemy a given array of enemies.
	 */
	public void Inventory(PlayerInfo p, BasicEnemy[] enemy, Floor f) {

		int option = printInventory();
		try {
			if (option == 1) {
				System.out.println("Which item do you want to use?");
				option = Startup.getScan().nextInt();
				System.out.println("----------------------");

				itemArr.get(option - 1).battleUse(p, enemy);
			} else if (option == 2) {
				f.printMap();
			}

			else
				return;

		} catch (IndexOutOfBoundsException e) {
			System.out.println("Invalid Index");
		}

	}

}

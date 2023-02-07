package game;

import java.util.InputMismatchException;

import dungeonRooms.Floor;
import enemies.BasicEnemy;

public class Battle {
	/**
	 * Simulates a battle between a player and an array of enemies. A battle has
	 * turns, a turn ends when all enemies have attacked. Enemies attack one at a
	 * time. after an enemy attacks, the player gets to attack. Does not have a
	 * chance for treasure as it could be different for different types of fights.
	 * 
	 * @param p The player in the fight
	 * @param e Array of enemies the player is fighting
	 * @param f the current floor the player is on. For use in battle to access the
	 *          map.
	 */
	public static void fight(PlayerInfo p, BasicEnemy[] e, Floor f) {
		int turns = 0;
		int damageTaken = 0;

		System.out.println("Enemies! Watch out!");
		for (BasicEnemy i : e) {
			System.out.println(i.status());
		}

		while (p.getHealth() > 0 && sumEnemyHealth(e) > 0) {
			try {

				for (BasicEnemy i : e) {
					if (i.isAlive()) {
						// player takes a turn
//						//player gets to make an option if they open up their inventory
						System.out.println("\nHP: " + p.getHealth());
						System.out.println("What will you do...");
						System.out.println("Attack [1], Access Inventory [2]");
						int move = Startup.getScan().nextInt();
						System.out.println("----------------------");

						if (move > 2 || move < 1)
							throw new InputMismatchException();

						if (move == 1) {

							BasicEnemy victim = findEnemy(e);

							if (victim.isAlive()) {
								victim.takeDamage(p.getAttack());
								System.out.println("You lunge at the " + victim.status());
							} else {
								System.out.println("Why would you pick that enemy? It's already dead!");
							}
						} else {
							p.Inventory(p, e, f);
						}
						turns++;

						// if the next enemy attacking is alive
						if (i.isAlive()) {
							// enemy takes a turn
							System.out.println("");
							damageTaken += p.takeDamage(i.getAttack());
							System.out.println("Attacked by " + i.getName() + ". You took "
									+ Math.max(i.getAttack() - p.getDefense(), 1) + " damage.");
						}
					}
				}

			} catch (InputMismatchException exeption) {
				System.out.println("That isn't a correct input... c'mon man");
				System.out.println("Redo that turn.");
				Startup.getScan().nextLine();
			}

		}

		if (p.getHealth() > 0) {
			System.out.println("Battle over. You win! Type anything to continue.");
			Startup.getScan().next();
			Startup.getScan().nextLine();
		} else {
			System.out.println("\n");
			System.out.println("Beaten and battered, you were hit on the head by an enemy.");
			return;
		}

		int xpGained = 0;
		for (BasicEnemy i : e) {
			xpGained += i.getXp();
		}

		System.out.println("Battle Info:");
		System.out.println("Turns: " + turns);
		System.out.println("Damage Taken: " + damageTaken);
		System.out.println("XP gained: " + xpGained);
		p.getExp(xpGained);

	}

	/**
	 * Use for selecting an enemy out of an array of enemies.
	 * 
	 * @param e array of 1 or more enemies
	 * @return Enemy that is selected out of array
	 */
	public static BasicEnemy findEnemy(BasicEnemy[] e) {
		// not 100% sure why it needs a while loop, but it freaks out at me if I try to
		// take it away.
		while (true) {

			try {

				if (e.length < 2) {
					// System.out.println("Attacking " + e[0].status());
					return e[0];
				}

				System.out.println("Who will you attack?");
				int count = 1;

				for (BasicEnemy i : e) {
					System.out.println("[" + count + "] " + i.status());
					count++;
				}

				int move = Startup.getScan().nextInt();
				BasicEnemy victim = e[move - 1];
				return victim;

			}

			catch (NumberFormatException ex) {
				System.out.println("Wrong type of input, silly!");
			}

			catch (IndexOutOfBoundsException e1) {
				System.out.println("Thats not a correct number...");
			} catch (InputMismatchException e2) {
				System.out.println("Thats not a valid input... try that again.");
				System.out.println("");
				Startup.getScan().next();
			}

		}

	}

	/**
	 * finds the sum of the health of an array of enemies
	 * 
	 * @param e an array of enemies.
	 * @return the sum of the health of the array of enemies
	 */
	private static int sumEnemyHealth(BasicEnemy[] e) {
		int health = 0;
		for (BasicEnemy i : e) {
			health += i.getHealth();
		}

		return health;
	}
}

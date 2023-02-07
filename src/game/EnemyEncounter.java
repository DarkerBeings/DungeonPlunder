package game;

import java.util.Random;

import dungeonRooms.Floor;
import enemies.*;


/**
 * Enemy encounter is the container for all of the occurances of finding and
 * fighting enemies. The actual fight method is written in the Battle class.
 * This class has everything else surrounding the battles. Such information
 * includes which enemies are fought based on which floor the player is on
 * 
 * @author eriks
 *
 */
public class EnemyEncounter {

	private int bossFights;

	private Random randy;

	private Floor floor;

	private BasicBoss[] bossList;

	public EnemyEncounter() {
		bossFights = 0;
		randy = new Random();
		bossList = new BasicBoss[] { new KingGoblin(), new QueenArachnid(), new FailedLabExperiment(), new Dragon() };
	}

	public BasicBoss getBoss() {
		return bossList[bossFights];
	}

	public void BossBattle(PlayerInfo player, BasicBoss e) {

		BasicBoss[] b = new BasicBoss[] { e };

		System.out.println(e.BossIntro());

		Battle.fight(player, b, floor);

		if (b[0].getHealth() <= 0) {
			System.out.println(b[0].BossDeath());

			Dungeon.treasureRoom(player, 4, "The Boss dropped their hoard of treasure!", false);
			player.getBossKey();

			bossFights += 1;
			// bossFights = Math.min(bossFights, 5);
		}

	}

	/**
	 * Puts a player in a battle. The enemies that the player will fight is based on
	 * how many bosses have been defeated.
	 * 
	 * @param player a given player in the game to be put in a battle
	 */

	public void BattleRoll(PlayerInfo player) {
		try {
			// 2D array of enemies??
			// Enemies that could be encountered changes on what level of the dungeon you
			// are
			// {new Goblin()}, {new Arachnid()}, {new BadBat()},
			// TODO fix this file, it got corrupted and I had to rollback to an outdated
			// version.
			int[] numEnemiesArr = new int[] { 1, 1, 1, 1, 1, 2, 2, 2, 3, 3 };
			int numEnemies = numEnemiesArr[randy.nextInt(10)];

			// BasicEnemy[][] e = new BasicEnemy[][] {{new Goblin()}, {new Arachnid()}, {new
			// EchoBat()}, {new Goblin(), new Arachnid()}};
			int[] enemyList = new int[] { 1, 2, 3 };

			switch (bossFights) {
			case 1:
				enemyList = new int[] { 4, 1, 2 };
				break;
			case 2:
				enemyList = new int[] { 6, 5, 7, 4 };
				break;
			case 3:
				enemyList = new int[] { 5, 6, 7, 8 };
			}

			BasicEnemy[] enemies = new BasicEnemy[numEnemies];
			for (int i = 0; i < numEnemies; i++) {
				enemies[i] = findEnemy(enemyList[randy.nextInt(enemyList.length)]);
			}

			Battle.fight(player, enemies, floor);

			if (player.getHealth() > 0) {
				Random rand = new Random();
				if (rand.nextInt(100) < 40)
					Dungeon.treasureRoom(player, 1, "The enemies dropped a treasure chest!", true);
			}

		}

		catch (IndexOutOfBoundsException a) {
			System.out.println("Boss Index out of bounds, skipping the fight lol");
		}
	}

	/**
	 * Finds an enemy based off a given enemy index num
	 * 
	 * @param num The index of a type of enemy
	 * @return new enemy
	 */
	private BasicEnemy findEnemy(int num) {
		BasicEnemy newEnemy = null;
		switch (num) {
		case 1:
			newEnemy = new Goblin();
			break;
		case 2:
			newEnemy = new Arachnid();
			break;
		case 3:
			newEnemy = new EchoBat();
			break;
		case 4:
			newEnemy = new PoisonousSludge();
			break;
		case 5:
			newEnemy = new CrystalGolem();
			break;
		case 6:
			newEnemy = new BlueGoblin();
			break;
		case 7:
			newEnemy = new WanderingSpirit();
			break;
		case 8:
			newEnemy = new KingGoblin();
			break;
		}
		return newEnemy;
	}
}

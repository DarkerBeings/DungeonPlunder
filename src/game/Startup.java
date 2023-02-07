package game;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Startup {

	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		
		try {
			exposition();
			int numMaps = getScan().nextInt();
			PlayerInfo player = new PlayerInfo();
			Dungeon dunn = new Dungeon(player, numMaps);
			dunn.state();
	
			if (player.getHealth() <= 0) {
				deathSentence();
			} else {
				winningSituation();
			}
			
			}
		catch (InputMismatchException e) {
			System.out.println(e);
		}

		getScan().close();

	}

	/**
	 * Helper method holding the dialogue when your player dies.
	 */
	private static void deathSentence() {
		System.out.println("Your eyelids begin to feel heavy as blood starts to rush from your head. "
				+ "You're very tired... shutting your eyes for a little bit won't hurt, right?");
		System.out.println("-----Game_Over-----");
	}

	/**
	 * Helper method holding the dialogue when you successfully make it through the
	 * dungeon
	 */
	private static void winningSituation() {
		System.out.println("After you walk through the set of stairs, you find a floor without any enemies.");
		System.out.println("After walking around, you find a house surrouned by a rock garden.");
		System.out.println(
				"You see the cat you've found along the way make its way to the house. It apparently lives there.");
		System.out.println(
				"Unsure of what else you can do, you decide that staying here is better than trying to explore more");
		System.out.println("-----Game End, Thank you for playing!-----");
	}

	/**
	 * Helper method holding the dialogue when first booting up the game.
	 */
	private static void exposition() {
		System.out.print("\r\n"
				+ " ______   __   __  __    _  _______  _______  _______  __    _    _______  ___      __   __  __    _  ______   _______  ______   \r\n"
				+ "|      | |  | |  ||  |  | ||       ||       ||       ||  |  | |  |       ||   |    |  | |  ||  |  | ||      | |       ||    _ |  \r\n"
				+ "|  _    ||  | |  ||   |_| ||    ___||    ___||   _   ||   |_| |  |    _  ||   |    |  | |  ||   |_| ||  _    ||    ___||   | ||  \r\n"
				+ "| | |   ||  |_|  ||       ||   | __ |   |___ |  | |  ||       |  |   |_| ||   |    |  |_|  ||       || | |   ||   |___ |   |_||_ \r\n"
				+ "| |_|   ||       ||  _    ||   ||  ||    ___||  |_|  ||  _    |  |    ___||   |___ |       ||  _    || |_|   ||    ___||    __  |\r\n"
				+ "|       ||       || | |   ||   |_| ||   |___ |       || | |   |  |   |    |       ||       || | |   ||       ||   |___ |   |  | |\r\n"
				+ "|______| |_______||_|  |__||_______||_______||_______||_|  |__|  |___|    |_______||_______||_|  |__||______| |_______||___|  |_|\r\n"
				+ "\r\n"
				+ "---------------------------------------------------------------------------------------------------------------------------------"
				+ "\r\n" + "");
		System.out.println("You are a rogue thief who has been caught by the Royal Guard.");
		System.out.println("As your punishment, you are thrown into a dungeon with the entrance blocked off.");
		System.out.println("With the no apparent way out, the only way to go is forward.");
		System.out.println("Type how many levels you want to explore: \n[1] through [4].");

	}

	public static Scanner getScan() {
		// Using this as a method so we only use one scanner through the whole program.
		// Creating a new scanner will want us to close it, which closes the other
		// scanners that we would've needed.
		return scan;
	}

}

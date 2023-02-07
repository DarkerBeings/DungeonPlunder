package game;

public class Testing {

	public static void main(String[] args) {
		PlayerInfo p = new PlayerInfo();

		for (int i = 0; i < 20; i++) {
			System.out.println(p.totalExperience());
			p.Inventory(p, null);
			p.getExp(1000);

		}

//		for (int i = 1; i < 20; i++) {
//			int maxXP = 5 * i * i;
//			System.out.println(maxXP);
//		}
//		
//		int nextXP = 0;
//		for (int i = 1; i < 20; i++) {
//			int maxXP = 5 * i * i;
//			int out = maxXP - nextXP;
//			nextXP = maxXP;
//			System.out.println(out);
//		}

	}

}

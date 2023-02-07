package enemies;

public class FailedLabExperiment extends BasicBoss {

	public FailedLabExperiment() {
		super("Failed Lab Experiment", 35, 275, 100);
	}

	@Override
	public String BossIntro() {
		String s = "A hideous monster is sitting down in agony. It's limbs seem each be from a different type of animal. \n"
				+ "It suddenly start moving towards you. It doesn't look friendly.";
		return s;
	}

	@Override
	public String BossDeath() {
		String s = "The limbs on the beast start to tear apart from each other. Apparently they weren't too well attached. \n"
				+ "Looking at the carcass of the beast, you find a boss key.";
		return s;
	}

	@Override
	public String revisitRoom() {
		return "The pile of body parts from various animals lays here rotting. Best not to stay here long.";
	}

}

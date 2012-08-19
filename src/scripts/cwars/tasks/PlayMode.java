package scripts.cwars.tasks;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.util.Time;
import scripts.cwars.CastleWars;
import scripts.cwars.util.Util;

/**
 * User: Cory
 * Date: 19/08/12
 * Time: 05:37
 */
public class PlayMode extends Strategy implements Task {

	private CastleWars castleWars;

	public PlayMode(CastleWars castleWars) {
		this.castleWars = castleWars;
	}

	public boolean validate() {
		return Util.inCWars();
	}

	public void run() {
		castleWars.getMode().run();
		Time.sleep(100);
	}
}
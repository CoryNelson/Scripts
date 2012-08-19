package scripts.cwars.tasks;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.util.Time;
import scripts.cwars.util.Util;
import scripts.cwars.util.Validations;

/**
 * User: Cory
 * Date: 19/08/12
 * Time: 03:45
 */
public class JoinTeam extends Strategy implements Task {

	public boolean validate() {
		return Validations.joinTeam();
	}

	public void run() {
		System.out.println("Enter Portal");
		if(Util.WalkToAndClick("Enter", Util.portals)) {
			int time = 0;
			while(validate() && time <= 5000) {
				time += 50;
				Time.sleep(50);
			}
		}
	}
}
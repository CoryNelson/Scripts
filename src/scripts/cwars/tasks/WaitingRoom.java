package scripts.cwars.tasks;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import scripts.cwars.util.Validations;

/**
 * User: Cory
 * Date: 19/08/12
 * Time: 03:58
 */
public class WaitingRoom extends Strategy implements Task {

	private int zammyPortalId = 4390;
	private int saraPortalId = 4389;

	public boolean validate() {
		return Validations.waitingRoom();
	}

	public void run() {
		if(Random.nextInt(1, 4) == 2)
		{
			int i = Random.nextInt(1, 40);
			switch (i){
				case 3:
					Camera.setAngle(Random.nextInt(0, 120));
					break;

				case 15:
					Camera.setPitch(Random.nextInt(30, 70));
					break;

				case 27:
					if(!Walking.isRunEnabled())
						Walking.setRun(true);
					break;

				case 38:
					Mouse.move(Random.nextInt(Mouse.getLocation().x - 150,
							Mouse.getLocation().x + 150),
							Random.nextInt(Mouse.getLocation().y - 150,
									Mouse.getLocation().y + 150));
					break;
			}
		}
	}
}
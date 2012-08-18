package scripts.SpiceLooter.tasks;/**
 * User: Cory
 * Date: 18/08/12
 * Time: 02:31
 */

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.widget.WidgetChild;
import scripts.SpiceLooter.SpiceLooter;
import scripts.SpiceLooter.util.Util;

public class ChaseVermin extends Strategy implements Task {

	WidgetChild chaseVermin = Widgets.get(1188, 13);
	private SpiceLooter spiceLooter;

	public ChaseVermin(SpiceLooter spiceLooter) {
		this.spiceLooter = spiceLooter;
	}

	public boolean validate() {
		NPC cat = spiceLooter.getCat();
		try {
			if(cat != null && cat.getInteracting() != null) {
				int interId = cat.getInteracting().getId();
				int myId = Players.getLocal().getId();
				return interId == myId && !cat.isMoving() && Util.getSpice() == null;
			}
		}
		catch(Exception e) {
			return false;
		}
		return false;
	}

	public void run() {
		NPC cat = spiceLooter.getCat();
		if(cat.interact("Interact-with", cat.getName())) {
			int time = 0;
			while (!chaseVermin.validate() && time <= 3000) {
				time += 50;
				Time.sleep(50);
			}
		}
		if(chaseVermin.validate() && chaseVermin.click(true)) {
			int time = 0;
			while (!cat.isMoving() && time <= 3000) {
				time += 50;
				Time.sleep(50);
			}
		}
	}
}
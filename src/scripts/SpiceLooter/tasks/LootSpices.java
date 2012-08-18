package scripts.SpiceLooter.tasks;/**
 * User: Cory
 * Date: 18/08/12
 * Time: 02:31
 */

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.GroundItem;
import scripts.SpiceLooter.SpiceLooter;
import scripts.SpiceLooter.util.Util;

public class LootSpices extends Strategy implements Task {

	private SpiceLooter spiceLooter;

	public LootSpices(SpiceLooter spiceLooter) {
		this.spiceLooter = spiceLooter;
	}

	public boolean validate() {
		return Util.getSpice() != null;
	}

	public void run() {
		GroundItem spice = Util.getSpice();
		int count = Inventory.getCount(spice.getId());
		if(lootSpice()) {
			int time = 0;
			while (Inventory.getCount(spice.getId()) <= count && time <= 5000) {
				time += 50;
				Time.sleep(50);
			}
		}
	}

	public boolean lootSpice() {
		GroundItem spice = Util.getSpice();
		if (spice != null && spice.isOnScreen()) {
			Mouse.move(spice.getCentralPoint(), 30, 50);
			return (spice.interact("Take", spice.getGroundItem().getName()));
		} else {
			Camera.setPitch(Random.nextInt(40, 90));
			Camera.setAngle(Camera.getMobileAngle(spice) + Random.nextInt(-90, 90));
		}
		return false;
	}
}
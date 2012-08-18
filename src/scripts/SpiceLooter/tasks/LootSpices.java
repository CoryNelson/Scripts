package scripts.SpiceLooter.tasks;/**
 * User: Cory
 * Date: 18/08/12
 * Time: 02:31
 */

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Filter;
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
		return getSpice() != null;
	}

	public void run() {
		GroundItem spice = getSpice();
		int count = Inventory.getCount(spice.getId());
		if(lootSpice()) {
			int time = 0;
			while (Inventory.getCount(spice.getId()) <= count && time <= 5000) {
				time += 50;
				Time.sleep(50);
			}
		}
	}

	public GroundItem getSpice() {
		return GroundItems.getNearest(new Filter<GroundItem>() {
			@Override
			public boolean accept(GroundItem item) {
				if(item == null || item.getGroundItem() == null)
					return false;
				String name = item.getGroundItem().getName();
				return name.contains("spice");
			}
		});
	}

	public boolean lootSpice() {
		GroundItem spice = getSpice();
		if (spice != null && spice.isOnScreen()) {
			Mouse.move(spice.getCentralPoint(), 30, 50);
			return (spice.interact("Take", spice.getGroundItem().getName()));
		} else {
			Util.turnTo(spice);
		}
		return false;
	}
}
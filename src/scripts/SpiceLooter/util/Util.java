package scripts.SpiceLooter.util;

import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.node.GroundItem;

/**
 * User: Cory
 * Date: 18/08/12
 * Time: 03:24
 */
public class Util {

	public static GroundItem getSpice() {
		return GroundItems.getNearest(new Filter<GroundItem>() {
			public boolean accept(GroundItem item) {
				if (item == null)
					return false;
				boolean valid = item.getId() >= 7480 && item.getId() <= 7495;
				return valid;
			}
		});
	}
}

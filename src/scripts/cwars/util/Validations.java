package scripts.cwars.util;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

/**
 * User: Cory
 * Date: 19/08/12
 * Time: 03:49
 */
public class Validations {

	public static WidgetChild timeTilGame = Widgets.get(57, 1);

	public static boolean useBank() {
		return Util.getChest() != null && Inventory.getCount(Util.tokenId) > 0;
	}

	public static boolean joinTeam() {
		if(useBank())
			return false;
		return !timeTilGame.validate() && !timeTilGame.visible();
	}

	public static boolean waitingRoom() {
		if(useBank())
			return false;
		return timeTilGame.validate() && timeTilGame.visible();
	}
}

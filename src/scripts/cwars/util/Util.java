package scripts.cwars.util;

import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.node.SceneObject;

/**
 * User: Cory
 * Date: 19/08/12
 * Time: 03:12
 */
public class Util {

	public static int[] portals = {4388, 4408, 4387};
	public static int tokenId = 4067;
	public static int chestId = 4483;

	public static boolean inCWars() {
		int x = Players.getLocal().getLocation().getX();
		int y = Players.getLocal().getLocation().getY();
		return (y >= 3072 && y < 3135 && x > 2368 && x < 2431);
	}

	public static boolean inSpawnRoom() {
		return Game.getPlane() == 1;
	}

	public static boolean inMiddle() {
		return Game.getPlane() == 2;
	}

	public static boolean inFlagRoom() {
		return Game.getPlane() == 3;
	}

	public static SceneObject getChest() {
		return SceneEntities.getNearest(new Filter<SceneObject>() {
			public boolean accept(SceneObject o) {
				if (o == null || o.getId() != chestId)
					return false;
				return o.getLocation().canReach();
			}
		});
	}

	public static boolean WalkToAndClick(String action, final int... id) {
		SceneObject o = SceneEntities.getNearest(portals);
		if(o == null)
			return false;
		if(o.isOnScreen()) {
			return o.interact(action);
		} else
			Walking.walk(o);
		return false;
	}
}

package scripts.EssMiner.util;

import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

/**
 * User: Cory
 * Date: 18/08/12
 * Time: 08:20
 */
public class Util {

	public static Tile bank = new Tile(3253, 3419, 0);
	public static Tile aubury = new Tile(3253, 3401, 0);
	public static Tile auburyDoor = new Tile(3253, 3398, 0);

	public static int portalId = 39831;
	public static int essenceId = 2491;
	public static int closedDoor = 24381;
	public static int auburyId = 5913;

	public static boolean inBank() {
		int x = Players.getLocal().getLocation().getX();
		int y = Players.getLocal().getLocation().getY();
		return x >= 3250 && y >= 3419 && x <= 3257 && y <= 3423;
	}

	public static boolean outsideAuburyShop() {
		int x = Players.getLocal().getLocation().getX();
		int y = Players.getLocal().getLocation().getY();
		return x >= 3246 && y >= 3396 && x <= 3259 && y <= 3410 && !inAuburyShop();
	}

	public static boolean inAuburyShop() {
		int x = Players.getLocal().getLocation().getX();
		int y = Players.getLocal().getLocation().getY();
		return x >= 3250 && y >= 3399 && x <= 3255 && y <= 3404;
	}

	public static boolean inEssenceMine() {
		return SceneEntities.getNearest(essenceId) != null;
	}

	public static boolean walkToAndClick(String action, int... id) {
		SceneObject o = SceneEntities.getNearest(id);
		if (Calculations.distanceTo(o) > 5 && !Players.getLocal().isMoving())
			Walking.walk(o);
		else {
			if (o != null && o.isOnScreen()) {
				Mouse.move(o.getCentralPoint(), 30, 50);
				return (o.interact(action));
			} else {
				Camera.setPitch(Random.nextInt(40, 90));
				Camera.setAngle(Camera.getMobileAngle(o) + Random.nextInt(-90, 90));
			}
		}
		return false;
	}
}

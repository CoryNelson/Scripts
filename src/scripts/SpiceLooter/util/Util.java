package scripts.SpiceLooter.util;

import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.Locatable;
import org.powerbot.game.api.wrappers.Tile;

/**
 * User: Cory
 * Date: 18/08/12
 * Time: 03:24
 */
public class Util {
	public static int angleTo(Locatable o) {
		try {
			Tile playerTile = Players.getLocal().getLocation();
			Tile tile = o.getLocation();
			double ydif = tile.getX() - playerTile.getX();
			double xdif = tile.getY() - playerTile.getY();
			return (int) (Math.atan2(ydif, xdif) * 180 / Math.PI);
		} catch (Exception e) {
			return -1;
		}
	}

	public static void turnTo(Locatable o) {
		for (int i = 0; i < 20; i++)
			if (o != null && !o.getLocation().isOnScreen()) {
				int cAngle = 360 - Camera.getYaw();
				int angle = 360 - angleTo(o);
				Camera.setAngle(Camera.getYaw() + ((cAngle > angle ? 5 : -5)));
				if (Math.abs(cAngle - angle) <= 50)
					Camera.setPitch(Random.nextInt(20, 300));
			}
	}
}

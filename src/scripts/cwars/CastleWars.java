package scripts.cwars;

import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.bot.event.listener.PaintListener;
import scripts.cwars.mode.Mode;
import scripts.cwars.paint.Paint;
import scripts.cwars.tasks.JoinTeam;
import scripts.cwars.tasks.UseBank;
import scripts.cwars.tasks.WaitingRoom;

import java.awt.*;

/**
 * User: Cory
 * Date: 19/08/12
 * Time: 03:04
 */
@Manifest(authors = {"Cory"}, name = "CastleWars")
public class CastleWars extends ActiveScript implements PaintListener {

	private Mode selectedMode;
	private Paint paint;

	protected void setup() {
		paint = new Paint();
		provide(new UseBank());
		provide(new JoinTeam());
		provide(new WaitingRoom());
	}

	public void onRepaint(Graphics g) {
		paint.draw(g);
	}

	public Mode getMode() {
		return selectedMode;
	}
}

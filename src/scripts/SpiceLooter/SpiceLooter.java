package scripts.SpiceLooter;

import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.bot.event.MessageEvent;
import org.powerbot.game.bot.event.listener.MessageListener;
import org.powerbot.game.bot.event.listener.PaintListener;
import scripts.SpiceLooter.paint.Paint;
import scripts.SpiceLooter.tasks.ChaseVermin;
import scripts.SpiceLooter.tasks.DetectMyCat;
import scripts.SpiceLooter.tasks.LootSpices;

import java.awt.*;


/**
 * User: Cory
 * Date: 18/08/12
 * Time: 02:11
 */
@Manifest(authors = {"Cory"}, name = "SpiceLooter")
public class SpiceLooter extends ActiveScript implements PaintListener, MessageListener {

	private Paint paint;
	private NPC cat;

	public void setup() {
		submit(new DetectMyCat(this));
		provide(new ChaseVermin(this));
		provide(new LootSpices(this));
		paint = new Paint(this);
	}

	public void onRepaint(Graphics g) {
		if(paint != null)
			paint.draw(g);
	}

	public void messageReceived(MessageEvent event) {
		System.out.println(""+event.getMessage());
		System.out.println(""+event.getSender());
		System.out.println(""+event.getId());
	}

	public NPC getCat() {
		return cat;
	}

	public void setCat(NPC cat) {
		this.cat = cat;
	}
}

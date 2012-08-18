package scripts.SpiceLooter;

import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.interactive.Players;
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
		provide(new DetectMyCat(this));
		provide(new ChaseVermin(this));
		provide(new LootSpices(this));
		paint = new Paint(this);
	}

	public void onRepaint(Graphics g) {
		if(paint != null)
			paint.draw(g);
	}

	public void messageReceived(MessageEvent event) {
		if(event.getId() == 2 && isMe(event.getSender())) {
			if(event.getMessage().contains("Hey well done"))
				Paint.Caught++;
			if(event.getMessage().contains("Go on"))
				Paint.Attempts++;
		}
		if(event.getId() == 0 && event.getMessage().contains("The rat manages"))
			Paint.Failed++;
		if(event.getId() == 0 && event.getMessage().contains("Your cat cannot"))
			Paint.Failed++;
	}

	private boolean isMe(String name) {
		return name.equals(Players.getLocal().getName());
	}

	public NPC getCat() {
		return cat;
	}

	public void setCat(NPC cat) {
		this.cat = cat;
	}
}

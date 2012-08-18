package scripts.EssMiner;


import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.bot.event.MessageEvent;
import org.powerbot.game.bot.event.listener.MessageListener;
import org.powerbot.game.bot.event.listener.PaintListener;
import scripts.EssMiner.paint.Paint;

import java.awt.*;

@Manifest(authors = {"Cory"}, name = "EssMiner")
public class EssMiner extends ActiveScript implements PaintListener, MessageListener {

	private Paint paint;

	public void setup() {
		paint = new Paint();
	}

	public void onRepaint(Graphics g) {
		paint.draw(g);
	}

	public void messageReceived(MessageEvent event) {

	}
}
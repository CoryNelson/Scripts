package scripts.SpiceLooter.paint;

import org.powerbot.game.api.methods.input.Mouse;
import scripts.SpiceLooter.SpiceLooter;

import java.awt.*;

/**
* User: Cory
	* Date: 18/08/12
	* Time: 02:31
	*/
public class Paint {

	private SpiceLooter spiceLooter;

	public Paint(SpiceLooter spiceLooter) {
		this.spiceLooter = spiceLooter;
	}

	public void draw(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setStroke(new BasicStroke(2.0f));

		g.setColor(new Color(0, 255, 0, 20));
		if(spiceLooter.getCat() != null) {
			for (Polygon p : spiceLooter.getCat().getBounds()) {
				g.drawPolygon(p);
			}
		}

		g.setColor(new Color(255, 255, 255, 140));
		g.drawLine(0, Mouse.getY(), 763, Mouse.getY());
		g.drawLine(Mouse.getX(), 0, Mouse.getX(), 553);
		g.drawOval(Mouse.getX() - 5, Mouse.getY() - 5, 10, 10);
	}
}

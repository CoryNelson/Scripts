package scripts.SpiceLooter.paint;

import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import scripts.SpiceLooter.SpiceLooter;

import java.awt.*;

/**
* User: Cory
	* Date: 18/08/12
	* Time: 02:31
	*/
public class Paint {

	public static double Failed;
	public static double Caught;
	public static double Attempts;
	private SpiceLooter spiceLooter;

	public Paint(SpiceLooter spiceLooter) {
		this.spiceLooter = spiceLooter;
	}

	public void draw(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setStroke(new BasicStroke(2.0f));

		int width = Game.getDimensions().width;
		int height = Game.getDimensions().height;
		int x = width-460;
		int y = 65;
		g.setColor(new Color(0, 0, 0, 140));
		g.fillRect(x, y, 200, 100);
		g.setColor(Color.WHITE);
		g.drawRect(x, y, 200, 100);

		g.setFont(new Font("Courier New", 1, 15));
		g.drawString("Spice Looter!", x+10, y+20);
		g.setFont(new Font("Courier New", 2, 12));
		g.drawString("Attempts: "+Attempts, x+10, y+40);
		g.drawString("Failed: "+Failed, x+10, y+55);
		g.drawString("Caught: "+Caught, x+10, y+70);
		g.drawString("Success Rate: "+(Attempts > 0 ? (Caught/Attempts)*100 : 0)+"%", x+10, y+85);

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

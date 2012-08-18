package scripts.EssMiner.paint;

import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;

import java.awt.*;

/**
 * User: Cory
 * Date: 18/08/12
 * Time: 08:20
 */
public class Paint {
	public void draw(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setStroke(new BasicStroke(2.0f));

		int width = Game.getDimensions().width;
		int height = Game.getDimensions().height;
		int x = width - 460;
		int y = 65;
		g.setColor(new Color(0, 0, 0, 140));
		g.fillRect(x, y, 200, 110);
		g.setColor(Color.WHITE);
		g.drawRect(x, y, 200, 110);

		g.setFont(new Font("Courier New", 1, 15));
		g.drawString("EssMiner!", x + 10, y + 20);
		g.setFont(new Font("Courier New", 2, 12));


		g.setColor(new Color(255, 255, 255, 140));
		g.drawLine(0, Mouse.getY(), 763, Mouse.getY());
		g.drawLine(Mouse.getX(), 0, Mouse.getX(), 553);
		g.drawOval(Mouse.getX() - 5, Mouse.getY() - 5, 10, 10);
	}
}

package scripts.SpiceLooter.paint;

import org.powerbot.game.api.methods.input.Mouse;
import scripts.SpiceLooter.SpiceLooter;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
* User: Cory
	* Date: 18/08/12
	* Time: 02:31
	*/
public class Paint {

	private SpiceLooter spiceLooter;
	private static LinkedHashMap<String, Object> settings = new LinkedHashMap<String, Object>();

	public static void add(String key, Object value) {
		try {
			if (settings.containsKey(key)) {
				for (Map.Entry<String, Object> entry : settings.entrySet()) {
					if (entry.getKey().equalsIgnoreCase(key))
						entry.setValue(value);
				}
			} else
				settings.put(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Paint(SpiceLooter spiceLooter) {
		this.spiceLooter = spiceLooter;
	}

	public void draw(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setStroke(new BasicStroke(2.0f));

		int y = 70;
		g.setColor(Color.BLACK);
		g.fillRect(5, y - 5, 200, 15 + settings.size() * 15);
		g.setColor(Color.WHITE);
		g.drawRect(5, y - 5, 200, 15 + settings.size() * 15);
		for (Map.Entry<String, Object> entry : settings.entrySet()) {
			g.drawString(entry.getKey() + (entry.getKey().contains("-") ? "" : ": ") + entry.getValue(), 10, y += 15);
		}

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

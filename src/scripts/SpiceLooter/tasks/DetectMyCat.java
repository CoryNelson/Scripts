package scripts.SpiceLooter.tasks;/**
 * User: Cory
 * Date: 18/08/12
 * Time: 02:45
 */

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.interactive.NPC;
import scripts.SpiceLooter.SpiceLooter;

public class DetectMyCat extends Strategy implements Task {

	private SpiceLooter spiceLooter;

	public DetectMyCat(SpiceLooter spiceLooter) {
		this.spiceLooter = spiceLooter;
	}

	public boolean validate() {
		return true;
	}

	public void run() {
		NPC cat = NPCs.getNearest(new Filter<NPC>() {
			@Override
			public boolean accept(NPC npc) {
				if (npc.getInteracting() == null)
					return false;
				int interId = npc.getInteracting().getId();
				int myId = Players.getLocal().getId();
				return interId == myId;
			}
		});
		if (cat != null)
			spiceLooter.setCat(cat);
	}
}
package scripts.EssMiner.tasks;/**
 * User: Cory
 * Date: 18/08/12
 * Time: 08:52
 */

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;
import scripts.EssMiner.util.Util;

public class MineEss extends Strategy implements Task {

	public boolean validate() {
		return !Inventory.isFull();
	}

	public void run() {
		walkToAubury();
		teleportToMine();
		walkToEss();
	}

	private void walkToAubury() {
		if(Util.inBank() && !Players.getLocal().isMoving()) {
			Walking.walk(Util.aubury);
		}
		if(Util.outsideAuburyShop() && !Players.getLocal().isMoving()) {
			SceneObject door = SceneEntities.getAt(Util.auburyDoor);
			if(door != null && door.getId() == Util.closedDoor) {
				if(door.interact("Open")) {
					int time = 0;
					while(SceneEntities.getAt(Util.auburyDoor).getId() == Util.closedDoor && time <= 4000) {
						time += 50;
						Time.sleep(50);
					}
				}
			}
		}
	}

	private void teleportToMine() {
		if(Util.inAuburyShop()) {
			NPC aubury = NPCs.getNearest(Util.auburyId);
			if(aubury.interact("teleport")) {
				int time = 0;
				while(!Util.inEssenceMine() && time <= 4000) {
					time += 50;
					Time.sleep(50);
				}
			}
		}
	}

	private void walkToEss() {
		if(Util.inEssenceMine() && !Players.getLocal().isMoving() && Players.getLocal().getAnimation() == -1) {
			if(Util.walkToAndClick("Mine", Util.essenceId)) {
				int time = 0;
				while(Players.getLocal().getAnimation() == -1 && time <= 4000) {
					time += 50;
					Time.sleep(50);
				}
			}
		}
	}
}
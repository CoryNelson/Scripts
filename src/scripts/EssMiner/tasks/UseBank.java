package scripts.EssMiner.tasks;/**
 * User: Cory
 * Date: 18/08/12
 * Time: 08:42
 */

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.SceneObject;
import scripts.EssMiner.util.Util;

public class UseBank extends Strategy implements Task {

	public boolean validate() {
		return Inventory.isFull();
	}

	public void run() {
		leaveEssenceMine();
		walkToBank();
		useBank();
	}

	private void leaveEssenceMine() {
		if(Util.inEssenceMine() && !Players.getLocal().isMoving()) {
			if(Util.walkToAndClick("Enter", Util.portalId)) {
				int time = 0;
				while(Util.inEssenceMine() && time <= 4000) {
					time += 50;
					Time.sleep(50);
				}
			}
		}
	}

	private void walkToBank() {
		if(Util.inAuburyShop() && !Players.getLocal().isMoving()) {
			SceneObject door = SceneEntities.getAt(Util.auburyDoor);
			if(door != null && door.getId() == Util.closedDoor) {
				if(door.interact("Open")) {
					int time = 0;
					while(SceneEntities.getAt(Util.auburyDoor).getId() == Util.closedDoor && time <= 4000) {
						time += 50;
						Time.sleep(50);
					}
				}
			} else {
				Walking.walk(Util.bank);
			}
		}
	}

	private void useBank() {
		if(Util.inBank() && !Players.getLocal().isMoving()) {
			if(Bank.open()) {
				if(Bank.depositInventory()) {
					int time = 0;
					while(Inventory.isFull() && time <= 4000) {
						time += 50;
						Time.sleep(50);
					}
				}
			}
		}
	}
}
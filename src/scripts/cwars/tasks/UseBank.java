package scripts.cwars.tasks;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Time;
import scripts.cwars.util.Util;
import scripts.cwars.util.Validations;

/**
 * User: Cory
 * Date: 19/08/12
 * Time: 03:14
 */
public class UseBank extends Strategy implements Task {

	public boolean validate() {
		return Validations.useBank();
	}

	public void run() {
		if(Bank.open()) {
			if(Bank.deposit(Util.tokenId, 0)) {
				int time = 0;
				while(validate() && time <= 4000) {
					time += 50;
					Time.sleep(50);
				}
			}
		}
	}
}
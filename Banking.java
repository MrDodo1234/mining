package mining;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;

public class Banking extends Node{

	@Override
	public boolean activate() {
		return 	Inventory.isFull() && VARS.bankingArea.contains(Players.getLocal());
	}

	@Override
	public void execute() {
		VARS.operation = "Banking";
		//when at bank deposit items
		Bank.open();
		Bank.deposit(VARS.INVENTORY_ITEMS, Inventory.getCount(VARS.INVENTORY_ITEMS));
			for(int i = 1617; i <= 1624; i++){
				while(Inventory.contains(i)){
					Bank.deposit(i, Inventory.getCount(i));
				}
			}
		Bank.close();
	}
	
}
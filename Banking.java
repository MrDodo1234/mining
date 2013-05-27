package mining;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;

public class Banking extends Node{

	@Override
	public boolean activate() {
		return Inventory.isFull();
	}

	@Override
	public void execute() {
		//when full inventory go to bank
		//this part works
		while(Inventory.isFull() && !VARS.bankingArea.contains(Players.getLocal())){
			VARS.operation = "Banking";
			VARS.PATH_TO_BANK.traverse();
			
		}
		//when at bank deposit items
		//this part works also
		while(Inventory.isFull() && VARS.bankingArea.contains(Players.getLocal())){
			Bank.open();
			Bank.deposit(VARS.INVENTORY_ITEMS, Inventory.getCount(VARS.INVENTORY_ITEMS));
			Bank.close();
		}
		//when finished banking, or at bank, go to mining site
		//this part, however, does not work properly
		while(!Inventory.isFull() && !VARS.miningArea.contains(Players.getLocal())){
			VARS.operation = "Going to mines";
			VARS.PATH_TO_MINING.traverse();
		}
		
	}
	
}
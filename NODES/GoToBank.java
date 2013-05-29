package mining.NODES;

import mining.VARS;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

public class GoToBank extends Node{

	@Override
	public boolean activate() {
		return Inventory.isFull() && !VARS.bankingArea.contains(Players.getLocal());
	}

	@Override
	public void execute() {
		VARS.failsafeCount = 0;
		while(!VARS.PATH_TO_BANK.getEnd().isOnScreen() && VARS.failsafeCount++ <10){
			VARS.operation = "Going to mines";
			VARS.PATH_TO_BANK.traverse();
			//sleep(Players.getLocal().getLocation() Walking.getDestination());
		}
		
	}

}

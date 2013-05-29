package mining;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

public class GoToMine extends Node{

	@Override
	public boolean activate() {
		return !Inventory.isFull() && !VARS.miningArea.contains(Players.getLocal());
	}

	@Override
	public void execute() {
		VARS.failsafeCount = 0;
		while(!VARS.PATH_TO_MINING.getEnd().isOnScreen() && VARS.failsafeCount++ <10){
			VARS.operation = "Going to mines";
			VARS.PATH_TO_MINING.traverse();
			//sleep(Players.getLocal().getLocation() Walking.getDestination());
		}
		
	}
}

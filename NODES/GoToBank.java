package mining.NODES;

import mining.EfficientMiner;
import mining.VARS;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

public class GoToBank extends Node{

	@Override
	public boolean activate() {
		return Inventory.isFull() && !VARS.BANKING_AREA.contains(Players.getLocal());
	}

	@Override
	public void execute() {
		EfficientMiner.operation = "Going to bank";
		VARS.PATH_TO_BANK.traverse();
	}

}

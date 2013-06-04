package mining.NODES;

import mining.EfficientMiner;
import mining.VARS;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

public class GoToMine extends Node{

	@Override
	public boolean activate() {
		return !Inventory.isFull() && !VARS.MINING_AREA.contains(Players.getLocal()) && !VARS.ABOVE_BANK.contains(Players.getLocal());
	}

	@Override
	public void execute() {
		EfficientMiner.operation = "Going to mines";
		VARS.PATH_TO_MINING.traverse();
	}
}

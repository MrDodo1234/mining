package mining.NODES;

import mining.VARS;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class AboveBank extends Node{
	
	private SceneObject stairs;
	
	@Override
	public boolean activate() {
		 return VARS.ABOVE_BANK.contains(Players.getLocal());
	}

	@Override
	public void execute() {
		stairs = SceneEntities.getAt(3256, 3421, 1);
		stairs.interact("Climb-down");
	}
	
}

package mining.NODES;

import mining.VARS;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Mine extends Node{
	
	private SceneObject rock;
	
	@Override
	public boolean activate() {
		return VARS.miningArea.contains(Players.getLocal()) && !Inventory.isFull();
	}

	@Override
	public void execute() {
		if(rock == null || !rock.validate()){
			VARS.operation = "Looking for ores";
			SceneObject rock = SceneEntities.getNearest(VARS.ORES);
			if(rock != null){
				if(rock.isOnScreen()){
					rock.interact("Mine");
					VARS.operation = "Mining ore";
					sleep(1000);
					while(rock.validate()){
						sleep(10);
					}
				}else Camera.turnTo(rock);
			}
		}
	}//end execute
}//end mine Node
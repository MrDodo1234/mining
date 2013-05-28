package mining;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Mine extends Node{
	
	private SceneObject rock;
	
	@Override
	public boolean activate() {
		return VARS.miningArea.contains(Players.getLocal()) && !Inventory.isFull();
	}

	@Override
	public void execute() {
		if(VARS.miningArea.contains(Players.getLocal()) && !Inventory.isFull()){
			if(rock == null || !rock.validate()){
				VARS.operation = "Looking for ores";
				SceneObject rock = SceneEntities.getNearest(VARS.ORES);
				if(rock != null){
					if(rock.isOnScreen()){
						if(Players.getLocal().isIdle()){
							rock.interact("Mine");
							VARS.operation = "Mining ore";
							sleep(900, 1100);
							while(Players.getLocal().getAnimation() == 625 || !Players.getLocal().isIdle()){
								sleep(900, 1100);
							}
						}
					}
					//else camera turn to rock
				}
			}
		}
	}//end execute
}//end mine Node
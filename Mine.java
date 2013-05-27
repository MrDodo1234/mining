package mining;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Mine extends Node{

	@Override
	public boolean activate() {
		return VARS.miningArea.contains(Players.getLocal()) && !Inventory.isFull();
	}

	@Override
	public void execute() {
		if(VARS.miningArea.contains(Players.getLocal()) && !Inventory.isFull()){
			SceneObject rock = SceneEntities.getNearest(VARS.ORES);
			VARS.operation = "Looking for ores";
			while(rock != null){
				if(rock.isOnScreen()){
					if(Players.getLocal().isIdle()){
						if (Players.getLocal().getAnimation() != 625){
							VARS.operation = "Mining ore";
							rock.interact("Mine");
							sleep(900, 1100);
							while(Players.getLocal().getAnimation() == 625)
								sleep(900, 1100);
						}
					}
				}
				//else camera turn to rock
			}
		}
	}//end execute
}//end mine Node
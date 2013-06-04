package mining.NODES;

import mining.EfficientMiner;
import mining.VARS;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Mine extends Node{
	
	//++++++++++++++++++++++++++++++++++++
	private final Tile[] ORES_TILES = {new Tile(3286, 3369, 0), new Tile(3285, 3368, 0)};
	private SceneObject[] rockArray = {SceneEntities.getAt(ORES_TILES[0]),SceneEntities.getAt(ORES_TILES[1])};
	private final int[] ORES = {11956, 11955};
	//++++++++++++++++++++++++++++++++++++
	
	@Override
	public boolean activate() {
		return VARS.MINING_AREA.contains(Players.getLocal()) && !Inventory.isFull() && !VARS.ABOVE_BANK.contains(Players.getLocal());
	}

	@Override
	public void execute() {
		for(int i = 0; i<rockArray.length; i++){
			EfficientMiner.operation = "Looking for ores";
			rockArray[i] = SceneEntities.getAt(ORES_TILES[i]);
			if(rockArray[i].getId() == ORES[i])
				if(rockArray[i].isOnScreen()){
					rockArray[i].interact("Mine");
					EfficientMiner.operation = "Mining ore";
					sleep(1000);
					while(rockArray[i].validate()){
						sleep(10);
					}
				}else Camera.turnTo(rockArray[i]);
		}
	}//end execute
}//end mine Node
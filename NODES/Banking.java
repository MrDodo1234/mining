package mining.NODES;

import mining.EfficientMiner;
import mining.VARS;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;

public class Banking extends Node{
	//++++++++++++++++++++++++++++++++++++
	private final int INVENTORY_ITEMS = 440;
	//++++++++++++++++++++++++++++++++++++
	@Override
	public boolean activate() {
		return 	Inventory.isFull() && VARS.BANKING_AREA.contains(Players.getLocal()) && !VARS.ABOVE_BANK.contains(Players.getLocal());
	}

	@Override
	public void execute() {
		EfficientMiner.operation = "Banking";
		Camera.setNorth();
		Camera.setPitch(Random.nextInt(20, 90));
		//when at bank deposit items
		Bank.open();
		//Timer t = new Timer(5000);
		while(/*t.isRunning() &&*/ Inventory.isFull()){
			Bank.deposit(INVENTORY_ITEMS, Inventory.getCount(INVENTORY_ITEMS));
		}
		for(int i = 1617; i <= 1624; i++){
			//t = new Timer(5000);
			while(/*t.isRunning() &&*/ Inventory.contains(i)){
				Bank.deposit(i, Inventory.getCount(i));
			}
		}
		Bank.close();
	}
	
}
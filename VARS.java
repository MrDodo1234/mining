package mining;

import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.TilePath;

public class VARS {
	//++++++++ INITIALISE AND DECLARE STUFF ++++++++//
		public static boolean startingUp = true;
		public static String operation="";
		
		//failsafe counter for traversing
		public static int failsafeCount;
		
		//the specific ores I like to mine
		public static final int[] ORES = {11955, 11956};
		
		//inventory to put in bank
		public static final int INVENTORY_ITEMS = 440;
		
		//timer
		public static final Timer time = new Timer(0);
		public static long timeRunning = 0;
		
		//area tiles
		public static final Area miningArea = new Area(new Tile(3283, 3372, 0), new Tile(3289, 3367, 0));
		public static final Area bankingArea = new Area(new Tile(3250, 3423, 0), new Tile(3257, 3419, 0));
		
		//paths
		public static final Tile[] TILES_TO_BANK = new Tile[]{		new Tile(3287, 3368, 0),
																	new Tile(3293, 3380, 0),
																	new Tile(3291, 3394, 0),
																	new Tile(3289, 3407, 0),
																	new Tile(3287, 3421, 0),
																	new Tile(3277, 3428, 0),
																	new Tile(3269, 3429, 0),
																	new Tile(3263, 3428, 0),
																	new Tile(3254, 3421, 0)
																					};
//		public static final Tile[] TILES_TO_MINING = new Tile[]{	new Tile(3265, 3428, 0),
//																	new Tile(3279, 3428, 0),
//																	new Tile(3286, 3414, 0),
//																	new Tile(3291, 3400, 0),
//																	new Tile(3292, 3388, 0),
//																	new Tile(3290, 3374, 0),
//																	new Tile(3286, 3366, 0)
//																					};
		
		public static final TilePath PATH_TO_BANK = new TilePath(TILES_TO_BANK);
//		public static final TilePath PATH_TO_MINING = new TilePath(TILES_TO_MINING);
		public static final TilePath PATH_TO_MINING = new TilePath(TILES_TO_BANK).reverse();
		 
		
		//++++++++ ---------------------------- ++++++++//
}

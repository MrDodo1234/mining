package mining;

import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.TilePath;

public class VARS {
	
	//paint stuff
	//timer
	public static long timeRunning = 0;
	public static long startTime = System.currentTimeMillis();
	
	//area tiles
	public static final Area MINING_AREA = new Area(new Tile(3275, 3385, 0), new Tile(3289, 3367, 0));
	public static final Area BANKING_AREA = new Area(new Tile(3250, 3423, 0), new Tile(3257, 3419, 0));
	public static final Area ABOVE_BANK = new Area(new Tile(3253, 3423, 1), new Tile(3258, 3420, 1));
	
	//paths
	public static final Tile[] TILES_TO_BANK = new Tile[]{		new Tile(3286, 3368, 0),
																new Tile(3288, 3373, 0),
																new Tile(3293, 3378, 0),
																new Tile(3293, 3384, 0),
																new Tile(3292, 3392, 0),
																new Tile(3292, 3400, 0),
																new Tile(3290, 3408, 0),
																new Tile(3287, 3416, 0),
																new Tile(3286, 3421, 0),
																new Tile(3281, 3423, 0),
																new Tile(3275, 3428, 0),
																new Tile(3269, 3428, 0),
																new Tile(3261, 3428, 0),
																new Tile(3254, 3427, 0),
																new Tile(3254, 3421, 0)
																				};
	
	public static final TilePath PATH_TO_BANK = new TilePath(TILES_TO_BANK);
	public static final TilePath PATH_TO_MINING = new TilePath(TILES_TO_BANK).reverse();
}

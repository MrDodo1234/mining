package mining;

import mining.NODES.AboveBank;
import mining.NODES.Banking;
import mining.NODES.GoToBank;
import mining.NODES.GoToMine;
import mining.NODES.Mine;

import org.powerbot.core.Bot;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.WidgetCache;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.client.Client;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

@Manifest(		
		authors = "MrDodo", 
		name = "Efficient Miner", 
		description = "Mines and banks iron ore in my fav location: Varrock East"
		)
public class EfficientMiner extends ActiveScript implements PaintListener, MessageListener{
	private Client client = Bot.client();
	private final Node[] jobs = {	new Mine(), 
									new Banking(),
									new GoToMine(),
									new GoToBank(),
									new AboveBank()
								};
	//VARIABLES///////////////////////
	private int startExp, expGained, levelsGained, startLevel;
	private int oresMined = 0;
	public static String operation="";
	//END VARIABLES///////////////////
	
	
	//paint
	private Image getPaint(String location) {
		try {
			return ImageIO.read(new URL(location));
		} catch(IOException e) {
			return null;
		}
	}
	private final Image paintImg = getPaint("http://i39.tinypic.com/5afktk.png");
	
	public void onStart(){
		if(Game.isLoggedIn()){
			startExp = Skills.getExperience(Skills.MINING);
		}
	}
	@Override
	public int loop() {
		//6 hour paint thing (wann keep dem paints up)
		//or whatever it is :/
		if (Game.getClientState() != Game.INDEX_MAP_LOADED) {
			return 1000;
		}
		if (client != Bot.client()) {
			WidgetCache.purge();
			Bot.context().getEventManager().addListener(this);
			client = Bot.client();
		}//end 6 hour thing
		
		if(Game.isLoggedIn()){
			for(Node job : jobs){
				if(job.activate()){
					System.out.println(job.getClass().getName());
					job.execute();
				}
			}//end node loop
		}//end if game is logged in
        return Random.nextInt(100, 200);
        
	}

	@Override
	public void onRepaint(Graphics g1) {
		//get variables and set shizz
		if(Game.isLoggedIn()){
			startLevel = Skills.getLevel(Skills.MINING);
			levelsGained = Skills.getLevel(Skills.MINING) - startLevel;
			expGained = Skills.getExperience(Skills.MINING) - startExp;
			Graphics2D g = (Graphics2D) g1;
			drawMouse(g);
			VARS.timeRunning = System.currentTimeMillis() - VARS.startTime;
			
			//draw errythaaaang
			g.drawImage(paintImg, 0, 0, null);
			g.setColor(Color.white);
			g.drawString("Time Elapsed: "+Time.format(VARS.timeRunning), 75, 29);
			g.drawString(""+operation, 250, 29);
			g.drawString("Ores Mined: "+oresMined+" ("+UTIL.getPerHour(oresMined)+"/h)", 390, 29);
			g.drawString("Exp gained: "+expGained+" ("+UTIL.getPerHour(expGained)+"/h) +"+levelsGained, 550, 29);
		}
	}
	private void drawMouse(final Graphics2D g) {
		final Point mouse = Mouse.getLocation();
		final Graphics2D gear1 = (Graphics2D) g.create();
		gear1.setColor(Color.WHITE);
		
		g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		gear1.rotate(VARS.timeRunning % 2000d / 2000d * (360d) * 2 * Math.PI / 180.0, mouse.x, mouse.y);
		gear1.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		gear1.drawArc(mouse.x - (4), mouse.y - (4), 8, 8, 0, 75);
		gear1.drawArc(mouse.x - (4), mouse.y - (4), 8, 8, 180, 75);
		gear1.drawArc(mouse.x - (8), mouse.y - (8), 16, 16, 180, 75);
		gear1.drawArc(mouse.x - (8), mouse.y - (8), 16, 16, 0, 75);
		gear1.rotate(VARS.timeRunning % 1300d / 1300d * (-360d) * 2 * Math.PI / 180.0, mouse.x, mouse.y);
		gear1.drawArc(mouse.x - (12), mouse.y - (12), 24, 24, 0, 75);
		gear1.drawArc(mouse.x - (12), mouse.y - (12), 24, 24, 180, 75);
		g.setColor(Color.CYAN);
		g.fillOval(mouse.x-1, mouse.y-1, 3, 3);
	}
	
	@Override
	public void messageReceived(MessageEvent me) {
		final String msg = me.getMessage().toLowerCase();
		if(me.getId() == 109){
			if(msg.contains("manage to mine some iron")){
				oresMined++;
			}
	    }
		
	}
}
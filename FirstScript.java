package mining;

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
		name = "First Script", 
		description = "Mines and banks iron ore in my fav location"
		)
public class FirstScript extends ActiveScript implements PaintListener, MessageListener{
	private Client client = Bot.client();
	private final Node[] jobs = {	new Mine(), 
									new Banking(),
									new GoToMine(),
									new GoToBank()
								};
	
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
			VARS.startExp = Skills.getExperience(Skills.MINING);
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
		VARS.levelsGained = Skills.getLevel(Skills.MINING);
		VARS.expGained = Skills.getExperience(Skills.MINING) - VARS.startExp;
		Graphics2D g = (Graphics2D) g1;
		drawMouse(g);
		VARS.timeRunning = VARS.time.getElapsed();
		
		//draw errythaaaang
		g.drawImage(paintImg, 0, 0, null);
		g.setColor(Color.white);
		g.drawString("Time Elapsed: "+Time.format(VARS.timeRunning), 75, 29);
		g.drawString(""+VARS.operation, 250, 29);
		g.drawString("Ores Mined: "+VARS.oresMined+" ("+UTIL.getPerHour(VARS.oresMined)+"/h)", 390, 29);
		g.drawString("Exp gained: "+VARS.expGained+" ("+UTIL.getPerHour(VARS.expGained)+"/h)", 550, 29);
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
	public void messageReceived(MessageEvent e) {
		final String msg = e.getMessage().toLowerCase();
		if(msg.contains("manage to mine some iron")){
			VARS.oresMined++;
	    }
	}
}
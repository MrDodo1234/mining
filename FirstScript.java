package mining;

import org.powerbot.core.Bot;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.methods.widget.WidgetCache;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.client.Client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

@Manifest(		
		authors = "MrDodo", 
		name = "First Script", 
		description = "Mines and banks iron ore in my fav location"
		)
public class FirstScript extends ActiveScript implements PaintListener{
	private Client client = Bot.client();
	private final Node[] jobs = {	new Mine(), 
									new Banking()
								};
	//for time display
	private final Timer time = new Timer(0);
	private long timeRunning=0;
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
		while(!Game.isLoggedIn())
			sleep(500,1000);
		while(VARS.startingUp){
			if(Game.isLoggedIn()){
				while(Camera.getPitch() < 90){
					Camera.setPitch(true);
					VARS.startingUp=false;
				}
			}
			sleep(500);
		}
	}
	
	@Override
	public int loop() {
		//6 hour paint thing (wann keep dem paints up)
		if (Game.getClientState() != Game.INDEX_MAP_LOADED) {
			return 1000;
		}
		if (client != Bot.client()) {
			WidgetCache.purge();
			Bot.context().getEventManager().addListener(this);
			client = Bot.client();
		}//end 6 hour paint thing
		
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
		Graphics2D g = (Graphics2D) g1;
		timeRunning = time.getElapsed();
		//draw errythaaaang
		g.drawImage(paintImg, 0, 0, null);
		g.setColor(Color.white);
		g.drawString("Time Elapsed: "+Time.format(timeRunning), 75, 29);
		g.drawString(""+VARS.operation, 250, 29);
	}
}
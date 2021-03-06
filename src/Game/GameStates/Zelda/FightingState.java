package Game.GameStates.Zelda;

import Game.GameStates.State;
import Game.Zelda.Entities.Dynamic.BossFight;
import Game.Zelda.Entities.Dynamic.LinkFight;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.Random;

/**
 * Created by Eithan on 5/18/2020
 */

public class FightingState extends State {
	public boolean enter = false, runOnce = false;
	public int size = 24, choice = 0, stealingCount = 60*2, stealingCount2 = 60*2, caser = 0, musicCounter = 0;
	public String shopMode = "Choices";
	public Rectangle floor = new Rectangle(130, handler.getHeight()/2 + 30 ,handler.getWidth()/2 + 600,10);;
    public FightingState(Handler handler) {
        super(handler);
    }
    
    public LinkFight linkFight = new LinkFight(150, handler.getHeight()/2 - 200, Images.linkFight, handler);
    public BossFight ganonFight = new BossFight(handler.getHeight(), handler.getHeight()/2 - 200, Images.ganonFight, handler);
    //------Initialize------ 
    //2D Fighter
    @Override
    public void tick() {
    	linkFight.tick();
    	ganonFight.tick();
    	//Runs the music Once, player probably will die first or finish the boss before the music ends
    	if (!runOnce) {
    		runOnce = true;
    		handler.getMusicHandler().changeMusic("FinalBattle2d.wav");
    	}
    	
    }
    @Override
    public void render(Graphics g) {    
    	//Draw Stage and the characters
    	g.drawImage(Images.linkFight[7],0 ,0, handler.getWidth(), handler.getHeight(), null);
    	g.drawRect(floor.x, floor.y, floor.width, floor.height);
    	linkFight.render(g);
    	ganonFight.render(g);
    	
    	g.setFont(new Font("TimesRoman", Font.PLAIN, size));
    	
    
    }
    @Override
    public void refresh() {

    }
}

  
  

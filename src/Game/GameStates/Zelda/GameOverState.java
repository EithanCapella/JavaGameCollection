package Game.GameStates.Zelda;

import Game.Galaga.Entities.EntityManager;
import Game.GameStates.State;
import Game.PacMan.World.MapBuilder;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameOverState extends State {
	public EntityManager entityManager;
	public String Mode = "Menu";
	public int selectPlayers = 1,entitySize = handler.getWidth()/10,caser,counter = 0;;
	public int startCooldown = 60*7;//seven seconds for the music to finish
	Random rand = new Random();
	boolean runOnce = false;
	public GameOverState(Handler handler){
		super(handler);
		caser = rand.nextInt(4);//the case is chosen when constructed
	
		
		
		
		
	}


	@Override
	public void tick() {
		
		if(!runOnce) {
			handler.getMusicHandler().changeMusic("GameOverZelda.wav");
			runOnce = true;
		}
		//Brings the player back to the game
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){//resets the map and starts the game from scratch
			handler.getZeldaGameState().link.setLife(3.0);
			restart();
			handler.getZeldaGameState().refresh();
			State.setState(handler.getMenuState());
			runOnce = false;
			handler.getFightingState().linkFight.setLife(3.0);
			handler.getFightingState().linkFight.x = 150;
			handler.getFightingState().ganonFight.setLife(15);
			handler.getFightingState().ganonFight.x = 550;
			handler.getFightingState().runOnce = false;
			
		}    
	}


	@Override
	public void render(Graphics g) { 	    
	
		
		g.drawImage(Images.gameOverScreen, handler.getWidth()/3, handler.getHeight()/3, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.ROMAN_BASELINE, 48));
		g.drawString("--Continue--", handler.getWidth()/2 - 150, handler.getHeight()/2 + 100);
	}


	@Override
	public void refresh() {
	}

	
	public void restart() {
		//resets Link's most important variables
		handler.getZeldaGameState().link.setLife(3.0);
		handler.getZeldaGameState().link.hasSword = false;
		handler.getZeldaGameState().link.hasBow = false;
		handler.getZeldaGameState().link.hasRaft = false;
		handler.getZeldaGameState().link.hasRod = false;
		handler.getZeldaGameState().refresh();
		handler.getMusicHandler().stopMusic();
		handler.getMusicHandler().changeMusic("nature.wav");
	}
}


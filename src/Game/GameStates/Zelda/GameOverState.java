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
			handler.getMusicHandler().changeMusic("GameOverZelda");
		}
		//Brings the player back to the game
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){//resets the map and starts the game from scratch
			handler.getZeldaGameState().link.health = 3;
			State.setState(handler.getMenuState());
			runOnce = false;
			
		}    
	}


	@Override
	public void render(Graphics g) { 	    
	
		
		g.drawImage(Images.gameOverScreen, handler.getWidth(), handler.getHeight(), null);
		
		
//		switch (caser) {//switches between the message displayed on the game over screen
//
//		case 0:
//			g.drawString("Waka waka waka, waka waka. Waka waka waka <Waka Waka! Waka...>", handler.getWidth()/6, handler.getHeight()/5);
//			break;
//
//		case 1:
//			g.drawString("At least you do not have to pay a quarter.", handler.getWidth()/4, handler.getHeight()/5);
//			break;
//
//		case 2:
//			g.drawString("Pac-Man... It's time to talk about your pill addiction.", handler.getWidth()/4, handler.getHeight()/5);
//			break;
//
//		case 3:
//			g.drawString("Ms.Pac-Man is dissapointed in you", handler.getWidth()/3, handler.getHeight()/5);
//			break;
//		}
	}


	@Override
	public void refresh() {
	}

	
	public void restart() {
		handler.getScoreManager().setPacmanCurrentScore(0);
		handler.getMusicHandler().changeMusic("nature.wav");
	}
}

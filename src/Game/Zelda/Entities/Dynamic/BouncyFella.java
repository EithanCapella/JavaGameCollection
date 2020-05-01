package Game.Zelda.Entities.Dynamic;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Statics.DungeonDoor;
import Game.Zelda.Entities.Statics.SectionDoor;
import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Game.Zelda.Entities.Statics.caveSword;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import static Game.GameStates.Zelda.ZeldaGameState.worldScale;
import static Game.Zelda.Entities.Dynamic.Direction.DOWN;
import static Game.Zelda.Entities.Dynamic.Direction.UP;

/**
 * Created by AlexVR on 3/15/2020
 */
public class BouncyFella extends BaseMovingEntity {


	private final int animSpeed = 120;
	private double life=3.0;
	int randomDirection = 0;
	int moveX=0,moveY=0,xExtraCounter=0,yExtraCounter=0, nextX= new Random().nextInt(70+50)-50 +x, nextY= new Random().nextInt(70+50)-50 +y;
	int directionCounter = 120,attackCounter=30,hurtCounter=20, count = 0;
	public boolean movingMap = false,hasSword=false,horray=false,hurt=false, roll = false, changeSpeed = false;
	Direction movingTo;
	Animation pickUpAnim,attackAnim,hurtAnim;
	Rectangle jumpBounds;
	
	


	public BouncyFella(int x, int y, Handler handler) {
		super(x, y, Images.bouncyEnemyFrames, handler);
		jumpBounds = (Rectangle) bounds.clone();
		jumpBounds.width= jumpBounds.width +2;
		jumpBounds.height= jumpBounds.height +2;
		interactBounds = (Rectangle) bounds.clone();
		count=new Random().nextInt(6*60)+3*60;

	}

	public BouncyFella(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
		jumpBounds = (Rectangle) bounds.clone();
		jumpBounds.width= jumpBounds.width;
		jumpBounds.height= jumpBounds.height;
		speed = 2;
		health = 6;


	}
	
	
	
	@Override
	public void tick() {
		if (directionCounter > 0) {
			directionCounter--;
			move();
		}
		else if (directionCounter <= 0) {
			
			nextX= new Random().nextInt(100+70)-70 +x;
			nextY= new Random().nextInt(100+70)-70 +y;
			directionCounter = 120;
			jumpBounds.x = nextX;
			jumpBounds.y = nextY;
			
		}
		
		
		
		//System.out.println( "Enemy pos" + x + "," + y);
		
		
			
		
		 
			
		
	}

	@Override
	public void render(Graphics g) {
		g.drawRect(jumpBounds.x, jumpBounds.y, jumpBounds.width, jumpBounds.height);
		if (moving&&!attacking) {
			g.drawImage(animation.getCurrentFrame(),x , y, width , height  , null);

		} else {
			if (movingMap&&!attacking){
				g.drawImage(animation.getCurrentFrame(),x , y, width, height  , null);
			}
			g.drawImage(sprite, x , y, width , height , null);
		}
		if(horray&&!attacking) {
			g.drawImage(pickUpAnim.getCurrentFrame(),x , y, width , height  , null);
			g.drawImage(Images.npc[4],x , y -40, width/2 , height  , null);
		}		
	}
	 

	
	public void move() {
		moving = true;
		changeIntersectingBounds();
		//chack for collisions
		
		if (directionCounter > 0) {
			
			if (interactBounds.intersects(jumpBounds)) {
				speed = 1;}
			else {speed = 2;}
			
			directionCounter--;
			if (x < jumpBounds.x) {
				x += speed;}
			else if (x > jumpBounds.x) {
				x -= speed;}	
			else if (y < jumpBounds.y) {
				y += speed;}	
			else if (y > jumpBounds.y) {
				y -= speed;}
			else if(x == jumpBounds.x && y == jumpBounds.y) { directionCounter = 0; }
		}
		
		
	
		
		
		
			
			for (SolidStaticEntities objects : handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
				if (!(objects instanceof SectionDoor) && objects.bounds.intersects(jumpBounds)) {
					directionCounter = 0;
					
				}
				else if (!(objects instanceof SectionDoor) && !(objects.bounds.intersects(jumpBounds))) {
	
				}
				
			}
		
		//Movement
		
		
		
		bounds.x = x;
		bounds.y = y;
		
		changeIntersectingBounds();

	}
	
	
	
	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}
}

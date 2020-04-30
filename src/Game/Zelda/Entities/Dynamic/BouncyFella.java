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
	int newMapX=0,newMapY=0,xExtraCounter=0,yExtraCounter=0, nextX = 0, nextY = 0;
	int directionCounter = 60,attackCounter=30,hurtCounter=20, count = 0;
	public boolean movingMap = false,hasSword=false,horray=false,hurt=false;
	Direction movingTo;
	Animation pickUpAnim,attackAnim,hurtAnim;
	Rectangle jumpBounds;
	
	


	public BouncyFella(int x, int y, Handler handler) {
		super(x, y, Images.bouncyEnemyFrames, handler);
		jumpBounds = (Rectangle) bounds.clone();
		interactBounds = (Rectangle) bounds.clone();
		count=new Random().nextInt(6*60)+3*60;

	}

	public BouncyFella(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
		jumpBounds = (Rectangle) bounds.clone();
		speed = 2;
		health = 6;


	}
	
	
	
	@Override
	public void tick() {
		
		
		//System.out.println( "Enemy pos" + x + "," + y);
		if (directionCounter > 0) {
			directionCounter--;
		}
		else if (directionCounter <= 0) {
			nextX= new Random().nextInt(70+50)-50 +x;
			nextY= new Random().nextInt(70+50)-50 +y;
			//randomDirection = new Random().nextInt(4);
			directionCounter = 60;
			
			move();
		}
			
		
	}

	@Override
	public void render(Graphics g) {
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
//		if (attacking) {    
//			if(direction == Direction.LEFT) {
//				g.drawImage(attackAnim.getCurrentFrame(),this.x -(attackAnim.getCurrentFrame().getWidth()*2-this.width) , y,attackAnim.getCurrentFrame().getWidth()*2 ,attackAnim.getCurrentFrame().getHeight()*2, null);
//			}
//			if(direction == Direction.UP) {
//				g.drawImage(attackAnim.getCurrentFrame(),x , this.y -(attackAnim.getCurrentFrame().getHeight()*2 -this.height), attackAnim.getCurrentFrame().getWidth()*2 ,attackAnim.getCurrentFrame().getHeight()*2, null);
//			}else {g.drawImage(attackAnim.getCurrentFrame(),x , y, attackAnim.getCurrentFrame().getWidth()*2 ,attackAnim.getCurrentFrame().getHeight()*2, null); }
//		}
		
	}
	 

	
	public void move() {
		moving = true;
		changeIntersectingBounds();
		//chack for collisions
		

		jumpBounds.x = nextX;
		jumpBounds.y = nextY;
			
			for (SolidStaticEntities objects : handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
				if (!(objects instanceof SectionDoor) && objects.bounds.intersects(jumpBounds)) {
					directionCounter = 0;	
					//System.out.println("cannot jump");
					
				}
				if (!(objects instanceof SectionDoor) && !(objects.bounds.intersects(jumpBounds))) {
					directionCounter = 60;	
					x = nextX;
					y = nextY;
				}
				
			}
		
		//Movement
//		switch (direction) {
//		case RIGHT:
//			x += speed;
//			break;
//		case LEFT:
//			x -= speed;
//
//			break;
//		case UP:
//			y -= speed;
//			break;
//		case DOWN:
//			y += speed;
//
//			break;
//		}
		bounds.x = x;
		bounds.y = y;
		jumpBounds.x = x;
		jumpBounds.y = y;
		changeIntersectingBounds();

	}
	
	
	
	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}
}

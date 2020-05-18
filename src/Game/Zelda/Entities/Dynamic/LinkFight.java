package Game.Zelda.Entities.Dynamic;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Statics.DungeonDoor;
import Game.Zelda.Entities.Statics.Item;
import Game.Zelda.Entities.Statics.SectionDoor;
import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Game.Zelda.Entities.Statics.blockBound;
import Game.Zelda.Entities.Statics.caveSword;
import Game.Zelda.Entities.Statics.magicalRod;
import Game.Zelda.Entities.Statics.magicalSword;
import Game.Zelda.Entities.Statics.moblinArrow;
import Game.Zelda.Entities.Statics.raft;
import Game.Zelda.Entities.Statics.riverBlock;
import Game.Zelda.Entities.Statics.superSword;
import Game.Zelda.Entities.Statics.superRing;
import Game.Zelda.Entities.Statics.swordLaser;
import Game.Zelda.Entities.Dynamic.superWave;
import Game.Zelda.Entities.Statics.whiteSword;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static Game.GameStates.Zelda.ZeldaGameState.worldScale;
import static Game.Zelda.Entities.Dynamic.Direction.DOWN;
import static Game.Zelda.Entities.Dynamic.Direction.UP;

/**
 * Created by Eithan on 5/18/2020
 */
public class LinkFight extends BaseMovingEntity {

	private final int animSpeed = 120;
	private double life=3.0;
	int newMapX=0,newMapY=0,xExtraCounter=0,yExtraCounter=0;
	int attackCoolDown= 30, jumpCoolDown = 30, jumpTime = 10, hitCount = 30;
	public boolean notFloor = false, jump = false, idle = false, attack = false, attackLow = false;
	Direction movingTo;
	String dir = ""; //facing relative to link
	public swordLaser laserSword;
	
	public superWave superWave;
	Animation running,runningL,attackAnim,animationL,attLow, attLowL;
	public Rectangle swordBounds = (Rectangle) bounds.clone();
	public Rectangle rodBounds = (Rectangle) bounds.clone();
	public Rectangle proyectileBounds = (Rectangle) bounds.clone();
	


	public LinkFight(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
		speed = 4;
		health = 6;
		bounds.height = 39*2;
		//--------------------------------//--------------------------------//--------------------------------//
		//------Animations Movements/Attacks/Block------
		BufferedImage[] animList = new BufferedImage[4];
		animList[0] = sprite[2];
		animList[1] = sprite[2];
		animList[2] = sprite[3];
		animList[3] = sprite[4];
		animation = new Animation(animSpeed,animList);
		BufferedImage[] animList2 = new BufferedImage[4];
		animList2[0] = Images.flipHorizontal(sprite[2]);
		animList2[1] = Images.flipHorizontal(sprite[2]);
		animList2[2] = Images.flipHorizontal(sprite[3]);
		animList2[3] = Images.flipHorizontal(sprite[4]);
		animationL = new Animation(animSpeed,animList2);
		BufferedImage[] runAnim = new BufferedImage[3];
		runAnim[0] = sprite[8];
		runAnim[1] = sprite[9];
		runAnim[2] = sprite[10];
		running = new Animation(animSpeed,runAnim);
		BufferedImage[] runAnimL = new BufferedImage[3];
		runAnimL[0] = Images.flipHorizontal(sprite[8]);
		runAnimL[1] = Images.flipHorizontal(sprite[9]);
		runAnimL[2] = Images.flipHorizontal(sprite[10]);
		runningL = new Animation(animSpeed,runAnimL);
		BufferedImage[] lowAnim = new BufferedImage[3];
		lowAnim[0] = sprite[11];
		lowAnim[1] = sprite[12];
		lowAnim[2] = sprite[13];
		attLow = new Animation(animSpeed,lowAnim);
		BufferedImage[] lowAnimL = new BufferedImage[3];
		lowAnimL[0] = Images.flipHorizontal(sprite[11]);
		lowAnimL[1] = Images.flipHorizontal(sprite[12]);
		lowAnimL[2] = Images.flipHorizontal(sprite[13]);
		attLowL = new Animation(animSpeed,lowAnimL);
		//--------------------------------//--------------------------------//--------------------------------//

	
		
	}
	@Override
	public void tick() {
		//--------------------------------//--------------------------------//--------------------------------//
		//Timers and attackCoolDowns
		if (attackCoolDown > 0 && (attack || attackLow)) {
			attackCoolDown--;
		}
		else if (attackCoolDown <= 0) {
			attackCoolDown = 30;
			attack = false;
			attackLow = false;
		}
		//--------------------------------//--------------------------------//--------------------------------//
		
		
		//------Physics------
		//Gravity, only runs after a jump is finished. IDEA: jump for a while then activate gravity to simulate the arc of a Jump.
		if(!jump && notFloor && !idle) {
			changeIntersectingBounds();
			bounds.x = x;
			bounds.y = y;
			y+=3;
			
		}
		//Collisions. If the character collides with the floor it will be pulled back up
		if (handler.getFightingState().floor.intersects(interactBounds) && !jump && !idle) {
			y--;
			changeIntersectingBounds();
			notFloor = false;
			idle = true;
			
			
		}
	
		//Makes the character's jump go up smoothly and at the "apex" slows down
			if (jump) {
				if (jumpTime > 0) {
					jumpTime--;
					y-=4;
				}
				else {
					jumpTime = 10;
					jump = false;
				}
			}
		//--------------------------------//--------------------------------//--------------------------------//
		
			
		//------Link's Movements------
			//movingTo functions is to save Link's last current position in terms of facing
			if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_W) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP) ) {
				//activates jump algorithm
					jump = true;
					jumpTime = 20;
					direction = UP;
					notFloor = true;
					
				idle = false;
				movingTo = UP;
				sprite = sprites[0];
					move(direction);	
				
			} else if (handler.getKeyManager().down) {
				if (direction != DOWN) {
					direction = Direction.DOWN;
					
				}
				movingTo = Direction.DOWN;
				sprite = sprites[1];
				move(direction);
				
					
				
			} else if (handler.getKeyManager().left && !attack) {
				if (direction != Direction.LEFT) {
					direction = Direction.LEFT;
					
					}
				movingTo = Direction.LEFT;
				sprite = Images.flipHorizontal(sprites[0]);		
				dir = "left";
				move(direction);
				
			} else if (handler.getKeyManager().right && !attack) {
				if (direction != Direction.RIGHT) {		
					direction = Direction.RIGHT;
					
				}	
				dir = "right";
				movingTo = Direction.RIGHT;
				sprite = sprites[0];
				move(direction);
			} else {
				jump = false;
				moving = false;
				sprite = sprites[0];
				movingTo = Direction.NONE;
			}
			//--------------------------------//--------------------------------//--------------------------------//
	
			
			
			//------Link's Attacks------
		
	
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && !attack ) {
			attack = true;
			bounds.x = x;
			bounds.y = y;
			swordSlash();
			handler.getMusicHandler().playEffect("Sword_Slash.wav");
		}
		if (handler.getKeyManager().shift == true) {
			attackLow = true;
			bounds.x = x;
			bounds.y = y;
			swordLow();
			handler.getMusicHandler().playEffect("Sword_Slash.wav");
		}
		
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_L)) {
			handler.changeState(handler.getFightingState());
		}
	}
	//--------------------------------//--------------------------------//--------------------------------//
		
	
	//------ Link's Attack Methods------
	public void swordSlash() {
		//Adjust the hitbox relative to where he is facing
		swordBounds = (Rectangle) bounds.clone();
		if (dir == "right") {
		swordBounds.x += 48;}
		else if (dir == "left") {
			swordBounds.x -= 48;
		}
		if (hitCount > 0) {hitCount--;}
		else if (hitCount <= 0) {
			hitCount = 29; 
			swordBounds.x = 0;
			swordBounds.y = 0;}
		
	}
	
	public void swordLow() {
		
		swordBounds = (Rectangle) bounds.clone();
		if (dir == "right") {
			swordBounds.x += 64;
			swordBounds.y += 20;}
			else if (dir == "left") {
				swordBounds.x -= 64;
				swordBounds.y += 20;
			}
		
		if (hitCount > 0) {hitCount--;}
		else if (hitCount <= 0) {
			hitCount = 29; 
			swordBounds.x = 0;
			swordBounds.y = 0;}
		
	}
	//--------------------------------//--------------------------------//--------------------------------//
	
	
	@Override
	public void move(Direction direction) {
		moving = true;
		changeIntersectingBounds();
		//chack for collisions	
				
		//Movement
				switch (direction) {
				case RIGHT:
					x += speed;
					break;
				case LEFT:
					x -= speed;
					
					break;
				case UP:
					
					//No current up movement
					
					break;
				}
				bounds.x = x;
				bounds.y = y;
				changeIntersectingBounds();
				
	}
	//--------------------------------//--------------------------------//--------------------------------//
	
	
	
	@Override
	public void render(Graphics g) {//render all anims
		g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
		
		//Draws Link's idle animation
			if (!attack && (movingTo != Direction.NONE)) {
			if (movingTo == Direction.DOWN) {
				if (dir == "right") {
					g.drawImage(sprite ,x, y+5, width , height  , null);}
				else if (dir == "left") {
					g.drawImage(Images.flipHorizontal(sprite) ,x, y+5, width , height  , null);}
			}
			//Link's movementAnims
			else if(movingTo == Direction.LEFT) {
				runningL.tick();
				g.drawImage(runningL.getCurrentFrame(), x , y-10, width-15 , height-20  , null);
			}
			else if (movingTo == Direction.RIGHT) {
				running.tick();
				g.drawImage(running.getCurrentFrame(), x , y-10, width-15 , height-20  , null);}
			}
			
			//adds directions to his anims depending to where he is looking
			//These renders focus on Link's Attacks
			else if (attack == true && dir == "right") {
				animation.tick();
				g.drawRect(swordBounds.x, swordBounds.y, swordBounds.width, swordBounds.height);
				g.drawImage(animation.getCurrentFrame() ,x+20 , y-25, width+10 , height  , null);
			}
			else if (attack == true && dir == "left") {
				animationL.tick();
				g.drawRect(swordBounds.x, swordBounds.y, swordBounds.width, swordBounds.height);
				g.drawImage(animationL.getCurrentFrame() ,x-20 , y-25, width+10 , height  , null);
			}
			else if (attackLow == true && dir == "right") {
				attLow.tick();
				g.drawRect(swordBounds.x, swordBounds.y, swordBounds.width, swordBounds.height);
				g.drawImage(attLow.getCurrentFrame() ,x+30 , y-5, width+10 , height-20  , null);
			}
			else if (attackLow == true && dir == "left") {
				attLowL.tick();
				g.drawRect(swordBounds.x, swordBounds.y, swordBounds.width, swordBounds.height);
				g.drawImage(attLowL.getCurrentFrame() ,x-30 , y-5, width+10 , height-20  , null);
			}
			else {
				if (dir == "right") {
				g.drawImage(sprite ,x , y-30, width , height  , null);}
				else if (dir == "left") {
					g.drawImage(Images.flipHorizontal(sprite) ,x , y-30, width , height  , null);}
				
			}
			
		    

			
				
			
						
		
		
		
	}
	
	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}

}

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
import java.util.Random;

import static Game.GameStates.Zelda.ZeldaGameState.worldScale;
import static Game.Zelda.Entities.Dynamic.Direction.DOWN;
import static Game.Zelda.Entities.Dynamic.Direction.UP;
/**
 * Created by Eithan on 5/18/2020
 */
public class BossFight extends BaseMovingEntity {

	private final int animSpeed = 120;
	private double life=3.0;
	int newMapX=0,newMapY=0,xExtraCounter=0,yExtraCounter=0;
	int attackCoolDown= 30, jumpCoolDown = 30, jumpTime = 10, hitCount = 30, choice = 100, choiceCount = 80;
	public boolean notFloor = true, jump = false, idle = false, attack = false, attackLow = false;
	Direction movingTo;
	String act = "jump", dir = ""; //attack or move action
	public swordLaser laserSword;
	Random random = new Random();
	public superWave superWave;
	Animation running,runningL,attackAnim,animationL,attLow, attLowL;
	public Rectangle swordBounds = (Rectangle) bounds.clone();
	public Rectangle rodBounds = (Rectangle) bounds.clone();
	public Rectangle proyectileBounds = (Rectangle) bounds.clone();
	


	public BossFight(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
		speed = 4;
		health = 10;
		bounds.height = 39*2;
		//Attack Animations- Had to make 2 since flipping an animation makes the order go in reverse
		BufferedImage[] animList = new BufferedImage[4];
		animList[0] = sprite[4];
		animList[1] = sprite[4];
		animList[2] = sprite[5];
		animList[3] = sprite[6];
		animation = new Animation(animSpeed,animList);
		BufferedImage[] animList2 = new BufferedImage[4];
		animList2[0] = Images.flipHorizontal(sprite[4]);
		animList2[1] = Images.flipHorizontal(sprite[4]);
		animList2[2] = Images.flipHorizontal(sprite[5]);
		animList2[3] = Images.flipHorizontal(sprite[6]);
		animationL = new Animation(animSpeed,animList2);


	
		
	}
	@Override
	public void tick() {
		//------Timers and CoolDowns------
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
					jumpTime = 5;
					jump = false;
				}
			}
		//--------------------------------//--------------------------------//--------------------------------//
			
			
		//----Algorithm for Ganon's movements and attacks----
			//Select one of the attacks
			if (choiceCount > 0) {
				choiceCount--;
			}
			else if (choiceCount <= 0) {
				choice = random.nextInt(4);
				choiceCount = 40;
				
				
			}
			
			//Dash Attack
			if (choice == 0) {
				speed = 5;
				if(handler.getFightingState().linkFight.x < x) {
					act = "left";
				}
				else if(handler.getFightingState().linkFight.x > x) {
					act = "right";
				}
			}
			else if (choice == 1) {
				speed = 4;
				act = "attack";
			}
			else {
				//Idle and say a comment
				act = "nothing";
			}
			
			//Jumping Mechanisms, timer of jump and decrease in Y, first half of an arc jump.
			if (act == "jump") {
				
					jump = true;
					jumpTime = 20;
					direction = UP;
					notFloor = true;
					
				idle = false;
				movingTo = UP;
				sprite = sprites[0];
					move(direction);	
				
			} 
			
			//When Ganon does Nothing, he drops his guard to mock the player
			else if (act == "nothing") {
				if (direction != DOWN) {
					direction = Direction.DOWN;
					
				}
				movingTo = Direction.DOWN;
				sprite = sprites[3];
				move(direction);
				
			//--------------------------------//--------------------------------//--------------------------------//
				
				
			//------Movement------
			//Basic Movement, the same as link, but reversed engineered as an Dash Attack
			} else if (act == "left" && !attack) {
				if (direction != Direction.LEFT) {
					direction = Direction.LEFT;
					
					}
				movingTo = Direction.LEFT;
				sprite = Images.flipHorizontal(sprites[1]);		
				dir = "left";
				move(direction);
				
			} else if (act == "right" && !attack) {
				if (direction != Direction.RIGHT) {		
					direction = Direction.RIGHT;
					
				}	
				dir = "right";
				movingTo = Direction.RIGHT;
				sprite = sprites[1];
				move(direction);
			} else {
				jump = false;
				moving = false;
				sprite = sprites[0];
				movingTo = Direction.NONE;
			}
		
	
		
	
		if (act == "attack" && !attack ) {
			attack = true;
			bounds.x = x;
			bounds.y = y;
			ganonCanon();
			handler.getMusicHandler().playEffect("Sword_Slash.wav");
		}
		//To do: Secondary Attack
//		if (handler.getKeyManager().shift == true) {
//			attackLow = true;
//			bounds.x = x;
//			bounds.y = y;
//			swordLow();
//			handler.getMusicHandler().playEffect("Sword_Slash.wav");
//		}
		
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_L)) {
			handler.changeState(handler.getFightingState());
		}

		
		
	
	
	
	
				
			}
		
	
	//------Ganon's Attacks Methods------
	public void ganonCanon() {
		// dir: flips the hitboxes for his attacks to his relative direction
		swordBounds = (Rectangle) bounds.clone();
		if (dir == "right") {
			swordBounds.x += 30;
			swordBounds.width+= 450;}
		else if (dir == "left") {
			swordBounds.x -= 400;
			swordBounds.width+= 450;
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
				swordBounds.y += 20;}
		
		if (hitCount > 0) {hitCount--;}
		else if (hitCount <= 0) {
			hitCount = 29; 
			swordBounds.x = 0;
			swordBounds.y = 0;}
		
	}
	
	@Override
	public void move(Direction direction) {
		moving = true;
		changeIntersectingBounds();
		//chack for collisions	
				
		//Movement
		bounds.width = 40 * 3;
				switch (direction) {
				case RIGHT:
					x += speed;
					break;
				case LEFT:
					x -= speed;
					
					break;
				case UP:
					//Nothing for now
					break;
				}
				bounds.x = x;
				bounds.y = y;
				changeIntersectingBounds();
				
	}
	@Override
	public void render(Graphics g) {//render all anims
		g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
		
			//Render Ganon's Idle sprite / mocking
			if (!attack && (movingTo != Direction.NONE)) {
			if (movingTo == Direction.DOWN) {
				if (dir == "right") {
					g.drawImage(sprite ,x, y+5, width , height  , null);}
				else if (dir == "left") {
					g.drawImage(Images.flipHorizontal(sprite) ,x, y+5, width , height  , null);}
				g.setColor(Color.RED);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
				g.drawString("Pathetic little fool!", x, y-10);
			}
			
			//Horizontal Movement render
			else if(movingTo == Direction.LEFT) {
	
				g.drawImage(sprite, x , y-10, sprite.getWidth() * 2, sprite.getHeight()  * 2   , null);
			}
			else if (movingTo == Direction.RIGHT) {
				
				g.drawImage(sprite, x , y-10, sprite.getWidth() * 2, sprite.getHeight()  * 2 , null);}
			}
			//adds directions to his anims depending to where he is looking
			else if (attack == true && dir == "right") {
				animation.tick();
				g.drawRect(swordBounds.x, swordBounds.y, swordBounds.width, swordBounds.height);
				g.drawImage(animation.getCurrentFrame() ,x+30 , y-50, animation.getCurrentFrame().getWidth() * 2 , animation.getCurrentFrame().getHeight() * 2 , null);
			}
			//The index is used to shift the animations (x,y) in order to have a coherent frame by frame anim.
			else if (attack == true && dir == "left") {
				animationL.tick();
				g.drawRect(swordBounds.x, swordBounds.y, swordBounds.width, swordBounds.height);
				if (animationL.getIndex() == 0) {
					g.drawImage(animationL.getCurrentFrame() ,x , y-50, animationL.getCurrentFrame().getWidth() * 2 , animationL.getCurrentFrame().getHeight() * 2 , null);
				}
				else if (animationL.getIndex() == 1) {
					g.drawImage(animationL.getCurrentFrame() ,x , y-50, animationL.getCurrentFrame().getWidth() * 2 , animationL.getCurrentFrame().getHeight() * 2 , null);
				}
				else if (animationL.getIndex() == 2) {
					g.drawImage(animationL.getCurrentFrame() ,x-350 , y-50, animationL.getCurrentFrame().getWidth() * 2 , animationL.getCurrentFrame().getHeight() * 2 , null);
				}
				else if (animationL.getIndex() == 3) {
					g.drawImage(animationL.getCurrentFrame() ,x-200 , y-50, animationL.getCurrentFrame().getWidth() * 2 , animationL.getCurrentFrame().getHeight() * 2 , null);
				}
				
			}
			//To do: add new attack's sprites
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

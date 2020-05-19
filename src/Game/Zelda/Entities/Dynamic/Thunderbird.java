package Game.Zelda.Entities.Dynamic;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import Game.Zelda.Entities.Dynamic.superWave;
import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Statics.moblinArrow;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Thunderbird extends BaseMovingEntity{
	Random random = new Random();
	Direction direction;
	int nextX,nextY;
	double distance=0;
	double distanceX=0;
	double distanceMovedX=0;
	double distancedToMoveX=0;
	double distanceY=0;
	double distanceMovedY=0;
	double distancedToMoveY=0;
	Direction movingTo;// Saves position for when laser is fired
	String act = "";
	int count=1, choice = 0;
	int moveCount= 120, flyCount = 20, loopity = 20;
	boolean bouncy=false;
	public superWave superWave;

	public Thunderbird(int x, int y, Handler handler) {
		super(x, y, Images.thunderBirdFrames, handler);
		
		animation = new Animation(90,sprites);	

	}
	@Override
	public void tick() {
		if(!dead) {
			animation.tick();
			if (moveCount > 0) {
				moveCount--;
			}
			else if (moveCount <= 0) {
				choice = random.nextInt(7);
				attacking = true;
				moveCount = 60;
			}
	//----------------------------//----------------------------//----------------------------//
			//------Attack Algorithm------
			if (choice <= 3) {
				act = "normal";
			}
			if (choice == 4 || choice == 5) {
				act = "dashAttack";
				//activates sideways attack
			}
			if (choice == 6) {
				act = "laserAttack";
			}
			
			if(act == "normal") {//chasing
				speed = 2;
				if (handler.getZeldaGameState().link.x > bounds.getCenterX()-1) {
					movingTo = Direction.RIGHT;
					direction = Direction.RIGHT;
				}
				else if (handler.getZeldaGameState().link.x < bounds.getCenterX()-1) {
					movingTo = Direction.LEFT;
					direction = Direction.LEFT;
				}
				else if (handler.getZeldaGameState().link.y < bounds.getCenterY()-1) {
					movingTo = Direction.UP;
					direction = Direction.UP;					
				}
				else if (handler.getZeldaGameState().link.y >  bounds.getCenterY()-1) {
					movingTo = Direction.DOWN;
					direction = Direction.DOWN;
				}
				move(direction);
			}
	//----------------------------//----------------------------//----------------------------//
			//---dashAttack---
			else if (act == "dashAttack") {
				speed = 4;
				if(handler.getZeldaGameState().link.x > x) {
					movingTo = Direction.RIGHT;
					direction = Direction.RIGHT;
					move(direction);
				}
				else if(handler.getZeldaGameState().link.x < x) {
					movingTo = Direction.LEFT;
					direction = Direction.LEFT;
					move(direction);
				}
			}
			
			else if (act == "laserAttack") {
				attacking = true;
				
				
			}
		}
	}

	public void superWave() {
		if(attacking) {
		//----------------------------//----------------------------//----------------------------//
		//Wave attack has a bit of range, x and y is shifted so it can be centered
					if (direction == direction.UP) {
						superWave = new superWave(this.x,this.y,Images.vaporWaveUp, handler,direction.UP);
						superWave.x = (int) bounds.getCenterX();
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(superWave);
						superWave.y = y - 32*2;
						superWave.x = x + 32;
						
					}
					else if (direction == direction.DOWN) {
						superWave = new superWave(this.x,this.y,Images.vaporWaveDown, handler,direction.DOWN);
						superWave.x = (int) bounds.getCenterX();
						superWave.y = y + 32*2;
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(superWave);
						superWave.x = x + 32;
					}
					else if (direction == direction.LEFT) {
						superWave = new superWave(this.x,this.y,Images.vaporWaveSideL, handler,direction.LEFT);
						superWave.y = (int) bounds.getCenterY() - 32*2;
						superWave.x = x - 32*2;
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(superWave);
					}
					else {
						superWave = new superWave(this.x,this.y,Images.vaporWaveSide, handler,direction.RIGHT);
						superWave.y = (int) bounds.getCenterY() - 32*2;
						superWave.x = x + 32*2;
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(superWave);
					
				}
					superWave.width = superWave.width * 3;
					superWave.height = superWave.height * 3;
					superWave.tick();
			}
		}
	
	@Override
	public void move(Direction direction) {
		moving = true;
		changeIntersectingBounds();
		//check for collisions	
		//Movement
				switch (direction) {
				case RIGHT:
					x += speed;
					break;
				case LEFT:
					x -= speed;
					
					break;
				case UP:
					y -= speed;
					break;
				case DOWN:
					y += speed;
					break;
				}
				bounds.x = x;
				bounds.y = y;
				changeIntersectingBounds();
				
	}
	@Override
	public void render(Graphics g) {
		if(!dead) {
			g.drawImage(animation.getCurrentFrame(),x,y,width,height,null);
		}
		if (attacking) {
			superWave();
			superWave.render(g);
		}

	}

	public void changeIntersectingBounds() {
		interactBounds = (Rectangle) bounds.clone();
	}

}

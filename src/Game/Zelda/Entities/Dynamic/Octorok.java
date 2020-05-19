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
public class Octorok extends BaseMovingEntity {

	private final int animSpeed = 120,knockback = 40;
	private double life=3.0;

	int randomDirection = 0;
	int newMapX=0,newMapY=0,xExtraCounter=0,yExtraCounter=0;
	int directionCounter = 60,attackCounter=30,hurtCounter=20;
	public boolean movingMap = false,hasSword=false,horray=false;
	Direction movingTo;
	Animation pickUpAnim,attackAnim,hurtAnim;

	public Octorok(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
		BufferedImage[] animList = new BufferedImage[2];
		animList[0] = sprite[0];
		animList[1] = sprite[1];
		animation = new Animation(animSpeed,animList);
		hurtAnim = new Animation(90,Images.octoHurtFrames);

		speed = 2;
		health = 3;


	}
	@Override
	public void tick() {
		//CounterArea
		if (hurtCounter > 0 && hurt) {hurtCounter--;}
		if (hurtCounter <= 0 && hurt) {hurtCounter = 20;hurt = false;}
		if(life<=0) {
			animation.tick();
			kill();
		}
		if(hurt) {
			hurtAnim.tick();
		}
		
		if (directionCounter > 0) {
			directionCounter--;
		}
		else if (directionCounter <= 0) {
			randomDirection = new Random().nextInt(4);
			directionCounter = 60;
		}
		BufferedImage[] animList = new BufferedImage[2];
		//------Basic Movement------
		switch (randomDirection) {
		case 0: 
			direction = direction.LEFT;
			animList[0] = sprites[2];
			animList[1] = sprites[3];
			animation = new Animation(animSpeed, animList);
			move(direction);
			animation.tick();
			break;

		case 1:
			direction = direction.RIGHT;
			animList[0] = Images.flipHorizontal(sprites[2]);
			animList[1] = Images.flipHorizontal(sprites[3]);
			animation = new Animation(animSpeed, animList);
			move(direction);
			animation.tick();
			break;

		case 2:
			direction = direction.UP;
			animList[0] = sprites[0];
			animList[1] = sprites[1];
			animation = new Animation(animSpeed, animList);
			move(direction);
			animation.tick();
			break;

		case 3:
			direction = direction.DOWN;
			animList[0] = sprites[0];
			animList[1] = sprites[1];
			animation = new Animation(animSpeed, animList);
			move(direction);
			animation.tick();
			break;
		}
		//------Differential Damages for weapons------
		if (handler.getZeldaGameState().link.hasSword) {
			if (bounds.intersects(handler.getZeldaGameState().link.swordBounds)) {
				if(handler.getZeldaGameState().link.swordHolding == "wood" && !(handler.getZeldaGameState().link.second)) {
					life -= 1;
					hurt=true;
				}
				if(handler.getZeldaGameState().link.swordHolding == "white" && !(handler.getZeldaGameState().link.second)) {
					life -= 1;
					hurt=true;
				}
				if(handler.getZeldaGameState().link.swordHolding == "magical"&& !(handler.getZeldaGameState().link.second)) {
					life -= 1;
					hurt=true;
				}
				if(handler.getZeldaGameState().link.swordHolding == "majora"&& !(handler.getZeldaGameState().link.second)) {
					life -= 3;
					hurt=true;
				}
				if(direction == Direction.LEFT) {
					x+=knockback;
				}else if(direction == Direction.RIGHT) {
					x-=knockback;
				}
				else if(direction == Direction.UP) {
					y+=knockback;
				}else if(direction == Direction.DOWN) {
					y-=knockback;
				}
			}

		}
		if (handler.getZeldaGameState().link.hasRod) {
			if (bounds.intersects(handler.getZeldaGameState().link.rodBounds)) {
			//System.out.println("runs shit" + " x,y enemy pendejo " + x + "," + y + " link pendejo " + handler.getZeldaGameState().link.rodBounds.x + "," +  handler.getZeldaGameState().link.rodBounds.y);
					if(handler.getZeldaGameState().link.second) {
					life -= 2;
					hurt=true;
					if(direction == Direction.LEFT) {
						x+=knockback;
					}else if(direction == Direction.RIGHT) {
						x-=knockback;
					}
					else if(direction == Direction.UP) {
						y+=knockback;
					}else if(direction == Direction.DOWN) {
						y-=knockback;
					}
				}
			}	
		}

	}

	@Override
	public void render(Graphics g) {
		animation.tick();
		g.drawImage(animation.getCurrentFrame(),x,y,width,height,null);

		if(horray&&!attacking) {
			g.drawImage(pickUpAnim.getCurrentFrame(),x , y, width , height  , null);
			g.drawImage(Images.npc[4],x , y -40, width/2 , height  , null);
		}
		if (hurt) {
			g.drawImage(hurtAnim.getCurrentFrame(),x , y, width , height, null); 
		}
	

	}


	@Override
	public void move(Direction direction) {
		moving = true;
		changeIntersectingBounds();
		


		for (SolidStaticEntities objects : handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
			if ((objects instanceof SectionDoor) && objects.bounds.intersects(bounds) && direction == ((SectionDoor) objects).direction) {
				directionCounter = 0;
				return;


			}
			else if (!(objects instanceof SectionDoor) && objects.bounds.intersects(interactBounds)) {
				//dont move
				//System.out.print("Collision");
				directionCounter = 0;
				return;
			}
		}

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
	public void damage(int amount){
		life-=amount;
		if (life<=0){
			kill();
		}
	}
	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}
}


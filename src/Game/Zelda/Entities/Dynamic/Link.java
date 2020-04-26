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

import static Game.GameStates.Zelda.ZeldaGameState.worldScale;
import static Game.Zelda.Entities.Dynamic.Direction.DOWN;
import static Game.Zelda.Entities.Dynamic.Direction.UP;

/**
 * Created by AlexVR on 3/15/2020
 */
public class Link extends BaseMovingEntity {


	private final int animSpeed = 120;
	private double life=3.0;


	int newMapX=0,newMapY=0,xExtraCounter=0,yExtraCounter=0;
	int celebrateCounter = 60,attackCounter=30,hurtCounter=20;
	public boolean movingMap = false,hasSword=false,horray=false,hurt=false;
	Direction movingTo;
	Animation pickUpAnim,attackAnim,hurtAnim;



	public Link(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
		speed = 4;
		health = 6;
		BufferedImage[] animList = new BufferedImage[2];
		animList[0] = sprite[4];
		animList[1] = sprite[5];
		
		animation = new Animation(animSpeed,animList);
		pickUpAnim = new Animation(500,Images.itemPickUpFrames);
		hurtAnim = new Animation(90,Images.linkHurtFrames);
		BufferedImage[] animList1 = new BufferedImage[4];
		animList1[0] = (Images.woodenSwordAttackFrames[8]);
		animList1[1] = (Images.woodenSwordAttackFrames[9]);
		animList1[2] = (Images.woodenSwordAttackFrames[10]);
		animList1[3] = (Images.woodenSwordAttackFrames[11]);
		attackAnim = new Animation(100,animList1);

	}

	@Override
	public void tick() {
		//Extra abilities for Link
		//To Do: add extra weapons if possible or abilities, magic, bow etc.
		//
		if(hurt) {
			hurtAnim.tick();
		}
		if (hurtCounter > 0 && hurt) {hurtCounter--;}
		if (hurtCounter <= 0 && hurt) {hurtCounter = 20;hurt = false;}
		if(attacking) {
			attackAnim.tick();
			attack(direction);
		}
		if (attackCounter > 0 && attacking) {attackCounter--;}
		if (attackCounter <= 0 && attacking) {attackCounter = 30; attacking = false;}
		if(!pickUpAnim.end && hasSword&& horray && celebrateCounter <= 0) {
			pickUpAnim.end = true;
			moving=true;
			horray=false;
			animation.tick();
		}
		else if (!pickUpAnim.end && hasSword&& horray&& celebrateCounter > 0) {celebrateCounter--;}
		if (handler.getKeyManager().shift == true) {
			speed = 5;}
		else {speed = 4;}

		if (movingMap){
			switch (movingTo) {
			case RIGHT:
				handler.getZeldaGameState().cameraOffsetX+=2;
				newMapX++;
				if (xExtraCounter>0){
					x-=11;
					xExtraCounter--;
					animation.tick();

				}else{
					x--;
				}
				break;
			case LEFT:
				handler.getZeldaGameState().cameraOffsetX-=2;
				newMapX--;
				if (xExtraCounter>0){
					x-=2;
					xExtraCounter--;
					animation.tick();

				}else{
					x++;
				}
				break;
			case UP:
				handler.getZeldaGameState().cameraOffsetY-=2;
				newMapY++;
				if (yExtraCounter>0){
					y-=2;
					yExtraCounter--;
					animation.tick();

				}else{
					y++;
				}
				break;
			case DOWN:
				handler.getZeldaGameState().cameraOffsetY+=2;
				newMapY--;
				if (yExtraCounter>0){
					y+=2;
					yExtraCounter--;
					animation.tick();
				}else{
					y--;
				}
				break;
			}
			bounds = new Rectangle(x,y,width,height);
			changeIntersectingBounds();
			if (newMapX == 0 && newMapY == 0){
				movingMap = false;
				movingTo = null;
				newMapX = 0;
				newMapY = 0;
			}
		}else {
			if (handler.getKeyManager().up&& !horray) {
				if (direction != UP) {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = sprites[4];
					animList[1] = sprites[5];
					animation = new Animation(animSpeed, animList);
					direction = UP;
					sprite = sprites[4];	
				}
				if(attacking) {
					BufferedImage[] animList1 = new BufferedImage[4];
					int Speed=100;
					animList1[0] = (Images.woodenSwordAttackFrames[8]);
					animList1[1] = (Images.woodenSwordAttackFrames[9]);
					animList1[2] = (Images.woodenSwordAttackFrames[10]);
					animList1[3] = (Images.woodenSwordAttackFrames[11]);
					attackAnim = new Animation(Speed, animList1);
				}
				if(!attacking&&!horray) {
					animation.tick();
					move(direction);
				}
			} else if (handler.getKeyManager().down&&!horray) {
				if (direction != DOWN) {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = sprites[0];
					animList[1] = sprites[1];
					animation = new Animation(animSpeed, animList);
					direction = DOWN;
					sprite = sprites[0];
				}
				if(attacking) {
					BufferedImage[] animList1 = new BufferedImage[4];
					int Speed=100;
					animList1[0] = (Images.woodenSwordAttackFrames[0]);
					animList1[1] = (Images.woodenSwordAttackFrames[1]);
					animList1[2] = (Images.woodenSwordAttackFrames[2]);
					animList1[3] = (Images.woodenSwordAttackFrames[3]);
					attackAnim = new Animation(Speed, animList1);
					//attacking=false;
				}
				if(!attacking&&!horray) {
					animation.tick();
					move(direction);
				}
			} else if (handler.getKeyManager().left&&!horray) {
				if (direction != Direction.LEFT) {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = Images.flipHorizontal(sprites[2]);
					animList[1] = Images.flipHorizontal(sprites[3]);
					animation = new Animation(animSpeed, animList);
					direction = Direction.LEFT;
					sprite = Images.flipHorizontal(sprites[3]);
				}
				if(attacking) {
					BufferedImage[] animList1 = new BufferedImage[4];
					int Speed=100;
					animList1[0] = (Images.flipHorizontal(Images.woodenSwordAttackFrames[4]));
					animList1[1] = (Images.flipHorizontal(Images.woodenSwordAttackFrames[5]));
					animList1[2] = (Images.flipHorizontal(Images.woodenSwordAttackFrames[6]));
					animList1[3] = (Images.flipHorizontal(Images.woodenSwordAttackFrames[7]));
					attackAnim = new Animation(Speed, animList1);
					//attacking=false;
				}
				if(!attacking&&!horray) {
					animation.tick();
					move(direction);
				}
			} else if (handler.getKeyManager().right&&!horray) {
				if (direction != Direction.RIGHT) {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = (sprites[2]);
					animList[1] = (sprites[3]);
					animation = new Animation(animSpeed, animList);
					direction = Direction.RIGHT;
					sprite = (sprites[3]);
				}
				if(attacking) {
					BufferedImage[] animList1 = new BufferedImage[4];
					int Speed=100;
					animList1[0] = (Images.woodenSwordAttackFrames[4]);
					animList1[1] = (Images.woodenSwordAttackFrames[5]);
					animList1[2] = (Images.woodenSwordAttackFrames[6]);
					animList1[3] = (Images.woodenSwordAttackFrames[7]);
					attackAnim = new Animation(Speed, animList1);
					//attacking=false;
				}
				if(!attacking&&!horray) {
					animation.tick();
					move(direction);
				}
				
			} else {
				moving = false;
			}
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
			attack(direction);
		}

		
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_H) && life < 3) {
			setLife(getLife() + 0.5);
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_J) && life >= 0.5) {
			setLife(getLife() - 0.5);
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
		if (attacking) {
			g.drawImage(attackAnim.getCurrentFrame(),x , y, attackAnim.getCurrentFrame().getWidth()*2 ,attackAnim.getCurrentFrame().getHeight()*2, null);
		}
		if (hurt&& !attacking) {
			g.drawImage(hurtAnim.getCurrentFrame(),x , y, width , height, null);
		}


	}

	@Override
	public void move(Direction direction) {
		moving = true;
		changeIntersectingBounds();
		//chack for collisions
		if (ZeldaGameState.inCave){
			for (SolidStaticEntities objects : handler.getZeldaGameState().caveObjects) {
				if ((objects instanceof caveSword) && objects.bounds.intersects(interactBounds)) {
					hasSword=true;
					pickUpAnim.tick();
					horray = true;
					moving=false;
				}
				if ((objects instanceof DungeonDoor) && objects.bounds.intersects(bounds) && direction == ((DungeonDoor) objects).direction) {
					if (((DungeonDoor) objects).name.equals("caveStartLeave")) {
						ZeldaGameState.inCave = false;
						x = ((DungeonDoor) objects).nLX + 20;
						y = ((DungeonDoor) objects).nLY + 10;
						direction = DOWN;
					}
				} else if (!(objects instanceof DungeonDoor) && objects.bounds.intersects(interactBounds)) {
					//dont move
					return;
				}
			}
		}
		else {
			for (BaseMovingEntity objects : handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
				if((objects instanceof Moblin)&&objects.bounds.intersects(bounds)) {
					hurt=true;
					life-=0.5;
					if(direction == Direction.LEFT) {
						x+=60;
					}else if(direction == Direction.RIGHT) {
						x-=60;
					}
					else if(direction == Direction.UP) {
						y+=60;
					}else if(direction == Direction.DOWN) {
						y-=60;
					}

				}
				if((objects instanceof Octorok)&&objects.bounds.intersects(bounds)) {
					hurt=true;
					life-=0.5;
					if(direction == Direction.LEFT) {
						x+=60;
					}else if(direction == Direction.RIGHT) {
						x-=60;
					}
					else if(direction == Direction.UP) {
						y+=60;
					}else if(direction == Direction.DOWN) {
						y-=60;
					}
				}
				if((objects instanceof Zora)&&objects.bounds.intersects(bounds)) {
					hurt=true;
					life-=0.5;
					if(direction == Direction.LEFT) {
						x+=60;
					}else if(direction == Direction.RIGHT) {
						x-=60;
					}
					else if(direction == Direction.UP) {
						y+=60;
					}else if(direction == Direction.DOWN) {
						y-=60;
					}
				}
				if((objects instanceof Leever)&&objects.bounds.intersects(bounds)) {
					hurt=true;
					life-=0.5;
					if(direction == Direction.LEFT) {
						x+=60;
					}else if(direction == Direction.RIGHT) {
						x-=60;
					}
					else if(direction == Direction.UP) {
						y+=60;
					}else if(direction == Direction.DOWN) {
						y-=60;
					}
				}
				if((objects instanceof BouncyFella)&&objects.bounds.intersects(bounds)) {
					hurt=true;
					life-=0.5;
					if(direction == Direction.LEFT) {
						x+=60;
					}else if(direction == Direction.RIGHT) {
						x-=60;
					}
					else if(direction == Direction.UP) {
						y+=60;
					}else if(direction == Direction.DOWN) {
						y-=60;
					}
				}
			}

			
			for (SolidStaticEntities objects : handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
				if ((objects instanceof SectionDoor) && objects.bounds.intersects(bounds) && direction == ((SectionDoor) objects).direction) {
					if (!(objects instanceof DungeonDoor)) {
						movingMap = true;
						movingTo = ((SectionDoor) objects).direction;
						switch (((SectionDoor) objects).direction) {
						case RIGHT:
							newMapX = -(((handler.getZeldaGameState().mapWidth) + 1) * 1/2 * worldScale);
							newMapY = 0;
							handler.getZeldaGameState().mapX++;
							xExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						case LEFT:
							newMapX = (((handler.getZeldaGameState().mapWidth) + 1) * 1/2 * worldScale);
							newMapY = 0;
							handler.getZeldaGameState().mapX--;
							xExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						case UP:
							newMapX = 0;
							newMapY = -(((handler.getZeldaGameState().mapHeight) + 1) * 1/2 * worldScale);
							handler.getZeldaGameState().mapY--;
							yExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						case DOWN:
							newMapX = 0;
							newMapY = (((handler.getZeldaGameState().mapHeight) + 1) * 1/2 * worldScale);
							handler.getZeldaGameState().mapY++;
							yExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						}
						return;
					}
					else {
						if (((DungeonDoor) objects).name.equals("caveStartEnter")) {
							ZeldaGameState.inCave = true;
							x = ((DungeonDoor) objects).nLX + 10;
							y = ((DungeonDoor) objects).nLY - 40;
							direction = UP;
						}
					}
				}
				else if (!(objects instanceof SectionDoor) && objects.bounds.intersects(interactBounds)) {
					//dont move
					return;
				}
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
	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}
}

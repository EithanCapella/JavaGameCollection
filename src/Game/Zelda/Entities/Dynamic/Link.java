package Game.Zelda.Entities.Dynamic;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Statics.DungeonDoor;
import Game.Zelda.Entities.Statics.SectionDoor;
import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Game.Zelda.Entities.Statics.caveSword;
import Game.Zelda.Entities.Statics.magicalRod;
import Game.Zelda.Entities.Statics.magicalSword;
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
 * Created by AlexVR on 3/15/2020
 */
public class Link extends BaseMovingEntity {

	private final int animSpeed = 120;
	private double life=3.0;
	int newMapX=0,newMapY=0,xExtraCounter=0,yExtraCounter=0;
	int celebrateCounter = 120,attackCounter=30,hurtCounter=20,count=0;
	public boolean movingMap = false,hasSword=false,horray=false,hurt=false,wooden=false,white=false,magical=false,rod=false;
	Direction movingTo;
	Animation pickUpAnim,attackAnim,hurtAnim;
	
	public Link(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
		speed = 4;
		health = 6;
		BufferedImage[] animList = new BufferedImage[2];
		BufferedImage[] animListPick = new BufferedImage[2];

		animList[0] = sprite[4];
		animList[1] = sprite[5];
		animation = new Animation(animSpeed,animList);
		animListPick[0] = Images.itemPickUpFrames[0];
		animListPick[1] = Images.itemPickUpFrames[1];
		pickUpAnim = new Animation(120,animListPick);
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
		//System.out.println("Link pos " + x + "," + y );
		//Extra abilities for Link
		//To Do: add extra weapons if possible or abilities, magic, bow etc.
		if(hurt) {
			hurtAnim.tick();
		}
		//CounterArea
		if (hurtCounter > 0 && hurt) {hurtCounter--;}
		if (hurtCounter <= 0 && hurt) {hurtCounter = 20;hurt = false;}
		if (attackCounter > 0 && attacking) {attackCounter--;}
		if (attackCounter <= 0 && attacking) {attackCounter = 30; attacking = false;}
		if (horray && celebrateCounter == 60) {pickUpAnim.tick();}
		if (horray && celebrateCounter > 0) {celebrateCounter--;}
		if (horray && celebrateCounter <= 0) {pickUpAnim.tick(); horray = false; celebratingMethod();}
		
		
	
		if (handler.getKeyManager().shift == true) {
			speed = 5;}
		else {speed = 4;}
		if (movingMap){
			switch (movingTo) {
			case RIGHT:
				handler.getZeldaGameState().cameraOffsetX+=3;
				newMapX++;
				if (xExtraCounter>0){
					x-=2;
					xExtraCounter--;
					animation.tick();

				}else{
					x--;
				}
				break;
			case LEFT:
				handler.getZeldaGameState().cameraOffsetX-=3;
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
				handler.getZeldaGameState().cameraOffsetY-=3;
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
				handler.getZeldaGameState().cameraOffsetY+=3;
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
				if(!attacking&&!horray) {
					animation.tick();
					move(direction);
				}	
			} else {
				moving = false;
			}
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && !attacking&&hasSword) {
			//attack(direction);
			attacking = true;
			
			attackingMethod();
		}
		
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_L)) {
			handler.changeState(handler.getZeldaMerchantState());
		}
		
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_H) && life < 3) {
			setLife(getLife() + 0.5);
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_J) && life >= 0.5) {
			setLife(getLife() - 0.5);
		}
	}

	public void celebratingMethod() {
		if (horray == true) {
			handler.getZeldaGameState().beginAdventure = false;			
			
			celebrateCounter = 60;
		}
		else if (horray == false) {
			animation.tick();
			
		}
	}
	
	public void attackingMethod() {
		if(attacking) {
			if (direction == direction.UP) {
				if(wooden && !(white&&magical&&rod)) {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.woodenSwordAttackFrames[8]);
					animList1[1] = (Images.woodenSwordAttackFrames[9]);
					animList1[2] = (Images.woodenSwordAttackFrames[10]);
					animList1[3] = (Images.woodenSwordAttackFrames[11]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(white&& !(wooden&&magical&&rod)) {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.whiteSwordAttackFrames[8]);
					animList1[1] = (Images.whiteSwordAttackFrames[9]);
					animList1[2] = (Images.whiteSwordAttackFrames[10]);
					animList1[3] = (Images.whiteSwordAttackFrames[11]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(magical&& !(white&&wooden&&rod)) {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.magicalSwordAttackFrames[8]);
					animList1[1] = (Images.magicalSwordAttackFrames[9]);
					animList1[2] = (Images.magicalSwordAttackFrames[10]);
					animList1[3] = (Images.magicalSwordAttackFrames[11]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(rod&& !(white&&magical&&wooden)) {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.magicalRodAttackFrames[8]);
					animList1[1] = (Images.magicalRodAttackFrames[9]);
					animList1[2] = (Images.magicalRodAttackFrames[10]);
					animList1[3] = (Images.magicalRodAttackFrames[11]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				}
			
			else if (direction == direction.DOWN) {
				if(wooden && !(white&&magical&&rod)) {
					BufferedImage[] animList1 = new BufferedImage[4];			
					animList1[0] = (Images.woodenSwordAttackFrames[0]);
					animList1[1] = (Images.woodenSwordAttackFrames[1]);
					animList1[2] = (Images.woodenSwordAttackFrames[2]);
					animList1[3] = (Images.woodenSwordAttackFrames[3]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(white&& !(wooden&&magical&&rod)) {
					BufferedImage[] animList1 = new BufferedImage[4];			
					animList1[0] = (Images.whiteSwordAttackFrames[0]);
					animList1[1] = (Images.whiteSwordAttackFrames[1]);
					animList1[2] = (Images.whiteSwordAttackFrames[2]);
					animList1[3] = (Images.whiteSwordAttackFrames[3]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(magical&& !(white&&wooden&&rod)) {
					BufferedImage[] animList1 = new BufferedImage[4];			
					animList1[0] = (Images.magicalSwordAttackFrames[0]);
					animList1[1] = (Images.magicalSwordAttackFrames[1]);
					animList1[2] = (Images.magicalSwordAttackFrames[2]);
					animList1[3] = (Images.magicalSwordAttackFrames[3]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(rod&& !(white&&magical&&wooden)) {
					BufferedImage[] animList1 = new BufferedImage[4];			
					animList1[0] = (Images.magicalRodAttackFrames[0]);
					animList1[1] = (Images.magicalRodAttackFrames[1]);
					animList1[2] = (Images.magicalRodAttackFrames[2]);
					animList1[3] = (Images.magicalRodAttackFrames[3]);
					attackAnim = new Animation(animSpeed, animList1);
				}
			}	
			else if (direction == direction.LEFT) {
				if(wooden && !(white&&magical&&rod)) {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.flipHorizontal(Images.woodenSwordAttackFrames[4]));
					animList1[1] = (Images.flipHorizontal(Images.woodenSwordAttackFrames[5]));
					animList1[2] = (Images.flipHorizontal(Images.woodenSwordAttackFrames[6]));
					animList1[3] = (Images.flipHorizontal(Images.woodenSwordAttackFrames[7]));
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(white&& !(wooden&&magical&&rod)) {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.flipHorizontal(Images.whiteSwordAttackFrames[4]));
					animList1[1] = (Images.flipHorizontal(Images.whiteSwordAttackFrames[5]));
					animList1[2] = (Images.flipHorizontal(Images.whiteSwordAttackFrames[6]));
					animList1[3] = (Images.flipHorizontal(Images.whiteSwordAttackFrames[7]));
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(magical&& !(white&&wooden&&rod)) {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.flipHorizontal(Images.magicalSwordAttackFrames[4]));
					animList1[1] = (Images.flipHorizontal(Images.magicalSwordAttackFrames[5]));
					animList1[2] = (Images.flipHorizontal(Images.magicalSwordAttackFrames[6]));
					animList1[3] = (Images.flipHorizontal(Images.magicalSwordAttackFrames[7]));
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(rod&& !(white&&magical&&wooden)) {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.flipHorizontal(Images.magicalRodAttackFrames[4]));
					animList1[1] = (Images.flipHorizontal(Images.magicalRodAttackFrames[5]));
					animList1[2] = (Images.flipHorizontal(Images.magicalRodAttackFrames[6]));
					animList1[3] = (Images.flipHorizontal(Images.magicalRodAttackFrames[7]));
					attackAnim = new Animation(animSpeed, animList1);
				}
			}
			else {
				if(wooden && !(white&&magical&&rod)) {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.woodenSwordAttackFrames[4]);
					animList1[1] = (Images.woodenSwordAttackFrames[5]);
					animList1[2] = (Images.woodenSwordAttackFrames[6]);
					animList1[3] = (Images.woodenSwordAttackFrames[7]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(white&& !(wooden&&magical&&rod)) {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.whiteSwordAttackFrames[4]);
					animList1[1] = (Images.whiteSwordAttackFrames[5]);
					animList1[2] = (Images.whiteSwordAttackFrames[6]);
					animList1[3] = (Images.whiteSwordAttackFrames[7]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(magical&& !(white&&wooden&&rod)) {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.magicalSwordAttackFrames[4]);
					animList1[1] = (Images.magicalSwordAttackFrames[5]);
					animList1[2] = (Images.magicalSwordAttackFrames[6]);
					animList1[3] = (Images.magicalSwordAttackFrames[7]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(rod&& !(white&&magical&&wooden)) {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.magicalRodAttackFrames[4]);
					animList1[1] = (Images.magicalRodAttackFrames[5]);
					animList1[2] = (Images.magicalRodAttackFrames[6]);
					animList1[3] = (Images.magicalRodAttackFrames[7]);
					attackAnim = new Animation(animSpeed, animList1);
				}
			}
		}
	}
	@Override
	public void render(Graphics g) {
		if (moving && !attacking && !horray) {
			g.drawImage(animation.getCurrentFrame(),x , y, width , height  , null);

		} else {
			if (movingMap && !attacking){
				g.drawImage(animation.getCurrentFrame(),x , y, width, height  , null);
			}
			if (!attacking && !horray) {
				g.drawImage(sprite, x , y, width , height , null);
			}
		}
		if(horray && !attacking && !moving ) {
			g.drawImage(pickUpAnim.getCurrentFrame(),x , y, width , height  , null);
			if(wooden && !(white&&magical&&rod)) {
				g.drawImage(Images.npc[4],x , y -40, width/2 , height  , null);
			}
			if(white&& !(wooden&&magical&&rod)) {
				g.drawImage(Images.otherWeapons[0],x , y -40, width/2 , height  , null);
			}
			if(magical&& !(white&&wooden&&rod)) {
				g.drawImage(Images.otherWeapons[1],x , y -40, width/2 , height  , null);
			}
			if(rod&& !(white&&magical&&wooden)) {
				g.drawImage(Images.otherWeapons[2],x , y -40, width/2 , height  , null);
			}
		}
		if (attacking && !horray) {  
			attackAnim.tick();
			if(direction == Direction.LEFT) {
				g.drawImage(attackAnim.getCurrentFrame(),this.x -(attackAnim.getCurrentFrame().getWidth()*handler.getZeldaGameState().worldScale-this.width) , y,attackAnim.getCurrentFrame().getWidth()*handler.getZeldaGameState().worldScale ,attackAnim.getCurrentFrame().getHeight()*handler.getZeldaGameState().worldScale, null);
			}
			else if(direction == Direction.UP) {
				g.drawImage(attackAnim.getCurrentFrame(),x , this.y -(attackAnim.getCurrentFrame().getHeight()*handler.getZeldaGameState().worldScale -this.height), attackAnim.getCurrentFrame().getWidth()*handler.getZeldaGameState().worldScale ,attackAnim.getCurrentFrame().getHeight()*handler.getZeldaGameState().worldScale, null);
			}else {g.drawImage(attackAnim.getCurrentFrame(),x , y, attackAnim.getCurrentFrame().getWidth()*handler.getZeldaGameState().worldScale ,attackAnim.getCurrentFrame().getHeight()*handler.getZeldaGameState().worldScale, null); }
		}
		if (hurt && !attacking && !horray) {
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
					pickUpAnim.tick();
					hasSword=true;
					horray = true;
					celebratingMethod();
					moving=false;
					wooden=true;
					count++;
					rod=false;
					white=false;
					magical=false;
					handler.getZeldaGameState().caveObjects.remove(objects);
				}
				if ((objects instanceof whiteSword) && objects.bounds.intersects(interactBounds)) {
					pickUpAnim.tick();
					hasSword=true;
					horray = true;
					celebratingMethod();
					moving=false;
					white=true;
					count++;
					rod=false;
					wooden=false;
					magical=false;
					handler.getZeldaGameState().caveObjects.remove(objects);

				}
				if ((objects instanceof magicalSword) && objects.bounds.intersects(interactBounds)) {
					pickUpAnim.tick();
					hasSword=true;
					horray = true;
					celebratingMethod();
					moving=false;
					magical=true;
					count++;
					rod=false;
					white=false;
					wooden=false;
					handler.getZeldaGameState().caveObjects.remove(objects);

				}
				if ((objects instanceof magicalRod) && objects.bounds.intersects(interactBounds)) {
					pickUpAnim.tick();
					hasSword=true;
					horray = true;
					celebratingMethod();
					moving=false;
					rod=true;
					count++;
					wooden=false;
					white=false;
					magical=false;
					handler.getZeldaGameState().caveObjects.remove(objects);

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
					if(moving) {
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
					if(attacking) {
						System.out.println("attacking hit");
						//handler.getOctorok().setHurt(true);
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
							newMapX = -(((handler.getZeldaGameState().mapWidth) + 1) * 1/3 * worldScale);
							newMapY = 0;
							handler.getZeldaGameState().mapX++;
							xExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						case LEFT:
							newMapX = (((handler.getZeldaGameState().mapWidth) + 1) * 1/3 * worldScale);
							newMapY = 0;
							handler.getZeldaGameState().mapX--;
							xExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						case UP:
							newMapX = 0;
							newMapY = -(((handler.getZeldaGameState().mapHeight) + 1) * 1/3 * worldScale);
							handler.getZeldaGameState().mapY--;
							yExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						case DOWN:
							newMapX = 0;
							newMapY = (((handler.getZeldaGameState().mapHeight) + 1) * 1/3 * worldScale);
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}
}

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

	private final int animSpeed = 120;
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
		//System.out.println( "Enemy pos" + x + "," + y);
		if (directionCounter > 0) {
			directionCounter--;
		}
		else if (directionCounter <= 0) {
			randomDirection = new Random().nextInt(4);
			directionCounter = 60;
		}
		BufferedImage[] animList = new BufferedImage[2];
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
		if (handler.getZeldaGameState().link.hasSword) {
			if (bounds.intersects(handler.getZeldaGameState().link.swordBounds)) {
				if(handler.getZeldaGameState().link.wooden && !(handler.getZeldaGameState().link.white&&handler.getZeldaGameState().link.magical&&handler.getZeldaGameState().link.majora&&handler.getZeldaGameState().link.second)) {
					life -= 1;
					hurt=true;
				}
				if(handler.getZeldaGameState().link.white&& !(handler.getZeldaGameState().link.wooden&&handler.getZeldaGameState().link.magical&&handler.getZeldaGameState().link.majora)) {
					life -= 1;
					hurt=true;
				}
				if(handler.getZeldaGameState().link.magical&& !(handler.getZeldaGameState().link.white&&handler.getZeldaGameState().link.wooden&&handler.getZeldaGameState().link.majora)) {
					life -= 1;
					hurt=true;
				}
				if(handler.getZeldaGameState().link.majora&& !(handler.getZeldaGameState().link.wooden&&handler.getZeldaGameState().link.magical&&handler.getZeldaGameState().link.white)) {
					life -= 3;
					hurt=true;
				}
				if(direction == Direction.LEFT) {
					x+=40;
				}else if(direction == Direction.RIGHT) {
					x-=40;
				}
				else if(direction == Direction.UP) {
					y+=40;
				}else if(direction == Direction.DOWN) {
					y-=40;
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
						x+=40;
					}else if(direction == Direction.RIGHT) {
						x-=40;
					}
					else if(direction == Direction.UP) {
						y+=40;
					}else if(direction == Direction.DOWN) {
						y-=40;
					}
				}
			}	
		}


		//			if (handler.getKeyManager().up&& !horray) {
		//				if (direction != UP) {
		//	
		//					direction = UP;
		//
		//				}
		//
		//				if(!attacking&&!horray) {
		//					animation.tick();
		//					move(direction);
		//				}
		//			} else if (handler.getKeyManager().down&&!horray) {
		//				if (direction != DOWN) {
		//					direction = DOWN;
		//				}
		//				if(!attacking&&!horray) {
		//
		//					move(direction);
		//				}
		//			} else if (handler.getKeyManager().left&&!horray) {
		//				if (direction != Direction.LEFT) {
		//
		//					direction = Direction.LEFT;
		//
		//				}
		//				
		//				if(!attacking&&!horray) {
		//					
		//					move(direction);
		//				}
		//			} else if (handler.getKeyManager().right&&!horray) {
		//				if (direction != Direction.RIGHT) {
		//					
		//					direction = Direction.RIGHT;
		//
		//				}
		//				if(attacking) {
		//				
		//				}
		//				if(!attacking&&!horray) {
		//					animation.tick();
		//					move(direction);
		//				}
		//				
		//			} else {
		//				moving = false;
		//			}
		//		

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
		//		if (attacking) {    
		//			if(direction == Direction.LEFT) {
		//				g.drawImage(attackAnim.getCurrentFrame(),this.x -(attackAnim.getCurrentFrame().getWidth()*2-this.width) , y,attackAnim.getCurrentFrame().getWidth()*2 ,attackAnim.getCurrentFrame().getHeight()*2, null);
		//			}
		//			if(direction == Direction.UP) {
		//				g.drawImage(attackAnim.getCurrentFrame(),x , this.y -(attackAnim.getCurrentFrame().getHeight()*2 -this.height), attackAnim.getCurrentFrame().getWidth()*2 ,attackAnim.getCurrentFrame().getHeight()*2, null);
		//			}else {g.drawImage(attackAnim.getCurrentFrame(),x , y, attackAnim.getCurrentFrame().getWidth()*2 ,attackAnim.getCurrentFrame().getHeight()*2, null); }
		//		}

	}


	@Override
	public void move(Direction direction) {
		moving = true;
		changeIntersectingBounds();
		//chack for collisions

		//			for (BaseMovingEntity objects : handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
		//				if((objects instanceof Moblin)&&objects.bounds.intersects(bounds)) {
		//					hurt=true;
		//					life-=0.5;
		//					if(direction == Direction.LEFT) {
		//						x+=60;
		//					}else if(direction == Direction.RIGHT) {
		//						x-=60;
		//					}
		//					else if(direction == Direction.UP) {
		//						y+=60;
		//					}else if(direction == Direction.DOWN) {
		//						y-=60;
		//					}
		//				}
		//				if((objects instanceof Octorok)&&objects.bounds.intersects(bounds)) {
		//					if(moving) {
		//						hurt=true;
		//						if(direction == Direction.LEFT) {
		//							x+=60;
		//						}else if(direction == Direction.RIGHT) {
		//							x-=60;
		//						}
		//						else if(direction == Direction.UP) {
		//							y+=60;
		//						}else if(direction == Direction.DOWN) {
		//							y-=60;
		//						}
		//					}
		//					if(attacking) {
		//						System.out.println("attacking hit");
		//						//handler.getOctorok().setHurt(true);
		//					}
		//				}
		//				if((objects instanceof Zora)&&objects.bounds.intersects(bounds)) {
		//					hurt=true;
		//					life-=0.5;
		//					if(direction == Direction.LEFT) {
		//						x+=60;
		//					}else if(direction == Direction.RIGHT) {
		//						x-=60;
		//					}
		//					else if(direction == Direction.UP) {
		//						y+=60;
		//					}else if(direction == Direction.DOWN) {
		//						y-=60;
		//					}
		//				}
		//				if((objects instanceof Leever)&&objects.bounds.intersects(bounds)) {
		//					hurt=true;
		//					life-=0.5;
		//					if(direction == Direction.LEFT) {
		//						x+=60;
		//					}else if(direction == Direction.RIGHT) {
		//						x-=60;
		//					}
		//					else if(direction == Direction.UP) {
		//						y+=60;
		//					}else if(direction == Direction.DOWN) {
		//						y-=60;
		//					}
		//				}
		//				if((objects instanceof BouncyFella)&&objects.bounds.intersects(bounds)) {
		//					hurt=true;
		//					life-=0.5;
		//					if(direction == Direction.LEFT) {
		//						x+=60;
		//					}else if(direction == Direction.RIGHT) {
		//						x-=60;
		//					}
		//					else if(direction == Direction.UP) {
		//						y+=60;
		//					}else if(direction == Direction.DOWN) {
		//						y-=60;
		//					}
		//				}
		//			}


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

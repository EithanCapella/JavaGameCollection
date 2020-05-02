package Game.Zelda.Entities.Dynamic;

import java.awt.Graphics;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Statics.SectionDoor;
import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Game.Zelda.Entities.Statics.moblinArrow;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Moblin extends BaseMovingEntity{
	protected int laserCooldown = new Random().nextInt(2000);
	private final int animSpeed = 200;
	private double life=3.0;

	int randomDirection = 0;
	int newMapX=0,newMapY=0,xExtraCounter=0,yExtraCounter=0;
	int directionCounter = 60,attackCounter=2000,hurtCounter=20;
	Direction movingTo;
	public moblinArrow moblinArrow;

	public Moblin(int x, int y,BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);

	}
	@Override
	public void tick() {
		if (attackCounter > 0 && attacking) {attackCounter--;}
		if (attackCounter <= 0 && attacking) {attackCounter = 2000; attacking = false;}
		if(!dead) {
			animation.tick();
			arrowMethod();

			if (directionCounter > 0) {
				directionCounter--;
			}
			else if (directionCounter <= 0) {
				randomDirection = new Random().nextInt(4);
				directionCounter = 60;
			}
			switch (randomDirection) {
			case 0: 
				direction = direction.LEFT;
				move(direction);
				break;

			case 1:
				direction = direction.RIGHT;
				move(direction);
				break;

			case 2:
				direction = direction.UP;
				move(direction);
				break;

			case 3:
				direction = direction.DOWN;
				move(direction);
				break;

			}
		}
	}
	@Override
	public void render(Graphics g) {
		if(!dead) {
			g.drawImage(animation.getCurrentFrame(),x,y,width,height,null);
		}
	}
	public void arrowMethod() {
		Random r =new Random();
		attacking=true;
		if(attacking) {
			if(r.nextInt(25)==1) {
				if(this.sprites==Images.dmoblinEnemyFrames) {
					if(animation.getIndex()==0) { //bottom frame
						moblinArrow = new moblinArrow(this.x,this.y,Images.dmoblinArrow[3], handler,direction.DOWN);
						handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(moblinArrow);
					}
					if(animation.getIndex()==1) { //top frame
						moblinArrow = new moblinArrow(this.x,this.y,Images.dmoblinArrow[0], handler,direction.UP);
						handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(moblinArrow);
					}
					if(animation.getIndex()==3) { //right side frame
						moblinArrow = new moblinArrow(this.x,this.y,Images.dmoblinArrow[2], handler,direction.RIGHT);
						handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(moblinArrow);
					}
					if(animation.getIndex()==5) { //left side frame
						moblinArrow = new moblinArrow(this.x,this.y,Images.flipHorizontal(Images.dmoblinArrow[2]), handler,direction.LEFT);
						handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(moblinArrow);
					}
				}else {
					if(animation.getIndex()==0) { //bottom frame
						moblinArrow = new moblinArrow(this.x,this.y,Images.moblinArrow[4], handler,direction.DOWN);
						handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(moblinArrow);
					}
					if(animation.getIndex()==1) { //top frame
						moblinArrow = new moblinArrow(this.x,this.y,Images.moblinArrow[0], handler,direction.UP);
						handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(moblinArrow);
					}
					if(animation.getIndex()==3) { //right side frame
						moblinArrow = new moblinArrow(this.x,this.y,Images.moblinArrow[1], handler,direction.RIGHT);
						handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(moblinArrow);
					}
					if(animation.getIndex()==5) { //left side frame
						moblinArrow = new moblinArrow(this.x,this.y,Images.flipHorizontal(Images.moblinArrow[2]), handler,direction.LEFT);
						handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(moblinArrow);
					}
				}
			}
		}
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

}

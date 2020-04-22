package Game.Zelda.Entities.Dynamic;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import Game.GameStates.Zelda.ZeldaGameState;
import Main.Handler;
import Resources.Images;

public class BouncyFella extends BaseMovingEntity{
	int nextX,nextY;
	double distance=0;
	double distanceX=0;
	double distanceMovedX=0;
	double distancedToMoveX=0;
	double distanceY=0;
	double distanceMovedY=0;
	double distancedToMoveY=0;
	int count=1;
	int movecount= 0;
	boolean bouncy=false;

	public BouncyFella(int x, int y, Handler handler) {
		super(x, y, Images.bouncyEnemyFrames, handler);
		interactBounds = (Rectangle) bounds.clone();
		count=new Random().nextInt(6*60)+3*60;

	}
	@Override
	public void tick() {
		if(!dead) {
			animation.tick();
//			if(! bouncy) {
//				count --;
//				if(count<=0) {
//					bouncy=true;
//					count=new Random().nextInt(6*60)+3*60;
//					nextX= new Random().nextInt(5)-2+x;
//					nextY= new Random().nextInt(5)-2+y;
//					if(nextX<=0) {
//						nextX=1;
//					}
//					if(nextY<=0) {
//						nextY=0;
//					}
//					distanceX=Math.abs(Point2D.distance(x,y,nextX,nextY));
//					distancedToMoveX=distanceX*((ZeldaGameState.stageWidth/16.0));
//					distanceY=Math.abs(Point2D.distance(x,y,nextX,nextY));
//					distancedToMoveY=distanceX*((ZeldaGameState.stageHeight/11.0));
//				}
//			}else {
//				movecount++;
//				if(movecount%4==0) {
//					movecount=0;
//					int tiles=1;
//					if(distanceX>=tiles) {
//						if(x>nextX) {
//							x-=speed;
//							distanceMovedX++;
//						}
//						else if(distanceX<=tiles){
//							x+=speed;
//							distanceMovedX++;
//						}
//						if(distanceMovedX%(ZeldaGameState.stageWidth/16.0)==0) {
//							distanceX--;
//						}
//					}
//					if(distanceY>=tiles) {
//						if(y>nextY) {
//							y-=speed;
//							distanceMovedY++;
//						}
//						else if(distanceY<=tiles){
//							y+=speed;
//							distanceMovedY++;
//
//						}
//						if(distanceMovedX%(ZeldaGameState.stageWidth/11.0)==0) {
//							distanceY--;
//						}
//						
//					}
//					if(distanceX<=0 && distanceY<=0) {
//						bouncy=false;
//					}
//
//				}	
//			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(!dead) {
			g.drawImage(animation.getCurrentFrame(),(x* (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(y* (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset,width,height,null);
		}

	}


	public void changeIntersectingBounds() {
		interactBounds = (Rectangle) bounds.clone();
	}

}

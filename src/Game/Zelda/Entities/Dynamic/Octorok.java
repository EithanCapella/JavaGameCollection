package Game.Zelda.Entities.Dynamic;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import Game.GameStates.Zelda.ZeldaGameState;
import Main.Handler;
import Resources.Images;

public class Octorok extends BaseMovingEntity{
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

	public Octorok(int x, int y, Handler handler) {
		super(x, y, Images.octorokEnemyFrames, handler);
		interactBounds = (Rectangle) bounds.clone();
		count=new Random().nextInt(6*60)+3*60;

	}
	@Override
	public void tick() {
		if(!dead) {
			animation.tick();

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

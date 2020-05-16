package Game.Zelda.Entities.Dynamic;



import java.awt.Graphics;

import java.awt.image.BufferedImage;

import Game.Zelda.Entities.Dynamic.Octorok;
import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Game.Zelda.Entities.Statics.bombBlock;
import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Dynamic.BaseMovingEntity;
import Game.Zelda.Entities.Dynamic.Direction;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class bombTile extends BaseMovingEntity{
	public Direction direction;
	Animation explosion;
	boolean explode;
	int bombCounter=75,explodeCounter=25;
	public bombTile(int x, int y, BufferedImage[] sprite, Handler handler,Direction direction) {
		super(x, y, sprite, handler);
		this.direction=direction;
		BufferedImage[] animList = new BufferedImage[2];
		animList[0] = sprite[0];
		animList[1] = sprite[1];
		animation = new Animation(60,animList);
		BufferedImage[] animList1 = new BufferedImage[3];
		animList1[0] = Images.bombExplosionsFrames[0];
		animList1[1] = Images.bombExplosionsFrames[1];
		animList1[2] = Images.bombExplosionsFrames[2];
		explosion = new Animation(60,animList1);


	}

	@Override
	public void render(Graphics g) {
		if (bombCounter <= 0) {
			g.drawImage(explosion.getCurrentFrame(),x , y, width*2 , height*2  , null);
		}else {
			g.drawImage(animation.getCurrentFrame(),x , y, width , height  , null);
		}

	}
	@Override
	public void tick() {
		collision();
		animation.tick();
		if (bombCounter > 0) {bombCounter--;}
		if (bombCounter <= 0) {
			explode=true;

		}
		if (explodeCounter > 0&&explode) {explodeCounter--;}
		if (explodeCounter <= 0) {
			explosion.end=true;			
		}
		if(explode) {
			explosion.tick();
		}


		//		switch (direction) {
		//		case RIGHT:
		//			this.x =32;
		//			break;
		//		case LEFT:
		//			this.x =-10;
		//			break;
		//		case UP:
		//			this.y=-10;
		//			break;
		//		case DOWN:
		//			this.y+= 32;
		//			break;
		//		}
		bounds.x= x;
		bounds.y = y;

	}
	public void collision() {
		changeIntersectingBounds();
		for (SolidStaticEntities objects : handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
			if((objects instanceof bombBlock)&&objects.bounds.intersects(bounds)) {
					if (explodeCounter <= 0) {
						handler.getZeldaGameState().link.boom=true;
					}
			}
		}
	}


}



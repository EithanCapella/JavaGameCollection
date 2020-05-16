package Game.Zelda.Entities.Dynamic;



import java.awt.Graphics;

import java.awt.image.BufferedImage;

import Game.Zelda.Entities.Dynamic.Octorok;
import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Dynamic.BaseMovingEntity;
import Game.Zelda.Entities.Dynamic.Direction;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class fireTile extends BaseMovingEntity{
    public Direction direction;
	public fireTile(int x, int y, BufferedImage[] sprite, Handler handler,Direction direction) {
		super(x, y, sprite, handler);
		this.direction=direction;
		BufferedImage[] animList = new BufferedImage[2];
		animList[0] = sprite[0];
		animList[1] = sprite[1];
		animation = new Animation(60,animList);	
		
	}
	
	@Override
	public void render(Graphics g) {
		animation.tick();
		g.drawImage(animation.getCurrentFrame(),x , y, width , height  , null);
	}
	@Override
	public void tick() {
		collision();
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
		for (BaseMovingEntity objects : handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
			if((objects instanceof Octorok)&&objects.bounds.intersects(bounds)) {
				objects.damage(1);
				handler.getZeldaGameState().link.projectile=true;
			}

		}
	}


	}



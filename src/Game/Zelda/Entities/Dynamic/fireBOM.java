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

public class fireBOM extends BaseMovingEntity{
	public fireBOM(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
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
		bounds.x= x;
		bounds.y = y;

		}
	public void collision() {
		changeIntersectingBounds();
		for (BaseMovingEntity objects : handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
			if((objects instanceof Octorok)&&objects.bounds.intersects(bounds)) {
				objects.damage(1);
				//handler.getZeldaGameState().link.projectile=true;
			}

		}
	}


	}



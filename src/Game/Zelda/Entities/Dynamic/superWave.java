package Game.Zelda.Entities.Dynamic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class superWave extends BaseMovingEntity{
	public Direction direction;
	public superWave(int x, int y, BufferedImage[] sprite, Handler handler,Direction direction) {
		super(x, y, sprite, handler);
		this.direction=direction;
		BufferedImage[] animList = new BufferedImage[4];
		animList[0] = sprite[0];
		animList[1] = sprite[1];
		animList[2] = sprite[2];
		animList[3] = sprite[3];
		animation = new Animation(30,animList);	
	}
	@Override
	public void render(Graphics g) {
		animation.tick();
		g.drawImage(animation.getCurrentFrame(),x , y, width , height  , null);
	}
	@Override
	public void tick() {
		collision();
		switch (direction) {
		case RIGHT:
			x+=4;
			break;
		case LEFT:
			x-=4;
			break;
		case UP:
			y-=4;
			break;
		case DOWN:
			y+=4;
			break;
		}
		bounds.x= x;
		bounds.y = y;

	}
	public void collision() {
		changeIntersectingBounds();
		for (BaseMovingEntity objects : handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
			if((objects instanceof Octorok)&&objects.bounds.intersects(bounds)) {
				objects.hurt=true;
				handler.getZeldaGameState().link.projectile=true;
				objects.damage(4);
				if(handler.getZeldaGameState().link.bookOfMagic) {
					handler.getZeldaGameState().link.BOM=true;
					handler.getZeldaGameState().link.fire=true;
					objects.damage(5);
				}
			}

		}
		for (BaseMovingEntity objects : handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY)) {
			if((objects instanceof Octorok)&&objects.bounds.intersects(bounds)) {
				objects.hurt=true;
				handler.getZeldaDungeonState().link.projectile=true;
				objects.damage(4);
				if(handler.getZeldaDungeonState().link.bookOfMagic) {
					handler.getZeldaDungeonState().link.BOM=true;
					handler.getZeldaDungeonState().link.fire=true;
					objects.damage(5);
				}
			}

		}		for (BaseMovingEntity objects : handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY)) {
			if((objects instanceof Octorok)&&objects.bounds.intersects(bounds)) {
				objects.hurt=true;
				handler.getZeldaOtherState().link.projectile=true;
				objects.damage(4);
				if(handler.getZeldaOtherState().link.bookOfMagic) {
					handler.getZeldaOtherState().link.BOM=true;
					handler.getZeldaOtherState().link.fire=true;
					objects.damage(5);
				}
			}

		}
	}

}
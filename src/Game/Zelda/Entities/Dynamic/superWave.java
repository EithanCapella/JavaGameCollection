package Game.Zelda.Entities.Dynamic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Handler;
import Resources.Animation;

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

	}
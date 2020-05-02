package Game.Zelda.Entities.Statics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Game.Galaga.Entities.BaseEntity;
import Game.Galaga.Entities.PlayerLaser;
import Game.Galaga.Entities.PlayerShip;
import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Dynamic.BaseMovingEntity;
import Game.Zelda.Entities.Dynamic.Direction;
import Main.Handler;
import Resources.Images;

public class moblinArrow extends SolidStaticEntities{
    public Direction direction;
	public moblinArrow(int x, int y, BufferedImage sprite, Handler handler,Direction direction) {
		super(x, y, sprite, handler);
		this.direction=direction;
	}
	@Override
	public void render(Graphics g) {
		g.drawImage(sprite,x,y ,width,height,null);
	}
	@Override
	public void tick() {
		//System.out.println("Arrow pos " + x + "," + y );
		switch (direction) {
		case RIGHT:
			x+=2;
			break;
		case LEFT:
			x-=2;
			break;
		case UP:
			y-=2;
			break;
		case DOWN:
			y+=2;
			break;
		}
		bounds.x= x;
		bounds.y = y;

		}

	}



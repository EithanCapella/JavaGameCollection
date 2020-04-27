package Game.Zelda.Entities.Statics;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Game.Galaga.Entities.BaseEntity;
import Game.Galaga.Entities.PlayerLaser;
import Game.Galaga.Entities.PlayerShip;
import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Dynamic.BaseMovingEntity;
import Main.Handler;
import Resources.Images;

public class swordLaser extends SolidStaticEntities{
	int speed = 6;
	public swordLaser(int x, int y, BufferedImage sprite, Handler handler) {
		super(x, y, sprite, handler);
	}
	@Override
	public void render(Graphics g) {
		g.drawImage(sprite, (x* (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(y* (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset,width,height,null);
	}
	@Override
	public void tick() {
		super.tick();
		y -= speed;
		bounds.y = y;
		for (BaseMovingEntity enemy :handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
			//	                if (enemy instanceof PlayerShip || enemy instanceof PlayerLaser) {
			//	                    continue;
			//	                }
			//	                if (enemy.bounds.intersects(bounds)) {
			//	                    enemy.damage(this);
			//	                }
		}

	}


}

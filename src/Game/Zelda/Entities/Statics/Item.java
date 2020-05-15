package Game.Zelda.Entities.Statics;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Dynamic.BaseMovingEntity;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Item extends SolidStaticEntities{
	public Item(int x, int y, BufferedImage sprite, Handler handler) {
		super(x, y, sprite, handler);
		
	}
	@Override
	public void render(Graphics g) {
		g.drawImage(sprite, x,y,width,height,null);
	}
	@Override
	public void tick() {
		bounds.x= x;
		bounds.y = y;

		}
//	public void item() {
//		if(this.sprites==Images.rupees) {
//    		handler.getZeldaGameState().link.setRupees(handler.getZeldaGameState().link.getRupees()+50);
//		}
//
//	}

	}



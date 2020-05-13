package Game.Zelda.Entities.Dynamic;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Game.Galaga.Entities.BaseEntity;
import Game.Galaga.Entities.PlayerLaser;
import Game.Galaga.Entities.PlayerShip;
import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Dynamic.BaseMovingEntity;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Items extends BaseMovingEntity{
    public Direction direction;
	public Items(int x, int y, BufferedImage[] sprite, Handler handler) {
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



package Game.Zelda.Entities.Statics;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Game.Galaga.Entities.BaseEntity;
import Game.Galaga.Entities.PlayerLaser;
import Game.Galaga.Entities.PlayerShip;
import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Dynamic.BaseMovingEntity;
import Game.Zelda.Entities.Dynamic.Direction;
import Game.Zelda.Entities.Dynamic.Octorok;
import Main.Handler;
import Resources.Images;

public class swordLaser extends SolidStaticEntities{
    public Direction direction;
    Rectangle interactBounds;
	int speed=4;

	public swordLaser(int x, int y, BufferedImage sprite, Handler handler,Direction direction) {
		super(x, y, sprite, handler);
		this.direction=direction;
        interactBounds = (Rectangle) bounds.clone();
        interactBounds.y+=(height/2);
        interactBounds.height/=2;
		
	}
	@Override
	public void render(Graphics g) {
		g.drawImage(sprite,x,y ,width,height,null);
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
				objects.damage(1);
				handler.getZeldaGameState().link.projectile=true;
			}
		}
	}
    public void changeIntersectingBounds() {
        interactBounds = (Rectangle) bounds.clone();
        interactBounds.y+=(height/2);
        interactBounds.height/=2;
        switch (direction){
            case DOWN:
                interactBounds.y+=speed;
                break;
            case UP:
                interactBounds.y-=speed;
                break;
            case LEFT:
                interactBounds.x-=speed;
                break;
            case RIGHT:
                interactBounds.x+=speed;
                break;
        }
    }


	}



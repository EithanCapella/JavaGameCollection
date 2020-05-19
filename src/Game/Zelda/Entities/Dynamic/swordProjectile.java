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

public class swordProjectile extends BaseMovingEntity{
	public Direction direction;
	fireTile fireTile;
	public swordProjectile(int x, int y, BufferedImage[] sprite, Handler handler,Direction direction) {
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
				if(sprites==Images.linkArrowsProjectileDown||sprites==Images.linkArrowsProjectileUp||sprites==Images.linkArrowsProjectileSide||sprites==Images.linkArrowsProjectileSide) {
					objects.damage(1);
					objects.hurt=true;
				}if(sprites==Images.rodWaveDown||sprites==Images.rodWaveUp||sprites==Images.rodWaveSide||sprites==Images.rodWaveSideL) {
					objects.hurt=true;
					handler.getZeldaGameState().link.projectile=true;
					objects.damage(3);
					if(handler.getZeldaGameState().link.bookOfMagic) {
						handler.getZeldaGameState().link.BOM=true;
						handler.getZeldaGameState().link.fire=true;
						objects.damage(3);
					}
				}
				else{objects.damage(2);objects.hurt=true;}
				handler.getZeldaGameState().link.projectile=true;
			}

		}
		for (BaseMovingEntity objects : handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY)) {
			if((objects instanceof Octorok)&&objects.bounds.intersects(bounds)) {
				if(sprites==Images.linkArrowsProjectileDown||sprites==Images.linkArrowsProjectileUp||sprites==Images.linkArrowsProjectileSide||sprites==Images.linkArrowsProjectileSide) {
					objects.damage(1);
					objects.hurt=true;
				}if(sprites==Images.rodWaveDown||sprites==Images.rodWaveUp||sprites==Images.rodWaveSide||sprites==Images.rodWaveSideL) {
					objects.hurt=true;
					handler.getZeldaDungeonState().link.projectile=true;
					objects.damage(3);
					if(handler.getZeldaDungeonState().link.bookOfMagic) {
						handler.getZeldaDungeonState().link.BOM=true;
						handler.getZeldaDungeonState().link.fire=true;
						objects.damage(3);
					}
				}
				else{objects.damage(2);objects.hurt=true;}
				handler.getZeldaDungeonState().link.projectile=true;
			}

		}		
		for (BaseMovingEntity objects : handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY)) {
			if((objects instanceof Octorok)&&objects.bounds.intersects(bounds)) {
				if(sprites==Images.linkArrowsProjectileDown||sprites==Images.linkArrowsProjectileUp||sprites==Images.linkArrowsProjectileSide||sprites==Images.linkArrowsProjectileSide) {
					objects.damage(1);
					objects.hurt=true;
				}if(sprites==Images.rodWaveDown||sprites==Images.rodWaveUp||sprites==Images.rodWaveSide||sprites==Images.rodWaveSideL) {
					objects.hurt=true;
					handler.getZeldaOtherState().link.projectile=true;
					objects.damage(3);
					if(handler.getZeldaOtherState().link.bookOfMagic) {
						handler.getZeldaOtherState().link.BOM=true;
						handler.getZeldaOtherState().link.fire=true;
						objects.damage(3);
					}
				}
				else{objects.damage(2);objects.hurt=true;}
				handler.getZeldaOtherState().link.projectile=true;
			}

		}
	}


}



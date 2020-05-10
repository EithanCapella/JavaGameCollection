package Game.Zelda.Entities.Dynamic;

import Game.GameStates.Zelda.ZeldaMMGameState;
import Game.GameStates.Zelda.ZeldaMapMakerState;
import Game.Zelda.Entities.BaseEntity;
import Game.Zelda.Entities.MMBaseEntity;
import Game.Zelda.Entities.Statics.MMMoveTile;
import Game.Zelda.Entities.Statics.MMSolidStaticEntities;
import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Game.Zelda.World.Map;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Game.Zelda.Entities.Dynamic.Direction.DOWN;
import static Game.Zelda.Entities.Dynamic.Direction.UP;

/**
 * Created by AlexVR on 3/15/2020
 */
public class MMLink extends MMBaseMovingEntity {


    private final int animSpeed = 120;
    public Map map;
    int speedRecur = 5;

    public MMLink(int x, int y, BufferedImage[] sprite, Handler handler) {
        super(x, y, sprite, handler);
        speed = 4;
        width = sprite[0].getWidth() * ZeldaMapMakerState.scale;
        height = sprite[0].getHeight()* ZeldaMapMakerState.scale;
        BufferedImage[] animList = new BufferedImage[2];
        animList[0] = sprite[4];
        animList[1] = sprite[5];

        animation = new Animation(animSpeed,animList);
    }

    @Override
    public void tick() {
    	
    	linkRecursion();

        
        
    		
    		
    			
    			
    		
            if (handler.getKeyManager().up) {
                if (direction != UP) {
                    BufferedImage[] animList = new BufferedImage[2];
                    animList[0] = sprites[4];
                    animList[1] = sprites[5];
                    animation = new Animation(animSpeed, animList);
                    direction = UP;
                    sprite = sprites[4];
                }
                animation.tick();
                move(direction);

            } else if (handler.getKeyManager().down) {
                if (direction != DOWN) {
                    BufferedImage[] animList = new BufferedImage[2];
                    animList[0] = sprites[0];
                    animList[1] = sprites[1];
                    animation = new Animation(animSpeed, animList);
                    direction = DOWN;
                    sprite = sprites[0];
                }
                animation.tick();
                move(direction);
            } else if (handler.getKeyManager().left) {
                if (direction != Direction.LEFT) {
                    BufferedImage[] animList = new BufferedImage[2];
                    animList[0] = Images.flipHorizontal(sprites[2]);
                    animList[1] = Images.flipHorizontal(sprites[3]);
                    animation = new Animation(animSpeed, animList);
                    direction = Direction.LEFT;
                    sprite = Images.flipHorizontal(sprites[3]);
                }
                animation.tick();
                move(direction);
            } else if (handler.getKeyManager().right) {
                if (direction != Direction.RIGHT) {
                    BufferedImage[] animList = new BufferedImage[2];
                    animList[0] = (sprites[2]);
                    animList[1] = (sprites[3]);
                    animation = new Animation(animSpeed, animList);
                    direction = Direction.RIGHT;
                    sprite = (sprites[3]);
                }
                animation.tick();
                move(direction);
            } else {
                moving = false;
            }

    }
    
   
    		
    			
    		
    public void linkRecursionHelper(MMMoveTile MoveTile) {
    	
    	if (MoveTile.sprite == (Images.recursiveTiles.get(0))) {
     		 y-=speedRecur;
     	 }
     	 else if (MoveTile.sprite == (Images.recursiveTiles.get(1))) {
     		 y+=speedRecur;
     	 }
     	 else if (MoveTile.sprite == (Images.recursiveTiles.get(2))) {
     		 x-=speedRecur;
     	 }
     	 else if (MoveTile.sprite == (Images.recursiveTiles.get(3))) {
     		 x+=speedRecur;
     	 	}
    	bounds.x = x;
    	bounds.y = y;
    	changeIntersectingBounds();
    	
    }  
    	
    //algorithm move link depending on tile THEN RECURSION is he standing on a tile or no
    boolean  reRun = true;
    public boolean linkRecursion() {
    	
    	if (reRun) {
    	for (MMBaseEntity entity: map.getBlocksOnMap()){
            if (entity instanceof MMMoveTile && entity.bounds.intersects(interactBounds)) {
            	linkRecursionHelper((MMMoveTile)entity);
            	reRun = false;
            	return linkRecursion();
            	
            }
           
    		}
    	 
    	}
    	else{
         	
         	return reRun = true;
         	}
		return reRun;
    }		


           
	
    		    		
    	
		
		
	
    
    @Override
    public void render(Graphics g) {
        if (moving) {
            g.drawImage(animation.getCurrentFrame(),x , y, width  , height   , null);
        } else {
            g.drawImage(sprite, x , y, width , height, null);
        }
    }

    @Override
    public void move(Direction direction) {
        moving = true;
        changeIntersectingBounds();
        //chack for collisions
        for (MMBaseEntity solidStaticEntities:map.getBlocksOnMap()){
            if (solidStaticEntities instanceof MMSolidStaticEntities && solidStaticEntities.bounds.intersects(interactBounds)){
                return;
            }
        }
        switch (direction) {
            case RIGHT:
                x += speed;
                map.xOffset-=speed;
                break;
            case LEFT:
                x -= speed;
                map.xOffset+=speed;

                break;
            case UP:
                y -= speed;
                map.yOffset+=speed;

                break;
            case DOWN:
                y += speed;
                map.yOffset-=speed;

                break;
        }
        bounds.x = x;
        bounds.y = y;
        changeIntersectingBounds();

    }
}

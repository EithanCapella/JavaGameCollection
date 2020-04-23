package Game.Zelda.Entities.Statics;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.BaseEntity;
import Game.Zelda.World.MapBuilder;
import Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 3/14/2020
 */
public class SolidStaticEntities extends BaseEntity {
    Rectangle interactBounds;

	public SolidStaticEntities(int x, int y, BufferedImage sprite, Handler handler) {
        super(x, y, sprite,handler);
        bounds = new Rectangle((x * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(y * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset,width,height);
        interactBounds = (Rectangle) bounds.clone();
        interactBounds.y+=(height/2);
        interactBounds.height/=2;

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprite,(x* (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(y* (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset,width,height,null);
    }

    public Rectangle getInteractBounds() {
		return interactBounds;
	}

	
}

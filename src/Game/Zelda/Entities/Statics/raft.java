package Game.Zelda.Entities.Statics;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Game.GameStates.Zelda.ZeldaGameState;
import Main.Handler;
import Resources.Images;

public class raft extends SolidStaticEntities{
	

	public raft(int x, int y, Handler handler,BufferedImage BuffedImage) {
		super(x, y, BuffedImage, handler);
	}
	@Override
	public void render(Graphics g) {
		g.drawImage(sprite, (x* (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(y* (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset,width,height,null);
	}


}

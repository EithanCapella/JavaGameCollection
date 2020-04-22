package Game.Zelda.Entities.Statics;


import java.awt.Graphics;

import Game.GameStates.Zelda.ZeldaGameState;
import Main.Handler;
import Resources.Images;

public class caveSword extends SolidStaticEntities{
	

	public caveSword(int x, int y, Handler handler) {
		super(x, y, Images.npc[4], handler);
	}
	@Override
	public void render(Graphics g) {
		g.drawImage(Images.npc[4], (x* (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(y* (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset,width,height,null);
	}


}

package Game.Zelda.Entities.Statics;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Dynamic.Direction;
import Main.Handler;

import java.awt.*;

/**
 * Created by AlexVR on 3/15/2020
 */
public class blockBound extends SolidStaticEntities {
	
   public blockBound(int x, int y,int widht, int height, Handler handler) {
       super(x, y, null, handler);
       this.width = widht;
       this.height = height;
       bounds = new Rectangle((x * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(y * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset,width,height);
   }

   @Override
   public void tick() {
       super.tick();

   }

   @Override
   public void render(Graphics g) {

       g.setColor(Color.white);
       g.fillRect((x * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(y * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset,width,height);

   }
}

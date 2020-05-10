package Game.Zelda.Entities.Statics;

import Game.GameStates.Zelda.ZeldaMMGameState;
import Game.Zelda.Entities.MMBaseEntity;
import Game.Zelda.Entities.Dynamic.Direction;
import Game.Zelda.Entities.Dynamic.MMLink;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class MMMoveTile extends MMBaseEntity {

	public int speedRecur = 4;

    public MMMoveTile(int x, int y, BufferedImage sprite, Handler handler) {
        super(x, y, sprite,handler);
        bounds = new Rectangle(x ,y ,width,height);

    }

    @Override
    public void tick() {
    	
    	
    	//System.out.println("Tile x,y " + x +" , "+ y + " Link x,y " + x + " , " + y);      
    }

 
    @Override
    public void render(Graphics g) {
        g.drawImage(sprite,x ,y,width,height,null);
    }
}

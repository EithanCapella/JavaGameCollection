package Game.GameStates.Zelda;

import Game.GameStates.State;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by AlexVR on 3/14/2020
 */
public class ZeldaMerchantState extends State {
	public boolean runOnce = false;
	public int size = 24;
    public ZeldaMerchantState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
    	
    }
    @Override
    public void render(Graphics g) {    
    	
        g.setColor(Color.WHITE);
    	g.drawRoundRect((int) (handler.getWidth() - handler.getWidth() * .90), handler.getHeight()/10, 400, 400, 50, 50);
    	g.drawRect((int) (handler.getWidth() - handler.getWidth() * .90), handler.getHeight()/2, 800, 400);//textbox merchant
    	g.drawRect((int) (handler.getWidth() - handler.getWidth() * .40), handler.getHeight()/2, 400, 400);//choices
    	g.setFont(new Font("TimesRoman", Font.PLAIN, size));
    	g.drawString("* Oh an adventurer! Feel free to browse my wares.", (int) (handler.getWidth() - handler.getWidth() * .85) , (int) (handler.getHeight()*.60));
    	g.drawString("* Browse or purchase an item", (int) (handler.getWidth() - handler.getWidth() * .35) , (int) (handler.getHeight()*.60));
    	
    
    }
    @Override
    public void refresh() {

    }
}

  
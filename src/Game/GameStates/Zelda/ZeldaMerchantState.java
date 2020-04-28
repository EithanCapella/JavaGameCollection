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
	public int size = 24, choice = 0;
	public String shopMode = "Choices";
    public ZeldaMerchantState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
    	if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)){
    		choice--;

        }else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
        	choice++;           
        }
    	if (choice > 3) {choice = 3;}
    	if (choice < 0) {choice = 0;}
    
    	if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
    		if (choice == 0) {
    			shopMode = "Wares";
    		}
    		else if (choice == 1) {
    			shopMode = "Info";
    		}
    		else if (choice == 2) {
    			shopMode = "Steal";
    		}
    		else if (choice == 3) {
    			shopMode = "Leave";
    		}
    	}
    	if (shopMode == "Leave") {
    		shopMode = "Choices";
    		handler.changeState(handler.getZeldaGameState());
    	}
    }
    @Override
    public void render(Graphics g) {    
    	
        g.setColor(Color.WHITE);
    	g.drawRoundRect((int) (handler.getWidth() - handler.getWidth() * .90), handler.getHeight()/10, 400, 400, 50, 50);
    	g.drawRect((int) (handler.getWidth() - handler.getWidth() * .90), handler.getHeight()/2, 800, 400);//textbox merchant
    	g.drawRect((int) (handler.getWidth() - handler.getWidth() * .40), handler.getHeight()/2, 400, 400);//choices
    	g.setFont(new Font("TimesRoman", Font.PLAIN, size));
    	
    	if (shopMode == "Choices") {
    	g.drawString("Oh an adventurer! Feel free to browse my wares.", (int) (handler.getWidth() - handler.getWidth() * .85) , (int) (handler.getHeight()*.60));
    	g.drawString("* Browse or purchase an item", (int) (handler.getWidth() - handler.getWidth() * .35) , (int) (handler.getHeight()*.55));
    	g.drawString("* Ask about rumors", (int) (handler.getWidth() - handler.getWidth() * .35) , (int) (handler.getHeight()*.60));
    	g.drawString("* Steal (You are not very nice)", (int) (handler.getWidth() - handler.getWidth() * .35) , (int) (handler.getHeight()*.65));
    	g.drawString("* Leave", (int) (handler.getWidth() - handler.getWidth() * .35) , (int) (handler.getHeight()*.70));}
    	
    	
    	
    	if (choice == 0) {
    		 g.drawImage(Images.galagaSelect,(int) (handler.getWidth() - handler.getWidth() * .38),(int) (handler.getHeight()* .52),32,32,null);
    	}
    	else if (choice == 1) {
    		 g.drawImage(Images.galagaSelect,(int) (handler.getWidth() - handler.getWidth() * .38),(int) (handler.getHeight()* .57),32,32,null);
    	}
    	else if (choice == 2) {
    		 g.drawImage(Images.galagaSelect,(int) (handler.getWidth() - handler.getWidth() * .38),(int) (handler.getHeight()* .62),32,32,null);
    	}
    	else if (choice == 3) {
    		 g.drawImage(Images.galagaSelect,(int) (handler.getWidth() - handler.getWidth() * .38),(int) (handler.getHeight()* .67),32,32,null);
    	}
    
    }
    @Override
    public void refresh() {

    }
}

  
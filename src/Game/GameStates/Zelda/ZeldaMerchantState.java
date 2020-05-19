package Game.GameStates.Zelda;

import Game.GameStates.State;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;


public class ZeldaMerchantState extends State {
	public boolean enter = false;
	public int size = 24, choice = 0, stealingCount = 60*2, stealingCount2 = 60*2, caser = 0, musicCounter = 0;
	public String shopMode = "Choices";
    public ZeldaMerchantState(Handler handler) {
        super(handler);
    }
    //TODO add shop mechanics Potion-40Rupees bomb-60  sword 140
    @Override
    public void tick() {
    	
    	if (musicCounter <= 0) {
    		musicCounter = 160 * 60;
    		handler.getMusicHandler().stopMusic();
    		handler.getMusicHandler().startMusic("ShopMerchant.wav");
    	}
    	else if (musicCounter > 0) {
    		musicCounter--;
    	}
    	
    	if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)){
    		handler.getMusicHandler().playEffect("choice.wav");
    		choice--;

        }else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
        	handler.getMusicHandler().playEffect("choice.wav");
        	choice++;           
        }
    	if (choice > 3) {choice = 3;}
    	if (choice < 0) {choice = 0;}
    
    	if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {enter = true;}
    		
    		//Choices Menu
    		if (shopMode == "Choices") {
    		if (choice == 0 && enter == true) {
    			enter = false;
    			shopMode = "Wares";
    		}
    		else if (choice == 1 && enter == true) {
    			enter = false;
    			shopMode = "Info";
    		}
    		else if (choice == 2 && enter == true) {
    			enter = false;
    			shopMode = "Steal";
    		}
    		else if (choice == 3 && enter == true) {
    			enter = false;
    			shopMode = "Leave";
    		}
    	}
    		//buying items
    		else if (shopMode == "Wares") {
    			//Buy potion, price 40
    			if (choice == 0 && enter == true) {
    				enter = false;
    				if (handler.getZeldaGameState().link.getRupees() >= 40) {
    					handler.getZeldaGameState().link.setRupees(handler.getZeldaGameState().link.getRupees() - 40);
    					handler.getZeldaGameState().link.addPotions(1);  				
    				}    				
        		}
        		else if (choice == 1 && enter == true) {
        			enter = false;
        			if (handler.getZeldaGameState().link.getRupees() >= 60) {
        				handler.getZeldaGameState().link.setBombs(handler.getZeldaGameState().link.getBombs() + 1);;
    				} 
        			//buys Bomb
        		}
        		else if (choice == 2 && enter == true) {
        			enter = false;
        			if (handler.getZeldaGameState().link.getRupees() >= 140) {
        				handler.getZeldaGameState().link.setRupees(handler.getZeldaGameState().link.getRupees() - 140);
        				handler.getZeldaGameState().link.swordHolding = "white";

    				} 
        			//buys new sword
        		}
        		else if (choice == 3 && enter == true) {
        			enter = false;
        			shopMode = "Choices";//Goes back to menu
        		}
    		}
    		else if (shopMode == "Info") {
    			
    			if (choice == 0 && enter == true) {
    				enter = false;
    				shopMode = "Stories";
        			//Ask about stories --lore-- gives a sense of direction of what to do
        		}
        		else if (choice == 1 && enter == true) {
        			enter = false;
        			shopMode = "Rumors";
        			caser = new Random().nextInt(4);
        			//Ask about rumors of treasures --hints to make the player go to dungeons
        		}
        		else if (choice == 2 && enter == true) {
        			enter = false;
        			shopMode = "Game";
        			//Ask about Zelda, the world and the swords
        		}
        		else if (choice == 3 && enter == true) {
        			enter = false;
        			shopMode = "Choices";//Goes back to menu
        		}
    		}
    		else if (shopMode == "Stories" && choice == 0 && enter) {
    			enter = false;
    			shopMode = "Info";
    		}
    		
    		else if (shopMode == "Rumors") {
    			if (choice == 0 && enter) {
    				enter = false;
    				caser = new Random().nextInt(4);

    			}
    			if (choice == 1 && enter) {
    				enter = false;
    				shopMode = "Info";

    			}
    		}
    		else if (shopMode == "Game") {
    			if (choice == 0 && enter) {
    				enter = false;
    				shopMode = "Info";
    			}
    		}
    		
    	
    	if (shopMode == "Steal") {
    		if (stealingCount > 0) {stealingCount--;}
    		else if (stealingCount <= 0) {
    			stealingCount = 60*2;
    			shopMode = "Punish";
    		}
    	}
    	
    	if (shopMode == "Leave" || shopMode == "Punish") {
    		if (stealingCount > 0) {stealingCount--;}
    		else if (stealingCount <= 0) {
    		handler.getZeldaGameState().link.setLife(handler.getZeldaGameState().link.getLife() - 1);
    		stealingCount = 60*2;
    		shopMode = "Choices";
    		handler.getMusicHandler().changeMusic("OverWorld.wav");
    		
    		handler.changeState(handler.getZeldaGameState());}
    	}
    }
    
    //------CHOICES AND MENU HUD------
    @Override
    public void render(Graphics g) {    
    	
        g.setColor(Color.WHITE);
    	g.drawRect((int) (handler.getWidth() - handler.getWidth() * .95), handler.getHeight()/10 - 64 , 402, 402);//merchantSprite
    	g.drawRect((int) (handler.getWidth() - handler.getWidth() * .95), handler.getHeight()/2, 750, 400);//textbox merchant
    	g.drawRect((int) (handler.getWidth() - handler.getWidth() * .42), handler.getHeight()/2, 450, 400);//choices
    	g.setFont(new Font("TimesRoman", Font.PLAIN, size));
    	
    	if (shopMode == "Choices") {
    	g.drawString("Oh an adventurer! Feel free to browse my wares.", (int) (handler.getWidth() - handler.getWidth() * .94) , (int) (handler.getHeight()*.60));
    	g.drawString("* Browse or purchase an item", (int) (handler.getWidth() - handler.getWidth() * .41) , (int) (handler.getHeight()*.55));
    	g.drawString("* Ask about rumors", (int) (handler.getWidth() - handler.getWidth() * .41) , (int) (handler.getHeight()*.60));
    	g.drawString("* Steal (You are not very nice)", (int) (handler.getWidth() - handler.getWidth() * .41) , (int) (handler.getHeight()*.65));
    	g.drawString("* Leave", (int) (handler.getWidth() - handler.getWidth() * .41) , (int) (handler.getHeight()*.70));
    	g.drawImage(Images.morshuWelcome,(int) (handler.getWidth() - handler.getWidth() * .95), handler.getHeight()/10 - 64 ,400,400,null);
    	}
    	
    	if (shopMode == "Wares") {
    		g.drawImage(Images.morshuSketchy,(int) (handler.getWidth() - handler.getWidth() * .95), handler.getHeight()/10 - 64 ,400,400,null);
    		g.drawString("Oh you want to shop, I have the goods as long as you have the coin!", (int) (handler.getWidth() - handler.getWidth() * .94) , (int) (handler.getHeight()*.60));
    		g.drawString("* Buy a potion", (int) (handler.getWidth() - handler.getWidth() * .41) , (int) (handler.getHeight()*.55));
        	g.drawString("* Buy a Bomb", (int) (handler.getWidth() - handler.getWidth() * .41) , (int) (handler.getHeight()*.60));
        	g.drawString("* Buy a new Sword", (int) (handler.getWidth() - handler.getWidth() * .41) , (int) (handler.getHeight()*.65));
        	g.drawString("* Go back", (int) (handler.getWidth() - handler.getWidth() * .41) , (int) (handler.getHeight()*.70));
        	
        	
    	}
    	if (shopMode == "Info") {
    		g.drawImage(Images.morshuSpeaking,(int) (handler.getWidth() - handler.getWidth() * .95), handler.getHeight()/10 - 64 ,400,400,null);
    		g.drawString("I see you seek information, what do you want?", (int) (handler.getWidth() - handler.getWidth() * .94) , (int) (handler.getHeight()*.60));
    		g.drawString("* Tell me about this place.", (int) (handler.getWidth() - handler.getWidth() * .41) , (int) (handler.getHeight()*.55));
        	g.drawString("* Have you heard about any weird rumors?", (int) (handler.getWidth() - handler.getWidth() * .41) , (int) (handler.getHeight()*.60));
        	g.drawString("* Tell be about the Legend of the Triforce", (int) (handler.getWidth() - handler.getWidth() * .41) , (int) (handler.getHeight()*.65));
        	g.drawString("* Go back", (int) (handler.getWidth() - handler.getWidth() * .41) , (int) (handler.getHeight()*.70));

    	}
    	if (shopMode == "Steal") {
    		if (stealingCount > 0) {
    		g.drawImage(Images.morshuThreat,(int) (handler.getWidth() - handler.getWidth() * .95), handler.getHeight()/10 - 64 ,400,400,null);
    		g.drawString("Keep your hands to yourself buddy!", (int) (handler.getWidth() - handler.getWidth() * .94) , (int) (handler.getHeight()*.60));
    		}
    	}
    	if (shopMode == "Punish") {
    		g.setFont(new Font("TimesRoman", Font.PLAIN, 36));
    		g.drawString("WHAT ARE YOU DOING!?!?!?!", (int) (handler.getWidth() - handler.getWidth() * .94) , (int) (handler.getHeight()*.60));
    		//damage link
    		g.drawImage(Images.morshuStealing,(int) (handler.getWidth() - handler.getWidth() * .95), handler.getHeight()/10 - 64 ,400,400,null);
    			
    		}
    	else if (shopMode == "Stories") {
    		g.drawImage(Images.morshuSpeaking,(int) (handler.getWidth() - handler.getWidth() * .95), handler.getHeight()/10 - 64 ,400,400,null);
    		if (choice > 0) {choice = 0;}
    		g.drawString("An old man told me you would be coming along. He said to tell you this: ", (int) (handler.getWidth() - handler.getWidth() * .94) , (int) (handler.getHeight()*.55));
    		g.drawString("There is a disturbance in our world, it has begun merging with the future ", (int) (handler.getWidth() - handler.getWidth() * .94) , (int) (handler.getHeight()*.60));
    		g.drawString("You are bound to experience events that will resonate with your Heroic Spirit ", (int) (handler.getWidth() - handler.getWidth() * .94) , (int) (handler.getHeight()*.65));
    		g.drawString("I don't know about this, kid. Be brave!", (int) (handler.getWidth() - handler.getWidth() * .94) , (int) (handler.getHeight()*.70));
    		
    		g.drawString("* Thank you for telling me that", (int) (handler.getWidth() - handler.getWidth() * .41) , (int) (handler.getHeight()*.55));
        	
    	}
    	else if (shopMode == "Rumors") {
    		g.drawImage(Images.morshuSketchy,(int) (handler.getWidth() - handler.getWidth() * .95), handler.getHeight()/10 - 64 ,400,400,null);
    		if (choice > 1) {choice = 1;}
    		g.drawString("* Tell me another rumor.", (int) (handler.getWidth() - handler.getWidth() * .41) , (int) (handler.getHeight()*.55));
        	g.drawString("* Thank you that is all for now.", (int) (handler.getWidth() - handler.getWidth() * .41) , (int) (handler.getHeight()*.60));
    		switch (caser) {//switches between the message displayed on the game over screen

    		case 0:
    			g.drawString("I heard that monsters sometimes carry keys", (int) (handler.getWidth() - handler.getWidth() * .94) , (int) (handler.getHeight()*.55));
     			g.drawString("It makes you think, are they sentient?", (int) (handler.getWidth() - handler.getWidth() * .94) , (int) (handler.getHeight()*.60));
    			break;

    		case 1:
    			g.drawString("I heard there is an item left behind by a past hero.", (int) (handler.getWidth() - handler.getWidth() * .94) , (int) (handler.getHeight()*.55));
     			g.drawString("Something about an musical Instrument.", (int) (handler.getWidth() - handler.getWidth() * .94) , (int) (handler.getHeight()*.60));
    			break;

    		case 2:
    			g.drawString("It is rumored that a special sword in this land.", (int) (handler.getWidth() - handler.getWidth() * .94) , (int) (handler.getHeight()*.55));
     			g.drawString("Legend says it has abilities beyond this world.", (int) (handler.getWidth() - handler.getWidth() * .94) , (int) (handler.getHeight()*.60));
    			break;

    		case 3:
    			g.drawString("At night it is rumored that laughter can be heard.", (int) (handler.getWidth() - handler.getWidth() * .94) , (int) (handler.getHeight()*.55));
     			g.drawString("It means danger to whom hears it, after a while a skeleton follows.", (int) (handler.getWidth() - handler.getWidth() * .94) , (int) (handler.getHeight()*.60));
    			break;
    		}
    	}
    	
    	else if (shopMode == "Game") {
    		if (choice > 0) {choice = 0;}
    		g.setFont(new Font("TimesRoman", Font.PLAIN, 22));
    		g.drawImage(Images.morshuSpeaking,(int) (handler.getWidth() - handler.getWidth() * .95), handler.getHeight()/10 - 64 ,400,400,null);
    		g.drawString("The princess of this kingdom has been kidnapped by Ganon.", (int) (handler.getWidth() - handler.getWidth() * .95) + 2 , (int) (handler.getHeight()*.55));
    		g.drawString("Impa, her nurse maid, set out to find a man with enough courage to defeat Ganon", (int) (handler.getWidth() - handler.getWidth() * .95) + 2 , (int) (handler.getHeight()*.60));
    		g.drawString("A young lad came to Impa's rescue and set out to save Zelda and the world. ", (int) (handler.getWidth() - handler.getWidth() * .95) + 2 , (int) (handler.getHeight()*.65));
    		g.drawString("I don't know what this lad is doing, but he better hurry up.", (int) (handler.getWidth() - handler.getWidth() * .95)+ 2 , (int) (handler.getHeight()*.70));
    		g.drawString("Having too many monsters around is bad for business. He better find the TriForce.", (int) (handler.getWidth() - handler.getWidth() * .95)-2 , (int) (handler.getHeight()*.75));
    	
    		g.drawString("Ok, thank you...", (int) (handler.getWidth() - handler.getWidth() * .41) , (int) (handler.getHeight()*.55));
    	}
    	
    	if (choice == 0) {
    		 g.drawImage(Images.triforcePointer,(int) (handler.getWidth() - handler.getWidth() * .45),(int) (handler.getHeight()* .52),32,32,null);
    	}
    	else if (choice == 1) {
    		 g.drawImage(Images.triforcePointer,(int) (handler.getWidth() - handler.getWidth() * .45),(int) (handler.getHeight()* .57),32,32,null);
    	}
    	else if (choice == 2) {
    		 g.drawImage(Images.triforcePointer,(int) (handler.getWidth() - handler.getWidth() * .45),(int) (handler.getHeight()* .62),32,32,null);
    	}
    	else if (choice == 3) {
    		 g.drawImage(Images.triforcePointer,(int) (handler.getWidth() - handler.getWidth() * .45),(int) (handler.getHeight()* .67),32,32,null);
    	}
    
    }
    @Override
    public void refresh() {

    }
}

  
  

  

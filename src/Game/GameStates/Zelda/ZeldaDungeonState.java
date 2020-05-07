package Game.GameStates.Zelda;

import Game.GameStates.State;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.Zelda.Entities.BaseEntity;
import Game.Zelda.Entities.Dynamic.BaseMovingEntity;
import Game.Zelda.Entities.Dynamic.BouncyFella;
import Game.Zelda.Entities.Dynamic.Direction;
import Game.Zelda.Entities.Dynamic.Leever;
import Game.Zelda.Entities.Dynamic.Link;
import Game.Zelda.Entities.Dynamic.Lynel;
import Game.Zelda.Entities.Dynamic.Moblin;
import Game.Zelda.Entities.Dynamic.Octorok;
import Game.Zelda.Entities.Dynamic.Thunderbird;
import Game.Zelda.Entities.Dynamic.Zora;
import Game.Zelda.Entities.Statics.DungeonDoor;
import Game.Zelda.Entities.Statics.Fire;
import Game.Zelda.Entities.Statics.oldMan;
import Game.Zelda.Entities.Statics.superSword;
import Game.Zelda.Entities.Statics.whiteSword;
import Game.Zelda.Entities.Statics.SectionDoor;
import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Game.Zelda.Entities.Statics.blockBound;
import Game.Zelda.Entities.Statics.caveSword;
import Game.Zelda.Entities.Statics.magicalRod;
import Game.Zelda.Entities.Statics.magicalSword;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by AlexVR on 3/14/2020
 */
public class ZeldaDungeonState extends State {
    public static int xOffset,yOffset,stageWidth,stageHeight,worldScale, animCount=0, deathCount=0;
    public int cameraOffsetX,cameraOffsetY;
    //map is 16 by 7 squares, you start at x=7,y=7 starts counting at 0
    public int mapX,mapY,mapWidth,mapHeight;
    public ArrayList<ArrayList<ArrayList<SolidStaticEntities>>> objects;
    //public ArrayList<ArrayList<ArrayList<BaseEntity>>> npc;
    public ArrayList<ArrayList<ArrayList<BaseMovingEntity>>> enemies;
    public Link link;
    public static boolean inCave = false, runOnce = false, beginAdventure=false,inTest=false;
    public ArrayList<SolidStaticEntities> caveObjects;
    public ArrayList<SolidStaticEntities> innObjects;
    ArrayList<BaseMovingEntity> toRemove;
   public ArrayList<BaseMovingEntity> monster;
   public ArrayList<SolidStaticEntities> solids;
    

    public ZeldaDungeonState(Handler handler) {
        super(handler);
        xOffset = handler.getWidth()/4;
        yOffset = handler.getHeight()/4;
        stageWidth = handler.getWidth()/3 + (handler.getWidth()/15);
        stageHeight = handler.getHeight()/2;
        worldScale = 2;
        mapX = 4;
        mapY = 4;
        mapWidth = 256;
        mapHeight = 176;
        cameraOffsetX =  ((mapWidth*mapX) + mapX + 1)*worldScale;
        cameraOffsetY = ((mapHeight*mapY) + mapY + 1)*worldScale;
        objects = new ArrayList<>();
        enemies = new ArrayList<>();
        caveObjects = new ArrayList<>();
        innObjects = new ArrayList<>();
        toRemove = new ArrayList<>();
        for (int i =0;i<16;i++){
            objects.add(new ArrayList<>());
            enemies.add(new ArrayList<>());
            //npc.add(new ArrayList<>());
            for (int j =0;j<8;j++) {
                objects.get(i).add(new ArrayList<>());
                enemies.get(i).add(new ArrayList<>());
                //npc.get(i).add(new ArrayList<>());
            }
        }
        addWorldObjects();

        link = handler.getZeldaGameState().link;

    }
    @Override
    public void tick() {
        link.tick();
    }
        		 
        	
        
    

    @Override
    public void render(Graphics g) {
    	g.drawImage(Images.zeldaDungeon, -cameraOffsetX + xOffset, -cameraOffsetY + yOffset, Images.zeldaDungeon.getWidth() * worldScale, Images.zeldaDungeon.getHeight() * worldScale, null);
    	if (!link.movingMap) {
    		for (SolidStaticEntities entity : objects.get(mapX).get(mapY)) {
    			entity.render(g);
    		}
    		for (BaseMovingEntity entity : enemies.get(mapX).get(mapY)) {
    			entity.render(g);
    		}
    	}
    	link.render(g);
    	g.setColor(Color.BLACK);
    	g.fillRect(0, 0, xOffset, handler.getHeight());
    	g.fillRect(xOffset + stageWidth, 0, handler.getWidth(), handler.getHeight());
    	g.fillRect(0, 0, handler.getWidth(), yOffset);
    	g.fillRect(0, yOffset + stageHeight, handler.getWidth(), handler.getHeight());


    	//rupees counter
    	g.setColor(Color.WHITE);
    	g.setFont(new Font("TimesRoman", Font.PLAIN, 48));
    	g.drawString(Integer.toString(link.getRupees()),handler.getWidth()/2 + (handler.getWidth()/6)+100,handler.getHeight()/3 - handler.getHeight()/16 + 10);

		if (animCount >= 0) {
		g.drawImage(Images.rupees[0],handler.getWidth()/2 + (handler.getWidth()/6)+25,handler.getHeight()/3 - handler.getHeight()/11,8*4,16*4,null);
		animCount-= 3;
		}
		else if (animCount < 0){
			g.drawImage(Images.rupees[1],handler.getWidth()/2 + (handler.getWidth()/6)+25,handler.getHeight()/3 - handler.getHeight()/11,8*4,16*4,null);
			animCount+= 2;
		}
	    
        if(link.getLife() == 3) {
			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 + (handler.getWidth()/6)+25,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 + (handler.getWidth()/6)+25 + 75,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 + (handler.getWidth()/6)+25 + 150,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
		}
        if(link.getLife() == 2.5) {
			g.drawImage(Images.linkHearts[1],handler.getWidth()/2 + (handler.getWidth()/6)+25,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 + (handler.getWidth()/6)+25 + 75,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 + (handler.getWidth()/6)+25 + 150,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
		}
		if(link.getLife() == 2) {
			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 + (handler.getWidth()/6)+25,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 + (handler.getWidth()/6)+25 + 75,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
		}
		if(link.getLife() == 1.5) {
			g.drawImage(Images.linkHearts[1],handler.getWidth()/2 + (handler.getWidth()/6)+25,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 + (handler.getWidth()/6)+25 + 75,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
		}
		if(link.getLife() == 1) {
			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 + (handler.getWidth()/6)+25,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
		}
		if(link.getLife() == 0.5) {
			g.drawImage(Images.linkHearts[1],handler.getWidth()/2 + (handler.getWidth()/6)+25,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
		}

    }
   
public BaseMovingEntity addEnemy() {
    	
    	for (BaseMovingEntity enemy : enemies.get(mapX).get(mapY)) {
    	if (enemy instanceof Octorok || enemy.dead) {
    		return enemy;
    		
    	
    		
    		}
    	}
		return null;
    }
    public void removeEnemy() {
    	
    	for (BaseMovingEntity enemy : monster) {
    		if (enemy instanceof Octorok || enemy.dead) {
    			
    			enemies.remove(addEnemy());
    			//enemies.remove(index);
    			
    			
    		}
    	
    	}
		
    }
    
    private void addWorldObjects() {

        
        //0,8
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new SectionDoor( 0,5,16*worldScale,16*worldScale, Direction.LEFT,handler));
        solids.add(new SectionDoor( 7,0,16*worldScale * 2,16*worldScale,Direction.UP,handler));
        solids.add(new DungeonDoor( 4,1,16*worldScale,16*worldScale,Direction.UP,"caveStartEnter",handler,(7 * (ZeldaDungeonState.stageWidth/16)) + ZeldaDungeonState.xOffset,(9 * (ZeldaDungeonState.stageHeight/11)) + ZeldaDungeonState.yOffset));
        solids.add(new SectionDoor( 15,5,16*worldScale,16*worldScale,Direction.RIGHT,handler));
        objects.get(7).set(0,solids);
        monster = new ArrayList<>();
//        monster.add(new BouncyFella(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.bouncyEnemyFrames, handler));
//        monster.add(new Octorok(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.octorokEnemyFrames, handler));
//        monster.add(new Leever(8,2,handler));
//        monster.add(new Zora(4,6,handler)); 
        monster.add(new Thunderbird(xOffset+(stageWidth/2),yOffset + (stageHeight/2),handler));
//        monster.add(new Moblin(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.moblinEnemyFrames,handler));
//        monster.add(new Moblin(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.dmoblinEnemyFrames,handler));
//        monster.add(new Lynel(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.lynelEnemyFrames ,handler));
//        monster.add(new Lynel(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.dlynelEnemyFrames ,handler));

        enemies.get(7).set(0,monster);
        
        
    }

    @Override
    public void refresh() {

    }
}

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
        mapX = 7;
        mapY = 1;
        mapWidth = 257;
        mapHeight = 179;
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
        link.dungeon=true;
		if (!link.movingMap) {
			for (SolidStaticEntities entity : objects.get(mapX).get(mapY)) {
				entity.tick();
			}
			for (BaseMovingEntity entity : enemies.get(mapX).get(mapY)) {
				entity.tick();
				if (entity.getInteractBounds().intersects(link.getInteractBounds())){
					link.damage(1);
				}
			}
		}
		ArrayList<BaseMovingEntity> toRemove = new ArrayList<>();
    	for (BaseMovingEntity enemy : enemies.get(mapX).get(mapY)) {
    	if (enemy instanceof Octorok && enemy.dead) {
    		toRemove.add(enemy);
    		}
    	}
    	for(BaseMovingEntity i: toRemove) {
    		//enemies.remove(i);
    		enemies.get(mapX).get(mapY).remove(i);
    	}

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
    	
    	//4,4 -> spawn area 
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*6,16*worldScale,handler)); // up part1
        solids.add(new blockBound( 9,0,16*worldScale*6,16*worldScale,handler)); // up part2
        solids.add(new blockBound( 0,8,16*worldScale*16,16*worldScale,handler)); //full down block 
        solids.add(new blockBound(15,0,16*worldScale,16*worldScale*9,handler)); //full-right side
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*9,handler));//full left side 
        solids.add(new SectionDoor( 7,0,16*worldScale*2,16*worldScale,Direction.UP,handler));
        
        //weird fishes 
        solids.add(new blockBound( 3,2,16*worldScale,16*worldScale,handler));
        solids.add(new blockBound( 3,4,16*worldScale,16*worldScale,handler));
        solids.add(new blockBound( 3,6,16*worldScale,16*worldScale,handler));
        solids.add(new blockBound( 6,2,16*worldScale,16*worldScale,handler));
        solids.add(new blockBound( 6,4,16*worldScale,16*worldScale,handler));
        solids.add(new blockBound( 6,6,16*worldScale,16*worldScale,handler));
        solids.add(new blockBound( 9,2,16*worldScale,16*worldScale,handler));
        solids.add(new blockBound( 9,4,16*worldScale,16*worldScale,handler));
        solids.add(new blockBound( 9,6,16*worldScale,16*worldScale,handler));
        solids.add(new blockBound( 12,2,16*worldScale,16*worldScale,handler));
        solids.add(new blockBound( 12,4,16*worldScale,16*worldScale,handler));
        solids.add(new blockBound( 12,6,16*worldScale,16*worldScale,handler));


        monster.add(new Octorok(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.octorokEnemyFrames, handler));

        objects.get(4).set(4,solids);
        enemies.get(4).set(4,monster);
        
    	//4,3 
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*6,16*worldScale,handler)); // up part1
        solids.add(new blockBound( 9,0,16*worldScale*6,16*worldScale,handler)); // up part2
        solids.add(new blockBound( 0,8,16*worldScale*6,16*worldScale,handler)); // down part1
        solids.add(new blockBound( 9,8,16*worldScale*6,16*worldScale,handler)); // down part2
        solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*4,handler));//right part1
        solids.add(new blockBound( 15,5,16*worldScale,16*worldScale*4,handler));//right part2
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*4,handler));//left part1
        solids.add(new blockBound( 0,5,16*worldScale,16*worldScale*4,handler));//left part2

        
        solids.add(new SectionDoor(15,4,16*worldScale,16*worldScale,Direction.RIGHT,handler));
        solids.add(new SectionDoor( 7,0,16*worldScale*2,16*worldScale,Direction.UP,handler));
        solids.add(new SectionDoor( 7,8,16*worldScale*2,16*worldScale,Direction.DOWN,handler));
        solids.add(new SectionDoor(0,4,16*worldScale,16*worldScale,Direction.LEFT,handler));
        
        solids.add(new blockBound( 3,2,16*worldScale,16*worldScale,handler));
        solids.add(new blockBound( 3,4,16*worldScale,16*worldScale,handler));
        solids.add(new blockBound( 3,6,16*worldScale,16*worldScale,handler));
        solids.add(new blockBound( 7,2,16*worldScale*2,16*worldScale,handler));
        solids.add(new blockBound( 7,4,16*worldScale*2,16*worldScale,handler));
        solids.add(new blockBound( 7,6,16*worldScale*2,16*worldScale,handler));
        solids.add(new blockBound( 12,2,16*worldScale,16*worldScale,handler));
        solids.add(new blockBound( 12,4,16*worldScale,16*worldScale,handler));
        solids.add(new blockBound( 12,6,16*worldScale,16*worldScale,handler));
        monster.add(new Octorok(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.octorokEnemyFrames, handler));
        objects.get(4).set(3,solids);
        enemies.get(4).set(3,monster);

        
    	//4,2
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new SectionDoor( 8,1,16*worldScale,16*worldScale,Direction.UP,handler));
        solids.add(new SectionDoor( 8,8,16*worldScale,16*worldScale,Direction.DOWN,handler));
        objects.get(4).set(2,solids);
        enemies.get(4).set(2,monster);
        
    	//4,1
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*16,16*worldScale,handler)); // up
        solids.add(new blockBound( 0,8,16*worldScale*6,16*worldScale,handler)); // down part1
        solids.add(new blockBound( 9,8,16*worldScale*6,16*worldScale,handler)); // down part2
        solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*4,handler));//right part1
        solids.add(new blockBound( 15,5,16*worldScale,16*worldScale*4,handler));//right part2
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*4,handler));//left part1
        solids.add(new blockBound( 0,5,16*worldScale,16*worldScale*4,handler));//left part2
        solids.add(new SectionDoor(14,4,16*worldScale,16*worldScale,Direction.RIGHT,handler));
        solids.add(new SectionDoor(1,4,16*worldScale,16*worldScale,Direction.LEFT,handler));
        solids.add(new SectionDoor( 8,8,16*worldScale,16*worldScale,Direction.DOWN,handler));
        objects.get(4).set(1,solids);
        enemies.get(4).set(1,monster);
        
    	//4,0
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new SectionDoor( 8,1,16*worldScale,16*worldScale,Direction.UP,handler));
        solids.add(new SectionDoor(1,4,16*worldScale,16*worldScale,Direction.LEFT,handler));
        solids.add(new SectionDoor(14,4,16*worldScale,16*worldScale,Direction.RIGHT,handler));
        objects.get(4).set(0,solids);
        enemies.get(4).set(0,monster);
        
    	//5,3 
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new SectionDoor( 8,1,16*worldScale,16*worldScale,Direction.UP,handler));
        solids.add(new SectionDoor(15,4,16*worldScale,16*worldScale,Direction.RIGHT,handler));
        solids.add(new SectionDoor(1,4,16*worldScale,16*worldScale,Direction.LEFT,handler));
        objects.get(5).set(3,solids);
        enemies.get(5).set(3,monster);
        
    	//5,2
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new SectionDoor( 8,8,16*worldScale,16*worldScale,Direction.DOWN,handler));
        objects.get(5).set(2,solids);
        enemies.get(5).set(2,monster);
    	//5,1
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*16,16*worldScale,handler)); // up
        solids.add(new blockBound( 0,8,16*worldScale*6,16*worldScale,handler)); // down part1
        solids.add(new blockBound( 9,8,16*worldScale*6,16*worldScale,handler)); // down part2
        solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*4,handler));//right part1
        solids.add(new blockBound( 15,5,16*worldScale,16*worldScale*4,handler));//right part2
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*4,handler));//left part1
        solids.add(new blockBound( 0,5,16*worldScale,16*worldScale*4,handler));//left part2
        solids.add(new SectionDoor(1,4,16*worldScale,16*worldScale,Direction.LEFT,handler));
        solids.add(new SectionDoor(15,4,16*worldScale,16*worldScale,Direction.RIGHT,handler));
        solids.add(new SectionDoor( 8,8,16*worldScale,16*worldScale,Direction.DOWN,handler));
        objects.get(5).set(1,solids);
        enemies.get(5).set(1,monster);
        
    	//6,3 
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new SectionDoor(1,4,16*worldScale,16*worldScale,Direction.LEFT,handler));
        objects.get(6).set(3,solids);
        enemies.get(6).set(3,monster);
        
    	//6,2 
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new SectionDoor( 8,1,16*worldScale,16*worldScale,Direction.UP,handler));
        solids.add(new SectionDoor( 8,8,16*worldScale,16*worldScale,Direction.DOWN,handler));
        solids.add(new SectionDoor(1,4,16*worldScale,16*worldScale,Direction.LEFT,handler));
        monster.add(new Octorok(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.octorokEnemyFrames, handler));
        objects.get(6).set(2,solids);
        enemies.get(6).set(2,monster);
        
    	//6,1
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new SectionDoor(15,4,16*worldScale,16*worldScale,Direction.RIGHT,handler));
        solids.add(new SectionDoor(1,4,16*worldScale,16*worldScale,Direction.LEFT,handler));
        solids.add(new blockBound( 0,0,16*worldScale*16,16*worldScale,handler)); // up
        solids.add(new blockBound( 0,8,16*worldScale*16,16*worldScale,handler)); // down
        solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*4,handler));//right part1
        solids.add(new blockBound( 15,5,16*worldScale,16*worldScale*4,handler));//right part2
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*4,handler));//left part1
        solids.add(new blockBound( 0,5,16*worldScale,16*worldScale*4,handler));//left part2
        objects.get(6).set(1,solids);
        enemies.get(6).set(1,monster);
       
    	//7,2
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new SectionDoor(1,4,16*worldScale,16*worldScale,Direction.LEFT,handler));
        objects.get(7).set(2,solids);
        enemies.get(7).set(2,monster);
        
    	//7,1-> BOSS AREA
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*6,16*worldScale,handler)); // up part1
        solids.add(new blockBound( 9,0,16*worldScale*6,16*worldScale,handler)); // up part2
        solids.add(new blockBound( 0,8,16*worldScale*16,16*worldScale,handler)); // down
        solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*9,handler));//right
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*4,handler));//left part1
        solids.add(new blockBound( 0,5,16*worldScale,16*worldScale*4,handler));//left part2
        solids.add(new SectionDoor( 7,1,16*worldScale*2,16*worldScale,Direction.UP,handler));
        solids.add(new SectionDoor(0,4,16*worldScale,16*worldScale,Direction.LEFT,handler));
        monster.add(new Thunderbird(xOffset+(stageWidth/2),yOffset + (stageHeight/2),handler));
        objects.get(7).set(1,solids);
        enemies.get(7).set(1,monster);
    
        //7,0 
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*16,16*worldScale,handler)); // up 
        solids.add(new blockBound( 0,8,16*worldScale*6,16*worldScale,handler)); // down part1
        solids.add(new blockBound( 9,8,16*worldScale*6,16*worldScale,handler)); // down part2
        solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*9,handler));//right 
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*9,handler));//left 
        solids.add(new SectionDoor( 8,8,16*worldScale,16*worldScale,Direction.DOWN,handler));
        objects.get(7).set(0,solids);
        enemies.get(7).set(0,monster);
        
        
    	//3,4 
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new SectionDoor( 8,1,16*worldScale,16*worldScale,Direction.UP,handler)); //bomb
        solids.add(new SectionDoor(15,4,16*worldScale,16*worldScale,Direction.RIGHT,handler));
        objects.get(3).set(4,solids);
        enemies.get(3).set(4,monster);
        
    	//3,3
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*6,16*worldScale,handler)); // up part1
        solids.add(new blockBound( 9,0,16*worldScale*6,16*worldScale,handler)); // up part2
        solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*4,handler));//right part1
        solids.add(new blockBound( 15,5,16*worldScale,16*worldScale*4,handler));//right part2
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*4,handler));//left part1
        solids.add(new blockBound( 0,5,16*worldScale,16*worldScale*4,handler));//left part2
        solids.add(new blockBound( 0,8,16*worldScale*16,16*worldScale,handler)); //full down block 
        solids.add(new SectionDoor(1,4,16*worldScale,16*worldScale,Direction.LEFT,handler));
        solids.add(new SectionDoor(15,4,16*worldScale,16*worldScale,Direction.RIGHT,handler));
        solids.add(new SectionDoor( 7,0,16*worldScale*2,16*worldScale,Direction.UP,handler)); 
        objects.get(3).set(3,solids);
        enemies.get(3).set(3,monster);
        
    	//3,2 
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new SectionDoor( 8,1,16*worldScale,16*worldScale,Direction.UP,handler));
        solids.add(new SectionDoor( 8,8,16*worldScale,16*worldScale,Direction.DOWN,handler));
        objects.get(3).set(2,solids);
        enemies.get(3).set(2,monster);
        
    	//3,1
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*6,16*worldScale,handler)); // up part1
        solids.add(new blockBound( 9,0,16*worldScale*6,16*worldScale,handler)); // up part2
        solids.add(new blockBound( 0,8,16*worldScale*6,16*worldScale,handler)); // down part1
        solids.add(new blockBound( 9,8,16*worldScale*6,16*worldScale,handler)); // down part2
        solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*4,handler));//right part1
        solids.add(new blockBound( 15,5,16*worldScale,16*worldScale*4,handler));//right part2
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*9,handler));//left 
        solids.add(new SectionDoor(15,4,16*worldScale,16*worldScale,Direction.RIGHT,handler));
        solids.add(new SectionDoor( 8,1,16*worldScale,16*worldScale,Direction.UP,handler));
        solids.add(new SectionDoor( 8,8,16*worldScale,16*worldScale,Direction.DOWN,handler));
        objects.get(3).set(1,solids);
        enemies.get(3).set(1,monster);
        
    	//3,0
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*6,16*worldScale,handler)); // up part1
        solids.add(new blockBound( 9,0,16*worldScale*6,16*worldScale,handler)); // up part2
        solids.add(new blockBound( 0,8,16*worldScale*6,16*worldScale,handler)); // down part1
        solids.add(new blockBound( 9,8,16*worldScale*6,16*worldScale,handler)); // down part2
        solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*4,handler));//right part1
        solids.add(new blockBound( 15,5,16*worldScale,16*worldScale*4,handler));//right part2
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*4,handler));//left part1
        solids.add(new blockBound( 0,5,16*worldScale,16*worldScale*4,handler));//left part2
        solids.add(new SectionDoor(15,4,16*worldScale,16*worldScale,Direction.RIGHT,handler));
        solids.add(new SectionDoor( 8,8,16*worldScale,16*worldScale,Direction.DOWN,handler));
        solids.add(new SectionDoor(1,4,16*worldScale,16*worldScale,Direction.LEFT,handler));
        objects.get(3).set(0,solids);
        enemies.get(3).set(0,monster);
        
    	//2,3
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*6,16*worldScale,handler)); // up part1
        solids.add(new blockBound( 9,0,16*worldScale*6,16*worldScale,handler)); // up part2
        solids.add(new blockBound( 0,8,16*worldScale*16,16*worldScale,handler)); // full down 
        solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*4,handler));//right part1
        solids.add(new blockBound( 15,5,16*worldScale,16*worldScale*4,handler));//right part2
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*4,handler));//left part1
        solids.add(new blockBound( 0,5,16*worldScale,16*worldScale*4,handler));//left part2
        solids.add(new SectionDoor( 8,1,16*worldScale,16*worldScale,Direction.UP,handler));
        solids.add(new SectionDoor(1,4,16*worldScale,16*worldScale,Direction.LEFT,handler));
        solids.add(new SectionDoor(15,4,16*worldScale,16*worldScale,Direction.RIGHT,handler));
        objects.get(2).set(3,solids);
        enemies.get(2).set(3,monster);
        
        //2,2
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new SectionDoor( 8,8,16*worldScale,16*worldScale,Direction.DOWN,handler));
        solids.add(new SectionDoor(15,4,16*worldScale,16*worldScale,Direction.RIGHT,handler));
        objects.get(2).set(1,solids);
        enemies.get(2).set(1,monster);
        //2,1
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new SectionDoor(15,4,16*worldScale,16*worldScale,Direction.RIGHT,handler));  
        solids.add(new SectionDoor(1,4,16*worldScale,16*worldScale,Direction.LEFT,handler));
        objects.get(2).set(1,solids);
        enemies.get(2).set(1,monster);
        
        //2,0
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*16,16*worldScale,handler)); // up 
        solids.add(new blockBound( 0,8,16*worldScale*16,16*worldScale,handler)); // down
        solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*4,handler));//right part1
        solids.add(new blockBound( 15,5,16*worldScale,16*worldScale*4,handler));//right part2
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*4,handler));//left part1
        solids.add(new blockBound( 0,5,16*worldScale,16*worldScale*4,handler));//left part2
        solids.add(new SectionDoor(15,4,16*worldScale,16*worldScale,Direction.RIGHT,handler));  
        solids.add(new SectionDoor(1,4,16*worldScale,16*worldScale,Direction.LEFT,handler));
        objects.get(2).set(0,solids);
        enemies.get(2).set(0,monster);
        
    	//1,3
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*6,16*worldScale,handler)); // up part1
        solids.add(new blockBound( 9,0,16*worldScale*6,16*worldScale,handler)); // up part2
        solids.add(new blockBound( 0,8,16*worldScale*16,16*worldScale,handler)); // down part1
        solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*4,handler));//right part1
        solids.add(new blockBound( 15,5,16*worldScale,16*worldScale*4,handler));//right part2
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*9,handler));//left part1
        solids.add(new SectionDoor( 7,1,16*worldScale*2,16*worldScale,Direction.UP,handler)); 
        solids.add(new SectionDoor(14,4,16*worldScale,16*worldScale,Direction.RIGHT,handler));
        objects.get(1).set(3,solids);
        enemies.get(1).set(3,monster);
        
        //1,2
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*6,16*worldScale,handler)); // up part1
        solids.add(new blockBound( 9,0,16*worldScale*6,16*worldScale,handler)); // up part2
        solids.add(new blockBound( 0,8,16*worldScale*6,16*worldScale,handler)); // down part1
        solids.add(new blockBound( 10,8,16*worldScale*6,16*worldScale,handler)); // down part2
        solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*9,handler));//right full
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*4,handler));//left part1
        solids.add(new blockBound( 0,5,16*worldScale,16*worldScale*4,handler));//left part2
        solids.add(new SectionDoor( 7,1,16*worldScale*2,16*worldScale,Direction.UP,handler)); 
        solids.add(new SectionDoor(1,4,16*worldScale,16*worldScale,Direction.LEFT,handler));
        solids.add(new SectionDoor( 7,8,16*worldScale*2,16*worldScale,Direction.DOWN,handler)); 
        objects.get(1).set(2,solids);
        enemies.get(1).set(2,monster);
        
        //1,1
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*6,16*worldScale,handler)); // up part1
        solids.add(new blockBound( 9,0,16*worldScale*6,16*worldScale,handler)); // up part2
        solids.add(new blockBound( 0,8,16*worldScale*6,16*worldScale,handler)); // down part1
        solids.add(new blockBound( 9,8,16*worldScale*6,16*worldScale,handler)); // down part2
        solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*4,handler));//right part1
        solids.add(new blockBound( 15,5,16*worldScale,16*worldScale*4,handler));//right part2
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*4,handler));//left part1
        solids.add(new blockBound( 0,5,16*worldScale,16*worldScale*4,handler));//left part2
        solids.add(new SectionDoor( 7,1,16*worldScale*2,16*worldScale,Direction.UP,handler)); 
        solids.add(new SectionDoor(14,4,16*worldScale,16*worldScale,Direction.RIGHT,handler));
        solids.add(new SectionDoor(1,4,16*worldScale,16*worldScale,Direction.LEFT,handler));
        solids.add(new SectionDoor( 7,8,16*worldScale*2,16*worldScale,Direction.DOWN,handler)); 
        objects.get(1).set(1,solids);
        enemies.get(1).set(1,monster);
        
    	//1,0
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*16,16*worldScale,handler)); // up 
        solids.add(new blockBound( 0,8,16*worldScale*6,16*worldScale,handler)); // down part1
        solids.add(new blockBound( 9,8,16*worldScale*6,16*worldScale,handler)); // down part2
        solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*4,handler));//right part1
        solids.add(new blockBound( 15,6,16*worldScale,16*worldScale*4,handler));//right part2
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*9,handler));//left 
        solids.add(new SectionDoor(15,4,16*worldScale,16*worldScale,Direction.RIGHT,handler)); 
        solids.add(new SectionDoor( 8,8,16*worldScale,16*worldScale,Direction.DOWN,handler)); 
        objects.get(1).set(0,solids);
        enemies.get(1).set(0,monster);
        
    	//0,1
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*16,16*worldScale,handler)); // up full
        solids.add(new blockBound( 0,8,16*worldScale*6,16*worldScale,handler)); // down part1
        solids.add(new blockBound( 9,8,16*worldScale*6,16*worldScale,handler)); // down part2
        solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*4,handler));//right part1
        solids.add(new blockBound( 15,5,16*worldScale,16*worldScale*4,handler));//right part2        
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*9,handler));//left full
        solids.add(new SectionDoor( 7,8,16*worldScale*2,16*worldScale,Direction.DOWN,handler)); 
        solids.add(new SectionDoor(14,4,16*worldScale,16*worldScale,Direction.RIGHT,handler));
        objects.get(0).set(1,solids);
        enemies.get(0).set(1,monster);    
        
    	//0,2
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*6,16*worldScale,handler)); // up part1
        solids.add(new blockBound( 9,0,16*worldScale*6,16*worldScale,handler)); // up part2
        solids.add(new blockBound( 0,8,16*worldScale*16,16*worldScale,handler)); // down part1
        solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*4,handler));//right part1
        solids.add(new blockBound( 15,5,16*worldScale,16*worldScale*4,handler));//right part2
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*9,handler));//left part1
        solids.add(new SectionDoor( 7,1,16*worldScale*2,16*worldScale,Direction.UP,handler));
        solids.add(new SectionDoor(14,4,16*worldScale,16*worldScale,Direction.RIGHT,handler));  
        objects.get(0).set(2,solids);
        enemies.get(0).set(2,monster);

        
    }

    @Override
    public void refresh() {

    }
}

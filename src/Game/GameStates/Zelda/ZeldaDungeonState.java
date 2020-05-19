package Game.GameStates.Zelda;

import Game.GameStates.State;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.Zelda.Entities.BaseEntity;
import Game.Zelda.Entities.Dynamic.BaseMovingEntity;
import Game.Zelda.Entities.Dynamic.BouncyFella;
import Game.Zelda.Entities.Dynamic.Direction;
import Game.Zelda.Entities.Dynamic.Items;
import Game.Zelda.Entities.Dynamic.Leever;
import Game.Zelda.Entities.Dynamic.Link;
import Game.Zelda.Entities.Dynamic.Lynel;
import Game.Zelda.Entities.Dynamic.Moblin;
import Game.Zelda.Entities.Dynamic.Octorok;
import Game.Zelda.Entities.Dynamic.Thunderbird;
import Game.Zelda.Entities.Dynamic.Zora;
import Game.Zelda.Entities.Dynamic.bombTile;
import Game.Zelda.Entities.Dynamic.fireTile;
import Game.Zelda.Entities.Dynamic.superWave;
import Game.Zelda.Entities.Dynamic.swordProjectile;
import Game.Zelda.Entities.Statics.DungeonDoor;
import Game.Zelda.Entities.Statics.Fire;
import Game.Zelda.Entities.Statics.Item;
import Game.Zelda.Entities.Statics.oldMan;
import Game.Zelda.Entities.Statics.raft;
import Game.Zelda.Entities.Statics.superSword;
import Game.Zelda.Entities.Statics.whiteSword;
import Game.Zelda.Entities.Statics.SectionDoor;
import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Game.Zelda.Entities.Statics.blockBound;
import Game.Zelda.Entities.Statics.bombBlock;
import Game.Zelda.Entities.Statics.caveSword;
import Game.Zelda.Entities.Statics.magicalRod;
import Game.Zelda.Entities.Statics.magicalSword;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

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
    public ArrayList<BaseMovingEntity> toRemove;
    public ArrayList<BaseMovingEntity> monster;
    public ArrayList<SolidStaticEntities> solids;
	public ArrayList<BaseMovingEntity> toAdd;
	public ArrayList<SolidStaticEntities> toAdd1;
	public ArrayList<SolidStaticEntities> toRemove1;


    public ZeldaDungeonState(Handler handler) {
        super(handler);
        xOffset = handler.getWidth()/4;
        yOffset = handler.getHeight()/4;
        stageWidth = handler.getWidth()/3 + (handler.getWidth()/15);
        stageHeight = handler.getHeight()/2;
        worldScale = 2;
        mapX = 4;
        mapY = 4;
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
		toRemove = new ArrayList<>();
		toAdd = new ArrayList<>();
		toAdd1 = new ArrayList<>();
		toRemove1 = new ArrayList<>();
		for (SolidStaticEntities object : objects.get(mapX).get(mapY)) {
			if (object instanceof Item && link.itemPickUp) {
				toRemove1.add(object);
			}
			if (object instanceof bombBlock && link.boom) {
				toRemove1.add(object);
				link.boom=false;
				//toAdd.add(new Items(object.x,object.y,Images.linkArrows, handler));
				toAdd1.add(new DungeonDoor( object.x,object.y,16*worldScale,16*worldScale,Direction.UP,"other",handler,(7 * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(9 * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset));
			}
		}

		for (BaseMovingEntity enemy : enemies.get(mapX).get(mapY)) {

			if (enemy instanceof Octorok && enemy.dead) {
				toRemove.add(enemy);
				link.itemPickUp=false;
				int items = new Random().nextInt(8);
				switch (items) {
				case 0:
					toAdd.add(new Items(enemy.x,enemy.y,Images.rupees, handler));
					break;
				case 1:
					toAdd.add(new Items(enemy.x,enemy.y,Images.itemHeart, handler));
					break;

				case 2:
					toAdd.add(new Items(enemy.x,enemy.y,Images.lifePotion, handler));
					break;

				case 3:
					toAdd.add(new Items(enemy.x,enemy.y,Images.lifePotion2, handler));
					break;
				case 4:
					toAdd.add(new Items(enemy.x,enemy.y,Images.itemHeart2, handler));
					break;
				case 5:
					toAdd.add(new Items(enemy.x,enemy.y,Images.bombItem, handler));
					break;
				case 6:
					toAdd.add(new Items(enemy.x,enemy.y,Images.linkArrows, handler));
					break;
				case 7:
					toAdd.add(new Items(enemy.x,enemy.y,Images.foodItem, handler));
					break;

				}
			}
			if (enemy instanceof Items && link.itemPickUp) {
				toRemove.add(enemy);
			}
			if (enemy instanceof superWave && link.projectile) {
				toRemove.add(enemy);
				if (link.BOM) {
					toAdd.add(new fireTile(enemy.x,enemy.y,Images.fireFrame, handler,Direction.UP));
				}
			}
			if (enemy instanceof swordProjectile && link.projectile) {
				toRemove.add(enemy);
				if (link.BOM) {
					toAdd.add(new fireTile(enemy.x,enemy.y,Images.fireFrame, handler,Direction.UP));
				}
			}
			if(enemy instanceof bombTile&&!link.bomb) {toRemove.add(enemy);}
			if (enemy instanceof fireTile &&(!link.fire)) {toRemove.add(enemy);}
		}
		for (SolidStaticEntities object : objects.get(mapX).get(mapY)) {
			if (object instanceof raft && link.removeRaft) {
				toRemove1.add(object);
			}
		}
		for(BaseMovingEntity i: toAdd) {
			//enemies.remove(i);
			enemies.get(mapX).get(mapY).add(i);
		}
		for(SolidStaticEntities i: toAdd1) {
			//enemies.remove(i);
			objects.get(mapX).get(mapY).add(i);
		}
		for(BaseMovingEntity i: toRemove) {
			//enemies.remove(i);
			enemies.get(mapX).get(mapY).remove(i);
		}
		for(SolidStaticEntities i: toRemove1) {
			//enemies.remove(i);
			objects.get(mapX).get(mapY).remove(i);
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

    	//attackSlots 
    			g.setColor(Color.WHITE);
    			g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
    			g.drawString("ENTER ",handler.getWidth()/2 - (handler.getWidth()/3)+113,handler.getHeight()/5);
    			if(link.hasBow) {g.drawString("E",handler.getWidth()/2 - (handler.getWidth()/3)+175,handler.getHeight()/5);}
    			else {g.drawString("SHIFT ",handler.getWidth()/2 - (handler.getWidth()/3)+165,handler.getHeight()/5);}


    			g.drawImage(Images.attackSlots[1],handler.getWidth()/2 - (handler.getWidth()/3)+110,handler.getHeight()/5,handler.getWidth()/37,handler.getHeight()/27 + 10,null);
    			g.drawImage(Images.attackSlots[0],handler.getWidth()/2 - (handler.getWidth()/3)+160,handler.getHeight()/5,handler.getWidth()/37,handler.getHeight()/27 + 10,null);
    			if(link.wooden && !(link.white&&link.magical&&link.majora)) {
    				g.drawImage(Images.npc[4],handler.getWidth()/2 - (handler.getWidth()/3)+120,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/55 + 10,null);
    			}
    			if(link.white&& !(link.wooden&&link.magical&&link.majora)) {
    				g.drawImage(Images.otherWeapons[8],handler.getWidth()/2 - (handler.getWidth()/3)+120,handler.getHeight()/5+5,handler.getWidth()/75,handler.getHeight()/55 + 10,null);
    			}
    			if(link.magical&& !(link.white&&link.wooden&&link.majora)) {
    				g.drawImage(Images.otherWeapons[19],handler.getWidth()/2 - (handler.getWidth()/3)+120,handler.getHeight()/5+5,handler.getWidth()/75,handler.getHeight()/55 + 10,null);
    			}
    			if(link.rod) {
    				if(link.majora) {g.drawImage(Images.otherWeapons[21],handler.getWidth()/2 - (handler.getWidth()/3)+170,handler.getHeight()/5+5,handler.getWidth()/105,handler.getHeight()/55+ 10,null);}
    				else {g.drawImage(Images.otherWeapons[2],handler.getWidth()/2 - (handler.getWidth()/3)+170,handler.getHeight()/5+5,handler.getWidth()/75,handler.getHeight()/55+ 10,null);}	
    			}
    			if(link.hasBow) {
    				g.drawImage(Images.otherWeapons[16],handler.getWidth()/2 - (handler.getWidth()/3)+170,handler.getHeight()/5+5,handler.getWidth()/75,handler.getHeight()/55+ 10,null);	
    			}
    			if(link.majora&& !(link.wooden&&link.magical&&link.white)) {
    				g.drawImage(Images.otherWeapons[20],handler.getWidth()/2 - (handler.getWidth()/3)+120,handler.getHeight()/5+5,handler.getWidth()/75,handler.getHeight()/55 + 10,null);
    			}

    			//item counter
    			g.setColor(Color.WHITE);
    			g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
    			g.drawString(Integer.toString(link.getRupees()),handler.getWidth()/2 - (handler.getWidth()/3)+220,handler.getHeight()/7+25);
    			g.drawString(Integer.toString(link.getPotions()),handler.getWidth()/2 - (handler.getWidth()/3)+220,handler.getHeight()/6+30);
    			g.drawString(Integer.toString(link.getOtherPotions()),handler.getWidth()/2 - (handler.getWidth()/3)+220,handler.getHeight()/5+30);
    			g.drawString(Integer.toString(link.getBombs()),handler.getWidth()/2 - (handler.getWidth()/3)+270,handler.getHeight()/7+25);
    			g.drawString(Integer.toString(link.getArrows()),handler.getWidth()/2 - (handler.getWidth()/3)+270,handler.getHeight()/6+30);
    			g.drawString(Integer.toString(link.getFood()),handler.getWidth()/2 - (handler.getWidth()/3)+270,handler.getHeight()/5+30);




    			if (animCount >= 0) {
    				g.drawImage(Images.rupees[0],handler.getWidth()/2 - (handler.getWidth()/3)+200,handler.getHeight()/7+10,handler.getWidth()/87,handler.getHeight()/67 + 10,null);
    				g.drawImage(Images.lifePotion[0],handler.getWidth()/2 - (handler.getWidth()/3)+200,handler.getHeight()/6+15,handler.getWidth()/87,handler.getHeight()/67 + 10,null);
    				g.drawImage(Images.lifePotion2[0],handler.getWidth()/2 - (handler.getWidth()/3)+200,handler.getHeight()/5+15,handler.getWidth()/87,handler.getHeight()/67 + 10,null);
    				animCount-= 3;
    			}
    			else if (animCount < 0){
    				g.drawImage(Images.rupees[1],handler.getWidth()/2 - (handler.getWidth()/3)+200,handler.getHeight()/7+10,handler.getWidth()/87,handler.getHeight()/67 + 10,null);
    				g.drawImage(Images.lifePotion[1],handler.getWidth()/2 - (handler.getWidth()/3)+200,handler.getHeight()/6+15,handler.getWidth()/87,handler.getHeight()/67 + 10,null);
    				g.drawImage(Images.lifePotion2[1],handler.getWidth()/2 - (handler.getWidth()/3)+200,handler.getHeight()/5+15,handler.getWidth()/87,handler.getHeight()/67 + 10,null);
    				animCount+= 2;
    			}
    			//future items
    			g.drawImage(Images.bombItem[0],handler.getWidth()/2 - (handler.getWidth()/3)+250,handler.getHeight()/7+10,handler.getWidth()/87,handler.getHeight()/67 + 10,null);
    			g.drawImage(Images.linkArrows[0],handler.getWidth()/2 - (handler.getWidth()/3)+250,handler.getHeight()/6+15,handler.getWidth()/87,handler.getHeight()/67 + 10,null);
    			g.drawImage(Images.foodItem[0],handler.getWidth()/2 - (handler.getWidth()/3)+250,handler.getHeight()/5+15,handler.getWidth()/87,handler.getHeight()/67 + 10,null);

    			//life
    			g.drawImage(Images.lifeImage,handler.getWidth()/2 - (handler.getWidth()/3)+320,handler.getHeight()/7,handler.getWidth()/17,handler.getHeight()/37 + 10,null);
    			if(link.getLife() >= 3 ) {
    				g.drawImage(Images.linkHearts[0],handler.getWidth()/2 - (handler.getWidth()/3)+320,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/57 + 10,null);
    				g.drawImage(Images.linkHearts[0],handler.getWidth()/2 - (handler.getWidth()/3)+340,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/57 + 10,null);
    				g.drawImage(Images.linkHearts[0],handler.getWidth()/2 - (handler.getWidth()/3)+360,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/57 + 10,null);
    				//future hearts that will be drawn with powerUps
    				//			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 - (handler.getWidth()/3)+380,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/57 + 10,null);
    				//			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 - (handler.getWidth()/3)+400,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/57 + 10,null);
    				//			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 - (handler.getWidth()/3)+420,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/57 + 10,null);
    				//			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 - (handler.getWidth()/3)+440,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/57 + 10,null);
    				//			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 - (handler.getWidth()/3)+460,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/57 + 10,null);
    				//			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 - (handler.getWidth()/3)+480,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/57 + 10,null);


    			}
    			if(link.getLife() == 2.5) {
    				g.drawImage(Images.linkHearts[0],handler.getWidth()/2 - (handler.getWidth()/3)+320,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/57 + 10,null);
    				g.drawImage(Images.linkHearts[0],handler.getWidth()/2 - (handler.getWidth()/3)+340,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/57 + 10,null);
    				g.drawImage(Images.linkHearts[1],handler.getWidth()/2 - (handler.getWidth()/3)+360,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/57 + 10,null);
    			}
    			if(link.getLife() == 2) {
    				g.drawImage(Images.linkHearts[0],handler.getWidth()/2 - (handler.getWidth()/3)+320,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/57 + 10,null);
    				g.drawImage(Images.linkHearts[0],handler.getWidth()/2 - (handler.getWidth()/3)+340,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/57 + 10,null);
    			}
    			if(link.getLife() == 1.5) {
    				g.drawImage(Images.linkHearts[0],handler.getWidth()/2 - (handler.getWidth()/3)+320,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/57 + 10,null);
    				g.drawImage(Images.linkHearts[1],handler.getWidth()/2 - (handler.getWidth()/3)+340,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/57 + 10,null);
    			}
    			if(link.getLife() == 1) {
    				g.drawImage(Images.linkHearts[0],handler.getWidth()/2 - (handler.getWidth()/3)+320,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/57 + 10,null);
    			}
    			if(link.getLife() == 0.5) {
    				g.drawImage(Images.linkHearts[1],handler.getWidth()/2 - (handler.getWidth()/3)+320,handler.getHeight()/5,handler.getWidth()/75,handler.getHeight()/57 + 10,null);
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
        objects.get(4).set(3,solids);
        enemies.get(4).set(3,monster);

        
    	//4,2
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*7,16*worldScale*2,handler)); // up part1
        solids.add(new blockBound( 7,2,16*worldScale*2,16*worldScale,handler)); // up 
        solids.add(new blockBound( 9,0,16*worldScale*6,16*worldScale*2,handler)); // up part2
        solids.add(new blockBound( 0,7,16*worldScale*7,16*worldScale*2,handler)); // down part1
        solids.add(new blockBound( 7,6,16*worldScale*2,16*worldScale,handler)); // down
        solids.add(new blockBound( 9,7,16*worldScale*6,16*worldScale*2,handler)); // down part2
        solids.add(new blockBound(15,0,16*worldScale,16*worldScale*9,handler)); //full-right side
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*9,handler));//full left side 
        solids.add(new SectionDoor( 7,1,16*worldScale*2,16*worldScale,Direction.UP,handler));
        solids.add(new SectionDoor( 7,8,16*worldScale*2,16*worldScale,Direction.DOWN,handler));
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
    
        //7,0 -> (other area maybe?)
        solids = new ArrayList<>();
        monster = new ArrayList<>();
        solids.add(new blockBound( 0,0,16*worldScale*16,16*worldScale,handler)); // up 
        solids.add(new blockBound( 0,8,16*worldScale*6,16*worldScale,handler)); // down part1
        solids.add(new blockBound( 10,8,16*worldScale*6,16*worldScale,handler)); // down part2
        solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*9,handler));//right 
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*9,handler));//left
        solids.add(new DungeonDoor( 7,3,16*worldScale,16*worldScale,Direction.UP,"other",handler,(7 * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(9 * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset));

        solids.add(new SectionDoor( 7,8,16*worldScale*2,16*worldScale,Direction.DOWN,handler));
        
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
        solids.add(new blockBound( 0,1,16*worldScale*7,16*worldScale,handler)); // up part1
        solids.add(new blockBound( 3,3,16*worldScale*10,16*worldScale,handler)); // up 
        solids.add(new blockBound( 3,3,16*worldScale,16*worldScale*3,handler)); // up 
        solids.add(new blockBound( 12,3,16*worldScale,16*worldScale*3,handler)); // up 
        solids.add(new blockBound( 9,1,16*worldScale*6,16*worldScale,handler)); // up part2
        solids.add(new blockBound( 0,8,16*worldScale*7,16*worldScale,handler)); // down part1
        solids.add(new blockBound( 3,6,16*worldScale*10,16*worldScale,handler)); // down
        solids.add(new blockBound( 9,8,16*worldScale*6,16*worldScale,handler)); // down part2
        solids.add(new blockBound(15,0,16*worldScale,16*worldScale*9,handler)); //full-right side
        solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*9,handler));//full left side 
        solids.add(new SectionDoor( 7,1,16*worldScale*2,16*worldScale,Direction.UP,handler));
        solids.add(new SectionDoor( 7,8,16*worldScale*2,16*worldScale,Direction.DOWN,handler));
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

package Game.GameStates.Zelda;

import Game.GameStates.State;



import Game.PacMan.entities.Statics.BaseStatic;
import Game.Zelda.Entities.BaseEntity;
import Game.Zelda.Entities.Dynamic.BaseMovingEntity;
import Game.Zelda.Entities.Dynamic.BouncyFella;
import Game.Zelda.Entities.Dynamic.Direction;
import Game.Zelda.Entities.Dynamic.fireTile;
import Game.Zelda.Entities.Dynamic.superWave;
import Game.Zelda.Entities.Dynamic.Leever;
import Game.Zelda.Entities.Dynamic.Link;
import Game.Zelda.Entities.Dynamic.Lynel;
import Game.Zelda.Entities.Dynamic.Moblin;
import Game.Zelda.Entities.Dynamic.Octorok;
import Game.Zelda.Entities.Dynamic.Thunderbird;
import Game.Zelda.Entities.Dynamic.Zora;
import Game.Zelda.Entities.Dynamic.bombTile;
import Game.Zelda.Entities.Dynamic.Items;
import Game.Zelda.Entities.Dynamic.swordProjectile;
import Game.Zelda.Entities.Statics.BookOfMagic;
import Game.Zelda.Entities.Statics.Bow;
import Game.Zelda.Entities.Statics.DungeonDoor;
import Game.Zelda.Entities.Statics.Fire;
import Game.Zelda.Entities.Statics.Item;
import Game.Zelda.Entities.Statics.RedCandle;
import Game.Zelda.Entities.Statics.oldMan;
import Game.Zelda.Entities.Statics.raft;
import Game.Zelda.Entities.Statics.riverBlock;
import Game.Zelda.Entities.Statics.superRing;
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
public class ZeldaGameState extends State {
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
	public ArrayList<BaseMovingEntity> monster;
	public ArrayList<SolidStaticEntities> solids;
	public ArrayList<BaseMovingEntity> toAdd;
	public ArrayList<BaseMovingEntity> toRemove;
	public ArrayList<SolidStaticEntities> toAdd1;
	public ArrayList<SolidStaticEntities> toRemove1;


	public ZeldaGameState(Handler handler) {
		super(handler);
		xOffset = handler.getWidth()/4;
		yOffset = handler.getHeight()/4;
		stageWidth = handler.getWidth()/3 + (handler.getWidth()/15);
		stageHeight = handler.getHeight()/2;
		worldScale = 2;
		mapX = 7;
		mapY = 7;
		mapWidth = 256;
		mapHeight = 176;
		cameraOffsetX =  ((mapWidth*mapX) + mapX + 1)*worldScale;
		cameraOffsetY = ((mapHeight*mapY) + mapY + 1)*worldScale;
		objects = new ArrayList<>();
		enemies = new ArrayList<>();
		caveObjects = new ArrayList<>();
		innObjects = new ArrayList<>();
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

		link = new Link(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.zeldaLinkFrames, handler);

	}
	@Override
	public void tick() {
		link.tick();
		if(link.dungeon) {
			handler.changeState(handler.getZeldaDungeonState());
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
		if (inCave){
			if (runOnce== false) {
				link.x = link.x + 20;
				link.y = link.y + 20;
				runOnce = true;

			}
			if(link.hasSword && !beginAdventure) {	
				handler.getMusicHandler().playEffect("newItem.wav");
				handler.getMusicHandler().changeMusic("OverWorld.wav");
				beginAdventure=true;
			}
		}else {
			if(!inTest) {
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
			}
		}
	}






	@Override
	public void render(Graphics g) {
		if (inCave){
			for (SolidStaticEntities entity : caveObjects) {
				entity.render(g);
			}
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.BOLD, 24));
			g.drawString("  IT ' S  DANGEROUS  TO  GO",(3 * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(2 * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset+ ((16*worldScale)));
			g.drawString("  ALONE !   TAKE  THIS",(5 * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(4 * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset- ((16*worldScale)/2));
			link.render(g);
		}else if(inTest) {
			g.drawImage(Images.inn[0], xOffset ,yOffset, Images.inn[0].getWidth() * worldScale, Images.inn[0].getHeight() * worldScale, null);
			link.render(g);
			for (SolidStaticEntities entity : innObjects) {
				entity.render(g);
			}
			//            g.setColor(Color.BLACK);
			//            g.fillRect(0, 0, xOffset, handler.getHeight());
			//            g.fillRect(xOffset + stageWidth, 0, handler.getWidth(), handler.getHeight());
			//            g.fillRect(0, 0, handler.getWidth(), yOffset);
			//            g.fillRect(0, yOffset + stageHeight, handler.getWidth(), handler.getHeight());

		}
		else {
			g.drawImage(Images.zeldaMap, -cameraOffsetX + xOffset, -cameraOffsetY + yOffset, Images.zeldaMap.getWidth() * worldScale, Images.zeldaMap.getHeight() * worldScale, null);
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
		}
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
		//cave
		for (int i = 0;i < 16;i++){
			for (int j = 0;j < 11;j++) {
				if (i>=2 && i<=13 && j>=2 && j< 9 ) {
					continue;
				}else{
					if (j>=9){
						if (i>1 && i<14) {
							if ((i == 7 || i==8 )){
								continue;
							}else {
								caveObjects.add(new SolidStaticEntities(i, j, Images.caveTiles.get(2), handler));
							}
						}else{
							caveObjects.add(new SolidStaticEntities(i,j,Images.caveTiles.get(5),handler));
						}
					}else{
						caveObjects.add(new SolidStaticEntities(i,j,Images.caveTiles.get(5),handler));
					}
				}
			}
		}
		caveObjects.add(new DungeonDoor(7,9,16*worldScale*2,16*worldScale * 2,Direction.DOWN,"caveStartLeave",handler,(4 * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(2 * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset));
		caveObjects.add(new oldMan(8,4,handler));
		caveObjects.add(new Fire(5,4,handler));
		caveObjects.add(new Fire(11,4,handler));
		caveObjects.add(new caveSword(8,5,handler,Images.npc[4])); 
		caveObjects.add(new whiteSword(6,5,handler,Images.otherWeapons[0]));
		caveObjects.add(new magicalSword(7,5,handler,Images.otherWeapons[1]));
		caveObjects.add(new magicalRod(9,5,handler,Images.otherWeapons[2]));
		caveObjects.add(new superSword(10,5,handler,Images.otherWeapons[3]));    
		caveObjects.add(new superRing(5,5,handler,Images.superRingFrames[0]));
		caveObjects.add(new Bow(11,5,handler,Images.otherWeapons[16])); 
		caveObjects.add(new BookOfMagic(4,5,handler,Images.otherWeapons[18]));
		caveObjects.add(new RedCandle(12,5,handler,Images.otherWeapons[17])); 





		//left
		innObjects.add(new blockBound( 0,5,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 0,3,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 0,4,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 0,2,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 0,1,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 0,0,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 1,1,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 1,2,16*worldScale,16*worldScale,handler));

		//right
		innObjects.add(new blockBound( 13,0,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 13,1,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 13,2,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 14,3,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 14,4,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 14,5,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 12,0,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 12,1,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 12,2,16*worldScale,16*worldScale,handler));

		//Down
		innObjects.add(new blockBound( 13,5,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 12,5,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 11,5,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 10,5,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 9,5,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 8,5,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 7,5,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 6,5,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 5,5,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 4,5,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 1,5,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 2,5,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 13,4,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 12,4,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 11,4,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 10,4,16*worldScale,16*worldScale,handler));

		//Up
		innObjects.add(new blockBound( 13,0,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 12,0,16*worldScale,16*worldScale,handler));
		// innObjects.add(new blockBound( 12,1,16*worldScale,16*worldScale, Direction.LEFT,handler));
		innObjects.add(new blockBound( 11,0,16*worldScale,16*worldScale,handler));
		//innObjects.add(new blockBound( 11,1,16*worldScale,16*worldScale, Direction.LEFT,handler));
		innObjects.add(new blockBound( 10,0,16*worldScale,16*worldScale,handler));
		//  innObjects.add(new blockBound( 10,1,16*worldScale,16*worldScale, Direction.LEFT,handler));
		innObjects.add(new blockBound( 9,0,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 8,0,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 7,0,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 6,0,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 5,0,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 4,0,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 4,2,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 5,2,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 3,0,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 2,0,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 1,0,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 9,1,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 9,2,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 6,1,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 6,2,16*worldScale,16*worldScale,handler));
		innObjects.add(new blockBound( 7,2,16*worldScale,16*worldScale,handler));

		// innObjects.add(new SolidStaticEntities(2,5, null,handler));
		// innObjects.add(new SolidStaticEntities(2,4, null,handler));


		//7,7
		solids = new ArrayList<>();
		monster = new ArrayList<>();
		solids.add(new SectionDoor( 0,5,16*worldScale,16*worldScale, Direction.LEFT,handler));
		solids.add(new SectionDoor( 7,0,16*worldScale * 2,16*worldScale,Direction.UP,handler));
		solids.add(new DungeonDoor( 4,1,16*worldScale,16*worldScale,Direction.UP,"caveStartEnter",handler,(7 * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(9 * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset));
		solids.add(new SectionDoor( 15,5,16*worldScale,16*worldScale,Direction.RIGHT,handler));
		solids.add(new SolidStaticEntities(6,0,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(5,1,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(6,1,Images.forestTiles.get(6),handler));
		solids.add(new SolidStaticEntities(3,2,Images.forestTiles.get(6),handler));
		solids.add(new SolidStaticEntities(2,3,Images.forestTiles.get(6),handler));
		solids.add(new SolidStaticEntities(1,4,Images.forestTiles.get(6),handler));
		solids.add(new SolidStaticEntities(1,6,Images.forestTiles.get(3),handler));
		solids.add(new SolidStaticEntities(1,7,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(1,8,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(2,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(3,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(4,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(5,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(6,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(7,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(8,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(9,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(10,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(11,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(12,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(13,9,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(14,8,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(14,7,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(14,6,Images.forestTiles.get(2),handler));
		solids.add(new SolidStaticEntities(14,4,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(13,4,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(12,4,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(11,4,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(10,4,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(9,4,Images.forestTiles.get(4),handler));
		solids.add(new SolidStaticEntities(9,3,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(9,2,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(9,1,Images.forestTiles.get(5),handler));
		solids.add(new SolidStaticEntities(9,0,Images.forestTiles.get(5),handler));
		
		
		solids.add(new bombBlock( 10,4,16*worldScale,16*worldScale,handler));

		objects.get(7).set(7,solids);
		monster = new ArrayList<>();
		monster.add(new BouncyFella(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.bouncyEnemyFrames, handler));
		monster.add(new Octorok(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.octorokEnemyFrames, handler));
		System.out.println(monster.get(1));
		monster.add(new Leever(8,3,handler));
		monster.add(new Zora(4,7,handler)); 
		//        monster.add(new Moblin(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.moblinEnemyFrames,handler));
		//        monster.add(new Moblin(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.dmoblinEnemyFrames,handler));
		//        monster.add(new Lynel(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.lynelEnemyFrames ,handler));
		//        monster.add(new Lynel(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.dlynelEnemyFrames ,handler));

		enemies.get(7).set(7,monster);

		//5,7
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new riverBlock( 5,1,16*worldScale*2,16*worldScale*10,handler)); // river1
		solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*13,handler)); // left full
		solids.add(new blockBound( 0,0,16*worldScale*3,16*worldScale,handler)); // top 1 
		solids.add(new blockBound( 7,1,16*worldScale*10,16*worldScale,handler)); // top 2
		solids.add(new blockBound( 0,8,16*worldScale*5,16*worldScale,handler)); // down 1 
		solids.add(new blockBound( 7,8,16*worldScale*10,16*worldScale,handler)); // down 2
		solids.add(new SectionDoor( 15,2,16*worldScale,16*worldScale*7, Direction.RIGHT,handler));
		solids.add(new SectionDoor( 4,0,16*worldScale,16*worldScale,Direction.UP,handler));
		solids.add(new DungeonDoor( 2,1,16*worldScale,16*worldScale,Direction.UP,"inn",handler,(7 * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(9 * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset));
		objects.get(5).set(7,solids);

		//6,7
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor( 0,2,16*worldScale,16*worldScale*7, Direction.LEFT,handler));
		solids.add(new SectionDoor( 12,0,16*worldScale * 2,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 15,5,16*worldScale,16*worldScale,Direction.RIGHT,handler));
		objects.get(6).set(7,solids);
		monster.add(new BouncyFella(2,4,handler));
		monster.add(new BouncyFella(3,2,handler));
		monster.add(new Moblin(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.moblinEnemyFrames,handler));
		monster.add(new Moblin(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.dmoblinEnemyFrames,handler));
		monster.add(new Lynel(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.lynelEnemyFrames ,handler));
		monster.add(new Lynel(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.dlynelEnemyFrames ,handler));


		enemies.get(6).set(7,monster);

		//8,7
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		monster.add(new Octorok(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.octorokEnemyFrames,handler));
		monster.add(new Octorok(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.octorokEnemyFrames,handler));
		monster.add(new Octorok(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.octorokEnemyFrames,handler));
		monster.add(new Octorok(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.octorokEnemyFrames,handler));
		monster.add(new Octorok(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.octorokEnemyFrames,handler));
		monster.add(new Octorok(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.octorokEnemyFrames,handler));
		monster.add(new Octorok(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.octorokEnemyFrames,handler));
		monster.add(new Octorok(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.octorokEnemyFrames,handler));
		monster.add(new Octorok(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.octorokEnemyFrames,handler));
		monster.add(new Octorok(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.octorokEnemyFrames,handler));
		monster.add(new Octorok(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.octorokEnemyFrames,handler));
		solids.add(new SectionDoor( 0,5,16*worldScale,16*worldScale, Direction.LEFT,handler));
		solids.add(new SectionDoor( 2,0,16*worldScale * 13,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 15,2,16*worldScale,16*worldScale*7,Direction.RIGHT,handler));        
		objects.get(8).set(7,solids);
		enemies.get(8).set(7,monster);


		//11,7
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor( 0,5,16*worldScale,16*worldScale, Direction.LEFT,handler));
		solids.add(new SectionDoor( 10,0,16*worldScale * 3,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 15,2,16*worldScale,16*worldScale*3,Direction.RIGHT,handler));        
		objects.get(11).set(7,solids);

		//0,6
		solids = new ArrayList<>();
		solids.add(new SectionDoor( 0,5,16*worldScale,16*worldScale*3, Direction.LEFT,handler));
		solids.add(new SectionDoor( 7,0,16*worldScale * 14,16*worldScale,Direction.UP,handler));
		objects.get(0).set(6,solids);
		//5,4 -> dungeon 
		solids = new ArrayList<>();
		solids.add(new DungeonDoor( 8,3,16*worldScale,16*worldScale*2,Direction.UP,"dungeon1",handler,(7 * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(9 * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset));
		solids.add(new SectionDoor( 0,0,16*worldScale,16*worldScale*16, Direction.LEFT,handler));
		solids.add(new SectionDoor( 16,0,16*worldScale,16*worldScale*16, Direction.RIGHT,handler));
		solids.add(new SectionDoor( 0,10,16*worldScale*16,16*worldScale,Direction.DOWN,handler));
		objects.get(5).set(4,solids);
		//5,5
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new riverBlock( 5,1,16*worldScale*2,16*worldScale*10,handler)); // river1
		solids.add(new riverBlock( 0,3,16*worldScale*16,16*worldScale,handler)); // river log
		solids.add(new riverBlock( 1,4,16*worldScale*13,16*worldScale,handler)); // river log
		solids.add(new raft(8,5,handler,Images.linkRaftFrames[0])); 

		solids.add(new SectionDoor( 0,0,16*worldScale*16,16*worldScale, Direction.UP,handler));
		solids.add(new SectionDoor( 0,4,16*worldScale,16*worldScale, Direction.LEFT,handler));
		solids.add(new SectionDoor( 15,4,16*worldScale,16*worldScale, Direction.RIGHT,handler));
		solids.add(new SectionDoor( 2,10,16*worldScale*4,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor( 6,10,16*worldScale*4,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor( 11,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor( 13,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
		objects.get(5).set(5,solids);
		//6,5
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor( 0,4,16*worldScale,16*worldScale, Direction.LEFT,handler));
		solids.add(new SectionDoor( 7,0,16*worldScale * 2,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 15,2,16*worldScale,16*worldScale*8, Direction.RIGHT,handler));

		objects.get(6).set(5,solids);
		//4,6
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new SectionDoor( 15,2,16*worldScale,16*worldScale*6, Direction.RIGHT,handler));
		solids.add(new SectionDoor( 3,0,16*worldScale*3,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 8,0,16*worldScale*3,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 13,0,16*worldScale*3,16*worldScale,Direction.UP,handler));
		objects.get(4).set(6,solids);
		//5,6
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new riverBlock( 5,0,16*worldScale*2,15*worldScale*5,handler)); // river1
		solids.add(new riverBlock( 5,5,16*worldScale*2,15*worldScale*5,handler)); // river2
		solids.add(new blockBound( 0,0,16*worldScale*2,16*worldScale,handler)); // top 1
		solids.add(new blockBound( 10,0,13*worldScale,16*worldScale*2,handler)); // top 4
		solids.add(new blockBound( 12,0,8*worldScale*2,16*worldScale*2,handler)); // top 5
		solids.add(new blockBound( 15,1,16*worldScale*2,16*worldScale,handler)); // top 6

		solids.add(new blockBound( 0,8,18*worldScale*3,16*worldScale,handler)); // down 1 
		solids.add(new blockBound( 7,8,16*worldScale*10,16*worldScale,handler)); // down 2
		solids.add(new SectionDoor( 0,2,16*worldScale,16*worldScale*6, Direction.LEFT,handler));
		solids.add(new SectionDoor( 4,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor( 15,2,16*worldScale,16*worldScale*6, Direction.RIGHT,handler));
		solids.add(new SectionDoor( 7,0,16*worldScale * 3,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 2,0,16*worldScale*3,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 9,0,16*worldScale,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 11,0,16*worldScale,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 13,0,16*worldScale,16*worldScale,Direction.UP,handler));
		objects.get(5).set(6,solids);

		//6,6
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new blockBound( 0,1,16*worldScale*6,16*worldScale,handler)); // up part1
		solids.add(new blockBound( 8,1,16*worldScale*9,16*worldScale,handler)); // up part2
		solids.add(new blockBound( 1,8,16*worldScale*11,16*worldScale,handler)); // down part1
		solids.add(new blockBound( 11,8,16*worldScale,16*worldScale*4,handler)); // down
		solids.add(new blockBound( 10,7,16*worldScale,16*worldScale*4,handler)); // down
		solids.add(new blockBound( 15,7,16*worldScale*2,16*worldScale*5,handler)); // down part2
		solids.add(new blockBound( 16,0,16*worldScale,16*worldScale*4,handler));//right part1
		solids.add(new blockBound( 16,5,16*worldScale,16*worldScale*4,handler));//right part2
		//rocks
		//        solids.add(new blockBound( 5,2,16*worldScale*4,16*worldScale,handler));
		//        solids.add(new blockBound( 5,4,16*worldScale*4,16*worldScale,handler));
		//        solids.add(new blockBound( 5,6,16*worldScale*4,16*worldScale,handler));
		//

		solids.add(new DungeonDoor( 7,1,16*worldScale,16*worldScale,Direction.UP,"merchant",handler,(7 * (ZeldaGameState.stageWidth/16)) + ZeldaGameState.xOffset,(9 * (ZeldaGameState.stageHeight/11)) + ZeldaGameState.yOffset));
		solids.add(new SectionDoor( 15,4,16*worldScale,16*worldScale*3, Direction.RIGHT,handler));
		solids.add(new SectionDoor( 12,10,16*worldScale * 2,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor( 0,2,16*worldScale,16*worldScale*6, Direction.LEFT,handler));
		monster.add(new Thunderbird(xOffset+(stageWidth/2),yOffset + (stageHeight/2),handler));

		objects.get(6).set(6,solids);
		enemies.get(6).set(6,monster);


		//7,6
		monster = new ArrayList<>();
		solids = new ArrayList<>();
		solids.add(new blockBound( 0,1,16*worldScale*16,16*worldScale,handler)); // up part1
		solids.add(new blockBound( 0,8,16*worldScale*7,16*worldScale,handler)); // down part1
		solids.add(new blockBound( 9,8,16*worldScale*6,16*worldScale,handler)); // down part2
		solids.add(new blockBound( 15,0,16*worldScale,16*worldScale*3,handler));//right part1
		solids.add(new blockBound( 15,5,16*worldScale,16*worldScale*4,handler));//right part2
		solids.add(new blockBound( 0,0,16*worldScale,16*worldScale*3,handler));//left part1
		solids.add(new blockBound( 0,5,16*worldScale,16*worldScale*4,handler));//left part2
		solids.add(new SectionDoor( 0,4,16*worldScale,16*worldScale*3, Direction.LEFT,handler));
		solids.add(new SectionDoor( 7,10,16*worldScale * 2,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor( 15,4,16*worldScale,16*worldScale*3,Direction.RIGHT,handler));
		objects.get(7).set(6,solids);

		//8,6
		solids = new ArrayList<>();
		solids.add(new SectionDoor( 0,3,16*worldScale,16*worldScale*3, Direction.LEFT,handler));
		solids.add(new SectionDoor( 15,3,16*worldScale,16*worldScale*3,Direction.RIGHT,handler));
		solids.add(new SectionDoor( 3,0,16*worldScale,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 5,0,16*worldScale,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 7,0,16*worldScale*2,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 10,0,16*worldScale,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 12,0,16*worldScale,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 3,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor( 5,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor( 7,10,16*worldScale*2,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor( 10,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor( 12,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
		solids.add(new SectionDoor( 14,10,16*worldScale*2,16*worldScale,Direction.DOWN,handler));      
		objects.get(8).set(6,solids);
		//9,6;
		solids = new ArrayList<>();
		solids.add(new SectionDoor( 0,3,16*worldScale,16*worldScale*3, Direction.LEFT,handler));
		solids.add(new SectionDoor( 15,4,16*worldScale,16*worldScale*3,Direction.RIGHT,handler));
		solids.add(new SectionDoor( 7,0,16*worldScale * 2,16*worldScale,Direction.UP,handler));
		objects.get(9).set(6,solids);
		//10,6
		solids = new ArrayList<>();
		solids.add(new SectionDoor( 0,4,16*worldScale,16*worldScale*3, Direction.LEFT,handler));
		solids.add(new SectionDoor( 15,2,16*worldScale,16*worldScale*6,Direction.RIGHT,handler));
		solids.add(new SectionDoor( 7,0,16*worldScale * 4,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 10,0,16*worldScale ,16*worldScale,Direction.UP,handler));
		objects.get(10).set(6,solids);
		//11,6
		solids = new ArrayList<>();
		solids.add(new SectionDoor( 0,2,16*worldScale,16*worldScale*10,Direction.LEFT,handler));
		solids.add(new SectionDoor( 15,5,16*worldScale,16*worldScale,Direction.RIGHT,handler));
		solids.add(new SectionDoor( 1,0,16*worldScale ,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 3,0,16*worldScale*4 ,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 9,0,16*worldScale ,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 11,0,16*worldScale ,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 9,10,16*worldScale*4 ,16*worldScale,Direction.DOWN,handler));
		objects.get(11).set(6,solids);
		//12,6
		solids = new ArrayList<>();
		solids.add(new SectionDoor( 0,5,16*worldScale,16*worldScale, Direction.LEFT,handler));
		solids.add(new SectionDoor( 15,13,16*worldScale,16*worldScale,Direction.RIGHT,handler));
		solids.add(new SectionDoor( 15,3,16*worldScale,16*worldScale,Direction.RIGHT,handler));
		solids.add(new SectionDoor( 3,0,16*worldScale ,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 7,0,16*worldScale ,16*worldScale,Direction.UP,handler));


		objects.get(12).set(6,solids);
		//13,6
		solids = new ArrayList<>();
		solids.add(new SectionDoor( 0,3,16*worldScale,16*worldScale,Direction.LEFT,handler));
		solids.add(new SectionDoor( 0,13,16*worldScale,16*worldScale,Direction.LEFT,handler));
		solids.add(new SectionDoor( 3,0,16*worldScale ,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 7,0,16*worldScale ,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 15,4,16*worldScale,16*worldScale*2,Direction.RIGHT,handler));
		objects.get(13).set(6,solids);
		//14,6
		solids = new ArrayList<>();
		solids.add(new SectionDoor( 0,3,16*worldScale,16*worldScale, Direction.LEFT,handler));
		solids.add(new SectionDoor( 15,2,16*worldScale,16*worldScale*6,Direction.RIGHT,handler));
		objects.get(14).set(6,solids);
		//15,6
		solids = new ArrayList<>();
		solids.add(new SectionDoor( 0,5,16*worldScale,16*worldScale, Direction.LEFT,handler));
		solids.add(new SectionDoor( 3,0,16*worldScale*4 ,16*worldScale,Direction.UP,handler));
		solids.add(new SectionDoor( 3,10,16*worldScale*4 ,16*worldScale,Direction.DOWN,handler));
		objects.get(15).set(6,solids);





	}

	@Override
	public void refresh() {

	}
}

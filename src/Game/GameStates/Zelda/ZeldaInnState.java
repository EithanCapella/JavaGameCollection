//package Game.GameStates.Zelda;
//
//import Game.GameStates.State;
//import Game.PacMan.entities.Statics.BaseStatic;
//import Game.Zelda.Entities.BaseEntity;
//import Game.Zelda.Entities.Dynamic.BaseMovingEntity;
//import Game.Zelda.Entities.Dynamic.BouncyFella;
//import Game.Zelda.Entities.Dynamic.Direction;
//import Game.Zelda.Entities.Dynamic.Leever;
//import Game.Zelda.Entities.Dynamic.Link;
//import Game.Zelda.Entities.Statics.SectionDoor;
//import Game.Zelda.Entities.Statics.SolidStaticEntities;
//import Game.Zelda.Entities.Statics.caveSword;
//import Game.Zelda.Entities.Statics.magicalRod;
//import Game.Zelda.Entities.Statics.magicalSword;
//import Main.Handler;
//import Resources.Images;
//
//import java.awt.*;
//import java.util.ArrayList;
//
///**
// * Created by AlexVR on 3/14/2020
// */
//public class ZeldaInnState extends State {
//    public static int xOffset,yOffset,stageWidth,stageHeight,worldScale, animCount=0;
//    public int cameraOffsetX,cameraOffsetY;
//    //map is 16 by 7 squares, you start at x=7,y=7 starts counting at 0
//    public int mapX,mapY,mapWidth,mapHeight;
//    public ArrayList<ArrayList<ArrayList<SolidStaticEntities>>> objects;
//    //public ArrayList<ArrayList<ArrayList<BaseEntity>>> npc;
//    public ArrayList<ArrayList<ArrayList<BaseMovingEntity>>> enemies;
//    public Link link;
//    public static boolean inCave = false, runOnce = false, beginAdventure=false,inTest=false;
//    public ArrayList<SolidStaticEntities> caveObjects;
//
//    public ZeldaInnState(Handler handler) {
//        super(handler);
//        xOffset = handler.getWidth()/4;
//        yOffset = handler.getHeight()/4;
//        stageWidth = handler.getWidth()/3 + (handler.getWidth()/15);
//        stageHeight = handler.getHeight()/2;
//        worldScale = 2;
//        mapX = 7;
//        mapY = 7;
//        mapWidth = 256;
//        mapHeight = 176;
//        cameraOffsetX =  ((mapWidth*mapX) + mapX + 1)*worldScale;
//        cameraOffsetY = ((mapHeight*mapY) + mapY + 1)*worldScale;
//        objects = new ArrayList<>();
//        enemies = new ArrayList<>();
//        caveObjects = new ArrayList<>();
//        for (int i =0;i<16;i++){
//            objects.add(new ArrayList<>());
//            enemies.add(new ArrayList<>());
//            //npc.add(new ArrayList<>());
//            for (int j =0;j<8;j++) {
//                objects.get(i).add(new ArrayList<>());
//                enemies.get(i).add(new ArrayList<>());
//                //npc.get(i).add(new ArrayList<>());
//            }
//        }
//        addWorldObjects();
//
//        link = new Link(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.zeldaLinkFrames, handler);
//
//    }
//    @Override
//    public void tick() {
//        link.tick();
//		if (inCave){
//			if (runOnce== false) {
//				link.x = link.x + 20;
//				link.y = link.y + 20;
//				runOnce = true;
//
//			}
//				if(link.hasSword && !beginAdventure) {	
//					handler.getMusicHandler().playEffect("newItem.wav");
//		        	handler.getMusicHandler().changeMusic("OverWorld.wav");
//					beginAdventure=true;
//					}
//        }else {
//        	if(!inTest) {
//        		if (!link.movingMap) {
//        			for (SolidStaticEntities entity : objects.get(mapX).get(mapY)) {
//        				entity.tick();
//        			}
//        			for (BaseMovingEntity entity : enemies.get(mapX).get(mapY)) {
//        				entity.tick();
//        				if (entity.getInteractBounds().intersects(link.getInteractBounds())){
//        					link.damage(1);
//        				}
//        			}
//        		}
//        	}
//        }
//    }
//
//    @Override
//    public void render(Graphics g) {
//        if (inCave){
//            for (SolidStaticEntities entity : caveObjects) {
//                entity.render(g);
//            }
//            g.setColor(Color.WHITE);
//            g.setFont(new Font("TimesRoman", Font.BOLD, 24));
//            g.drawString("  IT ' S  DANGEROUS  TO  GO",(3 * (ZeldaInnState.stageWidth/16)) + ZeldaInnState.xOffset,(2 * (ZeldaInnState.stageHeight/11)) + ZeldaInnState.yOffset+ ((16*worldScale)));
//            g.drawString("  ALONE !   TAKE  THIS",(5 * (ZeldaInnState.stageWidth/16)) + ZeldaInnState.xOffset,(4 * (ZeldaInnState.stageHeight/11)) + ZeldaInnState.yOffset- ((16*worldScale)/2));
//            link.render(g);
//        }else if(inTest) {
////        	g.drawImage(Images.inn[0], xOffset ,yOffset ,handler.getWidth()/2, handler.getHeight()/2,null);
////            link.render(g);
////            g.setColor(Color.BLACK);
////            g.fillRect(0, 0, xOffset, handler.getHeight());
////            g.fillRect(xOffset + stageWidth, 0, handler.getWidth(), handler.getHeight());
////            g.fillRect(0, 0, handler.getWidth(), yOffset);
////            g.fillRect(0, yOffset + stageHeight, handler.getWidth(), handler.getHeight());
//    
//        }else {
//            g.drawImage(Images.zeldaMap, -cameraOffsetX + xOffset, -cameraOffsetY + yOffset, Images.zeldaMap.getWidth() * worldScale, Images.zeldaMap.getHeight() * worldScale, null);
//            if (!link.movingMap) {
//                for (SolidStaticEntities entity : objects.get(mapX).get(mapY)) {
//                    entity.render(g);
//                }
//                for (BaseMovingEntity entity : enemies.get(mapX).get(mapY)) {
//                    entity.render(g);
//                }
//            }
//            link.render(g);
//            g.setColor(Color.BLACK);
//            g.fillRect(0, 0, xOffset, handler.getHeight());
//            g.fillRect(xOffset + stageWidth, 0, handler.getWidth(), handler.getHeight());
//            g.fillRect(0, 0, handler.getWidth(), yOffset);
//            g.fillRect(0, yOffset + stageHeight, handler.getWidth(), handler.getHeight());
//        }
//
//	    //rupees counter
//        g.setColor(Color.WHITE);
//        g.setFont(new Font("TimesRoman", Font.PLAIN, 48));
//		g.drawString(Integer.toString(link.getRupees()),handler.getWidth()/2 + (handler.getWidth()/6)+100,handler.getHeight()/3 - handler.getHeight()/16 + 10);
//
//		if (animCount >= 0) {
//		g.drawImage(Images.rupees[0],handler.getWidth()/2 + (handler.getWidth()/6)+25,handler.getHeight()/3 - handler.getHeight()/11,8*4,16*4,null);
//		animCount-= 3;
//		}
//		else if (animCount < 0){
//			g.drawImage(Images.rupees[1],handler.getWidth()/2 + (handler.getWidth()/6)+25,handler.getHeight()/3 - handler.getHeight()/11,8*4,16*4,null);
//			animCount+= 2;
//		}
//	    
//        if(link.getLife() == 3) {
//			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 + (handler.getWidth()/6)+25,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
//			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 + (handler.getWidth()/6)+25 + 75,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
//			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 + (handler.getWidth()/6)+25 + 150,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
//		}
//        if(link.getLife() == 2.5) {
//			g.drawImage(Images.linkHearts[1],handler.getWidth()/2 + (handler.getWidth()/6)+25,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
//			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 + (handler.getWidth()/6)+25 + 75,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
//			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 + (handler.getWidth()/6)+25 + 150,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
//		}
//		if(link.getLife() == 2) {
//			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 + (handler.getWidth()/6)+25,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
//			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 + (handler.getWidth()/6)+25 + 75,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
//		}
//		if(link.getLife() == 1.5) {
//			g.drawImage(Images.linkHearts[1],handler.getWidth()/2 + (handler.getWidth()/6)+25,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
//			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 + (handler.getWidth()/6)+25 + 75,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
//		}
//		if(link.getLife() == 1) {
//			g.drawImage(Images.linkHearts[0],handler.getWidth()/2 + (handler.getWidth()/6)+25,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
//		}
//		if(link.getLife() == 0.5) {
//			g.drawImage(Images.linkHearts[1],handler.getWidth()/2 + (handler.getWidth()/6)+25,handler.getHeight()/6,handler.getWidth()/35,handler.getHeight()/27 + 10,null);
//		}
//
//    }
//    private void addWorldObjects() {
//        //cave
//        for (int i = 0;i < 16;i++){
//            for (int j = 0;j < 11;j++) {
//                if (i>=2 && i<=13 && j>=2 && j< 9 ) {
//                    continue;
//                }else{
//                    if (j>=9){
//                        if (i>1 && i<14) {
//                            if ((i == 7 || i==8 )){
//                                continue;
//                            }else {
//                                caveObjects.add(new SolidStaticEntities(i, j, Images.caveTiles.get(2), handler));
//                            }
//                        }else{
//                            caveObjects.add(new SolidStaticEntities(i,j,Images.caveTiles.get(5),handler));
//                        }
//                    }else{
//                        caveObjects.add(new SolidStaticEntities(i,j,Images.caveTiles.get(5),handler));
//                    }
//                }
//            }
//        }
//        caveObjects.add(new DungeonDoor(7,9,16*worldScale*2,16*worldScale * 2,Direction.DOWN,"caveStartLeave",handler,(4 * (ZeldaInnState.stageWidth/16)) + ZeldaInnState.xOffset,(2 * (ZeldaInnState.stageHeight/11)) + ZeldaInnState.yOffset));
//        caveObjects.add(new oldMan(8,4,handler));
//        caveObjects.add(new Fire(5,4,handler));
//        caveObjects.add(new Fire(11,4,handler));
//        caveObjects.add(new caveSword(8,5,handler,Images.npc[4])); 
//        caveObjects.add(new whiteSword(4,5,handler,Images.otherWeapons[0]));
//        caveObjects.add(new magicalSword(6,5,handler,Images.otherWeapons[1]));
//        caveObjects.add(new magicalRod(10,5,handler,Images.otherWeapons[2]));
//        caveObjects.add(new superSword(12,5,handler,Images.otherWeapons[3]));
//
//        //7,7
//        ArrayList<SolidStaticEntities> solids = new ArrayList<>();
//        ArrayList<BaseMovingEntity> monster = new ArrayList<>();
//        solids.add(new SectionDoor( 0,5,16*worldScale,16*worldScale, Direction.LEFT,handler));
//        solids.add(new SectionDoor( 7,0,16*worldScale * 2,16*worldScale,Direction.UP,handler));
//        solids.add(new DungeonDoor( 4,1,16*worldScale,16*worldScale,Direction.UP,"caveStartEnter",handler,(7 * (ZeldaInnState.stageWidth/16)) + ZeldaInnState.xOffset,(9 * (ZeldaInnState.stageHeight/11)) + ZeldaInnState.yOffset));
//        solids.add(new SectionDoor( 15,5,16*worldScale,16*worldScale,Direction.RIGHT,handler));
//        solids.add(new SolidStaticEntities(6,0,Images.forestTiles.get(2),handler));
//        solids.add(new SolidStaticEntities(5,1,Images.forestTiles.get(5),handler));
//        solids.add(new SolidStaticEntities(6,1,Images.forestTiles.get(6),handler));
//        solids.add(new SolidStaticEntities(3,2,Images.forestTiles.get(6),handler));
//        solids.add(new SolidStaticEntities(2,3,Images.forestTiles.get(6),handler));
//        solids.add(new SolidStaticEntities(1,4,Images.forestTiles.get(6),handler));
//        solids.add(new SolidStaticEntities(1,6,Images.forestTiles.get(3),handler));
//        solids.add(new SolidStaticEntities(1,7,Images.forestTiles.get(5),handler));
//        solids.add(new SolidStaticEntities(1,8,Images.forestTiles.get(5),handler));
//        solids.add(new SolidStaticEntities(2,9,Images.forestTiles.get(2),handler));
//        solids.add(new SolidStaticEntities(3,9,Images.forestTiles.get(2),handler));
//        solids.add(new SolidStaticEntities(4,9,Images.forestTiles.get(2),handler));
//        solids.add(new SolidStaticEntities(5,9,Images.forestTiles.get(2),handler));
//        solids.add(new SolidStaticEntities(6,9,Images.forestTiles.get(2),handler));
//        solids.add(new SolidStaticEntities(7,9,Images.forestTiles.get(2),handler));
//        solids.add(new SolidStaticEntities(8,9,Images.forestTiles.get(2),handler));
//        solids.add(new SolidStaticEntities(9,9,Images.forestTiles.get(2),handler));
//        solids.add(new SolidStaticEntities(10,9,Images.forestTiles.get(2),handler));
//        solids.add(new SolidStaticEntities(11,9,Images.forestTiles.get(2),handler));
//        solids.add(new SolidStaticEntities(12,9,Images.forestTiles.get(2),handler));
//        solids.add(new SolidStaticEntities(13,9,Images.forestTiles.get(2),handler));
//        solids.add(new SolidStaticEntities(14,8,Images.forestTiles.get(5),handler));
//        solids.add(new SolidStaticEntities(14,7,Images.forestTiles.get(5),handler));
//        solids.add(new SolidStaticEntities(14,6,Images.forestTiles.get(2),handler));
//        solids.add(new SolidStaticEntities(14,4,Images.forestTiles.get(5),handler));
//        solids.add(new SolidStaticEntities(13,4,Images.forestTiles.get(5),handler));
//        solids.add(new SolidStaticEntities(12,4,Images.forestTiles.get(5),handler));
//        solids.add(new SolidStaticEntities(11,4,Images.forestTiles.get(5),handler));
//        solids.add(new SolidStaticEntities(10,4,Images.forestTiles.get(5),handler));
//        solids.add(new SolidStaticEntities(9,4,Images.forestTiles.get(4),handler));
//        solids.add(new SolidStaticEntities(9,3,Images.forestTiles.get(5),handler));
//        solids.add(new SolidStaticEntities(9,2,Images.forestTiles.get(5),handler));
//        solids.add(new SolidStaticEntities(9,1,Images.forestTiles.get(5),handler));
//        solids.add(new SolidStaticEntities(9,0,Images.forestTiles.get(5),handler));
//        objects.get(7).set(7,solids);
//        monster = new ArrayList<>();
//        monster.add(new BouncyFella(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.bouncyEnemyFrames, handler));
//        monster.add(new Octorok(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.octorokEnemyFrames, handler));
//        monster.add(new Leever(8,2,handler));
//        monster.add(new Zora(4,6,handler)); 
////        monster.add(new Moblin(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.moblinEnemyFrames,handler));
////        monster.add(new Moblin(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.dmoblinEnemyFrames,handler));
////        monster.add(new Lynel(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.lynelEnemyFrames ,handler));
////        monster.add(new Lynel(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.dlynelEnemyFrames ,handler));
//
//        enemies.get(7).set(7,monster);
//        
//        //5,7
//        monster = new ArrayList<>();
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 15,2,16*worldScale,16*worldScale*7, Direction.RIGHT,handler));
//        solids.add(new SectionDoor( 4,0,16*worldScale,16*worldScale,Direction.UP,handler));
//        solids.add(new DungeonDoor( 2,1,16*worldScale,16*worldScale,Direction.UP,"inn",handler,(7 * (ZeldaInnState.stageWidth/16)) + ZeldaInnState.xOffset,(9 * (ZeldaInnState.stageHeight/11)) + ZeldaInnState.yOffset));
//        objects.get(5).set(7,solids);
//
//        //6,7
//        monster = new ArrayList<>();
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 0,2,16*worldScale,16*worldScale*7, Direction.LEFT,handler));
//        solids.add(new SectionDoor( 12,0,16*worldScale * 2,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 15,5,16*worldScale,16*worldScale,Direction.RIGHT,handler));
//        objects.get(6).set(7,solids);
//        monster.add(new BouncyFella(2,4,handler));
//        monster.add(new BouncyFella(3,2,handler));
//        monster.add(new Moblin(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.moblinEnemyFrames,handler));
//        monster.add(new Moblin(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.dmoblinEnemyFrames,handler));
//        monster.add(new Lynel(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.lynelEnemyFrames ,handler));
//        monster.add(new Lynel(xOffset+(stageWidth/2),yOffset + (stageHeight/2),Images.dlynelEnemyFrames ,handler));
//
//
//        enemies.get(6).set(7,monster);
//         
//      //8,7
//        monster = new ArrayList<>();
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 0,5,16*worldScale,16*worldScale, Direction.LEFT,handler));
//        solids.add(new SectionDoor( 2,0,16*worldScale * 13,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 15,2,16*worldScale,16*worldScale*7,Direction.RIGHT,handler));        
//        objects.get(8).set(7,solids);
//        
//      //11,7
//        monster = new ArrayList<>();
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 0,5,16*worldScale,16*worldScale, Direction.LEFT,handler));
//        solids.add(new SectionDoor( 10,0,16*worldScale * 3,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 15,2,16*worldScale,16*worldScale*3,Direction.RIGHT,handler));        
//        objects.get(11).set(7,solids);
//        
//      //0,6
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 0,5,16*worldScale,16*worldScale*3, Direction.LEFT,handler));
//        solids.add(new SectionDoor( 7,0,16*worldScale * 14,16*worldScale,Direction.UP,handler));
//        objects.get(0).set(6,solids);
//      //5,5
//        monster = new ArrayList<>();
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 0,4,16*worldScale,16*worldScale, Direction.LEFT,handler));
//        solids.add(new SectionDoor( 15,4,16*worldScale,16*worldScale, Direction.RIGHT,handler));
//        solids.add(new SectionDoor( 2,10,16*worldScale*4,16*worldScale,Direction.DOWN,handler));
//        solids.add(new SectionDoor( 6,10,16*worldScale*4,16*worldScale,Direction.DOWN,handler));
//        solids.add(new SectionDoor( 11,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
//        solids.add(new SectionDoor( 13,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
//        objects.get(5).set(5,solids);
//      //6,5
//        monster = new ArrayList<>();
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 0,4,16*worldScale,16*worldScale, Direction.LEFT,handler));
//        solids.add(new SectionDoor( 7,0,16*worldScale * 2,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 15,2,16*worldScale,16*worldScale*8, Direction.RIGHT,handler));
//
//        objects.get(6).set(5,solids);
//      //4,6
//        monster = new ArrayList<>();
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 15,2,16*worldScale,16*worldScale*6, Direction.RIGHT,handler));
//        solids.add(new SectionDoor( 3,0,16*worldScale*3,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 8,0,16*worldScale*3,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 13,0,16*worldScale*3,16*worldScale,Direction.UP,handler));
//        objects.get(4).set(6,solids);
//      //5,6
//        monster = new ArrayList<>();
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 0,2,16*worldScale,16*worldScale*6, Direction.LEFT,handler));
//        solids.add(new SectionDoor( 4,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
//        solids.add(new SectionDoor( 15,2,16*worldScale,16*worldScale*6, Direction.RIGHT,handler));
//        solids.add(new SectionDoor( 7,0,16*worldScale * 3,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 2,0,16*worldScale*3,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 9,0,16*worldScale,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 11,0,16*worldScale,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 13,0,16*worldScale,16*worldScale,Direction.UP,handler));
//        objects.get(5).set(6,solids);
//      
//      //6,6
//        monster = new ArrayList<>();
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 15,4,16*worldScale,16*worldScale*3, Direction.RIGHT,handler));
//        solids.add(new SectionDoor( 12,10,16*worldScale * 2,16*worldScale,Direction.DOWN,handler));
//        solids.add(new SectionDoor( 0,2,16*worldScale,16*worldScale*6, Direction.LEFT,handler));
//        objects.get(6).set(6,solids);
//
//        //7,6
//        monster = new ArrayList<>();
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 0,4,16*worldScale,16*worldScale*3, Direction.LEFT,handler));
//        solids.add(new SectionDoor( 7,10,16*worldScale * 2,16*worldScale,Direction.DOWN,handler));
//        solids.add(new SectionDoor( 15,4,16*worldScale,16*worldScale*3,Direction.RIGHT,handler));
//        objects.get(7).set(6,solids);
//
//        //8,6
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 0,3,16*worldScale,16*worldScale*3, Direction.LEFT,handler));
//        solids.add(new SectionDoor( 15,3,16*worldScale,16*worldScale*3,Direction.RIGHT,handler));
//        solids.add(new SectionDoor( 3,0,16*worldScale,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 5,0,16*worldScale,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 7,0,16*worldScale*2,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 10,0,16*worldScale,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 12,0,16*worldScale,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 3,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
//        solids.add(new SectionDoor( 5,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
//        solids.add(new SectionDoor( 7,10,16*worldScale*2,16*worldScale,Direction.DOWN,handler));
//        solids.add(new SectionDoor( 10,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
//        solids.add(new SectionDoor( 12,10,16*worldScale,16*worldScale,Direction.DOWN,handler));
//        solids.add(new SectionDoor( 14,10,16*worldScale*2,16*worldScale,Direction.DOWN,handler));      
//        objects.get(8).set(6,solids);
//      //9,6;
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 0,3,16*worldScale,16*worldScale*3, Direction.LEFT,handler));
//        solids.add(new SectionDoor( 15,4,16*worldScale,16*worldScale*3,Direction.RIGHT,handler));
//        solids.add(new SectionDoor( 7,0,16*worldScale * 2,16*worldScale,Direction.UP,handler));
//        objects.get(9).set(6,solids);
//      //10,6
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 0,4,16*worldScale,16*worldScale*3, Direction.LEFT,handler));
//        solids.add(new SectionDoor( 15,2,16*worldScale,16*worldScale*6,Direction.RIGHT,handler));
//        solids.add(new SectionDoor( 7,0,16*worldScale * 4,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 10,0,16*worldScale ,16*worldScale,Direction.UP,handler));
//        objects.get(10).set(6,solids);
//      //11,6
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 0,2,16*worldScale,16*worldScale*10,Direction.LEFT,handler));
//        solids.add(new SectionDoor( 15,5,16*worldScale,16*worldScale,Direction.RIGHT,handler));
//        solids.add(new SectionDoor( 1,0,16*worldScale ,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 3,0,16*worldScale*4 ,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 9,0,16*worldScale ,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 11,0,16*worldScale ,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 9,10,16*worldScale*4 ,16*worldScale,Direction.DOWN,handler));
//        objects.get(11).set(6,solids);
//      //12,6
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 0,5,16*worldScale,16*worldScale, Direction.LEFT,handler));
//        solids.add(new SectionDoor( 15,13,16*worldScale,16*worldScale,Direction.RIGHT,handler));
//        solids.add(new SectionDoor( 15,3,16*worldScale,16*worldScale,Direction.RIGHT,handler));
//        solids.add(new SectionDoor( 3,0,16*worldScale ,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 7,0,16*worldScale ,16*worldScale,Direction.UP,handler));
//
//
//        objects.get(12).set(6,solids);
//      //13,6
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 0,3,16*worldScale,16*worldScale,Direction.LEFT,handler));
//        solids.add(new SectionDoor( 0,13,16*worldScale,16*worldScale,Direction.LEFT,handler));
//        solids.add(new SectionDoor( 3,0,16*worldScale ,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 7,0,16*worldScale ,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 15,4,16*worldScale,16*worldScale*2,Direction.RIGHT,handler));
//        objects.get(13).set(6,solids);
//      //14,6
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 0,3,16*worldScale,16*worldScale, Direction.LEFT,handler));
//        solids.add(new SectionDoor( 15,2,16*worldScale,16*worldScale*6,Direction.RIGHT,handler));
//        objects.get(14).set(6,solids);
//      //15,6
//        solids = new ArrayList<>();
//        solids.add(new SectionDoor( 0,5,16*worldScale,16*worldScale, Direction.LEFT,handler));
//        solids.add(new SectionDoor( 3,0,16*worldScale*4 ,16*worldScale,Direction.UP,handler));
//        solids.add(new SectionDoor( 3,10,16*worldScale*4 ,16*worldScale,Direction.DOWN,handler));
//        objects.get(15).set(6,solids);
//
//
//
//
//        
//    }
//
//    @Override
//    public void refresh() {
//
//    }
//}

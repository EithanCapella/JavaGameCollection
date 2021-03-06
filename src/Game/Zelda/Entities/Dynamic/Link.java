package Game.Zelda.Entities.Dynamic;

import Game.GameStates.Zelda.ZeldaGameState;
import Game.Zelda.Entities.Statics.BookOfMagic;
import Game.Zelda.Entities.Statics.Bow;
import Game.Zelda.Entities.Statics.DungeonDoor;
import Game.Zelda.Entities.Statics.Fire;
import Game.Zelda.Entities.Statics.Item;
import Game.Zelda.Entities.Statics.RedCandle;
import Game.Zelda.Entities.Statics.SectionDoor;
import Game.Zelda.Entities.Statics.SolidStaticEntities;
import Game.Zelda.Entities.Statics.blockBound;
import Game.Zelda.Entities.Statics.caveSword;
import Game.Zelda.Entities.Statics.magicalRod;
import Game.Zelda.Entities.Statics.magicalSword;
import Game.Zelda.Entities.Statics.moblinArrow;
import Game.Zelda.Entities.Statics.raft;
import Game.Zelda.Entities.Statics.riverBlock;
import Game.Zelda.Entities.Statics.superSword;
import Game.Zelda.Entities.Statics.superRing;
import Game.Zelda.Entities.Statics.swordLaser;
import Game.Zelda.Entities.Dynamic.superWave;
import Game.Zelda.Entities.Statics.whiteSword;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static Game.GameStates.Zelda.ZeldaGameState.worldScale;
import static Game.Zelda.Entities.Dynamic.Direction.DOWN;
import static Game.Zelda.Entities.Dynamic.Direction.UP;

/**
 * Created by AlexVR on 3/15/2020
 */
public class Link extends BaseMovingEntity {

	private final int animSpeed = 120, knockback = 40;
	private double life=3.0;
	int newMapX=0,newMapY=0,xExtraCounter=0,yExtraCounter=0;
	int celebrateCounter = 120,attackCounter=30,hurtCounter=20,fireCounter=60,bombCounter=120,raftCounter=20,count=0, rupees=300, potions = 0,otherPotions=0,food=0,bombs=15,arrows=10, hitCount=29;
	public boolean movingMap = false,hasSword=false,hasBow=false,horray=false,dungeon=false,otherWorld=false,raft=false,hasRaft=false,hasRod=false,hasCandle=false,hasBook=false,arrowAttack=false,
			itemPickUp=false,removeRaft=false,rod=false,superRing=false,projectile=false,first=false,second=false,fire=false,bomb=false,boom=false,candle=false,bookOfMagic=false,BOM=false;
	Direction movingTo;
	public String swordHolding = "";
	public swordLaser laserSword;
	public swordProjectile swordProyectile;
	public fireTile fireTile;
	public bombTile bombTile;
	public superWave superWave;
	Animation pickUpAnim,attackAnim,hurtAnim;
	public Rectangle swordBounds = (Rectangle) bounds.clone();
	public Rectangle rodBounds = (Rectangle) bounds.clone();
	public Rectangle proyectileBounds = (Rectangle) bounds.clone();


	public Link(int x, int y, BufferedImage[] sprite, Handler handler) {
		super(x, y, sprite, handler);
		speed = 4;
		health = 6;
		BufferedImage[] animList = new BufferedImage[2];
		BufferedImage[] animListPick = new BufferedImage[2];

		animList[0] = sprite[4];
		animList[1] = sprite[5];
		animation = new Animation(animSpeed,animList);
		animListPick[0] = Images.itemPickUpFrames[0];
		animListPick[1] = Images.itemPickUpFrames[1];
		pickUpAnim = new Animation(120,animListPick);
		hurtAnim = new Animation(90,Images.linkHurtFrames);
		BufferedImage[] animList1 = new BufferedImage[4];
		animList1[0] = (Images.woodenSwordAttackFrames[8]);
		animList1[1] = (Images.woodenSwordAttackFrames[9]);
		animList1[2] = (Images.woodenSwordAttackFrames[10]);
		animList1[3] = (Images.woodenSwordAttackFrames[11]);
		attackAnim = new Animation(100,animList1);
	}
	@Override
	public void tick() {
		if(life <= 0.0) {
			handler.changeState(handler.getGameOverState());
		}
		
		if (attacking) {if(first) {swordAttack(this.direction);}else if(second) {rodAttack(this.direction);}}
		if(hurt) {
			hurtAnim.tick();
		}
		//----------------------------//----------------------------//----------------------------//
		//------CounterArea------
		if (hurtCounter > 0 && hurt) {hurtCounter--;}
		if (hurtCounter <= 0 && hurt) {hurtCounter = 20;hurt = false;}
		if (raftCounter > 0 && raft) {raftCounter--; raftMethod();}
		if (raftCounter <= 0 && raft) {raftCounter = 120;raft = false;}
		if (attackCounter > 0 && attacking) {attackCounter--;}
		if (attackCounter <= 0 && attacking) {attackCounter = 30; attacking = false;}
		if (fireCounter > 0 && fire) {fireCounter--;}
		if (fireCounter <= 0 && fire) {
			fireCounter = 60; 
			fire = false;
			if(bookOfMagic) {BOM=false;fire=false;projectile=false;}}
		if (bombCounter > 0 && bomb) {bombCounter--;}
		if (bombCounter <= 0 && bomb) {bombCounter = 120; bomb = false;}
		if (horray && celebrateCounter == 60) {pickUpAnim.tick();}
		if (horray && celebrateCounter > 0) {celebrateCounter--;}
		if (horray && celebrateCounter <= 0) {pickUpAnim.tick(); horray = false; celebratingMethod();}

		//----------------------------//----------------------------//----------------------------//
		if(superRing) {superRing();} //if superRing is found 
		if (movingMap){
			switch (movingTo) {
			case RIGHT:
				if(dungeon) {
					handler.getZeldaDungeonState().cameraOffsetX+=3;
					newMapX++;
				}else if (otherWorld){
					handler.getZeldaOtherState().cameraOffsetX+=3;
					newMapX++;
				}else {
					handler.getZeldaGameState().cameraOffsetX+=3;
					newMapX++;
				}
				if (xExtraCounter>0){
					if(dungeon) {x-=6;}
					else {x-=12;}
					xExtraCounter--;
					animation.tick();

				}else{
					x--;
				}
				break;
			case LEFT:
				if(dungeon) {
					handler.getZeldaDungeonState().cameraOffsetX-=3;
					newMapX--;
				}else if (otherWorld){
					handler.getZeldaOtherState().cameraOffsetX-=3;
					newMapX--;
				}else {
					handler.getZeldaGameState().cameraOffsetX-=3;
					newMapX--;
				}
				if (xExtraCounter>0){
					if(dungeon) {x+=6;}
					else {x+=12;}
					xExtraCounter--;
					animation.tick();
				}else{
					x++;
				}
				break;
			case UP:
				if(dungeon) {
					handler.getZeldaDungeonState().cameraOffsetY-=3;
					newMapY++;
				}else if (otherWorld){
					handler.getZeldaOtherState().cameraOffsetY-=3;
					newMapY++;
				}else {
					handler.getZeldaGameState().cameraOffsetY-=3;
					newMapY++;
				}
				if (yExtraCounter>0){
					if(dungeon) {y+=6;}
					else {y+=12;}
					yExtraCounter--;
					animation.tick();
				}else{
					y++;
				}
				break;
			case DOWN:
				if(dungeon) {
					handler.getZeldaDungeonState().cameraOffsetY+=3;
					newMapY--;
				}else if (otherWorld){
					handler.getZeldaOtherState().cameraOffsetY+=3;
					newMapY--;
				}else {
					handler.getZeldaGameState().cameraOffsetY+=3;
					newMapY--;
				}
				if (yExtraCounter>0){
					if(dungeon) {y-=6;}
					else {y-=12;}
					yExtraCounter--;
					animation.tick();
				}else{
					y--;
				}
				break;
			}
			bounds = new Rectangle(x,y,width,height);
			changeIntersectingBounds();
			if (newMapX == 0 && newMapY == 0){
				movingMap = false;
				movingTo = null;
				newMapX = 0;
				newMapY = 0;
			}
		}
		//----------------------------//----------------------------//----------------------------//
		//------KeyBoardMovement/Attacks------
		else {
			if (handler.getKeyManager().up&& !horray) {
				if (direction != UP) {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = sprites[4];
					animList[1] = sprites[5];
					if(swordHolding == "majora" && !superRing) {
						animList[0] = Images.superLinkFrames[4];
						animList[1] = Images.superLinkFrames[5];
					}
					animation = new Animation(animSpeed, animList);
					direction = UP;
					sprite = sprites[4];
					if(swordHolding == "majora" && !superRing) {sprite = Images.superLinkFrames[4];}
				}
				if(!attacking&&!horray) {
					animation.tick();
					move(direction);
				}
			} else if (handler.getKeyManager().down&&!horray) {
				if (direction != DOWN) {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = sprites[0];
					animList[1] = sprites[1];
					if(swordHolding == "majora" && !superRing) {
						animList[0] = Images.superLinkFrames[0];
						animList[1] = Images.superLinkFrames[1];
					}
					animation = new Animation(animSpeed, animList);
					direction = DOWN;
					sprite = sprites[0];
					if(swordHolding == "majora" && !superRing) {sprite = Images.superLinkFrames[0];	}
				}
				if(!attacking&&!horray) {
					animation.tick();
					move(direction);
				}
			} else if (handler.getKeyManager().left&&!horray) {
				if (direction != Direction.LEFT) {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = Images.flipHorizontal(sprites[2]);
					animList[1] = Images.flipHorizontal(sprites[3]);
					if(swordHolding == "majora" && !superRing) {
						animList[0] = Images.flipHorizontal(Images.superLinkFrames[2]);
						animList[1] = Images.flipHorizontal(Images.superLinkFrames[3]);	
					}
					animation = new Animation(animSpeed, animList);
					direction = Direction.LEFT;
					sprite = Images.flipHorizontal(sprites[3]);
					if(swordHolding == "majora" &&!superRing) {sprite = Images.flipHorizontal(Images.superLinkFrames[3]);}
				}
				if(!attacking&&!horray) {
					animation.tick();
					move(direction);
				}
			} else if (handler.getKeyManager().right&&!horray) {
				if (direction != Direction.RIGHT) {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = (sprites[2]);
					animList[1] = (sprites[3]);
					if(swordHolding == "majora" &&!superRing) {
						animList[0] = Images.superLinkFrames[2];
						animList[1] = Images.superLinkFrames[3];
					}
					animation = new Animation(animSpeed, animList);
					direction = Direction.RIGHT;
					sprite = (sprites[3]);
					if(swordHolding == "majora" &&!superRing) {sprite = Images.superLinkFrames[3];	}
				}
				if(!attacking&&!horray) {
					animation.tick();
					move(direction);
				}	
			} else {
				moving = false;
			}
		}
		//------Debug Keys------
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_7) && getFood()>0 ) {
			if(getFood()!=0) {setFood(getFood()-1);setLife(getLife()+1);}
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_8) && getPotions()>0 ) {
			if(getPotions()!=0) {addPotions(getPotions()-1);setLife(getLife()+0.5);}
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_9) && getOtherPotions()>0 ) {
			if(getOtherPotions()!=0) {addOtherPotions(getOtherPotions()-1);setLife(getLife()+2.0);}
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_J) && hasSword && !fire &&candle) {
			fire=true;
			placeAttack();
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_B) && !bomb&& getBombs()>0  ) {
			bomb=true;
			placeAttack();
			if(getBombs()!=0) {setBombs(getBombs()-1);}
		}

		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_P) && hasSword && !attacking ) {
			attackingMethod();
			handler.getMusicHandler().playEffect("Sword_Combined.wav");
			laserMethod();
			projectile=false;
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_O) && hasSword && !attacking&& (swordHolding == "majora"||rod) ) {
			secondAttack();
			superWave();
			projectile=false;
			BOM=false;
			fire=false;
			handler.getMusicHandler().playEffect("MagicalRod.wav");
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E) && hasBow && !attacking && getArrows()>0 ) {
			if(getArrows()!=0) {setArrows(getArrows()-1);}
			arrowAttack=true;
			shootArrows();
			projectile=false;
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_R) && hasRaft) {raftMethod();}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && !attacking&&hasSword) {
			second=false;
			first=true;
			attackingMethod();
			handler.getMusicHandler().playEffect("Sword_Slash.wav");
		}
		if (handler.getKeyManager().shift == true && !attacking&&hasSword) {
			second=true;
			first=false;
			secondAttack();
			handler.getMusicHandler().playEffect("Sword_Slash.wav");
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_Z)) {
			swordHolding = "majora";
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_L)) {
			handler.changeState(handler.getFightingState());
		}

		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_H) && life < 3) {
			setLife(getLife() + 0.5);
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_G) && life >= 0.5) {
			setLife(getLife() - 0.5);
		}
	}
	//----------------------------//----------------------------//----------------------------//
	
	
	//------ExtraMethods(helpers)------
	public void celebratingMethod() {
		if (horray == true) {
			handler.getZeldaGameState().beginAdventure = false;
			celebrateCounter = 60;
		}
		else if (horray == false) {
			animation.tick();
		}
	}
	//------Attacks------
	public void attackingMethod() {//Takes care of all animations relating to sword attacks (ENTER)
		attacking=true;
		if(attacking) {
			if (direction == direction.UP) {
				if(swordHolding == "wood") {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.woodenSwordAttackFrames[8]);
					animList1[1] = (Images.woodenSwordAttackFrames[9]);
					animList1[2] = (Images.woodenSwordAttackFrames[10]);
					animList1[3] = (Images.woodenSwordAttackFrames[11]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(swordHolding == "white") {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.whiteSwordAttackFrames[8]);
					animList1[1] = (Images.whiteSwordAttackFrames[9]);
					animList1[2] = (Images.whiteSwordAttackFrames[10]);
					animList1[3] = (Images.whiteSwordAttackFrames[11]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(swordHolding == "magical") {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.magicalSwordAttackFrames[8]);
					animList1[1] = (Images.magicalSwordAttackFrames[9]);
					animList1[2] = (Images.magicalSwordAttackFrames[10]);
					animList1[3] = (Images.magicalSwordAttackFrames[11]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(swordHolding == "majora") {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.superSwordAttackFrames[8]);
					animList1[1] = (Images.superSwordAttackFrames[9]);
					animList1[2] = (Images.superSwordAttackFrames[10]);
					animList1[3] = (Images.superSwordAttackFrames[11]);
					attackAnim = new Animation(animSpeed, animList1);
				}
			}
			else if (direction == direction.DOWN) {
				if(swordHolding == "wood") {
					BufferedImage[] animList1 = new BufferedImage[4];			
					animList1[0] = (Images.woodenSwordAttackFrames[0]);
					animList1[1] = (Images.woodenSwordAttackFrames[1]);
					animList1[2] = (Images.woodenSwordAttackFrames[2]);
					animList1[3] = (Images.woodenSwordAttackFrames[3]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(swordHolding == "white") {
					BufferedImage[] animList1 = new BufferedImage[4];			
					animList1[0] = (Images.whiteSwordAttackFrames[0]);
					animList1[1] = (Images.whiteSwordAttackFrames[1]);
					animList1[2] = (Images.whiteSwordAttackFrames[2]);
					animList1[3] = (Images.whiteSwordAttackFrames[3]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(swordHolding == "magical") {
					BufferedImage[] animList1 = new BufferedImage[4];			
					animList1[0] = (Images.magicalSwordAttackFrames[0]);
					animList1[1] = (Images.magicalSwordAttackFrames[1]);
					animList1[2] = (Images.magicalSwordAttackFrames[2]);
					animList1[3] = (Images.magicalSwordAttackFrames[3]);
					attackAnim = new Animation(animSpeed, animList1);
				}

				if(swordHolding == "majora") {
					BufferedImage[] animList1 = new BufferedImage[4];			
					animList1[0] = (Images.superSwordAttackFrames[0]);
					animList1[1] = (Images.superSwordAttackFrames[1]);
					animList1[2] = (Images.superSwordAttackFrames[2]);
					animList1[3] = (Images.superSwordAttackFrames[3]);
					attackAnim = new Animation(animSpeed, animList1);
				}
			}	
			else if (direction == direction.LEFT) {
				if(swordHolding == "wood") {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.flipHorizontal(Images.woodenSwordAttackFrames[4]));
					animList1[1] = (Images.flipHorizontal(Images.woodenSwordAttackFrames[5]));
					animList1[2] = (Images.flipHorizontal(Images.woodenSwordAttackFrames[6]));
					animList1[3] = (Images.flipHorizontal(Images.woodenSwordAttackFrames[7]));
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(swordHolding == "white") {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.flipHorizontal(Images.whiteSwordAttackFrames[4]));
					animList1[1] = (Images.flipHorizontal(Images.whiteSwordAttackFrames[5]));
					animList1[2] = (Images.flipHorizontal(Images.whiteSwordAttackFrames[6]));
					animList1[3] = (Images.flipHorizontal(Images.whiteSwordAttackFrames[7]));
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(swordHolding == "magical") {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.flipHorizontal(Images.magicalSwordAttackFrames[4]));
					animList1[1] = (Images.flipHorizontal(Images.magicalSwordAttackFrames[5]));
					animList1[2] = (Images.flipHorizontal(Images.magicalSwordAttackFrames[6]));
					animList1[3] = (Images.flipHorizontal(Images.magicalSwordAttackFrames[7]));
					attackAnim = new Animation(animSpeed, animList1);
				}

				if(swordHolding == "majora") {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.flipHorizontal(Images.superSwordAttackFrames[4]));
					animList1[1] = (Images.flipHorizontal(Images.superSwordAttackFrames[5]));
					animList1[2] = (Images.flipHorizontal(Images.superSwordAttackFrames[6]));
					animList1[3] = (Images.flipHorizontal(Images.superSwordAttackFrames[7]));
					attackAnim = new Animation(animSpeed, animList1);
				}
			}
			else {
				if(swordHolding == "wood") {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.woodenSwordAttackFrames[4]);
					animList1[1] = (Images.woodenSwordAttackFrames[5]);
					animList1[2] = (Images.woodenSwordAttackFrames[6]);
					animList1[3] = (Images.woodenSwordAttackFrames[7]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(swordHolding == "white") {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.whiteSwordAttackFrames[4]);
					animList1[1] = (Images.whiteSwordAttackFrames[5]);
					animList1[2] = (Images.whiteSwordAttackFrames[6]);
					animList1[3] = (Images.whiteSwordAttackFrames[7]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(swordHolding == "magical") {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.magicalSwordAttackFrames[4]);
					animList1[1] = (Images.magicalSwordAttackFrames[5]);
					animList1[2] = (Images.magicalSwordAttackFrames[6]);
					animList1[3] = (Images.magicalSwordAttackFrames[7]);
					attackAnim = new Animation(animSpeed, animList1);
				}

				if(swordHolding == "majora") {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.superSwordAttackFrames[4]);
					animList1[1] = (Images.superSwordAttackFrames[5]);
					animList1[2] = (Images.superSwordAttackFrames[6]);
					animList1[3] = (Images.superSwordAttackFrames[7]);
					attackAnim = new Animation(animSpeed, animList1);
				}
			}
		}
	}
	//------Secondary Attack------
	public void secondAttack() {//Attacks with rod and magic spells (Shift)
		attacking=true;
		if(attacking) {
			if (direction == direction.UP) {
				if(rod&& !(swordHolding == "majora")) {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.magicalRodAttackFrames[8]);
					animList1[1] = (Images.magicalRodAttackFrames[9]);
					animList1[2] = (Images.magicalRodAttackFrames[10]);
					animList1[3] = (Images.magicalRodAttackFrames[11]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(swordHolding == "majora") {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.superRodAttackFrames[8]);
					animList1[1] = (Images.superRodAttackFrames[9]);
					animList1[2] = (Images.superRodAttackFrames[10]);
					animList1[3] = (Images.superRodAttackFrames[11]);
					attackAnim = new Animation(animSpeed, animList1);
				}
			}
			else if (direction == direction.DOWN) {	
				if(rod&&!(swordHolding == "majora")) {
					BufferedImage[] animList1 = new BufferedImage[4];			
					animList1[0] = (Images.magicalRodAttackFrames[0]);
					animList1[1] = (Images.magicalRodAttackFrames[1]);
					animList1[2] = (Images.magicalRodAttackFrames[2]);
					animList1[3] = (Images.magicalRodAttackFrames[3]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(swordHolding == "majora") {
					BufferedImage[] animList1 = new BufferedImage[4];			
					animList1[0] = (Images.superRodAttackFrames[0]);
					animList1[1] = (Images.superRodAttackFrames[1]);
					animList1[2] = (Images.superRodAttackFrames[2]);
					animList1[3] = (Images.superRodAttackFrames[3]);
					attackAnim = new Animation(animSpeed, animList1);
				}
			}	
			else if (direction == direction.LEFT) {
				if(rod&&!(swordHolding == "majora")) {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.flipHorizontal(Images.magicalRodAttackFrames[4]));
					animList1[1] = (Images.flipHorizontal(Images.magicalRodAttackFrames[5]));
					animList1[2] = (Images.flipHorizontal(Images.magicalRodAttackFrames[6]));
					animList1[3] = (Images.flipHorizontal(Images.magicalRodAttackFrames[7]));
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(swordHolding == "majora") {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.flipHorizontal(Images.superRodAttackFrames[4]));
					animList1[1] = (Images.flipHorizontal(Images.superRodAttackFrames[5]));
					animList1[2] = (Images.flipHorizontal(Images.superRodAttackFrames[6]));
					animList1[3] = (Images.flipHorizontal(Images.superRodAttackFrames[7]));
					attackAnim = new Animation(animSpeed, animList1);
				}
			}
			else {
				if(rod&&!(swordHolding == "majora")) {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.magicalRodAttackFrames[4]);
					animList1[1] = (Images.magicalRodAttackFrames[5]);
					animList1[2] = (Images.magicalRodAttackFrames[6]);
					animList1[3] = (Images.magicalRodAttackFrames[7]);
					attackAnim = new Animation(animSpeed, animList1);
				}
				if(swordHolding == "majora") {
					BufferedImage[] animList1 = new BufferedImage[4];
					animList1[0] = (Images.superRodAttackFrames[4]);
					animList1[1] = (Images.superRodAttackFrames[5]);
					animList1[2] = (Images.superRodAttackFrames[6]);
					animList1[3] = (Images.superRodAttackFrames[7]);
					attackAnim = new Animation(animSpeed, animList1);
				}
			}
		}
	}
	//------Placing Item Method------
	public void placeAttack() {//Takes care of placing attacks, mainly relates with attacks Link has such as bombs and fire spells
		
		if(fire) {
			if(dungeon) {
				if (direction == direction.UP) {
					fireTile = new fireTile(this.x,this.y-32,Images.fireFrame, handler,direction.UP);
					handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(fireTile);
				}
				else if (direction == direction.DOWN) {
					fireTile = new fireTile(this.x,this.y+32,Images.fireFrame, handler,direction.DOWN);
					handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(fireTile);
				}
				else if (direction == direction.LEFT) {
					fireTile = new fireTile(this.x-32,this.y,Images.fireFrame, handler,direction.LEFT);
					handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(fireTile);
				}
				else{
					fireTile = new fireTile(this.x+32,this.y,Images.fireFrame, handler,direction.RIGHT);
					handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(fireTile);
				}

			}
			if (otherWorld) {
				if (direction == direction.UP) {
					fireTile = new fireTile(this.x,this.y-32,Images.fireFrame, handler,direction.UP);
					handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(fireTile);
				}
				else if (direction == direction.DOWN) {
					fireTile = new fireTile(this.x,this.y+32,Images.fireFrame, handler,direction.DOWN);
					handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(fireTile);
				}
				else if (direction == direction.LEFT) {
					fireTile = new fireTile(this.x-32,this.y,Images.fireFrame, handler,direction.LEFT);
					handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(fireTile);
				}
				else{
					fireTile = new fireTile(this.x+32,this.y,Images.fireFrame, handler,direction.RIGHT);
					handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(fireTile);
				}

			}
			else {
				if (direction == direction.UP) {
					fireTile = new fireTile(this.x,this.y-32,Images.fireFrame, handler,direction.UP);
					handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(fireTile);
				}
				else if (direction == direction.DOWN) {
					fireTile = new fireTile(this.x,this.y+32,Images.fireFrame, handler,direction.DOWN);
					handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(fireTile);
				}
				else if (direction == direction.LEFT) {
					fireTile = new fireTile(this.x-32,this.y,Images.fireFrame, handler,direction.LEFT);
					handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(fireTile);
				}
				else{
					fireTile = new fireTile(this.x+32,this.y,Images.fireFrame, handler,direction.RIGHT);
					handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(fireTile);
				}

			}
					}
		if(bomb) {
			if(dungeon) {
				if (direction == direction.UP) {
					bombTile = new bombTile(this.x,this.y-32,Images.bombItem, handler,direction.UP);
					handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(bombTile);
				}
				else if (direction == direction.DOWN) {
					bombTile = new bombTile(this.x,this.y+32,Images.bombItem, handler,direction.DOWN);
					handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(bombTile);
				}
				else if (direction == direction.LEFT) {
					bombTile = new bombTile(this.x-32,this.y,Images.bombItem, handler,direction.LEFT);
					handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(bombTile);
				}
				else{
					bombTile = new bombTile(this.x+32,this.y,Images.bombItem, handler,direction.RIGHT);
					handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(bombTile);
				}
			}
			if (otherWorld) {
				if (direction == direction.UP) {
					bombTile = new bombTile(this.x,this.y-32,Images.bombItem, handler,direction.UP);
					handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(bombTile);
				}
				else if (direction == direction.DOWN) {
					bombTile = new bombTile(this.x,this.y+32,Images.bombItem, handler,direction.DOWN);
					handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(bombTile);
				}
				else if (direction == direction.LEFT) {
					bombTile = new bombTile(this.x-32,this.y,Images.bombItem, handler,direction.LEFT);
					handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(bombTile);
				}
				else{
					bombTile = new bombTile(this.x+32,this.y,Images.bombItem, handler,direction.RIGHT);
					handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(bombTile);
				}
			}
			else {
				if (direction == direction.UP) {
					bombTile = new bombTile(this.x,this.y-32,Images.bombItem, handler,direction.UP);
					handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(bombTile);
				}
				else if (direction == direction.DOWN) {
					bombTile = new bombTile(this.x,this.y+32,Images.bombItem, handler,direction.DOWN);
					handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(bombTile);
				}
				else if (direction == direction.LEFT) {
					bombTile = new bombTile(this.x-32,this.y,Images.bombItem, handler,direction.LEFT);
					handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(bombTile);
				}
				else{
					bombTile = new bombTile(this.x+32,this.y,Images.bombItem, handler,direction.RIGHT);
					handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(bombTile);
				}
			}
		}
		

	}
	//------Arrow Shooter Method------
	public void shootArrows() {
		if(arrowAttack) {
			if(dungeon) {//Allows creation of arrows in different states such as Dungeon, OtherWorld and normal State
				if (direction == direction.UP) {
					swordProyectile = new swordProjectile(this.x,this.y,Images.linkArrowsProjectileUp, handler,direction.UP);
					handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(swordProyectile);
				}
				else if (direction == direction.DOWN) {
					swordProyectile = new swordProjectile(this.x,this.y,Images.linkArrowsProjectileDown, handler,direction.DOWN);
					handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(swordProyectile);
				}
				else if (direction == direction.LEFT) {
					swordProyectile = new swordProjectile(this.x,this.y,Images.linkArrowsProjectileSideL, handler,direction.LEFT);
					handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(swordProyectile);
				}
				else {
					swordProyectile = new swordProjectile(this.x,this.y,Images.linkArrowsProjectileSide, handler,direction.RIGHT);
					handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(swordProyectile);
				}
			}
			if (otherWorld) {
				if (direction == direction.UP) {
					swordProyectile = new swordProjectile(this.x,this.y,Images.linkArrowsProjectileUp, handler,direction.UP);
					handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(swordProyectile);
				}
				else if (direction == direction.DOWN) {
					swordProyectile = new swordProjectile(this.x,this.y,Images.linkArrowsProjectileDown, handler,direction.DOWN);
					handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(swordProyectile);
				}
				else if (direction == direction.LEFT) {
					swordProyectile = new swordProjectile(this.x,this.y,Images.linkArrowsProjectileSideL, handler,direction.LEFT);
					handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(swordProyectile);
				}
				else {
					swordProyectile = new swordProjectile(this.x,this.y,Images.linkArrowsProjectileSide, handler,direction.RIGHT);
					handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(swordProyectile);
				}
			}
			else {
				if (direction == direction.UP) {
					swordProyectile = new swordProjectile(this.x,this.y,Images.linkArrowsProjectileUp, handler,direction.UP);
					handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
				}
				else if (direction == direction.DOWN) {
					swordProyectile = new swordProjectile(this.x,this.y,Images.linkArrowsProjectileDown, handler,direction.DOWN);
					handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
				}
				else if (direction == direction.LEFT) {
					swordProyectile = new swordProjectile(this.x,this.y,Images.linkArrowsProjectileSideL, handler,direction.LEFT);
					handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
				}
				else {
					swordProyectile = new swordProjectile(this.x,this.y,Images.linkArrowsProjectileSide, handler,direction.RIGHT);
					handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
				}
			}




		}
	}
	//------SuperWave------
	//Refers to majora's special abilities
	public void superWave() {
		if(attacking) {
			if(dungeon) {
				if(swordHolding == "majora") {
					if (direction == direction.UP) {
						superWave = new superWave(this.x,this.y,Images.vaporWaveUp, handler,direction.UP);
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(superWave);
					}
					else if (direction == direction.DOWN) {
						superWave = new superWave(this.x,this.y,Images.vaporWaveDown, handler,direction.DOWN);
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(superWave);
					}
					else if (direction == direction.LEFT) {
						superWave = new superWave(this.x,this.y,Images.vaporWaveSideL, handler,direction.LEFT);
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(superWave);
					}
					else {
						superWave = new superWave(this.x,this.y,Images.vaporWaveSide, handler,direction.RIGHT);
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(superWave);
					}
				}
			}
			if (otherWorld) {
				if(swordHolding == "majora") {
					if (direction == direction.UP) {
						superWave = new superWave(this.x,this.y,Images.vaporWaveUp, handler,direction.UP);
						handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(superWave);
					}
					else if (direction == direction.DOWN) {
						superWave = new superWave(this.x,this.y,Images.vaporWaveDown, handler,direction.DOWN);
						handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(superWave);
					}
					else if (direction == direction.LEFT) {
						superWave = new superWave(this.x,this.y,Images.vaporWaveSideL, handler,direction.LEFT);
						handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(superWave);
					}
					else {
						superWave = new superWave(this.x,this.y,Images.vaporWaveSide, handler,direction.RIGHT);
						handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(superWave);
					}
				}
			}
			else {
				if(rod && !(swordHolding == "majora")) {
					if (direction == direction.UP) {
						swordProyectile = new swordProjectile(this.x,this.y,Images.rodWaveUp, handler,direction.UP);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
					}
					else if (direction == direction.DOWN) {
						swordProyectile = new swordProjectile(this.x,this.y,Images.rodWaveDown, handler,direction.DOWN);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
					}
					else if (direction == direction.LEFT) {
						swordProyectile = new swordProjectile(this.x,this.y,Images.rodWaveSide, handler,direction.LEFT);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
					}
					else {
						swordProyectile = new swordProjectile(this.x,this.y,Images.rodWaveSideL, handler,direction.RIGHT);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
					}
				}
				if(swordHolding == "majora") {
					if (direction == direction.UP) {
						superWave = new superWave(this.x,this.y,Images.vaporWaveUp, handler,direction.UP);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(superWave);
					}
					else if (direction == direction.DOWN) {
						superWave = new superWave(this.x,this.y,Images.vaporWaveDown, handler,direction.DOWN);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(superWave);
					}
					else if (direction == direction.LEFT) {
						superWave = new superWave(this.x,this.y,Images.vaporWaveSideL, handler,direction.LEFT);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(superWave);
					}
					else {
						superWave = new superWave(this.x,this.y,Images.vaporWaveSide, handler,direction.RIGHT);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(superWave);
					}
				}
			}
		}
	}
	//------Sword laser------
	public void laserMethod() {//There are three distinct lasers for three swords: White,Magical,Majora
		if(attacking) {
			if(dungeon) {
				if (direction == direction.UP) {
					if(swordHolding == "white") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.whiteProyectileUp, handler,direction.UP);
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(swordProyectile);

					}
					if(swordHolding == "magical") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.magicalProyectileUp, handler,direction.UP);
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(swordProyectile);
					}
					if(swordHolding == "majora") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.majoraProyectileUp, handler,direction.UP);
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(swordProyectile);
					}
				}
				else if (direction == direction.DOWN) {
					if(swordHolding == "white") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.whiteProyectileDown, handler,direction.DOWN);
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(swordProyectile);
					}
					if(swordHolding == "magical") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.magicalProyectileDown, handler,direction.DOWN);
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(swordProyectile);
					}
					if(swordHolding == "majora") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.majoraProyectileDown, handler,direction.DOWN);
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(swordProyectile);
					}			}	
				else if (direction == direction.LEFT) {
					if(swordHolding == "white") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.whiteProyectileSideL, handler,direction.LEFT);
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(swordProyectile);
					}
					if(swordHolding == "magical") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.magicalProyectileSideL, handler,direction.LEFT);
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(swordProyectile);
					}
					if(swordHolding == "majora") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.majoraProyectileSideL, handler,direction.LEFT);
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(swordProyectile);
					}
				}
				else {
					if(swordHolding == "white") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.whiteProyectileSide, handler,direction.RIGHT);
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(swordProyectile);
					}
					if(swordHolding == "magical") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.magicalProyectileSide, handler,direction.RIGHT);
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(swordProyectile);
					}
					if(swordHolding == "majora") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.majoraProyectileSide, handler,direction.RIGHT);
						handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY).add(swordProyectile);
					}
				}
			}
			if (otherWorld) {
				if (direction == direction.UP) {
					if(swordHolding == "white") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.whiteProyectileUp, handler,direction.UP);
						handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(swordProyectile);

					}
					if(swordHolding == "magical") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.magicalProyectileUp, handler,direction.UP);
						handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(swordProyectile);
					}
					if(swordHolding == "majora") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.majoraProyectileUp, handler,direction.UP);
						handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(swordProyectile);
					}
				}
				else if (direction == direction.DOWN) {
					if(swordHolding == "white") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.whiteProyectileDown, handler,direction.DOWN);
						handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(swordProyectile);
					}
					if(swordHolding == "magical") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.magicalProyectileDown, handler,direction.DOWN);
						handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(swordProyectile);
					}
					if(swordHolding == "majora") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.majoraProyectileDown, handler,direction.DOWN);
						handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(swordProyectile);
					}			}	
				else if (direction == direction.LEFT) {
					if(swordHolding == "white") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.whiteProyectileSideL, handler,direction.LEFT);
						handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(swordProyectile);
					}
					if(swordHolding == "magical") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.magicalProyectileSideL, handler,direction.LEFT);
						handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(swordProyectile);
					}
					if(swordHolding == "majora") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.majoraProyectileSideL, handler,direction.LEFT);
						handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(swordProyectile);
					}
				}
				else {
					if(swordHolding == "white") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.whiteProyectileSide, handler,direction.RIGHT);
						handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(swordProyectile);
					}
					if(swordHolding == "magical") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.magicalProyectileSide, handler,direction.RIGHT);
						handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(swordProyectile);
					}
					if(swordHolding == "majora") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.majoraProyectileSide, handler,direction.RIGHT);
						handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY).add(swordProyectile);
					}
				}
			}
			else {
				if (direction == direction.UP) {
					if(swordHolding == "white") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.whiteProyectileUp, handler,direction.UP);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);

					}
					if(swordHolding == "magical") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.magicalProyectileUp, handler,direction.UP);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
					}
					if(swordHolding == "majora") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.majoraProyectileUp, handler,direction.UP);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
					}
				}
				else if (direction == direction.DOWN) {
					if(swordHolding == "white") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.whiteProyectileDown, handler,direction.DOWN);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
					}
					if(swordHolding == "magical") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.magicalProyectileDown, handler,direction.DOWN);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
					}
					if(swordHolding == "majora") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.majoraProyectileDown, handler,direction.DOWN);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
					}			}	
				else if (direction == direction.LEFT) {
					if(swordHolding == "white") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.whiteProyectileSideL, handler,direction.LEFT);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
					}
					if(swordHolding == "magical") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.magicalProyectileSideL, handler,direction.LEFT);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
					}
					if(swordHolding == "majora") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.majoraProyectileSideL, handler,direction.LEFT);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
					}
				}
				else {
					if(swordHolding == "white") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.whiteProyectileSide, handler,direction.RIGHT);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
					}
					if(swordHolding == "magical") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.magicalProyectileSide, handler,direction.RIGHT);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
					}
					if(swordHolding == "majora") {
						swordProyectile = new swordProjectile(this.x,this.y,Images.majoraProyectileSide, handler,direction.RIGHT);
						handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY).add(swordProyectile);
					}
				}
			}
		}
	}
	//----------------------------//----------------------------//----------------------------//
	
	
	//------SuperRing------
	public void superRing() {
		if (direction == direction.UP) {
			BufferedImage[] animList = new BufferedImage[2];
			animList[0] = Images.superRingFrames[5]; // link raft
			animList[1] = Images.superRingFrames[6]; // link raft
			animation = new Animation(animSpeed, animList);
			sprite= Images.superRingFrames[5];
			animation.tick();

		}
		else if (direction == direction.DOWN) {
			BufferedImage[] animList = new BufferedImage[2];
			animList[0] = Images.superRingFrames[1]; // superlink raft
			animList[1] = Images.superRingFrames[2]; // superlink raft
			animation = new Animation(animSpeed, animList);
			sprite= Images.superRingFrames[1];
			animation.tick();
		}	
		else if (direction == direction.LEFT) {
			BufferedImage[] animList = new BufferedImage[2];
			animList[0] = Images.flipHorizontal(Images.superRingFrames[3]); // link raft
			animList[1] = Images.flipHorizontal(Images.superRingFrames[4]); // link raft
			animation = new Animation(animSpeed, animList);
			sprite= Images.flipHorizontal(Images.superRingFrames[3]);
			animation.tick();
		}
		else {
			BufferedImage[] animList = new BufferedImage[2];
			animList[0] = Images.superRingFrames[3]; // link raft
			animList[1] = Images.superRingFrames[4]; // link raft
			animation = new Animation(animSpeed, animList);
			sprite= Images.superRingFrames[3];
			animation.tick();
		}
	}
	//----------------------------//----------------------------//----------------------------//
	
	
	//------Raft Travelling Method------
	public void raftMethod() {//Allows link to swim in water and travel to new Dungeons
		raft=true;
		if(raft) {
			if (direction == direction.UP) {
				if(swordHolding == "majora") {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = Images.linkRaftFrames[11]; // superlink raft
					animList[1] = Images.linkRaftFrames[12]; // superlink raft
					animation = new Animation(animSpeed, animList);
					sprite= Images.linkRaftFrames[11];
				}
				else {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = Images.linkRaftFrames[5]; // link raft
					animList[1] = Images.linkRaftFrames[6]; // link raft
					animation = new Animation(animSpeed, animList);
					sprite= Images.linkRaftFrames[5];
				}
			}
			else if (direction == direction.DOWN) {
				if(swordHolding == "majora") {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = Images.linkRaftFrames[7]; // superlink raft
					animList[1] = Images.linkRaftFrames[8]; // superlink raft
					animation = new Animation(animSpeed, animList);
					sprite= Images.linkRaftFrames[7];
				}
				else {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = Images.linkRaftFrames[1]; // link raft
					animList[1] = Images.linkRaftFrames[2]; // link raft
					animation = new Animation(animSpeed, animList);
					sprite= Images.linkRaftFrames[1];
				}
			}	
			else if (direction == direction.LEFT) {
				if(swordHolding == "majora") {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = Images.flipHorizontal(Images.linkRaftFrames[9]); // superlink raft
					animList[1] = Images.flipHorizontal(Images.linkRaftFrames[10]); // superlink raft
					animation = new Animation(animSpeed, animList);
					sprite= Images.flipHorizontal(Images.linkRaftFrames[9]);
				}
				else {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = Images.flipHorizontal(Images.linkRaftFrames[3]); // link raft
					animList[1] = Images.flipHorizontal(Images.linkRaftFrames[4]); // link raft
					animation = new Animation(animSpeed, animList);
					sprite= Images.flipHorizontal(Images.linkRaftFrames[3]);
				}
			}
			else {
				if(swordHolding == "majora") {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = Images.linkRaftFrames[9]; // superlink raft
					animList[1] = Images.linkRaftFrames[10]; // superlink raft
					animation = new Animation(animSpeed, animList);
					sprite= Images.linkRaftFrames[9];
				}
				else {
					BufferedImage[] animList = new BufferedImage[2];
					animList[0] = Images.linkRaftFrames[3]; // link raft
					animList[1] = Images.linkRaftFrames[4]; // link raft
					animation = new Animation(animSpeed, animList);
					sprite= Images.linkRaftFrames[3];
				}

			}
		}
	}
	//----------------------------//----------------------------//----------------------------//
	
	
	//spawns swords hitbox depending the direction
	public void swordAttack(Direction direction) {
		swordBounds = (Rectangle) bounds.clone();
		if (direction == Direction.LEFT) {
			swordBounds.x-= 32;				//shifts the sword's boundaries depending the relative direction Link is facing
		}
		else if (direction == Direction.RIGHT) {
			swordBounds.x+= 32;
		}
		else if (direction == Direction.UP) {
			swordBounds.y-= 32;
		}
		else if (direction == Direction.DOWN) {
			swordBounds.y+= 32;
		}
		if (hitCount > 0) {hitCount--;}//Time limit for the sword Boundaries
		else if (hitCount <= 0) {
			hitCount = 29; 
			swordBounds.x = 0;//Moves the hitBoxes out of sight
			swordBounds.y = 0;}
	}	
	
	//spawns rods hitbox depending the direction
	public void rodAttack(Direction direction) {
		rodBounds = (Rectangle) bounds.clone();
		if (direction == Direction.LEFT) {
			rodBounds.x-= 32;
		}
		else if (direction == Direction.RIGHT) {
			rodBounds.x+= 32;
		}
		else if (direction == Direction.UP) {
			rodBounds.y-= 32;
		}
		else if (direction == Direction.DOWN) {
			rodBounds.y+= 32;
		}
		if (hitCount > 0) {hitCount--;}
		else if (hitCount <= 0) {
			hitCount = 29; 
			rodBounds.x = 0;
			rodBounds.y = 0;}
	}	
	//----------------------------//----------------------------//----------------------------//
	
	
	//------Movement Algorithm------
	@Override
	public void move(Direction direction) {
		moving = true;
		changeIntersectingBounds();
		//chack for collisions
		if (ZeldaGameState.inTest) {
			for (SolidStaticEntities objects : handler.getZeldaGameState().innObjects) {
				if(objects.bounds.intersects(interactBounds)) {
					return;
				}
			}
		}
		if (ZeldaGameState.inCave){
			for (SolidStaticEntities objects : handler.getZeldaGameState().caveObjects) {
				if ((objects instanceof Bow) && objects.bounds.intersects(interactBounds)) {
					pickUpAnim.tick();
					horray = true;
					celebratingMethod();
					moving=false;
					count++;
					hasBow=true;
					rod=false;
					hasRod=false;
					hasCandle=false;
					hasBook=false;
					handler.getZeldaGameState().caveObjects.remove(objects);
				}
				if ((objects instanceof RedCandle) && objects.bounds.intersects(interactBounds)) {
					pickUpAnim.tick();
					candle=true;
					horray = true;
					celebratingMethod();
					moving=false;
					count++;
					hasRod=false;
					hasBow=false;
					hasCandle=true;
					hasBook=false;
					handler.getZeldaGameState().caveObjects.remove(objects);
				}
				if ((objects instanceof BookOfMagic) && objects.bounds.intersects(interactBounds)) {
					pickUpAnim.tick();
					horray = true;
					celebratingMethod();
					moving=false;
					count++;
					bookOfMagic=true;
					hasRod=false;
					hasBow=false;
					hasCandle=false;
					hasBook=true;
					handler.getZeldaGameState().caveObjects.remove(objects);
				}
				if ((objects instanceof caveSword) && objects.bounds.intersects(interactBounds)) {
					pickUpAnim.tick();
					hasSword=true;
					horray = true;
					celebratingMethod();
					moving=false;
					count++;
					hasRod=false;
					hasBow=false;
					hasCandle=false;
					hasBook=false;
					swordHolding = "wood";
					handler.getZeldaGameState().caveObjects.remove(objects);
				}
				if ((objects instanceof whiteSword) && objects.bounds.intersects(interactBounds)) {
					pickUpAnim.tick();
					hasSword=true;
					horray = true;
					celebratingMethod();
					moving=false;
					swordHolding = "white";
					count++;
					hasRod=false;
					hasBow=false;					
					hasCandle=false;
					hasBook=false;
					handler.getZeldaGameState().caveObjects.remove(objects);
				}
				if ((objects instanceof magicalSword) && objects.bounds.intersects(interactBounds)) {
					pickUpAnim.tick();
					hasSword=true;
					horray = true;
					celebratingMethod();
					moving=false;
					swordHolding = "magical";
					count++;
					hasRod=false;
					hasBow=false;
					hasCandle=false;
					hasBook=false;
					handler.getZeldaGameState().caveObjects.remove(objects);
				}
				if ((objects instanceof magicalRod) && objects.bounds.intersects(interactBounds)) {
					pickUpAnim.tick();
					hasSword=true;
					horray = true;
					celebratingMethod();
					moving=false;
					rod=true;
					hasRod=true;
					hasBow=false;
					hasCandle=false;
					hasBook=false;
					count++;
					handler.getZeldaGameState().caveObjects.remove(objects);
				}
				if ((objects instanceof superSword) && objects.bounds.intersects(interactBounds)) {
					pickUpAnim.tick();
					hasSword=true;
					horray = true;
					hasRod=false;
					hasBow=false;
					hasCandle=false;
					hasBook=false;
					celebratingMethod();
					moving=false;	
					count++;
					swordHolding = "majora";
					handler.getZeldaGameState().caveObjects.remove(objects);
				}
				if ((objects instanceof superRing) && objects.bounds.intersects(interactBounds)) {
					pickUpAnim.tick();
					hasSword=true;
					horray = true;
					celebratingMethod();
					moving=false;
					count++;
					hasRod=false;
					hasBow=false;
					hasCandle=false;
					hasBook=false;
					superRing=true;
					handler.getZeldaGameState().caveObjects.remove(objects);
				}

				if ((objects instanceof DungeonDoor) && objects.bounds.intersects(bounds) && direction == ((DungeonDoor) objects).direction) {
					if (((DungeonDoor) objects).name.equals("caveStartLeave")) {
						ZeldaGameState.inCave = false;
						x = ((DungeonDoor) objects).nLX + 20;
						y = ((DungeonDoor) objects).nLY + 10;
						direction = DOWN;
					}
				} else if (!(objects instanceof DungeonDoor) && objects.bounds.intersects(interactBounds)) {
					//dont move
					return;
				}
			}
		}else if(otherWorld) {
			for (BaseMovingEntity objects : handler.getZeldaOtherState().enemies.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY)) {
				if(objects.bounds.intersects(bounds) && (objects instanceof Octorok || objects instanceof BouncyFella || objects instanceof Leever  || objects instanceof Lynel || objects instanceof Moblin || objects instanceof Thunderbird || objects instanceof Zora)) {
					hurt=true;
					life-=0.5;
					if(direction == Direction.LEFT) {//Takes care of KnockBack
						x+=knockback;
					}else if(direction == Direction.RIGHT) {
						x-=knockback;
					}
					else if(direction == Direction.UP) {
						y+=knockback;
					}else if(direction == Direction.DOWN) {
						y-=knockback;
					}
				}
				
				//------Item Handler------
				//Sets up how items work if they are picked up by the player
				if((objects instanceof Items)&&objects.bounds.intersects(bounds)) {
					itemPickUp=true;
					if(objects.sprites==Images.rupees) {
						setRupees(getRupees()+50);
					}
					if(objects.sprites==Images.lifePotion) {
						addPotions(1);
					}
					if(objects.sprites==Images.lifePotion2) {
						addOtherPotions(1);
					}
					if(objects.sprites==Images.itemHeart) {
						setLife(getLife()+0.5);
					}
					if(objects.sprites==Images.itemHeart2) {
						setLife(getLife()+1.0);
					}
					if(objects.sprites==Images.bombItem) {
						setBombs(getBombs()+1);
					}
					if(objects.sprites==Images.foodItem) {
						setFood(getFood()+1);
					}
					if(objects.sprites==Images.linkArrows) {
						setArrows(getArrows()+1);
					}
				}

			}
			//----------------------------//----------------------------//----------------------------//
			
			
			//----------MOVING MAP----------
			for (SolidStaticEntities objects : handler.getZeldaOtherState().objects.get(handler.getZeldaOtherState().mapX).get(handler.getZeldaOtherState().mapY)) {
				if ((objects instanceof SectionDoor) && objects.bounds.intersects(bounds) && direction == ((SectionDoor) objects).direction) {
					if (!(objects instanceof DungeonDoor)) {
						movingMap = true;
						movingTo = ((SectionDoor) objects).direction;
						switch (((SectionDoor) objects).direction) {
						case RIGHT:
							newMapX = -(((handler.getZeldaOtherState().mapWidth) + 1) * 1/3 * worldScale);
							newMapY = 0;
							handler.getZeldaOtherState().mapX++;
							xExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						case LEFT:
							newMapX = (((handler.getZeldaOtherState().mapWidth) + 1) * 1/3 * worldScale);
							newMapY = 0;
							handler.getZeldaOtherState().mapX--;
							xExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						case UP:
							newMapX = 0;
							newMapY = -(((handler.getZeldaOtherState().mapHeight) + 1) * 1/3 * worldScale);
							handler.getZeldaOtherState().mapY--;
							yExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						case DOWN:
							newMapX = 0;
							newMapY = (((handler.getZeldaOtherState().mapHeight) + 1) * 1/3 * worldScale);
							handler.getZeldaOtherState().mapY++;
							yExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						}
						return;
					}
					else {
						//dungeon doors
					}
				}
				else if (!(objects instanceof SectionDoor) && objects.bounds.intersects(interactBounds)) {
					//dont move
					return;
				}
				else if(objects instanceof blockBound &&  objects.bounds.intersects(interactBounds)) {
					return;
				}
			}
		}
		
		//Takes care of collisions in the dungeon map
		else if(dungeon) {
			for (BaseMovingEntity objects : handler.getZeldaDungeonState().enemies.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY)) {
				if(objects.bounds.intersects(bounds) && (objects instanceof Octorok || objects instanceof BouncyFella || objects instanceof Leever  || objects instanceof Lynel || objects instanceof Moblin || objects instanceof Thunderbird || objects instanceof Zora)) {
					hurt=true;
					life-=0.5;
					if(direction == Direction.LEFT) {
						x+=knockback;
					}else if(direction == Direction.RIGHT) {
						x-=knockback;
					}
					else if(direction == Direction.UP) {
						y+=knockback;
					}else if(direction == Direction.DOWN) {
						y-=knockback;
					}
				}
				
				//checks for items in dungeonState
				if((objects instanceof Items)&&objects.bounds.intersects(bounds)) {
					itemPickUp=true;
					if(objects.sprites==Images.rupees) {
						setRupees(getRupees()+50);
					}
					if(objects.sprites==Images.lifePotion) {
						addPotions(1);
					}
					if(objects.sprites==Images.lifePotion2) {
						addOtherPotions(1);
					}
					if(objects.sprites==Images.itemHeart) {
						setLife(getLife()+0.5);
					}
					if(objects.sprites==Images.itemHeart2) {
						setLife(getLife()+1.0);
					}
					if(objects.sprites==Images.bombItem) {
						setBombs(getBombs()+1);
					}
					if(objects.sprites==Images.foodItem) {
						setFood(getFood()+1);
					}
					if(objects.sprites==Images.linkArrows) {
						setArrows(getArrows()+1);
					}
				}
			}
			for (SolidStaticEntities objects : handler.getZeldaDungeonState().objects.get(handler.getZeldaDungeonState().mapX).get(handler.getZeldaDungeonState().mapY)) {
				if ((objects instanceof SectionDoor) && objects.bounds.intersects(bounds) && direction == ((SectionDoor) objects).direction) {
					if (!(objects instanceof DungeonDoor)) {
						movingMap = true;
						movingTo = ((SectionDoor) objects).direction;
						switch (((SectionDoor) objects).direction) {
						case RIGHT:
							newMapX = -(((handler.getZeldaDungeonState().mapWidth) + 1) * 1/3 * worldScale);
							newMapY = 0;
							handler.getZeldaDungeonState().mapX++;
							xExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						case LEFT:
							newMapX = (((handler.getZeldaDungeonState().mapWidth) + 1) * 1/3 * worldScale);
							newMapY = 0;
							handler.getZeldaDungeonState().mapX--;
							xExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						case UP:
							newMapX = 0;
							newMapY = -(((handler.getZeldaDungeonState().mapHeight) + 1) * 1/3 * worldScale);
							handler.getZeldaDungeonState().mapY--;
							yExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						case DOWN:
							newMapX = 0;
							newMapY = (((handler.getZeldaDungeonState().mapHeight) + 1) * 1/3 * worldScale);
							handler.getZeldaDungeonState().mapY++;
							yExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						}
						return;
					}
					else {
						//dungeon doors
						if (((DungeonDoor) objects).name.equals("other")) {
							direction = UP;
							handler.changeState(handler.getZeldaOtherState());
							//							x = ((DungeonDoor) objects).nLX - 100;
							//							y = ((DungeonDoor) objects).nLY - 70;
						}
					}
				}
				else if (!(objects instanceof SectionDoor) && objects.bounds.intersects(interactBounds)) {
					//dont move
					return;
				}
				else if(objects instanceof blockBound &&  objects.bounds.intersects(interactBounds)) {
					return;
				}
			}
		}
		else {
			for (BaseMovingEntity objects : handler.getZeldaGameState().enemies.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {
				if(objects.bounds.intersects(bounds) && (objects instanceof Octorok || objects instanceof BouncyFella || objects instanceof Leever  || objects instanceof Lynel || objects instanceof Moblin || objects instanceof Thunderbird || objects instanceof Zora)) {
					hurt=true;//check for all enemies to avoid many if's
					life-=0.5;
					if(direction == Direction.LEFT) {
						x+=knockback;
					}else if(direction == Direction.RIGHT) {
						x-=knockback;
					}
					else if(direction == Direction.UP) {
						y+=knockback;
					}else if(direction == Direction.DOWN) {
						y-=knockback;
					}
				}
				if((objects instanceof Items)&&objects.bounds.intersects(bounds)) {
					itemPickUp=true;
					if(objects.sprites==Images.rupees) {
						setRupees(getRupees()+50);
					}
					if(objects.sprites==Images.lifePotion) {
						addPotions(1);
					}
					if(objects.sprites==Images.lifePotion2) {
						addOtherPotions(1);
					}
					if(objects.sprites==Images.itemHeart) {
						setLife(getLife()+0.5);
					}
					if(objects.sprites==Images.itemHeart2) {
						setLife(getLife()+1.0);
					}
					if(objects.sprites==Images.bombItem) {
						setBombs(getBombs()+1);
					}
					if(objects.sprites==Images.foodItem) {
						setFood(getFood()+1);
					}
					if(objects.sprites==Images.linkArrows) {
						setArrows(getArrows()+1);
					}
				}
			}
			for (SolidStaticEntities objects : handler.getZeldaGameState().objects.get(handler.getZeldaGameState().mapX).get(handler.getZeldaGameState().mapY)) {


				if((objects instanceof moblinArrow)&&objects.bounds.intersects(bounds)) {
					hurt=true;
					life-=0.5;
					if(direction == Direction.LEFT) {
						x+=knockback;
					}else if(direction == Direction.RIGHT) {
						x-=knockback;
					}
					else if(direction == Direction.UP) {
						y+=knockback;
					}else if(direction == Direction.DOWN) {
						y-=knockback;
					}
				}
				if ((objects instanceof SectionDoor) && objects.bounds.intersects(bounds) && direction == ((SectionDoor) objects).direction) {
					if (!(objects instanceof DungeonDoor)) {
						movingMap = true;
						movingTo = ((SectionDoor) objects).direction;
						switch (((SectionDoor) objects).direction) {
						case RIGHT:
							newMapX = -(((handler.getZeldaGameState().mapWidth) + 1) * 1/3 * worldScale);
							newMapY = 0;
							handler.getZeldaGameState().mapX++;
							xExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						case LEFT:
							newMapX = (((handler.getZeldaGameState().mapWidth) + 1) * 1/3 * worldScale);
							newMapY = 0;
							handler.getZeldaGameState().mapX--;
							xExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						case UP:
							newMapX = 0;
							newMapY = -(((handler.getZeldaGameState().mapHeight) + 1) * 1/3 * worldScale);
							handler.getZeldaGameState().mapY--;
							yExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						case DOWN:
							newMapX = 0;
							newMapY = (((handler.getZeldaGameState().mapHeight) + 1) * 1/3 * worldScale);
							handler.getZeldaGameState().mapY++;
							yExtraCounter = 8 * worldScale + (2 * worldScale);
							break;
						}
						return;
					}
					else {
						if (((DungeonDoor) objects).name.equals("caveStartEnter")) {
							ZeldaGameState.inCave = true;
							x = ((DungeonDoor) objects).nLX + 10;
							y = ((DungeonDoor) objects).nLY - 40;
							direction = UP;
						}
						if (((DungeonDoor) objects).name.equals("inn")) {
							ZeldaGameState.inTest = true;
							x = ((DungeonDoor) objects).nLX - 165;
							y = ((DungeonDoor) objects).nLY - 70;
							direction = UP;
						}
						if (((DungeonDoor) objects).name.equals("merchant")) {
							handler.changeState(handler.getZeldaMerchantState());
						}
						if (((DungeonDoor) objects).name.equals("dungeon1")) {
							dungeon=true;
							x = ((DungeonDoor) objects).nLX - 100;
							y = ((DungeonDoor) objects).nLY - 70;
							direction = UP;
						}
						if (((DungeonDoor) objects).name.equals("other")) {
							handler.changeState(handler.getZeldaOtherState());
							x = ((DungeonDoor) objects).nLX - 100;
							y = ((DungeonDoor) objects).nLY - 70;
							direction = UP;
						}
					}
				}
				else if((objects instanceof riverBlock) &&  objects.bounds.intersects(interactBounds)&& !raft) {
					return;
				}
				else if ((objects instanceof raft) && objects.bounds.intersects(interactBounds)) {
					hasRaft=true;
					pickUpAnim.tick();
					horray = true;
					celebratingMethod();
					moving=false;
					count++;
					removeRaft=true;
				}
				else if (!(objects instanceof SectionDoor)&&!(objects instanceof riverBlock) && objects.bounds.intersects(interactBounds)) {
					//dont move
					return;
				}
			}
		}
		//Movement
		switch (direction) {
		case RIGHT:
			x += speed;
			break;
		case LEFT:
			x -= speed;
			break;
		case UP:
			y -= speed;
			break;
		case DOWN:
			y += speed;
			break;
		}
		bounds.x = x;
		bounds.y = y;
		changeIntersectingBounds();
	}
	@Override
	public void render(Graphics g) {
		g.drawRect(swordBounds.x, swordBounds.y, swordBounds.width, swordBounds.height);
		g.drawRect(rodBounds.x, rodBounds.y, rodBounds.width, rodBounds.height);
		if (moving && !attacking && !horray) {
			g.drawImage(animation.getCurrentFrame(),x , y, width , height  , null);

		} else {
			if (movingMap && !attacking){
				g.drawImage(animation.getCurrentFrame(),x , y, width, height  , null);
			}
			if (!attacking && !horray) {
				g.drawImage(sprite, x , y, width , height , null);
			}
		}
		if(horray && !attacking && !moving ) {
			g.drawImage(pickUpAnim.getCurrentFrame(),x , y, width , height  , null);
			if(superRing) {
				g.drawImage(Images.superRingFrames[0],x , y -40, width, height  , null);
			}
			if(hasRaft) {
				g.drawImage(Images.linkRaftFrames[0],x , y -40, width, height  , null);
			}
			if(swordHolding == "wood") {
				g.drawImage(Images.npc[4],x , y -40, width/2 , height  , null);
			}
			if(swordHolding == "white") {
				g.drawImage(Images.otherWeapons[0],x , y -40, width/2 , height  , null);
			}
			if(swordHolding == "magical") {
				g.drawImage(Images.otherWeapons[1],x , y -40, width/2 , height  , null);
			}
			if(rod&&hasRod) {
				g.drawImage(Images.otherWeapons[2],x , y -40, width/2 , height  , null);
			}
			if(candle && hasCandle) {
				g.drawImage(Images.otherWeapons[17],x , y -40, width/2 , height  , null);
			}			
			if(bookOfMagic&&hasBook) {
				g.drawImage(Images.otherWeapons[18],x , y -40, width/2 , height  , null);
			}
			if(swordHolding == "majora") {
				g.drawImage(Images.otherWeapons[3],x , y -40, width/2 , height  , null);
			}
		}
		if (attacking && !horray) {  
			attackAnim.tick();

			if(direction == Direction.LEFT) {
				g.drawImage(attackAnim.getCurrentFrame(),this.x -(attackAnim.getCurrentFrame().getWidth()*handler.getZeldaGameState().worldScale-this.width) , y,attackAnim.getCurrentFrame().getWidth()*handler.getZeldaGameState().worldScale ,attackAnim.getCurrentFrame().getHeight()*handler.getZeldaGameState().worldScale, null);
			}
			else if(direction == Direction.UP) {
				g.drawImage(attackAnim.getCurrentFrame(),x , this.y -(attackAnim.getCurrentFrame().getHeight()*handler.getZeldaGameState().worldScale -this.height), attackAnim.getCurrentFrame().getWidth()*handler.getZeldaGameState().worldScale ,attackAnim.getCurrentFrame().getHeight()*handler.getZeldaGameState().worldScale, null);
			}else {g.drawImage(attackAnim.getCurrentFrame(),x , y, attackAnim.getCurrentFrame().getWidth()*handler.getZeldaGameState().worldScale ,attackAnim.getCurrentFrame().getHeight()*handler.getZeldaGameState().worldScale, null); }
		}
		if (hurt && !attacking && !horray) {
			g.drawImage(hurtAnim.getCurrentFrame(),x , y, width , height, null); 
		}
	}
	//----------------------------//----------------------------//----------------------------//
	
	
	//------Getters and Setters------
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}
	public int getRupees() {
		return rupees;
	}
	public int setRupees(int rup) {
		return this.rupees = rup;
	}
	public int addPotions(int pot) {
		return this.potions += pot;
	}
	public int getPotions() {
		return potions;
	}
	public int addOtherPotions(int pot) {
		return this.otherPotions += pot;
	}
	public int getOtherPotions() {
		return otherPotions;
	}
	public int getFood() {
		return food;
	}
	public void setFood(int food) {
		this.food = food;
	}
	public int getBombs() {
		return bombs;
	}
	public void setBombs(int bombs) {
		this.bombs = bombs;
	}
	public int getArrows() {
		return arrows;
	}
	public void setArrows(int arrows) {
		this.arrows = arrows;
	}
}

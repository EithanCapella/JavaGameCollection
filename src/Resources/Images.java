package Resources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by AlexVR on 1/24/2020.
 */
public class Images {


    public static BufferedImage titleScreenBackground;
    public static BufferedImage pauseBackground;
    public static BufferedImage selectionBackground;
    public static BufferedImage galagaCopyright;
    public static BufferedImage galagaSelect;
    public static BufferedImage muteIcon;
    public static BufferedImage galagaPlayerLaser;
    public static BufferedImage gameOverScreen;
    public static BufferedImage galagaTrophy, galagaSilverTrophy, galagaBronzeTrophy;
    public static BufferedImage[] startGameButton;
    public static BufferedImage[] galagaLogo;
    public static BufferedImage[] pauseResumeButton;
    public static BufferedImage[] pauseToTitleButton;
    public static BufferedImage[] pauseOptionsButton;
    public static BufferedImage[] galagaPlayer;
    public static BufferedImage[] galagaPlayerDeath;
    public static BufferedImage[] linkFight;
    public static BufferedImage[] ganonFight;
    public static BufferedImage[] galagaEnemyDeath;
    public static BufferedImage[] galagaEnemyBee;
    public static BufferedImage[] galagaHeMan;
    public static BufferedImage[] KirbyPowerUp;
    public static BufferedImage[] galagaNewEnemy;
    public static BufferedImage heManImage;
    public static BufferedImage heManAttack;
    public SpriteSheet heManSheet;
    public SpriteSheet heManAttackSheet;
    public static BufferedImage map1;
    public static BufferedImage ghost;
    public static BufferedImage[] pacmanDots;
    public static BufferedImage[] pacmanRight;
    public static BufferedImage[] pacmanLeft;
    public static BufferedImage[] pacmanUp;
    public static BufferedImage[] pacmanDown;
    public static BufferedImage[] bound;
    public static BufferedImage intro;
    public static BufferedImage start;
    public static BufferedImage galagaImageSheet;
    public SpriteSheet galagaSpriteSheet;
    public static BufferedImage pacmanImageSheet;
    public SpriteSheet pacmanSpriteSheet;
    public static BufferedImage zeldaImageSheet;
    public static BufferedImage storyImageSheet;
    public SpriteSheet zeldaSpriteSheet;
    public SpriteSheet storySpriteSheet;
    public static BufferedImage fightImageSet;
    public SpriteSheet linkFightSprites;
    public static BufferedImage GanonImageSet;
    public SpriteSheet GanonFightSprites;
    public static BufferedImage zeldaTriforceLogo;
    public static BufferedImage zeldaMap;
    public static BufferedImage otherZeldaMap;
    public static BufferedImage zeldaDungeon;
    public static ArrayList<BufferedImage> zeldaTiles;
    public static ArrayList<BufferedImage> recursiveTiles;
    public static BufferedImage[] zeldaTitleFrames;
    public static BufferedImage[] zeldaStoryFrames;
    public static BufferedImage[] itemPickUpFrames;
    public static BufferedImage zeldaWorldLayoutTileImage;
    public SpriteSheet zeldaWorldLayoutTileSpriteSheet;
    public static ArrayList<BufferedImage> zeldaWorldLayoutTiles;
    public static BufferedImage zeldaLinkImage;
    public static BufferedImage npcImage;
    public static BufferedImage itemImage;
    public static BufferedImage[] npc;
    public SpriteSheet zeldaLinkSpriteSheet;
    public SpriteSheet zeldaLinkSpriteSheet1;
    public SpriteSheet npcSpriteSheet;
    public SpriteSheet itemSpriteSheet;
    public static BufferedImage lifeImage;
    public static BufferedImage[] attackSlots;
    public SpriteSheet menuSpriteSheet;
    public SpriteSheet innSpriteSheet;
    public SpriteSheet thunderBirdSpriteSheet;
    public static BufferedImage[] thunderBirdFrames;
    public static BufferedImage[] attackThunderBirdFrames; //flying
    public static BufferedImage[] deathThunderBirdFrames;
    public static BufferedImage[] superRingFrames;




    public static BufferedImage[] zeldaLinkFrames;
    public static BufferedImage[] superLinkFrames;
    public static BufferedImage[] linkHurtFrames;
    public static BufferedImage[] woodenSwordAttackFrames;
    public static BufferedImage[] whiteSwordAttackFrames;
    public static BufferedImage[] magicalSwordAttackFrames;
    public static BufferedImage[] magicalRodAttackFrames;
    public static BufferedImage[] superSwordAttackFrames;
    public static BufferedImage[] superRodAttackFrames;
    public static BufferedImage[] linkRaftFrames;
    public static BufferedImage[] linkHearts;
    public static BufferedImage[] itemHeart;
    public static BufferedImage[] itemHeart2;
    public static BufferedImage[] lifePotion;
    public static BufferedImage[] lifePotion2;
    public static BufferedImage[] linkArrows;
    public static BufferedImage[] bombItem;
    public static BufferedImage[] foodItem;

    public static BufferedImage[] woodenLaser;
    public static BufferedImage[] vaporWaveUp;
    public static BufferedImage[] vaporWaveDown;
    public static BufferedImage[] vaporWaveSide;
    public static BufferedImage[] vaporWaveSideL;
    public static BufferedImage[] rodWaveUp;
    public static BufferedImage[] rodWaveDown;
    public static BufferedImage[]rodWaveSide;
    public static BufferedImage[] rodWaveSideL;
    public static BufferedImage triforcePointer;
    public static BufferedImage[] rupees;
    public static BufferedImage[] inn;



    public static BufferedImage morshuSketchy;
    public static BufferedImage morshuNoMoney;
    public static BufferedImage morshuSpeaking;
    public static BufferedImage morshuStealing;
    public static BufferedImage morshuThreat;
    public static BufferedImage morshuWelcome;
    public static ArrayList<BufferedImage> forestTiles;
    public static ArrayList<BufferedImage> caveTiles;
    public static ArrayList<BufferedImage> mountainTiles;
    public static ArrayList<BufferedImage> graveTiles;
    public static BufferedImage EnemyOverwoldImage;
    public SpriteSheet EnemyOverwoldSpriteSheet;
    public SpriteSheet EnemyOverwoldSpriteSheet1;
    public static BufferedImage[] bouncyEnemyFrames;
    public static BufferedImage[] octorokEnemyFrames;
    public static BufferedImage[] octoHurtFrames;
    public static BufferedImage[] otherOctoFrames;
    public static BufferedImage[] moblinEnemyFrames;
    public static BufferedImage[] dmoblinEnemyFrames;
    public static BufferedImage[] leeverEnemyFrames;
    public static BufferedImage[] zoraEnemyFrames;
    public static BufferedImage[] lynelEnemyFrames;
    public static BufferedImage[] dlynelEnemyFrames;
    public static BufferedImage[] moblinArrow;
    public static BufferedImage[] dmoblinArrow;
    public static BufferedImage octoBall;
    public static BufferedImage[] otherWeapons;
    public static BufferedImage[] whiteProyectileUp;
    public static BufferedImage[] whiteProyectileDown;
    public static BufferedImage[] whiteProyectileSide;
    public static BufferedImage[] whiteProyectileSideL;
    public static BufferedImage[] majoraProyectileUp;
    public static BufferedImage[] majoraProyectileDown;
    public static BufferedImage[] majoraProyectileSide;
    public static BufferedImage[] majoraProyectileSideL;
    public static BufferedImage[] magicalProyectileUp;
    public static BufferedImage[] magicalProyectileDown;
    public static BufferedImage[] magicalProyectileSide;
    public static BufferedImage[] magicalProyectileSideL;
    public static BufferedImage[] superProyectile;   
    public static BufferedImage[] fireFrame;
    public static BufferedImage[] linkArrowsProjectileUp;
    public static BufferedImage[] linkArrowsProjectileSide;
    public static BufferedImage[] linkArrowsProjectileSideL;
    public static BufferedImage[] linkArrowsProjectileDown;
    public static BufferedImage[] bombExplosionsFrames;
    public static BufferedImage[] recursiveTileset;



    








    public Images() {

        startGameButton = new BufferedImage[3];
        pauseResumeButton = new BufferedImage[2];
        pauseToTitleButton = new BufferedImage[2];
        pauseOptionsButton = new BufferedImage[2];
        galagaLogo = new BufferedImage[3];
        galagaPlayer = new BufferedImage[8];//not full yet, only has second to last image on sprite sheet
        galagaPlayerDeath = new BufferedImage[8];
        galagaEnemyDeath = new BufferedImage[5];
        galagaEnemyBee = new BufferedImage[8];
        KirbyPowerUp = new BufferedImage[2];
        galagaNewEnemy = new BufferedImage[8];
        galagaHeMan = new BufferedImage[5];
        
        
        pacmanDots = new BufferedImage[2];
        pacmanRight = new BufferedImage[2];
        pacmanLeft = new BufferedImage[2];
        pacmanUp = new BufferedImage[2];
        pacmanDown = new BufferedImage[2];
        bound = new BufferedImage[16];

        zeldaTiles = new ArrayList<>();
        zeldaTitleFrames = new BufferedImage[6];
        zeldaStoryFrames = new BufferedImage[8];
        zeldaWorldLayoutTiles = new ArrayList<>();

        recursiveTiles = new ArrayList<>();
        forestTiles = new ArrayList<>();
        caveTiles = new ArrayList<>();
        graveTiles = new ArrayList<>();
        mountainTiles = new ArrayList<>();

        zeldaLinkFrames = new BufferedImage[6];
        superLinkFrames = new BufferedImage[6];
        woodenSwordAttackFrames = new BufferedImage[16];
        whiteSwordAttackFrames = new BufferedImage[16];
        magicalSwordAttackFrames = new BufferedImage[16];
        magicalRodAttackFrames = new BufferedImage[16];
        superSwordAttackFrames = new BufferedImage[16];
        superRodAttackFrames = new BufferedImage[16];
        linkRaftFrames = new BufferedImage[16];
        linkHearts= new BufferedImage[2];
        itemHeart= new BufferedImage[2];
        itemHeart2= new BufferedImage[2];
        lifePotion= new BufferedImage[2];
        lifePotion2= new BufferedImage[2];
        linkArrows= new BufferedImage[2];
        bombItem= new BufferedImage[2];
        foodItem= new BufferedImage[2];
        linkFight = new BufferedImage[16];
        ganonFight = new BufferedImage[16];
        bouncyEnemyFrames = new BufferedImage[2];
        octorokEnemyFrames = new BufferedImage[4];
        octoHurtFrames = new BufferedImage[2];
        otherOctoFrames = new BufferedImage[4];
        moblinEnemyFrames = new BufferedImage[6];
        dmoblinEnemyFrames = new BufferedImage[6];
        leeverEnemyFrames = new BufferedImage[4];
        zoraEnemyFrames = new BufferedImage[4];
        lynelEnemyFrames = new BufferedImage[4];
        dlynelEnemyFrames = new BufferedImage[4];
        moblinArrow = new BufferedImage[5];
        dmoblinArrow = new BufferedImage[4];
        npc = new BufferedImage[5];
        itemPickUpFrames= new BufferedImage[2];
        linkHurtFrames = new BufferedImage[3];
        woodenLaser = new BufferedImage[3];
        otherWeapons = new BufferedImage[22];
        vaporWaveUp = new BufferedImage[4];
        vaporWaveDown = new BufferedImage[4];
        vaporWaveSide = new BufferedImage[4];
        vaporWaveSideL = new BufferedImage[4];
        rodWaveUp = new BufferedImage[2];
        rodWaveDown = new BufferedImage[2];
        rodWaveSide = new BufferedImage[2];
        rodWaveSideL = new BufferedImage[2];
        rupees = new BufferedImage[2];
        inn = new BufferedImage[4];
        whiteProyectileUp= new BufferedImage[2];
        whiteProyectileDown= new BufferedImage[2];
        whiteProyectileSide= new BufferedImage[2];
        whiteProyectileSideL= new BufferedImage[2];
        magicalProyectileUp= new BufferedImage[2];
        magicalProyectileDown= new BufferedImage[2];
        magicalProyectileSide= new BufferedImage[2];
        magicalProyectileSideL= new BufferedImage[2];
        thunderBirdFrames = new BufferedImage[60];
        attackThunderBirdFrames = new BufferedImage[15];
        deathThunderBirdFrames = new BufferedImage[4];
        superRingFrames = new BufferedImage[7];
        attackSlots = new BufferedImage[2];
        fireFrame = new BufferedImage[2];
        linkArrowsProjectileUp= new BufferedImage[2];
        linkArrowsProjectileDown= new BufferedImage[2];
        linkArrowsProjectileSide= new BufferedImage[2];
        linkArrowsProjectileSideL= new BufferedImage[2];
        bombExplosionsFrames = new BufferedImage[3];
        
        majoraProyectileUp= new BufferedImage[2];
        majoraProyectileDown= new BufferedImage[2];
        majoraProyectileSide= new BufferedImage[2];
        majoraProyectileSideL= new BufferedImage[2];
        recursiveTileset = new BufferedImage[4];
        





        





        try {

            startGameButton[0]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/NormalStartButton.png"));
            startGameButton[1]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/HoverStartButton.png"));
            startGameButton[2]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/ClickedStartButton.png"));

            titleScreenBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Title.png"));

            pauseBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Pause.png"));

            selectionBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Selection.png"));

            galagaCopyright = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/Copyright.png"));

            galagaSelect = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/galaga_select.png"));

            muteIcon = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/mute.png"));

            galagaLogo[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/galaga_logo.png"));
            galagaLogo[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Galaga/hover_galaga_logo.png"));
            galagaLogo[2] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Galaga/pressed_galaga_logo.png"));

            pauseResumeButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/Resume/NormalHoverResume.png"));
            pauseResumeButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/Resume/PressedResume.png"));

            pauseToTitleButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToTitle/NormalHoverToTitleButton.png"));
            pauseToTitleButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToTitle/PressedToTitleButton.png"));

            pauseOptionsButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToOptions/NormalHoverToOptionsButton.png"));
            pauseOptionsButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToOptions/PressedToOptionsButton.png"));

            galagaImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Galaga/Galaga.png"));
            galagaSpriteSheet = new SpriteSheet(galagaImageSheet);

            galagaPlayer[0] = galagaSpriteSheet.crop(160,55,15,16);

            galagaPlayerDeath[0] = galagaSpriteSheet.crop(209,48,32,32);
            galagaPlayerDeath[1] = galagaSpriteSheet.crop(209,48,32,32);
            galagaPlayerDeath[2] = galagaSpriteSheet.crop(247,48,32,32);
            galagaPlayerDeath[3] = galagaSpriteSheet.crop(247,48,32,32);
            galagaPlayerDeath[4] = galagaSpriteSheet.crop(288,47,32,32);
            galagaPlayerDeath[5] = galagaSpriteSheet.crop(288,47,32,32);
            galagaPlayerDeath[6] = galagaSpriteSheet.crop(327,47,32,32);
            galagaPlayerDeath[7] = galagaSpriteSheet.crop(327,47,32,32);

            galagaEnemyDeath[0] = galagaSpriteSheet.crop(201,191,32,32);
            galagaEnemyDeath[1] = galagaSpriteSheet.crop(223,191,32,32);
            galagaEnemyDeath[2] = galagaSpriteSheet.crop(247,191,32,32);
            galagaEnemyDeath[3] = galagaSpriteSheet.crop(280,191,32,32);
            galagaEnemyDeath[4] = galagaSpriteSheet.crop(320,191,32,32);

            galagaEnemyBee[0] = galagaSpriteSheet.crop(188,178,9,10);
            galagaEnemyBee[1] = galagaSpriteSheet.crop(162,178,13,10);
            galagaEnemyBee[2] = galagaSpriteSheet.crop(139,177,11,12);
            galagaEnemyBee[3] = galagaSpriteSheet.crop(113,176,14,13);
            galagaEnemyBee[4] = galagaSpriteSheet.crop(90,177,13,13);
            galagaEnemyBee[5] = galagaSpriteSheet.crop(65,176,13,14);
            galagaEnemyBee[6] = galagaSpriteSheet.crop(42,178,12,11);
            galagaEnemyBee[7] = galagaSpriteSheet.crop(19,177,10,13);

            KirbyPowerUp[0] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Galaga/KirbyWarp.png"));
            KirbyPowerUp[1] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Galaga/KirbyWarpDim.png"));
            
            galagaTrophy = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Galaga/trophy.png"));
            galagaSilverTrophy = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Galaga/silver.png"));
            galagaBronzeTrophy = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Galaga/bronze.png"));
            
            galagaPlayerLaser = galagaSpriteSheet.crop(365 ,219,3,8);

            pacmanImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/Background.png"));
            pacmanSpriteSheet = new SpriteSheet(pacmanImageSheet);
            map1 = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/PacManMaps/map1.png"));
            ghost = pacmanSpriteSheet.crop(456,64,16,16);
            pacmanDots[0] = pacmanSpriteSheet.crop(643,18,16,16);
            pacmanDots[1] = pacmanSpriteSheet.crop(623,18,16,16);

            bound[0] = pacmanSpriteSheet.crop(603,18,16,16);//single
            bound[1] = pacmanSpriteSheet.crop(615,37,16,16);//right open
            bound[2] = pacmanSpriteSheet.crop(635,37,16,16);//down open
            bound[3] = pacmanSpriteSheet.crop(655,37,16,16);//left open
            bound[4] = pacmanSpriteSheet.crop(655,57,16,16);//up open
            bound[5] = pacmanSpriteSheet.crop(655,75,16,16);//up/down
            bound[6] = pacmanSpriteSheet.crop(656,116,16,16);//left/Right
            bound[7] = pacmanSpriteSheet.crop(656,136,16,16);//up/Right
            bound[8] = pacmanSpriteSheet.crop(655,174,16,16);//up/left
            bound[9] = pacmanSpriteSheet.crop(655,155,16,16);//down/Right
            bound[10] = pacmanSpriteSheet.crop(655,192,16,16);//down/left
            bound[11] = pacmanSpriteSheet.crop(664,232,16,16);//all
            bound[12] = pacmanSpriteSheet.crop(479,191,16,16);//left
            bound[13] = pacmanSpriteSheet.crop(494,191,16,16);//right
            bound[14] = pacmanSpriteSheet.crop(479,208,16,16);//top
            bound[15] = pacmanSpriteSheet.crop(479,223,16,16);//bottom

            pacmanRight[0] = pacmanSpriteSheet.crop(473,1,12,13);
            pacmanRight[1] = pacmanSpriteSheet.crop(489,1,13,13);

            pacmanLeft[0] = pacmanSpriteSheet.crop(474,17,12,13);
            pacmanLeft[1] = pacmanSpriteSheet.crop(489,1,13,13);

            pacmanUp[0] = pacmanSpriteSheet.crop(473,34,13,12);
            pacmanUp[1] = pacmanSpriteSheet.crop(489,1,13,13);

            pacmanDown[0] = pacmanSpriteSheet.crop(473,48,13,12);
            pacmanDown[1] = pacmanSpriteSheet.crop(489,1,13,13);

            intro = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/intro.png"));
            start = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/startScreen.png"));

            //MORSHU POWER
            morshuSketchy = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/Merchant/MorshuBeingSketchy.png"));
            morshuNoMoney = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/Merchant/MorshuNoMoney.png"));
            morshuSpeaking = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/Merchant/MorshuSpeaking.png"));
            morshuStealing = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/Merchant/MorshuStealing.png"));
            morshuThreat = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/Merchant/MorshuThreat.png"));
            morshuWelcome = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/Merchant/MorshuWelcome.png"));
            gameOverScreen = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/GameOverZelda.png"));
            
            zeldaImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/tileSet.png"));
            zeldaTriforceLogo = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/triforceLogo.png"));
            zeldaMap = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/map.png"));
            zeldaMap = createImageTransparent(zeldaMap,"zelddaMap_0,128,0,green",new Color(0,128,0).getRGB());
            otherZeldaMap = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/zelda2.png"));
            zeldaDungeon = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/dungeon.png"));
            zeldaImageSheet = createImageTransparent(zeldaImageSheet,"tileSets_0,120,0,green",new Color(0,128,0).getRGB());
            zeldaSpriteSheet = new SpriteSheet(zeldaImageSheet);
            
          //2d FIGHTER SPRITES
            fightImageSet = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/LinkFight.png"));
            linkFightSprites = new SpriteSheet(fightImageSet);
            linkFight[0] = linkFightSprites.crop(73, 8, 34, 53); //link idle
            linkFight[1] = linkFightSprites.crop(275, 73, 34, 53); //block
            linkFight[2] = linkFightSprites.crop(6, 163, 49, 41); // attack start
            linkFight[3] = linkFightSprites.crop(5, 204, 54, 41); // attack mid
            linkFight[4] = linkFightSprites.crop(76, 180, 51, 62); // attack final
            linkFight[5] = linkFightSprites.crop(275, 73, 34, 53); //jump
            linkFight[6] = linkFightSprites.crop(275, 73, 34, 53); //jump
            linkFight[7] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/stage.png")); //level
            linkFight[8] = linkFightSprites.crop(4, 69, 39, 34); //run start
            linkFight[9] = linkFightSprites.crop(50, 67, 39, 34); //run mid
            linkFight[10] = linkFightSprites.crop(89, 67, 39, 34); //run end
            linkFight[11] = linkFightSprites.crop(138, 173, 41, 32); //attack start [low]
            linkFight[12] = linkFightSprites.crop(137, 211, 49, 32); //attack mid
            linkFight[13] = linkFightSprites.crop(193, 180, 75, 34); //attack end

            GanonImageSet = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/GanonFight.png"));
            GanonFightSprites = new SpriteSheet(GanonImageSet);
            ganonFight[0] = GanonFightSprites.crop(230, 113, 42, 83);// Ganon idle
            ganonFight[1] = GanonFightSprites.crop(290, 144, 77, 36);// Ganon move
            ganonFight[2] = GanonFightSprites.crop(4, 16, 57, 75);// Ganon attack sword
            ganonFight[3] = GanonFightSprites.crop(257, 211, 58, 83);// Ganon charge
            ganonFight[4] = GanonFightSprites.crop(4, 316, 63, 83);// Ganon punch 1 start
            ganonFight[5] = GanonFightSprites.crop(80, 305, 259, 94);// Ganon punch 1 mid
            ganonFight[6] = GanonFightSprites.crop(362, 306, 166, 95);// Ganon punch 1 end




            storyImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/title.png"));
            storySpriteSheet = new SpriteSheet(storyImageSheet);
            zeldaTitleFrames[5] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/TitleScreen/frame_0.gif"));
            zeldaTitleFrames[4] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/TitleScreen/frame_1.gif"));
            zeldaTitleFrames[3] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/TitleScreen/frame_2.gif"));
            zeldaTitleFrames[2] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/TitleScreen/frame_3.gif"));
            zeldaTitleFrames[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/TitleScreen/frame_4.gif"));
            zeldaTitleFrames[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/TitleScreen/frame_5.gif"));
            triforcePointer = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/Merchant/triforceCursor.png"));

            zeldaStoryFrames[0] = storySpriteSheet.crop(1, 250, 256,223);
            zeldaStoryFrames[1] = storySpriteSheet.crop(258, 250, 256,223);
            zeldaStoryFrames[2] = storySpriteSheet.crop(515, 250, 256,223);
            zeldaStoryFrames[3] = storySpriteSheet.crop(772, 250, 256,223);
            zeldaStoryFrames[4] = storySpriteSheet.crop(1, 475, 256,223);
            zeldaStoryFrames[5] = storySpriteSheet.crop(258, 475, 256,223);
            zeldaStoryFrames[6] = storySpriteSheet.crop(515, 475, 256,223);
            zeldaStoryFrames[7] = storySpriteSheet.crop(772, 475, 256,64);

            zeldaLinkImage = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/link.png"));
            zeldaLinkImage = createImageTransparent(zeldaLinkImage,"link_0,128,0_green",new Color(0,128,0).getRGB());
            zeldaLinkSpriteSheet = new SpriteSheet(createImageTransparent(zeldaLinkImage,"link_116,116,116_gray",new Color(116,116,116).getRGB()));
            zeldaLinkSpriteSheet1 = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/link1.png")));

            zeldaLinkFrames[0] = zeldaLinkSpriteSheet.crop(1,11,16,16);
            zeldaLinkFrames[1] = zeldaLinkSpriteSheet.crop(18,11,16,16);
            zeldaLinkFrames[2] = zeldaLinkSpriteSheet.crop(35,11,16,16);
            zeldaLinkFrames[3] = zeldaLinkSpriteSheet.crop(52,11,16,16);
            zeldaLinkFrames[4] = zeldaLinkSpriteSheet.crop(69,11,16,16);
            zeldaLinkFrames[5] = zeldaLinkSpriteSheet.crop(86,11,16,16);
            superLinkFrames[0] = zeldaLinkSpriteSheet1.crop(1,11,16,16);
            superLinkFrames[1] = zeldaLinkSpriteSheet1.crop(18,11,16,16);
            superLinkFrames[2] = zeldaLinkSpriteSheet1.crop(35,11,16,16);
            superLinkFrames[3] = zeldaLinkSpriteSheet1.crop(52,11,16,16);
            superLinkFrames[4] = zeldaLinkSpriteSheet1.crop(69,11,16,16);
            superLinkFrames[5] = zeldaLinkSpriteSheet1.crop(86,11,16,16);
            linkHurtFrames[0] = zeldaLinkSpriteSheet.crop(92,224,16,16);
            linkHurtFrames[1] = zeldaLinkSpriteSheet.crop(75,224,16,16);
            linkHurtFrames[2] = zeldaLinkSpriteSheet.crop(58,224,16,16);
            
            woodenSwordAttackFrames[0] = zeldaLinkSpriteSheet.crop(1,47,16,16); //attackDownStart
            woodenSwordAttackFrames[1] = zeldaLinkSpriteSheet.crop(18,47,16,28); 
            woodenSwordAttackFrames[2] = zeldaLinkSpriteSheet.crop(35,47,16,23);
            woodenSwordAttackFrames[3] = zeldaLinkSpriteSheet.crop(52,47,16,19); //downAnimEnd
            woodenSwordAttackFrames[4] = zeldaLinkSpriteSheet.crop(1,77,16,16);
            woodenSwordAttackFrames[5] = zeldaLinkSpriteSheet.crop(18,77,27,17); 
            woodenSwordAttackFrames[6] = zeldaLinkSpriteSheet.crop(46,77,23,17);
            woodenSwordAttackFrames[7] = zeldaLinkSpriteSheet.crop(70,77,19,17); //sideAnimEnd
            woodenSwordAttackFrames[8] = zeldaLinkSpriteSheet.crop(1,97,16,28);
            woodenSwordAttackFrames[9] = zeldaLinkSpriteSheet.crop(18,97,16,28); //attackUpEnd
            woodenSwordAttackFrames[10] = zeldaLinkSpriteSheet.crop(35,97,16,28);
            woodenSwordAttackFrames[11] = zeldaLinkSpriteSheet.crop(52,97,16,28); //upAnimEnd
            woodenSwordAttackFrames[12] = zeldaLinkSpriteSheet.crop(1,128,16,23);//unknown 
            woodenSwordAttackFrames[13] = zeldaLinkSpriteSheet.crop(18,128,16,19); //unknown 
            woodenSwordAttackFrames[14] = zeldaLinkSpriteSheet.crop(35,128,23,17);//unknown 
            woodenSwordAttackFrames[15] = zeldaLinkSpriteSheet.crop(59,128,19,17);//unknown
            
            superSwordAttackFrames[0] = zeldaLinkSpriteSheet1.crop(95,47,16,16); // attackDownStart
            superSwordAttackFrames[1] = zeldaLinkSpriteSheet1.crop(111,47,16,28); 
            superSwordAttackFrames[2] = zeldaLinkSpriteSheet1.crop(128,47,16,23);
            superSwordAttackFrames[3] = zeldaLinkSpriteSheet1.crop(146,47,16,19); //downAnimEnd
            superSwordAttackFrames[4] = zeldaLinkSpriteSheet1.crop(95,77,16,16);
            superSwordAttackFrames[5] = zeldaLinkSpriteSheet1.crop(111,77,27,17); 
            superSwordAttackFrames[6] = zeldaLinkSpriteSheet1.crop(139,77,23,17);
            superSwordAttackFrames[7] = zeldaLinkSpriteSheet1.crop(163,77,19,17); //sideAnimEnd
            superSwordAttackFrames[8] = zeldaLinkSpriteSheet1.crop(95,109,16,16);
            superSwordAttackFrames[9] = zeldaLinkSpriteSheet1.crop(111,98,16,28); 
            superSwordAttackFrames[10] = zeldaLinkSpriteSheet1.crop(128,98,16,27);
            superSwordAttackFrames[11] = zeldaLinkSpriteSheet1.crop(145,108,16,19); //upAnimEnd
            superSwordAttackFrames[12] = zeldaLinkSpriteSheet1.crop(93,128,16,23);//unknown 
            superSwordAttackFrames[13] = zeldaLinkSpriteSheet1.crop(111,128,16,19); //unknown 
            superSwordAttackFrames[14] = zeldaLinkSpriteSheet1.crop(128,128,23,17);//unknown 
            superSwordAttackFrames[15] = zeldaLinkSpriteSheet1.crop(152,128,19,17);//unknown 
            
            superRodAttackFrames[0] = zeldaLinkSpriteSheet1.crop(95,47,16,16); // attackDownStart
            superRodAttackFrames[1] = zeldaLinkSpriteSheet1.crop(20,47,16,28); 
            superRodAttackFrames[2] = zeldaLinkSpriteSheet1.crop(38,47,16,23);
            superRodAttackFrames[3] = zeldaLinkSpriteSheet1.crop(56,47,16,19); //downAnimEnd
            superRodAttackFrames[4] = zeldaLinkSpriteSheet1.crop(3,77,16,17);
            superRodAttackFrames[5] = zeldaLinkSpriteSheet1.crop(21,77,27,17); 
            superRodAttackFrames[6] = zeldaLinkSpriteSheet1.crop(50,77,23,17);
            superRodAttackFrames[7] = zeldaLinkSpriteSheet1.crop(72,77,19,17); //sideAnimEnd
            superRodAttackFrames[8] = zeldaLinkSpriteSheet1.crop(95,109,16,16);
            superRodAttackFrames[9] = zeldaLinkSpriteSheet1.crop(21,96,16,28); 
            superRodAttackFrames[10] = zeldaLinkSpriteSheet1.crop(40,97,16,27);
            superRodAttackFrames[11] = zeldaLinkSpriteSheet1.crop(57,105,16,19); //upAnimEnd
            superRodAttackFrames[12] = zeldaLinkSpriteSheet1.crop(93,128,16,23);//unknown 
            superRodAttackFrames[13] = zeldaLinkSpriteSheet1.crop(111,128,16,19); //unknown 
            superRodAttackFrames[14] = zeldaLinkSpriteSheet1.crop(128,128,23,17);//unknown 
            superRodAttackFrames[15] = zeldaLinkSpriteSheet1.crop(152,128,19,17);//unknown 

            
            linkRaftFrames[0] = zeldaLinkSpriteSheet1.crop(113,205,14,16);//raft object 
            linkRaftFrames[1] = zeldaLinkSpriteSheet1.crop(132,205,15,20);//link on raft object start
            linkRaftFrames[2] = zeldaLinkSpriteSheet1.crop(150,205,14,20);//link on raft object 
            linkRaftFrames[3] = zeldaLinkSpriteSheet1.crop(166,205,15,20);//link on raft object
            linkRaftFrames[4] = zeldaLinkSpriteSheet1.crop(183,206,14,19);//link on raft object 
            linkRaftFrames[5] = zeldaLinkSpriteSheet1.crop(201,205,14,20);//link on raft object 
            linkRaftFrames[6] = zeldaLinkSpriteSheet1.crop(218,205,14,20);//link on raft object end
            linkRaftFrames[7] = zeldaLinkSpriteSheet1.crop(132,229,15,25);//superlink on raft object start
            linkRaftFrames[8] = zeldaLinkSpriteSheet1.crop(150,230,14,24);//superlink on raft object
            linkRaftFrames[9] = zeldaLinkSpriteSheet1.crop(166,228,15,26);//superlink on raft object
            linkRaftFrames[10] = zeldaLinkSpriteSheet1.crop(183,229,15,25);//superlink on raft object
            linkRaftFrames[11] = zeldaLinkSpriteSheet1.crop(201,234,14,20);//superlink on raft object
            linkRaftFrames[12] = zeldaLinkSpriteSheet1.crop(201,234,14,20);//superlink on raft object end 
            
            superRingFrames[0] = zeldaLinkSpriteSheet1.crop(175,29,7,9);//superRing object 
            superRingFrames[1] = zeldaLinkSpriteSheet1.crop(170,40,17,24);//linkmoving superRing
            superRingFrames[2] = zeldaLinkSpriteSheet1.crop(190,42,17,24);//linkmoving superRing
            superRingFrames[3] = zeldaLinkSpriteSheet1.crop(207,41,17,23);//linkmoving superRing
            superRingFrames[4] = zeldaLinkSpriteSheet1.crop(225,41,17,23);//linkmoving superRing
            superRingFrames[5] = zeldaLinkSpriteSheet1.crop(244,47,16,17);//linkmoving superRing
            superRingFrames[6] = zeldaLinkSpriteSheet1.crop(261,47,16,17);//linkmoving superRing

            
            whiteSwordAttackFrames[0] = zeldaLinkSpriteSheet.crop(95,47,16,16); // attackDownStart
            whiteSwordAttackFrames[1] = zeldaLinkSpriteSheet.crop(111,47,16,28); 
            whiteSwordAttackFrames[2] = zeldaLinkSpriteSheet.crop(128,47,16,23);
            whiteSwordAttackFrames[3] = zeldaLinkSpriteSheet.crop(146,47,16,19); //downAnimEnd
            whiteSwordAttackFrames[4] = zeldaLinkSpriteSheet.crop(95,77,16,16);
            whiteSwordAttackFrames[5] = zeldaLinkSpriteSheet.crop(111,77,27,17); 
            whiteSwordAttackFrames[6] = zeldaLinkSpriteSheet.crop(139,77,23,17);
            whiteSwordAttackFrames[7] = zeldaLinkSpriteSheet.crop(163,77,19,17); //sideAnimEnd
            whiteSwordAttackFrames[8] = zeldaLinkSpriteSheet.crop(95,109,16,16);
            whiteSwordAttackFrames[9] = zeldaLinkSpriteSheet.crop(111,98,16,28); 
            whiteSwordAttackFrames[10] = zeldaLinkSpriteSheet.crop(128,98,16,27);
            whiteSwordAttackFrames[11] = zeldaLinkSpriteSheet.crop(145,108,16,19); //upAnimEnd
            whiteSwordAttackFrames[12] = zeldaLinkSpriteSheet.crop(93,128,16,23);//unknown 
            whiteSwordAttackFrames[13] = zeldaLinkSpriteSheet.crop(111,128,16,19); //unknown 
            whiteSwordAttackFrames[14] = zeldaLinkSpriteSheet.crop(128,128,23,17);//unknown 
            whiteSwordAttackFrames[15] = zeldaLinkSpriteSheet.crop(152,128,19,17);//unknown 
            
            magicalSwordAttackFrames[0] = zeldaLinkSpriteSheet.crop(1,47,16,16); // attackDownStart
            magicalSwordAttackFrames[1] = zeldaLinkSpriteSheet.crop(203,47,16,28); 
            magicalSwordAttackFrames[2] = zeldaLinkSpriteSheet.crop(221,47,16,23);
            magicalSwordAttackFrames[3] = zeldaLinkSpriteSheet.crop(238,47,16,19); //downAnimEnd
            magicalSwordAttackFrames[4] = zeldaLinkSpriteSheet.crop(187,77,16,16);
            magicalSwordAttackFrames[5] = zeldaLinkSpriteSheet.crop(204,77,27,17); 
            magicalSwordAttackFrames[6] = zeldaLinkSpriteSheet.crop(232,77,23,17);
            magicalSwordAttackFrames[7] = zeldaLinkSpriteSheet.crop(256,77,19,18); //sideAnimEnd
            magicalSwordAttackFrames[8] = zeldaLinkSpriteSheet.crop(187,109,16,16);
            magicalSwordAttackFrames[9] = zeldaLinkSpriteSheet.crop(204,97,16,28); 
            magicalSwordAttackFrames[10] = zeldaLinkSpriteSheet.crop(221,98,16,27);
            magicalSwordAttackFrames[11] = zeldaLinkSpriteSheet.crop(238,109,16,19); //upAnimEnd
            magicalSwordAttackFrames[12] = zeldaLinkSpriteSheet.crop(93,128,16,23);//unknown 
            magicalSwordAttackFrames[13] = zeldaLinkSpriteSheet.crop(111,128,16,19); //unknown 
            magicalSwordAttackFrames[14] = zeldaLinkSpriteSheet.crop(128,128,23,17);//unknown 
            magicalSwordAttackFrames[15] = zeldaLinkSpriteSheet.crop(152,128,19,17);//unknown
            
            magicalRodAttackFrames[0] = zeldaLinkSpriteSheet.crop(1,47,16,16); // attackDownStart
            magicalRodAttackFrames[1] = zeldaLinkSpriteSheet.crop(296,47,16,28); 
            magicalRodAttackFrames[2] = zeldaLinkSpriteSheet.crop(314,47,16,23);
            magicalRodAttackFrames[3] = zeldaLinkSpriteSheet.crop(330,47,16,19); //downAnimEnd
            magicalRodAttackFrames[4] = zeldaLinkSpriteSheet.crop(1,77,16,16);
            magicalRodAttackFrames[5] = zeldaLinkSpriteSheet.crop(297,77,27,17); 
            magicalRodAttackFrames[6] = zeldaLinkSpriteSheet.crop(325,77,23,17);
            magicalRodAttackFrames[7] = zeldaLinkSpriteSheet.crop(348,77,19,17); //sideAnimEnd
            magicalRodAttackFrames[8] = zeldaLinkSpriteSheet.crop(95,109,16,16);
            magicalRodAttackFrames[9] = zeldaLinkSpriteSheet.crop(298,98,16,28); 
            magicalRodAttackFrames[10] = zeldaLinkSpriteSheet.crop(314,98,16,27);
            magicalRodAttackFrames[11] = zeldaLinkSpriteSheet.crop(331,108,16,19); //upAnimEnd
            magicalRodAttackFrames[12] = zeldaLinkSpriteSheet.crop(93,128,16,23);//unknown 
            magicalRodAttackFrames[13] = zeldaLinkSpriteSheet.crop(111,128,16,19); //unknown 
            magicalRodAttackFrames[14] = zeldaLinkSpriteSheet.crop(128,128,23,17);//unknown 
            magicalRodAttackFrames[15] = zeldaLinkSpriteSheet.crop(152,128,19,17);//unknown
            
            vaporWaveUp[0] = zeldaLinkSpriteSheet1.crop(258,154,16,16); // attackDownStart
            vaporWaveUp[1] = zeldaLinkSpriteSheet1.crop(258,176,16,16); 
            vaporWaveUp[2] = zeldaLinkSpriteSheet1.crop(258,196,16,16);
            vaporWaveUp[3] = zeldaLinkSpriteSheet1.crop(258,219,16,16); //downAnimEnd
            
            vaporWaveDown[0] = zeldaLinkSpriteSheet1.crop(280,154,16,16); // attackDownStart
            vaporWaveDown[1] = zeldaLinkSpriteSheet1.crop(280,176,16,16); 
            vaporWaveDown[2] = zeldaLinkSpriteSheet1.crop(280,196,16,16);
            vaporWaveDown[3] = zeldaLinkSpriteSheet1.crop(280,219,16,16); //downAnimEnd
            
            vaporWaveSide[0] = zeldaLinkSpriteSheet1.crop(260,240,16,16); // attackDownStart
            vaporWaveSide[1] = zeldaLinkSpriteSheet1.crop(283,240,16,16); 
            vaporWaveSide[2] = zeldaLinkSpriteSheet1.crop(303,240,16,16);
            vaporWaveSide[3] = zeldaLinkSpriteSheet1.crop(325,240,16,16); //downAnimEnd
            
            vaporWaveSideL[0] = zeldaLinkSpriteSheet1.crop(260,257,16,16); // attackDownStart
            vaporWaveSideL[1] = zeldaLinkSpriteSheet1.crop(283,257,16,16); 
            vaporWaveSideL[2] = zeldaLinkSpriteSheet1.crop(303,257,16,16);
            vaporWaveSideL[3] = zeldaLinkSpriteSheet1.crop(325,257,16,16); //downAnimEnd
            
            
            rodWaveUp[0] = zeldaLinkSpriteSheet.crop(222,154,16,16); // attackDownStart
            rodWaveUp[1] = zeldaLinkSpriteSheet.crop(205,154,16,16); 

            
            rodWaveDown[0] = zeldaLinkSpriteSheet1.crop(234,176,16,16); // attackDownStart
            rodWaveDown[1] = zeldaLinkSpriteSheet1.crop(205,154,16,16); 

            rodWaveSide[0] = Images.flipHorizontal(zeldaLinkSpriteSheet.crop(290,156,16,16)); // attackDownStart
            rodWaveSide[1] = Images.flipHorizontal(zeldaLinkSpriteSheet.crop(273,156,16,16)); 

            
            rodWaveSideL[0] = zeldaLinkSpriteSheet.crop(290,156,16,16); // attackDownStart
            rodWaveSideL[1] = zeldaLinkSpriteSheet.crop(273,156,16,16); 
            
            bombExplosionsFrames[0] = zeldaLinkSpriteSheet.crop(139,186,14,15); 
            bombExplosionsFrames[1] = zeldaLinkSpriteSheet.crop(139,186,14,15); 
            bombExplosionsFrames[2] = zeldaLinkSpriteSheet.crop(172,186,14,15); 

            npcImage = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/npc1.png"));
            npcSpriteSheet = new SpriteSheet( npcImage);
            npc[0] = npcSpriteSheet.crop(18, 11, 16, 16); // oldman 
            npc[1] = npcSpriteSheet.crop(52, 11, 16, 16); // fire
            npc[2] = npcSpriteSheet.crop(35, 11, 16, 16); // old lady
            npc[3] = npcSpriteSheet.crop(109, 11, 16, 16); // merchant1
            
            fireFrame[0] = npcSpriteSheet.crop(52, 11, 16, 16); // fire
            fireFrame[1] = Images.flipHorizontal(npcSpriteSheet.crop(52, 11, 16, 16)); // fire

            menuSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/menus2pause.png")));
            lifeImage=menuSpriteSheet.crop(442,27,46,7); 
            attackSlots[0]=menuSpriteSheet.crop(405,30,18,26); //enter attack slot
            attackSlots[1]=menuSpriteSheet.crop(381,30,18,26); //shift attack slot

            itemImage = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/items.png"));
            itemSpriteSheet = new SpriteSheet( itemImage);
            npc[4] = itemSpriteSheet.crop(111, 0, 7, 16); //swordUp
            rupees[0] = itemSpriteSheet.crop(72, 0, 8, 16);
            rupees[1] = itemSpriteSheet.crop(72, 16, 8, 16);
            //woodenLaser[0] = zeldaLinkSpriteSheet.crop(1,154,8,16); //swordDown
            //woodenLaser[1] = zeldaLinkSpriteSheet.crop(1,154,8,16); //sword
            //WoodenLaser[2] = zeldaLinkSpriteSheet.crop(1,154,8,16); //sword

            linkHearts[0] = itemSpriteSheet.crop(0, 0, 8, 8); // fullHeart 
            linkHearts[1] = itemSpriteSheet.crop(8,0,8,8); // halfHeart
            itemHeart[0] = itemSpriteSheet.crop(0,0,7,8); // heart itemFrame1
            itemHeart[1] = itemSpriteSheet.crop(0,10,7,8); // heart itemFrame2
            itemHeart2[0] = itemSpriteSheet.crop(0,21,7,8); // heart2 itemFrame1
            itemHeart2[1] = itemSpriteSheet.crop(0,30,7,8); // heart2 itemFrame2
            lifePotion[0] = itemSpriteSheet.crop(83,0,8,16); // potion itemFrame1
            lifePotion[1] = itemSpriteSheet.crop(83,18,8,16); // potion itemFrame2
            lifePotion2[0] = itemSpriteSheet.crop(83,39,8,16); // potion itemFrame1
            lifePotion2[1] = itemSpriteSheet.crop(83,57,8,16); // potion itemFrame2
            linkArrows[0]= itemSpriteSheet.crop(161, 0, 5, 16);
            linkArrows[1]= itemSpriteSheet.crop(16,72, 5, 16);
            linkArrowsProjectileUp[0]= itemSpriteSheet.crop(161, 0, 5, 16);
            linkArrowsProjectileUp[1]= itemSpriteSheet.crop(161, 0, 5, 16);
            linkArrowsProjectileSide[0]= zeldaLinkSpriteSheet1.crop(11,190,16,5);
            linkArrowsProjectileSide[1]= zeldaLinkSpriteSheet1.crop(11,190,16,5);
            linkArrowsProjectileSideL[0]= Images.flipHorizontal(zeldaLinkSpriteSheet1.crop(11,190,16,5));
            linkArrowsProjectileSideL[1]= Images.flipHorizontal(zeldaLinkSpriteSheet1.crop(11,190,16,5));
            linkArrowsProjectileDown[0]= zeldaLinkSpriteSheet1.crop(16,199, 5, 16);
            linkArrowsProjectileDown[1]= zeldaLinkSpriteSheet1.crop(16,199, 5, 16);



            bombItem[0]= itemSpriteSheet.crop(143, 0, 8, 14);
            bombItem[1]= itemSpriteSheet.crop(16,72, 5, 16);
            foodItem[0]= itemSpriteSheet.crop(103, 0, 8, 16);
            foodItem[1]= itemSpriteSheet.crop(16,72, 5, 16);
            

            
            
            otherWeapons[0] = zeldaLinkSpriteSheet.crop(36,154,8,16); // white
            otherWeapons[1] = zeldaLinkSpriteSheet.crop(71,154,8,16); //magical
            otherWeapons[2] = zeldaLinkSpriteSheet.crop(145,154,8,16); //rod 
            otherWeapons[3] = zeldaLinkSpriteSheet1.crop(36,154,8,16); //superSword 
            otherWeapons[4] = zeldaLinkSpriteSheet.crop(45,159,16,8); // white - side
            otherWeapons[5] = zeldaLinkSpriteSheet.crop(80,159,16,8); //magical -side
            otherWeapons[6] = zeldaLinkSpriteSheet.crop(290,154,16,16); //rod - side
            otherWeapons[7] = zeldaLinkSpriteSheet1.crop(45,159,16,8); //superSword - side 
            otherWeapons[8] = zeldaLinkSpriteSheet1.crop(57,168,8,16); // white - down
            otherWeapons[9] = zeldaLinkSpriteSheet1.crop(87,170,8,16); //magical -down
            otherWeapons[10] = zeldaLinkSpriteSheet1.crop(234,176,16,16); //rod - down
            otherWeapons[11] = zeldaLinkSpriteSheet1.crop(48,168,8,16); //superSword - down 
            otherWeapons[12] = zeldaLinkSpriteSheet1.crop(222,154,16,16); //rod - Up
            otherWeapons[13] = zeldaLinkSpriteSheet1.crop(239,154,16,16); //superWave - Up
            otherWeapons[14] = zeldaLinkSpriteSheet1.crop(258,154,16,16); //superWave - down
            otherWeapons[15] = zeldaLinkSpriteSheet1.crop(277,154,16,16); //superWave - side
            otherWeapons[16] = itemSpriteSheet.crop(151, 0, 8, 16); //BOW ITEM
            otherWeapons[17] = itemSpriteSheet.crop(167, 0, 8, 16); //RED CANDLE ITEM        
            otherWeapons[18] = itemSpriteSheet.crop(239, 0, 8, 16); //BOOK OF MAGIC ITEM
            otherWeapons[19] = itemSpriteSheet.crop(119, 0, 8, 16); //MAGICAL SWORD
            otherWeapons[20] = itemSpriteSheet.crop(120,16, 8, 16); //SUPERSWORD ITEM
            otherWeapons[21] = zeldaLinkSpriteSheet1.crop(140,167,5,16); //superRod - UP
           
            
            recursiveTileset[0] = zeldaLinkSpriteSheet1.crop(176,105,16,16); //upTILE
            recursiveTileset[1] = zeldaLinkSpriteSheet1.crop(196,105,16,16); //rightsideTILE
            recursiveTileset[2] = Images.flipHorizontal(zeldaLinkSpriteSheet1.crop(196,105,16,16)); //leftsideTILE
            recursiveTileset[3] = zeldaLinkSpriteSheet1.crop(237,105,16,16); //leftsideTILE











    
            innSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/inn.png")));
            inn[0]= innSpriteSheet.crop(2,4,320,125);

            zeldaWorldLayoutTileImage = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/layout.png"));
            zeldaWorldLayoutTileSpriteSheet = new SpriteSheet( createImageTransparent(zeldaWorldLayoutTileImage,"layout_0,128,0_green",new Color(0,128,0).getRGB()));
            zeldaWorldLayoutTiles.add(zeldaWorldLayoutTileSpriteSheet.crop(1,154,152,84));
            zeldaWorldLayoutTiles.add(createImage(zeldaWorldLayoutTiles.get(0),"forest_brown4greeen",brown.getRGB(),new Color(0,168,0).getRGB()));
            zeldaWorldLayoutTiles.add(createImage(zeldaWorldLayoutTiles.get(0),"cave_brown4greeen",brown.getRGB(),new Color(124,8,0).getRGB()));
            zeldaWorldLayoutTiles.add(createImage(zeldaWorldLayoutTiles.get(0),"grave_brown4greeen",brown.getRGB(),new Color(252,252,252).getRGB()));


            EnemyOverwoldImage = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/enemy3.png"));
            EnemyOverwoldImage = createImageTransparent(EnemyOverwoldImage,"enemies_overworld_116,116,116_green",new Color(116,116,116).getRGB());
            EnemyOverwoldSpriteSheet = new SpriteSheet( createImageTransparent(EnemyOverwoldImage,"enemies_overworld_0,128,0_green",new Color(0,128,0).getRGB()));
            EnemyOverwoldSpriteSheet1 = new SpriteSheet( ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/enemy3_1.png")));
            
            majoraProyectileUp[0]=zeldaLinkSpriteSheet1.crop(36,154,8,16); //superSword 
            majoraProyectileUp[1]=EnemyOverwoldSpriteSheet.crop(100,93,7,16);
            majoraProyectileSide[0]=zeldaLinkSpriteSheet1.crop(45,159,16,8); //superSword - side 
            majoraProyectileSide[1]=EnemyOverwoldSpriteSheet.crop(107,115,16,7);
            majoraProyectileSideL[0]=Images.flipHorizontal(zeldaLinkSpriteSheet1.crop(45,159,16,8)); //superSword - side ;
            majoraProyectileSideL[1]=Images.flipHorizontal(EnemyOverwoldSpriteSheet.crop(107,115,16,7));
            majoraProyectileDown[0]=zeldaLinkSpriteSheet1.crop(48,168,8,16); //superSword - down 
            majoraProyectileDown[1]=EnemyOverwoldSpriteSheet1.crop(127,93,7,16);
            
            
            
            
            whiteProyectileUp[0]=zeldaLinkSpriteSheet.crop(36,154,8,16);
            whiteProyectileUp[1]=EnemyOverwoldSpriteSheet.crop(100,93,7,16);
            whiteProyectileSide[0]=zeldaLinkSpriteSheet.crop(45,159,16,8);
            whiteProyectileSide[1]=EnemyOverwoldSpriteSheet.crop(107,115,16,7);
            whiteProyectileSideL[0]=Images.flipHorizontal(zeldaLinkSpriteSheet.crop(45,159,16,8));
            whiteProyectileSideL[1]=Images.flipHorizontal(EnemyOverwoldSpriteSheet.crop(107,115,16,7));
            whiteProyectileDown[0]=EnemyOverwoldSpriteSheet1.crop(135,93,8,16);
            whiteProyectileDown[1]=EnemyOverwoldSpriteSheet1.crop(127,93,7,16);

            magicalProyectileUp[0]=zeldaLinkSpriteSheet.crop(71,154,8,16);
            magicalProyectileUp[1]=EnemyOverwoldSpriteSheet.crop(100,93,7,16);
            magicalProyectileSide[0]=zeldaLinkSpriteSheet.crop(80,159,16,8);
            magicalProyectileSide[1]=EnemyOverwoldSpriteSheet.crop(107,115,16,7);
            magicalProyectileSideL[0]=Images.flipHorizontal(zeldaLinkSpriteSheet.crop(80,159,16,8));
            magicalProyectileSideL[1]=Images.flipHorizontal(EnemyOverwoldSpriteSheet.crop(107,115,16,7));
            magicalProyectileDown[0]=EnemyOverwoldSpriteSheet1.crop(144,93,8,16);
            magicalProyectileDown[1]=EnemyOverwoldSpriteSheet1.crop(127,93,7,16);
            
            bouncyEnemyFrames[0] = EnemyOverwoldSpriteSheet.crop(162,90,16,16);
            bouncyEnemyFrames[1] = EnemyOverwoldSpriteSheet.crop(179,90,16,16);
            octorokEnemyFrames[0] = EnemyOverwoldSpriteSheet.crop(1,11,16,16);
            octorokEnemyFrames[1] = EnemyOverwoldSpriteSheet.crop(18,11,16,16);
            octorokEnemyFrames[2] = EnemyOverwoldSpriteSheet.crop(35,11,17,16);
            octorokEnemyFrames[3] = EnemyOverwoldSpriteSheet.crop(52,11,17,16);
            otherOctoFrames[0] = EnemyOverwoldSpriteSheet.crop(1,28,16,16);
            otherOctoFrames[1] = EnemyOverwoldSpriteSheet.crop(18,28,16,16);
            otherOctoFrames[2] = EnemyOverwoldSpriteSheet.crop(35,28,17,16);
            otherOctoFrames[3] = EnemyOverwoldSpriteSheet.crop(52,28,17,16);
            octoBall= EnemyOverwoldSpriteSheet.crop(69,11,8,16);
            moblinEnemyFrames[0] = EnemyOverwoldSpriteSheet.crop(82,11,16,16);
            moblinEnemyFrames[1] = EnemyOverwoldSpriteSheet.crop(99,11,16,16);
            moblinEnemyFrames[2] = EnemyOverwoldSpriteSheet.crop(116,11,16,16);
            moblinEnemyFrames[3] = EnemyOverwoldSpriteSheet.crop(133,11,16,16);
            moblinEnemyFrames[4] = Images.flipHorizontal(EnemyOverwoldSpriteSheet.crop(116,11,16,16));
            moblinEnemyFrames[5] = Images.flipHorizontal(EnemyOverwoldSpriteSheet.crop(133,11,16,16));
            moblinArrow[0] = EnemyOverwoldSpriteSheet.crop(150,11,8,16); //top
            moblinArrow[1] = EnemyOverwoldSpriteSheet.crop(159,11,16,16); //side
            moblinArrow[2] = EnemyOverwoldSpriteSheet.crop(159,11,16,16); //sideL
            moblinArrow[3] = EnemyOverwoldSpriteSheet.crop(176,11,8,16);
            moblinArrow[4] = EnemyOverwoldSpriteSheet1.crop(150,28,8,16); //down

           
            dmoblinEnemyFrames[0] = EnemyOverwoldSpriteSheet.crop(82,28,16,16);
            dmoblinEnemyFrames[1] = EnemyOverwoldSpriteSheet.crop(99,28,16,16);
            dmoblinEnemyFrames[2] = EnemyOverwoldSpriteSheet.crop(116,28,16,16);
            dmoblinEnemyFrames[3] = EnemyOverwoldSpriteSheet.crop(133,28,16,16);
            dmoblinEnemyFrames[4] = Images.flipHorizontal(EnemyOverwoldSpriteSheet.crop(116,28,16,16));
            dmoblinEnemyFrames[5] = Images.flipHorizontal(EnemyOverwoldSpriteSheet.crop(133,28,16,16));
            dmoblinArrow[0] = EnemyOverwoldSpriteSheet1.crop(185,28,8,16); //top
            dmoblinArrow[1] = EnemyOverwoldSpriteSheet1.crop(168,28,16,16); //side
            dmoblinArrow[2] = EnemyOverwoldSpriteSheet1.crop(168,28,16,16); //sideL
            dmoblinArrow[3] = EnemyOverwoldSpriteSheet1.crop(160,28,6,16); //down
            
            octoHurtFrames[0] = EnemyOverwoldSpriteSheet.crop(1,28,16,16);
            octoHurtFrames[1] = itemSpriteSheet.crop(16,72, 5, 16);



            
            leeverEnemyFrames[0] = EnemyOverwoldSpriteSheet.crop(1,59,16,16);
            leeverEnemyFrames[1] = EnemyOverwoldSpriteSheet.crop(18,59,16,16);
            leeverEnemyFrames[2] = EnemyOverwoldSpriteSheet.crop(36,59,16,16);
            leeverEnemyFrames[3] = EnemyOverwoldSpriteSheet.crop(52,59,16,16);
            zoraEnemyFrames[0] = EnemyOverwoldSpriteSheet.crop(189,11,16,16);
            zoraEnemyFrames[1] = EnemyOverwoldSpriteSheet.crop(206,11,16,16);
            zoraEnemyFrames[2] = EnemyOverwoldSpriteSheet.crop(224,11,16,16);
            zoraEnemyFrames[3] = EnemyOverwoldSpriteSheet.crop(240,11,16,16);
            lynelEnemyFrames[0] = EnemyOverwoldSpriteSheet.crop(90,59,16,16);
            lynelEnemyFrames[1] = EnemyOverwoldSpriteSheet.crop(107,59,16,16);
            lynelEnemyFrames[2] = EnemyOverwoldSpriteSheet.crop(124,59,16,16);
            lynelEnemyFrames[3] = EnemyOverwoldSpriteSheet.crop(142,59,16,16);
            dlynelEnemyFrames[0] = EnemyOverwoldSpriteSheet.crop(90,76,16,16);
            dlynelEnemyFrames[1] = EnemyOverwoldSpriteSheet.crop(107,76,16,16);
            dlynelEnemyFrames[2] = EnemyOverwoldSpriteSheet.crop(124,76,16,16);
            dlynelEnemyFrames[3] = EnemyOverwoldSpriteSheet.crop(142,76,16,16);

            itemPickUpFrames[0] = zeldaLinkSpriteSheet.crop(213,11,16,16);
            itemPickUpFrames[1] = zeldaLinkSpriteSheet.crop(230,11,16,16);
            
            thunderBirdSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Zelda/thunderbird.png")));
            thunderBirdFrames[0] = thunderBirdSpriteSheet.crop(29,27,67,65);
            thunderBirdFrames[1] = thunderBirdSpriteSheet.crop(119,26,77,67);
            thunderBirdFrames[2] = thunderBirdSpriteSheet.crop(213,16,79,78);
            thunderBirdFrames[3] = thunderBirdSpriteSheet.crop(302,25,91,68);
            thunderBirdFrames[4] = thunderBirdSpriteSheet.crop(399,26,87,66);
            thunderBirdFrames[5] = thunderBirdSpriteSheet.crop(504,27,67,73);
            thunderBirdFrames[6] = thunderBirdSpriteSheet.crop(614,27,67,65);
            thunderBirdFrames[7] = thunderBirdSpriteSheet.crop(704,26,77,67);
            thunderBirdFrames[8] = thunderBirdSpriteSheet.crop(798,16,79,78);
            thunderBirdFrames[9] = thunderBirdSpriteSheet.crop(887,25,91,68);
            thunderBirdFrames[10] = thunderBirdSpriteSheet.crop(984,26,87,66);
            thunderBirdFrames[11] = thunderBirdSpriteSheet.crop(1089,27,67,73);
            thunderBirdFrames[12] = thunderBirdSpriteSheet.crop(29,120,67,65);
            thunderBirdFrames[13] = thunderBirdSpriteSheet.crop(119,119,77,67);
            thunderBirdFrames[14] = thunderBirdSpriteSheet.crop(213,109,79,78);
            thunderBirdFrames[15] = thunderBirdSpriteSheet.crop(302,118,91,68);
            thunderBirdFrames[16] = thunderBirdSpriteSheet.crop(399,119,87,66);
            thunderBirdFrames[17] = thunderBirdSpriteSheet.crop(504,120,67,73);
            thunderBirdFrames[18] = thunderBirdSpriteSheet.crop(614,120,67,65);
            thunderBirdFrames[19] = thunderBirdSpriteSheet.crop(704,119,77,67);
            thunderBirdFrames[20] = thunderBirdSpriteSheet.crop(798,109,79,78);
            thunderBirdFrames[21] = thunderBirdSpriteSheet.crop(887,118,91,68);
            thunderBirdFrames[22] = thunderBirdSpriteSheet.crop(984,119,87,66);
            thunderBirdFrames[23] = thunderBirdSpriteSheet.crop(1089,120,67,73);
            thunderBirdFrames[24] = thunderBirdSpriteSheet.crop(29,213,67,65);
            thunderBirdFrames[25] = thunderBirdSpriteSheet.crop(119,212,77,67);
            thunderBirdFrames[26] = thunderBirdSpriteSheet.crop(213,202,79,78);
            thunderBirdFrames[27] = thunderBirdSpriteSheet.crop(213,202,79,78);
            thunderBirdFrames[28] = thunderBirdSpriteSheet.crop(399,212,87,66);
            thunderBirdFrames[29] = thunderBirdSpriteSheet.crop(504,213,67,73);
            thunderBirdFrames[30] = thunderBirdSpriteSheet.crop(614,213,67,65);
            thunderBirdFrames[31] = thunderBirdSpriteSheet.crop(704,212,77,67);
            thunderBirdFrames[32] = thunderBirdSpriteSheet.crop(798,202,79,78);
            thunderBirdFrames[33] = thunderBirdSpriteSheet.crop(887,211,91,68);
            thunderBirdFrames[34] = thunderBirdSpriteSheet.crop(984,212,87,66);
            thunderBirdFrames[35] = thunderBirdSpriteSheet.crop(1089,213,67,73);
            thunderBirdFrames[36] = thunderBirdSpriteSheet.crop(29,306,67,65);
            thunderBirdFrames[37] = thunderBirdSpriteSheet.crop(119,305,77,67);
            thunderBirdFrames[38] = thunderBirdSpriteSheet.crop(213,295,79,78);
            thunderBirdFrames[39] = thunderBirdSpriteSheet.crop(302,304,91,68);
            thunderBirdFrames[40] = thunderBirdSpriteSheet.crop(399,305,87,66);
            thunderBirdFrames[41] = thunderBirdSpriteSheet.crop(504,306,67,73);
            thunderBirdFrames[42] = thunderBirdSpriteSheet.crop(614,306,67,65);
            thunderBirdFrames[43] = thunderBirdSpriteSheet.crop(704,305,77,67);
            thunderBirdFrames[44] = thunderBirdSpriteSheet.crop(798,295,79,78);
            thunderBirdFrames[45] = thunderBirdSpriteSheet.crop(887,304,91,68);
            thunderBirdFrames[46] = thunderBirdSpriteSheet.crop(984,305,87,66);
            thunderBirdFrames[47] = thunderBirdSpriteSheet.crop(1089,306,67,73);
            thunderBirdFrames[48] = thunderBirdSpriteSheet.crop(29,399,67,65);
            thunderBirdFrames[49] = thunderBirdSpriteSheet.crop(119,398,77,67);
            thunderBirdFrames[50] = thunderBirdSpriteSheet.crop(213,388,79,78);
            thunderBirdFrames[51] = thunderBirdSpriteSheet.crop(302,397,91,68);
            thunderBirdFrames[52] = thunderBirdSpriteSheet.crop(399,398,87,66);
            thunderBirdFrames[53] = thunderBirdSpriteSheet.crop(504,399,67,73);
            thunderBirdFrames[54] = thunderBirdSpriteSheet.crop(614,399,67,65);
            thunderBirdFrames[55] = thunderBirdSpriteSheet.crop(704,398,77,67);
            thunderBirdFrames[56] = thunderBirdSpriteSheet.crop(798,388,79,78);
            thunderBirdFrames[57] = thunderBirdSpriteSheet.crop(887,397,91,68);
            thunderBirdFrames[58] = thunderBirdSpriteSheet.crop(984,398,87,66);
            thunderBirdFrames[59] = thunderBirdSpriteSheet.crop(1089,399,67,73);
            
            attackThunderBirdFrames[0] = thunderBirdSpriteSheet.crop(24,504,91,61);
            attackThunderBirdFrames[1] = thunderBirdSpriteSheet.crop(125,493,79,64);
            attackThunderBirdFrames[2] = thunderBirdSpriteSheet.crop(221,510,77,48);
            attackThunderBirdFrames[3] = thunderBirdSpriteSheet.crop(321,513,67,48);
            attackThunderBirdFrames[4] = thunderBirdSpriteSheet.crop(416,515,67,48);
            attackThunderBirdFrames[5] = thunderBirdSpriteSheet.crop(507,515,75,48);
            attackThunderBirdFrames[6] = thunderBirdSpriteSheet.crop(600,515,79,47);
            attackThunderBirdFrames[7] = thunderBirdSpriteSheet.crop(695,516,79,47);
            attackThunderBirdFrames[8] = thunderBirdSpriteSheet.crop(790,494,79,69);
            attackThunderBirdFrames[9] = thunderBirdSpriteSheet.crop(885,493,79,70);
            attackThunderBirdFrames[10] = thunderBirdSpriteSheet.crop(980,492,79,72);
            attackThunderBirdFrames[11] = thunderBirdSpriteSheet.crop(1075,491,79,72);
            attackThunderBirdFrames[12] = thunderBirdSpriteSheet.crop(31,584,77,72);
            attackThunderBirdFrames[13] = thunderBirdSpriteSheet.crop(128,584,73,72);
            attackThunderBirdFrames[14] = thunderBirdSpriteSheet.crop(223,584,73,72);
            
            deathThunderBirdFrames[0] = thunderBirdSpriteSheet.crop(404,596,91,43);
            deathThunderBirdFrames[1] = thunderBirdSpriteSheet.crop(501,599,87,41);
            deathThunderBirdFrames[2] = thunderBirdSpriteSheet.crop(607,602,67,55);
            deathThunderBirdFrames[3] = thunderBirdSpriteSheet.crop(702,602,67,56);



            
            
            //dungeon one tiles
            zeldaTiles.add(zeldaSpriteSheet.crop(815,11,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(848,11,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(881,11,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(914,11,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(947,11,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(848,44,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(815,44,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(881,44,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(914,44,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(947,44,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(815,77,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(848,77,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(881,77,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(914,77,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(947,77,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(815,110,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(848,110,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(881,110,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(914,110,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(947,110,32,32));
            zeldaTiles.add(zeldaSpriteSheet.crop(984,11,16,16));
            zeldaTiles.add(zeldaSpriteSheet.crop(1001,11,16,16));
            zeldaTiles.add(zeldaSpriteSheet.crop(1018,11,16,16));
            zeldaTiles.add(zeldaSpriteSheet.crop(1035,11,16,16));
            zeldaTiles.add(zeldaSpriteSheet.crop(1001,28,16,16));
            zeldaTiles.add(zeldaSpriteSheet.crop(984,28,16,16));
            zeldaTiles.add(zeldaSpriteSheet.crop(1018,28,16,16));
            zeldaTiles.add(zeldaSpriteSheet.crop(1035,28,16,16));
            zeldaTiles.add(zeldaSpriteSheet.crop(984,45,16,16));
            zeldaTiles.add(zeldaSpriteSheet.crop(1001,45,16,16));

            //main world tiles
            SpriteSheet mountain = new SpriteSheet(zeldaWorldLayoutTiles.get(0));
            SpriteSheet forest = new SpriteSheet(zeldaWorldLayoutTiles.get(1));
            SpriteSheet cave = new SpriteSheet(createImageTransparent(zeldaWorldLayoutTiles.get(2),"caveTransparent_252,216,168_crema", new Color(252,216,168).getRGB()));
            SpriteSheet grave = new SpriteSheet(zeldaWorldLayoutTiles.get(3));

            //recursive tiles
            recursiveTiles.add(zeldaLinkSpriteSheet1.crop(176,105,16,16));//up
            recursiveTiles.add(zeldaLinkSpriteSheet1.crop(237,105,16,16)); //down
            recursiveTiles.add(Images.flipHorizontal(zeldaLinkSpriteSheet1.crop(196,105,16,16)));//left
            recursiveTiles.add(zeldaLinkSpriteSheet1.crop(196,105,16,16));//right
            
            mountainTiles.add(mountain.crop(0,0,16,16));
            mountainTiles.add(mountain.crop(17,0,16,16));
            mountainTiles.add(mountain.crop(34,0,16,16));
            mountainTiles.add(mountain.crop(51,0,16,16));
            mountainTiles.add(mountain.crop(17,17,16,16));
            mountainTiles.add(mountain.crop(34,17,16,16));
            mountainTiles.add(mountain.crop(51,17,16,16));
            mountainTiles.add(mountain.crop(68,0,16,16));
            mountainTiles.add(mountain.crop(85,0,16,16));
            mountainTiles.add(mountain.crop(102,0,16,16));
            mountainTiles.add(mountain.crop(68,17,16,16));
            mountainTiles.add(mountain.crop(85,17,16,16));
            mountainTiles.add(mountain.crop(102,17,16,16));
            mountainTiles.add(mountain.crop(68,34,16,16));
            mountainTiles.add(mountain.crop(85,34,16,16));
            mountainTiles.add(mountain.crop(102,34,16,16));
            mountainTiles.add(mountain.crop(119,0,16,16));
            mountainTiles.add(mountain.crop(136,0,16,16));
            mountainTiles.add(mountain.crop(119,17,16,16));
            mountainTiles.add(mountain.crop(136,17,16,16));
            mountainTiles.add(mountain.crop(119,34,16,16));
            mountainTiles.add(mountain.crop(136,34,16,16));
            mountainTiles.add(mountain.crop(0,51,16,16));
            mountainTiles.add(mountain.crop(17,51,16,16));
            mountainTiles.add(mountain.crop(34,51,16,16));
            mountainTiles.add(mountain.crop(0,68,16,16));
            mountainTiles.add(mountain.crop(34,68,16,16));
            mountainTiles.add(mountain.crop(51,51,16,16));
            mountainTiles.add(mountain.crop(68,51,16,16));
            mountainTiles.add(mountain.crop(85,51,16,16));
            mountainTiles.add(mountain.crop(51,68,16,16));
            mountainTiles.add(mountain.crop(85,68,16,16));
            mountainTiles.add(mountain.crop(0,17,16,16));
            mountainTiles.add(mountain.crop(0,34,16,16));
            mountainTiles.add(mountain.crop(17,34,16,16));
            mountainTiles.add(mountain.crop(34,34,16,16));
            mountainTiles.add(mountain.crop(51,34,16,16));
            mountainTiles.add(mountain.crop(17,68,16,16));
            mountainTiles.add(mountain.crop(68,68,16,16));
            mountainTiles.add(mountain.crop(102,51,16,16));
            mountainTiles.add(mountain.crop(119,51,16,16));
            mountainTiles.add(mountain.crop(136,51,16,16));

            forestTiles.add(forest.crop(0,0,16,16));
            forestTiles.add(forest.crop(17,0,16,16));
            forestTiles.add(forest.crop(34,0,16,16));
            forestTiles.add(forest.crop(51,0,16,16));
            forestTiles.add(forest.crop(17,17,16,16));
            forestTiles.add(forest.crop(34,17,16,16));
            forestTiles.add(forest.crop(51,17,16,16));
            forestTiles.add(forest.crop(68,0,16,16));
            forestTiles.add(forest.crop(85,0,16,16));
            forestTiles.add(forest.crop(102,0,16,16));
            forestTiles.add(forest.crop(68,17,16,16));
            forestTiles.add(forest.crop(85,17,16,16));
            forestTiles.add(forest.crop(102,17,16,16));
            forestTiles.add(forest.crop(68,34,16,16));
            forestTiles.add(forest.crop(85,34,16,16));
            forestTiles.add(forest.crop(102,34,16,16));
            forestTiles.add(forest.crop(119,0,16,16));
            forestTiles.add(forest.crop(136,0,16,16));
            forestTiles.add(forest.crop(119,17,16,16));
            forestTiles.add(forest.crop(136,17,16,16));
            forestTiles.add(forest.crop(119,34,16,16));
            forestTiles.add(forest.crop(136,34,16,16));
            forestTiles.add(forest.crop(0,51,16,16));
            forestTiles.add(forest.crop(17,51,16,16));
            forestTiles.add(forest.crop(34,51,16,16));
            forestTiles.add(forest.crop(0,68,16,16));
            forestTiles.add(forest.crop(34,68,16,16));
            forestTiles.add(forest.crop(51,51,16,16));
            forestTiles.add(forest.crop(68,51,16,16));
            forestTiles.add(forest.crop(85,51,16,16));
            forestTiles.add(forest.crop(51,68,16,16));
            forestTiles.add(forest.crop(85,68,16,16));
            forestTiles.add(forest.crop(0,17,16,16));
            forestTiles.add(forest.crop(0,34,16,16));
            forestTiles.add(forest.crop(17,34,16,16));
            forestTiles.add(forest.crop(34,34,16,16));
            forestTiles.add(forest.crop(51,34,16,16));
            forestTiles.add(forest.crop(17,68,16,16));
            forestTiles.add(forest.crop(68,68,16,16));
            forestTiles.add(forest.crop(102,51,16,16));
            forestTiles.add(forest.crop(119,51,16,16));
            forestTiles.add(forest.crop(136,51,16,16));

            caveTiles.add(cave.crop(0,0,16,16));
            caveTiles.add(cave.crop(17,0,16,16));
            caveTiles.add(cave.crop(34,0,16,16));
            caveTiles.add(cave.crop(51,0,16,16));
            caveTiles.add(cave.crop(17,17,16,16));
            caveTiles.add(cave.crop(34,17,16,16));
            caveTiles.add(cave.crop(51,17,16,16));
            caveTiles.add(cave.crop(68,0,16,16));
            caveTiles.add(cave.crop(85,0,16,16));
            caveTiles.add(cave.crop(102,0,16,16));
            caveTiles.add(cave.crop(68,17,16,16));
            caveTiles.add(cave.crop(85,17,16,16));
            caveTiles.add(cave.crop(102,17,16,16));
            caveTiles.add(cave.crop(68,34,16,16));
            caveTiles.add(cave.crop(85,34,16,16));
            caveTiles.add(cave.crop(102,34,16,16));
            caveTiles.add(cave.crop(119,0,16,16));
            caveTiles.add(cave.crop(136,0,16,16));
            caveTiles.add(cave.crop(119,17,16,16));
            caveTiles.add(cave.crop(136,17,16,16));
            caveTiles.add(cave.crop(119,34,16,16));
            caveTiles.add(cave.crop(136,34,16,16));
            caveTiles.add(cave.crop(0,51,16,16));
            caveTiles.add(cave.crop(17,51,16,16));
            caveTiles.add(cave.crop(34,51,16,16));
            caveTiles.add(cave.crop(0,68,16,16));
            caveTiles.add(cave.crop(34,68,16,16));
            caveTiles.add(cave.crop(51,51,16,16));
            caveTiles.add(cave.crop(68,51,16,16));
            caveTiles.add(cave.crop(85,51,16,16));
            caveTiles.add(cave.crop(51,68,16,16));
            caveTiles.add(cave.crop(85,68,16,16));
            caveTiles.add(cave.crop(0,17,16,16));
            caveTiles.add(cave.crop(0,34,16,16));
            caveTiles.add(cave.crop(17,34,16,16));
            caveTiles.add(cave.crop(34,34,16,16));
            caveTiles.add(cave.crop(51,34,16,16));
            caveTiles.add(cave.crop(17,68,16,16));
            caveTiles.add(cave.crop(68,68,16,16));
            caveTiles.add(cave.crop(102,51,16,16));
            caveTiles.add(cave.crop(119,51,16,16));
            caveTiles.add(cave.crop(136,51,16,16));

            graveTiles.add(grave.crop(0,0,16,16));
            graveTiles.add(grave.crop(17,0,16,16));
            graveTiles.add(grave.crop(34,0,16,16));
            graveTiles.add(grave.crop(51,0,16,16));
            graveTiles.add(grave.crop(17,17,16,16));
            graveTiles.add(grave.crop(34,17,16,16));
            graveTiles.add(grave.crop(51,17,16,16));
            graveTiles.add(grave.crop(68,0,16,16));
            graveTiles.add(grave.crop(85,0,16,16));
            graveTiles.add(grave.crop(102,0,16,16));
            graveTiles.add(grave.crop(68,17,16,16));
            graveTiles.add(grave.crop(85,17,16,16));
            graveTiles.add(grave.crop(102,17,16,16));
            graveTiles.add(grave.crop(68,34,16,16));
            graveTiles.add(grave.crop(85,34,16,16));
            graveTiles.add(grave.crop(102,34,16,16));
            graveTiles.add(grave.crop(119,0,16,16));
            graveTiles.add(grave.crop(136,0,16,16));
            graveTiles.add(grave.crop(119,17,16,16));
            graveTiles.add(grave.crop(136,17,16,16));
            graveTiles.add(grave.crop(119,34,16,16));
            graveTiles.add(grave.crop(136,34,16,16));
            graveTiles.add(grave.crop(0,51,16,16));
            graveTiles.add(grave.crop(17,51,16,16));
            graveTiles.add(grave.crop(34,51,16,16));
            graveTiles.add(grave.crop(0,68,16,16));
            graveTiles.add(grave.crop(34,68,16,16));
            graveTiles.add(grave.crop(51,51,16,16));
            graveTiles.add(grave.crop(68,51,16,16));
            graveTiles.add(grave.crop(85,51,16,16));
            graveTiles.add(grave.crop(51,68,16,16));
            graveTiles.add(grave.crop(85,68,16,16));
            graveTiles.add(grave.crop(0,17,16,16));
            graveTiles.add(grave.crop(0,34,16,16));
            graveTiles.add(grave.crop(17,34,16,16));
            graveTiles.add(grave.crop(34,34,16,16));
            graveTiles.add(grave.crop(51,34,16,16));
            graveTiles.add(grave.crop(17,68,16,16));
            graveTiles.add(grave.crop(68,68,16,16));
            graveTiles.add(grave.crop(102,51,16,16));
            graveTiles.add(grave.crop(119,51,16,16));
            graveTiles.add(grave.crop(136,51,16,16));


        }catch (IOException e) {
        e.printStackTrace();
    }


    }
    public BufferedImage invertImage(BufferedImage bufferedImage, String name) {
        String path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
        String path2 = path.substring(0,path.indexOf("/bin/"))+"/res/Edited/"+name+".png";
        File imagess = new File(path2.replaceAll("%20"," "));
        if (imagess.exists()){
            try {
                return ImageIO.read(imagess.getAbsoluteFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                int rgba = bufferedImage.getRGB(x, y);
                Color col = new Color(rgba, true);
                col = new Color(255 - col.getRed(),
                        255 - col.getGreen(),
                        255 - col.getBlue());
                bufferedImage.setRGB(x, y, col.getRGB());
            }
        }
        File f = null;

        try
        {
            path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
            path2 = path.substring(0,path.indexOf("/bin/"))+"/res/Edited/"+name+".png";
            f = new File(path2.replaceAll("%20"," "));
            System.out.println("File saved in: "+path2);
            ImageIO.write(bufferedImage, "png", f);
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }
        return bufferedImage;
    }

    public static Color transparant = new Color(255, 255, 255, 0);
    public static Color brown = new Color(200,76,12);

    public BufferedImage createImageTransparent(BufferedImage image, String name, int RGBToReplace){


        return createImage(image,name,RGBToReplace,transparant.getRGB());
    }

    public BufferedImage createImage(BufferedImage image, String name, int RGBToReplace,int RGBReplaicing){

        int width = image.getWidth();
        int height = image.getHeight();
        String path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
        String path2 = path.substring(0,path.indexOf("/bin/"))+"/res/Edited/"+name+".png";
        File imagess = new File(path2.replaceAll("%20"," "));
        if (imagess.exists()){
            try {
                return ImageIO.read(imagess.getAbsoluteFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        // Create buffered image object
        BufferedImage img = null;

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // file object
        File f = null;

        // create random values pixel by pixel
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                if (image.getRGB(x, y) == RGBToReplace) {
                    img.setRGB(x, y, RGBReplaicing);
                } else {
                    img.setRGB(x, y, image.getRGB(x, y));
                }


            }
        }

        // write image, AKA save it to pc
        try
        {
            path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
            path2 = path.substring(0,path.indexOf("/bin/"))+"/res/Edited/"+name+".png";
            f = new File(path2.replaceAll("%20"," "));
            System.out.println("File saved in: "+path2);
            ImageIO.write(img, "png", f);
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }
        return img;
    }


    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Images.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static BufferedImage tint(BufferedImage src, float r, float g, float b) {

        // Copy image
        BufferedImage newImage = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(src, 0, 0, null);
        graphics.dispose();

        // Color image
        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                int ax = newImage.getColorModel().getAlpha(newImage.getRaster().getDataElements(i, j, null));
                int rx = newImage.getColorModel().getRed(newImage.getRaster().getDataElements(i, j, null));
                int gx = newImage.getColorModel().getGreen(newImage.getRaster().getDataElements(i, j, null));
                int bx = newImage.getColorModel().getBlue(newImage.getRaster().getDataElements(i, j, null));
                rx *= r;
                gx *= g;
                bx *= b;
                newImage.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx << 0));
            }
        }
        return newImage;
    }

    public static BufferedImage flipHorizontal(BufferedImage image){
        // Flip the image horizontally
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);
        return image;
    }

}

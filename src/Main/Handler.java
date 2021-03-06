package Main;

import Display.DisplayScreen;
import Game.GameStates.*;
import Game.GameStates.Zelda.FightingState;
import Game.GameStates.Zelda.GameOverState;
import Game.GameStates.Zelda.ZeldaDungeonState;
import Game.GameStates.Zelda.ZeldaGameState;
import Game.GameStates.Zelda.ZeldaMMGameState;
import Game.GameStates.Zelda.ZeldaIntroStates;
import Game.GameStates.Zelda.ZeldaMapMakerState;
import Game.GameStates.Zelda.ZeldaMerchantState;
import Game.GameStates.Zelda.ZeldaOtherState;
import Game.PacMan.World.Map;
import Game.PacMan.entities.Dynamics.PacMan;
import Game.Zelda.Entities.Dynamic.BouncyFella;
import Game.Zelda.Entities.Dynamic.Link;
import Game.Zelda.Entities.Dynamic.Moblin;
import Game.Zelda.Entities.Dynamic.Octorok;
import Game.Zelda.Entities.Dynamic.Zora;
import Game.Zelda.Entities.Dynamic.swordProjectile;
import Input.KeyManager;
import Input.MouseManager;
import Resources.MusicHandler;
import Resources.ScoreManager;
import Game.Zelda.Entities.Statics.MMMoveTile;
import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.Random;


/**
 * Created by AlexVR on 1/24/2020.
 */

public class Handler {

    private GameSetUp game;
    private DisplayScreen screen;
    private ScoreManager scoreManager;
    private boolean fullScreen = false,mute=false;
    private Clip backgroundMusic;
    private MusicHandler musicHandler;
    private ArrayList<Clip> effects;
    private State lastState;
    public static boolean DEBUG = true;
    private Map map;
    private PacMan pacman;
    private Link link;
    private Octorok octorok;
    private BouncyFella bouncyFella;
    private Moblin moblin;
    private Zora zora;
    private MMMoveTile MMMove;
    private swordProjectile swordProyectile;
    public boolean firstClick = false;
    public boolean secondClick = false;
    

	public Handler(GameSetUp game){
        this.game = game;
    }

    public int getWidth(){
        return getDisplayScreen().getCanvas().getWidth();
    }

    public int getHeight(){

        return getDisplayScreen().getCanvas().getHeight();
    }

    public GameSetUp getGameProperties() {
        return game;
    }

    public void setGameProperties(GameSetUp game) {
        this.game = game;
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }

    public DisplayScreen getDisplayScreen(){return screen;}

    public void setDisplayScreen(DisplayScreen screen){this.screen=screen;}

    public GameState getGameState (){
        return (GameState)getGameProperties().gameState;
    }

    public MenuState getMenuState (){
        return (MenuState) getGameProperties().menuState;
    }

    public PauseState getPauseState (){
        return (PauseState) getGameProperties().pauseState;
    }

    public GalagaState getGalagaState (){
        return (GalagaState)getGameProperties().galagaState;
    }

    public PacManState getPacManState (){
        return (PacManState)getGameProperties().pacmanState;
    }
    
    public ZeldaMMGameState getMMGameState() {
    	return (ZeldaMMGameState)getGameProperties().MMGameState;
    }

    public ZeldaMerchantState getZeldaMerchantState() {
    	return (ZeldaMerchantState)getGameProperties().zeldaMerchantState;
    }
    
    public ZeldaGameState getZeldaGameState (){
        return (ZeldaGameState)getGameProperties().zeldaGameState;
    }
    public ZeldaOtherState getZeldaOtherState (){
        return (ZeldaOtherState)getGameProperties().zeldaOtherState;
    }
    public ZeldaDungeonState getZeldaDungeonState (){
        return (ZeldaDungeonState)getGameProperties().zeldaDungeonState;
    }

    public ZeldaIntroStates getZeldaIntroState (){
        return (ZeldaIntroStates) getGameProperties().zeldaIntroState;
    }

    public ZeldaMapMakerState getZeldaMMState (){
        return (ZeldaMapMakerState)getGameProperties().zeldaMapMakerState;
    }
    public FightingState getFightingState() {
    	return (FightingState)getGameProperties().fightingState;
    }
    
    public GameOverState getGameOverState() {
    	return (GameOverState)getGameProperties().gameOverState;
    }

    public int getRandomFormation(int max, int min) {
		Random rando = new Random();
		int num = rando.nextInt(max-min) + min;
		return num;
	}
    
    public void changeState(State state){
        State.setState(state);
    }

    public State getState(){
        return State.getState();
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    public void setScoreManager(ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    public Clip getBackgroundMusic() {
        return backgroundMusic;
    }

    public void setBackgroundMusic(Clip backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public MusicHandler getMusicHandler() {
        return musicHandler;
    }

    public void setMusicHandler(MusicHandler musicHandler) {
        this.musicHandler = musicHandler;
    }

    public ArrayList<Clip> getEffects() {
        return effects;
    }

    public void setEffects(ArrayList<Clip> effects) {
        this.effects = effects;
    }

    public State getLastState() {
        return lastState;
    }

    public void setLastState(State lastState) {
        this.lastState = lastState;
    }

    public Map getMap() {
        return map;
    }
    public void setMap(Map map){
        this.map=map;
    }

    public PacMan getPacman() {
        return pacman;
    }

    public void setPacman(PacMan pacman) {
        this.pacman = pacman;
    }
    
    public MMMoveTile getMMMove() {
    	return MMMove;
    }
    
    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Octorok getOctorok() {
		return octorok;
	}

	public void setOctorok(Octorok octorok) {
		this.octorok = octorok;
	}

	public BouncyFella getBouncyFella() {
		return bouncyFella;
	}

	public void setBouncyFella(BouncyFella bouncyFella) {
		this.bouncyFella = bouncyFella;
	}

	public Moblin getMoblin() {
		return moblin;
	}

	public void setMoblin(Moblin moblin) {
		this.moblin = moblin;
	}

	public Zora getZora() {
		return zora;
	}

	public void setZora(Zora zora) {
		this.zora = zora;
	}

	public swordProjectile getSwordProyectile() {
		return swordProyectile;
	}

	public void setSwordProyectile(swordProjectile swordProyectile) {
		this.swordProyectile = swordProyectile;
	}

}

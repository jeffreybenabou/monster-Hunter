package GameCore;

import ImageHandel.ImageLoader;

import java.awt.*;

import ImageHandel.SpriteSheet;
import Objects.MainPlayer;
import Server.*;

import javax.swing.*;

public class StaticVariables {

    public static Image
            mainMenuBackGround,
            exitButton,
            startButton,
            loadFromServerButoon,
            loadFromComputerButton;


    public static Image
            lifePanel,
            levelLabel,
            shopIcon,
            settingLabel,
            moneyIcon;

    public static Image
    worldBackGround,
    tree;

    public static SpriteSheet attack1;

    public static GamePanel gamePanel;
    public static World world;
    public static MainMenu mainMenu;
    public static MainClass mainClass;
    public static MainPlayer mainPlayer;

    private static Sql sql;

    public StaticVariables(){
        mainMenuBackGround=ImageLoader.loadImage("Photos/menubackground.jpg");
        startButton=ImageLoader.loadImage("Photos/menuButton/newGame.png");
        exitButton=ImageLoader.loadImage("Photos/menuButton/exit.png");
        loadFromServerButoon=ImageLoader.loadImage("Photos/menuButton/loadOnline.png");
        loadFromComputerButton=ImageLoader.loadImage("Photos/menuButton/load.png");
        lifePanel =ImageLoader.loadImage("Photos/worldFrame/lifeBar1.png");
        levelLabel =ImageLoader.loadImage("Photos/worldFrame/level.png");
        shopIcon=ImageLoader.loadImage("Photos/worldFrame/shop_icon.png");
        settingLabel=ImageLoader.loadImage("Photos/worldFrame/setting_icon.png");
        moneyIcon=ImageLoader.loadImage("Photos/worldFrame/money_icon.png");
        tree=ImageLoader.loadImage("Photos/world/tree.png");

        setTheSpriteSheet();
        new Thread(new Runnable() {
            public void run() {

                new Thread(new Runnable() {
                    public void run() {
                        worldBackGround=ImageLoader.loadImage("Photos/world/background.jpg");
        world.setIcon(new ImageIcon(StaticVariables.worldBackGround.getScaledInstance(10000,10000,0)));

                    }
                }).start();
                mainPlayer=new MainPlayer();
                world=new World();

            }
        }).start();
        gamePanel=new GamePanel();
        mainClass=new MainClass();
        mainMenu=new MainMenu();

//        sql=new Sql();
        MainClass.addTheMainMenu();


    }

    private void setTheSpriteSheet() {
        attack1=new SpriteSheet(ImageLoader.loadImage("Photos/character/attack.png"));
    }

    public static void main(String[] args) {
        new StaticVariables();
    }
}

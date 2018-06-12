package GameCore;

import ImageHandel.ImageLoader;

import java.awt.*;

import ImageHandel.SpriteSheet;
import Objects.Ghost;
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
    tree,
    rock,
    trunk,
    houseNum1,houseNum2,houseNum3;

    public static Image
    ghost1,ghost2,ghost3;

    public static SpriteSheet attack1;

    public static GamePanel gamePanel;
    public static World world;
    public static MainMenu mainMenu;
    public static MainClass mainClass;
    public static MainPlayer mainPlayer;
    public static MiniMap miniMap;

    public static int level=1;

    private static Sql sql;

    public StaticVariables(){
        new Thread(new Runnable() {
            public void run() {
                Ghost.addGhostImage();
            }
        }).start();

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
        trunk=ImageLoader.loadImage("Photos/world/trunk.png");
        houseNum1=ImageLoader.loadImage("Photos/house/house1.png");
        houseNum2=ImageLoader.loadImage("Photos/house/house2.png");
        houseNum3=ImageLoader.loadImage("Photos/house/house3.png");
        rock=ImageLoader.loadImage("Photos/rock.png");







        new Thread(new Runnable() {
                    public void run() {
                        mainPlayer=new MainPlayer();
                        world=new World();
                       /* worldBackGround=ImageLoader.loadImage("Photos/world/background.jpg");
                        world.setIcon(new ImageIcon(worldBackGround.getScaledInstance(10000,10000,0)));
*/


                    }
                }).start();

        mainClass=new MainClass();
        mainMenu=new MainMenu();
        miniMap=new MiniMap();
        gamePanel=new GamePanel();
        gamePanel.add(miniMap);
        MainClass.addTheMainMenu();



//        sql=new Sql();


    }



    public static void main(String[] args) {
        new StaticVariables();
    }
}

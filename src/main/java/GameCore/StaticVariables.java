package GameCore;

import BackgroundObject.Bird;
import BackgroundObject.FootStep;
import ImageHandel.ImageLoader;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import Objects.Ghost;
import Objects.MainPlayer;
import Server.*;

import javax.swing.*;

public class StaticVariables {


    public static Dimension dimension= Toolkit.getDefaultToolkit().getScreenSize();

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
    trunk,
    cloud,
    houseNum1,houseNum2,houseNum3,
    crack,
    key1,key2,key3,
    carpet;


    public static GamePanel gamePanel;
    public static World world;
    public static MainMenu mainMenu;
    public static MainClass mainClass;
    public static MainPlayer mainPlayer;
    public static MiniMap miniMap;



    public static int level=1;

    private static Sql sql;

    public StaticVariables(){
        mainClass=new MainClass();
        mainClass.add(new Opening());
        mainMenuBackGround=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/menu.png");

        new Thread(new Runnable() {
            public void run() {

                key1=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/house/key1.png");
                key2=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/house/key2.png");
                key3=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/house/key3.png");
                carpet=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/debris-channel-slider.png");
                startButton=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/menuButton/newGame.png");
                exitButton=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/menuButton/exit.png");
                loadFromServerButoon=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/menuButton/loadOnline.png");
                loadFromComputerButton=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/menuButton/load.png");

                mainMenu=new MainMenu();
                mainMenu.setVisible(false);
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                worldBackGround=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/world/background.jpg");

            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                Ghost.addGhostImage();
            }
        }).start();








        new Thread(new Runnable() {
            public void run() {
                FootStep.left=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/foot_step/left.png");
                FootStep.right=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/foot_step/right.png");
                FootStep.up=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/foot_step/up.png");
                FootStep. down=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/foot_step/down.png");
                FootStep. leftUp=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/foot_step/left_up.png");
                FootStep. leftDown=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/foot_step/left_down.png");
                FootStep.  rightDown=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/foot_step/down_right.png");
                FootStep.   rightUp=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/foot_step/right_up.png");



                lifePanel =ImageLoader.loadImage("src/main/java/ImageHandel/Photos/worldFrame/lifeBar1.png");
                levelLabel =ImageLoader.loadImage("src/main/java/ImageHandel/Photos/worldFrame/level.png");
                shopIcon=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/worldFrame/shop_icon.png");
                settingLabel=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/worldFrame/setting_icon.png");
                moneyIcon=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/worldFrame/money_icon.png");
                tree=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/world/tree.png");
                trunk=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/world/trunk.png");
                crack=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/crack.png");
                houseNum1=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/house/house1.png");
                houseNum2=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/house/house2.png");
                houseNum3=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/house/house3.png");
                cloud=ImageLoader.loadImage("src/main/java/ImageHandel/Photos/cloud/cloud122.png");
            }
        }).start();











//        sql=new Sql();


    }



    public static void main(String[] args) {
        new StaticVariables();
    }
}

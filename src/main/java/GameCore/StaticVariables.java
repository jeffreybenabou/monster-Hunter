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
    houseNum1,houseNum2,houseNum3;


    public static GamePanel gamePanel;
    public static World world;
    public static MainMenu mainMenu;
    public static MainClass mainClass;
    public static MainPlayer mainPlayer;
    public static MiniMap miniMap;


    public static int level=1;

    private static Sql sql;

    public StaticVariables(){
        mainMenuBackGround=ImageLoader.loadImage("Photos/menu.jpg");

        new Thread(new Runnable() {
            public void run() {


                startButton=ImageLoader.loadImage("Photos/menuButton/newGame.png");
                exitButton=ImageLoader.loadImage("Photos/menuButton/exit.png");
                loadFromServerButoon=ImageLoader.loadImage("Photos/menuButton/loadOnline.png");
                loadFromComputerButton=ImageLoader.loadImage("Photos/menuButton/load.png");
                mainClass=new MainClass();
                mainMenu=new MainMenu();
                MainClass.addTheMainMenu();
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                worldBackGround=ImageLoader.loadImage("Photos/world/background.jpg");

            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                Ghost.addGhostImage();
            }
        }).start();








        new Thread(new Runnable() {
            public void run() {
                FootStep.left=ImageLoader.loadImage("Photos/foot_step/left.png");
                FootStep.right=ImageLoader.loadImage("Photos/foot_step/right.png");
                FootStep.up=ImageLoader.loadImage("Photos/foot_step/up.png");
                FootStep. down=ImageLoader.loadImage("Photos/foot_step/down.png");
                FootStep. leftUp=ImageLoader.loadImage("Photos/foot_step/left_up.png");
                FootStep. leftDown=ImageLoader.loadImage("Photos/foot_step/left_down.png");
                FootStep.  rightDown=ImageLoader.loadImage("Photos/foot_step/down_right.png");
                FootStep.   rightUp=ImageLoader.loadImage("Photos/foot_step/right_up.png");



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
                cloud=ImageLoader.loadImage("Photos/cloud/cloud122.png");
            }
        }).start();











//        sql=new Sql();


    }



    public static void main(String[] args) {
        new StaticVariables();
    }
}

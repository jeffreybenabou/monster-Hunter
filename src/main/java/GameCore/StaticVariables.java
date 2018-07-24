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


    public static Dimension dimension;
    public static Image watingLabel;

    private ImageLoader imageLoader;
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
        imageLoader=new ImageLoader();
        dimension= Toolkit.getDefaultToolkit().getScreenSize();
        mainClass=new MainClass();

        mainClass.add(new Opening());

        try
        {
            mainMenuBackGround=imageLoader.loadImage("ImageHandel/Photos/menu.png");

        }catch (Exception e)
        {

            JOptionPane.showMessageDialog(StaticVariables.mainClass,e);
            e.printStackTrace();

        }

        new Thread(new Runnable() {
            public void run() {
                try
                {
                    watingLabel=imageLoader.loadImage("ImageHandel/Photos/load.png");
                    key1=imageLoader.loadImage("ImageHandel/Photos/house/key1.png");
                    key2=imageLoader.loadImage("ImageHandel/Photos/house/key2.png");
                    key3=imageLoader.loadImage("ImageHandel/Photos/house/key3.png");
                    carpet=imageLoader.loadImage("ImageHandel/Photos/debris-channel-slider.png");
                    startButton=imageLoader.loadImage("ImageHandel/Photos/menuButton/newGame.png");
                    exitButton=imageLoader.loadImage("ImageHandel/Photos/menuButton/exit.png");
                    loadFromServerButoon=imageLoader.loadImage("ImageHandel/Photos/menuButton/loadOnline.png");
                    loadFromComputerButton=imageLoader.loadImage("ImageHandel/Photos/menuButton/load.png");

                    mainMenu=new MainMenu();
                    mainMenu.setVisible(false);
                }catch (Exception e)
                {
                    JOptionPane.showMessageDialog(StaticVariables.mainClass,e.getCause().toString());
                    e.printStackTrace();

                }

            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                try
                {
                    worldBackGround=imageLoader.loadImage("ImageHandel/Photos/world/background.jpg");

                }catch (Exception e)
                {
                    JOptionPane.showMessageDialog(StaticVariables.mainClass,e.getCause().toString());
e.printStackTrace();
                }

            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                Ghost.addGhostImage();
            }
        }).start();








        new Thread(new Runnable() {
            public void run() {
                try
                {
                    FootStep.left=imageLoader.loadImage("ImageHandel/Photos/foot_step/left.png");
                    FootStep.right=imageLoader.loadImage("ImageHandel/Photos/foot_step/right.png");
                    FootStep.up=imageLoader.loadImage("ImageHandel/Photos/foot_step/up.png");
                    FootStep. down=imageLoader.loadImage("ImageHandel/Photos/foot_step/down.png");
                    FootStep. leftUp=imageLoader.loadImage("ImageHandel/Photos/foot_step/left_up.png");
                    FootStep. leftDown=imageLoader.loadImage("ImageHandel/Photos/foot_step/left_down.png");
                    FootStep.  rightDown=imageLoader.loadImage("ImageHandel/Photos/foot_step/down_right.png");
                    FootStep.   rightUp=imageLoader.loadImage("ImageHandel/Photos/foot_step/right_up.png");



                    lifePanel =imageLoader.loadImage("ImageHandel/Photos/worldFrame/lifeBar1.png");
                    levelLabel =imageLoader.loadImage("ImageHandel/Photos/worldFrame/level.png");
                    shopIcon=imageLoader.loadImage("ImageHandel/Photos/worldFrame/shop_icon.png");
                    settingLabel=imageLoader.loadImage("ImageHandel/Photos/worldFrame/setting_icon.png");
                    moneyIcon=imageLoader.loadImage("ImageHandel/Photos/worldFrame/money_icon.png");
                    tree=imageLoader.loadImage("ImageHandel/Photos/world/tree.png");
                    trunk=imageLoader.loadImage("ImageHandel/Photos/world/trunk.png");
                    crack=imageLoader.loadImage("ImageHandel/Photos/crack.png");
                    houseNum1=imageLoader.loadImage("ImageHandel/Photos/house/house1.png");
                    houseNum2=imageLoader.loadImage("ImageHandel/Photos/house/house2.png");
                    houseNum3=imageLoader.loadImage("ImageHandel/Photos/house/house3.png");
                    cloud=imageLoader.loadImage("ImageHandel/Photos/cloud/cloud122.png");
                }catch (Exception e)
                {
                    JOptionPane.showMessageDialog(mainClass,e.getCause().toString());
                    e.printStackTrace();
                }

            }
        }).start();











//        sql=new Sql();


    }



    public static void main(String[] args) {
        new StaticVariables();
    }
}

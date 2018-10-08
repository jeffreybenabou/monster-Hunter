package GameCore;

import BackgroundObject.FootStep;
import ImageHandel.ImageLoader;
import Objects.Ghost;
import Objects.MainPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class StaticVariables {


    public static boolean stopTheGame=false;
    public static ArrayList<Thread> threadGroup;
    public static BufferedImage watingLabel;
    public static BufferedImage into;
    public static BufferedImage houseIconMiniMap;
    public static BufferedImage hole;

    public static ImageLoader imageLoader;
    public static BufferedImage
            mainMenuBackGround,
            exitButton,
            startButton,
            loadFromServerButoon,
            loadFromComputerButton;


    public static BufferedImage
            lifePanel,
            setting,
    pause,
            levelLabel,
            shopIcon,
            settingLabel,
            moneyIcon;


    public static BufferedImage
    worldBackGround,
    tree,
    trunk,
    specialTrunk,
    sackOfMoney,
    hollowTrunk,
    cloud,
    houseNum1,houseNum2,houseNum3,
    crack,
    key1,key2,key3,
    houseEnter;


    public static GamePanel gamePanel;
    public static World world;
    public static MainMenu mainMenu;
    public static MainClass mainClass;
    public static MainPlayer mainPlayer;
    public static MiniMap miniMap;



    public static Integer level=1,sumOfMoney=0;
    public static BufferedImage coin;
    public static BufferedImage shopLabel;


    public static BufferedImage houseIconChooseMiniMap;
    public static BufferedImage save;
    public static BufferedImage mute;
    public static BufferedImage worldBackGroundScaled;
    public static Image loadMenuBackGRound;
    public static BufferedImage exit;

    public StaticVariables() {
        try {
            threadGroup = new ArrayList<Thread>();
            imageLoader = new ImageLoader();
            mainClass = new MainClass();
            new Thread(new Runnable() {
                public void run() {

                    Opening opening = new Opening();
                    mainClass.add(opening);
                }
            }).start();


        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            coin = imageLoader.loadImage("Photos/coin.png");
            hole = imageLoader.loadImage("Photos/hole.png");
            houseIconMiniMap = imageLoader.loadImage("Photos/minimap/houseDanger.png");
            houseIconChooseMiniMap = imageLoader.loadImage("Photos/minimap/house.png");
            mainMenuBackGround = imageLoader.loadImage("Photos/menu.png");

        } catch (Exception e) {

            e.printStackTrace();

        }

        new Thread(new Runnable() {
            public void run() {
                try {

                    watingLabel = imageLoader.loadImage("Photos/load.png");
                    key1 = imageLoader.loadImage("Photos/house/key1.png");
                    key2 = imageLoader.loadImage("Photos/house/key2.png");
                    key3 = imageLoader.loadImage("Photos/house/key3.png");
                    houseEnter = imageLoader.loadImage("Photos/houseEntrance.png");
                    startButton = imageLoader.loadImage("Photos/menuButton/newGame.png");
                    exitButton = imageLoader.loadImage("Photos/menuButton/exit.png");
                    loadFromServerButoon = imageLoader.loadImage("Photos/option_bar_game/loadGame.png");
                    loadFromComputerButton = imageLoader.loadImage("Photos/menuButton/load.png");
                    save = imageLoader.loadImage("Photos/option_bar_game/saveGame.png");
                    exit=imageLoader.loadImage("Photos/option_bar_game/exit.png");

                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                try {

                    // TODO: 07/10/2018 change the image to png
                    worldBackGround = imageLoader.loadImage("Photos/world/background.png");

                } catch (Exception e) {
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
                try {
                    loadMenuBackGRound=imageLoader.loadImage("Photos/Untitled-1.png");
                    worldBackGroundScaled = imageLoader.loadImage("Photos/minimap/minimapframe1.png");
                    mute = imageLoader.loadImage("Photos/worldFrame/a.png");
                    shopLabel = imageLoader.loadImage("Photos/shop/shop.png");
                    lifePanel = imageLoader.loadImage("Photos/worldFrame/lifeBar1.png");
                    pause = imageLoader.loadImage("Photos/worldFrame/PlayPause.png");
                    setting = imageLoader.loadImage("Photos/option_bar_game/options.png");
                    levelLabel = imageLoader.loadImage("Photos/worldFrame/level.png");
                    shopIcon = imageLoader.loadImage("Photos/worldFrame/shop_icon.png");
                    settingLabel = imageLoader.loadImage("Photos/worldFrame/setting_icon.png");
                    moneyIcon = imageLoader.loadImage("Photos/worldFrame/money_icon.png");
                    tree = imageLoader.loadImage("Photos/world/tree.png");

                    trunk = imageLoader.loadImage("Photos/world/trunk.png");
                    specialTrunk=imageLoader.loadImage("Photos/world/spacial.png");
                    sackOfMoney=imageLoader.loadImage("Photos/bag.png");
                    hollowTrunk=imageLoader.loadImage("Photos/world/trunkh.png");
                    crack = imageLoader.loadImage("Photos/crack.png");
                    houseNum1 = imageLoader.loadImage("Photos/house/house1.png");
                    houseNum2 = imageLoader.loadImage("Photos/house/house2.png");
                    houseNum3 = imageLoader.loadImage("Photos/house/house3.png");
                    cloud = imageLoader.loadImage("Photos/cloud/cloud122.png");
                    FootStep.left = imageLoader.loadImage("Photos/foot_step/left.png");
                    FootStep.right = imageLoader.loadImage("Photos/foot_step/right.png");
                    FootStep.up = imageLoader.loadImage("Photos/foot_step/up.png");
                    FootStep.down = imageLoader.loadImage("Photos/foot_step/down.png");
                    FootStep.leftUp = imageLoader.loadImage("Photos/foot_step/left_up.png");
                    FootStep.leftDown = imageLoader.loadImage("Photos/foot_step/left_down.png");
                    FootStep.rightDown = imageLoader.loadImage("Photos/foot_step/down_right.png");
                    FootStep.rightUp = imageLoader.loadImage("Photos/foot_step/right_up.png");


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }


    public static void restart(){
        level=1;
        sumOfMoney=0;

        MainMenu.wantToLoadGame=false;
        Ghost.numberOfDeadGhost=0;
        Ghost.difficulty=0;
Ghost.notTheFirstGhost=false;
        mainClass.removeAll();
        mainClass.dispose();

        for (int i = 0; i <world.getGhostArrayList().size() ; i++) {
            world.getGhostArrayList().set(i,null);
            world.getGhostArrayList().remove(world.getGhostArrayList().get(i));
        }
        gamePanel=null;
        world=null;
        mainMenu=null;
        mainClass=null;
        mainPlayer=null;
        miniMap=null;



    }


    public static void main(String[] args) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if(screenSize.getHeight()!=768||screenSize.getWidth()!=1366)
            JOptionPane.showMessageDialog(null,"for now the game works  only in resolution of : 1366X768."+"\n"+" please change your resolution");
        else
        {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new StaticVariables();
                }
            });

        }


    }
}

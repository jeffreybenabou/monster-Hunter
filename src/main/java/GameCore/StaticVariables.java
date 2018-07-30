package GameCore;

import BackgroundObject.FootStep;
import ImageHandel.ImageLoader;
import Objects.Ghost;
import Objects.MainPlayer;
import Server.Sql;

import javax.swing.*;
import java.awt.*;

public class StaticVariables {


    public static Dimension dimension;
    public static Image watingLabel;
    public static Image into;

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
        try
        {
            imageLoader=new ImageLoader();
            dimension= Toolkit.getDefaultToolkit().getScreenSize();
            mainClass=new MainClass();

            new Thread(new Runnable() {
                public void run() {
                    into=imageLoader.loadImage("Photos/opening.png");
                    Opening opening=new Opening();
                    mainClass.add(opening);
                }
            }).start();

        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(StaticVariables.mainClass,e);

        }


        try
        {
            mainMenuBackGround=imageLoader.loadImage("Photos/menu.png");

        }catch (Exception e)
        {

            JOptionPane.showMessageDialog(StaticVariables.mainClass,e);
            e.printStackTrace();

        }

        new Thread(new Runnable() {
            public void run() {
                try
                {

                    watingLabel=imageLoader.loadImage("Photos/load.png");
                    key1=imageLoader.loadImage("Photos/house/key1.png");
                    key2=imageLoader.loadImage("Photos/house/key2.png");
                    key3=imageLoader.loadImage("Photos/house/key3.png");
                    carpet=imageLoader.loadImage("Photos/debris-channel-slider.png");
                    startButton=imageLoader.loadImage("Photos/menuButton/newGame.png");
                    exitButton=imageLoader.loadImage("Photos/menuButton/exit.png");
                    loadFromServerButoon=imageLoader.loadImage("Photos/menuButton/loadOnline.png");
                    loadFromComputerButton=imageLoader.loadImage("Photos/menuButton/load.png");

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
                    worldBackGround=imageLoader.loadImage("Photos/world/background.jpg");

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
                    FootStep.left=imageLoader.loadImage("Photos/foot_step/left.png");
                    FootStep.right=imageLoader.loadImage("Photos/foot_step/right.png");
                    FootStep.up=imageLoader.loadImage("Photos/foot_step/up.png");
                    FootStep. down=imageLoader.loadImage("Photos/foot_step/down.png");
                    FootStep. leftUp=imageLoader.loadImage("Photos/foot_step/left_up.png");
                    FootStep. leftDown=imageLoader.loadImage("Photos/foot_step/left_down.png");
                    FootStep.  rightDown=imageLoader.loadImage("Photos/foot_step/down_right.png");
                    FootStep.   rightUp=imageLoader.loadImage("Photos/foot_step/right_up.png");



                    lifePanel =imageLoader.loadImage("Photos/worldFrame/lifeBar1.png");
                    levelLabel =imageLoader.loadImage("Photos/worldFrame/level.png");
                    shopIcon=imageLoader.loadImage("Photos/worldFrame/shop_icon.png");
                    settingLabel=imageLoader.loadImage("Photos/worldFrame/setting_icon.png");
                    moneyIcon=imageLoader.loadImage("Photos/worldFrame/money_icon.png");
                    tree=imageLoader.loadImage("Photos/world/tree.png");
                    trunk=imageLoader.loadImage("Photos/world/trunk.png");
                    crack=imageLoader.loadImage("Photos/crack.png");
                    houseNum1=imageLoader.loadImage("Photos/house/house1.png");
                    houseNum2=imageLoader.loadImage("Photos/house/house2.png");
                    houseNum3=imageLoader.loadImage("Photos/house/house3.png");
                    cloud=imageLoader.loadImage("Photos/cloud/cloud122.png");
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

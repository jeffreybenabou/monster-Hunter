package GameCore;


import Objects.Ghost;
import Objects.MainPlayer;
import sound.Sound;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JLabel  {


    private   JButton muteButton ;

    public static Thread thread;
    public static boolean muteActive =false;
    private Sound buy,notbuy,coinFall;
    private JLabel lifeBar;
    private JLabel levelLabel;
    private JLabel shopIcon;
    private JLabel label;
    private JLabel pause;
    private JLabel shopLabel;
    private JLabel settingLabel;
    private JLabel moneyIcon;
    private JLabel sumOfMoney;
    private JLabel life1000,life2000;
    private JLabel power1,power2;
    private JLabel price1,price2,price3,price4,price5,priceTag;
    private JLabel setting;
    public static JProgressBar jProgressBar;


    private Integer width_ =100;

    private Font font=new Font("Serif", Font.BOLD, 20);
    private Bag bag;
    private JButton loadButton;
    private JButton exitButton;

    public GamePanel (){
        setBounds(0,0,MainClass.dimension.width,MainClass.dimension.height);
        addShopLabel();
        addLifeBar();
        addLevelLabel();
        addShopIcon();
        addSettingIcon();
        addMoneyLabel();
        addSpacialAttack();
        setTheSound();
        setThePauseLabel();
        addTheSettingMenu();

        thread=new Thread();

        setVisible(true);




    }

    private void addTheSettingMenu() {
        setting=new JLabel();
        setting.setBounds(MainClass.dimension.width/2-200,MainClass.dimension.height/3-100,400,400);
        setting.setIcon(new ImageIcon(StaticVariables.setting.getScaledInstance(setting.getWidth(),setting.getHeight(),4)));
        setting.setVisible(false);
        add(setting);
        addSaveTheGame();
        addTheMuteButton();
        addTheLoadButton();
        addTheExitButton();

        setting.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {


            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });

    }

    private void addTheExitButton() {
        exitButton=new JButton();
        exitButton.setContentAreaFilled(false);
        exitButton.setBounds(80,220,setting.getWidth()-setting.getWidth()/3,setting.getHeight()/8);
        exitButton.setIcon(new ImageIcon(StaticVariables.exit.getScaledInstance(exitButton.getWidth(),exitButton.getHeight(),4)));
        setting.add(exitButton);
        exitButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                System.exit(0);

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });
        setting.add(exitButton);
    }

    private void addTheLoadButton() {

        loadButton=new JButton();
        loadButton.setContentAreaFilled(false);
        loadButton.setBounds(80,160,setting.getWidth()-setting.getWidth()/3,setting.getHeight()/8);
        loadButton.setIcon(new ImageIcon(StaticVariables.loadFromServerButoon.getScaledInstance(loadButton.getWidth(),loadButton.getHeight(),4)));
        setting.add(loadButton);
        loadButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                new LoadGame(MainMenu.pathToFile);

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });
        setting.add(loadButton);
    }

    private void addTheMuteButton() {
        muteButton=new JButton();
        muteButton.setBounds(setting.getWidth()/2+10,setting.getHeight()-60,20,20);
        muteButton.setContentAreaFilled(false);
        muteButton.setBorderPainted(false);
        muteButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {

                if(!muteActive)
                    setTheMuteObject(false);
                else
                    setTheMuteObject(true);



            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });

        setting.add(muteButton);
    }

    private void setTheMuteObject(boolean mute) {
        if(!mute)
        {
            muteButton.setIcon(new ImageIcon(StaticVariables.mute.getScaledInstance(muteButton.getWidth(),muteButton.getHeight(),4)));
            muteActive =true;
            MainPlayer.walkingSound.setVolume(-80);
            MainPlayer.attackingSound.setVolume(-80);
            MainPlayer.earthQuaqe.setVolume(-80);
            StaticVariables.world.getBackGroundWorld().setVolume(-80);
            for (Ghost ghost:StaticVariables.world.getGhostArrayList()) {
                ghost.getAttackingSound().setVolume(-80);
                ghost.getDieingSound().setVolume(-80);
            }
        }else
        {
            muteButton.setIcon(null);

            muteActive =false;
            MainPlayer.walkingSound.setVolume(0);
            MainPlayer.attackingSound.setVolume(0);
            MainPlayer.earthQuaqe.setVolume(0);
            StaticVariables.world.getBackGroundWorld().setVolume(0);
            for (Ghost ghost:StaticVariables.world.getGhostArrayList()) {
                ghost.getAttackingSound().setVolume(0);
                ghost.getDieingSound().setVolume(0);
            }
        }


    }

    private void addSaveTheGame() {
        final JButton saveTheGame=new JButton();

        saveTheGame.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {

                if(!thread.isAlive())
                {

                    startTheThread();
                }


            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });
        saveTheGame.setContentAreaFilled(false);
        saveTheGame.setBounds(80,100,setting.getWidth()-setting.getWidth()/3,setting.getHeight()/8);
        saveTheGame.setIcon(new ImageIcon(StaticVariables.save.getScaledInstance(saveTheGame.getWidth(),saveTheGame.getHeight(),4)));
        setting.add(saveTheGame);
    }

    private synchronized void startTheThread() {
        if(!thread.isAlive())
        {
            thread=new Thread(new Runnable() {
                public void run() {
                    while (setting.isVisible())
                    {

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                    new SaveGame(MainMenu.pathToFile,MainMenu.pathToImage);
                }
            });
            thread.start();
        }

    }

    private void setThePauseLabel() {
        pause=new JLabel();
        pause.setBounds(MainClass.dimension.width-200, 0,50,50);
        pause.setIcon(new ImageIcon(StaticVariables.pause.getScaledInstance(pause.getWidth(),pause.getHeight(),1)));
        pause.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                StaticVariables.stopTheGame=!StaticVariables.stopTheGame;
            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });
        add(pause);
    }

    private void setTheSound() {
        buy=new Sound();
        buy.playSound(Sound.path.get(6));
        buy.stopSound();
        buy.setVolume(6);

        notbuy=new Sound();
        notbuy.playSound(Sound.path.get(8));
        notbuy.stopSound();
        notbuy.setVolume(6);

        coinFall=new Sound();
        coinFall.playSound(Sound.path.get(7));
        coinFall.stopSound();
        coinFall.setVolume(6);

    }


    private void addShopLabel() {
        shopLabel=new JLabel();
        shopLabel.setIcon(new ImageIcon(StaticVariables.shopLabel));
        shopLabel.setBounds(350,50,shopLabel.getIcon().getIconWidth(),shopLabel.getIcon().getIconHeight());

        add(shopLabel);
        shopLabel.addMouseListener(StaticVariables.world);
        addItemToShopLabel();
        shopLabel.setVisible(false);
    }

    private void addItemToShopLabel() {

//        25=150;
//        50=200;
//        1000=400;

//2000=750
        life1000=new JLabel();
        life1000.addMouseListener(StaticVariables.world);

        life2000=new JLabel();
        life2000.addMouseListener(StaticVariables.world);
        life1000.setIcon(new ImageIcon(StaticVariables.imageLoader.loadImage("Photos/shop/1000.png")));
        life2000.setIcon(new ImageIcon(StaticVariables.imageLoader.loadImage("Photos/shop/2000.png")));

        life2000.setBounds(380,80,100,100);
        life1000.setBounds(530,80,100,100);
        shopLabel.add(life2000);
        shopLabel.add(life1000);

        power1=new JLabel();
        power2=new JLabel();
        power1.addMouseListener(StaticVariables.world);
        power2.addMouseListener(StaticVariables.world);

        power1.setIcon(new ImageIcon(StaticVariables.imageLoader.loadImage("Photos/shop/25.png")));
        power2.setIcon(new ImageIcon(StaticVariables.imageLoader.loadImage("Photos/shop/50.png")));

        power1.setBounds(380,180,100,100);
        power2.setBounds(530,180,100,100);
        shopLabel.add(power2);
        shopLabel.add(power1);

        price1=new JLabel();
        price2=new JLabel();
        price3=new JLabel();
        price4=new JLabel();
        price5=new JLabel();


        price1.setIcon(new ImageIcon(StaticVariables.imageLoader.loadImage("Photos/shop/1.png")));
        price2.setIcon(new ImageIcon(StaticVariables.imageLoader.loadImage("Photos/shop/2.png")));
        price3.setIcon(new ImageIcon(StaticVariables.imageLoader.loadImage("Photos/shop/3.png")));
        price4.setIcon(new ImageIcon(StaticVariables.imageLoader.loadImage("Photos/shop/4.png")));
        price5.setIcon(new ImageIcon(StaticVariables.imageLoader.loadImage("Photos/shop/5.png")));
        price1.setBounds(35,380,165,28);
        price2.setBounds(35,430,165,28);
        price3.setBounds(35,480,165,28);
        price4.setBounds(35,530,165,28);
        price5.setBounds(35,580,165,28);

        priceTag=new JLabel("2000");
        priceTag.setForeground(Color.red);
        priceTag.setBounds(373,260,200,40);
        priceTag.setHorizontalTextPosition(JLabel.CENTER);
        priceTag.setVerticalTextPosition(JLabel.CENTER);
        shopLabel.add(priceTag);
        shopLabel.add(price1);
        shopLabel.add(price2);
        shopLabel.add(price3);
        shopLabel.add(price4);
        shopLabel.add(price5);


    }

    private void addSpacialAttack() {
        JLabel jLabel=new JLabel();
        jLabel.setBounds(50,0,345,11);
        jLabel.setForeground(Color.black);
        jProgressBar=new JProgressBar();
        jProgressBar.  setUI(new BasicProgressBarUI() {
            protected Color getSelectionBackground() { return Color.white; }
            protected Color getSelectionForeground() { return Color.white; }
        });
        jProgressBar.setStringPainted(true);
        jProgressBar.setValue(0);
        jProgressBar.setMaximum(100);
        jProgressBar.setBackground(Color.gray);
        jProgressBar.setForeground(Color.yellow);
        jProgressBar.setBounds(222,75,345,11);
        jProgressBar.setFont(new Font("Verdana", Font.BOLD, 12));
        jProgressBar.add(jLabel);
        lifeBar.add(jProgressBar);
    }


    private void addLevelLabel(){
         levelLabel=new JLabel();
        levelLabel.setBounds(MainClass.dimension.width-220,MainClass.dimension.height-220,230,250);
        levelLabel.setIcon(new ImageIcon(StaticVariables.levelLabel.getScaledInstance(levelLabel.getWidth(),levelLabel.getHeight(),1)));

         label=new JLabel(""+StaticVariables.level);
        label.setBounds(95,0,levelLabel.getWidth(),levelLabel.getHeight()-20);
        label.setForeground(Color.red);
        label.setFont(font);
        levelLabel.add(label);
        add(levelLabel);
    }

    private void addShopIcon() {
        shopIcon = new JLabel();
        shopIcon.setBounds( MainClass.dimension.width-50, 0,50,50);
        shopIcon.setIcon(new ImageIcon(StaticVariables.shopIcon.getScaledInstance(shopIcon.getWidth(),shopIcon.getHeight(),0)));
        add(shopIcon);
        shopIcon.addMouseListener(StaticVariables.world);
    }

    private void addSettingIcon() {
        settingLabel = new JLabel();
        settingLabel.setBounds( MainClass.dimension.width-130, 0,50,50);
        settingLabel.setIcon(new ImageIcon(StaticVariables.settingLabel.getScaledInstance(settingLabel.getWidth(),settingLabel.getHeight(),0)));
        add(settingLabel);
        settingLabel.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                if(setting.isVisible())
                    setting.setVisible(false);
                else
                    setting.setVisible(true);
            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void addMoneyLabel() {
        try
        {
            moneyIcon = new JLabel();
            moneyIcon.setBounds(50,0,200,150);
            moneyIcon.setIcon(new ImageIcon(StaticVariables.moneyIcon.getScaledInstance(moneyIcon.getWidth(),moneyIcon.getHeight(),0)));
            moneyIcon.addMouseListener(StaticVariables.world);
            sumOfMoney=new JLabel();
            sumOfMoney.setHorizontalTextPosition(JLabel.CENTER);
            sumOfMoney.setVerticalTextPosition(JLabel.CENTER);
            sumOfMoney.setFont(font);
            sumOfMoney.setForeground(Color.yellow);
            sumOfMoney.setBounds(moneyIcon.getWidth()/2-5,moneyIcon.getHeight()-40,300,40);
            sumOfMoney.setText(""+0);
            moneyIcon.add(sumOfMoney);
            add(moneyIcon);
            bag=new Bag();
            bag.addMouseListener(StaticVariables.world);
            add(bag);
            new Thread(new Runnable() {
                public void run() {

                    while (true)
                    {
                        try {
                            sumOfMoney.setText(""+StaticVariables.sumOfMoney);
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }catch (Exception e)
        {
            e.printStackTrace();
        }



    }

    private void addLifeBar() {
        lifeBar=new JLabel();
        lifeBar.setBounds(MainClass.dimension.width/4,0,MainClass.dimension.width/2,MainClass.dimension.height/7);
        lifeBar.setIcon(new ImageIcon(StaticVariables.lifePanel.getScaledInstance(lifeBar.getWidth(),lifeBar.getHeight(),0)));
        add(lifeBar);


        MainPlayer.life.getjProgressBar().setBounds(108,35,543,12);
        MainPlayer.life.getjProgressBar().setBackground(Color.BLACK);
        MainPlayer.life.getjProgressBar().setForeground(Color.RED);
        lifeBar.add(MainPlayer.life.getjProgressBar());


    }





    public Sound getBuy() {
        return buy;
    }


    public Sound getNotbuy() {
        return notbuy;
    }


    public JLabel getShopIcon() {
        return shopIcon;
    }


    public JLabel getLabel() {
        return label;
    }



    public JLabel getMoneyIcon() {
        return moneyIcon;
    }


    public static JProgressBar getjProgressBar() {
        return jProgressBar;
    }

    public JLabel getSettingLabel() {
        return settingLabel;
    }

    public void setSettingLabel(JLabel settingLabel) {
        this.settingLabel = settingLabel;
    }

    public JLabel getSetting() {
        return setting;
    }

    public void setSetting(JLabel setting) {
        this.setting = setting;
    }

    public int getWidth_() {
        return width_;
    }


    public JLabel getShopLabel() {
        return shopLabel;
    }


    public JLabel getLife1000() {
        return life1000;
    }


    public JLabel getLife2000() {
        return life2000;
    }


    public JLabel getPower1() {
        return power1;
    }


    public JLabel getPower2() {
        return power2;
    }



    public JLabel getPriceTag() {
        return priceTag;
    }


    public JLabel getSumOfMoney() {
        return sumOfMoney;
    }





    public Bag getBag() {
        return bag;
    }



    public static Thread getThread() {
        return thread;
    }

    public static void setThread(Thread thread) {
        GamePanel.thread = thread;
    }




}

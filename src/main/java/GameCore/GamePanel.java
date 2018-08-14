package GameCore;



import Objects.MainPlayer;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class GamePanel extends JLabel  {


    private JLabel lifeBar;
    private JLabel levelLabel;
    private JLabel shopIcon;
    private JLabel shopLabel;
    private JLabel settingLabel;
    private JLabel moneyIcon;
    private JLabel sumOfMoney;
    private JLabel life1000,life2000;
    private JLabel power1,power2;
    private JLabel price1,price2,price3,price4,price5,priceTag;
    public static JProgressBar jProgressBar;


    private Integer width_ =100;

    private Font font=new Font("Serif", Font.BOLD, 20);
    private Bag bag;

    public GamePanel (){
        setBounds(0,0,MainClass.dimension.width,MainClass.dimension.height);
        addShopLabel();
        addLifeBar();
        addLevelLabel();
        addShopIcon();
        addSettingIcon();
        addMoneyLabel();
        addSpacialAttack();

        setVisible(true);



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

        JLabel label=new JLabel(""+1);
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
        settingLabel.addMouseListener(StaticVariables.world);
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
            sumOfMoney.setForeground(Color.red);
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

        MainPlayer.life=new Life(MainPlayer.sumOfLife,null);

        MainPlayer.life.getjProgressBar().setBounds(108,35,543,12);
        MainPlayer.life.getjProgressBar().setBackground(Color.BLACK);
        MainPlayer.life.getjProgressBar().setForeground(Color.RED);
        lifeBar.add(MainPlayer.life.getjProgressBar());


    }



    public JLabel getLifeBar() {
        return lifeBar;
    }

    public void setLifeBar(JLabel lifeBar) {
        this.lifeBar = lifeBar;
    }

    public JLabel getLevelLabel() {
        return levelLabel;
    }

    public void setLevelLabel(JLabel levelLabel) {
        this.levelLabel = levelLabel;
    }

    public JLabel getShopIcon() {
        return shopIcon;
    }

    public void setShopIcon(JLabel shopIcon) {
        this.shopIcon = shopIcon;
    }

    public JLabel getSettingLabel() {
        return settingLabel;
    }

    public void setSettingLabel(JLabel settingLabel) {
        this.settingLabel = settingLabel;
    }

    public JLabel getMoneyIcon() {
        return moneyIcon;
    }

    public void setMoneyIcon(JLabel moneyIcon) {
        this.moneyIcon = moneyIcon;
    }

    public static JProgressBar getjProgressBar() {
        return jProgressBar;
    }

    public static void setjProgressBar(JProgressBar jProgressBar) {
        GamePanel.jProgressBar = jProgressBar;
    }


    public int getWidth_() {
        return width_;
    }

    public void setWidth_(int width_) {
        this.width_ = width_;
    }

    public JLabel getShopLabel() {
        return shopLabel;
    }

    public void setShopLabel(JLabel shopLabel) {
        this.shopLabel = shopLabel;
    }

    public JLabel getLife1000() {
        return life1000;
    }

    public void setLife1000(JLabel life1000) {
        this.life1000 = life1000;
    }

    public JLabel getLife2000() {
        return life2000;
    }

    public void setLife2000(JLabel life2000) {
        this.life2000 = life2000;
    }

    public JLabel getPower1() {
        return power1;
    }

    public void setPower1(JLabel power1) {
        this.power1 = power1;
    }

    public JLabel getPower2() {
        return power2;
    }

    public void setPower2(JLabel power2) {
        this.power2 = power2;
    }

    public JLabel getPrice1() {
        return price1;
    }

    public void setPrice1(JLabel price1) {
        this.price1 = price1;
    }

    public JLabel getPrice2() {
        return price2;
    }

    public void setPrice2(JLabel price2) {
        this.price2 = price2;
    }

    public JLabel getPrice3() {
        return price3;
    }

    public void setPrice3(JLabel price3) {
        this.price3 = price3;
    }

    public JLabel getPrice4() {
        return price4;
    }

    public void setPrice4(JLabel price4) {
        this.price4 = price4;
    }

    public JLabel getPrice5() {
        return price5;
    }

    public void setPrice5(JLabel price5) {
        this.price5 = price5;
    }

    public JLabel getPriceTag() {
        return priceTag;
    }

    public void setPriceTag(JLabel priceTag) {
        this.priceTag = priceTag;
    }

    public JLabel getSumOfMoney() {
        return sumOfMoney;
    }

    public void setSumOfMoney(JLabel sumOfMoney) {
        this.sumOfMoney = sumOfMoney;
    }

    public void setWidth_(Integer width_) {
        this.width_ = width_;
    }

    @Override
    public Font getFont() {
        return font;
    }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }

    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }
}

package GameCore;

import ImageHandel.ImageLoader;
import Objects.MainPlayer;



import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Vector;



public class MainMenu extends JLabel implements MouseListener {



    private JLabel watingLabel;
    private TextField textField;
    private JButton confirm,start,load,loadFromServer,exit;
    private String type="";
    private boolean hover=false;
    private Vector<Image> aMale,aFemale;
    private  JLabel male,female;
    private Border border;
    private JLabel chooseThePlayer;
    private int index=0;

    long startTime = System.currentTimeMillis();

    long total = 0;


    long stopTime ;
    long elapsedTime ;


    public MainMenu(){

        setIcon(new ImageIcon(StaticVariables.mainMenuBackGround.getScaledInstance(MainClass.dimension.width,MainClass.dimension.height, 0)));
        setBounds(0, 0, MainClass.dimension.width, MainClass.dimension.height);
        ChooseThePlayer();
        for (int i = 0; i <4 ; i++) {
            createButton(i);
        }
        repaint();



    }


    public void ChooseThePlayer(){
        chooseThePlayer=new JLabel();
        chooseThePlayer.setVisible(false);
        chooseThePlayer.addMouseListener(this);
        add(chooseThePlayer);
        new Thread(new Runnable() {
            public void run() {
                aMale=new Vector<Image>();
                aFemale=new Vector<Image>();
                female=new JLabel();
                male=new JLabel();

                new Thread(new Runnable() {
                    public void run() {
                        ImageLoader.addImageOfObject(new File("src/main/java/ImageHandel/Photos/character/male/stand/"),aMale,new Dimension(500,400));
                        ImageLoader.addImageOfObject(new File("src/main/java/ImageHandel/Photos/character/female/stand/"),aFemale,new Dimension(500,450));
                        female.setIcon(new ImageIcon(aFemale.get(0)));
                        male.setIcon(new ImageIcon(aMale.get(0)));
                    }
                }).start();




                chooseThePlayer.setBounds(250,100,getWidth()/2+200,getHeight()/2+200);
                chooseThePlayer.setOpaque(true);
                chooseThePlayer.setBackground(new Color(0, 0, 0, 254));
                border  = BorderFactory.createLineBorder(Color.red);

                chooseThePlayer.setBorder(border);
                chooseThePlayer.setForeground(Color.red);

                addPlayerLabel();
            }
        }).start();

    }

    private void addPlayerLabel() {

        chooseThePlayer.add(female);
        chooseThePlayer.add(male);
        female.addMouseListener(this);
        male.addMouseListener(this);
        male.setBounds(500,100,300,400);
        male.setName("male");
        male.setHorizontalAlignment(JLabel.CENTER);
        female.setBounds(80,100,300,400);
        female.setHorizontalAlignment(JLabel.CENTER);
        female.setName("female");

        female.setOpaque(true);

        male.setOpaque(true);
        getPlayerName();
    }

    public void moveTheCharacter(final JLabel label, final Vector<Image> vector)
    {

        new Thread(new Runnable() {
            public void run() {
                while (hover)
                {
                    try
                    {


                        index++;
                        if(index>=vector.size())
                            index=0;
                        label.setIcon(new ImageIcon(vector.get(index)));
                        Thread.sleep(15);

                    }
                    catch (NullPointerException e)
                    {

//                        System.out.println("NullPointerException on addplayer lebel"+e.getCause());
                        break;

                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {

//                        System.out.println("ArrayIndexOutOfBoundsException on addplayerlebel  "+e.getCause());
                        break;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();




    }


    private void getPlayerName() {

        textField=new TextField();
         confirm=new JButton();
        confirm.setBounds(390,540,90,30);
        confirm.setText("confirm");
        confirm.setName("confirm");
        confirm.addMouseListener(this);
        chooseThePlayer.add(confirm);

        textField.setName("choose");

        textField.setBounds(340,50,200,20);
        JLabel label=new JLabel(" enter your character name here :");
        label.setBounds(340,20,200,20);
        chooseThePlayer.add(label);

        chooseThePlayer.add(textField);



    }

    private void createButton(int i) {
        JButton button=null;

        switch (i)
        {
            case 0:{

                start=new JButton(new ImageIcon(StaticVariables.startButton.getScaledInstance(MainClass.dimension.width / 5, MainClass.dimension.height / 8,0)));
                start.setBounds(300, 100, start.getIcon().getIconWidth(), start.getIcon().getIconHeight());
                start.setName("start");
                add(start);
                button=start;
            break;
        }
            case 1:{
                exit=new JButton(new ImageIcon(StaticVariables.exitButton.getScaledInstance(MainClass.dimension.width / 5, MainClass.dimension.height / 8,0)));
                exit.setBounds(800, 500, exit.getIcon().getIconWidth(), exit.getIcon().getIconHeight());
                exit.setName("exit");
                add(exit);
                button=exit;
                break;
            }
            case 2:{
                loadFromServer=new JButton(new ImageIcon(StaticVariables.loadFromServerButoon.getScaledInstance(MainClass.dimension.width / 5, MainClass.dimension.height / 8,0)));
                loadFromServer.setBounds(300, 500, loadFromServer.getIcon().getIconWidth(), loadFromServer.getIcon().getIconHeight());
                loadFromServer.setName("loadServer");
                add(loadFromServer);
                button=loadFromServer;
                break;
            }
            case 3:{

                load=new JButton(new ImageIcon(StaticVariables.loadFromComputerButton.getScaledInstance(MainClass.dimension.width / 5, MainClass.dimension.height / 8,0)));
                load.setBounds(800, 100, load.getIcon().getIconWidth(), load.getIcon().getIconHeight());
                load.setName("load");
                add(load);
                button=load;
                break;
            }
        }

        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.addMouseListener(this);


    }



    private void addTheWatingLabel(){

        new Thread(new Runnable() {
            public void run() {
                startTime = System.currentTimeMillis();
                  watingLabel =new JLabel(new ImageIcon(ImageLoader.loadImage("Photos/Untitled-1.png").getScaledInstance(getWidth(),getHeight(),0)));
                watingLabel.setBounds(0,0,getWidth(),getHeight());
                  StaticVariables.mainClass.add(watingLabel);
                MainClass.removeTheMainMenu();
                new Thread(new Runnable() {

                    public void run() {
                        MainPlayer.makeNewElements(type);
                        while (true)
                        {
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(StaticVariables.worldBackGround==null)
                            {
                                try {
                                    Thread.sleep(5);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                            {

                                StaticVariables.mainPlayer=new MainPlayer();
                                StaticVariables.world=new World();
                                StaticVariables.world.setIcon(new ImageIcon(StaticVariables.worldBackGround));

                                StaticVariables.miniMap=new MiniMap();
                                StaticVariables.gamePanel=new GamePanel();
                                StaticVariables.gamePanel.add(StaticVariables.miniMap);
                                MainClass.addTheWorld();
                                watingLabel.setVisible(false);
                                stopTime = System.currentTimeMillis();


                                break;
                            }
                        }

                    }
                }).start();










    }
}).start();


    }


    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

        if(e.getComponent().equals(confirm))
        {

            if(!type.equals("")&&!textField.getText().equals(""))
            {
                addTheWatingLabel();
            }
            else

                JOptionPane.showMessageDialog(this,"pick user name and character");






        }
        if(e.getComponent().equals(male))
        {
            male.setBorder(border);
            male.setBorder( BorderFactory.createMatteBorder(
                    3, 3, 3, 3, Color.red));

            female.setBorder(null);
            type="male";
        }
        if(e.getComponent().equals(female))
        {
            female.setBorder(border);
            female.setBorder( BorderFactory.createMatteBorder(
                    3, 3, 3, 3, Color.red));
            male.setBorder(null);
            type="female";
        }


        if(e.getComponent().equals(start))
        {


            chooseThePlayer.setVisible(true);


        }
        if(e.getComponent().equals(exit))
        {

        }
        if(e.getComponent().equals(load))
        {

        }
        if(e.getComponent().equals(loadFromServer))
        {

        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {



            if(e.getComponent().equals(male))
            {
                hover=true;
                moveTheCharacter(male,aMale);
            }
            else if(e.getComponent().equals(female))
            {
                hover=true;
                moveTheCharacter(female,aFemale);
            }





    }

    public void mouseExited(MouseEvent e) {
        hover=false;
    }
}

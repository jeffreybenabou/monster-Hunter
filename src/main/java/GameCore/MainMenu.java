package GameCore;

import ImageHandel.ImageLoader;
import Objects.Ghost;
import Objects.MainPlayer;
import sound.Sound;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class MainMenu extends JLabel implements MouseListener {

    private ArrayList<String> saveName;
    public static LoadGame loadGame=null;
    public static String pathToFile,pathToImage;
    public static boolean wantToLoadGame=false;
    private int optionChoose,index=0;
    private ImageLoader imageLoader;
    private Sound backgroundSound;
    private JLabel watingLabel,male,female,chooseThePlayer,loadingIcon,loadGameLabel,usersNames;
    private TextField textField;
    private JButton confirm,start,exit,loadGameButton,loadGameFromLoadingMenu;
    private String type="";
    private boolean hover=false;
    private ArrayList<ImageIcon> aMale,aFemale;
    private Border border;
    private JOptionPane showOptionDialog ;
    private JDialog jDialog;
    private JLabel loadGameImage;
    private JLabel saveListLabebl;
    private JList jList;
    private ArrayList<BufferedImage> imageOfSave;


    public MainMenu(){

        imageOfSave=new ArrayList<BufferedImage>();
        imageLoader=new ImageLoader();
        backgroundSound=new Sound();
        backgroundSound.playSound(Sound.path.get(2));

        try
        {
            addMouseListener(this);
            setIcon(new ImageIcon(StaticVariables.mainMenuBackGround.getScaledInstance(MainClass.dimension.width,MainClass.dimension.height,Image.SCALE_SMOOTH)));
            setBounds(0, 0, MainClass.dimension.width, MainClass.dimension.height);
            addTheLoadingLabel();
            ChooseThePlayer();

            for (int i = 0; i <3 ; i++) {
                createButton(i);
            }
            setVisible(false);


        }catch (Exception e)
        {

            e.printStackTrace();

        }




    }

    private void loadTheSaveNames() {
        new Thread(new Runnable() {
            public void run() {

                File file = new File(getClass().getClassLoader().getResource("save/").getFile());
                File[] listOfFiles = file.listFiles();
                saveName = new ArrayList<String>();
                int j = 0;
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].getName().endsWith("txt")) {
                        saveName.add(listOfFiles[i].getName());

                    } else if (listOfFiles[i].getName().endsWith("png")) {

                        try {
                            imageOfSave.add(ImageIO.read(new File("target/classes/save/" + listOfFiles[i].getName())));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
                addTheListOfUsersSaveGame();
            }
        }).start();
    }

    private void addTheListOfUsersSaveGame() {



        setTheList();
        setTheScroolPen();
        addTheLoadingMenuButtons();
        jList.repaint();
        loadGameLabel.repaint();




    }

    private void setTheList() {
        jList = new JList(saveName.toArray());
        jList.setBounds(0, 50, saveListLabebl.getWidth(), saveListLabebl.getHeight());
        jList.setLayoutOrientation(JList.VERTICAL);
        jList.setBorder(border);
        jList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.setLayoutOrientation(JList.VERTICAL);

        jList.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {


            }

            public void mousePressed(MouseEvent e) {
                checkTheMatchImage();

            }

            public void mouseReleased(MouseEvent e) {


            }

            public void mouseEntered(MouseEvent e) {


            }

            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void setTheScroolPen() {
        final JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(jList);
        jScrollPane.setBounds(saveListLabebl.getBounds().x,70,saveListLabebl.getBounds().width,saveListLabebl.getBounds().height-140);
        jScrollPane.setViewportView(jList);
        jScrollPane.add(saveListLabebl);
        loadGameLabel.add(jScrollPane);
    }

    private void addTheLoadingMenuButtons() {
        loadGameFromLoadingMenu=new JButton("Press here to load the game ");
        loadGameFromLoadingMenu.setBounds(saveListLabebl.getX()+25,saveListLabebl.getHeight()-50,200,40);
        loadGameLabel.add(loadGameFromLoadingMenu);
                usersNames=new JLabel("choose the save game you want to load ");
        usersNames.setBounds(saveListLabebl.getX()+25,20,250,40);
        loadGameLabel.add(usersNames);
        loadGameFromLoadingMenu.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                setTheTypeOfCharacter();

                wantToLoadGame=true;

                addTheWaitingLabel();
            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });

    }

    private void setTheTypeOfCharacter() {
        try {
            setThePathToLoadGame(jList.getSelectedValue().toString(),false);

            Scanner in = new Scanner(new FileReader(pathToFile));
            while (in.hasNext())
            {

                String type=in.next();
                if(type.equals("male")||type.equals("female"))
                {
                    this.type=type;
                    break;
                }





            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void checkTheMatchImage() {
            loadGameImage.setIcon(new ImageIcon(imageOfSave.get(jList.getSelectedIndex()).getScaledInstance(loadGameImage.getWidth(),loadGameImage.getHeight(),4)));


    }

    private void addTheLoadingLabel() {
        setTheLoadGameLabel();
        setTheImageOfLoadGameLable();
        addTheListOfSaves();



    }

    private void addTheListOfSaves() {
        saveListLabebl=new JLabel();
        saveListLabebl.setBounds(loadGameImage.getWidth()+10,5,loadGameLabel.getWidth()-loadGameImage.getWidth()-15,loadGameLabel.getHeight()-10);
        saveListLabebl.setOpaque(true);

        loadTheSaveNames();
    }

    private void setTheImageOfLoadGameLable() {
        loadGameImage=new JLabel();
        loadGameImage.setOpaque(true);
        loadGameImage.setBackground(Color.BLACK);
        loadGameImage.setBorder(BorderFactory.createLineBorder(Color.white));

        loadGameImage.setBounds(5,5,loadGameLabel.getWidth()/2+loadGameLabel.getWidth()/10-5,loadGameLabel.getHeight()-10);

        try
        {
            loadGameImage.setIcon(new ImageIcon(StaticVariables.loadMenuBackGRound.getScaledInstance(loadGameImage.getWidth(),loadGameImage.getHeight(),4)));

        }catch (Exception e)
        {

            e.printStackTrace();
        }
        loadGameLabel.add(loadGameImage);
    }

    private void setTheLoadGameLabel() {
        loadGameLabel=new JLabel();

        loadGameLabel.setBorder(BorderFactory.createLineBorder(Color.white));
        loadGameLabel.setVisible(false);
        loadGameLabel.setOpaque(true);
        repaint();
        loadGameLabel.setBounds(MainClass.dimension.width/2-(MainClass.dimension.width/2)/2, MainClass.dimension.height/6,MainClass.dimension.width/2,MainClass.dimension.height/2+MainClass.dimension.height/5);
        loadGameLabel.setBackground(Color.GRAY);
        add(loadGameLabel);
    }


    public void ChooseThePlayer(){
        chooseThePlayer=new JLabel();
        chooseThePlayer.setBorder(BorderFactory.createLineBorder(Color.white));

        chooseThePlayer.setVisible(false);
        repaint();
        chooseThePlayer.addMouseListener(this);
        add(chooseThePlayer);
        new Thread(new Runnable() {
            public void run() {
                aMale=new ArrayList<ImageIcon>();
                aFemale=new ArrayList<ImageIcon>();
                female=new JLabel();
                male=new JLabel();
                addPlayerLabel();
                new Thread(new Runnable() {
                    public void run() {

                        new Thread(new Runnable() {
                            public void run() {

                                imageLoader.addImageOfObject(32,"Photos/character/male/attack/down/",aMale);

                                    male.setIcon(aMale.get(0));




                            }
                        }).start();
                        new Thread(new Runnable() {
                            public void run() {

                                imageLoader.addImageOfObject(43,"Photos/character/female/attack/down/",aFemale);
                                    female.setIcon(aFemale.get(0));




                            }
                        }).start();
                    }
                }).start();




                chooseThePlayer.setBounds(250,100,getWidth()/2+200,getHeight()/2+200);
                chooseThePlayer.setOpaque(true);
                chooseThePlayer.setBackground(new Color(0, 0, 0, 254));
                border  = BorderFactory.createLineBorder(Color.red);

                chooseThePlayer.setBorder(border);
                chooseThePlayer.setForeground(Color.red);
                setBorder(null);

            }
        }).start();

    }

    private void addPlayerLabel() {

        chooseThePlayer.add(female);
        chooseThePlayer.add(male);
        female.addMouseListener(this);
        male.addMouseListener(this);
        male.setBounds(480,100,380,400);
        male.setName("male");
        male.setHorizontalAlignment(JLabel.CENTER);
        female.setBounds(20,100,380 ,400);
        female.setHorizontalAlignment(JLabel.RIGHT);
        female.setName("female");

        female.setOpaque(true);

        male.setOpaque(true);
        getPlayerName();
    }

    public void moveTheCharacter(final JLabel label, final ArrayList<ImageIcon> vector) {

        new Thread(new Runnable() {
            public void run() {
                while (hover) {
                    try {


                        index++;
                        if (index >= vector.size())
                            index = 0;
                        label.setIcon((vector.get(index)));
                        Thread.sleep(40);

                    } catch (NullPointerException e) {


                        break;

                    } catch (ArrayIndexOutOfBoundsException e) {


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
        label.setForeground(Color.red);
        label.setBounds(340,20,200,20);
        chooseThePlayer.add(label);

        chooseThePlayer.add(textField);



    }

    private void createButton(int i) {
        JButton button=null;
        try
        {
            switch (i)
            {
                case 0:{

                    start=new JButton(new ImageIcon(StaticVariables.startButton.getScaledInstance(MainClass.dimension.width / 5, MainClass.dimension.height / 8,0)));
                    start.setBounds(550-MainClass.differenceX, 200-MainClass.differenceY, start.getIcon().getIconWidth(), start.getIcon().getIconHeight());
                    start.setName("start");
                    start.setPressedIcon(new ImageIcon(StaticVariables.startButton.getScaledInstance(start.getWidth()+20,start.getHeight()+20,4)));

                    add(start);
                    button=start;
                    break;
                }
                case 1:{
                    loadGameButton=new JButton(new ImageIcon(StaticVariables.loadFromComputerButton.getScaledInstance(MainClass.dimension.width / 5, MainClass.dimension.height / 8,0)));
                    loadGameButton.setBounds(550-MainClass.differenceX, 350-MainClass.differenceY, loadGameButton.getIcon().getIconWidth(), loadGameButton.getIcon().getIconHeight());
                    loadGameButton.setName("load");
                    loadGameButton.setPressedIcon(new ImageIcon(StaticVariables.loadFromComputerButton.getScaledInstance(loadGameButton.getWidth()+20,loadGameButton.getHeight()+20,4)));

                    add(loadGameButton);
                    button=loadGameButton;
                    break;
                }
                case 2:{
                    exit=new JButton(new ImageIcon(StaticVariables.exitButton.getScaledInstance(MainClass.dimension.width / 5, MainClass.dimension.height / 8,0)));
                    exit.setBounds(550-MainClass.differenceX, 500-MainClass.differenceY, exit.getIcon().getIconWidth(), exit.getIcon().getIconHeight());
                    exit.setName("exit");
                    exit.setPressedIcon(new ImageIcon(StaticVariables.exitButton.getScaledInstance(exit.getWidth()+20,exit.getHeight()+20,4)));

                    add(exit);
                    button=exit;
                    break;
                }


            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }



        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.addMouseListener(this);


    }



    private void addTheWaitingLabel(){













        new Thread(new Runnable() {
            public void run() {
                try
                {




                    loadingIcon=new JLabel("loading.....");
                    loadingIcon.setHorizontalTextPosition(JLabel.CENTER);
                    loadingIcon.setFont(new Font("Serif", Font.PLAIN, 45));
                    loadingIcon.setForeground(Color.red);
                    loadingIcon.setBounds(getWidth()-250-MainClass.differenceX,getHeight()-80-MainClass.differenceY,400-MainClass.differenceX,80-MainClass.differenceY);
                    watingLabel =new JLabel(new ImageIcon(StaticVariables.watingLabel.getScaledInstance(MainClass.dimension.width,MainClass.dimension.height,Image.SCALE_SMOOTH)));
                    watingLabel.setBounds(0,0,MainClass.dimension.width,MainClass.dimension.height);
                    watingLabel.add(loadingIcon);
                    StaticVariables.mainClass.add(watingLabel);
                    backgroundSound.stopSound();
                    MainClass.removeTheMainMenu();
                }catch (Exception e)
                {
                    e.printStackTrace();

                }

                new Thread(new Runnable() {

                    public void run() {
                        MainPlayer.makeNewElements(type);
                        while (true)
                        {
                            try {
                                Thread.sleep(5000);
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

                                try
                                {
                                    MainPlayer.life=new Life(MainPlayer.sumOfLife,null);
                                    StaticVariables.miniMap=new MiniMap();
                                    StaticVariables.mainPlayer=new MainPlayer();
                                    StaticVariables.world=new World();
                                    try
                                    {
                                                                            StaticVariables.world.setIcon(new ImageIcon(StaticVariables.worldBackGround));

                                    }catch (Exception e)
                                    {
                                        e.printStackTrace();
                                    }



                                    StaticVariables.gamePanel=new GamePanel();
                                    StaticVariables.gamePanel.add(StaticVariables.miniMap);
                                    MainClass.addTheWorld();
                                    watingLabel.setVisible(false);
                                    if(wantToLoadGame)
                                    loadGame=new LoadGame(pathToFile);
                                    else
                                    new SaveGame(MainMenu.pathToFile,MainMenu.pathToImage);

                                }catch (Exception e)
                                {

e.printStackTrace();
                                }



                                break;
                            }
                        }

                    }
                }).start();










    }
}).start();


    }

    private void setThePathToLoadGame(String path,boolean newGame) {
        if(newGame)
        {
            pathToFile=ImageLoader.class.getClassLoader().getResource("save/").getPath()+path+".txt";
            pathToImage=ImageLoader.class.getClassLoader().getResource("save/").getPath()+path+".png";
        }
        else
        {

            pathToFile=ImageLoader.class.getClassLoader().getResource("save/").getPath()+path;
            if(path.endsWith("txt"))
            path=path.replace("txt","png");
            pathToImage=ImageLoader.class.getClassLoader().getResource("save/").getPath()+path;

        }

    }

    private void defineTheLevelDifficulty() {


        Button button1=new Button("easy");
        button1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                showOptionDialog.setVisible(false);
                jDialog.setVisible(false);
                Ghost.difficulty=0;
            }
        });
        Button button2=new Button("normal");
        button2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                showOptionDialog.setVisible(false);
                jDialog.setVisible(false);
                Ghost.difficulty=1;
            }
        });
        Button button3=new Button("hard");
        button3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                showOptionDialog.setVisible(false);
                jDialog.setVisible(false);
                Ghost.difficulty=2;
            }
        });
        showOptionDialog = new JOptionPane(  "choose wisely ", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null, new Object[]{button1, button2,button3});
        jDialog = showOptionDialog.createDialog(null, "pick Difficulty");
        jDialog.setModal(true);
        showOptionDialog.setVisible(true);
        jDialog.setVisible(true);

        addTheLoadingMenuFromNewGame();
    }

    private void addTheLoadingMenuFromNewGame() {
        File file = new File(""+ImageLoader.class.getClassLoader().getResource("save/").getPath(),""+textField.getText()+".txt");

        try {
            if(!file.createNewFile()){
                askToLoadAGame();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void askToLoadAGame() {

        try
        {
            optionChoose= JOptionPane.showOptionDialog(this,null,"you have been use this user name already ,do you wish to start a new game ?all progress will be restart.",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,new ImageIcon(StaticVariables.imageLoader.loadImage("save/"+textField.getText()+".png").getScaledInstance(600,400,4)),null,null);

        }catch (Exception e)
        {
 e.printStackTrace();
        }


    }


    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

        if(e.getComponent().equals(loadGameButton))
        {
            start.setEnabled(false);
            loadGameLabel.setVisible(true);
        }
        if(e.getComponent().equals(confirm))
        {

            if(!type.equals("")&&!textField.getText().equals(""))
            {
                defineTheLevelDifficulty();
                setThePathToLoadGame(textField.getText(),true);

                if(optionChoose!=-1&&optionChoose!=1&&optionChoose!=2)
                addTheWaitingLabel();
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
            loadGameButton.setEnabled(false);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    chooseThePlayer.setVisible(true);
                }
            }).start();



        }

        if(e.getComponent().equals(this))
        {
            start.setEnabled(true);
            loadGameButton.setEnabled(true);
            chooseThePlayer.setVisible(false);
            loadGameLabel.setVisible(false);
        }
        if(e.getComponent().equals(exit))
        {
            new Thread(new Runnable() {
                public void run() {


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    System.exit(0);
                }
            }).start();

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


    public Sound getBackgroundSound() {
        return backgroundSound;
    }

    public void setBackgroundSound(Sound backgroundSound) {
        this.backgroundSound = backgroundSound;
    }

    public JLabel getWatingLabel() {
        return watingLabel;
    }

    public void setWatingLabel(JLabel watingLabel) {
        this.watingLabel = watingLabel;
    }

    public TextField getTextField() {
        return textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public JButton getConfirm() {
        return confirm;
    }

    public void setConfirm(JButton confirm) {
        this.confirm = confirm;
    }

    public JButton getStart() {
        return start;
    }

    public void setStart(JButton start) {
        this.start = start;
    }



    public JButton getExit() {
        return exit;
    }

    public void setExit(JButton exit) {
        this.exit = exit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHover() {
        return hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public ArrayList<ImageIcon> getaMale() {
        return aMale;
    }

    public void setaMale(ArrayList<ImageIcon> aMale) {
        this.aMale = aMale;
    }

    public ArrayList<ImageIcon> getaFemale() {
        return aFemale;
    }

    public void setaFemale(ArrayList<ImageIcon> aFemale) {
        this.aFemale = aFemale;
    }

    public JLabel getMale() {
        return male;
    }

    public void setMale(JLabel male) {
        this.male = male;
    }

    public JLabel getFemale() {
        return female;
    }

    public void setFemale(JLabel female) {
        this.female = female;
    }

    @Override
    public Border getBorder() {
        return border;
    }

    @Override
    public void setBorder(Border border) {
        this.border = border;
    }

    public JLabel getChooseThePlayer() {
        return chooseThePlayer;
    }

    public void setChooseThePlayer(JLabel chooseThePlayer) {
        this.chooseThePlayer = chooseThePlayer;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}

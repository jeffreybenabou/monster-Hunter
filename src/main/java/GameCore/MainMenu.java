package GameCore;

import ImageHandel.ImageLoader;
import Objects.MainPlayer;
import Server.Sql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MainMenu extends JLabel implements MouseListener {


    public MainMenu(){
        setBounds(0, 0, MainClass.dimension.width, MainClass.dimension.height);
        setIcon(new ImageIcon(StaticVariables.mainMenuBackGround.getScaledInstance(getWidth(),getHeight(), 1)));
        for (int i = 0; i <4 ; i++) {
            createButton(i);
        }

    }


    private String getPlayerName() {


        return MainPlayer.nameOfPlayer = (String) JOptionPane.showInputDialog(
                this,
                "pick your character name:\n",
                "Customized name",
                JOptionPane.PLAIN_MESSAGE,
                new ImageIcon(ImageLoader.loadImage("Photos/character/attack/0.png")),
                null,
                "enter name ");


    }

    private void createButton(int i) {
        JButton button=null;

        switch (i)
        {
            case 0:{
                button=new JButton(new ImageIcon(StaticVariables.startButton.getScaledInstance(MainClass.dimension.width / 5, MainClass.dimension.height / 8,0)));
                button.setBounds(300, 100, button.getIcon().getIconWidth(), button.getIcon().getIconHeight());
                button.setName("start");
                add(button);
            break;
        }
            case 1:{
                button=new JButton(new ImageIcon(StaticVariables.exitButton.getScaledInstance(MainClass.dimension.width / 5, MainClass.dimension.height / 8,0)));
                button.setBounds(800, 500, button.getIcon().getIconWidth(), button.getIcon().getIconHeight());
                button.setName("exit");
                add(button);
                break;
            }
            case 2:{
                button=new JButton(new ImageIcon(StaticVariables.loadFromServerButoon.getScaledInstance(MainClass.dimension.width / 5, MainClass.dimension.height / 8,0)));
                button.setBounds(300, 500, button.getIcon().getIconWidth(), button.getIcon().getIconHeight());
                button.setName("loadServer");
                add(button);
                break;
            }
            case 3:{
                button=new JButton(new ImageIcon(StaticVariables.loadFromComputerButton.getScaledInstance(MainClass.dimension.width / 5, MainClass.dimension.height / 8,0)));
                button.setBounds(800, 100, button.getIcon().getIconWidth(), button.getIcon().getIconHeight());
                button.setName("load");
                add(button);
                break;
            }
        }

        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.addMouseListener(this);


    }

    private void addTheUserNameToSql(){
        String name=getPlayerName();
        Sql.checkIfUserExist(getPlayerName());
    }

    private void addTheWatingLabel(){

        JLabel label=new JLabel(new ImageIcon(ImageLoader.loadImage("Photos/Untitled-1.png").getScaledInstance(getWidth(),getHeight(),0)));
        label.setBounds(0,0,getWidth(),getHeight());
        StaticVariables.mainClass.add(label);



            setVisible(false);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        /*
        * the sleep method is used for show the waiting label
        * for 5 second
        * */

        label.setVisible(false);

    }


    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if(e.getComponent().getName().equals("start"))
        {
//            addTheUserNameToSql();

            new Thread(new Runnable() {
                public void run() {

                    addTheWatingLabel();

                    MainClass.addTheWorld();



                }
            }).start();



        }
        if(e.getComponent().getName().equals("exit"))
        {

        }
        if(e.getComponent().getName().equals("load"))
        {

        }
        if(e.getComponent().getName().equals("loadServer"))
        {

        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}

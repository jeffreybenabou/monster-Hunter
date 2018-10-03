package GameCore;



import javax.swing.*;
import java.awt.*;

 class Opening  extends JPanel {

    private JLabel opquaeLabel;

     Opening() {

           setBounds(0, 0, MainClass.dimension.width, MainClass.dimension.height);
            ImageIcon imageIcon = new ImageIcon(StaticVariables.into.getScaledInstance(MainClass.dimension.width, MainClass.dimension.height, 4));
            opquaeLabel=new JLabel();
            opquaeLabel.setBounds(0, 0, MainClass.dimension.width, MainClass.dimension.height);


            JLabel imageLabel = new JLabel();
            imageLabel.setBounds(0, 0, MainClass.dimension.width, MainClass.dimension.height);
            imageLabel.setIcon(imageIcon);

            setOpaque(true);
            opquaeLabel.setOpaque(true);


            add(opquaeLabel);
            add(imageLabel);

            setLayout(null);
            setVisible(true);

            new Thread(new Runnable() {
                public void run() {
                    setTheLabelToOpaque();
                }
            }).start();










    }

    private   void setTheLabelToOpaque(){
        int opaque =254;

        while (opaque-- > 0) {


                opquaeLabel.setBackground(new Color(0, 0, 0, opaque));

                repaint();





            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        addTheMainMenu();



    }

    private void addTheMainMenu() {

        StaticVariables.mainMenu=new MainMenu();
        StaticVariables.mainMenu.setVisible(true);
        StaticVariables.mainMenu.setOpaque(true);
        MainClass.addTheMainMenu();
        StaticVariables.mainClass.remove(this);
        StaticVariables.mainClass.repaint();

        setVisible(false);
    }


}

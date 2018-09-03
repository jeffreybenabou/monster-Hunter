package GameCore;



import javax.swing.*;

public class Opening extends JLabel {


    public Opening(){




        try
        {


            ImageIcon imageIcon=new ImageIcon(StaticVariables.into.getScaledInstance(MainClass.dimension.width,MainClass.dimension.height,4));

           setIcon(imageIcon);
            setBounds(0,0,MainClass.dimension.width,MainClass.dimension.height);
setVisible(true);

            // TODO: 24/07/2018 check why the opening image wont work

        }catch (NullPointerException e)
        {
            e.printStackTrace();


        }

        new Thread(new Runnable() {
            public void run() {

                try {
                    Thread.sleep(5000);
                    StaticVariables.mainMenu.setVisible(true);
                    StaticVariables.mainMenu.setOpaque(true);
                    MainClass.addTheMainMenu();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setVisible(false);





            }
        }).start();

    }
}

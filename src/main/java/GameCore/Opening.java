package GameCore;



import javax.swing.*;

public class Opening extends JLabel {


    public Opening(){




        try
        {

            // TODO: 24/07/2018 check why the opening image wont work
            setBounds(0,0,StaticVariables.dimension.width,StaticVariables.dimension.height);
            setIcon(new ImageIcon(StaticVariables.into));






        }catch (NullPointerException e)
        {
            JOptionPane.showMessageDialog(StaticVariables.mainClass,e.getStackTrace());
            e.printStackTrace();


        }

        new Thread(new Runnable() {
            public void run() {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setVisible(false);
                StaticVariables.mainMenu.setVisible(true);
                MainClass.addTheMainMenu();




            }
        }).start();

    }
}

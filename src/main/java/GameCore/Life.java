package GameCore;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Life implements ChangeListener {
    private JProgressBar jProgressBar;
    private boolean isAlive;

    public Life(int max,GameObject gameObject){
        jProgressBar=new JProgressBar();


        jProgressBar.setMinimum(0);
        jProgressBar.setMaximum(max);
        jProgressBar.setValue(max);
        jProgressBar.setString(""+max);
        jProgressBar.setStringPainted(true);
        jProgressBar.addChangeListener(this);
        isAlive=true;
        if(gameObject!=null)
        {
            jProgressBar.setBounds(0,0,gameObject.getWidth(),15);
            jProgressBar.setForeground(Color.red);
            jProgressBar.setBackground(Color.black);
            gameObject.add(jProgressBar);

        }

    }

    public JProgressBar getjProgressBar() {
        return jProgressBar;
    }

    public void setjProgressBar(JProgressBar jProgressBar) {
        this.jProgressBar = jProgressBar;
    }

    public void stateChanged(ChangeEvent e) {

        if(jProgressBar.getValue()<=0)
        {
            isAlive=false;

        }

    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}

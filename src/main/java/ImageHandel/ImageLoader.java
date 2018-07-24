package ImageHandel;




import GameCore.StaticVariables;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.jar.JarFile;

public class ImageLoader {
    ClassLoader classLoader = getClass().getClassLoader();

    public BufferedImage loadImage(String path) {
        try {


            return ImageIO.read(classLoader.getResourceAsStream(path));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(StaticVariables.mainClass, e.getStackTrace());

            e.printStackTrace();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(StaticVariables.mainClass, e.getStackTrace());
            e.printStackTrace();

        }
        return null;
    }

    public void addImageOfObject(int type, String dir, ArrayList<ImageIcon> linkedList, Dimension size) {
        Image image;
        String dirc;




            for (int i = 0; i<type; i++) {
                try {
                    dirc = dir + i + ".png";
                    image = loadImage(dirc);
                    linkedList.add(new ImageIcon(image.getScaledInstance(size.width, size.height, 4)));
                } catch (NullPointerException e) {

                    break;
                }

            }


    }




}

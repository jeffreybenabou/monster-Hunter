package ImageHandel;


import GameCore.StaticVariables;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageLoader {
    private ClassLoader classLoader = ImageLoader.class.getClassLoader();
    private File file;

    public BufferedImage loadImage(String path) {
        try {

             file = new File(classLoader.getResource(path).toURI());

            return             ImageIO.read(file);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(StaticVariables.mainClass, e.getStackTrace());

            e.printStackTrace();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(StaticVariables.mainClass, e.getStackTrace());
            e.printStackTrace();

        }
        return null;
    }

    public void addImageOfObject(int type, String dir, ArrayList<ImageIcon> linkedList) {
        BufferedImage image;
        String dirc;




            for (int i = 0; i<type; i++) {
                try {
                    dirc = dir + i + ".png";
                    image = loadImage(dirc);
                    linkedList.add(new ImageIcon(image));

                } catch (NullPointerException e) {

                    break;
                }

            }


    }




}

package ImageHandel;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class ImageLoader {
    private ClassLoader classLoader = ImageLoader.class.getClassLoader();

    public BufferedImage loadImage(String path) {
        try {


            File file = new File(classLoader.getResource(path).toURI());

            return             ImageIO.read(file);

        } catch (IOException e) {


            e.printStackTrace();

        } catch (Exception e) {

            e.printStackTrace();

        }
        return null;
    }

    public synchronized void addImageOfObject(int type, String dir, LinkedList<ImageIcon> linkedList) {
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

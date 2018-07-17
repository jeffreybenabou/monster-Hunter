package ImageHandel;




import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class ImageLoader {
    public static BufferedImage loadImage(String path){
        try {


            return ImageIO.read(ImageLoader.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();

        }
    return null;
    }

    public    static void addImageOfObject(File dir, ArrayList<Image> linkedList,Vector<Image>vector, Dimension size) {
        String ImagePath;
        for (int i = 0; dir.listFiles().length > i; i++)
        {

            try
            {
                ImagePath = dir.getPath() + "\\" + i + ".png";
                ImagePath = ImagePath.replace("\\", "/");
                if(vector==null)
                linkedList.add(new ImageIcon(ImagePath).getImage().getScaledInstance(size.width,size.height,Image.SCALE_SMOOTH));
                else
                    vector.add(new ImageIcon(ImagePath).getImage().getScaledInstance(size.width,size.height,Image.SCALE_SMOOTH));

            }catch (NullPointerException e)
            {
                e.printStackTrace();
            }

        }
    }

}

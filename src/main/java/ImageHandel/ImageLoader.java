package ImageHandel;




import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    public  static void addImageOfObject(File dir, Vector<Image> linkedList, Dimension size) {
        String ImagePath;
        for (int i = 0; dir.listFiles().length > i; i++)
        {

            try
            {
                ImagePath = dir.getPath() + "\\" + i + ".png";
                ImagePath = ImagePath.replace("\\", "/");
                linkedList.add(new ImageIcon(ImagePath).getImage().getScaledInstance(size.width,size.height,Image.SCALE_FAST));

            }catch (NullPointerException e)
            {
                e.printStackTrace();
            }

        }
    }

}

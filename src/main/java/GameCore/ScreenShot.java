package GameCore;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class ScreenShot {
   private Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    private BufferedImage capture;
    private File file;
    private String path;




ScreenShot(final String path){
this.path=path;


new Thread(new Runnable() {
    public void run() {
        try {
            capture = new Robot().createScreenCapture(screenRect);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        try {


            file = new File((path));
            Thread.sleep(100);
            ImageIO.write(capture, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}).start();




}
}

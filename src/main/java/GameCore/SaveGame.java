package GameCore;

import Objects.Ghost;
import Objects.MainPlayer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveGame {

    public SaveGame(String path,String pathToImage) {
        new ScreenShot(pathToImage);

        File file = new File(path);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(StaticVariables.mainPlayer.getX() + "");
            writer.newLine();
            writer.write(StaticVariables.mainPlayer.getY() + "");
            writer.newLine();
            writer.write(StaticVariables.world.getX() + "");
            writer.newLine();
            writer.write(StaticVariables.world.getY() + "");
            writer.newLine();
            writer.write(MainPlayer.getLife().getjProgressBar().getValue() + "");
            writer.newLine();
            writer.write(GamePanel.getjProgressBar().getValue()+ "");
            writer.newLine();
            writer.write(StaticVariables.sumOfMoney+ "");
            writer.newLine();
            writer.write(StaticVariables.level+ "");
            writer.newLine();
            writer.write(Ghost.notTheFirstGhost+ "");
            writer.newLine();
            writer.write(Key.key1+ "");
            writer.newLine();
            writer.write(Key.key2+ "");
            writer.newLine();
            writer.write(Key.key3+ "");
            writer.newLine();
            writer.write(World.userInProgressToOpenHouse+ "");
            writer.newLine();
            writer.write( "");
            writer.newLine();
            writer.write(Ghost.difficulty+ "");
            writer.newLine();


            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

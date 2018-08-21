package sound;


import ImageHandel.ImageLoader;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.util.ArrayList;

public class Sound {

    // TODO: 21/07/2018 fix the sound problem
    public static ArrayList<String> path=new ArrayList<String>();
    private  Clip  clip;
    private static boolean firstPlay=true;
    AudioInputStream inputStream;
    public Sound()
    {
        if (firstPlay)
        {
            path.add("sounds/Walksing.wav");
            path.add("sounds/axeattack.wav");
            path.add("sounds/backgroundOpen.wav");
            path.add("sounds/femaleAttack.wav");
            path.add("sounds/backgroundworld.wav");
            path.add("sounds/earthQueaqe.wav");
            path.add("sounds/buy.wav");
            path.add("sounds/coinFall.wav");
            path.add("sounds/error.wav");
            path.add("sounds/monsterAttack.wav");
            path.add("sounds/ghostdieing.wav");

            firstPlay=false;
        }
    }


    public void setVolume(float value)
    {
//        min=-80

//        max=6

        try
        {
            FloatControl gainControl;

            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(value);
        }catch (Exception e )
        {

        }




    }

    public void stopSound()
    {

        clip.stop();
    }

    public void startSound(){

        clip.start();
        if(!clip.isRunning())
        {
            clip.setFramePosition(0);
        }
    }

    public void playSound(final String url) {


                try {
                    if(clip==null)
                    {
                        clip = AudioSystem.getClip();
                         inputStream = AudioSystem.getAudioInputStream(ImageLoader.class.getClassLoader().getResource(url));
                        clip.open(inputStream);
                        clip.start();
                            clip.loop(Clip.LOOP_CONTINUOUSLY);

                    }



                } catch (Exception e) {
e.printStackTrace();
                }

    }

    public static ArrayList<String> getPath() {
        return path;
    }

    public static void setPath(ArrayList<String> path) {
        Sound.path = path;
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public static boolean isFirstPlay() {
        return firstPlay;
    }

    public static void setFirstPlay(boolean firstPlay) {
        Sound.firstPlay = firstPlay;
    }

    public AudioInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(AudioInputStream inputStream) {
        this.inputStream = inputStream;
    }
}

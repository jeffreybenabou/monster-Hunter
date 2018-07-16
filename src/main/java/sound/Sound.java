package sound;





import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.util.ArrayList;

public class Sound {

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
            firstPlay=false;
        }
    }


    public void stopSound(){
        try
        {
            if(clip!=null&&clip.isActive())
            {
                clip.flush();
                clip.stop();
            }

        }catch (NullPointerException e)
        {
            e.printStackTrace();

        }

    }
    public void setVolume(float value)
    {
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(value);

    }

    public void playSound(final String url, final boolean alwayesActive) {


                try {
                    if(clip==null)
                    {
                        clip = AudioSystem.getClip();
                         inputStream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream(url));
                        clip.open(inputStream);
                        clip.start();
                        if(alwayesActive)
                            clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                    else if(!clip.isActive())
                    {
                        clip = AudioSystem.getClip();
                        inputStream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream(url));
                        clip.open(inputStream);
                        clip.start();
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

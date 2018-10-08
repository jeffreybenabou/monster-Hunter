package Objects;

import BackgroundObject.House;
import GameCore.*;
import ImageHandel.ImageLoader;
import sound.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Ghost extends GameObject {

    private Integer ghostType, damgeToMainPlayer, damgeFromMainPlayer, life;


    private JLabel miniMapLabel;


    private Sound attackingSound, dieingSound;

    private static   ArrayList<ImageIcon>
            moveLeft,
            moveRight,
            moveUp,
            moveDown,
            attackLeft,
            attackRight,
            attackUp,
            attackDown,

    dead;
    public static   Integer numberOfDeadGhost = 0;

    public static int difficulty=0;

    private Life lifeBar;
    private boolean stopMoving = false;

    private boolean

            dirLeft,
            dirUp,
            dirRight,
            dirDown,
            isAttackLeft,
            isAttackRight,
            isAttackUp,
            isAttackDown,
            isAttackLeftDown,
            isAttackLeftUp,
            isAttackRightUp,
            isAttackRightDown;

    private Integer speed;
    public static   boolean notTheFirstGhost = false;
    private Integer index = 0;
    private Integer width_, height_;


    public Ghost(int ghostType) {
        this.ghostType = ghostType;

        attackingSound =new Sound();
        attackingSound.playSound(Sound.path.get(9));
        attackingSound.stopSound();

        dieingSound =new Sound();
        dieingSound.playSound(Sound.path.get(10));
        dieingSound.stopSound();

        setHorizontalAlignment(JLabel.CENTER);
        checkTheGhostType();
        setTheDir();
        setTheActionToGhost();
        addMouseListener(StaticVariables.world);


    }

    public void checkIfGhostIntersectHouse() {
        Random random = new Random();
        for (House house : StaticVariables.world.getHouseArrayList()) {

            switch (StaticVariables.level) {
                case 1: {
                    width_ = 250;
                    height_ = 300;
                    break;
                }
                case 2: {
                    width_ = 300;
                    height_ = 350;
                    break;
                }
                case 3: {
                    width_ = 400;
                    height_ = 450;
                    break;
                }
            }
            setBounds(new Rectangle(random.nextInt(StaticVariables.world.getWidth()), random.nextInt(StaticVariables.world.getHeight()), width_, height_));
            if (house.getBounds().intersects(getBounds())) {
                setBounds(1500, 1500, width_, height_);
            }


        }
    }

    public void setTheDir() {
        Random random = new Random();
        dirLeft = random.nextBoolean();
        dirUp = random.nextBoolean();
        dirRight = !dirLeft;

        dirDown = !dirUp;
    }

    public static void addTheVector() {
        moveLeft = new ArrayList<ImageIcon>();
        moveRight = new ArrayList<ImageIcon>();
        moveUp = new ArrayList<ImageIcon>();
        moveDown = new ArrayList<ImageIcon>();

        attackDown = new ArrayList<ImageIcon>();
        attackLeft = new ArrayList<ImageIcon>();
        attackRight = new ArrayList<ImageIcon>();
        attackUp = new ArrayList<ImageIcon>();

        dead = new ArrayList<ImageIcon>();


    }

    public void removeTheGhostWhenDead() {
        StaticVariables.world.remove(this);
        StaticVariables.world.repaint();
        StaticVariables.world.revalidate();
        new CoinAdd(getX(),getY());
        StaticVariables.miniMap.remove(miniMapLabel);
        StaticVariables.miniMap.repaint();
        StaticVariables.miniMap.revalidate();

        setVisible(false);
    }


    public void setTheActionToGhost() {
        new Thread(new Runnable() {
            public void run() {
                miniMapLabel=MiniMap.addGhostToMiniMap(getX(),getY());
                StaticVariables.miniMap.add(miniMapLabel);
                while (lifeBar.isAlive()) {
                    if(!StaticVariables.stopTheGame)
                    {
                        miniMapLabel.setLocation(getX() / 20, getY() / 32);

                        stopMoving = getBounds().intersects(StaticVariables.mainPlayer.getBounds());

                        if (notTheFirstGhost)
                            moveTheGhostAroundTheWorld();
                        else
                            changeTheGhostIcon();
                        decreaseLife(StaticVariables.mainPlayer.getDemageToGhost());

                    }


                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                setTheGhostDeadAnimation();

                removeTheGhostWhenDead();
            }
        }).start();
    }

    private void decreaseMainPlayerLife() {
        if(!MainPlayer.playerDead)
        {
            MainPlayer.life.getjProgressBar().setValue(MainPlayer.life.getjProgressBar().getValue() - damgeToMainPlayer);
            MainPlayer.life.getjProgressBar().setString("" + (MainPlayer.life.getjProgressBar().getValue() - damgeToMainPlayer));
            if(MainPlayer.life.getjProgressBar().getValue()<=0)
                MainPlayer.life.getjProgressBar().setString("Game Over");
        }



    }

    public void decreaseLife(int damage) {
        if (getBounds().intersects(StaticVariables.mainPlayer.getBounds()) && (MainPlayer.isAttacking() || MainPlayer.spacialAttack)) {


            getLifeBar().getjProgressBar().setValue(getLifeBar().getjProgressBar().getValue() - damage);
            getLifeBar().getjProgressBar().setString("" + getLifeBar().getjProgressBar().getValue());
        }
    }

    private void setTheGhostDeadAnimation() {
        index = 0;
        dieingSound.startSound();
        while (index < dead.size()) {
            setIcon(dead.get(index));
            index++;
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void moveTheGhostAroundTheWorld() {

        /*
         *
         * this method check all the direction of the cloud to check the dir to mov to .
         *
         * also check that the player is not getting out of the world bound .
         *
         * */
        try {
            for (House house : StaticVariables.world.getHouseArrayList()) {

                if (getBounds().intersects(house.getBounds())) {
                    switch (Integer.parseInt(house.getName())) {
                        case 1: {
                            setLocation(getX() + 10, getY()+10);
                            dirUp = true;
                            dirDown = false;
                            dirLeft = false;
                            dirRight = true;
                            break;
                        }
                        case 2: {
                            setLocation(getX() + 10, getY()+10);
                            dirUp = false;
                            dirDown = true;
                            dirLeft = false;
                            dirRight = true;
                            break;
                        }
                        case 3: {
                            setLocation(getX() - 10, getY()+10);
                            dirUp = false;
                            dirDown = true;
                            dirLeft = true;
                            dirRight = false;
                            break;
                        }

                    }

                }

            }
        } catch (Exception e) {

            e.printStackTrace();
        }


        if (dirDown)
            setLocation(getX(), getY() + speed);
        if (dirUp)
            setLocation(getX(), getY() - speed);
        if (dirLeft)
            setLocation(getX() - speed, getY());
        if (dirRight)
            setLocation(getX() + speed, getY());


        if (getX() > StaticVariables.world.getWidth() - getWidth()) {
            dirRight = false;
            dirLeft = true;
        }
        if (getX() < 0) {
            dirRight = true;
            dirLeft = false;
        }
        if (getY() > StaticVariables.world.getHeight() - getHeight()) {
            dirDown = false;
            dirUp = true;
        }
        if (getY() < 0) {
            dirDown = true;
            dirUp = false;
        }


        changeTheGhostIcon();


    }

    public static   void addGhostImage() {
        addTheVector();
        String pathOfFile="";
        ImageLoader imageLoader;

        switch (StaticVariables.level) {
            case 1: {
                pathOfFile = "firstGhost";

                break;
            }
            case 2: {
                pathOfFile = "secondeGhost";

                break;
            }
            case 3: {
                pathOfFile = "thirdGhost";

                break;
            }
        }

        imageLoader = new ImageLoader();

        String DIR_1 = "Photos/ghost/" + pathOfFile + "/attack/attack_down/";
        if (pathOfFile.equals("firstGhost"))
            imageLoader.addImageOfObject(42, DIR_1, attackDown);
        else
            imageLoader.addImageOfObject(48, DIR_1, attackDown);

        DIR_1 = "Photos/ghost/" + pathOfFile + "/attack/attack_left/";
        if (pathOfFile.equals("firstGhost"))
            imageLoader.addImageOfObject(42, DIR_1, attackLeft);
        else
            imageLoader.addImageOfObject(48, DIR_1, attackLeft);


        DIR_1 = "Photos/ghost/" + pathOfFile + "/attack/attack_right/";
        if (pathOfFile.equals("firstGhost"))

            imageLoader.addImageOfObject(42, DIR_1, attackRight);
        else
            imageLoader.addImageOfObject(48, DIR_1, attackRight);


        DIR_1 = "Photos/ghost/" + pathOfFile + "/attack/attack_up/";
        if (pathOfFile.equals("firstGhost"))

            imageLoader.addImageOfObject(42, DIR_1, attackUp);
        else
            imageLoader.addImageOfObject(48, DIR_1, attackUp);


        DIR_1 = "Photos/ghost/" + pathOfFile + "/walk/walk_up/";
        imageLoader.addImageOfObject(42, DIR_1, moveUp);


        DIR_1 = "Photos/ghost/" + pathOfFile + "/walk/walk_right/";
        imageLoader.addImageOfObject(42, DIR_1, moveRight);


        DIR_1 = "Photos/ghost/" + pathOfFile + "/walk/walk_left/";
        imageLoader.addImageOfObject(42, DIR_1, moveLeft);


        DIR_1 = "Photos/ghost/" + pathOfFile + "/walk/walk_down/";
        imageLoader.addImageOfObject(42, DIR_1, moveDown);


        DIR_1 = "Photos/ghost/" + pathOfFile + "/dead/";
        imageLoader.addImageOfObject(40, DIR_1, dead);


    }


    private void changeTheGhostIcon() {
        try {
            if (index < moveLeft.size()-1) {
                if (stopMoving) {
                    if(!GamePanel.muteActive)
                    attackingSound.startSound();
                    dirLeft = false;
                    dirDown = false;
                    dirUp = false;
                    dirRight = false;
                    if (MainPlayer.isWalking())
                        setIcon(moveRight.get(index));
                    else if (StaticVariables.mainPlayer.isLeftFromTheGhost())
                        setIcon((attackLeft.get(index)));
                    else if (StaticVariables.mainPlayer.isDownFromTheGhost())
                        setIcon((attackDown.get(index)));
                    else if (StaticVariables.mainPlayer.isRightFromTheGhost())
                        setIcon((attackRight.get(index)));
                    else if (StaticVariables.mainPlayer.isUpFromTheGhost())
                        setIcon((attackUp.get(index)));

                    decreaseMainPlayerLife();


                } else {

                    if (!dirUp && !dirDown && !dirLeft && !dirRight)
                        setTheDir();

                    if (dirLeft)
                        setIcon((moveLeft.get(index)));
                    if (dirRight)
                        setIcon((moveRight.get(index)));
                    if (dirUp)
                        setIcon((moveUp.get(index)));
                    if (dirDown)
                        setIcon((moveDown.get(index)));
                }


            } else {
                index = 0;
            }


            index++;
        } catch (Exception e) {
            e.printStackTrace();

        }


    }


    private void checkTheGhostType() {
        Random random = new Random();
        switch (ghostType) {
            case 1: {
                setSize(250, 300);
                damgeToMainPlayer = 2 * random.nextInt(2) + 1;
                life = 2000;
                speed = 1;
                break;
            }
            case 2: {


                setSize(300, 350);
                damgeToMainPlayer = 3 * random.nextInt(2) + 1;
                life = 3500;
                speed = 2;
                break;
            }
            case 3: {

                setSize(400, 450);
                damgeToMainPlayer = 4 * random.nextInt(2) + 1;
                life = 5500;
                speed = 3;

                break;
            }
        }

        damgeToMainPlayer+=difficulty;
        life+=difficulty*250;
        lifeBar = new Life(life, this);
        add(lifeBar.getjProgressBar());



    }



    public Integer getGhostType() {
        return ghostType;
    }

    public void setGhostType(Integer ghostType) {
        this.ghostType = ghostType;
    }

    public Integer getDamgeToMainPlayer() {
        return damgeToMainPlayer;
    }

    public void setDamgeToMainPlayer(Integer damgeToMainPlayer) {
        this.damgeToMainPlayer = damgeToMainPlayer;
    }

    public Integer getDamgeFromMainPlayer() {
        return damgeFromMainPlayer;
    }

    public void setDamgeFromMainPlayer(Integer damgeFromMainPlayer) {
        this.damgeFromMainPlayer = damgeFromMainPlayer;
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }


    public Life getLifeBar() {
        return lifeBar;
    }

    public void setLifeBar(Life lifeBar) {
        this.lifeBar = lifeBar;
    }

    public boolean isStopMoving() {
        return stopMoving;
    }

    public void setStopMoving(boolean stopMoving) {
        this.stopMoving = stopMoving;
    }

    public boolean isDirLeft() {
        return dirLeft;
    }

    public void setDirLeft(boolean dirLeft) {
        this.dirLeft = dirLeft;
    }

    public boolean isDirUp() {
        return dirUp;
    }

    public void setDirUp(boolean dirUp) {
        this.dirUp = dirUp;
    }

    public boolean isDirRight() {
        return dirRight;
    }

    public void setDirRight(boolean dirRight) {
        this.dirRight = dirRight;
    }

    public boolean isDirDown() {
        return dirDown;
    }

    public void setDirDown(boolean dirDown) {
        this.dirDown = dirDown;
    }

    public boolean isAttackLeft() {
        return isAttackLeft;
    }

    public void setAttackLeft(boolean attackLeft) {
        isAttackLeft = attackLeft;
    }

    public boolean isAttackRight() {
        return isAttackRight;
    }

    public void setAttackRight(boolean attackRight) {
        isAttackRight = attackRight;
    }

    public boolean isAttackUp() {
        return isAttackUp;
    }

    public JLabel getMiniMapLabel() {
        return miniMapLabel;
    }

    public void setMiniMapLabel(JLabel miniMapLabel) {
        this.miniMapLabel = miniMapLabel;
    }

    public Sound getAttackingSound() {
        return attackingSound;
    }

    public void setAttackingSound(Sound attackingSound) {
        this.attackingSound = attackingSound;
    }

    public Sound getDieingSound() {
        return dieingSound;
    }

    public void setDieingSound(Sound dieingSound) {
        this.dieingSound = dieingSound;
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(int difficulty) {
        Ghost.difficulty = difficulty;
    }

    public void setAttackUp(boolean attackUp) {
        isAttackUp = attackUp;
    }

    public boolean isAttackDown() {
        return isAttackDown;
    }

    public void setAttackDown(boolean attackDown) {
        isAttackDown = attackDown;
    }

    public boolean isAttackLeftDown() {
        return isAttackLeftDown;
    }

    public void setAttackLeftDown(boolean attackLeftDown) {
        isAttackLeftDown = attackLeftDown;
    }

    public boolean isAttackLeftUp() {
        return isAttackLeftUp;
    }

    public void setAttackLeftUp(boolean attackLeftUp) {
        isAttackLeftUp = attackLeftUp;
    }

    public boolean isAttackRightUp() {
        return isAttackRightUp;
    }

    public void setAttackRightUp(boolean attackRightUp) {
        isAttackRightUp = attackRightUp;
    }

    public boolean isAttackRightDown() {
        return isAttackRightDown;
    }

    public void setAttackRightDown(boolean attackRightDown) {
        isAttackRightDown = attackRightDown;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }


    public ArrayList<ImageIcon> getMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(ArrayList<ImageIcon> moveLeft) {
        this.moveLeft = moveLeft;
    }

    public ArrayList<ImageIcon> getMoveRight() {
        return moveRight;
    }

    public void setMoveRight(ArrayList<ImageIcon> moveRight) {
        this.moveRight = moveRight;
    }

    public ArrayList<ImageIcon> getMoveUp() {
        return moveUp;
    }

    public void setMoveUp(ArrayList<ImageIcon> moveUp) {
        this.moveUp = moveUp;
    }

    public ArrayList<ImageIcon> getMoveDown() {
        return moveDown;
    }

    public void setMoveDown(ArrayList<ImageIcon> moveDown) {
        this.moveDown = moveDown;
    }

    public ArrayList<ImageIcon> getAttackLeft() {
        return attackLeft;
    }

    public void setAttackLeft(ArrayList<ImageIcon> attackLeft) {
        this.attackLeft = attackLeft;
    }

    public ArrayList<ImageIcon> getAttackRight() {
        return attackRight;
    }

    public void setAttackRight(ArrayList<ImageIcon> attackRight) {
        this.attackRight = attackRight;
    }

    public ArrayList<ImageIcon> getAttackUp() {
        return attackUp;
    }

    public void setAttackUp(ArrayList<ImageIcon> attackUp) {
        this.attackUp = attackUp;
    }

    public ArrayList<ImageIcon> getAttackDown() {
        return attackDown;
    }

    public void setAttackDown(ArrayList<ImageIcon> attackDown) {
        this.attackDown = attackDown;
    }

    public ArrayList<ImageIcon> getDead() {
        return dead;
    }

    public void setDead(ArrayList<ImageIcon> dead) {
        this.dead = dead;
    }

    public Integer getNumberOfDeadGhost() {
        return numberOfDeadGhost;
    }

    public void setNumberOfDeadGhost(Integer numberOfDeadGhost) {
        this.numberOfDeadGhost = numberOfDeadGhost;
    }

    public boolean isNotTheFirstGhost() {
        return notTheFirstGhost;
    }

    public void setNotTheFirstGhost(boolean notTheFirstGhost) {
        this.notTheFirstGhost = notTheFirstGhost;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getWidth_() {
        return width_;
    }

    public void setWidth_(Integer width_) {
        this.width_ = width_;
    }

    public Integer getHeight_() {
        return height_;
    }

    public void setHeight_(Integer height_) {
        this.height_ = height_;
    }
}

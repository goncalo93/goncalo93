package org.academiadecodigo.game;

import org.academiadecodigo.game.Splitables.Salmon;
import org.academiadecodigo.game.direction.Direction;
import org.academiadecodigo.game.keyboardListener.KeyboardListener;
import org.academiadecodigo.game.movable.Player;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {

    private GameWin gameWin;
    private Loading loading;
    private Instructions instructions;
    private Player player;
    private Picture background;
    private int score;
    private Text scoreDisplay;
    private int counter = 0;
    private int randomY;
    private int randomX;
    private int numberOfSalmons = 8;
    private int kills;
    private Picture transitionLevel;

    private Salmon[] salmon1 = new Salmon[numberOfSalmons];
    private int [] checkrandomY= new int[numberOfSalmons];

    public void showInstructions() throws InterruptedException {
        instructions = new Instructions();
        Thread.sleep(12000);
        loadingToGame();
    }


    public void loadingToGame() throws InterruptedException {
        loading = new Loading();
        Thread.sleep(2500);
        init();
    }
    public void init() throws InterruptedException {
        background = generateBackground();
        background.draw();

        createSalmons();

        showScore();


        player = new Player();
        player.setKnifesRemaining(10);

        new KeyboardListener(player, KeyboardEvent.KEY_RIGHT, KeyboardEvent.KEY_LEFT, KeyboardEvent.KEY_SPACE);

        start();

    }

    public void start() throws InterruptedException {
        while ((kills < salmon1.length) && (player.getKnifesRemaining() > -1)) {
            moveObjects();
            moveSalmon();
            Thread.sleep(GameConstants.DELAY);
        }
        if ((kills == 8 && player.getKnifesRemaining() > -1)) {
            Game2 nextlevel = new Game2();
            transitionLevel = new Picture(GameConstants.PADDING, GameConstants.PADDING, "resources/transitionLevel.jpeg");
            transitionLevel.draw();
            Thread.sleep(2500);
            nextlevel.init();
            transitionLevel.delete();

        } else {
            gameOver().draw();
            background.delete();
            Thread.sleep(2500);
            System.exit(0);
        }
    }


    private void moveObjects() {
        player.move();

    }

    private void moveSalmon(){
        int counter = 0;
        while(counter < numberOfSalmons) {
            salmon1[counter].move();
            if (player.checkBulletHit(salmon1[counter].getPos())) {

                salmon1[counter].split();
                kills++;
                updateScore(100);


            }
            counter++;
        }

    }

    private Picture generateBackground(){
        return new Picture(GameConstants.PADDING, GameConstants.PADDING, GameConstants.LEVEL_1_IMAGE);

    }

    private Picture gameOver(){
        return new Picture(GameConstants.PADDING,GameConstants.PADDING, "resources/finalgameover.jpeg");
    }

    public void showScore() {
        scoreDisplay = new Text(GameConstants.PADDING * 0.4 + GameConstants.GAME_WIDTH *0.1 ,
                GameConstants.PADDING + GameConstants.GAME_HEIGHT *0.85 ,"Score: " + score);
        scoreDisplay.setColor(Color.WHITE);
        scoreDisplay.grow(60,30);
        scoreDisplay.draw();
    }

    public void updateScore(int points) {
        score = score + points;
        scoreDisplay.setText("Score: " + score);
    }

    public void createSalmons(){
        while (counter < numberOfSalmons) {
            int num = 20;

            randomX = (int) (Math.random() * (GameConstants.GAME_WIDTH - 200));

            for (int i = 0; i < salmon1.length; i++) {
                randomY = num;
                checkrandomY[i] = randomY;
                num = num + 45;
            }

            salmon1[counter] = new Salmon(randomX, checkrandomY[counter], Direction.RIGHT);
            counter++;
        }


    }





}

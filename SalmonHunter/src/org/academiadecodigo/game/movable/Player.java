package org.academiadecodigo.game.movable;

import org.academiadecodigo.game.GameConstants;
import org.academiadecodigo.game.Position;
import org.academiadecodigo.game.direction.Direction;
import org.academiadecodigo.game.movable.mechanics.KnifeMechanics;
import org.academiadecodigo.game.movable.mechanics.Mechanics;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player implements Movable {

    private Direction dir;

    private Mechanics mechanics;
    private Position pos;
    private int width = GameConstants.PLAYER_WIDTH;
    private int height = GameConstants.PLAYER_HEIGHT;

    private Text knifesRemainingText;
    private int knifesRemaining;




    public Player() {
        pos = new Position((GameConstants.PADDING + (GameConstants.GAME_WIDTH / 2 - width / 2)),
                (GameConstants.PADDING + (GameConstants.GAME_HEIGHT - height)),
                width, height, "resources/sushimanprov.png");
         //A new player allways starts with 10 knifes.
        knifesRemainingText = new Text(GameConstants.PADDING + GameConstants.GAME_WIDTH * 0.88,
                GameConstants.PADDING + GameConstants.GAME_HEIGHT * 0.80, "Knifes Remaining:" + knifesRemaining );
        knifesRemainingText.setColor(Color.WHITE);
        knifesRemainingText.grow(30,10);
        knifesRemainingText.draw();
        this.knifesRemaining = knifesRemaining;

    }

    public void setKnifesRemaining(int knifesRemaining) {
        this.knifesRemaining = knifesRemaining;
    }

    public int getKnifesRemaining() {
        return knifesRemaining;
    }

    public void shoot() {

        if (mechanics != null) {
            return;
        }

        int x = pos.getX() + width / 2 - GameConstants.BULLET_WIDTH / 2;
        int y = (GameConstants.GAME_HEIGHT -70) - GameConstants.BULLET_GROWTH_SPEED + GameConstants.PADDING;

        mechanics = new KnifeMechanics(x,y);
        knifesRemaining--;
        knifesRemainingText.setText("Knifes remaining " + this.knifesRemaining);

        if (knifesRemaining <= 0) {
            //game over if there are still salmons left
            // next level/end of game if you killed the last salmon in the last knife
            knifesRemainingText.setText("Out of knifes.");
        }

    }

    public boolean checkHit(Position pos) {
        return this.pos.overlaps(pos) || pos.overlaps(this.pos);
    }

    public boolean checkBulletHit(Position salmon) {

        if (mechanics == null) {
            return false;
        }

        if (mechanics.checkHit(salmon)) {
            mechanics.delete();
            mechanics = null;
            return true;
        }

        return false;
    }

    public void deleteBullet() {

        if (mechanics == null) {
            return;
        }

        mechanics.delete();
        mechanics = null;
    }



    public void setDirection(Direction dir) {
        this.dir = dir;
    }


    @Override
    public void move() {

        if (dir != null) {

            switch (dir) {
                case RIGHT:
                    moveRight();
                    break;

                case LEFT:
                    moveLeft();
            }
        }

        if (mechanics == null) {
            return;
        }

        if (mechanics.checkEndingPoint()) {

            mechanics.delete();
            mechanics = null;
            return;
        }

        mechanics.move();

    }

    private void moveRight() {
        if (pos.getX() + width + GameConstants.PLAYER_SPEED < GameConstants.GAME_WIDTH + GameConstants.PADDING) {
            pos.translate(GameConstants.PLAYER_SPEED, 0);
        }
    }

    private void moveLeft() {
        if (pos.getX() - GameConstants.PLAYER_SPEED > GameConstants.PADDING) {
            pos.translate(-GameConstants.PLAYER_SPEED, 0);
        }
    }

    public Position getPos() {
        return pos;
    }

    public void resetPos() {

        pos = new Position((GameConstants.PADDING + (GameConstants.GAME_WIDTH / 2 - width / 2)), (GameConstants.PADDING + (GameConstants.GAME_HEIGHT - height)), width, height, "resources/player.png");
    }







}

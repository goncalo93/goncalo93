package org.academiadecodigo.game.Splitables;

import org.academiadecodigo.game.GameConstants;
import org.academiadecodigo.game.Position;
import org.academiadecodigo.game.direction.Direction;

public class Salmon implements Splitable{

    private Position pos;

    private Direction horizontalDirection;
    private Direction verticalDirection;
    private int speed;
    private int jumpUp;
    private int flatline;

    public Salmon(int x, int y, Direction dir) {
        speed = 5;

        pos = new Position(x,y, 130,9,"resources/salmon2.png");
        horizontalDirection = dir;
        verticalDirection = Direction.UP;

        jumpUp = 30;
        if (y > GameConstants.PADDING + GameConstants.GAME_HEIGHT - ((GameConstants.PLAYER_HEIGHT + GameConstants.BALL_MIN_SIZE + 20) * 4)) {
            jumpUp = 0;
        }
        flatline = 0;
    }

    @Override
    public void split() {
        pos.delete();


    }

    @Override
    public void move() {
        if (checkHorizontalBoundaries()) {
            horizontalDirection = horizontalDirection.getOpposite();
        }

        if (checkVerticalBoundaries()) {
            verticalDirection = verticalDirection.getOpposite();

            if (verticalDirection == Direction.UP) {
                int currentPosY = pos.getY();
                int maxY = GameConstants.GAME_HEIGHT + GameConstants.PADDING - 24;

                pos.translate(0, maxY - currentPosY);
                return;
            }

            flatline = 12;

        }

        if (jumpUp > 0) {
            speed = 1;
            jumpUp--;
            verticalDirection = Direction.UP;
        }

        else if (flatline > 0 && jumpUp == 0) {
            speed = 0;
            flatline--;
        }



        switch (verticalDirection) {

            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
        }

        if (horizontalDirection == null) {
            return;
        }

        switch (horizontalDirection) {

            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();
                break;
            default:
                break;
        }

    }

    private void moveUp() {
        pos.translate(0, speed * -1);
    }

    private void moveRight() {
        pos.translate(4, 0);
    }

    private void moveDown() {
        pos.translate(0, speed);
    }

    private void moveLeft() {
        pos.translate(-4, 0);
    }

    private void stop() {pos.translate(0,0);}

    private boolean checkHorizontalBoundaries() {
        return (horizontalDirection == Direction.RIGHT && pos.getX() + pos.getWidth() >= GameConstants.GAME_WIDTH + GameConstants.PADDING - 25) ||
                (horizontalDirection == Direction.LEFT && pos.getX() <= GameConstants.PADDING);
    }

    private boolean checkVerticalBoundaries() {

        return (verticalDirection == Direction.UP && pos.getY() <= GameConstants.PADDING + GameConstants.GAME_HEIGHT - ((GameConstants.PLAYER_HEIGHT + GameConstants.BALL_MIN_SIZE - 220) * 3) ||
                (verticalDirection == Direction.DOWN && pos.getY() + 24 + speed >= GameConstants.GAME_HEIGHT + GameConstants.PADDING));
    }

    @Override
    public Position getPos() {
        return pos;
    }
}

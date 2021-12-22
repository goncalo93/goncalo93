package org.academiadecodigo.game.movable.mechanics;

import org.academiadecodigo.game.GameConstants;
import org.academiadecodigo.game.Position;
import org.academiadecodigo.game.direction.Direction;

public class SalmonMechanics implements Mechanics {

    private Position pos;
    private Direction horizontalDirection;
    private Direction verticalDirection;
    private boolean over = false;
    private int speed = 15;

    public SalmonMechanics(int x, int y) {
        pos = new Position(x, y, 25, 25, "resources/sun.png");

        verticalDirection = Direction.UP;
        horizontalDirection = Direction.RIGHT;
    }

    @Override
    public void move() {

        if (checkVerticalBoundaries()) {
            verticalDirection = verticalDirection.getOpposite();
        }

        if (checkHorizontalBoundaries()) {
            horizontalDirection = horizontalDirection.getOpposite();
        }


        switch (horizontalDirection) {
            case LEFT:
                pos.translate(-speed, 0);
                break;

            case RIGHT:
                pos.translate(speed / 2, 0);
        }

        switch (verticalDirection) {
            case UP:
                pos.translate(0, -speed / 2);
                break;

            case DOWN:
                pos.translate(0, speed);
                break;
        }

    }

    private boolean checkVerticalBoundaries() {
        return (verticalDirection == Direction.UP && pos.getY() - speed <= GameConstants.PADDING) ||
                (verticalDirection == Direction.DOWN && pos.getY() + pos.getHeight() + speed >= GameConstants.PADDING + GameConstants.GAME_HEIGHT);
    }

    private boolean checkHorizontalBoundaries() {
        return (horizontalDirection == Direction.LEFT && pos.getX() - speed <= GameConstants.PADDING) ||
                (horizontalDirection == Direction.RIGHT && pos.getX() + pos.getWidth() + speed >= GameConstants.PADDING + GameConstants.GAME_WIDTH);
    }

    @Override
    public Position getPos() {
        return pos;
    }

    @Override
    public boolean checkHit(Position pos) {
        boolean hit = this.pos.overlaps(pos) || pos.overlaps(this.pos);

        over = over || hit;
        return hit;
    }

    @Override
    public void delete() {
        pos.delete();
    }


    @Override
    public boolean checkEndingPoint() {
        return over;
    }

}

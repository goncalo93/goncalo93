package org.academiadecodigo.game.movable.mechanics;

import org.academiadecodigo.game.GameConstants;
import org.academiadecodigo.game.Position;

public class KnifeMechanics implements Mechanics{

    private Position pos;

    public KnifeMechanics(int x, int y){
        pos = new Position(x,y,50,9,"resources/knife.png");
    }


    @Override
    public boolean checkHit(Position pos) {
        return this.pos.overlaps(pos) || pos.overlaps(this.pos);
    }

    @Override
    public void delete() {
        pos.delete();
    }

    @Override
    public boolean checkEndingPoint() {
        return pos.getY() <= GameConstants.PADDING ;
    }

    @Override
    public void move() {
        pos.translate(0,-10);
    }

    @Override
    public Position getPos() {
        return pos;
    }
}

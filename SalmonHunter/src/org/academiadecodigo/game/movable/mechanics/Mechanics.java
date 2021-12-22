package org.academiadecodigo.game.movable.mechanics;

import org.academiadecodigo.game.Position;
import org.academiadecodigo.game.movable.Movable;

public interface Mechanics extends Movable{

    boolean checkHit(Position pos);
    void delete();
    boolean checkEndingPoint();

}

package org.academiadecodigo.game;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class GameWin {

    private Picture pic;

    public GameWin() {
        pic = new Picture(GameConstants.PADDING, GameConstants.PADDING, "resources/gamewin.png");
        pic.draw();
    }
}

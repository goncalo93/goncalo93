package org.academiadecodigo.game;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Instructions {

    private Picture pic;

    public Instructions() {
        pic = new Picture(GameConstants.PADDING, GameConstants.PADDING, "resources/instructions.png");
        pic.draw();
    }

}

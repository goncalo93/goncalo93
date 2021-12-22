package org.academiadecodigo.game;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Loading {

    private Picture pic;

    public Loading() {
        pic = new Picture(GameConstants.PADDING, GameConstants.PADDING, "resources/loading.png");
        pic.draw();

    }
}

package org.academiadecodigo.game;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Game g = new Game();
        Sound.play("resources/main.wav");

        g.showInstructions();
    }

}

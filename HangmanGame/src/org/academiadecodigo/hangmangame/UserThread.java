package org.academiadecodigo.hangmangame;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class UserThread extends Thread {

    private Socket socket;
    private Server server;

    private InputStream input;
    private OutputStream output;
    private PrintStream writer;
    private Prompt prompt;

    private Scanner keyboard;

    private StringInputScanner question;

    private List<String> user = new LinkedList<>();

    private String name;

    public UserThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {

            System.out.println("Entered Run");

            input = socket.getInputStream();
            output = socket.getOutputStream();
            writer = new PrintStream(output, true);
            prompt =  new Prompt(input, writer);
            keyboard = new Scanner(input);

            question = new StringInputScanner();
            question.setMessage("\n What is your name? ");

            name = prompt.getUserInput(question);

            writer.println("\n Hello " + name);

            server.addUserName(name);
            user.add(name);


            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            headerDesign();
                            //writer.println("\n" + AsciiReader.readAscii(70,89));
                            writer.println(" ");
                            writer.println("\n Waiting for other players to join.");
                        }
                    },
                    3000
            );

            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                printUsers();
                                begin();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    10000
            );

            String serverMessage = "\n New user connected: " + name;
            server.broadcast(serverMessage, this);

        } catch (IOException e) {
            System.out.println("Error in UserThread: " + e.getMessage());
            e.printStackTrace();
        }
    }

    void printUsers() {
        if (server.hasUsers()) {
            writer.println("\n Connected users: " + server.getUserNames());
        } else {
            writer.println("\n No other users connected");
        }
    }

    void sendMessage(String message) {
        writer.println(message);
    }

    public void begin() throws IOException {

        System.out.println("Began");

        String word;

        //Scanner scanner = new Scanner(new File("resources/words.txt"));
        List<String> words = new LinkedList<>();
        words.add("codebreak");
        words.add("inheritance");
        words.add("interface");
        words.add("composition");
        words.add("encapsulation");
        words.add("developer");
        words.add("concurrency");
        words.add("network");
        words.add("threadpool");
        words.add("synchronized");
        words.add("paradigm");
        words.add("immutability");
        words.add("declarative");
        words.add("imperative");
        words.add("functional");
        words.add("codigos");

        /*while (words.hasNext()) {
            words.add(scanner.nextLine());
        }

         */

        Random rand = new Random();
        word = words.remove(rand.nextInt(words.size()));


        List<Character> playerGuesses = new ArrayList<>();

        Integer wrongCount = 0;

        while (true) {

            printHangedMan(wrongCount);

            if (wrongCount >= 6) {
                writer.println("\n You lose!");
                writer.println("\n The word was: " + word);
                String message = "\n User " + name + " has lost! ";
                server.broadcast(message, this);
                server.removeUser(name,this);
                System.out.println("Lose " + server.getUserNames());
                socket.close();
                break;
            }

            printWordState(word, playerGuesses);
            if (!getPlayerGuess(writer, word, playerGuesses)) {
                wrongCount++;
            }

            if (printWordState(word, playerGuesses)) {
                writer.println("\n You win!");
                String message = "\n User " + user + " has won! ";
                server.broadcast(message, this);
                server.removeUser(name,this);
                System.out.println("Win " + server.getUserNames());
                socket.close();
                break;
            }

            writer.println("\n Please enter your guess for the word: ");
            if (keyboard.nextLine().equals(word)) {
                writer.println("\n You win!");
                String message = "\n User " + user + " has won! \n" + "\n But you can continue! \n ";
                server.broadcast(message, this);
                server.removeUser(name,this);
                System.out.println("Win " + server.getUserNames());
                socket.close();
                break;
            } else {
                writer.println("\n Nope! Try again.");
            }
        }
    }





    public void printHangedMan(Integer wrongCount) {

        if(wrongCount < 1) {
            designForque();
            //writer.println(AsciiReader.readAscii(58, 68));
        }
        if (wrongCount == 1) {
            designCabeças();
            //writer.println(AsciiReader.readAscii(48, 57));
        }

        if (wrongCount == 2) {
            designTronque();
            //writer.println(AsciiReader.readAscii(37, 46));
        }

        if(wrongCount == 3){
            writer.println("\n I will give you this one for free! \n");
            //writer.println(AsciiReader.readAscii(37, 46));
            designTronque();
        }

        if (wrongCount == 4) {
           // writer.println(AsciiReader.readAscii(26, 35));
            designOneBrace();
        }

        if (wrongCount == 5) {
           // writer.print(AsciiReader.readAscii(14, 23));
            designTwoBrace();
        }
            if (wrongCount == 6) {
                //writer.println(AsciiReader.readAscii(2, 11));
                designTwoPerns();
            }
            else {
                writer.println("");
            }
        writer.println("");
        writer.println("");
    }

    public boolean getPlayerGuess(PrintStream writer, String word, List<Character> playerGuesses) {
        question = new StringInputScanner();
        question.setMessage("\n Please enter a letter: \n");
        String letterGuess = prompt.getUserInput(question);
        playerGuesses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }

    public boolean printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                writer.print(" " + word.charAt(i));
                correctCount++;
            }
            else {
                writer.print(" - ");
            }
        }
        writer.println();

        return (word.length() == correctCount);
    }

    public void headerDesign() {
        writer.print("               _______ _\n" +
                "                (_______| |\n" +
                "                 _      | | _   ____\n" +
                "                | |     | || \\ / _  )\n" +
                "                | |_____| | | ( (/ /\n" +
                "                 \\______|_| |_|\\____)\n" +
                "\n" +
                "       _     _                   ______\n" +
                "      | |   | |                 |  ___ \\\n" +
                "      | |__ | | ____ ____   ____| | _ | | ____ ____\n" +
                "      |  __)| |/ _  |  _ \\ / _  | || || |/ _  |  _ \\\n" +
                "      | |   | ( ( | | | | ( ( | | || || ( ( | | | | |\n" +
                "      |_|   |_|\\_||_|_| |_|\\_|| |_||_||_|\\_||_|_| |_|\n" +
                "                          (_____|\n" +
                "                ______\n" +
                "               / _____)\n" +
                "              | /  ___  ____ ____   ____\n" +
                "              | | (___)/ _  |    \\ / _  )\n" +
                "              | \\____/( ( | | | | ( (/ /\n" +
                "               \\_____/ \\_||_|_|_|_|\\____)" +
                "" +
                "");

    }

    public void designForque() {
        writer.print("                   ______________\n" +
                "                    |            |\n" +
                "                    |            |\n" +
                "                    |            \n" +
                "                    |            \n" +
                "                    |            \n" +
                "                    |         \n" +
                "                    |         \n" +
                "                ____|____\n" +
                "        ------------------------");
    }

    public void designCabeças() {
        writer.print("                   ______________\n" +
                "                    |            |\n" +
                "                    |            |\n" +
                "                    |            O\n" +
                "                    |            \n" +
                "                    |            \n" +
                "                    |         \n" +
                "                    |         \n" +
                "                ____|____\n" +
                "        ------------------------ ");
    }

    public void designTronque() {
        writer.print("                   ______________\n" +
                "                    |            |\n" +
                "                    |            |\n" +
                "                    |            O\n" +
                "                    |            |\n" +
                "                    |            |\n" +
                "                    |         \n" +
                "                    |         \n" +
                "                ____|____\n" +
                "        ------------------------ ");
    }

    public void designOneBrace() {
        writer.print("                   ______________\n" +
                "                    |            |\n" +
                "                    |            |\n" +
                "                    |            O\n" +
                "                    |        >---|\n" +
                "                    |            |\n" +
                "                    |         \n" +
                "                    |         \n" +
                "                ____|____\n" +
                "        ------------------------ ");
    }

    public void designTwoBrace() {
        writer.print("                   ______________\n" +
                "                    |            |\n" +
                "                    |            |\n" +
                "                    |            O\n" +
                "                    |        >---|---<\n" +
                "                    |            |\n" +
                "                    |           \n" +
                "                    |         \n" +
                "                ____|____\n" +
                "        ------------------------ ");
    }

    public void designTwoPerns() {
        writer.print("                   ______________\n" +
                "                    |            |\n" +
                "                    |            |\n" +
                "                    |            O\n" +
                "                    |        >---|---<\n" +
                "                    |            |\n" +
                "                    |           / \\\n" +
                "                    |         _/   \\_\n" +
                "                ____|____\n" +
                "        ------------------------ ");
    }




}


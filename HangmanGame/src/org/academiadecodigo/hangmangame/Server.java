package org.academiadecodigo.hangmangame;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    private int port;

    private List<String> userNames = new LinkedList<>();
    private List<UserThread> userThreads = new LinkedList<>();

    public Server (int port){
        this.port = port;
    }

    public void start() throws IOException {

            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Chat server started on port: " + port);


            while (true) {

                System.out.println(getUserNames().size());

                Socket socket = serverSocket.accept();
                System.out.println("New user connected");

                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                System.out.println(newUser);
                newUser.start();

            }
    }




    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = new Server(2445);
        server.start();

    }

    public void broadcast(String message, UserThread excludeUser){

        for(UserThread user : userThreads){
            if(user != excludeUser){
                user.sendMessage(message);
            }
        }

    }

    public void addUserName(String userName){
        userNames.add(userName);
    }

    public void removeUser(String userName, UserThread user){
        boolean removed = userNames.remove(userName);
        if(removed){
            userThreads.remove(user);
            System.out.println("The user " + userName + " has left");
        }

    }

    public List<String> getUserNames(){
        return this.userNames;
    }


    public boolean hasUsers(){
        return !this.userNames.isEmpty();
    }

}

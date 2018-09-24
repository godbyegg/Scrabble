package com;

import com.messages.Message;
import com.view.username.UsernameController;
import javafx.scene.Scene;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.StringTokenizer;

public class Listener extends Thread {

    private static boolean nameValid;
    private ObjectInputStream ois;
    public static String name;
    public static Message msg = null;
    public Listener(ObjectInputStream ois) {
        this.ois = ois;
    }

    @Override
    public void run(){
        try {
            //Read messages from the server while the end of the stream is not reached
            while((msg = (Message) ois.readObject()) != null) {
               //Print the messages to the console
                System.out.println(msg);
                switch (msg.getPlayerStatus()){
                    case SET_NAME:
                        if (msg.getFeedBackMessage().equals("ValidName")){
                            UsernameController.getInstance().showHall();
                        }
                        else{
                            //TODO show duplicate message.
                        }
                        break;
                }
                /*StringTokenizer st = new StringTokenizer(msg, "|");
                String operation = st.nextToken();
                String msg = st.nextToken();
                if (operation.equals("hallplayer")){
                    //TODO referesh playerlist
                }
                if (operation.equals("halltable")){
                    //TODO referesh table list
                }
                if (operation.equals("gameplay")){
                    //TODO referesh game playerlist
                }
                if (operation.equals("voting")){
                    Game.voting(msg);
                }
                if (operation.equals("gamestart")){
                    //TODO show game start message
                }
                if (operation.equals("turn")){
                    if (msg.equals("yourturn")){
                        Game.turn = true;
                    }
                    else{
                        Game.turn =false;
                    }
                }*/
            }
        } catch (SocketException e) {
            System.out.println("Socket closed because the user typed exit");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Message getMessage(){
        return msg;
    }
    public static boolean checkNameValid(){
        return nameValid;
    }

}
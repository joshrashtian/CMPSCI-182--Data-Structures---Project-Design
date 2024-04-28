package Project4;
/***************************************************************

Project Number 4 - Comp Sci 182 - Data Structures
Start Code - Build your program starting with this code

Snakes! I Hate Snakes!   -   Indiana Jones

Copyright 2003,2005 Christopher C. Ferguson
This code may only be used with the permission of Christopher C. Ferguson

***************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

import javax.swing.*;
import javax.swing.event.*;

import Universal.Log;

public class Project4 extends JFrame implements ActionListener {

    private static int xpos = 0, ypos = 0;// place window at this position
    private static int xsize = 700, ysize = 500;// set window to this size
    private boolean keyboard = false;

    // Private state variables.
    private RoomStackInterface roomStack = new RoomStackArray();
    private JPanel northPanel, centerPanel;
    private JButton pushButton, popButton, dumpButton, exitButton;
    private JTextField colorField;
    private JTextField codeField;
    private JTextArea outputArea;

    //////////// MAIN////////////////////////

    public static void main(String[] args) {
        Project4 tpo = new Project4();
    }

    //////////// CONSTRUCTOR/////////////////////

    public Project4() {
        addScreenComponents(); // put the stuff on the screen

        // Exit when the window is closed. i.e. when top right X box pressed
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setSize(xsize, ysize);
        setLocation(xpos, ypos);
        setVisible(true);

    }

    public void addScreenComponents() {

        northPanel = new JPanel();
        northPanel.add(new JLabel("Enter A Color: "));
        colorField = new JTextField("", 15);
        northPanel.add(colorField);
        northPanel.add(new JLabel("And A Code: "));
        codeField = new JTextField("", 5);
        northPanel.add(codeField);

        pushButton = new JButton("Push");
        northPanel.add(pushButton);
        pushButton.addActionListener(this);
        popButton = new JButton("Pop");
        northPanel.add(popButton);
        popButton.addActionListener(this);
        dumpButton = new JButton("Dump");
        northPanel.add(dumpButton);
        dumpButton.addActionListener(this);
        exitButton = new JButton("Exit");
        northPanel.add(exitButton);
        exitButton.addActionListener(this);

        getContentPane().add("North", northPanel);

        centerPanel = new JPanel();
        outputArea = new JTextArea("Who Dares Enter.... The Temple of Gloom!", 20, 60);
        centerPanel.add(outputArea);
        getContentPane().add(centerPanel, "Center");

    }
    //////////// BUTTON CLICKS ///////////////////////////

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == exitButton) {
            dispose();
            System.exit(0);
        }

        if (e.getSource() == popButton) {
            String newcolor = colorField.getText();
            int newcode = Integer.parseInt(codeField.getText());
            if(!newcolor.equals(roomStack.peek().getRoomColor()) || newcode != roomStack.peek().getRoomCode() ){
                Log.log("Invalid Code or Color!" + roomStack.peek().getRoomColor());
                outputArea.setText("Oops! You lost!");
                roomStack.empty();
                keyboard = false;
                return;
            }
            Log.log("popping off");
            Room i = roomStack.pop();

            if(roomStack.size() == 0 && keyboard) {
                outputArea.setText("Congrats! You have beaten the game!");
            } else {
                outputArea.setText("Pop returning to " + i.getRoomColor());
            }
            return;
            // add code to pop color off the stack, check that the color/code matches and
            // change to that color room
        }

        if (e.getSource() == pushButton) {
            String newcolor = colorField.getText();
            outputArea.setText("Push entering " + newcolor);
            // add code to push color/code ON the stack and change to that color room
            if (newcolor.equals(null) || codeField.getText().length() != 3)
                return;
            if(verifyRoom(colorField.getText())) {
                Log.log("does connect");
                roomStack.push(new Room(newcolor, Integer.parseInt(codeField.getText())));
            } else {
                Log.log("no connection");
                outputArea.setText("Died. No Connection");
                roomStack.empty();
                keyboard = false;

            }
        }

        if (e.getSource() == dumpButton) {
            System.out.println("Stack Contents Dump: ");
            // add code to print contents of Stack to the CONSOLE
            roomStack.dump();
        }

    }

    public boolean verifyRoom(String name) {
        /**From the green room, there are doors to the brown, pink and blue rooms.
         From the pink room, there are doors to the green, brown and blue rooms.
         From the brown room, there are doors to the pink, green and red rooms.
         From the blue room there are doors to the green, pink and yellow rooms
         From the red room, there are doors to the brown and yellow rooms.
         From the yellow room there are doors to the red, blue and gold rooms.
         From the gold room, there is a door to the yellow room.**/
        String[][] rooms = {{"green", "brown", "pink", "blue"}, {"pink", "green", "brown", "blue"}, {"brown", "pink", "green", "pink", "yellow"},
                {"blue", "green", "pink", "yellow"}, {"red", "brown", "yellow"}, {"yellow", "red", "blue", "gold"}, {"gold", "yellow"}
        };

        if(roomStack.peek() == null) {
            return true;
        }

        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i][0].equals(roomStack.peek().getRoomColor())) {
                for (int j = 0; j < rooms[i].length; j++) {
                    if (rooms[i][j].equals(name)) {
                        if(name.equals("gold")){
                            if(roomStack.size() < 2) return false;
                            outputArea.setText("You have recieved the golden keyboard. Currently, you are at room gold.");
                            keyboard = true;
                        }
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    public void changeCourse() {

    }

    public class Room {
        private String roomColor;
        private int roomCode;

        Room() {

        }

        Room(String color, int code) {
            roomCode = code;
            roomColor = color;
        }

        public void setRoomColor(String color) {
            roomColor = color;
        }

        public String getRoomColor() {
            return roomColor;
        }

        public void setRoomCode(int code) {
            roomCode = code;
        }

        public int getRoomCode() {
            return roomCode;
        }

        public String toString() {
            return "ROOM: " + roomColor + " | CODE: " + roomCode;
        }
    }

    interface RoomStackInterface {
        void push(Room obj);

        Room pop();

        Room peek();

        int size();

        void dump();

        void empty();
    }

    public class RoomStackArray implements RoomStackInterface {
        private int length = 0;
        private Room[] rooms = new Room[10];

        public RoomStackArray() {

        }

        public RoomStackArray(Room[] Rooms) {
            for (int i = 0; i < Rooms.length; i++) {
                this.push(Rooms[i]);
            }
        }

        public void push(Room obj) {
            Log.log(obj.toString());
            rooms[length] = obj;
            length++;
        }

        public Room pop() {
            Room temp = rooms[length-1];
            rooms[length] = null;
            length--;
            return temp;
        }

        public Room peek() {
            if(length == 0){
                return null;
            } else {
                return rooms[length - 1];
            }
        }

        public void empty() {
            Log.log("Emptied!");

            for(int i = 0; i < length; i++) {
                rooms[i] = null;

            }
            length = 0;
        }

        public void dump() {
            for (int i = 0; i < length; i++) {
                Log.log(rooms[i].toString());
            }
        }

        public int size() {
            return length;
        }
    }

} // End Of Project4

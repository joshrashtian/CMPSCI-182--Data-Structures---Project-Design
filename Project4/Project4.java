
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

public class Project4 extends JFrame implements ActionListener {

    private static int xpos = 0, ypos = 0;// place window at this position
    private static int xsize = 700, ysize = 500;// set window to this size

    // Private state variables.

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
            outputArea.setText("Pop returning to " + newcolor);
            // add code to pop color off the stack, check that the color/code matches and
            // change to that color room
        }

        if (e.getSource() == pushButton) {
            String newcolor = colorField.getText();
            outputArea.setText("Push entering " + newcolor);
            // add code to push color/code ON the stack and change to that color room
        }

        if (e.getSource() == dumpButton) {
            System.out.println("Stack Contents Dump: ");
            // add code to print contents of Stack to the CONSOLE
        }

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
            return "Room: " + roomColor + " Code: " + roomCode;
        }
    }

    public interface RoomStackInterface {
        void push(Room obj);

        Room pop();

        Room peek();

        boolean empty();
    }

    public class RoomStack extends Stack<RoomStackInterface> {
        protected Stack<RoomStackInterface> curr = new Stack<RoomStackInterface>();

        public RoomStack() {

        }

        public RoomStack(Room[] Rooms) {
            for (int i = 0; i < Rooms.length; i++) {
                curr.push(Rooms[i]);
            }
        }
    }

} // End Of Project4

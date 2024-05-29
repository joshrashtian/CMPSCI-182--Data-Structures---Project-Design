/***************************************************************

 Project Number 6 - Comp Sci 182 - Data Structures
 Start Code - Build your program starting with this code

 Copyright 2005,2016 Christopher C. Ferguson
 This code may only be used with the permission of Christopher C. Ferguson

 ***************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project6 extends JFrame implements ActionListener {

    private static int win_xpos=0,win_ypos=0;// place window here
    private static int win_xsize=700,win_ysize=500;//  window size

    private String[] names = { "fred" , "barney", "tom", "jerry", "larry", "moe","curly",
            "betty" , "wilma", "bart", "homer", "marge", "maggie", "lisa",
            "pebbles" , "bambam", "smithers", "burns", "milhouse", "george", "astro",
            "dino" , "mickey", "minnie", "pluto", "goofy", "donald", "huey",
            "louie" , "dewey", "snowwhite", "happy", "doc", "grumpy", "sneezy",
            "dopey" , "sleepy", "bambi", "belle", "gaston", "tarzan", "jane",
            "simba" , "scar", "mufasa", "ariel", "flounder", "bugs", "daffy",
            "elmer" , "foghorn", "chickenhawk", "roger", "jessica", "hank", "bobby",
            "peggy" , "spot", "pongo", "perdy", "buzz", "potatohead", "woody",
            "chuckie" , "tommy", "phil", "lil", "angelica", "dill", "spike",
            "pepe" , "speedy", "yosemite", "sam", "tweety", "sylvester", "granny",
            "spiderman" , "batman", "superman", "supergirl", "robin", "jimmy","olsen",
            "thing" , "flash", "silversurfer", "xmen", "pokemon", "joker", "wonderwoman"
    };

// Private state variables.

    private Font boldfont = new Font ("TimesRoman",Font.BOLD,18);
    private Font plainfont = new Font ("TimesRoman",Font.PLAIN,12);

    private JButton hashbutton,exitbutton;
    private JPanel northPanel;
    private MyJPanel centerPanel;
    private JTextField hashsizefield;
    private String thetext = "101";
    private HashTable table = new HashTable(names.length);

////////////MAIN////////////////////////

    public static void main(String[] args) {
        Project6 tpo  = new Project6();

        tpo.addWindowListener(new WindowAdapter() {   // this exits the program when X box clicked
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

////////////CONSTRUCTOR/////////////////////

    public Project6 ()  {
        northPanel = new JPanel();
        northPanel.add(new Label("Enter a hashtable size: "));
        hashsizefield = new JTextField(thetext,20);
        northPanel.add(hashsizefield);
        hashbutton = new JButton("CreateHash");
        northPanel.add(hashbutton);
        hashbutton.addActionListener(this);
        exitbutton = new JButton("Exit");
        northPanel.add(exitbutton);
        exitbutton.addActionListener(this);
        getContentPane().add("North",northPanel);
        centerPanel = new MyJPanel();
        getContentPane().add("Center",centerPanel);

        // need more init stuff? try here.
        setSize(win_xsize,win_ysize);
        setLocation(win_xpos,win_ypos);
        setVisible(true);
    }

////////////BUTTON CLICKS ///////////////////////////

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==exitbutton) {
            dispose(); System.exit(0);
        }

        if (e.getSource()==hashbutton) {
            int length = Integer.parseInt(hashsizefield.getText());
            for (int i = 0; i < length; i++) {
                table.insert(new DataItem(names[(int)(Math.random() * names.length - 1)]));
            }
            repaint();
        }
    } // end actionPerformed

    class MyJPanel extends JPanel {

        ////////////    PAINT   ////////////////////////////////
        public void paintComponent (Graphics g) {

            g.setFont(plainfont);


            if(table != null) {
                int y = 50;
                g.drawString("Hash Crashes: " + table.viewCollisions().size(), 20, 30);
                for(int i = 0; i < table.viewCollisions().toArray().length; i++) {
                    Collision item = table.viewCollisions().get(i);
                    g.drawString(item.getItem().getKey() + " should be at " + table.find(item.getItem().getKey()).getKey() + " but found at " + item.getAt(),20,y);
                    y += 20;
                }
            }
        }
    } // End Of MyJPanel

}     // End Of Project6
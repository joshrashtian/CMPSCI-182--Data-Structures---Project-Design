package CardGame;

/***************************************************************
  Project Number 3 - Comp Sci 182 - Data Structures (w/ Swing)
  Start Code - Build your program starting with this code
               Card Game
  Copyright 2005-2016 Christopher C. Ferguson
  This code may only be used with the permission of Christopher C. Ferguson
***************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Project3 extends JFrame implements ActionListener {

  private static int winxpos = 0, winypos = 0; // place window here

  private JButton shuffleButton, exitButton, newButton;
  private CardList theDeck = null;
  private JPanel northPanel;
  private MyPanel centerPanel;
  private static JFrame myFrame = null;

  //////////// MAIN ////////////////////////
  public static void main(String[] args) {
    Project3 tpo = new Project3();
  }

  //////////// CONSTRUCTOR /////////////////////
  public Project3() {
    myFrame = this; // need a static variable reference to a JFrame object
    northPanel = new JPanel();
    northPanel.setBackground(Color.white);
    shuffleButton = new JButton("Shuffle");
    northPanel.add(shuffleButton);
    shuffleButton.addActionListener(this);
    newButton = new JButton("New Deck");
    northPanel.add(newButton);
    newButton.addActionListener(this);
    exitButton = new JButton("Exit");
    northPanel.add(exitButton);
    exitButton.addActionListener(this);
    getContentPane().add("North", northPanel);

    centerPanel = new MyPanel();
    getContentPane().add("Center", centerPanel);

    theDeck = new CardList(3);

    setSize(800, 700);
    setLocation(winxpos, winypos);

    setVisible(true);
  }

  //////////// BUTTON CLICKS ///////////////////////////
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == exitButton) {
      dispose();
      System.exit(0);
    }
    if (e.getSource() == shuffleButton) {
      theDeck.shuffle();
      repaint();
    }
    if (e.getSource() == newButton) {
      theDeck.deleteCard(1);
      repaint();
    }
  }

  // This routine will load an image into memory
  //
  public static Image load_picture(String fname) {
    // Create a MediaTracker to inform us when the image has
    // been completely loaded.
    Image image;
    MediaTracker tracker = new MediaTracker(myFrame);

    // getImage() returns immediately. The image is not
    // actually loaded until it is first used. We use a
    // MediaTracker to make sure the image is loaded
    // before we try to display it.

    image = myFrame.getToolkit().getImage(fname);

    // Add the image to the MediaTracker so that we can wait
    // for it.
    tracker.addImage(image, 0);
    try {
      tracker.waitForID(0);
    } catch (InterruptedException e) {
      System.err.println(e);
    }

    if (tracker.isErrorID(0)) {
      image = null;
    }
    return image;
  }
  // -------------- end of load_picture ---------------------------

  class MyPanel extends JPanel {

    //////////// PAINT ////////////////////////////////
    public void paintComponent(Graphics g) {
      //
      int xpos = 25, ypos = 5;
      if (theDeck == null)
        return;
      Card current = theDeck.getFirstCard();
      while (current != null) {
        Image tempimage = current.getCardImage();
        g.drawImage(tempimage, xpos, ypos, this);
        // note: tempimage member variable must be set BEFORE paint is called
        xpos += 80;
        if (xpos > 700) {
          xpos = 25;
          ypos += 105;
        }
        current = current.getNextCard();
      } // while
    }
  }

} // End Of class Project3

/*****************************************************************
 * Class Link, the base class for a link list of playing cards
 * May be placed in a file named Link.java
 * 
 ******************************************************************/
class Link {
  protected Link next;

  public Link getNext() {
    return next;
  }

  public void setNext(Link newnext) {
    next = newnext;
  }

} // end class Link

/*****************************************************************
 * Class Card, the derived class each card is one object of type Card
 * May be placed in a file named Card.java
 ******************************************************************/

class Card extends Link {
  String[] dictionary = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "10", "10", "0" };
  private Image cardimage;
  private int cardvalue;

  public Card(int cardnum) {
    cardimage = Project3.load_picture("images/gbCard" + cardnum + ".gif");
    cardvalue = cardnum;

    // code ASSUMES there is an images sub-dir in your project folder
    if (cardimage == null) {
      System.out.println("Error - image failed to load: images/gbCard" + cardnum + ".gif");
      System.exit(-1);
    }
  }

  public Card getNextCard() {
    return (Card) next;
  }

  public int getCardNum() {
    return cardvalue;
  }

  public int getCardValue(int currentScore) {
    int value = cardvalue;
    // 14 -> 1, 24 -> 9
    while (value > 13) {
      value = value - 13;
    }

    if (dictionary[cardvalue] == "A") {
      if (currentScore + 11 > 21) {
        return 1;
      } else
        return 11;
    }

    return Integer.parseInt(dictionary[cardvalue]);
  }

  public Image getCardImage() {
    return cardimage;
  }
} // end class Card

/*****************************************************************
 * Class CardList, A Linked list of playing cards
 * May be placed in a file named CardList.java
 * 
 * Note : This class can be used to create a 'hand' of cards
 * Just Create another CardList object, and delete cards from
 * 'theDeck' and insert the cards into the new CardList object
 * 
 ******************************************************************/

class CardList {
  private Card firstcard = null;
  private int numcards = 0;

  public CardList(int num) {
    numcards = num; // set numcards in the deck
    for (int i = 0; i < num; i++) { // load the cards
      Card temp = new Card(i);
      if (firstcard != null) {
        temp.setNext(firstcard);
      }
      firstcard = temp;
    }
  }

  public Card getFirstCard() {
    return firstcard;
  }

  public Card deleteCard(int cardnum) {
    Card target, targetprevious;

    if (cardnum > numcards)
      return null; // not enough cards to delete that one
    else
      numcards--;

    target = firstcard;
    targetprevious = null;
    while (cardnum-- > 0) {
      targetprevious = target;
      target = target.getNextCard();
      if (target == null)
        return null; // error, card not found
    }
    if (targetprevious != null)
      targetprevious.setNext(target.getNextCard());
    else
      firstcard = target.getNextCard();
    return target;
  }

  public void insertCard(Card target) {
    numcards++;
    if (firstcard != null)
      target.setNext(firstcard);
    else
      target.setNext(null);
    firstcard = target;
  }

  public void shuffle() {
    for (int i = 0; i < 300; i++) {
      int rand = (int) (Math.random() * 100) % numcards;
      Card temp = deleteCard(rand);
      if (temp != null)
        insertCard(temp);
    } // end for loop
  } // end shuffle

} // end class CardList

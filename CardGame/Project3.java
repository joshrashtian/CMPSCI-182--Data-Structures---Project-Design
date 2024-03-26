package CardGame;

/***************************************************************
  Project Number 3 - Comp Sci 182 - Data Structures (w/ Swing)
  Start Code - Build your program starting with this code
               Card Game
  Copyright 2005-2016 Christopher C. Ferguson
  This code may only be used with the permission of Christopher C. Ferguson
***************************************************************/

import javax.swing.*;

import Universal.Log;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Project3 extends JFrame implements ActionListener {

  private static int winxpos = 0, winypos = 0; // place window here

  private JButton HitButton, exitButton, StandButton;
  private JLabel deckAmount;
  private CardList PlayerDeck = null;
  private CardList dealerDeck = null;
  private JPanel northPanel;
  private MyPanel centerPanel;
  private DealerPanel bottomPanel;
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
    HitButton = new JButton("Hit");
    northPanel.add(HitButton);
    HitButton.addActionListener(this);
    StandButton = new JButton("Stand");
    northPanel.add(StandButton);
    StandButton.addActionListener(this);
    exitButton = new JButton("Exit");
    northPanel.add(exitButton);
    exitButton.addActionListener(this);
    getContentPane().add(northPanel, BorderLayout.NORTH);

    centerPanel = new MyPanel();
    centerPanel.setSize(800, 10);
    centerPanel.setBackground(Color.green);

    bottomPanel = new DealerPanel();
    bottomPanel.setSize(800, 50);
    bottomPanel.setBackground(Color.CYAN);
    getContentPane().add(centerPanel, BorderLayout.CENTER);

    PlayerDeck = new CardList(2);

    dealerDeck = new CardList(2);

    getContentPane().add(bottomPanel, BorderLayout.SOUTH);

    deckAmount = new JLabel();
    deckAmount.setText("Card Amount: " + PlayerDeck.getDeckAmount());
    centerPanel.add(deckAmount);

    setSize(800, 900);
    setLocation(winxpos, winypos);

    setVisible(true);
  }

  //////////// BUTTON CLICKS ///////////////////////////
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == exitButton) {
      dispose();
      System.exit(0);
    }
    if (e.getSource() == StandButton) {
      repaint();
    }
    if (e.getSource() == HitButton) {
      PlayerDeck.randomNewCard();
      deckAmount.setText("Card Amount: " + PlayerDeck.getDeckAmount());
      checkValues(PlayerDeck, dealerDeck);
      repaint();
    }
  }

  public void NewUserTurn() {

  }

  public void checkValues(CardList user, CardList dealer) {

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
      int xpos = 25, ypos = 0;
      if (PlayerDeck == null)
        return;
      Card current = PlayerDeck.getFirstCard();
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

  class DealerPanel extends JPanel {
    public void paintComponent(Graphics g) {
      //
      int xpos = 25, ypos = 0;
      if (dealerDeck == null)
        return;
      Card current = dealerDeck.getFirstCard();
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

    if (dictionary[value] == "A") {
      if (currentScore + 11 > 21) {
        return 1;
      } else
        return 11;
    }

    return Integer.parseInt(dictionary[value]);
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
 * 'PlayerDeck' and insert the cards into the new CardList object
 * 
 ******************************************************************/

class CardList {
  private Card firstcard = null;
  private int numcards = 0;
  private int valueOfCards = 0;

  public CardList(int num) {
    numcards = num; // set numcards in the deck
    for (int i = 0; i < num; i++) { // load the cards
      int rand = (int) (Math.random() * 13);
      Card temp = new Card(rand);
      if (firstcard != null) {
        temp.setNext(firstcard);
      }
      firstcard = temp;
      valueOfCards += temp.getCardValue(valueOfCards);
    }
  }

  public Card getFirstCard() {
    return firstcard;
  }

  public int getDeckAmount() {
    return valueOfCards;
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

  public void randomNewCard() {
    int rand = (int) (Math.random() * 51);
    Card temp = new Card(rand);
    if (temp != null)
      insertCard(temp);
  }

  public void insertCard(Card target) {
    numcards++;
    if (firstcard != null)
      target.setNext(firstcard);
    else
      target.setNext(null);
    firstcard = target;
    Log.log(target.getCardValue(valueOfCards));
    valueOfCards += target.getCardValue(valueOfCards);
    Log.log("Value: " + valueOfCards);
  }

  public void shuffle() {
    // shuffle the deck
    valueOfCards = 0;
    for (int i = 0; i < 300; i++) {
      int rand = (int) (Math.random() * 51);
      Card temp = deleteCard(rand);
      if (temp != null)
        insertCard(temp);

    } // end for loop
  } // end shuffle

} // end class CardList

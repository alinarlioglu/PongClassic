/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 *
 * @author Ali
 */
public class Pong extends JFrame {
    //The components making up Pong - game mechanics. Works as my custom panel.
    private PongWorld pongWorld;
    
    public Pong(){
        //The constructor of PongWorld throws errors, thus the try-catch block will capture the errors and initialise the 'pongWorld' instance in the 'try' block.
        try {
            pongWorld = new PongWorld();
        } catch (LineUnavailableException ex) {
            System.out.println(ex.getMessage());
        } catch (UnsupportedAudioFileException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        //Title of the JFrame
        setTitle("Pong");
        //Terminates the game once the game window is closed.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        //Placing my custom panel into the JFrame - by creating my custom panel and placing it inside the JFrame
        //helps to prevent the players and the ball from constantly flickering due to overloading the repaint() method
        //when creating the game inside the JFrame only.
        add(pongWorld);
        //Adding the key listeners - helps to move the players.
        addKeyListener(new KeyControllerSingle(this));
        addKeyListener(new KeyController(this));
        //Doesn't let the game window be resized
        setResizable(false);
        pack();
        //Makes the window visible
        setVisible(true);
    }
    
    public PongWorld getPongWorld(){
        return pongWorld;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Pong();
    }
    
}

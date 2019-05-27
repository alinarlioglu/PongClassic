/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

/**
 *
 * @author Ali
 */
//All the game components are in this class - my custom panel.
public class PongWorld extends JPanel {
    //Variables to manipulate in order to move the first and the second player.
    private int xPos, xPos2, yPos, yPos2, firstPlayerScore, secondPlayerScore, pickSpeed;
    //Double precision variables used to move the ball.
    private double dx, dy, xPosCircle, yPosCircle;
    //Used to randomly select a starting position for the ball.
    private Random random;
    //Declaring a double array to be used in ball's direction initialisation whenever a player scores.
    private double[] ballSpeed;
    //Declaring an InputStream, Clip and File to obtain the sound file and simply send it to the stream and play the sound using the Clip.
    private AudioInputStream inputStream;
    private Clip coinSong;
    private File coinSongLocation;
    
    public PongWorld() throws LineUnavailableException, UnsupportedAudioFileException, IOException{
        //Initialising a file which simply travels to the indicated location and obtains the sound file.
        coinSongLocation = new File("data/coin.wav");
        //Sending the file to the stream.
        inputStream = AudioSystem.getAudioInputStream(coinSongLocation);
        //Reading the file within the stream.
        coinSong = AudioSystem.getClip();
        coinSong.open(inputStream);
        //To initialise the ball in a random range.
        random = new Random();
        //Once a player scores, this double array is used to randomly initialise the ball's direction when the game restarts.
        ballSpeed = new double[]{-0.13,0.13};
        //Initialising the coordinates of the players, the ball, the speed and direction of movement for the ball and the scores.
        xPosCircle = 300;
        yPosCircle = 350;
        xPos2 = 200;
        xPos = 70;
        yPos = 50;
        yPos2 = 750;
        dx = 0.13;
        dy = 0.13;
        firstPlayerScore = 0;
        secondPlayerScore = 0;
        //Sets the size of the panel.
        setPreferredSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setMaximumSize(new Dimension(800, 600));
    }
    
    //Draws the players and the ball on the screen.
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setFont(new Font("Arial", Font.PLAIN, 50));
        g2D.drawString("" + secondPlayerScore, 180, 60);
        g2D.drawString("" + firstPlayerScore, 580, 60);
        //Draws the lines in the centre of the screen.
        g2D.drawLine(395, 10, 395, 60);
        g2D.drawLine(395, 90, 395, 140);
        g2D.drawLine(395, 170, 395, 220);
        g2D.drawLine(395, 250, 395, 300);
        g2D.drawLine(395, 330, 395, 380);
        g2D.drawLine(395, 410, 395, 460);
        g2D.drawLine(395, 490, 395, 540);
        g2D.drawLine(395, 570, 395, 720);
        g2D.drawLine(395, 750, 395, 800);
        g2D.drawLine(395, 830, 395, 850);
        //Draws the first player on the screen
        g2D.drawRect(yPos, xPos, 10, 40);
        //Draws the second player on the screen.
        g2D.drawRect(yPos2, xPos2, 10, 40);
        //Creates a circle
        g.drawOval((int) yPosCircle, (int) xPosCircle, 10, 10);
        circleMovement();
        firstPlayerCollision();
        secondPlayerCollision();
        //Score throws errors, thus the try-catch block will capture the errors thrown enabling the running of the code in the 'try' block.
        try {
            score();
        } catch (LineUnavailableException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (UnsupportedAudioFileException ex) {
            System.out.println(ex.getMessage());
        }
        
        //Updates the user interface - whenever a change occurs i.e. ball or player moving and scoring.
        repaint();
    }
    
    //Enables the ball to move across the height of the screen only.
    public void circleMovement(){
        //Checks if ball at the bottom of the screen, then makes 'dx' positive, so ball's x-position increases making the ball go up.
        if(xPosCircle<=0){ dx = 0.13; }
        //Checks if ball is at the top of the screen, then makes 'dx' negative, so ball's x-position decreases making the ball go down.
        if(xPosCircle>=(getBounds().height-10)) { dx = -0.13; }
        //'dy' constantly increase's ball's y-position depending on it's x-position - think of the wheels of a car, it just moves forward but the driver changes the direction. 
        xPosCircle = xPosCircle + dx;
        yPosCircle = yPosCircle + dy;
    }
    
    //Detects if the ball has collided with the first player - the right-side rectangle.
    public void firstPlayerCollision(){
        //Checks if the ball is within the rectangle's bounds, then changes the direction of the y-velocity.
        //Since the first player is at the right-side of the screen, the change in y-velocity causes the ball
        //to move towards the left-side of the screen making it seem as if the ball 'bounced'.
        if(yPosCircle>=(yPos2-10) && yPosCircle<=(yPos2) && xPosCircle>=(xPos2) && xPosCircle<=(xPos2+40)){
            dy = -0.13;
        }
    }
    
    //Detects if the ball has collided with the second player - the left-side rectangle.
    public void secondPlayerCollision(){
        //Checks if the ball is within the rectangle's bounds, then changes the direction of the y-velocity.
        //Since the second player is at the left-side of the screen, the change in y-velocity causes the ball
        //to move towards the right-side of the screen making it seem as if the ball 'bounced'.
        if(yPosCircle>=(yPos) && yPosCircle<=(yPos+10) && xPosCircle>=(xPos) && xPosCircle<=(xPos+40)){
            dy = 0.13;
        }
    }
    
    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }
    
    public int getxPos2(){
        return xPos2;
    }
    
    public void setxPos2(int xPos2){
        this.xPos2 = xPos2;
    }
    
    public void score() throws LineUnavailableException, IOException, UnsupportedAudioFileException{
        if(yPosCircle>getBounds().getWidth()){
            ++secondPlayerScore;
            //Positions the ball randomly between the random of 200-350 for both it's x and y coordinate.
            xPosCircle = 250 + (350 - 100) * random.nextDouble();
            yPosCircle = 250 + (350 - 100) * random.nextDouble();
            //Initialising the ball's direction randomly, so it goes off to either player whenever the game restarts.
            //Selects a random element in the double array and initialises that element as the ball's direction movement.
            pickSpeed = ThreadLocalRandom.current().nextInt(0, 2);
            dx = ballSpeed[pickSpeed];
            dy = ballSpeed[pickSpeed];
            try {
                coinSong.loop(0);
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
            //Re-initialising the AudioInputStream and the Clip, as the InputStream can only be read once,
            //thus it plays the sound once only. Therefore, by re-initialising the InputStream and re-adding it to the clip
            //will help to re-play the sound over and over.
            inputStream = AudioSystem.getAudioInputStream(coinSongLocation);
            coinSong = AudioSystem.getClip();
            coinSong.open(inputStream);
            //Pauses the game for 2 seconds once a player scores - helps to orientate the player whenever a score occurs.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if(yPosCircle<0){
            ++firstPlayerScore;
            //Positions the ball randomly between the random of 200-350 for both it's x and y coordinate.
            xPosCircle = 250 + (350 - 100) * random.nextDouble();
            yPosCircle = 250 + (350 - 100) * random.nextDouble();
            //Initialising the ball's direction randomly, so it goes off to either player whenever the game restarts.
            //Selects a random element in the double array and initialises that element as the ball's direction movement.
            pickSpeed = ThreadLocalRandom.current().nextInt(0, 2);
            dx = ballSpeed[pickSpeed];
            dy = ballSpeed[pickSpeed];
            try {
               coinSong.loop(0);
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
            //Re-initialising the AudioInputStream and the Clip, as the InputStream can only be read once,
            //thus it plays the sound once only. Therefore, by re-initialising the InputStream and re-adding it to the clip
            //will help to re-play the sound over and over.
            inputStream = AudioSystem.getAudioInputStream(coinSongLocation);
            coinSong = AudioSystem.getClip();
            coinSong.open(inputStream);
            //Pauses the game for 2 seconds once a player scores - helps to orientate the player whenever a score occurs.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Ali
 */
public class KeyController extends KeyAdapter {
    //Accessed to manipulate the positions of the players
    private Pong pong;
    //Set of currently pressed keys
    private final Set<Integer> pressedKeys;
    
    public KeyController(Pong pong){
        this.pong = pong;
 
        pressedKeys = new HashSet<>();
    }
    
    //The Set maintains a list of "active" keys and 'synchronized' allows multiple executions of the method to occur, thus multiple keys can be pressed at a time.
    @Override
    public synchronized void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        pressedKeys.add(e.getKeyCode());
        
        int x = 0;
        if(pressedKeys.size()>1){
            //Movement for the first player
            if(pressedKeys.contains(KeyEvent.VK_W)==true){
                x = pong.getPongWorld().getxPos();
                x = x - 5;
                pong.getPongWorld().setxPos(x);
            } else if(pressedKeys.contains(KeyEvent.VK_S)==true){
                x = pong.getPongWorld().getxPos();
                x = x + 5;
                pong.getPongWorld().setxPos(x);
            } 
        
            if(pressedKeys.contains(KeyEvent.VK_UP)==true){
                x = pong.getPongWorld().getxPos2();
                x = x - 5;
                pong.getPongWorld().setxPos2(x);
            }
            else if(pressedKeys.contains(KeyEvent.VK_DOWN)==true){
                x = pong.getPongWorld().getxPos2();
                x = x + 5;
                pong.getPongWorld().setxPos2(x);
            }
        }
    }

    //Once the key pressed is released, then syncrhonized only allows one key to be released at a time, thus the other keys are still "active"
    //therefore, they can still run the action indicated to them.
    @Override
    public synchronized void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        pressedKeys.remove(e.getKeyCode());
    }

    //@Override
    //public void keyTyped(KeyEvent e) { }
    
    
    
}

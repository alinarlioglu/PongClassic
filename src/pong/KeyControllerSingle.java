/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Ali
 */
public class KeyControllerSingle extends KeyAdapter {
    //Accessed to manipulate the positions of the players
    private Pong pong;
    
    public KeyControllerSingle(Pong pong){
        this.pong = pong;
    }
    
    //Key movement for single key presses only, thus allowing a single player to still move if the other player stops moving.
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int x=0;
        
        //Movement for the first player
        if(e.getKeyCode()==KeyEvent.VK_W){
            x = pong.getPongWorld().getxPos();
            x = x - 5;
            pong.getPongWorld().setxPos(x);
        } else if(e.getKeyCode()==KeyEvent.VK_S){
            x = pong.getPongWorld().getxPos();
            x = x + 5;
            pong.getPongWorld().setxPos(x);
        } 
        else if(e.getKeyCode()==KeyEvent.VK_UP){
            x = pong.getPongWorld().getxPos2();
            x = x - 5;
            pong.getPongWorld().setxPos2(x);
        }
        else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            x = pong.getPongWorld().getxPos2();
            x = x + 5;
            pong.getPongWorld().setxPos2(x);
        }
    }
}

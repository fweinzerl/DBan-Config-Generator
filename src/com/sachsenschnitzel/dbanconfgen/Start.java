/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sachsenschnitzel.dbanconfgen;

import com.sachsenschnitzel.dbanconfgen.gui.InputFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author schnitzel
 */
public class Start{	
    public static void main(String[] args){
        //! hallo! die erklärenden kommentare für dich sind mit ! markiert
        //! wennstas nimmer brauchst die deutschen kommentare wieder weg
        //! tun (musst aber auch nicht ostereier suchen gehen)
        //! und nur auf englisch programmieren ;)
        InputFrame ifr = new InputFrame();
        ifr.setSize(800, 600);
        ifr.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ifr.setVisible(true);
    }
}

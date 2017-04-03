/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sachsenschnitzel.dbanconfgen.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author schnitzel
 */
public class InputPanel extends JPanel{
    public static final int GRID_W = 7;
    public static final JLabel[] HEADLINES = {new JLabel("IP"), new JLabel("MAC"),
                                            new JLabel("Name"), new JLabel("Anm. #1"),
                                            new JLabel("Anm. #2"), new JLabel("CName #1"),
                                            new JLabel("CName #2"), new JLabel("CName #3")};
    
    private JTextField[][] grid; //! da is alles drin wasd brauchst, schaus dir im saveTo an wies geht
    
    public InputPanel(int size){
        grid = new JTextField[size][GRID_W];
        
        this.setBackground(Color.DARK_GRAY);
        
        GridLayout l = new GridLayout(size+1, GRID_W);
        l.setHgap(5);
        l.setVgap(5);
        this.setLayout(l);
        
        for(JLabel lbl : HEADLINES){
            lbl.setForeground(Color.LIGHT_GRAY);
            lbl.setHorizontalAlignment(JLabel.CENTER);
            this.add(lbl);
        }
        
        for(int i = 0; i < grid.length; i++){
            JLabel jlbl = new JLabel("10.1.1." + (i+1));
            jlbl.setForeground(Color.WHITE);
            this.add(jlbl);
            for(int j = 0; j < grid[i].length; j++)
                this.add(grid[i][j] = new JTextField());
        }
    }
    
    public void saveTo(File path){
        try{
            BufferedWriter w = new BufferedWriter(new FileWriter(path));
            for(int i = 0; i < grid.length; i++){
                String line = grid[i][0].getText();
                for(int j = 1; j < grid[i].length; j++)
                    line += "\t" + grid[i][j].getText();
                w.write(line + "\n");
            }
            
            w.flush();
            w.close();
        }catch(IOException e){
            System.out.println("Could not write to " + path);
        }
    }
    
    public void loadFrom(File path){
        try{
            BufferedReader r = new BufferedReader(new FileReader(path));
            for(int i = 0; i < grid.length; i++){
                String line = r.readLine();
                String[] fields = line.split("\t");
                for(int j = 0; j < grid[i].length ; j++){
                    if(j < fields.length)
                        grid[i][j].setText(fields[j]);
                    else
                        grid[i][j].setText("");
                }
            }
            
            r.close();
        }catch(IOException e){
            System.out.println("Could not read from " + path);
        }
    }
    
    public void exportTo(File path){
        //! dacht ich mir halt...
    }
}

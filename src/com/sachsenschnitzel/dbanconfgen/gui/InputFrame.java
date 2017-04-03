/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sachsenschnitzel.dbanconfgen.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

/**
 *
 * @author schnitzel
 */
public class InputFrame extends JFrame{
    private InputPanel panel;
    private JMenuItem miSave;
    private JMenuItem miSaveAs;
    private JMenuItem miLoad;
    private JMenuItem miExport; //! dein menuitem...
    private File lastSave;
    
    public InputFrame(){
        panel = new InputPanel(254);
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());
        c.add(new JScrollPane(panel), BorderLayout.CENTER);
        JButton jb = new JButton("Transfer");
        
        //add menu bar
        JMenuBar jmb = new JMenuBar();
        JMenu menu = new JMenu("File");
        jmb.add(menu);
        
        initMenuItems(); //! da drin schreiben (und im InputPanel)
        
        menu.add(miSave);
        menu.add(miSaveAs);
        menu.add(miLoad);
        menu.addSeparator(); //! ...trennstrich im menü, dann dein item
        menu.add(miExport);
        this.setJMenuBar(jmb);
    }
    
    private void initMenuItems(){
        this.miSave = new JMenuItem("Save"); 
        this.miSave.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    if(lastSave == null){
                        File save = getChosenSaveFile();
                        if(save != null)
                            lastSave = save;
                    }
                    panel.saveTo(lastSave);
                }
            });
        
        
        
        this.miSaveAs = new JMenuItem("Save as ...");
        this.miSaveAs.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    File save = getChosenSaveFile();
                    if(save != null)
                        panel.saveTo(lastSave = save);
                }
            });
        
        
        
        this.miLoad = new JMenuItem("Load");
        this.miLoad.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    File load = getChosenLoadFile();
                    if(load != null)
                        panel.loadFrom(lastSave = load);
                }
            });
        
        
        //! --> nääämlich hier
        this.miExport = new JMenuItem("Export");
        this.miExport.setAccelerator(KeyStroke.getKeyStroke('E', // sets Ctrl + E as shortcut
                                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        this.miExport.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    //! hast sogar deine eigene methode kriegt (getChosenExportFile())
                    //! ich teils gern auf damit hier nicht der ganze FileChoose-müll is...
                    File export = getChosenExportFile();
                    if(export != null)
                        ;//panel.loadFrom(lastSave = export);
                }
            });
    }
    
    public File getChosenSaveFile(){
        JFileChooser fc = new JFileChooser(){
            @Override
            public void approveSelection(){
                File f = getSelectedFile();
                if(f.exists() && getDialogType() == SAVE_DIALOG){
                    int result = JOptionPane.showConfirmDialog(this,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_OPTION);
                    switch(result){
                        case JOptionPane.YES_OPTION:
                            super.approveSelection();
                            return;
                        case JOptionPane.NO_OPTION:
                        case JOptionPane.CLOSED_OPTION:
                            return;
                    }
                }
                super.approveSelection();
            }        
        };
                
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
            return fc.getSelectedFile();
        else
            return null;
    }
    
    public File getChosenLoadFile(){
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            return fc.getSelectedFile();
        else
            return null;
    }
    
    public File getChosenExportFile(){
        //! eigene Methode und nicht alte weiterverwenden deswegen, weilst
        //! das mit dem 'Ordner auswählen' wolltest...
        //! dann iss allerdings blöd mit filenamen festlegen, ich vermute,
        //! der gibt dir den path zum ordner zrück. aber vllt sinds eh fixe
        //! namen dann passts ja, stells halt OBEN um
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // user can only select directories
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            return fc.getSelectedFile();
        else
            return null;
    }
}

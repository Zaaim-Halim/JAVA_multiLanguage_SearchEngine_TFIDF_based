package com.halim.gui;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class Gui extends JFrame{

	private static final long serialVersionUID = 1L;
    private JTextField searchField ;
    private JButton searchBtn;
    private JList resList;
    private JTextPane displayText;
    
	public Gui(String title) throws HeadlessException {
		super(title);
		
	}
	
	public void init() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        
		
        JPanel ui = new JPanel(new BorderLayout(2, 2));
        ui.setBorder(new EmptyBorder(4, 4, 4, 4));

        JPanel controls = new JPanel(new BorderLayout(2, 2));
        ui.add(controls, BorderLayout.PAGE_START);
                
        searchBtn = new JButton("Search");
        searchField = new JTextField();
        searchField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        controls.add(searchField, BorderLayout.CENTER);
        
        controls.add(searchBtn, BorderLayout.LINE_END);
 
        JTextArea text2 = new JTextArea(4, 40);
        
       
        resList = new JList<>();
        // elements to the list
        
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, resList ,text2 );

        ui.add(sp, BorderLayout.CENTER);

       
        
        this.setContentPane(ui);
        this.pack();
        this.setLocationByPlatform(true);
       
        this.setVisible(true);
	}

}

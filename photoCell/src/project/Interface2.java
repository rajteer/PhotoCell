package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class Interface2 extends JFrame {
	
	JMenuBar topMenuBar;
	JMenu menu, elementsSubMenu;
	
	JRadioButton colorTheme;
	
	JComboBox<String> languageOption;
	String[] languageSelection = { "angielski", "francuski", "polski"};
	
	JButton saveButton, exitButton;

	JMenuItem showData, moreInformation, aboutProgram;
	JMenuItem elementYtterbium, elementVanadium, elementCaesium, elementBeryl, elementFerrum, elementArgentum;
	
	JPanel topPanel, centerPanel, leftCenterPanel, rightCenterPanel;
	
	String choosedElement = "Cez";
	String selectedTheme = "lightTheme";
	
	JFrame optionFrame;

	
	Interface2()
	{
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(640,480);
        this.setLayout(new BorderLayout());
        
        JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		
		saveButton = new JButton("Zapisz dane");
		topPanel.add(saveButton);
		
		
		exitButton = new JButton("Zakończ");
		exitButton.setForeground(Color.RED);
		exitButton.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				int n = JOptionPane.showConfirmDialog(
						optionFrame, "Czy pewno zakonczyc?",
                        "Zakoncz",
                        JOptionPane.YES_NO_OPTION);
	
				if (n == JOptionPane.YES_OPTION) 
		        	dispose();	
			}
		});
		
		colorTheme = new JRadioButton("Tryb ciemny");
		colorTheme.addActionListener(new ActionListener() 
    	{
    		@Override
        	public void actionPerformed(ActionEvent e)
        	{
    			if(selectedTheme == "lightMode")
    			{
    				selectedTheme = "darkMode";
    				topPanel.setBackground(Color.WHITE);
    				leftCenterPanel.setBackground(Color.WHITE);
    				rightCenterPanel.setBackground(Color.WHITE);
    			}
    			else
    			{
    				selectedTheme = "lightMode";
    				topPanel.setBackground(Color.GRAY);
    				leftCenterPanel.setBackground(Color.GRAY);
    				rightCenterPanel.setBackground(Color.GRAY);
    			}
        	}
    	});
		colorTheme.setActionCommand("DarkMode");
		
		topPanel.add(colorTheme);
		topPanel.add(exitButton);
		
		languageOption = new JComboBox<String>(languageSelection);
		languageOption.setSelectedIndex(2);
		topPanel.add(languageOption);
		
		this.add(topPanel, BorderLayout.PAGE_START);
		
		showData = new JMenuItem("Wyświetl dane");
		moreInformation = new JMenuItem("Wstęp teoretyczny");
		moreInformation.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				TheoryWindow moreInformationPanel = new TheoryWindow();
				moreInformationPanel.setVisible(true);
			}
		});
		
		choosedElement = "Cez";
		
		elementsSubMenu = new JMenu(choosedElement);
		menu.add(elementsSubMenu);

		elementYtterbium = new JMenuItem("Yb - iterb");
		elementsSubMenu.add(elementYtterbium);
		elementYtterbium.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				choosedElement = "Iterb";
				elementsSubMenu.setText(choosedElement);
			}
		});
		
		elementVanadium = new JMenuItem("V - wanad");
		elementsSubMenu.add(elementVanadium);
		elementVanadium.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				choosedElement = "Wanad";
				elementsSubMenu.setText(choosedElement);
			}
		});
		
		elementCaesium = new JMenuItem("Cs - cez");
		elementsSubMenu.add(elementCaesium);
		elementCaesium.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				choosedElement = "Cez";
				elementsSubMenu.setText(choosedElement);
			}
		});
		
		elementBeryl = new JMenuItem("Be - beryl");
		elementsSubMenu.add(elementBeryl);
		elementBeryl.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				choosedElement = "Beryl";
				elementsSubMenu.setText(choosedElement);
			}
		});
		
		elementFerrum = new JMenuItem("Fe - żelazo");
		elementsSubMenu.add(elementFerrum);
		elementFerrum.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				choosedElement = "Żelazo";
				elementsSubMenu.setText(choosedElement);
			}
		});
		
		elementArgentum = new JMenuItem("Ag - srebro");
		elementsSubMenu.add(elementArgentum);
		elementArgentum.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				choosedElement = "Srebro";
				elementsSubMenu.setText(choosedElement);
			}
		});
		
		
		aboutProgram = new JMenuItem("O programie");
		aboutProgram.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				TheoryWindow moreInformationPanel = new TheoryWindow();
				moreInformationPanel.setVisible(true);
			}
		});
		menu.add(showData);
		menu.add(moreInformation);
		menu.addSeparator();
		menu.add(aboutProgram);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1,2));
		
		rightCenterPanel = new JPanel();
		
		PhotoEffect photoEffectPanel = new PhotoEffect();
		PhotoCellSettings  photoCellSettingsPanel = new PhotoCellSettings();
		
		//OnOffToggle animationTogglePanel = new OnOffToggle();
		
		leftCenterPanel = new JPanel();
		
		leftCenterPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 200;
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		leftCenterPanel.add(photoEffectPanel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 100;
		c.weighty = 1000;   
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridx = 0;       
		c.gridwidth = 3;   
		c.gridy = 2;
		leftCenterPanel.add(photoCellSettingsPanel, c);
		
		centerPanel.add(leftCenterPanel);
		centerPanel.add(rightCenterPanel);
		
		
		this.add(centerPanel, BorderLayout.CENTER);
	}
	
	public static void main(String[] args)
	{
		Interface2 window = new Interface2();
		window.setVisible(true);
		window.setTitle("Fotokomórka");
	}
}
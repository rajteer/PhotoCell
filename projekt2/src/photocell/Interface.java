package photocell;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.Statement;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

public class Interface extends JFrame 
{
	JMenuBar menuBar;
	JMenu menu, elementsSubMenu;
	final static String LOOKANDFEEL = "Nimbus";
	JRadioButton colorTheme;
	JButton polish, english;
	JButton saveButton, exitButton;
	JMenuItem showData, moreInformation, aboutProgram;
	JMenuItem elementSodium, elementRubidium, elementCaesium, elementCalcium, elementPotassium;
	JPanel topPanel, centerPanel, leftCenterPanel, rightCenterPanel, rightTopPanel, leftTopPanel;
	JFrame f;
	static PlotPanel rightTopPane;
	static private String choosedElement = "Cs";
	String selectedTheme = "lightTheme";
	JFrame optionFrame;
	static JOptionPane informationWindow = new JOptionPane();
	DataTable dataFrame;
	static PhotoEffect photoEffectPanel;
	static PhotoCellSettings  photoCellSettingsPanel;
	SaveData saveData = new SaveData();
	String AboutAuthors, ExitQuestion, exit, information;
	TheoryWindow moreInformationPanel;
	
	public static String getChoosedElement() 
	{
		return choosedElement;
	}
	
	Interface() throws FontFormatException, IOException
	{
		try 
		{
			UIManager.put("nimbusBlueGrey", new Color(255, 255, 255));
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
		    {
		        if (LOOKANDFEEL.equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) 
		{
			
			System.err.println("Couldn't find class for specified look and feel");
		}
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(640,480);
        this.setLayout(new BorderLayout());
        
        
        menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		
		leftTopPanel = new JPanel();
		rightTopPanel = new JPanel();
		
		saveButton = new JButton();
		
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dataFrame = new DataTable(PlotPanel.getListTableModel());
				dataFrame.setVisible(true);	
			}
		});
		
		leftTopPanel.add(saveButton);
		
		
		
		exitButton = new JButton();
		exitButton.setForeground(Color.RED);
		exitButton.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				int n = JOptionPane.showConfirmDialog(
						optionFrame, ExitQuestion,
                        exit,
                        JOptionPane.YES_NO_OPTION);
	
				if (n == JOptionPane.YES_OPTION) 
				{	
					photoEffectPanel.stopThread();
		        	dispose();	
				}
			}
		});
		colorTheme = new JRadioButton();
		colorTheme.addActionListener(new ActionListener() 
    	{
    		@Override
        	public void actionPerformed(ActionEvent e)
        	{
        			if(selectedTheme == "lightMode")
        			{
        				
        				selectedTheme = "darkMode";
        				try 
        				{
        					UIManager.put("nimbusBlueGrey", new Color(255, 255, 255));
        					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        					SwingUtilities.updateComponentTreeUI(Interface.this);
       				 	}
       				 	catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) 
       				 	{
       				 			e1.printStackTrace();
       				 	}
        			}
        			else
        			{
        				selectedTheme = "lightMode";
        				 try 
        				 {
        					UIManager.put("nimbusBlueGrey", new Color(100, 100, 100));
        					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        					SwingUtilities.updateComponentTreeUI(Interface.this);
        				 }
        				 catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) 
        				 {
        		                e1.printStackTrace();
        				 }
        			}
            	}
    	});
		colorTheme.setActionCommand("DarkMode");
		rightTopPanel.add(colorTheme);
		
		
		ImageIcon obrazek1 = new ImageIcon(getClass().getResource("polishFlag.png"));
		ImageIcon obrazek2 = new ImageIcon(getClass().getResource("britishFlag.png"));
		
		polish= new JButton();
		polish.setActionCommand("pl_PL");
		polish.setPreferredSize(new Dimension(35, 25));
		polish.setIcon(obrazek1);
		polish.addActionListener(locChanger);
		english= new JButton();
		english.setActionCommand("en_GB");
		english.setPreferredSize(new Dimension(35, 25));
		english.setIcon(obrazek2);
		english.addActionListener(locChanger);
		
		rightTopPanel.add(polish);
		rightTopPanel.add(english);
		rightTopPanel.add(exitButton);
		
		topPanel.add(leftTopPanel, BorderLayout.LINE_START);
		topPanel.add(rightTopPanel, BorderLayout.LINE_END);
		
		this.add(topPanel, BorderLayout.PAGE_START);
		
		showData = new JMenuItem();
		showData.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				moreInformationPanel = new TheoryWindow("manual.html");
				moreInformationPanel.setVisible(true);
				moreInformationPanel.setTitle("Instrukcja");
			}
		});
		
		moreInformation = new JMenuItem();
		moreInformation.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				moreInformationPanel = new TheoryWindow("test.html");
				moreInformationPanel.setVisible(true);	
				moreInformationPanel.setTitle("Wstęp teoretyczny");
			}
		});
		
		elementsSubMenu = new JMenu(choosedElement);
		menu.add(elementsSubMenu);

		elementSodium  = new JMenuItem("Na - Sód");
		elementsSubMenu.add(elementSodium);
		elementSodium.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				choosedElement = "Na";
				elementsSubMenu.setText(choosedElement);
				PlotPanel.updateChart();
				PlotPanel.updateCurrentVoltageChart();
				PlotPanel.updateLowerPanelTable();
			}
		});
		
		elementRubidium = new JMenuItem("Rb - Rubid");
		elementsSubMenu.add(elementRubidium);
		elementRubidium.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				choosedElement = "Rb";
				elementsSubMenu.setText(choosedElement);
				PlotPanel.updateChart();
				PlotPanel.updateCurrentVoltageChart();
				PlotPanel.updateLowerPanelTable();
			}
		});
		
		elementCaesium = new JMenuItem("Cs - Cez");
		elementsSubMenu.add(elementCaesium);
		elementCaesium.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				choosedElement = "Cs";
				elementsSubMenu.setText(choosedElement);
				PlotPanel.updateChart();
				PlotPanel.updateCurrentVoltageChart();
				PlotPanel.updateLowerPanelTable();
			}
		});
		
		elementCalcium  = new JMenuItem("Ca - Wapń");
		elementsSubMenu.add(elementCalcium);
		elementCalcium.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				choosedElement = "Ca";
				elementsSubMenu.setText(choosedElement);
				PlotPanel.updateChart();
				PlotPanel.updateCurrentVoltageChart();
				PlotPanel.updateLowerPanelTable();
			}
		});
		
		elementPotassium = new JMenuItem("K - Potas");
		elementsSubMenu.add(elementPotassium);
		elementPotassium.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				choosedElement = "K";
				elementsSubMenu.setText(choosedElement);
				PlotPanel.updateChart();
				PlotPanel.updateCurrentVoltageChart();
				PlotPanel.updateLowerPanelTable();
			}
		});
		
		
		aboutProgram = new JMenuItem();
		aboutProgram.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(
						informationWindow,AboutAuthors ,
						information,
                            JOptionPane.PLAIN_MESSAGE);
			}
		});
		menu.add(showData);
		menu.add(moreInformation);
		menu.addSeparator();
		menu.add(aboutProgram);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1,2));
		
		rightCenterPanel = new JPanel();
		
		photoEffectPanel = new PhotoEffect();
		photoCellSettingsPanel = new PhotoCellSettings();
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
		
		rightCenterPanel.setLayout(new BorderLayout());
		rightTopPane=new PlotPanel();
		rightTopPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		rightCenterPanel.add(rightTopPane, BorderLayout.NORTH);

		this.add(centerPanel, BorderLayout.CENTER);
		
		localizeLeft(Locale.getDefault());
		localizeRight(Locale.getDefault());
		localizePhotoEffect(Locale.getDefault());
		localizeSettings(Locale.getDefault());
		localizeMenu(Locale.getDefault());
		localizeButton(Locale.getDefault());
		
	}
	private void localizeLeft(Locale loc) 
	{
	    Locale.setDefault(loc);
	    ResourceBundle info = ResourceBundle.getBundle("photocell.Info");  
	    String[] txt = {info.getString("saveButton")};
	    
	    Component[] c = leftTopPanel.getComponents();
	    for (int i=0; i<c.length; i++) 
	    {
	    	Statement stmt = new Statement(c[i], "setText", new Object[] { txt[i] } );
	    	try 
	    	{
	    		stmt.execute();
	    	} 
	    	catch(Exception exc) 
	    	{ 
	    		exc.printStackTrace(); 
	    	}
	    }
	    leftTopPanel.invalidate();
	  }
	private void localizeRight(Locale loc) 
	{
	    Locale.setDefault(loc);
	    ResourceBundle info =
	                   ResourceBundle.getBundle("photocell.Info");  
	    String[] txt = {info.getString("darkTheme"),"", "", info.getString("exitButton")};
	    
	    Component[] c = rightTopPanel.getComponents();
	    for (int i=0; i<c.length; i++) 
	    {
	    	Statement stmt = new Statement(c[i], "setText", new Object[] { txt[i] } );
	    	try 
	    	{
	    		stmt.execute();
	    	} 
	    	catch(Exception exc) 
	    	{ 
	    		exc.printStackTrace();
	    	}
	    }
	    rightTopPanel.invalidate();
	  }
	private void localizePhotoEffect(Locale loc) 
	{
	    Locale.setDefault(loc);
	    ResourceBundle info =
	                   ResourceBundle.getBundle("photocell.Info");  
	    String[] txt = {
	                       info.getString("startButton"),
	                       info.getString("stopButton")
	                   };
	    Component[] c = photoEffectPanel.getComponents();
	    for (int i=0; i<2; i++) {
	      Statement stmt = new Statement(c[i], "setText",
	                            new Object[] { txt[i] } );
	      try {
	        stmt.execute();
	      } catch(Exception exc) { exc.printStackTrace(); }
	    }
	    photoEffectPanel.invalidate();
	    
	    
	  }
	private void localizeSettings(Locale loc) 
	{
	    Locale.setDefault(loc);
	    ResourceBundle info =
	                   ResourceBundle.getBundle("photocell.Info");  
	    String[] txt = {info.getString("waveLength"), info.getString("waveFrequency"), info.getString("waveIntensity"), info.getString("energy") };
	    
	    Component[] c = photoCellSettingsPanel.panelWaveLength.getComponents();
	    Component[] c1 = photoCellSettingsPanel.panelWaveFrequency.getComponents();
	    Component[] c2 = photoCellSettingsPanel.panelLightIntensity.getComponents();
	    Component[] c3 = photoCellSettingsPanel.panelElectroMagneticWaveInfo.getComponents();
	    Statement stmt = new Statement(c[0], "setText",new Object[] { txt[0] } );
	    Statement stmt1 = new Statement(c1[0], "setText",new Object[] { txt[1] } );
	    Statement stmt2 = new Statement(c2[0], "setText",new Object[] { txt[2] } );
	    Statement stmt3 = new Statement(c3[0], "setText",new Object[] { txt[3] } );
	    try 
	    {
	        stmt.execute();
	        stmt1.execute();
	        stmt2.execute();
	        stmt3.execute();
	     } 
	    catch(Exception exc) 
	    { 
	    	  exc.printStackTrace(); 
	    }
	    
	    photoCellSettingsPanel.panelWaveLength.invalidate();
	    photoCellSettingsPanel.panelWaveFrequency.invalidate();
	    photoCellSettingsPanel.panelLightIntensity.invalidate();
	    photoCellSettingsPanel.panelElectroMagneticWaveInfo.invalidate();
	    
	    photoCellSettingsPanel.error= info.getString("error");
	    photoCellSettingsPanel.numbers= info.getString("numbers");
	    photoCellSettingsPanel.kom1= info.getString("kom1");
	    photoCellSettingsPanel.kom2= info.getString("kom2");
	    photoCellSettingsPanel.kom3= info.getString("kom3");
	  }
	private void localizeMenu(Locale loc) 
	{
	    Locale.setDefault(loc);
	    ResourceBundle info =ResourceBundle.getBundle("photocell.Info");  
	    String[] txt = {info.getString("showData"), info.getString("theoryInfo"), info.getString("aboutProgram"), info.getString("Sodium"),info.getString("Rubidium"),
	                       info.getString("Caesium"), info.getString("Calcium"), info.getString("Potassium")};
	    
	    Statement stmt = new Statement(showData, "setText",new Object[] { txt[0] } );
	    Statement stmt1 = new Statement(moreInformation, "setText",new Object[] { txt[1] } );
	    Statement stmt2 = new Statement(aboutProgram, "setText",new Object[] { txt[2] } );
	    Statement stmt3 = new Statement(elementSodium, "setText",new Object[] { txt[3] } );
	    Statement stmt4 = new Statement(elementRubidium, "setText",new Object[] { txt[4] } );
	    Statement stmt5 = new Statement(elementCaesium, "setText",new Object[] { txt[5] } );
	    Statement stmt6 = new Statement(elementCalcium, "setText",new Object[] { txt[6] } );
	    Statement stmt7 = new Statement(elementPotassium, "setText",new Object[] { txt[7] } );
	    try 
	    {
	    	stmt.execute();
	        stmt1.execute();
	        stmt2.execute();
	        stmt3.execute();
	        stmt4.execute();
	        stmt5.execute();
	        stmt6.execute();
	        stmt7.execute();
	    } 
	    catch(Exception exc) 
	    { 
	    	exc.printStackTrace(); 
	    }
	    showData.invalidate();
	    moreInformation.invalidate();
	    aboutProgram.invalidate();
	    elementSodium.invalidate();
	    elementRubidium.invalidate();
	    elementCaesium.invalidate();
	    elementCalcium.invalidate();
	    elementPotassium.invalidate();
	    AboutAuthors=info.getString("aboutAuthors");
	    ExitQuestion=info.getString("ExitQuestion");
	    exit=info.getString("exit");
	   
	    information=info.getString("aboutProgram");

	}
	private void localizeButton(Locale loc) 
	{
	    Locale.setDefault(loc);
	    ResourceBundle info =ResourceBundle.getBundle("photocell.Info");  
	    
	    String[] txt = {info.getString("SavePlot"), info.getString("SavePDF")};
	    
	    Statement stmt = new Statement(rightTopPane.savrAsPDFButton, "setText",new Object[] { txt[1] } );
	    try 
	    {
	    	stmt.execute();

		} 
	    catch(Exception exc) 
	    { 
	    	exc.printStackTrace(); 
	    }
	    Statement stmt1 = new Statement(rightTopPane.saveButton, "setText",new Object[] { txt[0] } );
	    try 
	    {
	    	stmt1.execute();

	  	} 
	    catch(Exception exc) 
	    { exc.printStackTrace(); }
	    rightTopPane.savrAsPDFButton.invalidate();
	    rightTopPane.saveButton.invalidate();
	}
	  

	ActionListener locChanger =  new ActionListener() 
	{
	    public void actionPerformed(ActionEvent e) 
	    {
	      String symloc = e.getActionCommand();
	      String[] locArg = symloc.split("_");
	      localizeLeft(new Locale(locArg[0], locArg[1]));
	      localizeRight(new Locale(locArg[0], locArg[1]));
	      localizePhotoEffect(new Locale(locArg[0], locArg[1]));
	      localizeSettings(new Locale(locArg[0], locArg[1]));
	      localizeMenu(new Locale(locArg[0], locArg[1]));
	      localizeButton(new Locale(locArg[0], locArg[1]));
	      rightTopPane.localizeLabel(new Locale(locArg[0], locArg[1]));
	    }
	  };
}
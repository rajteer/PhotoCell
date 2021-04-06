package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.basic.BasicMenuBarUI;


public class Interface extends JFrame 
{
	JMenuBar menuBar;
	JMenu submenu;
	JMenuItem dane, infor, oprog;
	JMenuItem cs, yb, v, be, fe, ag;
	JButton menuButton, saveButton, exit;
	JCheckBox motyw;
	JComboBox<String> languageOption;
	String[] language = { "angielski", "francuski", "polski"};
	static JOptionPane okno = new JOptionPane();
	final JPopupMenu menu = new JPopupMenu();
	
	public Interface()
	{
		
		Font f = new Font("Monospaced", Font.BOLD, 12);
		Font f1 = new Font("Monospaced", Font.BOLD, 24);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//pasek menu
		menuBar = new JMenuBar();
		menuBar.setFont(f);
		
		//menu g��wne
		menuButton = new JButton("Menu");
		menuButton.setBounds(10, 30, 89, 23);
		menuButton.setFont(f);
		menuBar.add(menuButton);	
		dane = new JMenuItem("Wy�wietl dane");
		dane.setFont(f);
		menu.add(dane);
		infor = new JMenuItem("Informacje");
		infor.setFont(f);
		menu.add(infor);
		menu.addSeparator();
		submenu = new JMenu("Metal");
		submenu.setFont(f);
		menu.add(submenu);
		cs = new JMenuItem("Cs-Cez");
		cs.setFont(f);
		submenu.add(cs);
		yb = new JMenuItem("Yb-Iterb");
		yb.setFont(f);
		submenu.add(yb);
		v = new JMenuItem("V-Wanad");
		v.setFont(f);
		submenu.add(v);
		be = new JMenuItem("Be-Beryl");
		be.setFont(f);
		submenu.add(be);
		fe = new JMenuItem("Fe-�elazo");
		fe.setFont(f);
		submenu.add(fe);
		ag = new JMenuItem("Ag-Srebro");
		ag.setFont(f);
		submenu.add(ag);
		oprog = new JMenuItem("O programie");
		oprog.setFont(f);
		menu.add(oprog);
		
		//przycisk zapisu danych pomiarowych
		saveButton= new JButton("Zapisz dane");
		saveButton.setFont(f);
		menuBar.add(saveButton);
		
		//przerwa
		menuBar.add(Box.createRigidArea(new Dimension(300,30)));
		
		//zmiana motywu aplikacji
		JCheckBox motyw=new JCheckBox("Tryb ciemny");	
		motyw.setFont(f);
		menuBar.add(motyw);
		
		//zmiana j�zyka
		String[] language = { "angielski", "francuski", "polski"};
		languageOption = new JComboBox<String>(language);
		languageOption.setSelectedIndex(2);
		languageOption.setFont(f);
		languageOption.setPreferredSize(new Dimension(10,20));
	    menuBar.add(languageOption);
		
	    exit = new JButton("Zako�cz");
	    exit.setBackground(Color.RED);
	    exit.setFont(f);
		menuBar.add(exit);
		
		setJMenuBar(menuBar);
	
		
		//Zdarzenia
		menuButton.addMouseListener((MouseListener) new MouseAdapter() 
		{
			public void mouseReleased(MouseEvent e)
			{
		    if ( e.getButton() == 1 )
		    {
		    	menu.show(e.getComponent(), e.getX(), e.getY());
		    }
		    }
		});
		oprog.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(
                            okno, "Aplikacja wykonana przez zesp� Fotokom�rka w ramach projektu na przedmiot Programowanie Obiektowe \n Hello I'm Julia Nice to meet you",
                            "Informacje",
                            JOptionPane.PLAIN_MESSAGE);
				
			}
		});
		
		exit.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				int n = JOptionPane.showConfirmDialog(
						okno, "Czy pewno zako�czy�?",
                        "Zako�cz",
                        JOptionPane.YES_NO_OPTION);
	
				if (n == JOptionPane.YES_OPTION) 
		        	dispose();	
			}
		});

		
	}
	
	public static void main(String[] args)
	{
		//tworzenie g��wnego okna
		Interface mainFrame = new Interface();
		mainFrame.setLayout(new BorderLayout());
		//dodane panele
		PhotoEffect panel=new PhotoEffect();
		PhotoCellSettings panel1= new PhotoCellSettings();
		mainFrame.getContentPane().add(panel);
		mainFrame.add(panel1, BorderLayout.LINE_END);
		mainFrame.setBounds(500, 500, 900, 600);
		mainFrame.setVisible(true);
	}
}
package photocell;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class DataTable extends JFrame {
	
    JTable j;
    JPanel bottomPanel;
    static JButton saveButton;

	DataTable(DefaultTableModel listTableModel)
	{		
	        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        this.setLayout(new BorderLayout());
	        String name;
			
			name = Interface.getChoosedElement();
			
		
	        this.setTitle(name);
	  
	        j = new JTable(listTableModel);
	        j.setBounds(30, 40, 200, 300);
	        
	        j.setCellEditor(null);
	        
	        JScrollPane sp = new JScrollPane(j);
	        this.add(sp, BorderLayout.CENTER);
	        
	        bottomPanel = new JPanel();
	        saveButton = new JButton("Zapisz dane/ Save data");
	        bottomPanel.add(saveButton);
	        
	        saveButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					SaveData.saveAstxt();
				}
			});
	        
	        this.add(bottomPanel, BorderLayout.PAGE_END);
	        this.setSize(500, 400);
	        this.setVisible(true);
	}
}
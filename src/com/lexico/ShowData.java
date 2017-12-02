package com.lexico;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * This class is going to show data in a table and 
 * that's all 
 */
public abstract class ShowData extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JTable table;
	protected DefaultTableModel model;
	public ShowData() {
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(605, 393);
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		String column[] = {"Tipo","Variable","Valor","Estado","Tipo de Valor","Counter"};
		model= new DefaultTableModel(column,0);
		table =new JTable(model);	
		scrollPane.setViewportView(table);
	}
	public abstract void addData();
}

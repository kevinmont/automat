package com.lexico;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Tokens extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JTable table;
	protected DefaultTableModel model;
	public Tokens() {
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(605, 393);
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		String column[] = {"Tipo","Variable","Valor","Estado","Tipo de Valor"};
		model= new DefaultTableModel(column,0);
		table =new JTable(model);	
		scrollPane.setViewportView(table);
	}
	public void addData(Object data[]) {
		model.addRow(data);
	}
}

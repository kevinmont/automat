package com.lexico;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.ComponentOrientation;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextArea;
import com.lineNumber.TextLineNumber;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class Lexico extends JFrame {

	/**
	 * This serial version for the class
	 */
	private static final long serialVersionUID = 1L;
	private TextLineNumber lineNumber;
	private boolean isEdit, firstTime = true;
	private boolean isCompile;
	private static String nameFile = "nuevo.txt";
	private static File global = null;
	private LexicalAnalyzer analyse;

	public Lexico() {
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(605, 393);
		getContentPane().setLayout(null);
		init();
	}

	public static void main(String a[]) {
		new Lexico().setVisible(true);
	}

	private final void init() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, nameFile, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(10, 34, 580, 133);
		getContentPane().add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(64, 64, 64), 1, true));

		scrollPane.setViewportView(textArea);
		// create our object line number
		lineNumber = new TextLineNumber(textArea);
		scrollPane.setRowHeaderView(lineNumber);
		textArea.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				lineNumber.setUpdateFont(true);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				lineNumber.setUpdateFont(true);
				isEdit = true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				lineNumber.setUpdateFont(true);
			}
		});

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(2, 187, 595, 178);
		getContentPane().add(tabbedPane);

		JTextPane textPane = new JTextPane();
		tabbedPane.addTab("Console", new ImageIcon(Lexico.class.getResource("/com/lexico/assets/console.ico")),
				textPane, null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		menuBar.setBounds(0, 0, 597, 21);
		getContentPane().add(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("New");
		mnNewMenu.add(mntmNewMenuItem);
		addListeners(mntmNewMenuItem, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (isEdit) {
						message("Guardar el archivo","Advertencia");
				} else {
					textArea.setText("");
					renombrar(nameFile,scrollPane);
					firstTime = true;
					isCompile=false;
				}
			}
		});

		JMenuItem mntmNuevo = new JMenuItem("Open File");
		addListeners(mntmNuevo, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!isEdit) {
					FileDialog dialog = new FileDialog(Lexico.this, "Open File", FileDialog.LOAD);
					dialog.setVisible(true);
					global = new File(dialog.getDirectory() + dialog.getFile());
					renombrar(dialog.getFile(),scrollPane);
					textArea.setText("");
					try {
						FileReader s = new FileReader(global);
						BufferedReader ss = new BufferedReader(s);
						try {
							String line;
							while ((line = ss.readLine()) != null) {
								textArea.setText(textArea.getText() + line + "\r\n");
							}
							isEdit = false;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							try {
								ss.close();
								s.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					message("Archivo actual no guardado","Advertencia");
				}
			}
		});
		mnNewMenu.add(mntmNuevo);

		JMenuItem mntmSave = new JMenuItem("Save");
		addListeners(mntmSave, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveFile(textArea);
			}
		});
		mnNewMenu.add(mntmSave);

		JMenu mnRun = new JMenu("Run");
		menuBar.add(mnRun);

		JMenuItem mntmCompile = new JMenuItem("Compile");
		addListeners(mntmCompile, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (global!=null && !isEdit) {
					try {
						analyse = new LexicalAnalyzer(new BufferedReader(new FileReader(global)));
						try {
							analyse.yylex();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						isCompile = true;
						textPane.setText("Compilled succesful");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
					message("Archivo no guardado","Error");
			}
		});
		mnRun.add(mntmCompile);

		JMenuItem mntmTokens = new JMenuItem("Tokens");
		addListeners(mntmTokens, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (isCompile) {
					// Execute this
					analyse.showData();
					analyse.setVisible(true);
				}
			}
		});
		mnRun.add(mntmTokens);

		JMenu mnExit = new JMenu("Exit");
		menuBar.add(mnExit);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Windows");
		addListeners(mntmNewMenuItem_1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnExit.add(mntmNewMenuItem_1);
	}
	
	private void saveFile(JTextArea textArea) {
		if (firstTime || isEdit) {
			if (firstTime) {
				FileDialog dialog = new FileDialog(Lexico.this, "Save", FileDialog.SAVE);
				dialog.setFile(nameFile);
				dialog.setVisible(true);
				String directory = dialog.getDirectory();
				if (directory != null)
					firstTime = false;
				global = new File(directory + nameFile);
			}

			if (global.exists())
				global.delete();
			try {
				global.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			FileWriter fileWriter = null;
			BufferedWriter write = null;
			try {
				fileWriter = new FileWriter(global);
				write = new BufferedWriter(fileWriter);
				for (String algo : textArea.getText().split("\\n")) {
					write.write(algo);
					write.newLine();
				}
				isEdit = false;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					write.close();
					fileWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	private void addListeners(JMenuItem menu, ActionListener s) {
		menu.addActionListener(s);
	}
	private void message(String message,String title) {
		JOptionPane.showMessageDialog(Lexico.this, message, title,JOptionPane.WARNING_MESSAGE);
	}
	private void renombrar(String rename,JComponent comp) {
	comp.setBorder(new TitledBorder(null, rename, TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}
}

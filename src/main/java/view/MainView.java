package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.SwingConstants;

import controller.Controller;
import query.Query;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;
import java.awt.event.MouseAdapter;

public class MainView {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}
	JList list;
	DbTableView dbTableView;
	JLabel lblTableName;
	JTextArea textArea;
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 452, 441);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		lblTableName = new JLabel("TableName");
		lblTableName.setHorizontalTextPosition(SwingConstants.CENTER);
		frame.getContentPane().add(lblTableName, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.EAST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{89, 0};
		gbl_panel.rowHeights = new int[]{42, 42, 42, 42, 42, 42, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tableName = getSelectedTable();
				if(tableName == null) return;
				try {
					//Create an activate dialog for selected dbTableView
					Class<?> clz = Class.forName("view.Dlg" 
							+ getSelectedTable());		
					IDlg dlg = (IDlg)clz.newInstance();		
					dlg.setVisible(true);
					//Get data from dialog
					Map<String, Object> map = dlg.getMap();
					//Send data to controller
					Controller.add(tableName, map);
					//Show results of operation
					textArea.setText("Added " +map.toString());	
					dbTableView.setDbTableModel(Controller.getAll(tableName));
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.BOTH;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 0;
		panel.add(btnAdd, gbc_btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tableName = getSelectedTable();
				if(tableName == null) return;
				//Map for selected row
				Map<String, Object> map = dbTableView.getSelectedRowMap();
				if(map == null) return;		
				try {
					//Create an activate dialog for selected dbTableView row
					Class clz = Class.forName("view.Dlg" + getSelectedTable());	
					Constructor<IDlg> cns = clz.getConstructor(Map.class);
					IDlg dlg = cns.newInstance(map);		
					dlg.setVisible(true);
					//Get new data from dialog
					Map<String, Object> newMap = dlg.getMap();
					//Send data to controller
					Controller.edit(tableName, newMap);
					//Show results of operation
					textArea.setText("OLd data " +map.toString());	
					dbTableView.setDbTableModel(Controller.getAll(tableName));
				} catch (Exception e1) {
					e1.printStackTrace();
				}	

			}
		});
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.fill = GridBagConstraints.BOTH;
		gbc_btnEdit.insets = new Insets(0, 0, 5, 0);
		gbc_btnEdit.gridx = 0;
		gbc_btnEdit.gridy = 1;
		panel.add(btnEdit, gbc_btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tableName = getSelectedTable();
				if(tableName == null) return;
				Map<String, Object> map = dbTableView.getSelectedRowMap();
				if(map == null) return;
				Controller.delete(tableName, map);
				dbTableView.setDbTableModel(Controller.getAll(tableName));
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.BOTH;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 2;
		panel.add(btnDelete, gbc_btnDelete);
		
		JButton btnQuery1 = new JButton("Query 1");
		btnQuery1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgQuery1 dlg = new DlgQuery1();
				dlg.setVisible(true);
				Map<String, Object> dlgmap = dlg.getMap();
				int year = (int) dlgmap.get("year");
				int avgrade = (int) dlgmap.get("avgrade");
				String query = Query.query1(avgrade, year);
				dbTableView.setDbTableModel(Controller.executeQuery(query));
			}
		});
		GridBagConstraints gbc_btnQuery1 = new GridBagConstraints();
		gbc_btnQuery1.fill = GridBagConstraints.BOTH;
		gbc_btnQuery1.insets = new Insets(0, 0, 5, 0);
		gbc_btnQuery1.gridx = 0;
		gbc_btnQuery1.gridy = 3;
		panel.add(btnQuery1, gbc_btnQuery1);
		
		JButton btnQuery2 = new JButton("Query 2");
		btnQuery2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgQuery2 dlg = new DlgQuery2();
				dlg.setVisible(true);
				Map<String, Object> dlgmap = dlg.getMap();
				String degree = (String) dlgmap.get("degree");
				int idLesson = (int) dlgmap.get("idLesson");
				String query = Query.query2(degree, idLesson);
				dbTableView.setDbTableModel(Controller.executeQuery(query));
			}
		});
		GridBagConstraints gbc_btnQuery2 = new GridBagConstraints();
		gbc_btnQuery2.fill = GridBagConstraints.BOTH;
		gbc_btnQuery2.insets = new Insets(0, 0, 5, 0);
		gbc_btnQuery2.gridx = 0;
		gbc_btnQuery2.gridy = 4;
		panel.add(btnQuery2, gbc_btnQuery2);
		
		JButton btnWriteQuery = new JButton("Write query");
		btnWriteQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgWriteQuery dlg = new DlgWriteQuery();
				dlg.setVisible(true);
				String query = dlg.getQuery();
				dlg.dispose();
				if(query.toLowerCase().indexOf("select")>=0) {
						dbTableView.setDbTableModel(Controller.
						executeQuery(query));	
						textArea.setText(query);
				}
				else {
					int res = Controller.executeUpdate(query);
					textArea.setText("Updated " + res);
				}

			}
		});
		GridBagConstraints gbc_btnWriteQuery = new GridBagConstraints();
		gbc_btnWriteQuery.fill = GridBagConstraints.BOTH;
		gbc_btnWriteQuery.gridx = 0;
		gbc_btnWriteQuery.gridy = 5;
		panel.add(btnWriteQuery, gbc_btnWriteQuery);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		frame.getContentPane().add(scrollPane_1, BorderLayout.CENTER);
		
		dbTableView = new DbTableView();
		scrollPane_1.setViewportView(dbTableView);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(22, 100));
		frame.getContentPane().add(scrollPane, BorderLayout.SOUTH);
		
		textArea = new JTextArea();
		textArea.setText("Information area.");
		scrollPane.setViewportView(textArea);
		
		list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				onMouseClickedList(arg0);
			}
		});
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Lesson", "Teacher", "Student"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		frame.getContentPane().add(list, BorderLayout.WEST);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("setDbFullName");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onSetDbFullName();
			}
		});
		mnFile.add(mntmNewMenuItem);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmInfo = new JMenuItem("Developer info");
		mntmInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				new Info().setVisible(true);
			}
		});
		mnAbout.add(mntmInfo);
	}

	protected void onSetDbFullName() {
	
	}
	
	private String getSelectedTable() {
		String tableName = (String) list.getSelectedValue();
		if(tableName == null) {
			JOptionPane.showMessageDialog(frame, 
					"Table was not Selected.");
		}
		return tableName;
	}
	
	protected void onMouseClickedList(MouseEvent e) {
		textArea.setText("");
		String tableName = getSelectedTable();
		if(tableName == null) return;
		if(!Controller.tableExist(tableName))
			Controller.createTable(tableName);
		lblTableName.setText("Table "+tableName);
		List<Map<String, Object>>  model = Controller.getAll(tableName);	
		dbTableView.setDbTableModel(model);
	}


}

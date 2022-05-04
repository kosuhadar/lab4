package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;

import controller.Controller;

import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgStudent extends JDialog implements IDlg{

	private final JPanel contentPanel = new JPanel();
	private JTextField fieldYear;
	private JTextField fieldName;
	private JTextField fieldTeacher;
	private JTextField fieldAvgrade;
	private Map<String, Object> map;
	private int idTeacher;
	private int idStudent;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgStudent dialog = new DlgStudent();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgStudent() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			fieldName = new JTextField();
			fieldName.setBorder(new TitledBorder(null, "Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_fieldName = new GridBagConstraints();
			gbc_fieldName.insets = new Insets(0, 0, 5, 5);
			gbc_fieldName.fill = GridBagConstraints.HORIZONTAL;
			gbc_fieldName.gridx = 0;
			gbc_fieldName.gridy = 1;
			contentPanel.add(fieldName, gbc_fieldName);
			fieldName.setColumns(10);
		}
		{
			fieldYear = new JTextField();
			fieldYear.setBorder(new TitledBorder(null, "Year", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_fieldYear = new GridBagConstraints();
			gbc_fieldYear.insets = new Insets(0, 0, 5, 0);
			gbc_fieldYear.fill = GridBagConstraints.HORIZONTAL;
			gbc_fieldYear.gridx = 1;
			gbc_fieldYear.gridy = 1;
			contentPanel.add(fieldYear, gbc_fieldYear);
			fieldYear.setColumns(10);
		}
		{
			fieldAvgrade = new JTextField();
			fieldAvgrade.setBorder(new TitledBorder(null, "Average grade", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_fieldAvgrade = new GridBagConstraints();
			gbc_fieldAvgrade.insets = new Insets(0, 0, 5, 5);
			gbc_fieldAvgrade.fill = GridBagConstraints.HORIZONTAL;
			gbc_fieldAvgrade.gridx = 0;
			gbc_fieldAvgrade.gridy = 3;
			contentPanel.add(fieldAvgrade, gbc_fieldAvgrade);
			fieldAvgrade.setColumns(10);
		}
		{
			fieldTeacher = new JTextField();
			fieldTeacher.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					mouseClickedFieldTeacher();
				}
			});
			fieldTeacher.setBorder(new TitledBorder(null, "Teacher", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_fieldTeacher = new GridBagConstraints();
			gbc_fieldTeacher.insets = new Insets(0, 0, 5, 0);
			gbc_fieldTeacher.fill = GridBagConstraints.HORIZONTAL;
			gbc_fieldTeacher.gridx = 1;
			gbc_fieldTeacher.gridy = 3;
			contentPanel.add(fieldTeacher, gbc_fieldTeacher);
			fieldTeacher.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onOK();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onCancel();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public DlgStudent(Map map) {
		this();
		idStudent = (Integer)map.get("id");
		idTeacher = (Integer)map.get("idTeacher");
		fieldName.setText((String)map.get("name"));
		fieldYear.setText(String.valueOf(map.get("year")));
		fieldAvgrade.setText(String.valueOf(map.get("avgrade")));
		fieldTeacher.setText((String)map.get("teacher"));
	}
	protected void onCancel() {
		map = null;
		setVisible(false);
	}

	protected void onOK() {
		if(idTeacher == 0) {
			JOptionPane.showMessageDialog(fieldTeacher, "Teacher not selected");
			return;
		}
		
		String name = fieldName.getText();
		String yeartxt = fieldYear.getText();
		String avgradetxt = fieldAvgrade.getText();
		int year = 0;
		int avgrade = 0;
		try {
			year = Integer.parseInt(yeartxt);
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(this, "\""
					+ yeartxt +"\" is not correct number");
			return;
		}
		try {
			avgrade = Integer.parseInt(avgradetxt);
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(this, "\""
					+ avgradetxt +"\" is not correct number");
			return;
		}
		map = new LinkedHashMap<>();
		map.put("id", idTeacher);
		map.put("name", name);
		map.put("year", year);
		map.put("avgrade", avgrade);
		map.put("idTeacher", idTeacher);
		setVisible(false);
	}
	public void clear() {
		fieldName.setText("");
		fieldYear.setText("");
		fieldAvgrade.setText("");
		fieldTeacher.setText("");
		idTeacher=0;
		map=null;
	}
	protected void mouseClickedFieldTeacher() {
		List<Map<String, Object>> listMap = 
				Controller.getAll("Teacher");
		DlgSelect ds = new DlgSelect(listMap);
		ds.setTitle("QueryLesson selection");
		ds.setVisible(true);
		Map map = ds.getMap();
		ds.dispose();
		idTeacher = (int) map.get("id");
		fieldTeacher.setText((String) map.get("name"));		
	}
	public Map<String, Object> getMap() {
		return map;
	}
}

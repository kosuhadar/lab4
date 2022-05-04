package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class DlgQuery1 extends JDialog{

	private final JPanel contentPanel = new JPanel();
	private JTextField fieldAvgrade;
	private JTextField fieldYear;
	private Map<String,Object> map;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgQuery1 dialog = new DlgQuery1();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgQuery1() {
		setTitle("Students with scholarship");
		setModal(true);
		setBounds(100, 100, 310, 121);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			fieldAvgrade = new JTextField();
			fieldAvgrade.setBorder(new TitledBorder(null, "Average Grade", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(fieldAvgrade);
			fieldAvgrade.setColumns(10);
		}
		{
			fieldYear = new JTextField();
			fieldYear.setBorder(new TitledBorder(null, "Year", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(fieldYear);
			fieldYear.setColumns(10);
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

	protected void onCancel() {
		map = null;
		setVisible(false);
	}

	protected void onOK() {
		String avgradetxt = fieldAvgrade.getText();
		String yeartxt = fieldYear.getText();
		int avgrade, year = 0;
		try {
			avgrade = Integer.parseInt(avgradetxt);			
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Please, enter correct number");
			return;
		}
		try {
			year = Integer.parseInt(yeartxt);			
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Please, enter correct number");
			return;
		}
		map = new LinkedHashMap<>();
		map.put("avgrade", avgrade);
		map.put("year", year);
		setVisible(false);
	}
	public Map<String, Object> getMap() {
		return map;
	}

}

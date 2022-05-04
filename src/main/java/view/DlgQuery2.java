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

public class DlgQuery2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField fieldDegree;
	private JTextField fieldIdlesson;
	private Map<String,Object> map;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgQuery2 dialog = new DlgQuery2();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgQuery2() {
		setModal(true);
		setTitle("Teachers of specific degree");
		setBounds(100, 100, 329, 148);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			fieldDegree = new JTextField();
			fieldDegree.setBorder(new TitledBorder(null, "Degree", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(fieldDegree);
			fieldDegree.setColumns(10);
		}
		{
			fieldIdlesson = new JTextField();
			fieldIdlesson.setBorder(new TitledBorder(null, "Lesson ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(fieldIdlesson);
			fieldIdlesson.setColumns(10);
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
		String degree = fieldDegree.getText();
		int idLesson = 0;
		try {
			idLesson = Integer.parseInt(fieldIdlesson.getText());			
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Please, enter correct number");
			return;
		}
		map = new LinkedHashMap<>();
		map.put("degree", degree);
		map.put("idLesson", idLesson);
		setVisible(false);
	}
	public Map<String, Object> getMap() {
		return map;
	}
}

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
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgLesson extends JDialog implements IDlg {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldTitle;
	private JTextField textFieldCredits;
	private JRadioButton rdbtnHasLabs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLesson dialog = new DlgLesson();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	private int lessonId;
	private Map<String,Object> map;

	public DlgLesson() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{215, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			textFieldTitle = new JTextField();
			textFieldTitle.setBorder(new TitledBorder(null, "Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_textFieldTitle = new GridBagConstraints();
			gbc_textFieldTitle.insets = new Insets(0, 0, 0, 5);
			gbc_textFieldTitle.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldTitle.gridx = 0;
			gbc_textFieldTitle.gridy = 0;
			contentPanel.add(textFieldTitle, gbc_textFieldTitle);
			textFieldTitle.setColumns(10);
		}
		{
			textFieldCredits = new JTextField();
			textFieldCredits.setBorder(new TitledBorder(null, "Credits", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_textFieldCredits = new GridBagConstraints();
			gbc_textFieldCredits.insets = new Insets(0, 0, 0, 5);
			gbc_textFieldCredits.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldCredits.gridx = 1;
			gbc_textFieldCredits.gridy = 0;
			contentPanel.add(textFieldCredits, gbc_textFieldCredits);
			textFieldCredits.setColumns(10);
		}
		{
			rdbtnHasLabs = new JRadioButton("Has labs");
			GridBagConstraints gbc_rdbtnHasLabs = new GridBagConstraints();
			gbc_rdbtnHasLabs.gridx = 2;
			gbc_rdbtnHasLabs.gridy = 0;
			contentPanel.add(rdbtnHasLabs, gbc_rdbtnHasLabs);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						onOk();
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

	public DlgLesson(Map<String, Object> map) {
		this();
		textFieldTitle.setText((String) map.get("name"));
		textFieldCredits.setText(String.valueOf((int)map.get("credits")));
		if((int)map.get("haslabs") == 1){
			rdbtnHasLabs.setSelected(true);
		}
		lessonId = (int)map.get("id");
	}
	public void clear() {
		textFieldTitle.setText("");
		textFieldCredits.setText("");
		rdbtnHasLabs.setSelected(false);
		lessonId=0;
		map=null;
	}
	private void onOk() {
		String name = textFieldTitle.getText();
		int credits = 0;
		try {
			credits = Integer.parseInt(textFieldCredits.getText());			
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Please, enter correct number");
			return;
		}
		boolean hasLabs = false;
		if (rdbtnHasLabs.isSelected()) {
			hasLabs = true;
		}
		map = new LinkedHashMap<>();
		map.put("id", lessonId);
		map.put("name", name);
		map.put("credits", credits);
		if (hasLabs == true) {
			map.put("hasLabs", 1);
		}
		else {
			map.put("hasLabs", 0);
		}
		setVisible(false);
	}
	private void onCancel() {
		map = null;
		setVisible(false);
	}
	public Map<String, Object> getMap(){
		return map;
	}

}

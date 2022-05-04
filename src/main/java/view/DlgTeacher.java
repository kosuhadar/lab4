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
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class DlgTeacher extends JDialog implements IDlg{

	private final JPanel contentPanel = new JPanel();
	private JTextField fieldDegree;
	private JTextField fieldName;
	private JTextField fieldLesson;
	private JTextField fieldRating;
	private Map<String, Object> map;
	private int idTeacher;
	private int idLesson;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgTeacher dialog = new DlgTeacher();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgTeacher() {
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
			fieldDegree = new JTextField();
			fieldDegree.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Degree", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			GridBagConstraints gbc_fieldDegree = new GridBagConstraints();
			gbc_fieldDegree.insets = new Insets(0, 0, 5, 0);
			gbc_fieldDegree.fill = GridBagConstraints.HORIZONTAL;
			gbc_fieldDegree.gridx = 1;
			gbc_fieldDegree.gridy = 1;
			contentPanel.add(fieldDegree, gbc_fieldDegree);
			fieldDegree.setColumns(10);
		}
		{
			fieldRating = new JTextField();
			fieldRating.setBorder(new TitledBorder(null, "Rating", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_fieldRating = new GridBagConstraints();
			gbc_fieldRating.insets = new Insets(0, 0, 5, 5);
			gbc_fieldRating.fill = GridBagConstraints.HORIZONTAL;
			gbc_fieldRating.gridx = 0;
			gbc_fieldRating.gridy = 3;
			contentPanel.add(fieldRating, gbc_fieldRating);
			fieldRating.setColumns(10);
		}
		{
			fieldLesson = new JTextField();
			fieldLesson.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					mouseClickedFieldLesson();
				}
			});
			fieldLesson.setBorder(new TitledBorder(null, "Lesson", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_fieldLesson = new GridBagConstraints();
			gbc_fieldLesson.insets = new Insets(0, 0, 5, 0);
			gbc_fieldLesson.fill = GridBagConstraints.HORIZONTAL;
			gbc_fieldLesson.gridx = 1;
			gbc_fieldLesson.gridy = 3;
			contentPanel.add(fieldLesson, gbc_fieldLesson);
			fieldLesson.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
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
	
	public DlgTeacher(Map map) {
		this();
		idTeacher = (Integer) map.get("id");
		idLesson = (Integer) map.get("idLesson");
		fieldName.setText((String)map.get("name"));
		fieldDegree.setText((String)map.get("grade"));
		fieldRating.setText(String.valueOf(map.get("rating")));
		fieldLesson.setText((String)map.get("lesson"));
	}
	protected void onCancel() {
		map = null;
		setVisible(false);
	}
	
	protected void onOK() {
		if(idLesson <0) {
			JOptionPane.showMessageDialog(fieldLesson, "Lesson not selected");
			return;
		}
		
		String name = fieldName.getText();
		String degree = fieldDegree.getText();
		String ratingtxt = fieldRating.getText();
		int rating = 0;
		try {
			rating = Integer.parseInt(ratingtxt);
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(this, "\""
					+ ratingtxt +"\" is not correct number");
			return;
		}
		
		map = new LinkedHashMap<>();
		map.put("id", idTeacher);
		map.put("name", name);
		map.put("degree", degree);
		map.put("rating", rating);
		map.put("idLesson", idLesson);
		setVisible(false);
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void clear() {
		fieldName.setText("");
		fieldDegree.setText("");
		fieldRating.setText("");
		fieldLesson.setText("");
		idTeacher=0;
		map=null;
	}
	
	protected void mouseClickedFieldLesson() {
		List<Map<String, Object>> listMap = 
				Controller.getAll("Lesson");
		DlgSelect ds = new DlgSelect(listMap);
		ds.setTitle("QueryLesson selection");
		ds.setVisible(true);
		Map map = ds.getMap();
		ds.dispose();
		idLesson = (int) map.get("id");
		fieldLesson.setText((String) map.get("name"));		
	}

}

package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Info extends JFrame {

	private JPanel contentPane;
	JPanel panelPhoto;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Info frame = new Info();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Info() {
		setTitle("Developer Info");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 264, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{314, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		panelPhoto = getPanelPhoto();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panelPhoto, gbc_panel);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textArea.setTabSize(4);
		textArea.setText("Косуха Дарина Владиславівна\r\nКБ-201\r\npinkjuice4@gmail.com\r\n0638183787\r\n");
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 1;
		contentPane.add(textArea, gbc_textArea);
	}
	private JPanel getPanelPhoto() {	
		if (panelPhoto == null) {
			//Create panel as anonymous class object
			panelPhoto = new JPanel() {
				//override method paintComponent in anonymous class
				public void paintComponent(Graphics g){
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					BufferedImage img;
					// set path to photo
					URL url = getClass().getResource("/resource/photo.jpg");
					try {
						//reading photo to image
						img = ImageIO.read(url);
					} catch (IOException e) {
						e.printStackTrace();
						return;
					}

					//image scaling according to panel size
					double k = (double)img.getHeight() / img.getWidth();
					int width = getWidth();
					int height = getHeight();
					if((double)height / width > k)
						height = (int) (width *k);
					else
						width = (int) (height /k);
					Image scaledImg = img.getScaledInstance(
							width, height, Image.SCALE_SMOOTH);
					//show photo
					g2d.drawImage(scaledImg,0,0,null);
				};
			};
		}
		return panelPhoto;

}
}

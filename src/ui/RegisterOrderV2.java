package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;

public class RegisterOrderV2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtKundeTlf;
	private JTextField txtProduktno;
	private JTextField txtMedarbejderid;
	private JTable table_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterOrderV2 frame = new RegisterOrderV2();
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
	public RegisterOrderV2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Registrer ordre:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{30, 0, 0};
		gbl_panel.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		txtKundeTlf = new JTextField();
		txtKundeTlf.setHorizontalAlignment(SwingConstants.LEFT);
		txtKundeTlf.setText("Kunde Tlf:");
		GridBagConstraints gbc_txtKundeTlf = new GridBagConstraints();
		gbc_txtKundeTlf.insets = new Insets(0, 0, 5, 0);
		gbc_txtKundeTlf.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtKundeTlf.gridx = 1;
		gbc_txtKundeTlf.gridy = 1;
		panel.add(txtKundeTlf, gbc_txtKundeTlf);
		txtKundeTlf.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Tilføj kunde");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 2;
		panel.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("Kunde");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 3;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		txtProduktno = new JTextField();
		txtProduktno.setHorizontalAlignment(SwingConstants.LEFT);
		txtProduktno.setText("ProduktNo:");
		GridBagConstraints gbc_txtProduktno = new GridBagConstraints();
		gbc_txtProduktno.insets = new Insets(0, 0, 5, 0);
		gbc_txtProduktno.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProduktno.gridx = 1;
		gbc_txtProduktno.gridy = 4;
		panel.add(txtProduktno, gbc_txtProduktno);
		txtProduktno.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Tilføj materiale");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 5;
		panel.add(btnNewButton_3, gbc_btnNewButton_3);
		
		txtMedarbejderid = new JTextField();
		txtMedarbejderid.setText("MedarbejderID:");
		GridBagConstraints gbc_txtMedarbejderid = new GridBagConstraints();
		gbc_txtMedarbejderid.insets = new Insets(0, 0, 5, 0);
		gbc_txtMedarbejderid.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMedarbejderid.gridx = 1;
		gbc_txtMedarbejderid.gridy = 6;
		panel.add(txtMedarbejderid, gbc_txtMedarbejderid);
		txtMedarbejderid.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Tilføj medarbejder");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.gridx = 1;
		gbc_btnNewButton_4.gridy = 7;
		panel.add(btnNewButton_4, gbc_btnNewButton_4);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_6 = new JLabel("Materiale pris:");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 0;
		panel_1.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("0 KR.");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 0;
		panel_1.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		JLabel lblNewLabel_3 = new JLabel("Total Timer:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 11;
		gbc_lblNewLabel_3.gridy = 0;
		panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_4 = new JLabel("0 KR.");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 12;
		gbc_lblNewLabel_4.gridy = 0;
		panel_1.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Total pris:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 1;
		panel_1.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JLabel lblNewLabel_8 = new JLabel("0 KR.");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 1;
		panel_1.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		JButton btnNewButton_1 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 11;
		gbc_btnNewButton_1.gridy = 1;
		panel_1.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.gridx = 12;
		gbc_btnNewButton.gridy = 1;
		panel_1.add(btnNewButton, gbc_btnNewButton);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{30, 100, 30, 100, 30, 0};
		gbl_panel_3.rowHeights = new int[]{25, 329, 25, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblNewLabel_9 = new JLabel("Materialer");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 1;
		gbc_lblNewLabel_9.gridy = 0;
		panel_3.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		JLabel lblNewLabel_2 = new JLabel("Medarbejdere:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 0;
		panel_3.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		table_1 = new JTable();
		GridBagConstraints gbc_table_1 = new GridBagConstraints();
		gbc_table_1.insets = new Insets(0, 0, 5, 5);
		gbc_table_1.fill = GridBagConstraints.BOTH;
		gbc_table_1.gridx = 1;
		gbc_table_1.gridy = 1;
		panel_3.add(table_1, gbc_table_1);
		
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 3;
		gbc_table.gridy = 1;
		panel_3.add(table, gbc_table);
	}

}

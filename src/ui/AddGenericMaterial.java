package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Material;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddGenericMaterial extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtPris;
	private JTextField txtSalgsPris;
	private JTextField txtBeskrivelse;
	private static JFrame previousScreen;
	private static Material material;
	private static int amountNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddGenericMaterial dialog = new AddGenericMaterial(material, previousScreen, amountNo);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param registerOrderV2 
	 * @param material 
	 * @param amountNo 
	 */
	public AddGenericMaterial(Material material, JFrame previousScreen, int amountNo) {
		setBounds(100, 100, 207, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel_3 = new JLabel("Du har valgt: " + material.getProductName());
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel = new JLabel("Intast købs pris: ");
			contentPanel.add(lblNewLabel);
		}
		{
			txtPris = new JTextField();
			contentPanel.add(txtPris);
			txtPris.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Indtast salgs pris: ");
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtSalgsPris = new JTextField();
			contentPanel.add(txtSalgsPris);
			txtSalgsPris.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Indtast beskrivelse: ");
			contentPanel.add(lblNewLabel_2);
		}
		{
			txtBeskrivelse = new JTextField();
			contentPanel.add(txtBeskrivelse);
			txtBeskrivelse.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Bekræft");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						double purchasePrice = Double.parseDouble(txtPris.getText());
						double salesPrice = Double.parseDouble(txtSalgsPris.getText());
						String description = txtBeskrivelse.getText(); 
						if (previousScreen instanceof RegisterOrderUI) {
							((RegisterOrderUI) previousScreen).addGenericMaterial(purchasePrice, salesPrice, description, material, amountNo);
							dispose();
						}
						else if (previousScreen instanceof LogOrder) {
							((LogOrder) previousScreen).addGenericMaterial(purchasePrice, salesPrice, description, material, amountNo);
							dispose();
						}
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

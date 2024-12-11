package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Employee;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class AddHoursDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtTimer;
	private JTextField txtMinutter;
	private static Employee employee;
	private static RegisterOrderV2 registerOrderV2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddHoursDialog dialog = new AddHoursDialog(employee, registerOrderV2);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param registerOrderV2 
	 * @param employee 
	 */
	public AddHoursDialog(Employee employee, RegisterOrderV2 registerOrderV2) {
		this.employee = employee;
		this.registerOrderV2 = registerOrderV2;
		setBounds(100, 100, 373, 175);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Indtast mængde arbejdet timer:");
			contentPanel.add(lblNewLabel);
		}
		{
			txtTimer = new JTextField();
			txtTimer.setText("Timer:");
			contentPanel.add(txtTimer);
			txtTimer.setColumns(10);
		}
		{
			txtMinutter = new JTextField();
			txtMinutter.setText("Minutter:");
			contentPanel.add(txtMinutter);
			txtMinutter.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				//TODO ryk til controller
				JButton cancelButton = new JButton("Bekræft");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						double minutes = Double.parseDouble(txtMinutter.getText());
						double hours = Double.parseDouble(txtTimer.getText());
						double conversionNo = minutes / 60;
						double result = 0;
						System.out.println(conversionNo);
						if (conversionNo > 0 && conversionNo <= 0.25) {
							result = hours + 0.25;
							returnHours(result);
						}
						else if(conversionNo > 0.25 && conversionNo <= 0.50) {
							result = hours + 0.50;
							returnHours(result);
						}
						else if (conversionNo > 0.50 && conversionNo <= 0.75) {
							result = hours + 0.75;
							returnHours(result);
						}
						else if (conversionNo > 0.75 && conversionNo <= 1.0) {
							result = hours + 1;
							returnHours(result);
						}
						else if (conversionNo < 0.0 || conversionNo > 1.0) {
						}
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton okButton = new JButton("Annuler");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			}
		}

	public void returnHours(double result) {
		BigDecimal hours = new BigDecimal(result);
		registerOrderV2.addEmployeeAndHours(employee, hours);
	}
}

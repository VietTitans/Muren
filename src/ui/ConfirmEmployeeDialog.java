package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Employee;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmEmployeeDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static RegisterOrderV2 registerOrderV2;
	private static Employee employee;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConfirmEmployeeDialog dialog = new ConfirmEmployeeDialog(employee, registerOrderV2);
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
	public ConfirmEmployeeDialog(Employee employee, RegisterOrderV2 registerOrderV2) {
		this.employee = employee;
		this.registerOrderV2 = registerOrderV2;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Er dette den rigtige medarbejder:");
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel employeelbl = new JLabel(employee.getfName() + " " + employee.getlName() + "?");
			contentPanel.add(employeelbl);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Bekræft");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AddHoursDialog addHoursDialog = new AddHoursDialog(employee, registerOrderV2);
						addHoursDialog.setVisible(true);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

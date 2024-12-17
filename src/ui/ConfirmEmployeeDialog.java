package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Employee;

public class ConfirmEmployeeDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JFrame previousScreen;
	private static Employee employee;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConfirmEmployeeDialog dialog = new ConfirmEmployeeDialog(employee, previousScreen);
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
	public ConfirmEmployeeDialog(Employee employee, JFrame previousScreen) {
		this.employee = employee;
		this.previousScreen = previousScreen;
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
				JButton cancelButton = new JButton("Bekræft");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AddHoursDialog addHoursDialog = new AddHoursDialog(employee, previousScreen);
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

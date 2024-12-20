package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Employee;

public class AddHoursDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtTimer;
	private JTextField txtMinutter;
	private static Employee employee;
	private static JFrame previousScreen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddHoursDialog dialog = new AddHoursDialog(employee, previousScreen);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public AddHoursDialog(Employee employee, JFrame previousScreen) {
		this.employee = employee;
		this.previousScreen = previousScreen;
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
			txtTimer.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txtTimer.setText("");
				}
				@Override
				public void focusLost(FocusEvent e) {
					String str = txtTimer.getText();
					int strLenght = str.length();
					if(strLenght < 1) {
						txtTimer.setText("Timer:");
					} else {
						
					}
				}
			});
			txtTimer.setText("Timer:");
			contentPanel.add(txtTimer);
			txtTimer.setColumns(10);
		}
		{
			txtMinutter = new JTextField();
			txtMinutter.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txtMinutter.setText("");
				}
			});
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
						boolean containsLetter = false;
				        
				        for (int i = 0; i < txtMinutter.getText().length(); i++) {
				            if (Character.isLetter(txtMinutter.getText().charAt(i))) {
				                containsLetter = true;
				                break;
				            }
				        }
				        
				        for (int i = 0; i < txtTimer.getText().length(); i++) {
				            if (Character.isLetter(txtTimer.getText().charAt(i))) {
				                containsLetter = true;
				                break;
				            }
				        }
				        
				        if (containsLetter == true) {
							NoHoursAddedDialog noHoursAdded = new NoHoursAddedDialog();
							noHoursAdded.setVisible(true);
				        }
				        else {
				        	
				        	double minutes = Double.parseDouble(txtMinutter.getText());
				        	double hours = Double.parseDouble(txtTimer.getText());
				        	if (minutes == 0 && hours == 0) {
				        		NoHoursAddedDialog noHoursAdded = new NoHoursAddedDialog();
				        		noHoursAdded.setVisible(true);
				        	}
				        	else {
				        		
				        		double conversionNo = minutes / 60;
				        		double result = 0;
				        		
				        		if (conversionNo == 0 ) {
				        			returnHours(hours);
				        		}
				        		else if (conversionNo > 0 && conversionNo <= 0.25) {
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
						}
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			}
		}

	public void returnHours(double result) {
		BigDecimal hours = new BigDecimal(result);
		if (previousScreen instanceof RegisterOrderUI) {
		((RegisterOrderUI) previousScreen).addEmployeeAndHours(employee, hours);
		dispose();
		}
		else if (previousScreen instanceof LogOrderUI) {
			((LogOrderUI) previousScreen).addEmployeeAndHours(employee, hours);
			dispose();
		}
	}
}

package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class CancelOrderDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static RegisterOrderV2 registerOrderFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CancelOrderDialog dialog = new CancelOrderDialog(registerOrderFrame);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CancelOrderDialog(RegisterOrderV2 registerOrderFrame) {
		this.registerOrderFrame = registerOrderFrame;
		setBounds(100, 100, 304, 106);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Er du sikker på du vil annulere nuværende ordre?");
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton jaButton = new JButton("Ja");
				jaButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Orders ordersFrame = new Orders();
						ordersFrame.setVisible(true);
						registerOrderFrame.dispose();
						dispose();
					}
				});
				jaButton.setActionCommand("OK");
				buttonPane.add(jaButton);
				getRootPane().setDefaultButton(jaButton);
			}
			{
				JButton nejButton = new JButton("Nej");
				nejButton.setActionCommand("Cancel");
				buttonPane.add(nejButton);
			}
		}
	}

}

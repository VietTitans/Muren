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
public class CancelOrderDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JFrame previousScreen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CancelOrderDialog dialog = new CancelOrderDialog(previousScreen);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CancelOrderDialog(JFrame previousScreen) {
		this.previousScreen = previousScreen;
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
						previousScreen.dispose();
						dispose();
					}
				});
				jaButton.setActionCommand("OK");
				buttonPane.add(jaButton);
				getRootPane().setDefaultButton(jaButton);
			}
			{
				JButton nejButton = new JButton("Nej");
				nejButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				nejButton.setActionCommand("Cancel");
				buttonPane.add(nejButton);
			}
		}
	}

}

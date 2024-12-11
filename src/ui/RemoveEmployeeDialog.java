package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RemoveEmployeeDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static RegisterOrderV2 registerOrderV2;
	private static JTable employeeTable;
	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RemoveEmployeeDialog dialog = new RemoveEmployeeDialog(registerOrderV2, employeeTable);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param employeeTable 
	 * @param registerOrderV2 
	 */
	public RemoveEmployeeDialog(RegisterOrderV2 registerOrderV2, JTable employeeTable) {
		this.registerOrderV2 = registerOrderV2;
		this.employeeTable = employeeTable;
		ArrayList<String> listData = new ArrayList<>();
		for (int row = 0; row < employeeTable.getRowCount(); row++) {
            String name = Integer.toString((int) employeeTable.getValueAt(row, 0)); // First column (Name)
            listData.add(name); // Add the name to the listData
        }
		
		String[] listArray = listData.toArray(new String[0]);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Hvilken række vil du fjerne");
			contentPanel.add(lblNewLabel);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				list = new JList();
				scrollPane.setViewportView(list);
				list.setListData(listArray);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Bekræft");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int [] removeList = list.getSelectedIndices();
						
						 // Reverse the array in place
				        int start = 0;
				        int end = removeList.length - 1;

				        while (start < end) {
				            // Swap the elements at start and end
				            int temp = removeList[start];
				            removeList[start] = removeList[end];
				            removeList[end] = temp;

				            start++;
				            end--;
				        }
				        registerOrderV2.removeRowEmployee(removeList);
				        dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

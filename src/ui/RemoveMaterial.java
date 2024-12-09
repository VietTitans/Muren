package ui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.awt.event.ActionEvent;

public class RemoveMaterial extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static RegisterOrderV2 registerOrderV2;
	private JList list;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RemoveMaterial dialog = new RemoveMaterial(registerOrderV2, table);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param registerOrderV2 
	 */
	public RemoveMaterial(RegisterOrderV2 registerOrderV2, JTable table) {
		this.table = table;
		this.registerOrderV2 = registerOrderV2;
		ArrayList<String> listData = new ArrayList<>();
		for (int row = 0; row < table.getRowCount(); row++) {
            String name = Integer.toString((int) table.getValueAt(row, 0)); // First column (Name)
            listData.add(name); // Add the name to the listData
        }
		
		String[] listArray = listData.toArray(new String[0]);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Hvilken række vil du fjerne?");
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
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
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
						registerOrderV2.removeRow(removeList);
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}

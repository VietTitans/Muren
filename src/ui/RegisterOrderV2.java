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
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.DataAccessException;
import controller.OrderController;
import model.Customer;
import model.Employee;
import model.Material;
import model.MaterialDescription;
import model.Price;
import model.StockMaterial;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.awt.Color;

public class RegisterOrderV2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtKundeTlf;
	private JTextField txtProduktno;
	private JTextField txtMedarbejderid;
	private JTable table_1;
	private JTable table;
	private JTextField txtMngde;
	private OrderController currentOrderController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					RegisterOrderV2 frame = new RegisterOrderV2();
					frame.setVisible(true);
				
			}
		});
	}


	public RegisterOrderV2() {
		try {
		this.currentOrderController = new OrderController();
		} catch (Exception e) {
			 e.printStackTrace();  // Log the exception
		      //  JOptionPane.showMessageDialog(this, "Failed to initialize OrderController.");
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		gbl_panel.rowHeights = new int[]{30, 0, 0, 0, 30, 0, 0, 0, 30, 0, 0, 30, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		txtKundeTlf = new JTextField();
		txtKundeTlf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtKundeTlf.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String str = txtKundeTlf.getText();
				int strLenght = str.length();
				if(strLenght < 1) {
					txtKundeTlf.setText("Kunde Tlf:");
				} else {
					
				}
			}
		});
		txtKundeTlf.setHorizontalAlignment(SwingConstants.LEFT);
		txtKundeTlf.setText("Kunde Tlf:");
		GridBagConstraints gbc_txtKundeTlf = new GridBagConstraints();
		gbc_txtKundeTlf.insets = new Insets(0, 0, 5, 0);
		gbc_txtKundeTlf.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtKundeTlf.gridx = 1;
		gbc_txtKundeTlf.gridy = 1;
		panel.add(txtKundeTlf, gbc_txtKundeTlf);
		txtKundeTlf.setColumns(10);
		
		JButton addCustomerButton = new JButton("Tilføj kunde");
		GridBagConstraints gbc_addCustomerButton = new GridBagConstraints();
		gbc_addCustomerButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_addCustomerButton.insets = new Insets(0, 0, 5, 0);
		gbc_addCustomerButton.gridx = 1;
		gbc_addCustomerButton.gridy = 2;
		panel.add(addCustomerButton, gbc_addCustomerButton);
		
		JLabel customerLabel = new JLabel("Kunde");
		GridBagConstraints gbc_customerLabel = new GridBagConstraints();
		gbc_customerLabel.insets = new Insets(0, 0, 5, 0);
		gbc_customerLabel.gridx = 1;
		gbc_customerLabel.gridy = 3;
		panel.add(customerLabel, gbc_customerLabel);
		
		txtProduktno = new JTextField();
		txtProduktno.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtProduktno.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String str = txtProduktno.getText();
				int strLenght = str.length();
				if(strLenght < 1) {
					txtProduktno.setText("ProduktNo:");
				} else {
					
				}
			}
		});
		txtProduktno.setHorizontalAlignment(SwingConstants.LEFT);
		txtProduktno.setText("MaterialNo:");
		GridBagConstraints gbc_txtProduktno = new GridBagConstraints();
		gbc_txtProduktno.insets = new Insets(0, 0, 5, 0);
		gbc_txtProduktno.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProduktno.gridx = 1;
		gbc_txtProduktno.gridy = 5;
		panel.add(txtProduktno, gbc_txtProduktno);
		txtProduktno.setColumns(10);
		
		txtMngde = new JTextField();
		txtMngde.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtMngde.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String str = txtMngde.getText();
				int strLenght = str.length();
				if(strLenght < 1) {
					txtMngde.setText("Mængde:");
				} else {
					
				}
			}
		});
		txtMngde.setText("Mængde:");
		GridBagConstraints gbc_txtMngde = new GridBagConstraints();
		gbc_txtMngde.insets = new Insets(0, 0, 5, 0);
		gbc_txtMngde.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMngde.gridx = 1;
		gbc_txtMngde.gridy = 6;
		panel.add(txtMngde, gbc_txtMngde);
		txtMngde.setColumns(10);
		
		JButton addMaterialBtn = new JButton("Tilføj materiale");
		addMaterialBtn.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_addMaterialBtn = new GridBagConstraints();
		gbc_addMaterialBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_addMaterialBtn.insets = new Insets(0, 0, 5, 0);
		gbc_addMaterialBtn.gridx = 1;
		gbc_addMaterialBtn.gridy = 7;
		panel.add(addMaterialBtn, gbc_addMaterialBtn);
		
		txtMedarbejderid = new JTextField();
		txtMedarbejderid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtMedarbejderid.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				String str = txtMedarbejderid.getText();
				int strLenght = str.length();
				if(strLenght < 1) {
					txtMedarbejderid.setText("MedarbejderID:");
				} else {
					
				}
			}
		});
		txtMedarbejderid.setText("MedarbejderID:");
		GridBagConstraints gbc_txtMedarbejderid = new GridBagConstraints();
		gbc_txtMedarbejderid.insets = new Insets(0, 0, 5, 0);
		gbc_txtMedarbejderid.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMedarbejderid.gridx = 1;
		gbc_txtMedarbejderid.gridy = 9;
		panel.add(txtMedarbejderid, gbc_txtMedarbejderid);
		txtMedarbejderid.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Tilføj medarbejder");
		btnNewButton_4.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_4.gridx = 1;
		gbc_btnNewButton_4.gridy = 10;
		panel.add(btnNewButton_4, gbc_btnNewButton_4);
		
		JButton btnNewButton_6 = new JButton("Fjern materiale");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveMaterial removeMaterialFrame= new RemoveMaterial(RegisterOrderV2.this, table_1);
				removeMaterialFrame.setVisible(true);
			}
		});
		btnNewButton_6.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_6.gridx = 1;
		gbc_btnNewButton_6.gridy = 12;
		panel.add(btnNewButton_6, gbc_btnNewButton_6);
		
		JButton btnNewButton_5 = new JButton("Fjern medarbejder");
		btnNewButton_5.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.gridx = 1;
		gbc_btnNewButton_5.gridy = 13;
		panel.add(btnNewButton_5, gbc_btnNewButton_5);
		
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
		
		JButton btnNewButton_1 = new JButton("Annuler ordre");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelOrderDialog cancelOrderFrame= new CancelOrderDialog(RegisterOrderV2.this);
					cancelOrderFrame.setVisible(true);
			}
			
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 11;
		gbc_btnNewButton_1.gridy = 1;
		panel_1.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton = new JButton("Gem ordre");
		btnNewButton.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.gridx = 12;
		gbc_btnNewButton.gridy = 1;
		panel_1.add(btnNewButton, gbc_btnNewButton);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 100, 0, 100, 0, 0};
		gbl_panel_3.rowHeights = new int[]{25, 329, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblNewLabel_9 = new JLabel("Materialer:");
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panel_3.add(scrollPane, gbc_scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"NR:", "ID:", "Name:", "Beskrivelse:", "M\u00E6ngde:", "Stk pris:", "Total pris:"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, String.class, String.class, Integer.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_1.getColumnModel().getColumn(3).setPreferredWidth(90);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 3;
		gbc_scrollPane_1.gridy = 1;
		panel_3.add(scrollPane_1, gbc_scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nr:", "ID:", "Navn:", "Timer:"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		addCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Customer customer = currentOrderController.findAndAddCustomerByPhoneNo(txtKundeTlf.getText());
						if (customer.getfName() == null) {
							CustomerNotFoundDialog customerNotFoundDialog = new CustomerNotFoundDialog();
							customerNotFoundDialog.setVisible(true);
							
						} 
						else {
							String name = "Navn: " + customer.getfName() + " " + customer.getlName();
							customerLabel.setText(name);
							txtKundeTlf.setText("Kunde Tlf:");	
						}
				} 
				catch (Exception b) {
					b.printStackTrace();  // This will print the stack trace of the exception to the console
				}
			}
		});

		addMaterialBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Price> salesPrices = new ArrayList<>();
				BigDecimal priceValue = new BigDecimal(10);
				Price salesPrice = new Price(priceValue);
				salesPrices.add(salesPrice);
				
				ArrayList<Price> purchasePrices = new ArrayList<>();
				BigDecimal purchasePriceValue = new BigDecimal(5);
				Price purchasePrice = new Price(purchasePriceValue);
				purchasePrices.add(purchasePrice);
				
				MaterialDescription materialDescription = new MaterialDescription("en ting");
				
				StockMaterial material = new StockMaterial(10, "ting", materialDescription, salesPrices, purchasePrices, 1, 100, 55);
//				Employee employee = new Employee();
//				int materialNo = Integer.parseInt(txtProduktno.getText());
				int amountNo = Integer.parseInt(txtMngde.getText());
				
				
				//try {
					//Material material = currentOrderController.findAndAddMaterialByMaterialNo(employee,materialNo,amountNo);
						if (material.getProductName() == null) {
							MaterialNotFoundDialog materialNotFoundDialog = new MaterialNotFoundDialog();
							materialNotFoundDialog.setVisible(true);
						}
						else {
							int newNr = table_1.getRowCount() + 1;
							BigDecimal totalBDPrice = priceValue.multiply(new BigDecimal(amountNo));
							Double totalPrice = totalBDPrice.doubleValue();
							Double saleprice = priceValue.doubleValue();
							
							Object[] newRow = {newNr,
									material.getMaterialNo(),
									material.getProductName(), 
									material.getMaterialDescription(),
									amountNo, 
									saleprice, 
									totalPrice};
							DefaultTableModel model = (DefaultTableModel) table_1.getModel();
							model.addRow(newRow);	
						}
				//}
			//catch (DataAccessException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			//}
		});
	}


	public void removeRow(int[] removeList) {
		//for (int index : removeList) {
			//System.out.println("index: " + index);
			//DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			//model.removeRow(index);
			//updateRowNumbers(table_1, index);

		//}
		// TODO Auto-generated method stub
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		for (int i = removeList.length - 1; i >= 0; i--) {
	        int index = removeList[i];
	        System.out.println("index: " + index);
	        System.out.println(table_1.getValueAt(index, 1));
	        model.removeRow(index);
	        System.out.println(table_1.getValueAt(index, 1));
	        updateRowNumbers(model, index);}
	}
	public void updateRowNumbers(DefaultTableModel model, int index) {
		//while (index < table_1.getRowCount()) {
			//table_1.setValueAt(index, index, 1);
		//}
		for (int i = index; i < table_1.getRowCount(); i++) {
			System.out.println(table_1.getValueAt(index, 1));
	        table_1.setValueAt(i + 1, i, 0); // Assuming column 1 is for row numbers
	        System.out.println(table_1.getValueAt(index, 1));
		}
	}

}

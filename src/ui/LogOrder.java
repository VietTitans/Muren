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
import controller.GeneralException;
import controller.OrderController;
import model.Customer;
import model.Employee;
import model.Material;
import model.MaterialDescription;
import model.MaterialLog;
import model.Price;
import model.StockMaterial;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.awt.Color;

public class LogOrder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtProduktno;
	private JTextField txtMedarbejderid;
	private JTable materialTable;
	private JTable employeeTable;
	private JTextField txtMngde;
	private OrderController currentOrderController;
	private Employee placeHolderEmployee;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					LogOrder frame = new LogOrder();
					frame.setVisible(true);
				
			}
		});
	}


	public LogOrder() {
		try {
		this.currentOrderController = new OrderController();
		currentOrderController.getCurrentOrder().setDeadLine(LocalDate.now());
		placeHolderEmployee = new Employee();
		placeHolderEmployee.setEmployeeId(1);
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
		gbl_panel.rowHeights = new int[]{30, 0, 0, 0, 30, 0, 0, 0, 30, 0, 0, 30, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel customerLabel = new JLabel("Kunde");
		GridBagConstraints gbc_customerLabel = new GridBagConstraints();
		gbc_customerLabel.insets = new Insets(0, 0, 5, 0);
		gbc_customerLabel.gridx = 1;
		gbc_customerLabel.gridy = 3;
		panel.add(customerLabel, gbc_customerLabel);
		
		JButton btnNewButton_5 = new JButton("Fjern medarbejder");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveEmployeeDialog removeEmployeeDialog = new RemoveEmployeeDialog(LogOrder.this, employeeTable);
				removeEmployeeDialog.setVisible(true);
			}
		});
		
		JButton btnNewButton_6 = new JButton("Fjern materiale");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveMaterial removeMaterialFrame= new RemoveMaterial(LogOrder.this, materialTable);
				removeMaterialFrame.setVisible(true);
			}
		});
		
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
		
				addMaterialBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//ArrayList<Price> salesPrices = new ArrayList<>();
						//BigDecimal priceValue = new BigDecimal(10);
						//Price salesPrice = new Price(priceValue);
						//salesPrices.add(salesPrice);
						
						//ArrayList<Price> purchasePrices = new ArrayList<>();
						//BigDecimal purchasePriceValue = new BigDecimal(5);
						//Price purchasePrice = new Price(purchasePriceValue);
						//purchasePrices.add(purchasePrice);
						
						//ArrayList<MaterialDescription> descriptions = new ArrayList<>();
						//MaterialDescription materialDescription = new MaterialDescription("en ting");
						//descriptions.add(materialDescription);
						
						//StockMaterial material = new StockMaterial(10, "ting", descriptions, salesPrices, purchasePrices, 1, 100, 55);
						int materialNo = Integer.parseInt(txtProduktno.getText());
						int amountNo = Integer.parseInt(txtMngde.getText());
						
						
						try {
							Material material = currentOrderController.findAndAddMaterialByMaterialNo(placeHolderEmployee,materialNo,amountNo);
								if (material == null) {
									MaterialNotFoundDialog materialNotFoundDialog = new MaterialNotFoundDialog();
									materialNotFoundDialog.setVisible(true);
								}
								else {
									int newNr = materialTable.getRowCount() + 1;
									BigDecimal totalBDPrice = material.getCurrentSalesPrice().getPreVATValue().multiply(new BigDecimal(amountNo));
									Double totalPrice = totalBDPrice.doubleValue();
									Double saleprice = material.getCurrentSalesPrice().getPreVATValue().doubleValue();
									
									Object[] newRow = {newNr,
											material.getMaterialNo(),
											material.getProductName(), 
											material.getCurrentMaterialDescription().getDescription(),
											amountNo, 
											saleprice, 
											totalPrice};
									DefaultTableModel model = (DefaultTableModel) materialTable.getModel();
									model.addRow(newRow);	
								}
						}
					catch (DataAccessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
		
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
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int employeeID = Integer.parseInt(txtMedarbejderid.getText());
				try {
					Employee employee = currentOrderController.findEmployeeByEmployeeId(employeeID, false);
					System.out.println(employee.getfName());
					if(employee.getfName() == null) {
						
					}
					else {
						ConfirmEmployeeDialog confirmEmployeeDialog = new ConfirmEmployeeDialog(employee, LogOrder.this);
						confirmEmployeeDialog.setVisible(true);
						
					}
				} catch (GeneralException e1) {
					 //TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DataAccessException e1) {
					 //TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_6.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_6.gridx = 1;
		gbc_btnNewButton_6.gridy = 13;
		panel.add(btnNewButton_6, gbc_btnNewButton_6);
		btnNewButton_5.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.gridx = 1;
		gbc_btnNewButton_5.gridy = 14;
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
				CancelOrderDialog cancelOrderFrame= new CancelOrderDialog(LogOrder.this);
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
		
		materialTable = new JTable();
		scrollPane.setViewportView(materialTable);
		materialTable.setModel(new DefaultTableModel(
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
		materialTable.getColumnModel().getColumn(3).setPreferredWidth(90);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 3;
		gbc_scrollPane_1.gridy = 1;
		panel_3.add(scrollPane_1, gbc_scrollPane_1);
		
		employeeTable = new JTable();
		scrollPane_1.setViewportView(employeeTable);
		employeeTable.setModel(new DefaultTableModel(
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Customer customer = currentOrderController.getCustomer();
					System.out.println(customer.getfName());
					if (customer != null && materialTable.getRowCount() > 0) {
						
						currentOrderController.getCurrentOrder().setOrderMadeBy(placeHolderEmployee);
						//ArrayList<MaterialLog> materialLogs = currentOrderController.getCurrentOrder().getMaterialLogs();
						//for (Material : materiakLogs) {
							
						//}
						currentOrderController.saveOrder();
					}
					else if (customer != null && employeeTable.getRowCount() > 0) {
						currentOrderController.getCurrentOrder().setOrderMadeBy(placeHolderEmployee);
						currentOrderController.saveOrder();
					}
					else {
						OrderNotCompleteDialog orderNotCompleteDialog = new OrderNotCompleteDialog();
						orderNotCompleteDialog.setVisible(true);
					}
				} catch (GeneralException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
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
		DefaultTableModel model = (DefaultTableModel) materialTable.getModel();
		for (int i = removeList.length - 1; i >= 0; i--) {
	        int index = removeList[i];
	        System.out.println("index: " + index);
	        System.out.println(materialTable.getValueAt(index, 1));
	        model.removeRow(index);
	        System.out.println(materialTable.getValueAt(index, 1));
	        updateRowNumbers(model, index);
			currentOrderController.removeMaterialLog(index);
			}
	}
	public void removeRowEmployee (int[] removeList) {
		DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
		for (int i = removeList.length - 1; i >= 0; i--) {
	        int index = removeList[i];
	        System.out.println("index: " + index);
	        System.out.println(employeeTable.getValueAt(index, 1));
	        model.removeRow(index);
	        System.out.println(employeeTable.getValueAt(index, 1));
	        updateRowNumbersEmployee(model, index);
			currentOrderController.removeHourLog(index);
			}
		
	}
	public void addEmployeeAndHours(Employee employee, BigDecimal hours) {
		System.out.println("timer: " +hours);
		int newNr = employeeTable.getRowCount() + 1;

		String name = employee.getfName() + " " + employee.getlName();
		
		Object[] newRow = {newNr,
				employee.getEmployeeId(),
				name, 
				hours};
		DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
		model.addRow(newRow);
		try {
			currentOrderController.addWorkHours(employee, hours);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateRowNumbers(DefaultTableModel model, int index) {
		//while (index < table_1.getRowCount()) {
			//table_1.setValueAt(index, index, 1);
		//}
		for (int i = index; i < materialTable.getRowCount(); i++) {
			System.out.println(materialTable.getValueAt(index, 1));
	        materialTable.setValueAt(i + 1, i, 0); // Assuming column 1 is for row numbers
	        System.out.println(materialTable.getValueAt(index, 1));
		}
	}
	public void updateRowNumbersEmployee(DefaultTableModel model, int index) {
		for (int i = index; i < employeeTable.getRowCount(); i++) {
			System.out.println(employeeTable.getValueAt(index, 1));
	        employeeTable.setValueAt(i + 1, i, 0); // Assuming column 1 is for row numbers
	        System.out.println(employeeTable.getValueAt(index, 1));
		}
		
	}

}
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.DataAccessException;
import controller.OrderController;
import model.Customer;
import model.Employee;
import model.GenericMaterial;
import model.Material;
import model.MaterialDescription;
import model.MaterialLog;
import model.Price;
import model.StockMaterial;

public class RegisterOrderUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtKundeTlf;
	private JTextField txtProduktno;
	private JTextField txtMedarbejderid;
	private JTable materialTable;
	private JTable employeeTable;
	private JTextField txtMngde;
	private OrderController currentOrderController;
	private Employee placeHolderEmployee;
	private JLabel materialPriceTotal;
	private JLabel totalOverallPrice;
	private JLabel hoursTotalPrice;
	private JLabel priceWithVAT;
	private JButton btnRemoveMaterial;
	private JButton btnRemoveHourLog;
	private JLabel lblCheckConnection;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
						
						try {
							RegisterOrderUI frame = new RegisterOrderUI();
							frame.setVisible(true);
						}
						 catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
			
			
					
				
			}
		});
	}


	public RegisterOrderUI() throws Exception {
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
		
		lblCheckConnection = new JLabel("New label");
		GridBagConstraints gbc_lblCheckConnection = new GridBagConstraints();
		gbc_lblCheckConnection.insets = new Insets(0, 0, 5, 0);
		gbc_lblCheckConnection.gridx = 1;
		gbc_lblCheckConnection.gridy = 0;
		panel.add(lblCheckConnection, gbc_lblCheckConnection);
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
		
		btnRemoveHourLog = new JButton("Fjern medarbejder");
		btnRemoveHourLog.setEnabled(false);
		btnRemoveHourLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveEmployeeDialog removeEmployeeDialog = new RemoveEmployeeDialog(RegisterOrderUI.this, employeeTable);
				removeEmployeeDialog.setVisible(true);
			}
		});
		
		btnRemoveMaterial = new JButton("Fjern materiale");
		btnRemoveMaterial.setEnabled(false);
		btnRemoveMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveMaterial removeMaterialFrame= new RemoveMaterial(RegisterOrderUI.this, materialTable);
				removeMaterialFrame.setVisible(true);
			}
		});
		
		txtProduktno = new JTextField();
		txtProduktno.setEnabled(false);
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
		txtMngde.setEnabled(false);
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
		
		JButton btnAddMaterial = new JButton("Tilføj materiale");
		btnAddMaterial.setEnabled(false);
		btnAddMaterial.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_btnAddMaterial = new GridBagConstraints();
		gbc_btnAddMaterial.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddMaterial.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddMaterial.gridx = 1;
		gbc_btnAddMaterial.gridy = 7;
		panel.add(btnAddMaterial, gbc_btnAddMaterial);
		
				btnAddMaterial.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int materialNo = Integer.parseInt(txtProduktno.getText());
						int amountNo = Integer.parseInt(txtMngde.getText());
						SwingWorkerFindAndAddMaterial swingWorker = new SwingWorkerFindAndAddMaterial(currentOrderController,materialNo,placeHolderEmployee,amountNo);
						try {
							Material material = swingWorker.doInBackground();
							//Anvend execute for at sikre swingworker er færdig før den fortsætter
							
							
							if (material == null) {
								MaterialNotFoundDialog materialNotFoundDialog = new MaterialNotFoundDialog();
								materialNotFoundDialog.setVisible(true);
							}
							else if (material instanceof StockMaterial) {
								int newNr = materialTable.getRowCount() + 1;
								BigDecimal totalBDPrice = material.getCurrentSalesPrice().getPreVATValue().multiply(new BigDecimal(amountNo));
								Double totalPrice = totalBDPrice.doubleValue();
								Double saleprice = material.getCurrentSalesPrice().getPreVATValue().doubleValue();
								
								Object[] newRow = {newNr,
										material.getMaterialNo(),
										material.getProductName(), 
										material.getCurrentMaterialDescription().getDescription(),
										amountNo, 
										(((StockMaterial) material).getAvailableAmount()),
										saleprice, 
										totalPrice};
								DefaultTableModel model = (DefaultTableModel) materialTable.getModel();
								model.addRow(newRow);	
								addToMaterialTotal();
								btnRemoveMaterial.setEnabled(true);
							}
							else if (material instanceof GenericMaterial) {
								AddGenericMaterial addGenericMaterial = new AddGenericMaterial(material , RegisterOrderUI.this, amountNo);
								addGenericMaterial.setVisible(true);
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}

						
					}
				});
		
		txtMedarbejderid = new JTextField();
		txtMedarbejderid.setEnabled(false);
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
		
		JButton btnAddEmployee = new JButton("Tilføj medarbejder");
		btnAddEmployee.setEnabled(false);
		btnAddEmployee.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_btnAddEmployee = new GridBagConstraints();
		gbc_btnAddEmployee.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddEmployee.gridx = 1;
		gbc_btnAddEmployee.gridy = 10;
		panel.add(btnAddEmployee, gbc_btnAddEmployee);
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int employeeID = Integer.parseInt(txtMedarbejderid.getText());
				try {
					Employee employee = currentOrderController.findEmployeeByEmployeeId(employeeID, false);
					if(employee == null) {//employee name changed to employee
						
					}
					else {
						ConfirmEmployeeDialog confirmEmployeeDialog = new ConfirmEmployeeDialog(employee, RegisterOrderUI.this);
						confirmEmployeeDialog.setVisible(true);
						
					}
				}  catch (DataAccessException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnRemoveCustomer = new JButton("Fjern kunde");
		btnRemoveCustomer.setEnabled(false);
		btnRemoveCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentOrderController.removeCustomer();
				customerLabel.setText("kunde");
				btnRemoveCustomer.setEnabled(false);;
				btnAddMaterial.setEnabled(false);
				btnAddEmployee.setEnabled(false);
				txtProduktno.setEnabled(false);
				txtMngde.setEnabled(false);
				txtMedarbejderid.setEnabled(false);
			}
		});
		GridBagConstraints gbc_btnRemoveCustomer = new GridBagConstraints();
		gbc_btnRemoveCustomer.insets = new Insets(0, 0, 5, 0);
		gbc_btnRemoveCustomer.gridx = 1;
		gbc_btnRemoveCustomer.gridy = 12;
		panel.add(btnRemoveCustomer, gbc_btnRemoveCustomer);
		btnRemoveMaterial.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_btnRemoveMaterial = new GridBagConstraints();
		gbc_btnRemoveMaterial.insets = new Insets(0, 0, 5, 0);
		gbc_btnRemoveMaterial.gridx = 1;
		gbc_btnRemoveMaterial.gridy = 13;
		panel.add(btnRemoveMaterial, gbc_btnRemoveMaterial);
		btnRemoveHourLog.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_btnRemoveHourLog = new GridBagConstraints();
		gbc_btnRemoveHourLog.gridx = 1;
		gbc_btnRemoveHourLog.gridy = 14;
		panel.add(btnRemoveHourLog, gbc_btnRemoveHourLog);
		
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
		
		materialPriceTotal = new JLabel("0");
		GridBagConstraints gbc_MaterialPriceTotal = new GridBagConstraints();
		gbc_MaterialPriceTotal.insets = new Insets(0, 0, 5, 5);
		gbc_MaterialPriceTotal.gridx = 1;
		gbc_MaterialPriceTotal.gridy = 0;
		panel_1.add(materialPriceTotal, gbc_MaterialPriceTotal);
		
		JLabel lblNewLabel_1 = new JLabel("DKK.");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 0;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		hoursTotalPrice = new JLabel("0");
		GridBagConstraints gbc_hoursTotalPrice = new GridBagConstraints();
		gbc_hoursTotalPrice.anchor = GridBagConstraints.EAST;
		gbc_hoursTotalPrice.insets = new Insets(0, 0, 5, 5);
		gbc_hoursTotalPrice.gridx = 11;
		gbc_hoursTotalPrice.gridy = 0;
		panel_1.add(hoursTotalPrice, gbc_hoursTotalPrice);
		hoursTotalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_4 = new JLabel("DKK.");
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
		
		totalOverallPrice = new JLabel("0");
		GridBagConstraints gbc_totalOverallPrice = new GridBagConstraints();
		gbc_totalOverallPrice.anchor = GridBagConstraints.WEST;
		gbc_totalOverallPrice.insets = new Insets(0, 0, 0, 5);
		gbc_totalOverallPrice.gridx = 1;
		gbc_totalOverallPrice.gridy = 1;
		panel_1.add(totalOverallPrice, gbc_totalOverallPrice);
		
		JButton btnNewButton_1 = new JButton("Annuler ordre");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelOrderDialog cancelOrderFrame= new CancelOrderDialog(RegisterOrderUI.this);
					cancelOrderFrame.setVisible(true);
			}
			
		});
		
		JLabel lblNewLabel_7 = new JLabel("DKK.");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 1;
		panel_1.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		JLabel lblNewLabel_3 = new JLabel("Inkl. Moms:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 4;
		gbc_lblNewLabel_3.gridy = 1;
		panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		priceWithVAT = new JLabel("0");
		GridBagConstraints gbc_priceWithVAT = new GridBagConstraints();
		gbc_priceWithVAT.insets = new Insets(0, 0, 0, 5);
		gbc_priceWithVAT.gridx = 5;
		gbc_priceWithVAT.gridy = 1;
		panel_1.add(priceWithVAT, gbc_priceWithVAT);
		
		JLabel lblNewLabel_8 = new JLabel("DKK:");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_8.gridx = 6;
		gbc_lblNewLabel_8.gridy = 1;
		panel_1.add(lblNewLabel_8, gbc_lblNewLabel_8);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 11;
		gbc_btnNewButton_1.gridy = 1;
		panel_1.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton = new JButton("Gem ordre");
		btnNewButton.setForeground(new Color(0, 0, 0));
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
				"NR:", "ID:", "Name:", "Beskrivelse:", "M\u00E6ngde:","Tilgængelig", "Stk pris:", "Total pris:"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, String.class, String.class, Integer.class,Integer.class, Double.class, Double.class
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
		
		addCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Customer customer = currentOrderController.findAndAddCustomerByPhoneNo(txtKundeTlf.getText());
						if (customer == null) {
							CustomerNotFoundDialog customerNotFoundDialog = new CustomerNotFoundDialog();
							customerNotFoundDialog.setVisible(true);
							
						} 
						else {
							String name = "Navn: " + customer.getfName() + " " + customer.getlName();
							customerLabel.setText(name);
							txtKundeTlf.setText("Kunde Tlf:");
							btnRemoveCustomer.setEnabled(true);
							btnAddMaterial.setEnabled(true);
							btnAddEmployee.setEnabled(true);
							txtProduktno.setEnabled(true);
							txtMngde.setEnabled(true);
							txtMedarbejderid.setEnabled(true);
							
							
						}
				} 
				catch (Exception b) {
					b.printStackTrace();  // This will print the stack trace of the exception to the console
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int orderNo = 0;
					Customer customer = currentOrderController.getCustomer();
					if (customer != null && materialTable.getRowCount() > 0) {
						
						currentOrderController.getCurrentOrder().setOrderMadeBy(placeHolderEmployee);
						//ArrayList<MaterialLog> materialLogs = currentOrderController.getCurrentOrder().getMaterialLogs();
						//for (Material : materiakLogs) {
							
						//}
						orderNo = currentOrderController.saveOrder();
						SaveOrder saveOrder = new SaveOrder(orderNo, RegisterOrderUI.this);
						saveOrder.setVisible(true);
					}
					else if (customer != null && employeeTable.getRowCount() > 0) {
						currentOrderController.getCurrentOrder().setOrderMadeBy(placeHolderEmployee);
						orderNo = currentOrderController.saveOrder();
						SaveOrder saveOrder = new SaveOrder(orderNo, RegisterOrderUI.this);
						saveOrder.setVisible(true);
					}
					else {
						OrderNotCompleteDialog orderNotCompleteDialog = new OrderNotCompleteDialog();
						orderNotCompleteDialog.setVisible(true);
					}
					
					
					
				}  catch (DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		updateTableMaterial();
		updateConnectionLabel(lblCheckConnection);
		
	}
	public void removeRow(int[] removeList) {
		DefaultTableModel model = (DefaultTableModel) materialTable.getModel();
		for (int i = removeList.length - 1; i >= 0; i--) {
	        int index = removeList[i];
	        model.removeRow(index);
	        updateRowNumbers(model, index);
			currentOrderController.removeMaterialLog(index);
			addToHoursTotal();
			}
		if (materialTable.getRowCount() < 1) {
			btnRemoveMaterial.setEnabled(false);
		}
	}
	public void removeRowEmployee (int[] removeList) {
		DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
		for (int i = removeList.length - 1; i >= 0; i--) {
	        int index = removeList[i];
	        model.removeRow(index);
	        updateRowNumbersEmployee(model, index);
			currentOrderController.removeHourLog(index);
			addToHoursTotal();
			if (employeeTable.getRowCount() < 1) {
				btnRemoveHourLog.setEnabled(false);
			}
		}
	}
	public void addEmployeeAndHours(Employee employee, BigDecimal hours) {
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
			addToHoursTotal();
			btnRemoveHourLog.setEnabled(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addGenericMaterial(double price, double price2, String description, Material material, int amountNo) {
		int newNr = materialTable.getRowCount() + 1;
		ArrayList<MaterialLog> materialLogs = currentOrderController.getCurrentOrder().getMaterialLogs();
		int index = materialLogs.size() - 1;
		LocalDateTime timeStamp = materialLogs.get(index).getTimeStamp();
		BigDecimal totalPriceDB = new BigDecimal(price2).multiply(new BigDecimal(amountNo));
		double totalPrice = totalPriceDB.doubleValue();
		
		BigDecimal purchasePriceBD = new BigDecimal(price);
		Price purchasePrice = new Price(timeStamp, purchasePriceBD);
		material.setCurrentPurchasePrice(purchasePrice);
		
		BigDecimal salesPriceDB = new BigDecimal(price2);
		Price salesPrice = new Price(timeStamp, salesPriceDB);
		material.setCurrentSalesPrice(salesPrice);
		
		MaterialDescription materialDescription = new MaterialDescription(description);
		material.setCurrentMaterialDescription(materialDescription);
		
		Object[] newRow = {newNr,
				material.getMaterialNo(),
				material.getProductName(), 
				description,
				amountNo,
				0,
				price2, 
				totalPrice};
		DefaultTableModel model = (DefaultTableModel) materialTable.getModel();
		model.addRow(newRow);	
		addToMaterialTotal();
		btnRemoveMaterial.setEnabled(true);
	}

	public void updateRowNumbers(DefaultTableModel model, int index) {
		for (int i = index; i < materialTable.getRowCount(); i++) {
	        materialTable.setValueAt(i + 1, i, 0); // Assuming column 1 is for row numbers
		}
	}
	public void updateRowNumbersEmployee(DefaultTableModel model, int index) {
		for (int i = index; i < employeeTable.getRowCount(); i++) {
	        employeeTable.setValueAt(i + 1, i, 0); // Assuming column 1 is for row numbers
		}
	}
	public void addToMaterialTotal() {
		BigDecimal price =currentOrderController.calculateTotalMaterialPrice();
		double totalPrice = price.doubleValue();
		materialPriceTotal.setText("" + totalPrice);
		addToTotalPrice();
	}
	public void addToTotalPrice() {
		BigDecimal price =currentOrderController.calculateTotalOrderPrice();
		double totalPrice = price.doubleValue();
		totalOverallPrice.setText("" + totalPrice);
		addPriceWithVAT(price);
	}
	public void addToHoursTotal() {
		BigDecimal price = currentOrderController.calculateTotalHoursPrice();
		double totalPrice = price.doubleValue();
		hoursTotalPrice.setText("" + totalPrice);
		addToTotalPrice();
	}
	public void addPriceWithVAT(BigDecimal price) {
		BigDecimal totalPriceWithVATBD = price.multiply(new BigDecimal(1.25));
		double totalPriceWithVAT = totalPriceWithVATBD.doubleValue();
		priceWithVAT.setText("" + totalPriceWithVAT);
	}
public  void updateConnectionLabel(JLabel connectionLabel) throws Exception {
	SwingWorkerCheckConnection checkConnection = new SwingWorkerCheckConnection(connectionLabel);
	 checkConnection.doInBackground();

	}
public void updateTableMaterial() throws Exception {
	SwingWorkerUpdateMaterial updateWorker = new SwingWorkerUpdateMaterial(materialTable);
	updateWorker.doInBackground();
	
}
}


package ui;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import controller.DataAccessException;
import controller.MaterialController;
import db.DBConnection;
import db.MaterialDB;
import model.Material;
import model.StockMaterial;

public class SwingWorkerUpdateMaterial extends SwingWorker<Object, Boolean> {
	private JTable materialTable;
	

private final static String query = 
"DECLARE @last_sync_version bigint = ?; " +
        "DECLARE @current_version bigint; " +
        "SELECT @current_version = MAX(SYS_CHANGE_VERSION) " +
        "FROM CHANGETABLE(CHANGES MaterialQuantity, @last_sync_version) as SomeAlias; " +
        "SELECT @current_version AS CurrentVersion;";
private long lastSyncVersion = 1; // Example: The version number from the last sync
	
public SwingWorkerUpdateMaterial(JTable materialTable) {
	super();
	this.materialTable = materialTable;
}
@Override
protected Object doInBackground() throws Exception {
	 Timer timer = new Timer();
     timer.scheduleAtFixedRate(new TimerTask() {
     	
         @Override
         public void run() {
             try {
            	 System.out.println(LocalDate.now());
         //   	 checkIfNewChanges();
            	 updateTables(materialTable);
             } catch (DataAccessException e) {
                 e.printStackTrace();
         }
            
            	 

     }
     } , 0, 10000);
	return null;
     
}
@Override
protected void process(List<Boolean> chunks) {
	// Updates the JLabel
	for (boolean status : chunks) {
		if (status) {
			System.out.println("Things have chancged");
			
		} else {
		}
	}
}//TODO Currently the returned version is always null
	public boolean checkIfNewChanges() throws DataAccessException, SQLException {
       
                    PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(query);

                    // Set the last sync version parameter
                    stmt.setLong(1, lastSyncVersion);

                    // Execute the query
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        long currentVersion = rs.getLong("CurrentVersion");
                        System.out.println(rs.getLong(1));
                        System.out.println(rs.getLong("CurrentVersion"));

                        // Check if changes have occurred
                        if (currentVersion > lastSyncVersion) {
                        	System.out.println("Changes detected");
                        	lastSyncVersion = currentVersion;
                        	return true;
                          
                        } else {
                            System.out.println("No changes detected");
                            return false;
                        }
                    }
					return false;
            
            }
	public void updateTables(JTable materialTable) throws DataAccessException {
		DefaultTableModel model = (DefaultTableModel) materialTable.getModel();
		ArrayList<Material> updatedMaterials = materialsToBeUpdated();
		
		
		
        // Iterate through the ArrayList and update the "Beskrivelse" column for matching rows
        for (Material material : updatedMaterials) {
        	if(material instanceof StockMaterial) {
        		StockMaterial Stockmaterial = (StockMaterial) material;
            // Iterate through the table rows and match based on materialNo
            for (int row = 0; row < model.getRowCount(); row++) {
                int tableMaterialNo = (int) model.getValueAt(row, 1); // Material No is in the first column (index 0)

                // Check if the materialNo matches
                if (tableMaterialNo == Stockmaterial.getMaterialNo()) {
                   
                    model.setValueAt(Stockmaterial.calculateAvailableAmount(),row,5);
                    
                    break; // Stop searching once a match is found
                }
            }
        }
	}
	}
	public ArrayList<Material> materialsToBeUpdated () throws DataAccessException{
		DefaultTableModel model = (DefaultTableModel) materialTable.getModel();
		ArrayList<Material> materialsToBeUpdated = new ArrayList<>();
		MaterialController materialController = new MaterialController();
		
		for(int row = 0; row < model.getRowCount(); row++) {		
			int materialNo = (int) model.getValueAt(row,1);
			Material material = materialController.findMaterialByMaterialNo(materialNo);
			if(material instanceof StockMaterial) {
				
				materialsToBeUpdated.add(material);
			}
	}
		return materialsToBeUpdated;
		
        }
}




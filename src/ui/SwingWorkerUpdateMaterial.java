package ui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


//The check for whether or not there have been changes in the DB is disabled, since it doesn't work

// "updateTables" is only meant to run if "checkIfNewChanges" publishes True
@Override
protected Object doInBackground() throws Exception {
	 Timer timer = new Timer();
     timer.scheduleAtFixedRate(new TimerTask() {
     	
         @Override
         public void run() {
             try {
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
			try {
				updateTables(materialTable);
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
		}
	}
}//TODO Currently the returned version is always null
	public void checkIfNewChanges() throws DataAccessException, SQLException {
       
                    PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(query);

                    // Set the last sync version parameter
                    stmt.setLong(1, lastSyncVersion);

                    // Execute the query
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        long currentVersion = rs.getLong("CurrentVersion");

                        // Check if changes have occurred
                        if (currentVersion > lastSyncVersion) {
                        	lastSyncVersion = currentVersion;
                        	publish(true);
                          
                        } else {
                            publish( false);
                        }
                    }
            
            }
	public void updateTables(JTable materialTable) throws DataAccessException {
		DefaultTableModel model = (DefaultTableModel) materialTable.getModel();
		ArrayList<Material> updatedMaterials = materialsToBeUpdated();
		
		
		
        // Iterate through the ArrayList and update the "Tilgængelig" column 
        for (Material material : updatedMaterials) {
        	if(material instanceof StockMaterial) {
        		StockMaterial Stockmaterial = (StockMaterial) material;
            // Iterate through the table rows and match based on materialNo
            for (int row = 0; row < model.getRowCount(); row++) {
                int tableMaterialNo = (int) model.getValueAt(row, 1); 

                if (tableMaterialNo == Stockmaterial.getMaterialNo()) {
                   
                    model.setValueAt(Stockmaterial.calculateAvailableAmount(),row,5);
                    
                    
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




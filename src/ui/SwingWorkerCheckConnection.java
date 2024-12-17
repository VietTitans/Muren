package ui;

import java.awt.Color;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

import controller.DataAccessException;
import db.DBConnection;

public class SwingWorkerCheckConnection extends SwingWorker<Void, Boolean> {

	 private final JLabel lblCheckConnection;


     public SwingWorkerCheckConnection(JLabel lblCheckConnection) {
         this.lblCheckConnection = lblCheckConnection;
     }

     @Override
     protected Void doInBackground() throws Exception {
         // Create a Timer to check every 10 seconds
         Timer timer = new Timer();
         timer.scheduleAtFixedRate(new TimerTask() {
             @Override
             public void run() {

                 try {
                     CheckDatabaseConnection();
                 } catch (SQLException | DataAccessException e) {
                     e.printStackTrace();
                 }
             }
         }, 0, 10000); // repeat after 10 secounds 
         return null;
     }

     @Override
     protected void process(List<Boolean> chunks) {
         // Updates the JLabel
         for (boolean status : chunks) {
             if (!status) {
            	 lblCheckConnection.setBackground(new Color(255, 0, 0)); // Red for failure
            	 lblCheckConnection.setOpaque(true);
            	 lblCheckConnection.setText("Not Connected");
             } else {
            	 lblCheckConnection.setBackground(new Color(0, 128, 0)); // Green for success
            	 lblCheckConnection.setText("Connected");
            	 lblCheckConnection.setOpaque(true);
             }
         }
     }

     // Method to check the database connection
     public void CheckDatabaseConnection() throws SQLException, DataAccessException {
         DatabaseMetaData data =  DBConnection.getInstance().getConnection().getMetaData();

         if (data != null) {
             publish(true); 
         } else {
             publish(false); 
         }
     }
 }



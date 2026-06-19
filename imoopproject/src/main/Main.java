package main;

import gui.MainMenuFrame;
import dao.DBConnection;
import java.awt.EventQueue;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        
        // Test the database connection first
        Connection conn = DBConnection.connect();
        
        if (conn != null) {
            System.out.println("database connection good");
            
            // Launch the Main Menu only if connection is successful
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                 try {
                        MainMenuFrame frame = new MainMenuFrame();
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            
        } else {
            System.out.println("run sudo systemctl enable --now mysqld on your terminal first");        
        }
    }
}
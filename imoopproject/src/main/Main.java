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
            System.out.println("aye gng it's running good job");
        } else {
            System.out.println("hey gng database is dead did you do sudo systemctl enable --now mysql??? DO IT NOW");
        }
        
        // Launch the Main Menu
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
    }
}
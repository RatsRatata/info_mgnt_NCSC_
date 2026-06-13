package main;

import dao.ClientInfoDAO;
import dao.DBConnection;
import model.ClientInfo;
import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        Connection conn = DBConnection.connect();
        if (conn != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Connection failed!");
        }
        
    	ClientInfoDAO dao = new ClientInfoDAO();
        List<ClientInfo> clients = dao.getAll();
        
        if (clients.isEmpty()) {
            System.out.println("No clients found.");
        } else {
            for (ClientInfo client : clients) {
                System.out.println(client.getReferenceCode() + " - " + client.getName());
            }
        }
    }
    
}
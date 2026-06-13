package gui;

import javax.swing.JTable;

public class UIUtils {
	
	public static void applyModernTableStyle(JTable table) {
		
		// This exists to simply make the tables look better
		
		table.setRowHeight(35);
		table.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 14));
		table.setShowVerticalLines(false);
		table.setGridColor(new java.awt.Color(230, 230, 230));
		table.setIntercellSpacing(new java.awt.Dimension(10, 0));
		table.getTableHeader().setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));
		table.getTableHeader().setBackground(new java.awt.Color(240, 240, 240));
		table.getTableHeader().setReorderingAllowed(false);
	}
}
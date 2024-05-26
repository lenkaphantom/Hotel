package view;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Hotel Management System");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800, 600);
			frame.setLayout(new MigLayout("wrap 2", "[grow][grow]", "[]10[]"));

			// Dodavanje komponenti
			frame.add(new JLabel("Username:"), "align label");
			frame.add(new JTextField(20), "growx");

			frame.add(new JLabel("Password:"), "align label");
			frame.add(new JPasswordField(20), "growx");

			frame.add(new JButton("Login"), "span, center");

			frame.setVisible(true);
		});
	}
}

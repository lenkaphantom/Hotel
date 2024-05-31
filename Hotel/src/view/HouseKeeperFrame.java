package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class HouseKeeperFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HouseKeeperFrame frame = new HouseKeeperFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HouseKeeperFrame() {
		setBackground(new Color(214, 204, 194));
		setForeground(new Color(102, 0, 34));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("img/hotel.png").getImage());
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[5px][grow]", "[5px,grow]"));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, "cell 1 0,grow");
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Manage Administrators", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Manage Employees", null, panel_1, null);
	}

}

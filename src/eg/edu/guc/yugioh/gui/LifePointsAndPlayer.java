package eg.edu.guc.yugioh.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LifePointsAndPlayer extends JPanel {
	
	private JLabel JLifePoints;
	private String Name;
	private boolean active;
	public LifePointsAndPlayer(String Name)
	{
		this.Name=Name;
		setLayout(new GridLayout(2,1));
		setVisible(true);
		JLifePoints = new JLabel("LifePoints: 8000");
		add(JLifePoints);   
		add(new JLabel(Name));
	
	}
	public JLabel getLifePoints() {
		return JLifePoints;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

}

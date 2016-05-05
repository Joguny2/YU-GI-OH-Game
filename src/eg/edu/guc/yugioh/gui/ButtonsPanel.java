package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.listeners.*;

public class ButtonsPanel extends JPanel 
{
	private InfoPanel IP;
	public ButtonsPanel(String p1,String p2)
	{
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setVisible(true);
		IP = new InfoPanel();
		add(IP);
		JPanel J1 = new JPanel();
		JButton SummonMonster = new JButton("Summon Monster");
		SummonMonster.addActionListener(new SummonMonsterAL());
		J1.add(SummonMonster);
		add(J1);
		JPanel J7 = new JPanel();
		JButton SwitchMonster = new JButton("  Switch Monster  ");
		SwitchMonster.addActionListener(new SwitchMonsterAL());
		J7.add(SwitchMonster);
		add(J7);
		JPanel J6 = new JPanel();
		JButton ActivateSpell = new JButton("Activate Spell");
		J6.add(ActivateSpell);
		ActivateSpell.addActionListener(new ActivateSpellAL());
		add(J6);
		JPanel J8 = new JPanel();
		JButton DeclareAttack = new JButton("DeclareAttack");
		DeclareAttack.addActionListener(new DeclareAttackAL());
		J8.add(DeclareAttack);
		add(J8);
		JPanel J4 = new JPanel();
		JButton SetMonster = new JButton("Set Monster");
		SetMonster.addActionListener(new SetMonsterAL());
		J4.add(SetMonster);
		add(J4);
		JButton EndPhase = new JButton("End Phase");
		EndPhase.addActionListener(new EndPhaseAL());
		JPanel J2 = new JPanel();
		J2.add(EndPhase);
		add(J2);
		JPanel J3 = new JPanel();
		JButton EndTurn = new JButton("End Turn");
		EndTurn.addActionListener(new EndTurnAL());
		J3.add(EndTurn);
		add(J3);
		JPanel J5 = new JPanel();
		JButton SetSpell = new JButton("Set Spell");
		SetSpell.addActionListener(new SetSpellAL());
		J5.add(SetSpell);
		add(J5);
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	public InfoPanel getIP() {
		return IP;
	}
	public void setIP(InfoPanel iP) {
		IP = iP;
	}
}

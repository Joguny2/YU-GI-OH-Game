package eg.edu.guc.yugioh.cards;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.player.Phase;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;

public class MonsterCard extends Card{
	private int level;
	private int defensePoints;
	private int attackPoints;
	private Mode mode;
	private boolean flag=false;
	public MonsterCard(String name, String description, int level, int attack, int defence)
	{
		super(name,description);
		this.level=level;
		this.attackPoints=attack;
		this.defensePoints=defence;
		this.mode=Mode.DEFENSE;
		
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getDefensePoints() {
		return defensePoints;
	}

	public void setDefensePoints(int defensePoints) {
		this.defensePoints = defensePoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}
	public String getInfo()
	{
		String result = "<html>"+super.getName();
		result += "<br>Attack Power : ";
		result+= this.attackPoints;
		result+="<br>Defence Power : ";
		result+= this.defensePoints;
		result+="<br>Level :";
		result+=this.level;
		return result;
		
	}
	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}
	public void setFlag(boolean flag)
	{
		this.flag=flag;
	}
	public boolean getFlag()
	{
		return flag;
	}
	public void action(MonsterCard monster)
	{
		if (flag==true)
		{
			JOptionPane.showMessageDialog(null, "You can't attack with same monster twice");
			return;
		}
    	flag=true;
		this.setMode(Mode.ATTACK);
		Mode m=monster.getMode();
		if(m==Mode.ATTACK)
		{
			
			if(this.getAttackPoints()>monster.getAttackPoints())
			{
				int l=Card.getBoard().getOpponentPlayer().getLifePoints();
				l-=(this.getAttackPoints()-monster.getAttackPoints());
				Card.getBoard().getOpponentPlayer().setLifePoints(l);
				Card.getBoard().getOpponentPlayer().getField()
					.getpField().getLP().getLifePoints().setText("Life Points :"+l);
				Card.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
			}
			else if(this.getAttackPoints()==monster.getAttackPoints())
			{
				Card.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
				Card.getBoard().getActivePlayer().getField().removeMonsterToGraveyard(this);
			}
			else
			{
				int l=Card.getBoard().getActivePlayer().getLifePoints();
				l-=(monster.getAttackPoints()-this.getAttackPoints());
				Card.getBoard().getActivePlayer().setLifePoints(l);
				Card.getBoard().getActivePlayer().getField()
					.getpField().getLP().getLifePoints().setText("Life Points :"+l);
				Card.getBoard().getActivePlayer().getField().removeMonsterToGraveyard(this);
			}
		}
		else if(m==Mode.DEFENSE)
		{
			if(this.getAttackPoints()>monster.getDefensePoints()){
				
				Card.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(monster);
			}
			else if(this.getAttackPoints()<monster.getDefensePoints())
			{
				int l=Card.getBoard().getActivePlayer().getLifePoints();
				l-=(monster.getDefensePoints()-this.getAttackPoints());
				Card.getBoard().getActivePlayer().setLifePoints(l);
			}
			
		}
		if(Card.getBoard().getActivePlayer().getLifePoints()<=0)
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
		else if(Card.getBoard().getOpponentPlayer().getLifePoints()<=0) 
			Card.getBoard().setWinner(Card.getBoard().getActivePlayer());   //score calculation
	}
    public void action()
    {
    	if (flag==true)
    	{
    		JOptionPane.showMessageDialog(null, "You can not attack with same monster twice");
    		//throw new MonsterMultipleAttackException();
    		return;
    	}
    	flag=true;
    	if(Card.getBoard().getOpponentPlayer().getField().getMonstersArea().isEmpty())
    	{
    		
    		this.setMode(Mode.ATTACK);
    		int l=Card.getBoard().getOpponentPlayer().getLifePoints();
    		l-=(this.getAttackPoints());
    		Card.getBoard().getOpponentPlayer().setLifePoints(l);
    		Card.getBoard().getOpponentPlayer().getField().getpField().getLP().getLifePoints().setText("Life Points "+l);
    		if(Card.getBoard().getOpponentPlayer().getLifePoints()<=0)
    			Card.getBoard().setWinner(Card.getBoard().getActivePlayer()); 
    		//Card.getBoard().getActivePlayer().getField().setPhase(Phase.MAIN2);
    	}
    }

}

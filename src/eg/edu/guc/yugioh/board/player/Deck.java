
package eg.edu.guc.yugioh.board.player;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.CardDestruction;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.DarkHole;
import eg.edu.guc.yugioh.cards.spells.GracefulDice;
import eg.edu.guc.yugioh.cards.spells.HarpieFeatherDuster;
import eg.edu.guc.yugioh.cards.spells.HeavyStorm;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.MonsterReborn;
import eg.edu.guc.yugioh.cards.spells.PotOfGreed;
import eg.edu.guc.yugioh.cards.spells.Raigeki;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;
import eg.edu.guc.yugioh.gui.PlayerField;
public class Deck {
	private static ArrayList<Card> monsters;  
	private static ArrayList<Card> spells; 
	private ArrayList<Card> deck;
	
	
	private static String monstersPath="Database-Monsters.csv";
    private static String spellsPath="Database-Spells.csv";
	
	public static ArrayList<Card> getMonsters() {
		return monsters;
	}
	public static void setMonsters(ArrayList<Card> monsters) {
		Deck.monsters = monsters;
	}
	
	public static ArrayList<Card> getSpells() {
		return spells;
	}
	public static void setSpells(ArrayList<Card> spells) {
		Deck.spells = spells;
	}
	
	public ArrayList<Card> getDeck() {
		return deck;
	}
	
	
	
	public static String getMonstersPath() {
		return monstersPath;
	}
	public static void setMonstersPath(String monstersPath) {
		Deck.monstersPath = monstersPath;
	}
	public static String getSpellsPath() {
		return spellsPath;
	}
	public static void setSpellsPath(String spellsPath) {
		Deck.spellsPath = spellsPath;
	}
	public Deck()throws IOException , UnexpectedFormatException
	{
		
		monsters=new ArrayList<Card>();
		spells=new ArrayList<Card>();
		Scanner sc=new Scanner(System.in);
		boolean flag=false;
		int counter=0;
		deck = new ArrayList<Card>();
		if(monsters.isEmpty())
		{  
			for(int i=0;i<3;i++)
			{
				try
				{
					monsters=loadCardsFromFile(monstersPath);
					flag=true;
					break;
				}
				catch(FileNotFoundException|UnexpectedFormatException e)
				{
					System.out.println("Please enter a correct path");
					monstersPath=sc.next();
					counter++;
				}
			
			}
			if(flag==false)
			{
				monsters=loadCardsFromFile(monstersPath);
			}
			
		}
		flag=false;
		if(spells.isEmpty())
		{
			 
				for(;counter<3;counter++){
				try{
				
				spells=loadCardsFromFile(spellsPath);
				flag=true;
				break;
				}
				catch(FileNotFoundException|UnexpectedFormatException e){
					System.out.println("Please enter a correct path");
					spellsPath=sc.next();
				
				}
				}
				if(flag==false)
					spells=loadCardsFromFile(spellsPath);
			
			}
			
				
		buildDeck(monsters,spells);
		this.shuffleDeck();
		}
		
	public ArrayList<Card> loadCardsFromFile(String path)throws IOException, UnexpectedFormatException
	{
		
		ArrayList<Card> r=new ArrayList<Card>();
		String currentLine = "";
        FileReader fileReader= new FileReader(path);
        BufferedReader br = new BufferedReader(fileReader);
        int c=1;
        while ((currentLine = br.readLine()) != null) 
        {
        	String [] s= currentLine.split(",");
        	if(s.length!=3 && s.length!=6){
        		br.close();
        		throw new MissingFieldException(path,c);
        	}
        	for(int i=1;i<s.length;i++){
        		if(s[i]==null || s[i].equals(" ") || s[i].equals(""))
        			throw new EmptyFieldException(path,c,i+1);
        	}
        	if(s[0].equals("Monster"))
            {
        		if(s.length==3){
        			br.close();
            		throw new MissingFieldException(path,c);
        		}
            	r.add(new MonsterCard(s[1],s[2],Integer.parseInt(s[5]),Integer.parseInt(s[3]),Integer.parseInt(s[4])));
            }
            else if(s[0].equals("Spell"))
            {
            	switch(s[1])
            	{
            	case "Card Destruction":r.add(new CardDestruction(s[1],s[2])); break;
            	case "Change Of Heart":r.add(new ChangeOfHeart(s[1],s[2]));break;
            	case "Dark Hole":r.add(new DarkHole(s[1],s[2]));break;
            	case "Graceful Dice":r.add(new GracefulDice(s[1],s[2]));break;
            	case "Harpie's Feather Duster":r.add(new HarpieFeatherDuster(s[1],s[2]));break;
            	case "Heavy Storm":r.add(new HeavyStorm(s[1],s[2]));break;
            	case "Mage Power":r.add(new MagePower(s[1],s[2]));break;
            	case "Pot of Greed":r.add(new PotOfGreed(s[1],s[2]));break;
            	case "Raigeki":r.add(new Raigeki(s[1],s[2]));break;
            	case "Monster Reborn":r.add(new MonsterReborn(s[1],s[2]));break;    
            	default:throw new UnknownSpellCardException(path, c,s[1]);
            	}
        }
            else {
            	throw new UnknownCardTypeException(path,c,s[0]);
            }
            c++;
	}
        br.close();
        return r;
	}
		
	
       private void buildDeck(ArrayList<Card> monsters, ArrayList<Card> spells)
        {
        	
        	Random r=new Random();
        	if(monsters.size() > 0)
        	for(int i=0;i<15;i++)
        	{
        		//int index = Math.random()*monsters.size();
        		int index = r.nextInt(monsters.size());
        		MonsterCard m =(MonsterCard)(monsters.get(index));
        		MonsterCard m2=new MonsterCard(m.getName(),m.getDescription(),m.getLevel(),m.getAttackPoints(),m.getDefensePoints());
        		deck.add(m2);
        		m2.setLocation(Location.DECK);
        		
        	}
        	if(spells.size() > 0)
        	for(int i=0;i<5;i++)
        	{
            	int index = r.nextInt(spells.size());
            	//make sure it doesnt remove it from the spells
                Card m =(spells.get(index));
                Card c = null;
                switch(m.getName())
                {
                case "Card Destruction":c = new CardDestruction(m.getName(),m.getDescription()); break;
            	case "Change Of Heart":c = new ChangeOfHeart(m.getName(),m.getDescription());break;
            	case "Dark Hole":c = new DarkHole(m.getName(),m.getDescription());break;
            	case "Graceful Dice":c = new GracefulDice(m.getName(),m.getDescription());break;
            	case "Harpie's Feather Duster":c = new HarpieFeatherDuster(m.getName(),m.getDescription());break;
            	case "Heavy Storm":c =  new HeavyStorm(m.getName(),m.getDescription());break;
            	case "Mage Power":c = new MagePower(m.getName(),m.getDescription());break;
            	case "Pot of Greed":c = new PotOfGreed(m.getName(),m.getDescription());break;
            	case "Raigeki":c = new Raigeki(m.getName(),m.getDescription());break;
            	case "Monster Reborn":c = new MonsterReborn(m.getName(),m.getDescription());    //last spell
            	//default:break;
                
                }
                if(c != null)
                {
                	c.setLocation(Location.DECK);
                	deck.add(c);
                }

            }		
        }
	


        
        private void shuffleDeck (){
        	Collections.shuffle(deck);
        }

        public ArrayList<Card> drawNCards(int n){
        	ArrayList<Card> result=new ArrayList<Card>();
        	for(int i=0;i<n;i++)
        	{
        		result.add(drawOneCard());
        	}
        	return result;
        }
	
	public Card drawOneCard()
	{
		
		if(!deck.isEmpty())
		{

			Card c=deck.remove(deck.size()-1);
			if (!Card.getBoard().isFlag())
				return c;
			Card.getBoard().getActivePlayer().getField().getpField().getDeckButton().setText("Deck "+deck.size());
			Card.getBoard().getActivePlayer().getField().getpField().revalidate();
			Card.getBoard().getActivePlayer().getField().getpField().repaint();
			return c;
		}
		 
		else
			return null;    //check whether this is correct
	}
	
	
	

} 

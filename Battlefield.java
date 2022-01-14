/***********************
 *
 *   Battlefield - class representing a battlefield populated with
 *                 ogres, centaurs, and dragons.
 *
 *   Author:   Your Name Here
 *   Date:     2018-Nov
 *
 ***********************/
 import java.io.*;
 import java.util.*;

public class Battlefield {

    // Use the FILL_CHAR for a space that contains no creature.
    // Use these chars for creatures so that your code will pass
    // the tests used for evaluating your program.
    public final char FILL_CHAR = '-';
    public final char OGRE      = 'o';
    public final char CENTAUR   = '7';
    public final char DRAGON    = 'X';
    
    public final char noMove = 'n';
    public final char moved = 'y';

    private char[][] field;
    private char[][] alreadyMovedField;

    public Battlefield(String fn) {
    	int numLine = 0;
    	int r = 0;
    	int c = 0;
    	char type;
    	int rpos = 0;
    	int cpos = 0;
        try {
        		File file = new File(fn);
        		Scanner filescan = new Scanner(file);
        		while(filescan.hasNextLine())
        		{
        			String line = filescan.nextLine();	
        			Scanner linescan = new Scanner(line);
        			while(linescan.hasNext())
        			{
        				if(numLine == 0)
						{
							linescan.useDelimiter("x");
							r = linescan.nextInt();
							c = linescan.nextInt();
							field = new char[r][c];
							alreadyMovedField = new char[r][c];
							fillArray(field,FILL_CHAR);
							fillArray(alreadyMovedField,'n');
							numLine++;			
						} else 
						{
							linescan.useDelimiter(",");
							type = linescan.next().charAt(0);
							rpos = linescan.nextInt();
							cpos = linescan.nextInt();
							modArray(field,rpos,cpos,type);
							numLine++;
						}
        			}
        		}
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }
    }

    ////////////////////////////////////////////////////////
    //two arrays to help construct the starting field configuration and modify afterwards
    private void modArray(char[][] field, int r, int c, char ch)
    {	
    	field[r][c] = ch;		
    }
    
    private void fillArray(char[][] arr, char ch)
    {
    	for(int i = 0; i<arr.length;i++)
    	{
    		for(int j = 0 ; j<arr[i].length; j++)
    		{
    			arr[i][j] = ch;
    		}	
    	}
    }
    ///////////////////////////////////////////////
    private int getRPOS(char[][] field, char c)
    {
    		int pos = 0;
    		for(int i =0; i<field.length;i++)
    		{
    			for(int j = 0; j<field[i].length;j++)
    			{
    				if(field[i][j] == c)
    				{
    					pos = i;	
    				}
    			}
    		}
    		return pos;
    }
    
    private int getCPOS(char[][] field, char c)
    {
    		int pos = 0;
    		for(int i =0; i<field.length;i++)
    		{
    			for(int j = 0; j<field[i].length;j++)
    			{
    				if(field[i][j] == c)
    				{
    					pos = j;	
    				}
    			}
    		}
    		return pos;
    }
    
    //this method handles the kills and movement of the ogre
    private boolean ogreMoveAndKill(char[][] field, int r, int c) //position of ogre given
    {
    	boolean x = false; //made kill
    	
    	if(!x) {
			if(((r-1) >= 0) && (r-1) < field.length && c >= 0 && c < field[r].length)
			{
				if(field[r-1][c] != FILL_CHAR && field[r-1][c] != OGRE)  //north
				{
					modArray(field,r-1,c,OGRE);
					modArray(field,r,c,FILL_CHAR);
					modArray(alreadyMovedField,r-1,c,moved);
					x = true;
				} 
			}
    	}
    	
    	if(!x) {
			if(((r-1) >= 0) && (r-1) < field.length && c+1 >= 0 && c+1 < field[r].length) 
			{
				if(field[r-1][c+1] != FILL_CHAR && field[r-1][c+1] != OGRE)  //northeast
				{
					modArray(field,r-1,c+1,OGRE);	
					modArray(field,r,c,FILL_CHAR);
					modArray(alreadyMovedField,r-1,c+1,moved);
					x = true;
				} 
			}
    	}
    	
    	if(!x) {
			if(((r) >= 0) && (r) < field.length && c+1 >= 0 && c+1 < field[r].length) 
			{
				if(field[r][c+1] != FILL_CHAR && field[r][c+1] != OGRE)  //east
				{
					modArray(field,r,c+1,OGRE);
					modArray(field,r,c,FILL_CHAR);
					modArray(alreadyMovedField,r,c+1,moved);
					x = true;
				} 
			}
    	}
    	
    	if(!x) {
			if(((r+1) >= 0) && (r+1) < field.length && c+1 >= 0 && c+1 < field[r].length) {
				if(field[r+1][c+1] != FILL_CHAR && field[r+1][c+1] != OGRE) //southeast
				{
					modArray(field,r+1,c+1,OGRE);
					modArray(field,r,c,FILL_CHAR);
					modArray(alreadyMovedField,r+1,c+1,moved);
					x = true;
				} 
			}
    	}
    
    	if(!x) {
			if(((r+1) >= 0) && (r+1) < field.length && c >= 0 && c < field[r].length)
			{			
				if(field[r+1][c] != FILL_CHAR && field[r+1][c] != OGRE) //south
				{
					modArray(field,r+1,c,OGRE);
					modArray(field,r,c,FILL_CHAR);
					modArray(alreadyMovedField,r+1,c,moved);
					x = true;
				} 
			}
    	}
    	
    	if(!x) {
			if(((r+1) >= 0) && (r+1) < field.length && c-1 >= 0 && c-1 < field[r].length) 
			{
				 if(field[r+1][c-1] != FILL_CHAR && field[r+1][c-1] != OGRE) //southwest
				{
					modArray(field,r+1,c-1,OGRE);
					modArray(field,r,c,FILL_CHAR);
					modArray(alreadyMovedField,r+1,c-1,moved);
					x = true;
				}
			} 
    	}
    	
    	if(!x) {
			if(((r) >= 0) && (r) < field.length && c-1 >= 0 && c-1 < field[r].length) 
			{
				if(field[r][c-1] != FILL_CHAR && field[r][c-1] != OGRE) //west
				{
					modArray(field,r,c-1,OGRE);
					modArray(field,r,c,FILL_CHAR);
					modArray(alreadyMovedField,r,c-1,moved);
					x = true;
				} 
			}
    	}
    	
    	if(!x) {
			if( ((r-1) >= 0) && (r-1) < field.length && c-1 >= 0 && c-1 < field[r].length)
			{
				if(field[r-1][c-1] != FILL_CHAR && field[r-1][c-1] != OGRE) //northwest
				{
					modArray(field,r-1,c-1,OGRE);
					modArray(field,r,c,FILL_CHAR);
					modArray(alreadyMovedField,r-1,c-1,moved);
					x = true;
				} 
			}
    	}
    	return x;
    }
    
    public boolean ogresMove() 
    {
    	int rpos = 0;
    	int cpos = 0;
        boolean moved = false;
        boolean x = false;
        for(int i = 0; i < field.length; i++)
        {
        	for(int j = 0; j<field[i].length;j++)
        	{
        		if(field[i][j] == OGRE && alreadyMovedField[i][j] == noMove)
        		{
        				rpos = i;
        				cpos = j;
        				x = ogreMoveAndKill(field,rpos,cpos);
        				if(x)
						{
							moved = true;
						}
        		}
        	}
        }    
       
        return moved;
    }
    
    
//////////////////////////////////////////////////////////////////////////////
    // This getter is needed for testing/grading purposes.
    public char[][] getField() {
        return field;
    }
    
    // The strike method is complete. You need not add code to it.
    public boolean strike() {

        boolean ogresMoved = ogresMove();
        boolean centaursMoved = centaursMove();
        boolean dragonsMoved = dragonsMove();

        boolean moveMade = false;
        if (ogresMoved || centaursMoved || dragonsMoved){
            moveMade = true;
        }
        fillArray(alreadyMovedField,noMove);
        
        return moveMade;
    }

    //////////////////////////////////////////////////////////////////////////////
   
    public boolean centaursMove() {
        boolean moved=false;
        int rpos = 0;
        int cpos = 0;
        boolean x = false;
        
         for(int i = 0; i < field.length; i++)
        {
        	for(int j = 0; j<field[i].length;j++)
        	{
        		if(field[i][j] == CENTAUR && alreadyMovedField[i][j] == noMove)
        		{
        				rpos = i;
        				cpos = j;
        				x = centaurMoveAndKill(field,rpos,cpos);
        				if(x)
						{
							moved = true;
						}
        		}
        	}
        }      
      
        return moved;
    }
    
    //this method handles the kills and movement of the centaur
    private boolean centaurMoveAndKill(char[][] field, int r, int c) //position of centaur
    {
    	int lastKillSpot = 0;
    	boolean x = false; //moved bool
    	if(((r+2) >= 0) && (r+2) < field.length && c-2 >= 0 && c-2 < field[r].length)   //southwest
    	{
    		if(field[r+2][c-2] != FILL_CHAR)
    		{
    			lastKillSpot = 1;
    			modArray(field,r+2,c-2,FILL_CHAR);
    			x = true;
    		}	
    	}
    	
    	if(((r-2) >= 0) && (r-2) < field.length && c-2 >= 0 && c-2 < field[r].length) //NW
    	{
    		if(field[r-2][c-2] != FILL_CHAR)
    		{
    			lastKillSpot = 2;
    			modArray(field,r-2,c-2,FILL_CHAR);
    			x = true;
    		}	
    	}
    	
    	if(((r-2) >= 0) && (r-2) < field.length && c+2 >= 0 && c+2 < field[r].length) //NE
    	{
    		if(field[r-2][c+2] != FILL_CHAR)
    		{
    			lastKillSpot = 3;
    			modArray(field,r-2,c+2,FILL_CHAR);
    			x = true;
    		}	
    	}
    	
    	if(((r+2) >= 0) && (r+2) < field.length && c+2 >= 0 && c+2 < field[r].length) //SE
    	{
    		if(field[r+2][c+2] != FILL_CHAR)
    		{
    			lastKillSpot = 4;
    			modArray(field,r+2,c+2,FILL_CHAR);
    			x = true;
    		}	
    	}
    	
    	
    	if(lastKillSpot == 1)
    	{
    		modArray(field,r,c,FILL_CHAR);
    		modArray(field,r+2,c-2,CENTAUR);
    		modArray(alreadyMovedField,r+2,c-2,moved);
    		x = true;
    	} else if(lastKillSpot == 2)
    	{
    		modArray(field,r,c,FILL_CHAR);
    		modArray(field,r-2,c-2,CENTAUR);
    		modArray(alreadyMovedField,r-2,c-2,moved);
    		x = true;
    	} else if(lastKillSpot == 3)
    	{
    		modArray(field,r,c,FILL_CHAR);
    		modArray(field,r-2,c+2,CENTAUR);
    		modArray(alreadyMovedField,r-2,c+2,moved);	
    		x = true;
    	} else if(lastKillSpot == 4)
    	{
    		modArray(field,r,c,FILL_CHAR);
    		modArray(field,r+2,c+2,CENTAUR);
    		modArray(alreadyMovedField,r+2,c+2,moved);
    		x = true;
    	}
    	return x;
    }
    // stub - don't change header, needed for testing
    //
    // Dragons obliterate everything in their row and column
    // then move 2 rows east and 2 rows north.
    // (This may mean they fly off the field)
    // When a dragon moves, if there is a centaur or ogre
    // on that space, the centaur or ogre kills the dragon.
    //
    // dragonsMove returns true if any dragons moved.
    public boolean dragonsMove() {
    	int rpos = 0;
    	int cpos = 0;
    	boolean x = false;
        boolean moved = false;
        
        for(int i = 0; i < field.length; i++)
        {
        	for(int j = 0; j<field[i].length;j++)
        	{
        		if(field[i][j] == DRAGON && alreadyMovedField[i][j] == noMove)
        		{
        				rpos = i;
        				cpos = j;
        				x = dragonMoveAndKill(field,rpos,cpos);
        				if(x)
					{
						moved = true;	
					}
        		}
        	}
        }
      
 
        return moved;
    }
    
    private void dragonClear(char[][] field, int r, int c)
    {
    		for(int i =0; i<field.length;i++)  //clears column
    		{
    			field[i][c] = FILL_CHAR;
    		}
    		for(int j = 0; j <field[r].length;j++) //clears row
    		{
    			field[r][j] = FILL_CHAR;
    		}
    		
    		//now the "+" should be cleared and made into fill_char
    }

    //handles  movements and killing of dragon
    private boolean dragonMoveAndKill(char[][] field, int r, int c) //dragon position
    {
    	boolean x = false;
    	dragonClear(field,r,c); //runs clearing method
    		if(((r-2) >= 0) && (r-2) < field.length && c+2 >= 0 && c+2 < field[r].length) {
				if(field[r-2][c+2] == CENTAUR || field[r-2][c+2] == OGRE || field[r-2][c+2] == DRAGON)
				{
					modArray(field,r,c,FILL_CHAR);	
					x = true;
					
				} else if(field[r-2][c+2] == FILL_CHAR)  //if spot dragon wants to move to is valid and empty
				{
					modArray(field,r-2,c+2,DRAGON); //put dragon there
					modArray(field,r,c,FILL_CHAR); //reset old spot 
					modArray(alreadyMovedField,r-2,c+2,moved); //update new spot on alreadyMovedField
					x = true;
				}	
    		} else 
    		{
    			modArray(field,r,c,FILL_CHAR); //this only runs if dragon moves off of the map, this clears the spot it was on	
    			x = true;
    		}
    			return x; //dragons will always clear and move and either die
    }
    
    public String toString() {
    	String blank = "";
    	for(int i = 0; i<field.length;i++)
    	{
    		for(int j= 0; j<field[i].length;j++)
    		{
    			blank += field[i][j] + " ";
    		}
    		blank += "\n";
    	}
        return blank;
    }
}

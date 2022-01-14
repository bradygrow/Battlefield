import java.io.*;
import java.util.Scanner;
public class test
{
public static void main(String[] args) throws IOException
{
	
	File file = new File("1field.dat");
        		char[][] field = new char[0][0];
        		int rowpos = 0;
        		int colpos = 0;
        		char type;
        		int row = 0;
        		int col = 0;
        		int linepos = 0;
        		Scanner scan = new Scanner(file);
        		while(scan.hasNextLine())
        		{
        			String line = scan.nextLine();
        			Scanner linescan = new Scanner(line);
						if(linepos == 0)
						{
							linescan.useDelimiter("x");	
						} else if(linepos > 0)
						{
							linescan.useDelimiter(",");	
						}
        			while(linescan.hasNext())
        			{
        					if(linepos == 0)
        					{
        						linepos++;
        						row = linescan.nextInt();
        						System.out.println(row);
        						
        						col = linescan.nextInt();
        						System.out.println(col);
        						
        						field = new char[row][col];
        						for(int i = 0; i < field.length; i++)
									{
										for(int j = 0; j < field[i].length; j++)
											{
												field[i][j] = '-';
											}
									}
        						
        					
        					}
        				
        					//field should be all fill_char now
        					else if(linepos > 0)
        					{
        							
        					}
        		}
        	}
        	printArray(field);
}
        	
        	 public static void printArray(char[][] arr)
    {
    	System.out.println();
    	for (char[] row : arr) 
    	{  
    		for (char ch: row) 
    		{  // cannot change an array with a for each loop
    			System.out.print(" " + ch);
            }
        System.out.println();
        }	   	
    }
}


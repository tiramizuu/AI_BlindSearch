
public class Puzzle {
	private char[][] number;
	private final int PUZZLESIZE = 3;
	private int holePositionX = -1,holePositionY = -1;
	
	public Puzzle(char a11,char a21,char a31,char a12,char a22,char a32,char a13,char a23,char a33)
	{
		number = new char[][]{{a11,a21,a31},{a12,a22,a32},{a13,a23,a33}};
		findHolePosition();
	}
	public Puzzle(char[][] number)
	{
		this.number = number;
	}
	public Puzzle()
	{
		this('1','2','3','4','5','6','7','8',' ');
	}
	/*private boolean isNumberOk() //check it's 1-8 and space and not duplicated
	{
		java.util.ArrayList<Character> list = new java.util.ArrayList<Character>();
		for(int i = 1 ; i <= 8 ; i++)
		{
			list.add((char)(0x30 + i));
		}
		for(int i = 0 ; i < PUZZLESIZE ; i++)
			for(int j = 0 ; j < PUZZLESIZE ; j++)
				if(! list.remove((Character)number[i][j])) return false;
		return true;
	}*/
	private void findHolePosition() //look for hole
	{
		for(int i = 0 ; i < PUZZLESIZE ; i++)
			for(int j = 0 ; j < PUZZLESIZE ; j++)
				if(number[i][j] == ' ') {
					setHolePositionX(i);
					holePositionY = j;
				}
	}
	private void swap(int xpos1,int ypos1,int xpos2,int ypos2) //swap 2 adjacent character
	{
		char temp = number[ypos1][xpos1];
		number[ypos1][xpos1] = number[ypos2][xpos2];
		number[ypos2][xpos2] = temp;
	}
	@Deprecated public void printPuzzle()
	{
		for(int i = 0 ; i < PUZZLESIZE ; i++)
		{
			for(int j = 0 ; j < PUZZLESIZE ; j++)
				System.out.print(number[i][j] + " ");
			System.out.println();
		}
	}
	public char numberAt(int xpos,int ypos)
	{
		return number[xpos][ypos];
	}
	public int getHolePositionX() {
		return holePositionX;
	}
	public void setHolePositionX(int holePositionX) {
		this.holePositionX = holePositionX;
	}
	public void moveHoleUp()
	{
		swap(holePositionX,holePositionY,holePositionX,holePositionY - 1);
		holePositionY--;
	}
	public void moveHoleDown()
	{
		swap(holePositionX,holePositionY,holePositionX,holePositionY + 1);
		holePositionY++;
	}
	public void moveHoleLeft()
	{
		swap(holePositionX,holePositionY,holePositionX - 1,holePositionY);
		holePositionX--;
	}
	public void moveHoleRight()
	{
		swap(holePositionX,holePositionY,holePositionX + 1,holePositionY);
		holePositionX++;
	}
	public boolean isGoal()
	{
		for(int i = 0 ; i < PUZZLESIZE ; i++)
		{
			for(int j = 0 ; j < PUZZLESIZE - (i / 2) ; j++) //in the last row it wont check last panel
			{
				if(number[i][j] != (char)(0x30 + (3 * i) + j + 1)) return false;
			}
		}
		return number[2][2] == ' '; //last panel of last row
	}
	public void reset()
	{
		number = new char[][]{{'1','2','3'},{'4','5','6'},{'7','8',' '}};
		holePositionX = 2; holePositionY = 2;
	}
	public void shuffle(int times) //move hole randomly for 'times' time
	{
		java.util.Random random = new java.util.Random();
		for(int i = 0 ; i < times ; i++)
		{
			if(random.nextInt(2) == 0) //move hole horizontally
			{
				switch(holePositionX)
				{
					case(0) : { //hole is at left
						moveHoleRight();
						break; 
					}
					case(1) : { //hole is at center
						if(random.nextInt(2) == 0) moveHoleRight();
						else moveHoleLeft();
						break;
					}
					case(2) : { //hole is at right
						moveHoleLeft();
						break;
					}
				}
			}
			else //move hole vertically
			{
				switch(holePositionY)
				{
					case(0) : { //hole is at top
						moveHoleDown();
						break; 
					}
					case(1) : { //hole is at center
						if(random.nextInt(2) == 0) moveHoleDown();
						else moveHoleUp();
						break;
					}
					case(2) : { //hole is at bottom
						moveHoleUp();
						break;
					}
				}
			}
		}
	}
	public char[][] getNumber() {
		return number;
	}
}

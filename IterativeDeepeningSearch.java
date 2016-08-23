
public class IterativeDeepeningSearch {
	private Puzzle puzzle;
	private boolean found;
	private java.util.Stack<Character> actions; //collect how to solve
	public IterativeDeepeningSearch(Puzzle puz)
	{
		puzzle = puz;
		found = false;
	}
	public void execute(int maxMoves)
	{
		for(int i = 0 ; i < maxMoves + 1 && ! found ; i++)
		{
			depthLimitedSearch(i);
		}
	}
	public boolean depthLimitedSearch(int maxLevel)
	{
		return traverse(maxLevel,0);
	}
	private boolean traverse(int maxLevel,int currentLevel)
	{
		if(currentLevel <= maxLevel)
		{
			if(puzzle.isGoal()) {
				found = true;
				actions = new java.util.Stack<Character>();
				return true;
			}
			else {
				boolean left = false, right = false, up = false ,down = false;
				if(puzzle.getHolePositionX() > 0 && ! found) { //not leftmost
					puzzle.moveHoleLeft();
					left = traverse(maxLevel ,currentLevel + 1);
					if(found) actions.push('L');
					puzzle.moveHoleRight();
				}
				if(puzzle.getHolePositionX() < puzzle.getPuzzleSize() - 1 && ! found) { //not rightmost
					puzzle.moveHoleRight();
					right = traverse(maxLevel ,currentLevel + 1);
					if(found) actions.push('R');
					puzzle.moveHoleLeft();
				}
				if(puzzle.getHolePositionY() > 0 && ! found) { //not top
					puzzle.moveHoleUp();
					up = traverse(maxLevel ,currentLevel + 1);
					if(found) actions.push('U');
					puzzle.moveHoleDown();
				}
				if(puzzle.getHolePositionY() < puzzle.getPuzzleSize() - 1 && ! found) { //not bottom
					puzzle.moveHoleDown();
					down = traverse(maxLevel ,currentLevel + 1);
					if(found) actions.push('D');
					puzzle.moveHoleUp();
				}
				return left && right && up && down;
			}
		}
		return false;
	}
	public java.util.Stack<Character> getActions() {
		return actions;
	}
}

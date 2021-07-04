package edu.wm.cs.cs301.DavidMontenegro.generation;

import java.util.ArrayList;

public class MazeBuilderKruskal extends MazeBuilder implements Runnable {
	
	/**
	 * Constructor.
	 */
	public MazeBuilderKruskal() {
		super();
	}

	/**
	 * This method generates pathways into the maze by using Kruskal's algorithm. The method creates a candidates list of 
	 * wallboards and a matrix to keep track of slots. The methods loops through the candidates list randomly removing a wallboard.
	 * If neighboring slot is not yet in the path then the wallboard is deleted. The matrix keeps track of the current path.
	 */
	@Override
	protected void generatePathways() {
		final ArrayList<Wallboard> candidates = new ArrayList<Wallboard>();
		updateListOfWallboards(candidates);
		
		int count = 1;
		int[][] board = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				board[i][j] = count;
				count++;
			}
		}
		
		Wallboard curWallboard;
		while(!candidates.isEmpty()){
			curWallboard = extractWallboardFromCandidateSetRandomly(candidates);
			if (board[curWallboard.getX()][curWallboard.getY()] != board[curWallboard.getNeighborX()][curWallboard.getNeighborY()]) {
				floorplan.deleteWallboard(curWallboard);
				int value = board[curWallboard.getX()][curWallboard.getY()];
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						if (board[i][j] == value)
							board[i][j] = board[curWallboard.getNeighborX()][curWallboard.getNeighborY()];
					}
				}
			}
		}
	}
	
	/**
	 * This method removes a random candidate from a list of candidates and returns it.
	 * @param candidates is the list of candidates to randomly remove a wall board from
	 * @return randomly chosen candidate from the list
	 */
	private Wallboard extractWallboardFromCandidateSetRandomly(final ArrayList<Wallboard> candidates) {
		// Taken from MazeBuilderPrim class
		return candidates.remove(random.nextIntWithinInterval(0, candidates.size()-1)); 
	}
	
	/**
	 * This method loops through the maze and sets a wallboard for each direction in each slot. Wallboards that can be
	 * torn down are added to the candidates list.
	 * @param wallboards the new elements should be added to
	 */
	private void updateListOfWallboards(ArrayList<Wallboard> wallboards) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				// Taken from MazeBuilderPrim class
				Wallboard wallboard = new Wallboard(i, j, CardinalDirection.East);
				for (CardinalDirection cd : CardinalDirection.values()) {
					wallboard.setLocationDirection(i, j, cd);
					if (floorplan.canTearDown(wallboard))
					{
						wallboards.add(new Wallboard(i, j, cd));
					}
				}
			}
		}
	}
}

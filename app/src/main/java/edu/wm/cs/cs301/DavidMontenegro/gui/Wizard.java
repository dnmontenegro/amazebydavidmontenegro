package edu.wm.cs.cs301.DavidMontenegro.gui;

import edu.wm.cs.cs301.DavidMontenegro.generation.CardinalDirection;
import edu.wm.cs.cs301.DavidMontenegro.generation.Maze;
import edu.wm.cs.cs301.DavidMontenegro.generation.MazeContainer;
import edu.wm.cs.cs301.DavidMontenegro.gui.Robot.Direction;
import edu.wm.cs.cs301.DavidMontenegro.gui.Robot.Turn;
/**
 * Class: Wizard
 * 
 * Responsibilities: Leads a robot by using distances from exit to find closest path to the exit
 * 
 * Collaborators: RobotDriver, BasicRobot, MazeContainer
 * 
 * @author David Montenegro
 *
 */

public class Wizard implements RobotDriver {
	
	private BasicRobot basicRobot;
	private MazeContainer maze;

	/**
	 * Constructor that setups initial objects.
	 */
	public Wizard() {
		basicRobot = null;
		maze = null;
	}

	/**
	 * This method sets the robot to the parameter.
	 * @param a robot
	 */
	@Override
	public void setRobot(Robot r) {
		basicRobot = (BasicRobot) r;
	}

	/**
	 * This method sets the maze to the parameter.
	 * @param a maze
	 */
	@Override
	public void setMaze(Maze maze) {
		this.maze = (MazeContainer) maze;
	}
	
	/**
	 * This method leads the robot to the exit using the strategy of using 
	 * distances from the exit to find the closest path to the exit.
	 * @return true if exit is found, false if otherwise
	 */
	@Override
	public boolean drive2Exit() throws Exception {
		while(!basicRobot.isAtExit()) {
			if(basicRobot.hasStopped())
				throw new Exception();
			int[] neighbor = maze.getNeighborCloserToExit(basicRobot.getCurrentPosition()[0], basicRobot.getCurrentPosition()[1]);
			CardinalDirection neighborDirection = CardinalDirection.getDirection(neighbor[0]-basicRobot.getCurrentPosition()[0], 
					neighbor[1]-basicRobot.getCurrentPosition()[1]);
			switch(basicRobot.getCurrentDirection()) {
				case North:
					switch(neighborDirection) {
						case North:
							break;
						case South:
							basicRobot.rotate(Turn.AROUND);
							break;
						case East:
							basicRobot.rotate(Turn.LEFT);
							break;
						case West:
							basicRobot.rotate(Turn.RIGHT);
							break;
					}
					break;
				case South:
					switch(neighborDirection) {
						case North:
							basicRobot.rotate(Turn.AROUND);
							break;
						case South:
							break;
						case East:
							basicRobot.rotate(Turn.RIGHT);
							break;
						case West:
							basicRobot.rotate(Turn.LEFT);
							break;
					}
					break;
				case East:
					switch(neighborDirection) {
						case North:
							basicRobot.rotate(Turn.RIGHT);
							break;
						case South:
							basicRobot.rotate(Turn.LEFT);
							break;
						case East:
							break;
						case West:
							basicRobot.rotate(Turn.AROUND);
							break;
					}
					break;
				case West:
					switch(neighborDirection) {
						case North:
							basicRobot.rotate(Turn.LEFT);
							break;
						case South:
							basicRobot.rotate(Turn.RIGHT);
							break;
						case East:
							basicRobot.rotate(Turn.AROUND);
							break;
						case West:
							break;
					}
					break;
			}
			basicRobot.move(1);
		}
		if(basicRobot.isAtExit()) {
			while(basicRobot.canSeeThroughTheExitIntoEternity(Direction.FORWARD) == false) {
				basicRobot.rotate(Turn.LEFT);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * This method leads the robot one step towards the exit using the strategy of using 
	 * distances from the exit to find the closest path to the exit.
	 * @return true if one step towards the exit is made, false if otherwise
	 */
	@Override
	public boolean drive1Step2Exit() throws Exception {
		if(basicRobot.hasStopped())
			throw new Exception();
		if(basicRobot.isAtExit()) {
			while(basicRobot.canSeeThroughTheExitIntoEternity(Direction.FORWARD) == false) {
				basicRobot.rotate(Turn.LEFT);
			}
			return false;
		}
		else if(!basicRobot.isAtExit()) {
			int[] neighbor = maze.getNeighborCloserToExit(basicRobot.getCurrentPosition()[0], basicRobot.getCurrentPosition()[1]);
			CardinalDirection neighborDirection = CardinalDirection.getDirection(neighbor[0]-basicRobot.getCurrentPosition()[0], 
					neighbor[1]-basicRobot.getCurrentPosition()[1]);
			switch(basicRobot.getCurrentDirection()) {
				case North:
					switch(neighborDirection) {
						case North:
							break;
						case South:
							basicRobot.rotate(Turn.AROUND);
							break;
						case East:
							basicRobot.rotate(Turn.LEFT);
							break;
						case West:
							basicRobot.rotate(Turn.RIGHT);
							break;
					}
					break;
				case South:
					switch(neighborDirection) {
						case North:
							basicRobot.rotate(Turn.AROUND);
							break;
						case South:
							break;
						case East:
							basicRobot.rotate(Turn.RIGHT);
							break;
						case West:
							basicRobot.rotate(Turn.LEFT);
							break;
					}	
					break;
				case East:
					switch(neighborDirection) {
						case North:
							basicRobot.rotate(Turn.RIGHT);
							break;
						case South:
							basicRobot.rotate(Turn.LEFT);
							break;
						case East:
							break;
						case West:
							basicRobot.rotate(Turn.AROUND);
							break;
					}
					break;
				case West:
					switch(neighborDirection) {
						case North:
							basicRobot.rotate(Turn.LEFT);
							break;
						case South:
							basicRobot.rotate(Turn.RIGHT);
							break;
						case East:
							basicRobot.rotate(Turn.AROUND);
							break;
						case West:
							break;
					}
					break;
			}
			basicRobot.move(1);
			return true;
		}
		else
			return false;
	}

	/**
	 * This method gets the amount of energy used during traversal of the maze.
	 * @return float amount of energy
	 */
	@Override
	public float getEnergyConsumption() {
		return 2000.0f - basicRobot.getBatteryLevel();
	}

	/**
	 * This method gets the amount of cells traveled during traversal of the maze.
	 * @return int number of cells visited
	 */
	@Override
	public int getPathLength() {
		return basicRobot.getOdometerReading();
	}

}

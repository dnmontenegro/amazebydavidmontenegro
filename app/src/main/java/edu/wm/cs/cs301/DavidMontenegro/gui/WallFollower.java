package edu.wm.cs.cs301.DavidMontenegro.gui;

import edu.wm.cs.cs301.DavidMontenegro.generation.Maze;
import edu.wm.cs.cs301.DavidMontenegro.generation.MazeContainer;
import edu.wm.cs.cs301.DavidMontenegro.gui.Robot.Direction;
import edu.wm.cs.cs301.DavidMontenegro.gui.Robot.Turn;
/**
 * Class: WallFollower
 * 
 * Responsibilities: Leads a robot by following the left hand wall
 * 
 * Collaborators: RobotDriver, BasicRobot, MazeContainer
 * 
 * @author David Montenegro
 *
 */

public class WallFollower implements RobotDriver {
	
	private BasicRobot basicRobot;
	private MazeContainer maze;

	/**
	 * Constructor that setups initial objects.
	 */
	public WallFollower() {
		basicRobot = null;
		maze = null;
	}

	/**
	 * This method sets the robot to the parameter.
	 */
	@Override
	public void setRobot(Robot r) {
		basicRobot = (BasicRobot) r;
	}

	/**
	 * This method sets the maze to the parameter.
	 */
	@Override
	public void setMaze(Maze maze) {
		this.maze = (MazeContainer) maze;
	}

	/**
	 * This method leads the robot to the exit using the strategy of following the left hand wall.
	 * @return true if exit is found, false if otherwise
	 */
	@Override
	public boolean drive2Exit() throws Exception {
		while(!basicRobot.isAtExit()) {
			if(basicRobot.hasStopped())
				throw new Exception();
			if(basicRobot.distanceToObstacle(Direction.LEFT) != 0) {
				basicRobot.rotate(Turn.LEFT);
				basicRobot.move(1);
			}
			else if(basicRobot.distanceToObstacle(Direction.FORWARD) == 0)
				basicRobot.rotate(Turn.RIGHT);
			else 
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
	 * This method leads the robot one step towards the exit using the strategy of following the left hand wall.
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
			if(basicRobot.distanceToObstacle(Direction.LEFT) != 0) {
				basicRobot.rotate(Turn.LEFT);
				basicRobot.move(1);
				return true;
			}
			else if(basicRobot.distanceToObstacle(Direction.FORWARD) == 0) {
				basicRobot.rotate(Turn.RIGHT);
				return false;
			}
			else {
				basicRobot.move(1);
				return true;
			}
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

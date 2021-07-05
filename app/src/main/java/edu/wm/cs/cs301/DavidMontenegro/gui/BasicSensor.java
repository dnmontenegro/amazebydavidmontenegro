package edu.wm.cs.cs301.DavidMontenegro.gui;

import edu.wm.cs.cs301.DavidMontenegro.generation.CardinalDirection;
import edu.wm.cs.cs301.DavidMontenegro.generation.Maze;
import edu.wm.cs.cs301.DavidMontenegro.generation.MazeContainer;
import edu.wm.cs.cs301.DavidMontenegro.gui.Robot.Direction;
/**
 * Class: BasicSensor
 * 
 * Responsibilities: Measure distance from an obstacle
 * 
 * Collaborators: DistanceSensor, MazeContainer, CardinalDirection
 * 
 * @author David Montenegro
 *
 */

public class BasicSensor implements DistanceSensor {
	
	private MazeContainer maze;
	private Direction direction;

	/**
	 * Constructor that setups initial objects.
	 */
	public BasicSensor() {
		maze = null;
		direction = null;
	}

	/**
	 * This method checks the distance to an obstacle.
	 * @param position of the robot, direction of the robot, and the battery level of the robot
	 * @return the int value of the distance to an obstacle
	 */
	@Override
	public int distanceToObstacle(int[] currentPosition, CardinalDirection currentDirection, float[] powersupply)
			throws Exception {
		if(powersupply[0] < getEnergyConsumptionForSensing())
			throw new Exception();
		int[] position = currentPosition;
		int distanceTraveled = 0;
		while(maze.getFloorplan().hasNoWall(position[0], position[1], currentDirection) == true) {
			if(maze.getFloorplan().isExitPosition(position[0], position[1]) == true)
				if(maze.isValidPosition(position[0] + currentDirection.getDirection()[0], position[1] + currentDirection.getDirection()[1]) == false)
					return Integer.MAX_VALUE;
			distanceTraveled++;
			position[0] = position[0] + currentDirection.getDirection()[0];
			position[1] = position[1] + currentDirection.getDirection()[1];
		}
		return distanceTraveled;
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
	 * This method sets the direction to the parameter.
	 * @param direction to mount the sensor
	 */
	@Override
	public void setSensorDirection(Direction mountedDirection) {
		direction = mountedDirection;
	}

	/**
	 * This method gets the energy amount needed for sensing.
	 * @return the float energy amount
	 */
	@Override
	public float getEnergyConsumptionForSensing() {
		return 1.0f;
	}

	/**
	 * This method throws an UnsupportedOperationException.
	 */
	@Override
	public void startFailureAndRepairProcess(int meanTimeBetweenFailures, int meanTimeToRepair)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * This method throws an UnsupportedOperationException.
	 */
	@Override
	public void stopFailureAndRepairProcess() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

}

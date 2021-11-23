package uk.co.which.exercise;

import uk.co.which.exercise.model.Coordinate;
import uk.co.which.exercise.model.Facing;

import static uk.co.which.exercise.model.Facing.*;

public class NavigationMap {

    private Coordinate coordinate;
    private Facing facing;

    public NavigationMap(Facing facing, Coordinate coordinate) {
        this.facing = facing;
        this.coordinate = coordinate;
    }

    public Coordinate getCurrentCoordinate() {
        return coordinate;
    }

    public void forward() {

        switch (facing) {
            case NORTH:
                coordinate = new Coordinate(coordinate.getX(), coordinate.getY() + 1);
                break;
            case SOUTH:
                coordinate = new Coordinate(coordinate.getX(), coordinate.getY() - 1);
                break;
            case EAST:
                coordinate = new Coordinate(coordinate.getX() + 1, coordinate.getY());
                break;
            case WEST:
                coordinate = new Coordinate(coordinate.getX() - 1, coordinate.getY());
                break;
        }
    }

    public void left() {

        switch (facing) {
            case NORTH:
                facing = WEST;
                break;
            case SOUTH:
                facing = EAST;
                break;
            case EAST:
                facing = NORTH;
                break;
            case WEST:
                facing = SOUTH;
                break;
        }
    }

    public void right() {

        switch (facing) {
            case NORTH:
                facing = EAST;
                break;
            case SOUTH:
                facing = WEST;
                break;
            case EAST:
                facing = SOUTH;
                break;
            case WEST:
                facing = NORTH;
                break;
        }
    }
}

package com.submersible.probecontrol.model;

import java.util.ArrayList;
import java.util.List;

public class Probe {
    private Position position;
    private Direction direction;
    private final List<Position> visitedPositions;

    public Probe(Position initialPosition, Direction initialDirection) {
        this.position = initialPosition;
        this.direction = initialDirection;
        this.visitedPositions = new ArrayList<>();
        this.visitedPositions.add(initialPosition); // Start is visited
    }

    public void moveTo(Position newPosition) {
        this.position = newPosition;
        this.visitedPositions.add(newPosition);
    }

    public void turnLeft() {
        this.direction = direction.turnLeft();
    }

    public void turnRight() {
        this.direction = direction.turnRight();
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public List<Position> getVisitedPositions() {
        return List.copyOf(visitedPositions);
    }
}
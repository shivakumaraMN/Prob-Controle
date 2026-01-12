package com.submersible.probecontrol.model;

import java.util.Set;

public class Grid {
    private final int width;
    private final int height;
    private final Set<Position> obstacles;

    public Grid(int width, int height, Set<Position> obstacles){
        this.width = width;
        this.height = height;
        this.obstacles = Set.copyOf(obstacles);
    }

    public boolean isWithinBounds(Position position){
        return position.x() >= 0 && position.x() < width && position.y() >= 0 && position.y() < height;
    }

    public boolean isObstacle(Position position) {
        return obstacles.contains(position);
    }

    public boolean canMoveTo(Position position){
        return isWithinBounds(position) && !isObstacle(position);
    }
}

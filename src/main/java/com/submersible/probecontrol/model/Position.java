package com.submersible.probecontrol.model;

import java.util.Objects;

public record Position(int x, int y) {
    public Position move(int deltaX, int deltaY){
        return new Position(x + deltaX, y + deltaY);
    }

    @Override
    public boolean equals(Object o){
        if(this == o ) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x, y);
    }
}

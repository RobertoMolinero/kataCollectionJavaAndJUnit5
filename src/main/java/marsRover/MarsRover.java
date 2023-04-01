package marsRover;

public class MarsRover {
    private final Position position;

    private final Direction direction;

    public MarsRover(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public MarsRover nextStep(World world, String command) {
        Position position = new Position(0,0);
        Direction direction = Direction.NORTH;

        switch (command) {
            case "M":
                position = move(this.position, this.direction);
                break;
            default:
                break;
        }

        return new MarsRover(position, direction);
    }

    private Position move(Position position, Direction direction) {
        return new Position(position.getX() + direction.getHorizontal(), position.getY() + direction.getVertical());
    }

    @Override
    public String toString() {
        return "MarsRover{" +
                "position=" + position +
                ", direction=" + direction +
                '}';
    }
}

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

enum class TurnDirection {
    LEFT, RIGHT
}

data class Position(var x: Int,
    var y: Int)

data class Rover(var direction: Direction,
    var position: Position)

fun Rover.turn(turnDirection: TurnDirection) {
    direction =
        if (turnDirection.equals(TurnDirection.LEFT)) {
            when(direction) {
                Direction.NORTH - > Direction.WEST
                Direction.EAST - > Direction.NORTH
                Direction.SOUTH - > Direction.EAST
                Direction.WEST - > Direction.SOUTH

            }
        } else {
            when(direction) {
                Direction.NORTH - > Direction.EAST
                Direction.EAST - > Direction.SOUTH
                Direction.SOUTH - > Direction.WEST
                Direction.WEST - > Direction.NORTH

            }
        }
}

fun Rover.move() {
    when(direction) {
        Direction.NORTH - > ++position.y
        Direction.EAST - > ++position.x
        Direction.SOUTH - > --position.y
        Direction.WEST - > --position.x
    }
}


fun main() {
    val r1 = Rover(Direction.NORTH, Position(0, 0))
    println(r1)
    r1.move();
    println(r1)

    r1.turn(TurnDirection.LEFT)
    println(r1)
    r1.move();
    r1.move();

    println(r1)
}
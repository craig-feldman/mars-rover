import java.io.File

enum class Direction {
    N, S, W, E
}

enum class TurnDirection {
    L, R
}

data class Position(var x: Int, var y: Int)

data class Rover(var direction: Direction, var position: Position) {
    override fun toString(): String {
        return "${position.x} ${position.y} ${direction}"
    }
}

/*
 'Turns' the rover by setting the rover's direction based on it's current heading and turn direction.
*/
fun Rover.turn(turnDirection: TurnDirection) {
    direction =
        // Could also use a 'when' here instead as that would force us to ensure we cover all cases if a new direction is added.
        if (turnDirection.equals(TurnDirection.L)) {
            when (direction) {
                Direction.N -> Direction.W
                Direction.E -> Direction.N
                Direction.S -> Direction.E
                Direction.W -> Direction.S
            }
        } else {
            when (direction) {
                Direction.N -> Direction.E
                Direction.E -> Direction.S
                Direction.S -> Direction.W
                Direction.W -> Direction.N
            }
        }
}

/*
 Advances the rover in the direction it is facing by updating the position accordingly.

 Note: Assuming that all positions ae valid and that we don't need to worry about the robot heading out of bounds.
 */
fun Rover.move() {
    when (direction) {
        Direction.N -> ++position.y
        Direction.E -> ++position.x
        Direction.S -> --position.y
        Direction.W -> --position.x
    }
}


/*
 Reads the input from a file named 'input'.

 Outputs the rover(s) final coordinates and direction.
 */
fun main() {
    val input = File("input").readLines()
    var rover: Rover? = null

    for ((index, value) in input.withIndex()) {
        // Note: We shall assume that the input file is formatted correctly.
        if (index == 0) {
            // For now, we are not worrying about grid size or going out of bounds
        } else if (index % 2 == 1) {
            // Odd indexes are a rover's setup
            val setup = value.split(" ")
            rover = Rover(Direction.valueOf(setup[2]), Position(setup[0].toInt(), setup[1].toInt()))
        } else {
            // Rover commands
            value.toCharArray().forEach { 
                when (it) {
                    'M' -> rover?.move()
                    'L' -> rover?.turn(TurnDirection.L)
                    'R' -> rover?.turn(TurnDirection.R)
                } 
            }

            // Output the final position
            println(rover)
        }
    }   
}
package uk.co.which.exercise

import spock.lang.Specification
import spock.lang.Subject
import uk.co.which.exercise.model.Coordinate
import uk.co.which.exercise.model.Facing

class NavigationMapSpec extends Specification {

    def "Should changes the coordinate when move forward to #facing"() {
        given:
        def coordinate = new Coordinate(0, 0)

        when:
        @Subject
        NavigationMap map = new NavigationMap(facing, coordinate)
        map.forward()

        then:
        map.currentCoordinate == new Coordinate(x, y)

        where:
        facing       | x  | y
        Facing.EAST  | 1  | 0
        Facing.WEST  | -1 | 0
        Facing.NORTH | 0  | 1
        Facing.SOUTH | 0  | -1
    }

    def "Should changes the facing when move left from #facing"() {
        given:
        def coordinate = new Coordinate(0, 0)

        when:
        @Subject
        NavigationMap map = new NavigationMap(facing, coordinate)
        map.left()

        then:
        map.facing == expectd

        where:
        facing       | expectd
        Facing.EAST  | Facing.NORTH
        Facing.WEST  | Facing.SOUTH
        Facing.NORTH | Facing.WEST
        Facing.SOUTH | Facing.EAST
    }

    def "Should changes the facing when move right from #facing"() {
        given:
        def coordinate = new Coordinate(0, 0)

        when:
        @Subject
        NavigationMap map = new NavigationMap(facing, coordinate)
        map.right()

        then:
        map.facing == expected

        where:
        facing       | expected
        Facing.EAST  | Facing.SOUTH
        Facing.WEST  | Facing.NORTH
        Facing.NORTH | Facing.EAST
        Facing.SOUTH | Facing.WEST
    }

    def "Should changes the coordinate when move right from #facing and move forward"() {
        given:
        def coordinate = new Coordinate(0, 0)

        when:
        @Subject
        NavigationMap map = new NavigationMap(facing, coordinate)
        map.right()
        map.forward()

        then:
        map.currentCoordinate == new Coordinate(x, y)

        where:
        facing       | x  | y
        Facing.EAST  | 0  | -1
        Facing.WEST  | 0  | 1
        Facing.NORTH | 1  | 0
        Facing.SOUTH | -1 | 0
    }
}

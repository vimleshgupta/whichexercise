package uk.co.which.exercise

import org.assertj.core.internal.bytebuddy.utility.RandomString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import uk.co.which.exercise.exception.ForensicsAPIClientException
import uk.co.which.exercise.model.Coordinate
import uk.co.which.exercise.model.Facing
import uk.co.which.exercise.service.InvestigationService

@SpringBootTest
class MainApplicationSpec extends Specification {

    @Autowired
    private InvestigationService investigationService

    def "should find a valid coordinate and locate kittens with passing the coordinate"() {

        given:
        investigationService.email = new RandomString(5).nextString() + "@gmail.com"

        when:

        Coordinate coordinate = investigationService.findCoordinate(Facing.NORTH, new Coordinate(0, 0))
        String message = investigationService.locate(coordinate)

        then:
        message.contains("Congratulations! The search party successfully recovered the missing kittens.")
    }

    def "locate should not allow to check again when the total number of attempts exceeded"() {

        given:
        investigationService.email = new RandomString(5).nextString() + "@gmail.com"
        def coordinate = new Coordinate(1, 5)

        when:
        investigationService.locate(coordinate)
        investigationService.locate(coordinate)
        investigationService.locate(coordinate)
        investigationService.locate(coordinate)
        investigationService.locate(coordinate)
        String message = investigationService.locate(coordinate)

        then:
        message.contains("Uh oh, it looks like you're out of attempts.")
    }

    def "findCoordinate should return a valid coordinate"() {

        when:
        Coordinate coordinate = investigationService.findCoordinate(Facing.NORTH, new Coordinate(0, 0))

        then:
        coordinate == new Coordinate(5, 2)
    }

    def "findCoordinate should throw an exception when the email id is not valid"() {

        given: "Set invalid email id"
        investigationService.email = "email"

        when:
        investigationService.findCoordinate(Facing.NORTH, new Coordinate(0, 0))

        then:
        def ex = thrown(ForensicsAPIClientException)
        ex.statusCode == 400
        ex.getMessage() == "Invalid email address: '$investigationService.email'"
    }

    def "locate should not return a success response when the coordinate is not valid"() {

        given:
        investigationService.email = new RandomString(5).nextString() + "@gmail.com"

        when:
        String message = investigationService.locate(new Coordinate(1, 5))

        then:
        message.contains("Unfortunately, the search party failed to recover the missing kittens. You have 4 attempts remaining.")
    }
}

package uk.co.which.exercise

import spock.lang.Specification
import spock.lang.Subject
import uk.co.which.exercise.client.response.Directions
import uk.co.which.exercise.client.ForensicsAPIClient
import uk.co.which.exercise.client.response.Message
import uk.co.which.exercise.exception.ForensicsAPIClientException
import uk.co.which.exercise.exception.UnknownDirectionFoundException
import uk.co.which.exercise.model.Coordinate
import uk.co.which.exercise.model.Facing
import uk.co.which.exercise.service.InvestigationService

class InvestigationServiceSpec extends Specification {

    def "findCoordinate should return valid coordinate based on directions"() {

        given:
        def email = "email@email.com"
        def forensicsAPIClient = Mock(ForensicsAPIClient)
        forensicsAPIClient.getDirections(email) >> new Directions(directions: directions)

        when:
        @Subject
        InvestigationService investigationService = new InvestigationService(email: email, forensicsAPIClient: forensicsAPIClient)
        def coordinate = investigationService.findCoordinate(Facing.NORTH, new Coordinate(0, 0))

        then:
        coordinate == new Coordinate(x, y)

        where:
        x | y | directions
        2 | 1 | ["forward", "right", "forward", "forward"]
        2 | 1 | ["right", "forward", "forward", "left", "forward"]
        3 | 4 | ["forward", "right", "forward", "forward", "forward", "left", "forward", "forward", "left", "right", "forward"]
    }

    def "findCoordinate should throw an exception when the direction is not valid"() {

        given:
        def invalid = "wwww"
        def email = "email@email.com"
        def forensicsAPIClient = Mock(ForensicsAPIClient)
        forensicsAPIClient.getDirections(email) >> new Directions(directions: ["forward", "right", "forward", invalid])

        when:
        @Subject
        def investigationService = new InvestigationService(email: email, forensicsAPIClient: forensicsAPIClient)
        investigationService.findCoordinate(Facing.NORTH, new Coordinate(0, 0))

        then:
        def ex = thrown(UnknownDirectionFoundException)
        ex.getMessage() == "Invalid or Unknown direction - " + invalid
    }

    def "findCoordinate should throw an exception when the email is not valid"() {

        given:
        def email = "email"
        def forensicsAPIClient = Mock(ForensicsAPIClient)
        forensicsAPIClient.getDirections(email) >> {throw new ForensicsAPIClientException(400, "BAD_REQUEST")}

        when:
        @Subject
        def investigationService = new InvestigationService(email: email, forensicsAPIClient: forensicsAPIClient)
        investigationService.findCoordinate(Facing.NORTH, new Coordinate(0, 0))

        then:
        def ex = thrown(ForensicsAPIClientException)
        ex.getStatusCode() ==  400
        ex.getMessage() ==  "BAD_REQUEST"
    }

    def "locate should return a message"() {

        given:
        def email = "email@email.com"
        def coordinate = new Coordinate(0, 0)
        def forensicsAPIClient = Mock(ForensicsAPIClient)
        forensicsAPIClient.locate(email, coordinate) >> new Message(message: "Success")

        when:
        @Subject
        InvestigationService investigationService = new InvestigationService(email: email, forensicsAPIClient: forensicsAPIClient)
        def message = investigationService.locate(coordinate)

        then:
        message == "Success"
    }
}

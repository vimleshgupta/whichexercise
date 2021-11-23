package uk.co.which.exercise

import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Subject
import uk.co.which.exercise.client.response.Directions
import uk.co.which.exercise.client.ForensicsAPIClient
import uk.co.which.exercise.client.response.Message
import uk.co.which.exercise.model.Coordinate

class ForensicsAPIClientSpec extends Specification {

    def restTemplate = Mock(RestTemplate)

    def "getDirections should return list directions"() {

        given:
        def email = "email@gmail.com"
        def expected = new Directions(directions: ["left", "forward"])
        def baseUrl = "https://baseurl.com"
        restTemplate.getForObject(baseUrl + "/api/{email}/directions", Directions, email) >> expected

        when:
        @Subject
        ForensicsAPIClient resource = new ForensicsAPIClient(restTemplate: restTemplate, baseUrl: baseUrl)
        Directions directions = resource.getDirections(email)

        then:
        directions.directions == expected.directions
    }

    def "locate method should return a reponse with message"() {

        given:
        def email = "email@gmail.com"
        def coordinate = new Coordinate(1, 2)
        def expected = new Message(message: "Success")
        def baseUrl = "https://baseurl.com"
        restTemplate.getForObject(baseUrl + "/api/{email}/location/{x}/{y}", Message, email, coordinate.getX(), coordinate.getY()) >> expected

        when:
        @Subject
        ForensicsAPIClient resource = new ForensicsAPIClient(restTemplate: restTemplate, baseUrl: baseUrl)
        Message response = resource.locate(email, coordinate)

        then:
        response.getMessage() == expected.getMessage()
    }
}

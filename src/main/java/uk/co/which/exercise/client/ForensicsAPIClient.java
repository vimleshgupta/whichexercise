package uk.co.which.exercise.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uk.co.which.exercise.client.response.Directions;
import uk.co.which.exercise.client.response.Message;
import uk.co.which.exercise.model.Coordinate;

@Component
public class ForensicsAPIClient {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${baseUrl}")
    private String baseUrl;

    public Directions getDirections(String email) {

        return restTemplate.getForObject(baseUrl + "/api/{email}/directions", Directions.class, email);
    }

    public Message locate(String email, Coordinate coordinate) {

        return restTemplate.getForObject(baseUrl + "/api/{email}/location/{x}/{y}", Message.class, email, coordinate.getX(), coordinate.getY());
    }
}

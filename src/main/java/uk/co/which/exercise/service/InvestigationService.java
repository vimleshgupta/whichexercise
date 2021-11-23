package uk.co.which.exercise.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uk.co.which.exercise.client.response.Directions;
import uk.co.which.exercise.exception.UnknownDirectionFoundException;
import uk.co.which.exercise.model.Coordinate;
import uk.co.which.exercise.model.Facing;
import uk.co.which.exercise.NavigationMap;
import uk.co.which.exercise.client.ForensicsAPIClient;

@Service
public class InvestigationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvestigationService.class);

    @Autowired
    private ForensicsAPIClient forensicsAPIClient;
    @Value("${email}")
    private String email;

    public Coordinate findCoordinate(Facing currentFacing, Coordinate coordinate) {

        LOGGER.debug("Call the forensic API to get the directions");
        Directions directions = forensicsAPIClient.getDirections(email);
        NavigationMap map = new NavigationMap(currentFacing, coordinate);

        LOGGER.debug("Navigate to map by directions");
        directions.getDirections().forEach(direction -> {

            switch (direction) {
                case "forward":
                    map.forward();
                    break;
                case "left":
                    map.left();
                    break;
                case "right":
                    map.right();
                    break;
                default:
                    throw new UnknownDirectionFoundException("Invalid or Unknown direction - " + direction);
            }
        });
        return map.getCurrentCoordinate();
    }

    public String locate(Coordinate coordinate) {

        LOGGER.debug("Locate the kittens by coordinate");
        return forensicsAPIClient.locate(email, coordinate).getMessage();
    }
}

package uk.co.which.exercise.client.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Directions {

    @JsonProperty("directions")
    private List<String> directions;
}

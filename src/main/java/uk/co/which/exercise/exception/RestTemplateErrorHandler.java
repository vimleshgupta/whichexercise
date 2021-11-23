package uk.co.which.exercise.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import uk.co.which.exercise.client.response.ErrorResponse;

import java.io.IOException;

public class RestTemplateErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            ObjectMapper objectMapper = new ObjectMapper();
            ErrorResponse error = objectMapper.readValue(response.getBody(), ErrorResponse.class);

            throw new ForensicsAPIClientException(response.getRawStatusCode(), error.getError());
        }
    }
}

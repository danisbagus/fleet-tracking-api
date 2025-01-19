package danisbagus.fleet_tracking_api.exception;

import danisbagus.fleet_tracking_api.domain.dto.WebResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<WebResponse<Object>> handleNotFoundException(NotFoundException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    };

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<WebResponse<Object>> handleBadRequestException(BadRequestException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    };

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<WebResponse<Object>> handleInternalServerException(InternalServerException ex)  {
        return buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    };

    /**
     * Utility method to build a response entity
     *
     * @param message the message to include in the response
     * @param status  the HTTP status to use for the response
     * @return a ResponseEntity containing the response object
     */
    private ResponseEntity<WebResponse<Object>> buildResponse(String message, HttpStatus status) {
        WebResponse<Object> response = new WebResponse<>(
                message,
                status.value()
        );

        return new ResponseEntity<>(response, status);
    }
}

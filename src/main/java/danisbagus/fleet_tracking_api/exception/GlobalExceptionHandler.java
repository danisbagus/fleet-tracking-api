package danisbagus.fleet_tracking_api.exception;

import danisbagus.fleet_tracking_api.domain.dto.WebResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<WebResponse<Object>> handleNotFoundException(NotFoundException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    };

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<WebResponse<Object>> handleBadRequestException(BadRequestException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    };

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<WebResponse<Object>> handleInternalServerException(InternalServerException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    };

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<WebResponse<Object>> handleUnauthorizedException(UnauthorizedException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    };

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WebResponse<Object>> handleAllexception(Exception ex) {
        return buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        WebResponse<Object> response = new WebResponse<>(
                ex.getFieldError().getDefaultMessage(),
                status);

        return new ResponseEntity<>(response, status);
    }

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
                status);

        return new ResponseEntity<>(response, status);
    }
}

package danisbagus.fleet_tracking_api.domain.dto;

import org.springframework.http.HttpStatusCode;

public class WebResponse<T> {
    private HttpStatusCode status;
    private String message;
    private T data;

    public WebResponse(T data, String message, HttpStatusCode status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public WebResponse(String message, HttpStatusCode status) {
        this.message = message;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatusCode getStatus() {
        return status;
    }

    public void setStatus(HttpStatusCode status) {
        this.status = status;
    }
}

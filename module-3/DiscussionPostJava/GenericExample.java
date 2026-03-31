
import java.net.http.HttpResponse;

public class GenericExample {

    /**
     * This method takes a generic type T that extends the API_Result interface.
     * It checks if the response had an error and returns an appropriate
     * HttpResponse based on the result.
     *
     * @param <T> the type of the response, which must implement the API_Result
     * interface
     * @param response the response object that implements the API_Result
     * interface
     * @return an HttpResponse object with a status code of 500 if there was an
     * error, or 200 if there was no error
     */
    public <T extends API_Result> HttpResponse GetHttpResponse(T response) {
        if (response.HadError()) {
            return new HttpResponse(500, response);
        } else {
            return new HttpResponse(200, response);
        }
    }

    interface API_Result {

        boolean HadError();
    }
}

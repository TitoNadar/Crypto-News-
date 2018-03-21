package tito.example.com.cryptonews.Interface;

import retrofit.Call;
import retrofit.http.GET;
import tito.example.com.cryptonews.Modal.Response;

/**
 * Created by tito on 21/3/18.
 */

public interface NewsService {
    @GET("v2/everything?q=cryptocurrency&apiKey=44aa4bd036d74cd9a38c0c5f485c3557")
    Call<Response> getCryptoNews();
}

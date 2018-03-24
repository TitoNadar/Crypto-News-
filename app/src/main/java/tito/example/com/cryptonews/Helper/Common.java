package tito.example.com.cryptonews.Helper;

import tito.example.com.cryptonews.Interface.NewsService;
import tito.example.com.cryptonews.Remote.RetrofitClient;

/**
 * Created by tito on 21/3/18.
 */

public class Common {
    private static String Base_Url="http://newsapi.org/";
    public static final String API_KEY="44aa4bd036d74cd9a38c0c5f485c3557";

    public static NewsService getNewsService()
    {
        return RetrofitClient.getclient(Base_Url).create(NewsService.class);
    }
}

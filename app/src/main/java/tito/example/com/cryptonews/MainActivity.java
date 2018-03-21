package tito.example.com.cryptonews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import retrofit.Callback;
import retrofit.Retrofit;
import tito.example.com.cryptonews.Adapter.NewsAdapter;
import tito.example.com.cryptonews.Helper.Common;
import tito.example.com.cryptonews.Interface.NewsService;
import tito.example.com.cryptonews.Modal.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewsService newsService;
     private NewsAdapter newsAdapter;
     private RecyclerView.LayoutManager layoutManager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.market:
                    showMarketDetails();
                    return true;
                case R.id.news:
                    showNews();
                    return true;

            }
            return false;
        }
    };

    private void showNews() {
        newsService.getCryptoNews().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(retrofit.Response<Response> response, Retrofit retrofit) {
             newsAdapter=new NewsAdapter(response.body().getArticles(),MainActivity.this);
               newsAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(newsAdapter);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
    private void showMarketDetails() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        newsService= Common.getNewsService();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}

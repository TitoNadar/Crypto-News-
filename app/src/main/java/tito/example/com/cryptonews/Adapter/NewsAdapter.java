package tito.example.com.cryptonews.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import tito.example.com.cryptonews.Helper.ISO8601DateParser;
import tito.example.com.cryptonews.Interface.ItemClickListener;
import tito.example.com.cryptonews.Modal.ArticlesItem;
import tito.example.com.cryptonews.R;

/**
 * Created by tito on 21/3/18.
 */

class NewsListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    TextView article_title,article_source;
    RelativeTimeTextView article_time;
    CircleImageView article_image;
    ItemClickListener itemClickListener;

    public NewsListViewHolder(View itemView) {
        super(itemView);
        article_title=itemView.findViewById(R.id.article_title);
        article_image=itemView.findViewById(R.id.article_image);
        article_time=itemView.findViewById(R.id.article_time);
           article_source=itemView.findViewById(R.id.source);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(itemView,getAdapterPosition(),false);
    }
}

public class NewsAdapter extends RecyclerView.Adapter<NewsListViewHolder>
{
    private List<ArticlesItem> articlesList;
    private Context context;

    public NewsAdapter(List<ArticlesItem> articlesList, Context context) {
        this.articlesList = articlesList;
        this.context = context;
    }

    @Override
    public NewsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView=layoutInflater.inflate(R.layout.news_card_item,parent,false);
        return new NewsListViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(NewsListViewHolder holder, int position) {
        Picasso.with(context).load(articlesList.get(position).getUrlToImage()).into(holder.article_image);
        if (articlesList.get(position).getTitle().length() > 20) {
            holder.article_title.setText(articlesList.get(position).getTitle().substring(0, 19) + "...");
        } else {
            holder.article_title.setText(articlesList.get(position).getTitle());
        }
        Date date = null;
        try {
            date = ISO8601DateParser.parse(articlesList.get(position).getPublishedAt());


            //   holder.article_time.setText(date.toString());


            holder.article_time.setReferenceTime(date.getDate());

            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {

                }
            });
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        holder.article_source.setText(articlesList.get(position).getSource().getName());

    }




    @Override
    public int getItemCount() {
        return articlesList.size();
    }
}

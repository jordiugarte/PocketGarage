package bo.com.golpistasElectricistas.pocketGarage.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.R;
import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.ui.callback.ArticleCallback;
import bo.com.golpistasElectricistas.pocketGarage.ui.viewHolders.ArticleViewHolder;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
    private Context context;

    private List<Article> articles;
    private LayoutInflater inflater;

    private ArticleCallback callback;

    public ArticleAdapter(List<Article> articles, Context context) {
        this.context = context;
        this.articles = articles;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.inflater.inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.titleTextView.setText(article.getTitle());
        holder.shortDescriptionTextView.setText(article.getShortDescription());
        holder.priceTextView.setText(article.getPrice() + " Bs");
        Picasso.with(context).load(article.getPhotos().get(0)).into(holder.coverImageView);
        holder.itemView.setOnClickListener(view -> {
            if (callback != null) {
                callback.onStartupClicked(article);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void updateItems(List<Article> posts) {
        this.articles = posts;
        notifyDataSetChanged();
    }

    public void setCallback(ArticleCallback callback) {
        this.callback = callback;
    }
}

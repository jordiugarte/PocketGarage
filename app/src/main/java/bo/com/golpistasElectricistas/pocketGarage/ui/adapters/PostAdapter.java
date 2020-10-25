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
import bo.com.golpistasElectricistas.pocketGarage.model.Post;
import bo.com.golpistasElectricistas.pocketGarage.ui.callback.PostCallback;
import bo.com.golpistasElectricistas.pocketGarage.ui.viewHolders.PostViewHolder;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {
    private Context context;

    private List<Post> posts;
    private LayoutInflater inflater;

    private PostCallback callback;

    public PostAdapter(List<Post> posts, Context context) {
        this.context = context;
        this.posts = posts;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.inflater.inflate(R.layout.item_article, parent, false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.titleTextView.setText(post.getTitle());
        holder.shortDescriptionTextView.setText(post.getShortDescription());
        holder.priceTextView.setText(post.getPrice() + " Bs");
        Picasso.with(context).load(post.getThumbnail()).into(holder.coverImageView);
        holder.itemView.setOnClickListener(view -> {
            if (callback != null) {
                callback.onStartupClicked(post);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void updateItems(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    public void setCallback(PostCallback callback) {
        this.callback = callback;
    }
}

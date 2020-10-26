package bo.com.golpistasElectricistas.pocketGarage.ui.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import bo.com.golpistasElectricistas.pocketGarage.R;

public class ArticleViewHolder extends RecyclerView.ViewHolder {

    public RelativeLayout relativeLayout;
    public ImageView coverImageView;
    public TextView titleTextView;
    public TextView shortDescriptionTextView;
    public TextView priceTextView;

    public ArticleViewHolder(@NonNull View itemView) {
        super(itemView);
        relativeLayout = itemView.findViewById(R.id.itemArticle);
        coverImageView = itemView.findViewById(R.id.articleItemImage);
        titleTextView = itemView.findViewById(R.id.articleItemTitle);
        shortDescriptionTextView = itemView.findViewById(R.id.articleItemShortDescription);
        priceTextView = itemView.findViewById(R.id.articleItemPrice);
    }
}

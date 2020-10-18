package bo.com.golpistasElectricistas.pocketGarage.ui.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import bo.com.golpistasElectricistas.pocketGarage.R;

public class ArticleViewHolder extends RecyclerView.ViewHolder {

    public ImageView coverImageView;
    public TextView titleTextView;
    public TextView shortDescriptionTextView;
    public TextView priceTextView;

    public ArticleViewHolder(@NonNull View itemView) {
        super(itemView);
        coverImageView = itemView.findViewById(R.id.articleItemImage);
        titleTextView = itemView.findViewById(R.id.titleField);
        shortDescriptionTextView = itemView.findViewById(R.id.articleItemShortDescription);
        priceTextView = itemView.findViewById(R.id.articleItemPrice);
    }
}

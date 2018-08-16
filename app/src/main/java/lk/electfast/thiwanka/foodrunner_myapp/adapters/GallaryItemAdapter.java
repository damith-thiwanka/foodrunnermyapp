package lk.electfast.thiwanka.foodrunner_myapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lk.electfast.thiwanka.foodrunner_myapp.R;
import lk.electfast.thiwanka.foodrunner_myapp.models.CardItem;
import lk.electfast.thiwanka.foodrunner_myapp.models.CardModel;

public class GallaryItemAdapter extends RecyclerView.Adapter<GallaryItemAdapter.GallaryItemViewHolder> {


    private Context mContext;
   private List<CardItem> cardList;
    //private List<CardModel> cardList;


    public class GallaryItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtLocation)
        TextView Title;
        @BindView(R.id.card_img)
        ImageView Image;
        @BindView(R.id.txtViews)
        TextView Views;
        @BindView(R.id.txtDownloads)
        TextView Downloads;

        public GallaryItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public GallaryItemAdapter(Context mContext, List<CardItem> cardList) {
        this.mContext = mContext;
        this.cardList = cardList;
    }


    @NonNull
    @Override
    public GallaryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
        return new GallaryItemViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull final GallaryItemViewHolder holder, int position) {
        CardItem cardItem = cardList.get(position);
        //CardModel cardItem = cardList.get(position);
        holder.Title.setText(cardItem.getLocation().getTitle());
        holder.Downloads.setText(cardItem.getDownloads());
        holder.Views.setText(cardItem.getViews());

        Glide.with(mContext)
                .load(cardItem.getUrls().getSmall())
                .into(holder.Image);


    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }


}

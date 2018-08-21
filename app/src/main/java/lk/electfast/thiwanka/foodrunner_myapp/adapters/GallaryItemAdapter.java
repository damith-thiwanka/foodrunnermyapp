package lk.electfast.thiwanka.foodrunner_myapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
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
    CustomItemClickListener customItemClickListener;

    public class GallaryItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtLocation)
        TextView Title;
        @BindView(R.id.card_img)
        ImageView Image;
        @BindView(R.id.txtViews)
        TextView Views;
        @BindView(R.id.txtDownloads)
        TextView Downloads;
        @BindView(R.id.txt_rating_view)
        TextView rating;
        @BindView(R.id.RatingBar_view)
        RatingBar ratingBar;

        public GallaryItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(CardItem item){

            if(null != item.getLocation())
                this.Title.setText(item.getLocation().getTitle());
            if(null != item.getDownloads())
                this.Downloads.setText(item.getDownloads());
            if(null != item.getViews())
                this.Views.setText(item.getViews());

            Glide.with(mContext)
                    .load(item.getUrls().getSmall())
                    .into(this.Image);
            rating.setText(String.valueOf(ratingBar.getRating())+"/5.0");
        }
    }

    public GallaryItemAdapter(Context mContext, List<CardItem> cardList,CustomItemClickListener customItemClickListener) {
        this.mContext = mContext;
        this.cardList = cardList;
        this.customItemClickListener = customItemClickListener;
    }


    @NonNull
    @Override
    public GallaryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
        final GallaryItemViewHolder myViewHolder = new GallaryItemViewHolder(itemview);

        myViewHolder.Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customItemClickListener.onItemClick(view, cardList.get(myViewHolder.getPosition()).getUrls().getSmall());
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final GallaryItemViewHolder holder, int position) {
        CardItem cardItem = cardList.get(position);
        holder.setData(cardItem);
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }


    // Clean all elements of the recycler
    public void clear() {
        cardList.clear();
        notifyDataSetChanged();
    }

}

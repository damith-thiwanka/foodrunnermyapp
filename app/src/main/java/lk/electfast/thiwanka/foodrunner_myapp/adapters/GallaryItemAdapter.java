package lk.electfast.thiwanka.foodrunner_myapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class GallaryItemAdapter extends RecyclerView.Adapter<GallaryItemAdapter.GallaryItemViewHolder> {
    @NonNull
    @Override
    public GallaryItemAdapter.GallaryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull GallaryItemAdapter.GallaryItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class GallaryItemViewHolder extends RecyclerView.ViewHolder {
        public GallaryItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}

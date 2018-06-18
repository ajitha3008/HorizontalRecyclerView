package braingalore.horizontalrecyclerview;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s92 on 6/18/2018.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder> {

    private List<Uri> items = new ArrayList<>();

    public HorizontalAdapter() {
    }

    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_view, parent, false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HorizontalViewHolder holder, int position) {
        holder.image.setImageURI(items.get(position));
    }

    public void addItem(Uri item) {
        items.add(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        ImageView delete;
        public HorizontalViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.file_view);
            delete = itemView.findViewById(R.id.delete_image);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    items.remove(getAdapterPosition());
                }
            });
        } }
}
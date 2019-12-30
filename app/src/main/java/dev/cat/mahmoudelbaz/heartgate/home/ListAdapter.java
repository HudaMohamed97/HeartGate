package dev.cat.mahmoudelbaz.heartgate.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import dev.cat.mahmoudelbaz.heartgate.R;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context context;
    private List<Menu_item> listDataHeader;

    public ListAdapter(Context context, List<Menu_item> listDataHeader) {
        this.context = context;
        this.listDataHeader = listDataHeader;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(listDataHeader.get(i).getName());
        viewHolder.listColor.setBackground(ContextCompat.getDrawable(context, listDataHeader.get(i).getBackgroundColorResId()));
        viewHolder.myCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ConcorData.class);
                intent.putExtra("contentText", listDataHeader.get(i).getMessage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDataHeader.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public LinearLayout myCell;
        public RelativeLayout listColor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.lblListItem);
            this.myCell = itemView.findViewById(R.id.myCell);
            this.listColor = itemView.findViewById(R.id.listcolor);

        }
    }
}
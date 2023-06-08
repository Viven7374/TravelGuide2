package com.example.travelguide2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelguide2.DetailActivity;
import com.example.travelguide2.InfoActivity;
import com.example.travelguide2.R;
import com.example.travelguide2.entity.Article;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RCViewHolder> {

    ArrayList<Article> modelArrayList;
    Context context;
    LayoutInflater layoutInflater;
    private OnItemClickListener itemClickListener;



    public RecyclerViewAdapter(Context context, ArrayList<Article> modelArrayList) {
        this.modelArrayList = modelArrayList;
        this.context = context;
//        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context==null){
            context=parent.getContext();
        }
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rc_item,parent,false);
        return new RCViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RCViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Article article= modelArrayList.get(position);
        holder.rc_title.setText(article.getTitle());
        //设置图片,是数字类型,暂时为默认图片
        holder.rc_image.setImageResource(R.drawable.index_a);
//        Glide.with(context).load(article.getTitle()).into(holder.rc_image);
        holder.position=position;
        //对Item监听
        final RecyclerViewAdapter.RCViewHolder holder1 = holder;
        holder1.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder1.getAdapterPosition();
                Article article = modelArrayList.get(position);
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.ARTICLE_NAME, article.getTitle());
                intent.putExtra(DetailActivity.ARTICLE_IMAGE_ID, article.getId());
                intent.putExtra(DetailActivity.ARTICLE_CONTENT,article.getContent());
                context.startActivity(intent);
            }
        });
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //获取item参数
//                if (listener!=null){
//                    listener.onItemClick(position);
//                }
//            }
//        });
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public class RCViewHolder extends RecyclerView.ViewHolder {
        int position;
        public ImageView rc_image;
        public TextView rc_title,textView2;
        CardView cardView;
        public RCViewHolder(@NonNull View itemView) {
            super(itemView);
            rc_image=itemView.findViewById(R.id.rc_image);
            rc_title=itemView.findViewById(R.id.rc_titile);
            cardView=(CardView)itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener!=null){
                        itemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}



//public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RCViewHolder> {
//
//    Context context;
//    ArrayList<RCModel> modelArrayList;
//
//    public RecyclerViewAdapter(Context context, ArrayList<RCModel> modelArrayList) {
//        this.context = context;
//        this.modelArrayList = modelArrayList;
//    }
//
//    @NonNull
//    @Override
//    public RCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.rc_item,parent,false);
//        return new RCViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RCViewHolder holder, int position) {
//        RCModel rcModel= modelArrayList.get(position);
//        holder.rc_title.setText(rcModel.title);
//        holder.rc_image.setImageResource(rcModel.image);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return modelArrayList.size();
//    }
//
//    public class RCViewHolder extends RecyclerView.ViewHolder {
//        ImageView rc_image;
//        TextView rc_title;
//
//        public RCViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            rc_image=itemView.findViewById(R.id.rc_image);
//            rc_title=itemView.findViewById(R.id.rc_titile);
//        }
//    }

//}

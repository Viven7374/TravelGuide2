package com.example.travelguide2.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelguide2.R;
import com.example.travelguide2.entity.Article;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RCViewHolder> {

    ArrayList<Article> modelArrayList;
    Context context;
    LayoutInflater layoutInflater;

    public RecyclerViewAdapter(Context context,ArrayList<Article> modelArrayList) {
        this.modelArrayList = modelArrayList;
        this.context = context;
//        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public RCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rc_item,parent,false);
        return new RCViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RCViewHolder holder, int position) {
        Article article= modelArrayList.get(position);
        holder.rc_title.setText(article.getTitle());
        //设置图片,是数字类型
        holder.rc_image.setImageResource(R.drawable.index_a);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

//    public View getView(int position, View convertView, ViewGroup parent) {
//        Log.e("arrayList","enter adapter");
//        Article article=modelArrayList.get(position);
//        RCViewHolder holder = null;//创建一个中转站
//        if (convertView==null){
//            convertView=layoutInflater.inflate(R.layout.rc_item,null);
//            holder = new RCViewHolder(convertView);
////            holder.imageView=convertView.findViewById(R.id.rc_image);
//            holder.rc_title = convertView.findViewById(R.id.rc_titile);
//            convertView.setTag(holder);//绑定项目视图和holder
//        }
//        else {
//            holder = (RCViewHolder) convertView.getTag();
//        }
//        //读取数据：title、image等
//        holder.rc_title.setText(article.getTitle());
//        //设置相应图片
//
//        return convertView;
//    }


    public class RCViewHolder extends RecyclerView.ViewHolder {
        public ImageView rc_image;
        public TextView rc_title,textView2;
        public RCViewHolder(@NonNull View itemView) {
            super(itemView);
            rc_image=itemView.findViewById(R.id.rc_image);
            rc_title=itemView.findViewById(R.id.rc_titile);
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

package com.example.marsplay.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marsplay.Models.Doc;
import com.example.marsplay.R;

import java.util.List;

public class Adapter_Model extends RecyclerView.Adapter<Adapter_Model.myViewHolder> {
     List<Doc> mylist;
    public class myViewHolder extends RecyclerView.ViewHolder {
        public TextView article_type,journal,eissn,score;
        ExpandableTextView expandableTextView;
        LinearLayout linearLayout;
        public myViewHolder (View view) {
            super(view);
            article_type = (TextView) view.findViewById(R.id.type);
            eissn=view.findViewById(R.id.eissn);
            score=view.findViewById(R.id.score);
            journal = (TextView) view.findViewById(R.id.journal);
            linearLayout=view.findViewById(R.id.layout);
            expandableTextView =  view.findViewById(R.id.expandable_text);

        }

    }
    public Adapter_Model(Context context,List<Doc>list){
       this.mylist=list;
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemsview= LayoutInflater.from(parent.getContext()).inflate(R.layout.skeleton,parent,false);
        return new myViewHolder(itemsview);

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, final int position) {

        final Doc model=mylist.get(position);

        holder.article_type.setText("Artice Type: "+model.getArticleType());
        holder.eissn.setText("Eissn: "+model.getEissn());
        holder.score.setText("Score: "+model.getScore());
        holder.journal.setText("Journal: "+model.getJournal());
        //holder.abstrac.setText(model.getAbstract().get(0));
        boolean expanded = model.isExpanded();
//        // Set the visibility based on state
      holder.linearLayout.setVisibility(expanded ? View.VISIBLE : View.GONE);

        holder.expandableTextView.setText("Abstract\n"+model.getAbstract().get(0));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean expanded=model.isExpanded();
                model.setExpanded(!expanded);
                // Notify the adapter that item has changed
                notifyItemChanged(position);
            }
        });
    }



    @Override
    public int getItemCount() {
        return mylist.size();

    }
}

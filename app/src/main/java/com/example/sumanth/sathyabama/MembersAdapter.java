package com.example.sumanth.sathyabama;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MembersViewHolder>
{
    ArrayList<CreateCode> namelist;
    Context c;

    MembersAdapter(ArrayList<CreateCode> namelist, Context c)
    {
        this.namelist= namelist;
        this.c=c;
    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    @NonNull
    @Override
    public MembersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        MembersViewHolder membersViewHolder = new MembersViewHolder(v,c,namelist);

        return membersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MembersViewHolder membersViewHolder, int position)
    {
        CreateCode createCodeobj =namelist.get(position);
        membersViewHolder.name_text.setText(createCodeobj.code);
        Picasso.get().load(createCodeobj.imageUrl).placeholder(R.drawable.smiley).into(membersViewHolder.circleImageView);
    }

    public static class MembersViewHolder extends RecyclerView.ViewHolder implements View

            .OnClickListener
    {

        TextView name_text;
        CircleImageView circleImageView;
        Context c;
        ArrayList<CreateCode> nameArrayList;
        FirebaseAuth auth;
        FirebaseUser user;

        public MembersViewHolder(@NonNull View itemView, Context c, ArrayList<CreateCode> nameArrayList) {
            super(itemView);
            this.c = c;
            this.nameArrayList = nameArrayList;

            itemView.setOnClickListener(this);
            auth= FirebaseAuth.getInstance();
            user=auth.getCurrentUser();

            name_text=itemView.findViewById(R.id.item_title);
            circleImageView =itemView.findViewById(R.id.i11);

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(c,"you have selected this bus",Toast.LENGTH_SHORT).show();
        }
    }



}

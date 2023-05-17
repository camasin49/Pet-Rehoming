package com.example.petrehoming;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    private static Context mContext;
    private static List<Upload> mUploads;

    public PetAdapter(Context context, List<Upload> uploads) {
        this.mContext = context;
        this.mUploads = uploads;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        return new PetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        Upload sad = mUploads.get(position);
        holder.desc_Pet.setText("Description: "+sad.getDescription());
        holder.nameRecycle.setText("Name: "+sad.getName());
        holder.genderRecycle.setText("Gender: "+sad.getGender());
        holder.userName.setText("User: "+sad.getUser());
        Picasso.with(mContext)
                .load(sad.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.image_Pet);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder{
        ImageView image_Pet;
        TextView nameRecycle;
        TextView genderRecycle;
        TextView desc_Pet;
        TextView userName;
        Button adopt;

        public PetViewHolder(@NonNull View itemView) {
            super(itemView);

            image_Pet = itemView.findViewById(R.id.imageRecycle);
            nameRecycle = itemView.findViewById(R.id.nameRecycle);
            genderRecycle = itemView.findViewById(R.id.genderRecycle);
            desc_Pet = itemView.findViewById(R.id.descRecycle);
            userName = itemView.findViewById(R.id.usernameRecycle);
            adopt = itemView.findViewById(R.id.adopt);

            adopt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Upload upload = mUploads.get(position);
                    String email = upload.getUser();
                    String contactNumber = upload.getContactNo();

                    Intent intent = new Intent(mContext, Messaging.class);
                    intent.putExtra("email", email);
                    intent.putExtra("contactNumber", contactNumber);
                    mContext.startActivity(intent);
                }
            });

        }
    }
}

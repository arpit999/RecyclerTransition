package com.production.home.recyclertransition;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Arpit on 05-Oct-16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MovieHolder> {

    List<Movie> movieList;
    Context context;
    int lastPosition = -1;

    public RVAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item,viewGroup,false);
        MovieHolder movieHolder = new MovieHolder(view);

        return movieHolder;
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {

        holder.tv_title.setText(movieList.get(position).getName());
        holder.rating.setText(movieList.get(position).getRating());
        Picasso.with(context)
                .load(movieList.get(position).getImage())
                .into(holder.img_movie);

        // Here you apply the animation when the view is bound
        setAnimation(holder.cv, position);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_title,rating;
        ImageView img_movie;
        CardView cv;

        public MovieHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.cv);
            tv_title = (TextView)itemView.findViewById(R.id.tv_title);
            rating = (TextView)itemView.findViewById(R.id.tv_rating);
            img_movie= (ImageView) itemView.findViewById(R.id.img_movie);

            img_movie.setOnClickListener(this);
            tv_title.setOnClickListener(this);
            rating.setOnClickListener(this);
            cv.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (v.getId()==img_movie.getId()){

                Toast.makeText(v.getContext(), "Image : "+movieList.get(getAdapterPosition()).getImage() , Toast.LENGTH_SHORT).show();

            }else if (v.getId() == tv_title.getId()) {

                Toast.makeText(v.getContext(), "Title : "+movieList.get(getAdapterPosition()).getName() , Toast.LENGTH_SHORT).show();

            } else if (v.getId() == rating.getId()) {

                Toast.makeText(v.getContext(), "Rating : "+movieList.get(getAdapterPosition()).getRating() , Toast.LENGTH_SHORT).show();

            }
            else if(v.getId() == cv.getId()) {
                Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        }



    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated

        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


}

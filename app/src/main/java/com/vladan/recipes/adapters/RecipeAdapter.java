package com.vladan.recipes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vladan.recipes.R;
import com.vladan.recipes.db.model.RecipeModel;
import com.vladan.recipes.utils.OnSwipedListener;

import java.util.List;



public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> implements OnSwipedListener{

    private List<RecipeModel> listOfRecipes;
    private Context context;
    private OnClickedAndSwipedInterface listener;

    public RecipeAdapter(List<RecipeModel> listOfRecipes, Context context, OnClickedAndSwipedInterface listener ) {
        this.listOfRecipes = listOfRecipes;
        this.context = context;
        this.listener = listener;
    }

    public List<RecipeModel> getListOfRecipes() {
        return listOfRecipes;
    }

    public void setListOfRecipes(List<RecipeModel> listOfRecipes) {
        this.listOfRecipes = listOfRecipes;
        notifyDataSetChanged();
    }

    @Override
    public void onItemDismiss(int position) {

        notifyItemRemoved(position);
        listener.onSwiped(position);
    }

    //interface
    public interface OnClickedAndSwipedInterface {
        void onItemClicked(int index);
        void onSwiped(int index);
    }


    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.ViewHolder holder, int position) {
            final RecipeModel recipe =listOfRecipes.get(position);

            //binding data
            holder.bind(recipe.getRecipeName(), recipe.getRecipeImg());
    }

    @Override
    public int getItemCount() {
        return listOfRecipes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView thumbnailOfRecipe;
        TextView nameOfRecipe;


        private ViewHolder(View itemView) {
            super(itemView);
            thumbnailOfRecipe = (ImageView) itemView.findViewById(R.id.iv_recipe_image_thumbnail);
            nameOfRecipe = (TextView) itemView.findViewById(R.id.tv_recipe_name);
            itemView.setOnClickListener(this);
        }

        void bind(String name, String image){
            Picasso.with(context).load(image).into(thumbnailOfRecipe);
            nameOfRecipe.setText(name);
        }

        @Override
        public void onClick(View v) {
            int index = getAdapterPosition();
            listener.onItemClicked(index);
        }
    }
}

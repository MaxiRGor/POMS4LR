package maxir.gor.poms4lr;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import io.reactivex.Observable;
import maxir.gor.poms4lr.ui.main.PageViewModel;

import static maxir.gor.poms4lr.MainActivity.TAG;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.TypeViewHolder> {

    //private ArrayList<Post> posts;
    private PageViewModel pageViewModel;

    RecyclerViewAdapter(Context context) {
        pageViewModel = ViewModelProviders.of((MainActivity) context).get(PageViewModel.class);

    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post, parent, false);
        return new TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, int position) {
        pageViewModel.setPosition(Observable.just(position));
        holder.bind(pageViewModel.getPosts().get(position));
    }

    @Override
    public int getItemCount() {
        return pageViewModel.getPosts().size();
    }

/*    public void loadPosts(ArrayList<Post> posts) {
        this.pageViewModel.getPosts().clear();
        this.pageViewModel.getPosts() = posts;
        notifyDataSetChanged();
    }

    public void loadPost(Post post) {
        this.posts.clear();
        this.posts.add(post);
        notifyDataSetChanged();
    }*/


    class TypeViewHolder extends RecyclerView.ViewHolder {
        TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.post_id);
            titleTextView = itemView.findViewById(R.id.post_title);
            bodyTextView = itemView.findViewById(R.id.post_body);
        }

        TextView idTextView;
        TextView titleTextView;
        TextView bodyTextView;

        void bind(final Post post) {
            idTextView.setText(String.valueOf(post.getId()));
            titleTextView.setText(post.getTitle());
            bodyTextView.setText(post.getBody());
        }
    }

}
package maxir.gor.poms4lr;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.SearchView;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import maxir.gor.poms4lr.ui.main.PageViewModel;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "myLOGS";
    private RecyclerViewAdapter adapter;
    PageViewModel pageViewModel;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        SearchView searchView = findViewById(R.id.searchView);
        adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(onQueryTextListener());
    }


    @Override
    protected void onStart() {
        super.onStart();
        checkToLoadPosts();
    }

    private void checkToLoadPosts() {
        if (pageViewModel.getPosts().size() == 0) {
            loadPosts();
        }
    }

    private SearchView.OnQueryTextListener onQueryTextListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return onLetterTapped(s);
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return onLetterTapped(s);
            }

            private boolean onLetterTapped(String s) {
                if (invalid(s)) {
                    loadPosts();
                    return false;
                }
                loadPost(Integer.valueOf(s));
                return false;
            }

            private boolean invalid(String s) {
                return s.length() == 0;
            }
        };
    }

    private void loadPost(int id) {
        Observable<Post> observable = NetworkService.getInstance().getJSONApi().getPost(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Post post) {
                        if (post != null) {
                            ArrayList<Post> posts = new ArrayList<>();
                            posts.add(post);
                            pageViewModel.setPosts(posts);
                            adapter.notifyDataSetChanged();
                            Log.d(TAG, "loaded post");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("INFO")
                                .setMessage("Nothing found...")
                                .setIcon(android.R.drawable.ic_dialog_info)
                                .setCancelable(false)
                                .setNegativeButton("Close",
                                        (dialog, id1) -> dialog.cancel());
                        AlertDialog alert = builder.create();
                        alert.show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void loadPosts() {
        Observable<List<Post>> observable = NetworkService.getInstance().getJSONApi().getAllPosts();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        if (pageViewModel.getPosition().subscribe(integer -> recyclerView.smoothScrollToPosition(integer)).isDisposed())
                            Log.d(TAG, "disposed");
                        pageViewModel.setPosts((ArrayList<Post>) posts);
                        adapter.notifyDataSetChanged();
                        Log.d(TAG, "loaded posts");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "handle error " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}


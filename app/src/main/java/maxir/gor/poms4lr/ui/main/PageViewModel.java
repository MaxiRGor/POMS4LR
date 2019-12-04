package maxir.gor.poms4lr.ui.main;

import android.app.Application;
import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import maxir.gor.poms4lr.NetworkService;
import maxir.gor.poms4lr.Post;

import static maxir.gor.poms4lr.MainActivity.TAG;

public class PageViewModel extends ViewModel {
    public PageViewModel() {
        Log.d(TAG, "PageViewModel created");
        position = Observable.just(0);
        posts = new ArrayList<>();
    }

    private ArrayList<Post> posts;

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    private Observable<Integer> position;

    public Observable<Integer> getPosition() {
        return position;
    }

    public void setPosition(Observable<Integer> position) {
        this.position = position;
    }

}



/*

    private Observable<List<Post>> posts;

    public Observable<List<Post>> getPosts() {
        return posts;
    }

    public void setPosts(Observable<List<Post>> posts) {
        this.posts = posts;
    }


     void loadOne(int id) {
        Observable<Post> observable = NetworkService.getInstance().getJSONApi().getPost(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Post post) {
                        ArrayList<Post> posts1 = new ArrayList<>();
                        posts1.add(post);
                        setPosts(Observable.just(posts1));
                        Log.d(TAG, post.toString());
                        Log.d(TAG, "posts setted");
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


     void loadFullListOfPosts() {
        Observable<List<Post>> observable = NetworkService.getInstance().getJSONApi().getAllPosts();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        setPosts(Observable.just(posts));
*/
/*                        adapter.loadPosts((ArrayList<Post>) posts);
                        pageViewModel.getPosition().subscribe(integer -> recyclerView.smoothScrollToPosition(integer));*//*

                        Log.d(TAG, posts.toString());
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
*/


package maxir.gor.poms4lr;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderAPI {
    @GET("/posts")
    Observable<List<Post>> getAllPosts();

    @GET("/posts/{id}")
    Observable<Post> getPost(@Path("id")int id);
}


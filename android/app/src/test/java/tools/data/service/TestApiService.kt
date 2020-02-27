package tools.data.service

import com.movies.api.Movie
import com.movies.api.MovieDetail
import com.movies.data.service.ApiService
import io.reactivex.Maybe
import io.reactivex.Single

class TestApiService : ApiService {

    // TODO: allow to provide test data
    override fun getMovies(): Single<List<Movie>> {
        return Single.just(emptyList())
    }

    // TODO: allow to provide test data
    override fun getMovieDetail(id: Long): Maybe<MovieDetail> {
        return Maybe.empty()
    }
}
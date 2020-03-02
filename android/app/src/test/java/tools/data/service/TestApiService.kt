package tools.data.service

import com.movies.api.Movie
import com.movies.api.MovieDetail
import com.movies.data.service.ApiService
import io.reactivex.Maybe
import io.reactivex.Single
import tools.rx.TestSubject

class TestApiService : ApiService {

    val movies = TestSubject<List<Movie>>()
    val movieDetail = TestSubject<MovieDetail>()

    override fun getMovies(): Single<List<Movie>> {
        return movies.toSingle()
    }

    override fun getMovieDetail(id: Long): Maybe<MovieDetail> {
        return movieDetail.toMaybe()
    }
}
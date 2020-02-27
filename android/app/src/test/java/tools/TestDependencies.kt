package tools

import com.movies.ui.movieDetail.MovieDetailPm
import com.movies.ui.moviesList.MoviesListPm
import tools.data.service.TestApiService
import javax.inject.Inject

class TestDependencies {

    @Inject
    lateinit var apiService: TestApiService

    @Inject
    lateinit var moviesListPm: MoviesListPm

    @Inject
    lateinit var movieDetailPm: MovieDetailPm

}
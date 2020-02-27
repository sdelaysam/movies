package com.movies.di

import com.movies.ui.movieDetail.MovieDetailPm
import com.movies.ui.moviesList.MoviesListPm
import com.movies.util.di.PmKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import md.medici.util.di.pm.PmFactory
import md.medici.util.di.pm.PmFactoryImpl
import me.dmdev.rxpm.PresentationModel

@Module
abstract class PmModule {

    @Binds
    abstract fun bindPmFactory(factory: PmFactoryImpl): PmFactory

    @Binds
    @IntoMap
    @PmKey(MoviesListPm::class)
    abstract fun bindMoviesListPm(pm: MoviesListPm): PresentationModel

    @Binds
    @IntoMap
    @PmKey(MovieDetailPm::class)
    abstract fun bindMovieDetailPm(pm: MovieDetailPm): PresentationModel
}
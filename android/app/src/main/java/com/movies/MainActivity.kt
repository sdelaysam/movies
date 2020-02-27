package com.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.movies.navigation.Navigation
import com.movies.ui.movieDetail.MovieDetailController
import com.movies.ui.moviesList.MoviesListController
import kotlinx.android.synthetic.main.activity_main.*
import me.dmdev.rxpm.navigation.NavigationMessage
import me.dmdev.rxpm.navigation.NavigationMessageHandler

class MainActivity : AppCompatActivity(), NavigationMessageHandler {

    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureRouter(savedInstanceState)
    }

    private fun configureRouter(savedInstanceState: Bundle?) {
        router = Conductor.attachRouter(this, root_container, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(MoviesListController()))
        }
    }

    override fun handleNavigationMessage(message: NavigationMessage): Boolean {
        when (message) {
            is Navigation.Back -> onBackPressed()
            is Navigation.MoviesList -> showMoviesList()
            is Navigation.MovieDetail -> showMovieDetail(message.id)
        }
        return true
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

    private fun showMoviesList() {
        router.popToRoot()
    }

    private fun showMovieDetail(movieId: Long) {
        val transaction = RouterTransaction.with(MovieDetailController.newInstance(movieId))
        router.pushController(transaction)
    }
}
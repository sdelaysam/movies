#include <string>
#include <thread>
#include <chrono>
#include "MovieApi.hpp"

using namespace std::string_literals;

namespace movies {

    std::shared_ptr<Api> Api::create() {
        static std::shared_ptr<Api> instance(new MovieApi());
        return instance;
    }

    MovieApi::MovieApi() {
        for (auto i = 0; i < 10; i++) {
            auto name = "Top Gun " + std::to_string(i);
            auto description = "As students at the United States Navy's elite fighter weapons school compete to be best in the class, one daring young pilot learns a few things from a civilian instructor that are not taught in the classroom."s;
            movies.push_back(Movie(i, name, i * 10000));
            movieDetails.insert({ i, MovieDetail(i, name, description, rand() % 10, geMovieActors(i)) });
        }
    }

    std::vector<Movie> MovieApi::getMovies() {
        std::this_thread::sleep_for(std::chrono::milliseconds(1500));
        return movies;
    }

    std::optional<MovieDetail> MovieApi::getMovieDetail(int64_t id) {
        std::this_thread::sleep_for(std::chrono::milliseconds(1500));
        auto it = movieDetails.find(id);
        if (it != movieDetails.end()) {
            return it->second;
        }
        return std::nullopt;
    }

    std::vector<Actor> MovieApi::geMovieActors(int64_t id) {
        std::vector<Actor> actors;
        actors.push_back(Actor("Tom Cruise", 50, "https://cdn.steemitimages.com/DQmScrfSNQJpH5Q6YkbeSBcGNJp1GC3AF3CpsPtPiFqUExq/img_tamiguet_20180206-123646_imagenes_lv_gtres_dl_u376773_076.jpg"));
        actors.push_back(Actor("Val Kilmer", 46, "https://m.media-amazon.com/images/M/MV5BMTk3ODIzMDA5Ml5BMl5BanBnXkFtZTcwNDY0NTU4Ng@@._V1_UY317_CR4,0,214,317_AL_.jpg"));
        if (id % 2 == 0) {
            actors.push_back(Actor("Tim Robbins", 55, "https://m.media-amazon.com/images/M/MV5BMTI1OTYxNzAxOF5BMl5BanBnXkFtZTYwNTE5ODI4._V1_UY317_CR16,0,214,317_AL_.jpg"));
        } else {
            actors.push_back(Actor("Jennifer Connelly", 39, "https://m.media-amazon.com/images/M/MV5BOTczNTgzODYyMF5BMl5BanBnXkFtZTcwNjk4ODk4Mw@@._V1_UY317_CR12,0,214,317_AL_.jpg"));
        }
        return actors;
    }
}

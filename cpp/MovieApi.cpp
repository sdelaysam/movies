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
            auto posterUrl = getMoviePoster(i);
            movies.push_back(Movie(i, name, posterUrl, i * 10000));
            movieDetails.insert({ i, MovieDetail(i, name, description, posterUrl, rand() % 10, geMovieActors(i)) });
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
        actors.push_back(Actor("Tom Cruise", 50, "https://m.media-amazon.com/images/M/MV5BMTk1MjM3NTU5M15BMl5BanBnXkFtZTcwMTMyMjAyMg@@._V1_.jpg"));
        actors.push_back(Actor("Val Kilmer", 46, "https://m.media-amazon.com/images/M/MV5BMTk3ODIzMDA5Ml5BMl5BanBnXkFtZTcwNDY0NTU4Ng@@._V1_UY317_CR4,0,214,317_AL_.jpg"));
        if (id % 2 == 0) {
            actors.push_back(Actor("Tim Robbins", 55, "https://m.media-amazon.com/images/M/MV5BMTI1OTYxNzAxOF5BMl5BanBnXkFtZTYwNTE5ODI4._V1_UY317_CR16,0,214,317_AL_.jpg"));
        } else {
            actors.push_back(Actor("Jennifer Connelly", 39, "https://m.media-amazon.com/images/M/MV5BOTczNTgzODYyMF5BMl5BanBnXkFtZTcwNjk4ODk4Mw@@._V1_UY317_CR12,0,214,317_AL_.jpg"));
        }
        return actors;
    }

    std::string MovieApi::getMoviePoster(int64_t id) {
        switch (id) {
            case 0: return "https://m.media-amazon.com/images/M/MV5BZjQxYTA3ODItNzgxMy00N2Y2LWJlZGMtMTRlM2JkZjI1ZDhhXkEyXkFqcGdeQXVyNDk3NzU2MTQ@._V1_SY1000_CR0,0,646,1000_AL_.jpg"s;
            case 1: return "https://m.media-amazon.com/images/M/MV5BNTEyYTA5YWYtYmIxYS00NWRlLWExNjMtNjliZmVlZDgxNTBlXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_SY1000_CR0,0,679,1000_AL_.jpg"s;
            case 2: return "https://i.pinimg.com/474x/c9/86/71/c9867193b0eb3f1a85888891bd8dbd9c.jpg"s;
            case 3: return "https://i.etsystatic.com/13201187/r/il/6586c2/1187722454/il_570xN.1187722454_kpl0.jpg"s;
            case 4: return "https://i.pinimg.com/736x/f4/ae/2f/f4ae2f74a4febf9413cbc902660688a9.jpg"s;
            case 5: return "https://i.pinimg.com/originals/fe/09/ee/fe09ee43911562e308dc17bc5eabbcae.jpg"s;
            case 6: return "https://m.media-amazon.com/images/M/MV5BZjQxYTA3ODItNzgxMy00N2Y2LWJlZGMtMTRlM2JkZjI1ZDhhXkEyXkFqcGdeQXVyNDk3NzU2MTQ@._V1_SY1000_CR0,0,646,1000_AL_.jpg"s;
            case 7: return "https://m.media-amazon.com/images/M/MV5BNTEyYTA5YWYtYmIxYS00NWRlLWExNjMtNjliZmVlZDgxNTBlXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_SY1000_CR0,0,679,1000_AL_.jpg"s;
            case 8: return "https://i.pinimg.com/474x/c9/86/71/c9867193b0eb3f1a85888891bd8dbd9c.jpg"s;
            case 9: return "https://i.etsystatic.com/13201187/r/il/6586c2/1187722454/il_570xN.1187722454_kpl0.jpg"s;
            default: return "https://i.pinimg.com/originals/fe/09/ee/fe09ee43911562e308dc17bc5eabbcae.jpg"s;
        }
    }

}

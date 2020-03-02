#pragma once

#include <optional>
#include <map>
#include "Api.hpp"
#include "Movie.hpp"
#include "MovieDetail.hpp"
#include "Actor.hpp"

namespace movies {

class MovieApi : public Api {

public:
    MovieApi();
    std::vector<Movie> getMovies();
    std::optional<MovieDetail> getMovieDetail(int64_t id);
private:
    std::vector<Movie> movies;
    std::map<int64_t, MovieDetail> movieDetails;
    std::vector<Actor> geMovieActors(int64_t id);
    std::string getMoviePoster(int64_t id);
};

}

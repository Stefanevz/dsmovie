package com.springboot.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.dsmovie.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}

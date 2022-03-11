package com.springboot.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.dsmovie.entities.Score;
import com.springboot.dsmovie.entities.ScorePK;

public interface ScoreRepository extends JpaRepository<Score, ScorePK>{

}

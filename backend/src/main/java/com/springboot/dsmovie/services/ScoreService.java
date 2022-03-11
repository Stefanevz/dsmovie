package com.springboot.dsmovie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.dsmovie.dto.MovieDTO;
import com.springboot.dsmovie.dto.ScoreDTO;
import com.springboot.dsmovie.entities.Movie;
import com.springboot.dsmovie.entities.Score;
import com.springboot.dsmovie.entities.User;
import com.springboot.dsmovie.repositories.MovieRepository;
import com.springboot.dsmovie.repositories.ScoreRepository;
import com.springboot.dsmovie.repositories.UserRepository;

@Service
public class ScoreService {
	
	@Autowired
	private MovieRepository movierepository;
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private ScoreRepository scorerepository;
	
	@Transactional
	public MovieDTO saveScore(ScoreDTO dto) {
		User user = userrepository.findByEmail(dto.getEmail());
		if(user == null) {
			user = new User();
			user.setEmail(dto.getEmail());
			user = userrepository.saveAndFlush(user);
		}
		Movie movie = movierepository.findById(dto.getMovieId()).get();
		
		Score score = new Score();
		score.setMovie(movie);
		score.setUser(user);
		score.setValue(dto.getScore());
		
		score = scorerepository.saveAndFlush(score);
		
		double sum = 0.0;
		for(Score s : movie.getScores()) {
			sum = sum + s.getValue();
		}
		
		double avg = sum / movie.getScores().size();
		
		movie.setScore(avg);
		movie.setCount(movie.getScores().size());
		
		movie = movierepository.save(movie);
		
		return new MovieDTO(movie);
	}
	

}

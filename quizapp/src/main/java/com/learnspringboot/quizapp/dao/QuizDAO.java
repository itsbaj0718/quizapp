package com.learnspringboot.quizapp.dao;

import com.learnspringboot.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDAO extends JpaRepository<Quiz,Integer> {
}

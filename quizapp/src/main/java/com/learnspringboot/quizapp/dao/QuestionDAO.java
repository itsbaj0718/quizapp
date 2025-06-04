package com.learnspringboot.quizapp.dao;


import com.learnspringboot.quizapp.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question,Integer> {

    List<Question> findByDiffLevel(String diffLevel);

    void deleteById(Integer id);

//    String deleteByDiffLevel(String diffLevel);
}

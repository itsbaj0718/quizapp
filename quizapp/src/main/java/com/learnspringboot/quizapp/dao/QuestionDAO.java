package com.learnspringboot.quizapp.dao;


import com.learnspringboot.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question,Integer> {

    List<Question> findByDiffLevel(String diffLevel);

    void deleteById(Integer id);

    @Query(value="select * from question q where q.category=:category order by RANDOM() LIMIT 4",nativeQuery = true)
    List<Question> createQuestionByCategory(String category, int numQ);

//    String deleteByDiffLevel(String diffLevel);
}

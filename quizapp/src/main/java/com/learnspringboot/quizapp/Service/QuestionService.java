package com.learnspringboot.quizapp.Service;

import com.learnspringboot.quizapp.Question;
import com.learnspringboot.quizapp.dao.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
    }

    public List<Question> getQuestionsByDifficultyLevel(String diffLevel) {
        return questionDao.findByDiffLevel(diffLevel);
    }

    public ResponseEntity<String> removeQuestion(Integer id) {
        if (questionDao.existsById(id)) {
            questionDao.deleteById(id);
            return ResponseEntity.ok("Question deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return ResponseEntity.status(HttpStatus.CREATED).body("Question added successfully");
    }

    public ResponseEntity<String> updateQuestion(Question question, Integer id) {
        if(questionDao.existsById(id)) {
            questionDao.save(question);
            return ResponseEntity.ok("Question updated successfully");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question doesn't exist");
        }
    }
}

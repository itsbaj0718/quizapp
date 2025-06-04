package com.learnspringboot.quizapp.controller;

import com.learnspringboot.quizapp.Service.QuizService;
import com.learnspringboot.quizapp.model.Question;
import com.learnspringboot.quizapp.model.QuestionWrapper;
import com.learnspringboot.quizapp.model.Quiz;
import com.learnspringboot.quizapp.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submit(@PathVariable Integer id, @RequestBody List<UserResponse> responses){
        return quizService.calculateResult(id,responses);
    }
}

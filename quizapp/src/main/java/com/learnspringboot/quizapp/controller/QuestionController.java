package com.learnspringboot.quizapp.controller;

import com.learnspringboot.quizapp.Question;
import com.learnspringboot.quizapp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> allQuestion(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{diffLevel}")
    public List<Question> getQuestionsByDifficultyLevel(@PathVariable String diffLevel){
        return questionService.getQuestionsByDifficultyLevel(diffLevel);
    }

    @DeleteMapping("category/{id}")
    public ResponseEntity<String> removeQuestion(@PathVariable Integer id){
        return questionService.removeQuestion(id);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question,@PathVariable Integer id){
        return questionService.updateQuestion(question,id);
    }
}

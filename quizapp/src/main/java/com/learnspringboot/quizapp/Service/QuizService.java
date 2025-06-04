package com.learnspringboot.quizapp.Service;

import com.learnspringboot.quizapp.dao.QuestionDAO;
import com.learnspringboot.quizapp.dao.QuizDAO;
import com.learnspringboot.quizapp.model.Question;
import com.learnspringboot.quizapp.model.QuestionWrapper;
import com.learnspringboot.quizapp.model.Quiz;
import com.learnspringboot.quizapp.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDAO quizDao;
    @Autowired
    QuestionDAO questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions=questionDao.createQuestionByCategory(category,numQ);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        try{
            Optional<Quiz> quiz=quizDao.findById(id);

            if(quiz.isEmpty()){
                return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
            }
            List<Question> questionsFromDB= quiz.get().getQuestions();
            List<QuestionWrapper> questionForUser= new ArrayList<>();

            for(Question q : questionsFromDB){
                QuestionWrapper qw= new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
                questionForUser.add(qw);
            }

            return new ResponseEntity<>(questionForUser,HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();

            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<UserResponse> responses) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        List<String> list=new ArrayList<>();
        int i=0;
        int count=0;
        for(UserResponse response:responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                count++;
            i++;
        }

        return new ResponseEntity<>(count,HttpStatus.OK);
    }
}

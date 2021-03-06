/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.quiz_master.user;

import com.sv.quiz_master.user.model.Question;
import com.sv.quiz_master.user.model.QuestionPaper;
import com.sv.quiz_master.user.model.QuizSession;
import com.sv.quiz_master.user.model.QuizSessionUser;
import com.sv.quiz_master.user.model.QuizSessionUserAnswer;
import com.sv.quiz_master.zsystem.QuizSessionStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mohan
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public QuizSession getQuizSession(Serializable quizSessionId) {
        QuizSession quizSession = (QuizSession) userRepository.getObject(QuizSession.class, quizSessionId);
        quizSession.getQuestionPaper();
        return quizSession;
    }

    @Override
    public List<QuizSession> getQuizSessionList() {
        return userRepository.getQuizSessionList();
    }

    @Override
    public QuizSessionUser saveQuizSessionUser(QuizSessionUser quizSessionUser) {
        Serializable userId = userRepository.saveObject(quizSessionUser);
        return (QuizSessionUser) userRepository.getObject(QuizSessionUser.class, userId);
    }

    @Override
    public QuizSession finishQuizSession(QuizSession quizSession) {
        quizSession.setFinishedOn(new Date());
        quizSession.setStatus(QuizSessionStatus.COMPLETED);
        userRepository.updateObject(quizSession);
        return quizSession;
    }

    @Override
    public void updateQuestionPaperLastUsed(QuestionPaper questionPaper) {
        userRepository.updateObject(questionPaper);
    }

    @Override
    public Question getNextQuestion(QuestionPaper questionPaper, Question currentQuestion) {
        return userRepository.getNextQuestion(questionPaper, currentQuestion);
    }

    @Override
    public void saveAnswer(QuizSession quizSession, QuizSessionUser quizSessionUser, QuestionPaper questionPaper, Question question, String answer, Integer duration) {
        QuizSessionUserAnswer quizSessionUserAnswer = new QuizSessionUserAnswer();

        //quizSessionUserAnswer.setIndexNo(null);   //auto increment
        quizSessionUserAnswer.setQuestionPaper(quizSession.getQuestionPaper());
        quizSessionUserAnswer.setQuestion(question);
        quizSessionUserAnswer.setQuizSession(quizSession);
        quizSessionUserAnswer.setQuizSessionUser(quizSessionUser);
        quizSessionUserAnswer.setAnswer(answer);
        quizSessionUserAnswer.setCorrect(question.getCorrectAnswer().equalsIgnoreCase(answer));
        quizSessionUserAnswer.setDuration(duration);

        userRepository.saveQuizSessionUserAnswer(quizSessionUserAnswer);
    }

    @Override
    public List<QuizSessionUserAnswer> listResults(QuizSessionUser quizSessionUser) {
        return userRepository.listResults(quizSessionUser);
    }
}

package com.sv.quiz_master.master.model;
// Generated May 31, 2016 8:46:19 AM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * QuizSession generated by hbm2java
 */
@Entity(name = "com.sv.quiz_master.master.model.QuizSession")
@Table(name = "quiz_session")
public class QuizSession implements java.io.Serializable {

    private Integer indexNo;
    private QuestionPaper questionPaper;
    private Date startedOn;
    private Date finishedOn;
    private String status;
    private String location;
    private Set<QuizSessionUser> quizSessionUsers = new HashSet<QuizSessionUser>(0);
    private Set<QuizSessionUserAnswer> quizSessionUserAnswers = new HashSet<QuizSessionUserAnswer>(0);

    public QuizSession() {
    }

    public QuizSession(QuestionPaper questionPaper, Date startedOn, String status, String location) {
        this.questionPaper = questionPaper;
        this.startedOn = startedOn;
        this.status = status;
        this.location = location;
    }

    public QuizSession(QuestionPaper questionPaper, Date startedOn, Date finishedOn, String status, String location, Set<QuizSessionUser> quizSessionUsers, Set<QuizSessionUserAnswer> quizSessionUserAnswers) {
        this.questionPaper = questionPaper;
        this.startedOn = startedOn;
        this.finishedOn = finishedOn;
        this.status = status;
        this.location = location;
        this.quizSessionUsers = quizSessionUsers;
        this.quizSessionUserAnswers = quizSessionUserAnswers;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "index_no", unique = true, nullable = false)
    public Integer getIndexNo() {
        return this.indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_paper", nullable = false)
    public QuestionPaper getQuestionPaper() {
        return this.questionPaper;
    }

    public void setQuestionPaper(QuestionPaper questionPaper) {
        this.questionPaper = questionPaper;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "started_on", nullable = false, length = 19)
    public Date getStartedOn() {
        return this.startedOn;
    }

    public void setStartedOn(Date startedOn) {
        this.startedOn = startedOn;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finished_on", length = 19)
    public Date getFinishedOn() {
        return this.finishedOn;
    }

    public void setFinishedOn(Date finishedOn) {
        this.finishedOn = finishedOn;
    }

    @Column(name = "status", nullable = false, length = 25)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "location", nullable = true, length = 50)
    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quizSession")
    public Set<QuizSessionUser> getQuizSessionUsers() {
        return this.quizSessionUsers;
    }

    public void setQuizSessionUsers(Set<QuizSessionUser> quizSessionUsers) {
        this.quizSessionUsers = quizSessionUsers;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quizSession")
    public Set<QuizSessionUserAnswer> getQuizSessionUserAnswers() {
        return this.quizSessionUserAnswers;
    }

    public void setQuizSessionUserAnswers(Set<QuizSessionUserAnswer> quizSessionUserAnswers) {
        this.quizSessionUserAnswers = quizSessionUserAnswers;
    }

}

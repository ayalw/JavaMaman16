package maman16.trivianet.triviacommon;

public interface QuestionProvider {
    Question getRandomQuestion();
    boolean hasUnusedQuestions();
}

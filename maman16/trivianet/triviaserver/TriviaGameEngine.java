package maman16.trivianet.triviaserver;

import maman16.trivianet.triviacommon.Question;

/**
 * This class holds non-UI and logic of the game.
 */
public class TriviaGameEngine {

    /**
     * The parsed questions.
     */
    private QuestionRepository m_questionRepository;

    /**
     * The current score.
     */
    private int m_score;

    /**
     * Constructor.
     * @param inputFileName
     */
    public TriviaGameEngine(String inputFileName) {
        m_questionRepository = new QuestionRepository(inputFileName);
        m_score = 0;
    }

    /**
     * Get next question after previous one has been answered.
     * @return
     */
    public Question getNextQuestion() {
        if (!m_questionRepository.hasUnusedQuestions()) {
            return null;
        }
        return  m_questionRepository.getRandomQuestion();
    }

    public boolean hasUnusedQuestion() {
        return m_questionRepository.hasUnusedQuestions();
    }

    /**
     * Get new score after new user action.
     * @param question is the current question on which the user has just answered.
     * @param chosenAnswer is the 'chosen' answer. If timeout occured, chosen answer will be the last selected option, or null if no option is selected.
     * @return
     */
    public int checkScore(Question question, String chosenAnswer) {
        if (question == null) return m_score;
        if (chosenAnswer != null && chosenAnswer.equals(question.getCorrectAnswer())) {
            m_score += 10;
        }
        else {
            m_score -=5;
        }
        return m_score;
    }
}

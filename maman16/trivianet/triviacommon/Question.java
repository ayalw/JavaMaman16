package maman16.trivianet.triviacommon;

import java.io.Serializable;

/**
 * Data structure for all information related to a single question.
 */
public class Question implements Serializable {

    /**
     * The question itself.
     */
    private String m_questionText;
    /**
     * The correct answer.
     */
    private String m_correctAnswer;

    /**
     * The additional incorrect answers.
     */
    private String m_incorrectAnswerA;
    private String m_incorrectAnswerB;
    private String m_incorrectAnswerC;

    /**
     * Constructor
     * @param questionText
     * @param correctAnswer
     * @param incorrectAnswerA
     * @param incorrectAnswerB
     * @param incorrectAnswerC
     */
    public Question(String questionText,
                    String correctAnswer,
                    String incorrectAnswerA,
                    String incorrectAnswerB,
                    String incorrectAnswerC) {
        m_questionText = questionText;
        m_correctAnswer = correctAnswer;
        m_incorrectAnswerA = incorrectAnswerA;
        m_incorrectAnswerB = incorrectAnswerB;
        m_incorrectAnswerC = incorrectAnswerC;
    }

    public String getQuestionText() {
        return m_questionText;
    }

    public String getCorrectAnswer() {
        return m_correctAnswer;
    }

    public String getIncorrectAnswerA() {
        return m_incorrectAnswerA;
    }

    public String getIncorrectAnswerB() {
        return m_incorrectAnswerB;
    }

    public String getIncorrectAnswerC() {
        return m_incorrectAnswerC;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Text: " + m_questionText + " | ");
        sb.append("IncorrectA: " + m_incorrectAnswerA + " | ");
        sb.append("IncorrectA: " + m_incorrectAnswerB + " | ");
        sb.append("IncorrectA: " + m_incorrectAnswerC + " | ");
        return sb.toString();
    }
}

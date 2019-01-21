package maman16.trivianet.triviacommon;

import java.util.ArrayList;
import java.util.Collections;

// This class is a wrapper for Question objects.
// When instantinated, it will shuffle the 4 possible answers and store them as (A), (B), (C), (D).

public class AnswersShuffler {

    private Question m_question;
    private String m_optionA = null;
    private String m_optionB = null;
    private String m_optionC = null;
    private String m_optionD = null;

    /**
     * Constructor
     * @param question to be shuffled.
     */
    public AnswersShuffler(Question question) {
        ArrayList<String> allOptions = new ArrayList<>();
        allOptions.add(question.getCorrectAnswer());
        allOptions.add(question.getIncorrectAnswerA());
        allOptions.add(question.getIncorrectAnswerB());
        allOptions.add(question.getIncorrectAnswerC());
        Collections.shuffle(allOptions);
        m_optionA = allOptions.get(0);
        m_optionB = allOptions.get(1);
        m_optionC = allOptions.get(2);
        m_optionD = allOptions.get(3);
    }

    public String getOptionA() {
        return m_optionA;
    }

    public String getOptionB() {
        return m_optionB;
    }

    public String getOptionC() {
        return m_optionC;
    }

    public String getOptionD() {
        return m_optionD;
    }
}

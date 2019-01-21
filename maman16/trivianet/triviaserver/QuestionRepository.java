package maman16.trivianet.triviaserver;

import maman16.trivianet.triviacommon.Question;
import maman16.trivianet.triviacommon.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Data structure for all questions provided in the input .txt file.
 */
public class QuestionRepository {

    /**
     * Every parsed question is assigned an ID and stored.
     */
    private Map<Integer, Question> m_allQuestions;

    /**
     * Every parsed question is tracked whether it's been in use or not.
     */
    private Map<Integer, Boolean> m_questionsUsed;

    /**
     * Constructor.
     * @param inputFile with the questions data.
     *                  Assumptions:
     *                  5 lines for each question.
     *                  Blank lines are used to separate questions.
     */
    public QuestionRepository(String inputFile) {
        try {
                URL url = getClass().getResource("Trivia.txt");
                File file = new File(url.getPath());
                Scanner input = new Scanner(file);
                m_allQuestions = new HashMap<Integer, Question>();
                m_questionsUsed = new HashMap<Integer, Boolean>();
                int runningId = 0;
                while (input.hasNext()) {
                    String line = input.nextLine();
                    if (Utils.isNullOrWhitespace(line)) continue;
                    String questionText = line;

                    if (!input.hasNext()) break;
                    line = input.nextLine();
                    if (Utils.isNullOrWhitespace(line)) continue;
                    String correctAnswer = line;

                    if (!input.hasNext()) break;
                    line = input.nextLine();
                    if (Utils.isNullOrWhitespace(line)) continue;
                    String incorrectAnswerA = line;

                    if (!input.hasNext()) break;
                    line = input.nextLine();
                    if (Utils.isNullOrWhitespace(line)) continue;
                    String incorrectAnswerB = line;

                    if (!input.hasNext()) break;
                    line = input.nextLine();
                    if (Utils.isNullOrWhitespace(line)) continue;
                    String incorrectAnswerC = line;

                    Question question = new Question(questionText,
                            correctAnswer,
                            incorrectAnswerA,
                            incorrectAnswerB,
                            incorrectAnswerC);
                    m_allQuestions.put(runningId, question);
                    m_questionsUsed.put(runningId, false);
                    runningId++;
                }
                input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Provide a random question from the repository.
     * Once provided, the question is marked as 'been used' for future calls.
     * @return
     */
    public Question getRandomQuestion() {
        while (hasUnusedQuestions()) {
            int randomId = ThreadLocalRandom.current().nextInt(0, m_questionsUsed.size());
            if (m_questionsUsed.get(randomId) == false) {
                m_questionsUsed.put(randomId, true);
                return m_allQuestions.get(randomId);
            }
        }
        return null;
    }

    /**
     * Check if unused questions are available to provide.
     * @return
     */
    public boolean hasUnusedQuestions() {
        for (int i=0; i<m_questionsUsed.size(); i++) {
            if (m_questionsUsed.get(i) == false) return true;
        }
        return false;
    }
}

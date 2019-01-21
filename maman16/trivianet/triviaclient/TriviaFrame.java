package maman16.trivianet.triviaclient;

import maman16.trivianet.triviacommon.AnswersShuffler;
import maman16.trivianet.triviacommon.Constants;
import maman16.trivianet.triviacommon.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

/**
 * This class holds the UI representation of the trivia game.
 * Single frame (window).
 */
public class TriviaFrame extends JFrame {

    private JPanel questionPanel;
    private JPanel answersPanel;
    private JTextArea questionText;
    private JRadioButton optionA;
    private JRadioButton optionB;
    private JRadioButton optionC;
    private JRadioButton optionD;
    private JButton selectButton;
    private ButtonGroup answersGroup;
    private JPanel buttonsPanel;
    private JButton startNewGameButton;
    private JLabel scoreLabel;

    private TriviaGameEngine m_engine;
    private Question m_currentQuestion;
    private Timer m_timer;

    /**
     * Constructor.
     */
    public TriviaFrame() {
        super("Trivia");
        initTriviaFrame();
        reset();
    }

    /**
     * Start a new game and clear previous score and data.
     */
    private void reset() {
        m_engine = new TriviaGameEngine("input.txt");
        m_currentQuestion = m_engine.getNextQuestion();
        scoreLabel.setText("Score: 0");
        this.selectButton.setEnabled(true);
        this.answersGroup.clearSelection();
        displayQuestion(m_currentQuestion);
    }

    /**
     * Init UI components.
     */
    private void initTriviaFrame() {
        questionPanel = new JPanel();
        questionText = new JTextArea();
        questionPanel.add(questionText);
        add(questionPanel, BorderLayout.NORTH);
        answersPanel = new JPanel();
        answersPanel.setLayout(new GridLayout(5,1));
        optionA = new JRadioButton();
        optionB = new JRadioButton();
        optionC = new JRadioButton();
        optionD = new JRadioButton();
        selectButton = new JButton("Choose Answer and Proceed to Next Question");
        answersGroup = new ButtonGroup();
        answersGroup.add(optionA);
        answersGroup.add(optionB);
        answersGroup.add(optionC);
        answersGroup.add(optionD);
        answersPanel.add(optionA);
        answersPanel.add(optionB);
        answersPanel.add(optionC);
        answersPanel.add(optionD);
        answersPanel.add(selectButton);
        add(answersPanel, BorderLayout.CENTER);
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout());
        startNewGameButton = new JButton("Start New Game");
        buttonsPanel.add(startNewGameButton);
        add(buttonsPanel, BorderLayout.SOUTH);
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setForeground(Color.RED);
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 36));
        scoreLabel.setVisible(true);
        add(scoreLabel, BorderLayout.AFTER_LINE_ENDS);
        setSize(Constants.WINDOW_WIDTH_PIXELS,Constants.WINDOW_HEIGHT_PIXELS);
        setVisible(true);
        setResizable(false);
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onFinishQuestion();
            }
        } );
        startNewGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        } );
    }

    /**
     * Evaluate score after a question has been answered.
     * A question is considered as being answered iff
     * 1. User clicks on 'select answer and proceed'.
     * 2. Timeout occurs for given question.
     */
    private void onFinishQuestion() {
        m_timer.cancel();
        m_timer.purge();
        String chosenAnswer = "";
        if (this.optionA.isSelected()) chosenAnswer = this.optionA.getText();
        if (this.optionB.isSelected()) chosenAnswer = this.optionB.getText();
        if (this.optionC.isSelected()) chosenAnswer = this.optionC.getText();
        if (this.optionD.isSelected()) chosenAnswer = this.optionD.getText();
        this.scoreLabel.setText("Score: " + m_engine.checkScore(m_currentQuestion, chosenAnswer));
        m_currentQuestion = m_engine.getNextQuestion();
        if (m_currentQuestion != null) {
            displayQuestion(m_currentQuestion);
            this.answersGroup.clearSelection();
        }
        else {
            this.selectButton.setEnabled(false);
            this.scoreLabel.setBackground(Color.YELLOW);
            this.scoreLabel.setText("FINAL Score: " + m_engine.checkScore(m_currentQuestion, chosenAnswer));
        }
    }

    /**
     * Refresh relevant UI components when a new question is provided.
     * @param question
     */
    private void displayQuestion(Question question) {
        questionText.setText(question.getQuestionText());
        AnswersShuffler shuffler = new AnswersShuffler(question);
        optionA.setText(shuffler.getOptionA());
        optionB.setText(shuffler.getOptionB());
        optionC.setText(shuffler.getOptionC());
        optionD.setText(shuffler.getOptionD());
        m_timer = new Timer();
        m_timer.schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        onFinishQuestion();
                    }
                },
                Constants.TIME_LIMIT_SECONDS * 1000
        );
    }
}

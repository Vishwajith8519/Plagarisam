import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class PlagiarismRemoverApp {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Plagiarism Remover");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Input text area
        JTextArea inputArea = new JTextArea();
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);

        // Scroll pane for the input area
        JScrollPane inputScrollPane = new JScrollPane(inputArea);
        inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Output text area
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        // Scroll pane for the output area
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        outputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Button to trigger plagiarism removal
        JButton processButton = new JButton("Remove Plagiarism");

        // Panel to hold the button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(processButton);

        // Add components to the frame
        frame.setLayout(new BorderLayout());
        frame.add(inputScrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(outputScrollPane, BorderLayout.SOUTH);

        // Action listener for the button
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputArea.getText();
                if (inputText.isEmpty()) {
                    outputArea.setText("Please enter some text to process.");
                } else {
                    String rewrittenText = removePlagiarism(inputText);
                    outputArea.setText(rewrittenText);
                }
            }
        });

        // Display the frame
        frame.setVisible(true);
    }

    /**
     * Method to remove plagiarism by rephrasing text.
     * This involves basic synonym replacement and sentence restructuring.
     *
     * @param text The input text.
     * @return The rewritten content with reduced similarity.
     */
    public static String removePlagiarism(String text) {
        // Basic synonym dictionary
        Map<String, String> synonyms = new HashMap<>();
        synonyms.put("check", "verify");
        synonyms.put("plagiarism", "duplication");
        synonyms.put("submitted", "provided");
        synonyms.put("text", "content");
        synonyms.put("identify", "detect");
        synonyms.put("highlight", "emphasize");
        synonyms.put("sections", "parts");
        synonyms.put("database", "repository");
        synonyms.put("similarity", "likeness");
        synonyms.put("content", "material");
        synonyms.put("algorithm", "process");

        // Split text into words
        String[] words = text.split("\\s+");
        StringBuilder rewrittenText = new StringBuilder();

        for (String word : words) {
            // Normalize word for dictionary lookup
            String cleanedWord = word.toLowerCase().replaceAll("[^a-zA-Z]", "");
            String punctuation = word.replaceAll("[a-zA-Z]", ""); // Preserve punctuation
            // Replace word with a synonym if available
            if (synonyms.containsKey(cleanedWord)) {
                rewrittenText.append(synonyms.get(cleanedWord));
            } else {
                rewrittenText.append(cleanedWord);
            }
            rewrittenText.append(punctuation).append(" ");
        }

        // Add basic sentence restructuring (simple demonstration)
        String[] sentences = rewrittenText.toString().split("\\.\\s*");
        StringBuilder restructuredText = new StringBuilder();
        for (String sentence : sentences) {
            if (sentence.trim().isEmpty()) continue;
            String[] sentenceWords = sentence.split(" ");
            if (sentenceWords.length > 4) {
                restructuredText.append(sentenceWords[sentenceWords.length - 1]).append(" ");
                for (int i = 0; i < sentenceWords.length - 1; i++) {
                    restructuredText.append(sentenceWords[i]).append(" ");
                }
            } else {
                restructuredText.append(sentence).append(" ");
            }
            restructuredText.append(". ");
        }

        return restructuredText.toString().trim();
    }
}
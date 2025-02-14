package gui;

import algorithms.BubbleSort;
import algorithms.InsertionSort;
import algorithms.MergeSort;
import algorithms.SelectionSort;
import algorithms.SortingAlgorithm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MainWindow extends JFrame {
    private JComboBox<String> algorithmComboBox;
    private JTextField elementCountField;
    private JButton sortButton;
    private SortingPanel sortingPanel;

    public MainWindow() {
        setTitle("Sorting Visualizer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        controlPanel.setBackground(new Color(60, 63, 65));

        JLabel algorithmLabel = new JLabel("Algorithm:");
        algorithmLabel.setForeground(Color.WHITE);
        algorithmLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel elementCountLabel = new JLabel("Number of Elements:");
        elementCountLabel.setForeground(Color.WHITE);
        elementCountLabel.setFont(new Font("Arial", Font.BOLD, 14));

        algorithmComboBox = new JComboBox<>(new String[]{"Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort"});
        algorithmComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        elementCountField = new JTextField(5);
        elementCountField.setFont(new Font("Arial", Font.PLAIN, 14));

        sortButton = new JButton("Sort");
        sortButton.setFont(new Font("Arial", Font.BOLD, 14));
        sortButton.setBackground(new Color(51, 153, 255));
        sortButton.setForeground(Color.WHITE);
        sortButton.setFocusPainted(false);

        controlPanel.add(algorithmLabel);
        controlPanel.add(algorithmComboBox);
        controlPanel.add(elementCountLabel);
        controlPanel.add(elementCountField);
        controlPanel.add(sortButton);

        sortingPanel = new SortingPanel();

        add(controlPanel, BorderLayout.NORTH);
        add(sortingPanel, BorderLayout.CENTER);

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int elementCount;
                try {
                    elementCount = Integer.parseInt(elementCountField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainWindow.this, "Please enter a valid number of elements.");
                    return;
                }

                int[] array = generateRandomArray(elementCount);
                sortingPanel.setArray(array);

                String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
                SortingAlgorithm algorithm;
                switch (selectedAlgorithm) {
                    case "Bubble Sort":
                        algorithm = new BubbleSort();
                        break;
                    case "Selection Sort":
                        algorithm = new SelectionSort();
                        break;
                    case "Insertion Sort":
                        algorithm = new InsertionSort();
                        break;
                    case "Merge Sort":
                        algorithm = new MergeSort();
                        break;
                    default:
                        algorithm = new BubbleSort();
                        break;
                }

                new Thread(() -> {
                    try {
                        algorithm.sort(array, sortingPanel);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }).start();
            }
        });
    }

    private int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size) + 1;
        }
        return array;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow();
            window.setVisible(true);
        });
    }
}

package ui.buttons;

import ui.JavaFit;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkoutsButton implements ListSelectionListener {

    private JFrame frame;
    private JPanel pushPanel;
    private JPanel pullPanel;
    private JPanel legsPanel;
    private JList pushList;
    private JList pullList;
    private JList legsList;
    private DefaultListModel pushListModel;
    private DefaultListModel pullListModel;
    private DefaultListModel legsListModel;
    private AddPushExerciseListener addPushExerciseListener;
    private AddPullExerciseListener addPullExerciseListener;
    private AddLegsExerciseListener addLegsExerciseListener;

    public WorkoutsButton() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(900, 420));
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        setUpFrame();
    }

    public void setUpFrame() {
        setUpPushPanel();
        addPushExerciseListener = new AddPushExerciseListener();

        setUpPullPanel();
        addPullExerciseListener = new AddPullExerciseListener();

        setUpLegsPanel();
        addLegsExerciseListener = new AddLegsExerciseListener();

        frame.add(pushPanel, BorderLayout.WEST);
        frame.add(pullPanel, BorderLayout.CENTER);
        frame.add(legsPanel, BorderLayout.EAST);
    }

    public void setUpPushPanel() {
        pushPanel = new JPanel();
        pushPanel.setSize(new Dimension(300, 420));
        pushPanel.setLayout(new BoxLayout(pushPanel, BoxLayout.PAGE_AXIS));
        pushListModel = new DefaultListModel();
        pushList = new JList(pushListModel);
        pushList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pushList.setSelectedIndex(0);
        pushList.addListSelectionListener(this);
        pushList.setVisibleRowCount(400);
        JScrollPane listScrollPane = new JScrollPane(pushList);

        JButton pushAddButton = new JButton("Add New Push Exercise!");
        JLabel pushTitle = new JLabel("Push Workout");
        pushPanel.add(pushTitle);
        pushPanel.add(listScrollPane);
        JPanel pushAddPanel = new JPanel();
        JTextField pushTextField = new JTextField(10);
        pushAddPanel.setLayout(new BoxLayout(pushAddPanel, BoxLayout.LINE_AXIS));
        pushAddPanel.add(pushAddButton);
        pushAddPanel.add(pushTextField);
        pushPanel.add(pushAddPanel, BorderLayout.SOUTH);
    }

    public void setUpPullPanel() {
        pullPanel = new JPanel();
        pullPanel.setSize(new Dimension(300, 420));
        pullPanel.setLayout(new BoxLayout(pullPanel, BoxLayout.PAGE_AXIS));
        pullListModel = new DefaultListModel();
        pullList = new JList(pushListModel);
        pullList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pullList.setSelectedIndex(0);
        pullList.addListSelectionListener(this);
        pullList.setVisibleRowCount(400);
        JScrollPane listScrollPane = new JScrollPane(pullList);

        JButton pushAddButton = new JButton("Add New Pull Exercise!");
        JLabel pushTitle = new JLabel("Pull Workout");
        pullPanel.add(pushTitle);
        pullPanel.add(listScrollPane);
        JPanel pushAddPanel = new JPanel();
        JTextField pushTextField = new JTextField(10);
        pushAddPanel.setLayout(new BoxLayout(pushAddPanel, BoxLayout.LINE_AXIS));
        pushAddPanel.add(pushAddButton);
        pushAddPanel.add(pushTextField);
        pullPanel.add(pushAddPanel, BorderLayout.SOUTH);
    }

    public void setUpLegsPanel() {
        legsPanel = new JPanel();
        legsPanel.setSize(new Dimension(300, 420));
        legsPanel.setLayout(new BoxLayout(legsPanel, BoxLayout.PAGE_AXIS));
        legsListModel = new DefaultListModel();
        legsList = new JList(legsListModel);
        legsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        legsList.setSelectedIndex(0);
        legsList.addListSelectionListener(this);
        legsList.setVisibleRowCount(400);
        JScrollPane listScrollPane = new JScrollPane(legsList);

        JButton pushAddButton = new JButton("Add New Legs Exercise!");
        JLabel pushTitle = new JLabel("Legs Workout");
        legsPanel.add(pushTitle);
        legsPanel.add(listScrollPane);
        JPanel pushAddPanel = new JPanel();
        JTextField pushTextField = new JTextField(10);
        pushAddPanel.setLayout(new BoxLayout(pushAddPanel, BoxLayout.LINE_AXIS));
        pushAddPanel.add(pushAddButton);
        pushAddPanel.add(pushTextField);
        legsPanel.add(pushAddPanel, BorderLayout.SOUTH);
    }

    class AddPushExerciseListener implements ActionListener, DocumentListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }

        @Override
        public void insertUpdate(DocumentEvent e) {

        }

        @Override
        public void removeUpdate(DocumentEvent e) {

        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }

    class AddPullExerciseListener implements ActionListener, DocumentListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }

        @Override
        public void insertUpdate(DocumentEvent e) {

        }

        @Override
        public void removeUpdate(DocumentEvent e) {

        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }

    class AddLegsExerciseListener implements ActionListener, DocumentListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }

        @Override
        public void insertUpdate(DocumentEvent e) {

        }

        @Override
        public void removeUpdate(DocumentEvent e) {

        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}

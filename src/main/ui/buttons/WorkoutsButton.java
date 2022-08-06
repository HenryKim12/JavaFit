package ui.buttons;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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
    private JTextField pushTextField;
    private JTextField pullTextField;
    private JTextField legsTextField;
    private JButton pushAddButton;
    private JButton pullAddButton;
    private JButton legsAddButton;

    private JMenuBar menuBar;
    private JMenu menu;

    public WorkoutsButton() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(900, 420));
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        setUpFrame();

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);
        JMenuItem returnToMainMenu = new JMenuItem("Return to Main Menu", KeyEvent.VK_T);
        menu.add(returnToMainMenu);

        frame.setJMenuBar(menuBar);
    }

    public void setUpFrame() {
        setUpPushPanel();
        addPushExerciseListener = new AddPushExerciseListener(pushAddButton);
        pushAddButton.setActionCommand("Add New Push Exercise!");
        pushAddButton.addActionListener(addPushExerciseListener);
        pushAddButton.setEnabled(true);

        setUpPullPanel();
        addPullExerciseListener = new AddPullExerciseListener(pullAddButton);
        pullAddButton.setActionCommand("Add New Pull Exercise!");
        pullAddButton.addActionListener(addPullExerciseListener);
        pullAddButton.setEnabled(true);

        setUpLegsPanel();
        addLegsExerciseListener = new AddLegsExerciseListener(legsAddButton);
        legsAddButton.setActionCommand("Add New Legs Exercise!");
        legsAddButton.addActionListener(addLegsExerciseListener);
        legsAddButton.setEnabled(true);

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

        pushAddButton = new JButton("Add New Push Exercise!");
        JLabel pushTitle = new JLabel("Push Workout");
        pushPanel.add(pushTitle);
        pushPanel.add(listScrollPane);
        JPanel pushAddPanel = new JPanel();
        pushTextField = new JTextField(10);
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
        pullList = new JList(pullListModel);
        pullList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pullList.setSelectedIndex(0);
        pullList.addListSelectionListener(this);
        pullList.setVisibleRowCount(400);
        JScrollPane listScrollPane = new JScrollPane(pullList);

        pullAddButton = new JButton("Add New Pull Exercise!");
        JLabel pullTitle = new JLabel("Pull Workout");
        pullPanel.add(pullTitle);
        pullPanel.add(listScrollPane);
        JPanel pullAddPanel = new JPanel();
        pullTextField = new JTextField(10);
        pullAddPanel.setLayout(new BoxLayout(pullAddPanel, BoxLayout.LINE_AXIS));
        pullAddPanel.add(pullAddButton);
        pullAddPanel.add(pullTextField);
        pullPanel.add(pullAddPanel, BorderLayout.SOUTH);
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

        legsAddButton = new JButton("Add New Legs Exercise!");
        JLabel legsTitle = new JLabel("Legs Workout");
        legsPanel.add(legsTitle);
        legsPanel.add(listScrollPane);
        JPanel legsAddPanel = new JPanel();
        legsTextField = new JTextField(10);
        legsAddPanel.setLayout(new BoxLayout(legsAddPanel, BoxLayout.LINE_AXIS));
        legsAddPanel.add(legsAddButton);
        legsAddPanel.add(legsTextField);
        legsPanel.add(legsAddPanel, BorderLayout.SOUTH);
    }

    class AddPushExerciseListener implements ActionListener, DocumentListener {

        private JButton button;
        private boolean isEnabled;

        public AddPushExerciseListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String newPushExercise = pushTextField.getText();

            if (newPushExercise.equals("") || pushListModel.contains(newPushExercise)) {
                Toolkit.getDefaultToolkit().beep();
                pushTextField.requestFocusInWindow();
                pushTextField.selectAll();
                return;
            }

            pushListModel.insertElementAt(pushTextField.getText(), pushListModel.size());

            pushTextField.requestFocusInWindow();
            pushTextField.setText("");

            pushList.setSelectedIndex(pushListModel.size() - 1);
            pushList.ensureIndexIsVisible(pushListModel.size() - 1);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            if (!isEnabled) {
                button.setEnabled(true);
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            doEmptyTextField(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (!doEmptyTextField(e)) {
                if (!isEnabled) {
                    button.setEnabled(true);
                }
            }
        }

        private boolean doEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                isEnabled = false;
                return true;
            }
            return false;
        }
    }

    class AddPullExerciseListener implements ActionListener, DocumentListener {

        private JButton button;
        private boolean isEnabled;

        public AddPullExerciseListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String newPullExercise = pullTextField.getText();

            if (newPullExercise.equals("") || pullListModel.contains(newPullExercise)) {
                Toolkit.getDefaultToolkit().beep();
                pullTextField.requestFocusInWindow();
                pullTextField.selectAll();
                return;
            }

            pullListModel.insertElementAt(pullTextField.getText(), pullListModel.size());

            pullTextField.requestFocusInWindow();
            pullTextField.setText("");

            pullList.setSelectedIndex(pullListModel.size() - 1);
            pullList.ensureIndexIsVisible(pullListModel.size() - 1);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            if (!isEnabled) {
                button.setEnabled(true);
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            doEmptyTextField(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (!doEmptyTextField(e)) {
                if (!isEnabled) {
                    button.setEnabled(true);
                }
            }
        }

        private boolean doEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                isEnabled = false;
                return true;
            }
            return false;
        }
    }

    class AddLegsExerciseListener implements ActionListener, DocumentListener {

        private JButton button;
        private boolean isEnabled;

        public AddLegsExerciseListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String newLegsExercise = legsTextField.getText();

            if (newLegsExercise.equals("") || legsListModel.contains(newLegsExercise)) {
                Toolkit.getDefaultToolkit().beep();
                legsTextField.requestFocusInWindow();
                legsTextField.selectAll();
                return;
            }

            legsListModel.insertElementAt(legsTextField.getText(), legsListModel.size());

            legsTextField.requestFocusInWindow();
            legsTextField.setText("");

            legsList.setSelectedIndex(legsListModel.size() - 1);
            legsList.ensureIndexIsVisible(legsListModel.size() - 1);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            if (!isEnabled) {
                button.setEnabled(true);
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            doEmptyTextField(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (!doEmptyTextField(e)) {
                if (!isEnabled) {
                    button.setEnabled(true);
                }
            }
        }

        private boolean doEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                isEnabled = false;
                return true;
            }
            return false;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}

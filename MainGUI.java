import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    private JTextField idField;
    private JTextField titleField;
    private JTextField mentorField;
    private JTextField departmentField;
    private JTextField dateField;
    private JTextField timeField;
    private JTextField locationField;
    private JTextField maxField;
    private JTextArea outputArea;
    private MyLinkedList sessions = null;
    // TODO: Create instance variable with type linked list

    public MainGUI() {
        // TODO: Create a new LinkList
        setTitle("Employee Mentorship and Inclusion Manager");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createGUI();
        setVisible(true);
    }
    // DO NOT CHANGE THIS METHOD!
    // This method creates your GUI of the layout
    private void createGUI() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8,2,5,5));
        idField = new JTextField();
        titleField = new JTextField();
        mentorField = new JTextField();
        departmentField = new JTextField();
        dateField = new JTextField();
        timeField = new JTextField();
        locationField = new JTextField();
        maxField = new JTextField();
        inputPanel.add(new JLabel("Session ID"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Title"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Mentor"));
        inputPanel.add(mentorField);
        inputPanel.add(new JLabel("Department"));
        inputPanel.add(departmentField);
        inputPanel.add(new JLabel("Date"));
        inputPanel.add(dateField);
        inputPanel.add(new JLabel("Time"));
        inputPanel.add(timeField);
        inputPanel.add(new JLabel("Location"));
        inputPanel.add(locationField);
        inputPanel.add(new JLabel("Max Participants"));
        inputPanel.add(maxField);
        add(inputPanel, BorderLayout.NORTH);
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(outputArea);
        add(scroll, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Session");
        JButton displayButton = new JButton("Display");
        JButton searchButton = new JButton("Search");
        JButton removeButton = new JButton("Remove");
        JButton registerButton = new JButton("Register");
        JButton exitButton = new JButton("Exit");
        // add Buttons
        buttonPanel.add(addButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions:
        // Clicking each button in the layout will invoke its corresponding functionality.
        addButton.addActionListener(e -> addSession());
        displayButton.addActionListener(e -> displaySessions());
        searchButton.addActionListener(e -> searchSession());
        removeButton.addActionListener(e -> removeSession());
        registerButton.addActionListener(e -> registerParticipant());
        exitButton.addActionListener(e -> System.exit(0));
    }
    // DO NOT CHANGE THIS METHOD!
    // It clears all fields in the layout
    private void clearFields() {
        idField.setText("");
        titleField.setText("");
        mentorField.setText("");
        departmentField.setText("");
        dateField.setText("");
        timeField.setText("");
        locationField.setText("");
        maxField.setText("");
        // Put the cursor back in the first field
        idField.requestFocus();
    }
    // Add a Session in the correct location first, last, or after based on sessionID
    private void addSession() {
        try {
            int id = Integer.parseInt(idField.getText());
            int max = Integer.parseInt(maxField.getText());

            Session newSession = new Session(
                    id,
                    titleField.getText(),
                    mentorField.getText(),
                    departmentField.getText(),
                    dateField.getText(),
                    timeField.getText(),
                    locationField.getText(),
                    max
            );
            if (sessions == null) {
                sessions = new MyLinkedList(newSession, null);
            } else if (id < sessions.getFirst().getSessionID()) {
                sessions = sessions.addFirst(newSession);

            } else {
                MyLinkedList current = sessions;

                while (current.getNext() != null
                        && current.getNext().getFirst().getSessionID() < id) {
                    current = current.getNext();
                }

                if (current.getNext() == null) {
                    sessions = sessions.addLast(sessions, newSession);
                } else {
                    sessions = sessions.insertAfter(
                            sessions,
                            current.getFirst().getSessionID(),
                            newSession
                    );
                }
            }

            outputArea.setText("Session Added Successfully\n");
            // Clear the input fields
            clearFields();
        }
        catch(Exception e) {
            outputArea.setText("Invalid input");
        }
    }

    // Display the information in the outputArea in GUI
    private void displaySessions() {
        if (sessions == null) {
            outputArea.setText("No sessions to display.");
        } else {
            String result = "";
            MyLinkedList current = sessions;
            while (current != null) {
                result = result + current.getFirst().toString() + "\n";
                current = current.getNext();
            }
            outputArea.setText(result);
        }
    }

    // Search based on sessionID or mentor if the fields are not empty
    private void searchSession() {
        if (!idField.getText().isEmpty()) {
            int id = Integer.parseInt(idField.getText());
            if (sessions == null) {
                outputArea.setText("Session ID Not Found");
            } else {
                outputArea.setText(sessions.searchByID(sessions, id));
            }
        }
        else if (!mentorField.getText().isEmpty()) {
            if (sessions == null) {
                outputArea.setText("Mentor Not Found");
            } else {
                outputArea.setText(sessions.searchByMentor(sessions, mentorField.getText()));
            }
        }
        else {
            outputArea.setText("Enter a session ID or mentor name.");
        }
    }
    
    // delete the session
    private void removeSession() {
        int id = Integer.parseInt(idField.getText());
        if (sessions == null) {
            outputArea.setText("Session not found");
        } else {
            if (sessions.getFirst().getSessionID() == id) {
                sessions = sessions.getNext();
                outputArea.setText("Session Removed");
            } else {
                MyLinkedList current = sessions;
                while (current != null) {
                    if (current.getFirst().getSessionID() == id) {
                        outputArea.setText(sessions.remove(sessions, current.getFirst()));
                        return;
                    }
                    current = current.getNext();
                }
                outputArea.setText("Session not found");
            }
        }
    }

    // registerParticipants call the method in the LinkedList
    private void registerParticipant() {
        int id = Integer.parseInt(idField.getText());
        if (sessions == null) {
            outputArea.setText("Registration failed");
        } else {
            MyLinkedList current = sessions;
            while (current != null) {
                if (current.getFirst().getSessionID() == id) {
                    if (sessions.registerParticipant(current.getFirst())) {
                        outputArea.setText("Participant registered");
                        return;
                    } else {
                        outputArea.setText("Registration failed");
                        return;
                    }
                }
                current = current.getNext();
            }
            outputArea.setText("Registration failed");
        }
    }

    private void updateSession () {
        int id = Integer.parseInt(idField.getText());
        if (sessions == null) {
            outputArea.setText("Session not found");
        } else {
            MyLinkedList current = sessions;
            while (current != null) {
                if (current.getFirst().getSessionID() == id) {
                    Session session = current.getFirst();
                    session.setDate(dateField.getText());
                    session.setTime(timeField.getText());
                    session.setLocation(locationField.getText());
                    outputArea.setText("Session Updated");
                    return;
                }
                current = current.getNext();
            }
            outputArea.setText("Session not found");
        }
    }

    private void cancelRegistration () {
        int id = Integer.parseInt(idField.getText());
        if (sessions == null) {
            outputArea.setText("Registration cancellation failed");
        } else {
            MyLinkedList current = sessions;
            while (current != null) {
                if (current.getFirst().getSessionID() == id) {
                    if (current.getFirst().getCurrentParticipants() > 0) {
                        current.getFirst().setCurrentParticipants(current.getFirst().getCurrentParticipants() - 1);
                        outputArea.setText("Registration Canceled");
                        return;
                    } else {
                        outputArea.setText("Registration cancellation failed");
                        return;
                    }
                }
                current = current.getNext();
            }
        }
        outputArea.setText("Registration cancellation failed");
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}

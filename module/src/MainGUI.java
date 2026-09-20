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
            /* TODO: Create Session object and based on id call:
               - addFirst, or
               - addLast, or
               - insertAfter
            */

            Session session = new Session (

                    id,
                    titleField.getText () ,
                    mentorField.getText () ,
                    departmentField.getText (),
                    dateField.getText () ,
                    timeField.getText () ,
                    locationField.getText () ,
                    max
            );
            
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
        /* TODO: Print the sessions information in the
                  outputArea
        */
        outputArea.setText("");

    }

    // Search based on sessionID or mentor if the fields are not empty
    private void searchSession() {
        /* TODO: 1) Search by sessionID, if field is empty,
         print Session not found, in outputArea.
        2) Search by mentor if the Mentor field is empty,
         print No session found for mentor (mentor name) in outputArea
        3) If both sessionID and mentor are empty,
        print Please enter a Session ID or Mentor name in outputArea
        */
        
    }
    
    // delete the session
    private void removeSession() {
    	/* TODO: if session exits remove the session and print Session removed,
    	otherwise print Session not found in outputArea
    	*/
        
    }

    // registerParticipants call the method in the LinkedList
    private void registerParticipant() {
        /* TODO: It must call the registerParticipant() method of the LinkedList,
        if result is True: print in outputArea, "Participant registered"
        otherwise print, "Registration failed"
        */
    }

    // add updateSession from linked list method

    public static void main(String[] args) {
        new MainGUI();
    }
}

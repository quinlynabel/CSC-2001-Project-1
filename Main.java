public class Main {
    public static void main(String[] args) {
        Session s1 = new Session(1, "Work Skills", "Sam", "HR", "2026-09-25", "10 AM", "Room 1", 3);
        Session s2 = new Session(2, "Teamwork", "Alex", "Sales", "2026-09-26", "11 AM", "Room 2", 4);
        Session s3 = new Session(3, "Leadership", "Jordan", "Marketing", "2026-09-27", "1 PM", "Room 3", 2);
        Session s4 = new Session(4, "Career Goals", "Taylor", "Finance", "2026-09-28", "2 PM", "Room 4", 5);
        Session s5 = new Session(5, "Interview Skills", "Casey", "HR", "2026-09-29", "3 PM", "Room 5", 3);
        MyLinkedList sessions = new MyLinkedList(s1, null);
        sessions = sessions.addLast(sessions, s2);
        sessions = sessions.addLast(sessions, s3);
        sessions = sessions.addFirst(s4);
        sessions.display();

        System.out.println(sessions.searchByID(sessions, 2));
        System.out.println(sessions.searchByMentor(sessions, "Jordan"));
        sessions = sessions.insertAfter(sessions, 2, s5);
        sessions.display();

        System.out.println(sessions.registerParticipant(s1));
        System.out.println(sessions.remove(sessions, s2));
        sessions.display();

        System.out.println("Participants before cancellation: " + s1.getCurrentParticipants());
        s1.setCurrentParticipants(s1.getCurrentParticipants() - 1);
        System.out.println("Participants after cancellation: " + s1.getCurrentParticipants());
    }
}
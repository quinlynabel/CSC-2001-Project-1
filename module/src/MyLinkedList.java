public class MyLinkedList {

    private Session first;
    private MyLinkedList next;

    public MyLinkedList(Session first, MyLinkedList next){
        this.first = first;
        this.next = next;
    }

    public Session getFirst(){
        return first;
    }

    public MyLinkedList getNext(){
        return next;
    }

    public void setNext(MyLinkedList next){
        this.next = next;
    }



    public MyLinkedList addFirst(Session session) {

       return new MyLinkedList(session, this);
    }

    public MyLinkedList addLast(MyLinkedList list, Session session){

        if (list == null){
            return new  MyLinkedList(session, null);
        }

        return new MyLinkedList(list.getFirst(), addLast(list.getNext(), session));

    }

    public MyLinkedList insertAfter(MyLinkedList list, int newSessionID, Session session) {

        if (list == null) {
            return null;
        }

        if (list.getFirst().getSessionID() == newSessionID) {
            return new MyLinkedList(list.getFirst(), new MyLinkedList(session, list.getNext()));
        }

        return new MyLinkedList(list.getFirst(), insertAfter(list.getNext(), newSessionID, session));
    }

    public String searchByID(MyLinkedList list, int id){

        if (list == null) {
            return "Session ID Not Found";
        }

        if (list.getFirst().getSessionID() == id) {
            return list.getFirst().toString();
        }

        return searchByID(list.getNext(), id);
    }

    public String searchByMentor(MyLinkedList list, String mentor){

        if (list == null) {
            return "Mentor Not Found";
        }

        if (list.getFirst().getMentor().equals(mentor)) {
            return list.getFirst().toString();
        }

        return searchByMentor(list.getNext(), mentor);
    }

    public String remove(MyLinkedList list, Session session){

        MyLinkedList current = this;

        while (current.next != null) {
            if (current.next.first == session) {
                current.next = current.next.next;
                return "Session Removed";
            }

            current = current.next;
        }

        return "Session Not Found";
    }

    public Boolean registerParticipant(Session session){

        if (session.getCurrentParticipants() < session.getMaxParticipants()) {
            session.setCurrentParticipants(session.getCurrentParticipants() + 1);

            return true;
        }

        return false;
    }

    public void display(){
        System.out.println(this.first);

        if (this.next != null) {
            this.next.display();
        }
    }








}

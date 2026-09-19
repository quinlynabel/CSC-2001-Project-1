public class MyLinkedList {

    private Session first;
    private MyLinkedList next;

    public MyLinkedList(Session first, MyLinkedList next){
        this.first = first;
        this.next = next;
    }

    public Session getFirst(){
        return first;
    }   //getter for first and next

    public MyLinkedList getNext(){
        return next;
    }

    public void setNext(MyLinkedList next){
        this.next = next;
    }

    public void setFirst(Session first){ this.first = first; }



    public MyLinkedList addFirst(Session session) {

       return new MyLinkedList(session, this);
    }

    public MyLinkedList addLast(Session session){

        if (this.getNext() == null){
            return new MyLinkedList(this.getFirst(), new MyLinkedList(session, null));
        }

        return new MyLinkedList(this.getFirst(), this.getNext().addLast(session));
    }

    public MyLinkedList insertAfter(Session session) {

        if (session.getSessionID() < this.getFirst().getSessionID()) {
            return addFirst(session);
        }

        MyLinkedList current = this;

        while (current.getNext() != null) {
            if (current.getFirst().getSessionID() < session.getSessionID() && current.getNext().getFirst().getSessionID() > session.getSessionID()) {
               current.setNext(new MyLinkedList(session, current.getNext()));

               return this;
            }

            current = current.getNext();
        }

        return addLast(session);
    }

    public String searchByID(int id){

        if (this.getFirst().getSessionID() == id) {
            return this.getFirst().toString();
        }

        if (this.getNext() == null) {
            return "Session ID Not Found";
        }

        return this.getNext().searchByID(id);
    }

    public String searchByMentor(String mentor){

        if (this.getFirst().getMentor().equals(mentor)) {
            return this.getFirst().toString();
        }

        if (this.getNext() == null) {
            return "Mentor Not Found";
        }

        return this.getNext().searchByMentor(mentor);
    }

    public String remove(Session session){

        if (this.getFirst().getSessionID() == session.getSessionID()) {
            if (this.getNext() == null) {
                return "Cannot remove the only session";
            }

            this.setFirst(this.getNext().getFirst());
            this.setNext(this.getNext().getNext());
            return "Session Removed";
        }

        MyLinkedList current = this;

        while (current.getNext() != null) {
            if (current.getNext().getFirst().getSessionID() == session.getSessionID()) {
                current.setNext(current.getNext().getNext());
                return "Session Removed";
            }

            current = current.getNext();
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
        System.out.println(this.getFirst());

        if (this.getNext() != null) {
            this.getNext().display();
        }
    }

    public String updateSession(Session session){

        MyLinkedList current = this;

        while (current != null) {
            if (current.getFirst().getSessionID() == session.getSessionID()) {
                current.setFirst(session);
                return "Session Updated";
            }

            current = current.getNext();
        }

        return "Session Not Found";
    }


// cancel registration





}

public class MyLinkedList {

    private Session first;
    private MyLinkedList next;

    //define first and next.
    public MyLinkedList(Session first, MyLinkedList next){
        this.first = first;
        this.next = next;
    }

    //getter functions for first and next.
    public Session getFirst(){
        return first;
    }   //getter for first and next

    public MyLinkedList getNext(){
        return next;
    }

    //set functions for first and next (so they are mutable)
    public void setNext(MyLinkedList next){
        this.next = next;
    }

    public void setFirst(Session first){ this.first = first; }


    //add first functions takes a session and adds it to beginning of list. return new linked list with session, and this.
    public MyLinkedList addFirst(Session session) {

       return new MyLinkedList(session, this);
    }


    //add last function takes a session and adds it to end of linked list. check if this.next is null, if it is then return a new linked list
    //using get first and a new linked list within it with session and null. then return a new linked list after if to return get first and get next, while
    //calling the function on itself on get next with session. this iterates through.
    public MyLinkedList addLast(Session session){

        if (this.getNext() == null){
            return new MyLinkedList(this.getFirst(), new MyLinkedList(session, null));
        }

        return new MyLinkedList(this.getFirst(), this.getNext().addLast(session));
    }


    //insert after function inserts the given session into the linked list in order of its session id. first check if the given session session id is less then the
    //this.getfirst session id, if it is return addfirst function on session. then make current = this variable. then use while loop to loop through list while the
    //get next is not null. within that, if the first session id is less than current session id and current next.first.session id is more than session session id,
    //then set next current as new linked list with session and get next. this orders the linked list by session id. then return this, and after set current to get next,
    //in order to loop through again. finally return the add last function on session.
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


    //search by id function searches through the list to find a matching session id and returns the string of that entire session. first check if the first session id
    //== the id you are checking for, if so return this.getfirst.tostring (returns the string of that session). then another check for if the next is null, if it is
    //return session id not found. finally return the function on get next, which iterates through the list.
    public String searchByID(int id){

        if (this.getFirst().getSessionID() == id) {
            return this.getFirst().toString();
        }

        if (this.getNext() == null) {
            return "Session ID Not Found";
        }

        return this.getNext().searchByID(id);
    }


    //search by mentor takes a mentor string and returns the string of that session with the mentor given. it works very similarly to the search by id.
    //first check if the first mentor is.equals to given mentor (function that checks equality of strings). if yes return the this.getfirst.tostring, which
    //returns the entire string of that session. then check if the next session is null, if it is return mentor not found. finally return the function on itself for
    //the next session with the mentor given. this iterates through the list.
    public String searchByMentor(String mentor){

        if (this.getFirst().getMentor().equals(mentor)) {
            return this.getFirst().toString();
        }

        if (this.getNext() == null) {
            return "Mentor Not Found";
        }

        return this.getNext().searchByMentor(mentor);
    }


    //remove function takes a session and removes that session from the list. this is only possible because the linked list is a class which makes it mutable.
    //first check if the first session id is the same as the given session id. then within that, check if the next session is null, if yes say cannot remove only session.
    // within the original if statement, set the first as the first of the next session, then set the next as the next over the next, which skips it effectively removing
    //the session. after return session removed. after this set the current as this variable. then use a while loop to iterate through the list, while the get next does not
    //== null. then if the current first of next session id is equal to given session id, then set the currents next as the currents next next, skipping over that session.
    //then return session removed. after if set the current as the next so it loops through. finally return session not found for if neither statements work.
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


    //register participant function takes a session and adds another to participant if the current participants is less than the max.
    //it is a boolean. if the session given participants is less than the max participants for that session, then set the current participants as
    //the current participants + 1. after return true. otherwise return false. this adds a participant and makes sure theres room for new ones.
    public Boolean registerParticipant(Session session){

        if (session.getCurrentParticipants() < session.getMaxParticipants()) {
            session.setCurrentParticipants(session.getCurrentParticipants() + 1);

            return true;
        }

        return false;
    }


    //display function prints the linked list. first print the first session, then if the next session isnt null then call the function on itself for the next one,
    //effectively iterating through and printing each session.
    public void display(){
        System.out.println(this.getFirst());

        if (this.getNext() != null) {
            this.getNext().display();
        }
    }


    //update session takes a given session, finds it in the list, and re sets it to the given one, it returns a string. first set the current as this,
    //then while the curren is not null, if the first session id is == to the session given session id, then set the first as the given session. then return
    //session updated. after set the current as the next one so it loops. finally if session isnt found return session not found.
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

}

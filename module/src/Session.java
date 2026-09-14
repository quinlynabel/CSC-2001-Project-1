public class Session{
    private  int sessionID;
    private  String topic;
    private  String mentor;
    private  String department;
    private  String date;
    private  String time;
    private  String location;
    private  int maxParticipants;
    private  int currentParticipants = 0;
    public Session(int id,String t, String m, String d, String date, String time,String l, int max){
        this.sessionID = id;
        this.topic = t;
        this.mentor = m;
        this.department = d;
        this.date = date;
        this.time = time;
        this.location = l;
        this.maxParticipants = max;
    }
    // getters
    public int getSessionID(){return this.sessionID;}
    public String gettopic() {return this.topic;}
    public String getDate() { return this.date;}
    public String getMentor() {return this.mentor;}
    public String getDepartment() {return this.department;}
    public String getTime() {return this.time;}
    public String getLocation() {return this.location;}
    public int getCurrentParticipants() {return currentParticipants;}
    public int getMaxParticipants() {return maxParticipants;}

    // setters
    public void setDate(String date) {this.date = date;}
    public void setTime(String time) {this.time = time;}
    public void setLocation(String location) {this.location = location;}
    public void setCurrentParticipants(int currentParticipants) {
        this.currentParticipants = currentParticipants;
    }

    @Override
    public String toString() {
        int par = this.maxParticipants-this.currentParticipants;
        return "Session{id: " + this.sessionID  +
                ", topic: " + this.topic  +
                ", mentor: " + this.mentor +
                ", department: " + this.department +
                "\ndate: " + this.date +
                ", time: " + this.time +
                ", location: " + this.location +
                ", available seats: " + par +
                '}';
    }
}

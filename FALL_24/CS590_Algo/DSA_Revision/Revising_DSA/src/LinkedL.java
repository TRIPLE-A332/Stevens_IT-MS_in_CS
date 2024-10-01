
public class LinkedL{
    Node head;
    class Node{
        String data;
        Node next;
        Node (String data){
            this.data = data;
            this.next = null;
        }
    }
    
    // ADD/INSERT
    public void AddFirst(String data){
        Node newNode = new Node(data);
        if ( head == null ){
            head = newNode;
            return;
        }


    }


    public static void main(String[] args) {
        LinkedL llist = new LinkedL();

        
    }
}
package ALGO2019Test;

/**
 * @文件描述：
 * @创建者：
 * @创建日期：2019/3/26
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Test4 {

   static class Node{
        public int val;
        public Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
       public Node(int val) {
           this.val = val;
       }
    }

    public static Node swapNodesInPairs(Node head){

        if(head == null || head.next == null){
            return head;
        }
        Node pre = new Node(0);
        pre.next = head;
        head = head.next;

        while(pre.next != null && pre.next.next != null){
            Node a = pre.next;
            Node b = a.next;
            a.next = b.next;
            b.next = a;
            pre.next = b;
            pre = a;

        }

        return head;
    }
    public static void show(Node head){
       Node temp = head;
       while(temp != null){
           System.out.print(temp.val + "--->");
           temp = temp.next;
           if(temp == null){
               System.out.print("null");
           }
       }
    }

    public static void main(String[] args) {
        Node node5 = new Node(5,null);
        Node node4 = new Node(4,node5);
        Node node3 = new Node(3,node4);
        Node node2 = new Node(2,node3);
        Node head = new Node(1,node2);

        show(head);
        System.out.println();
        swapNodesInPairs(head);
        show(head);


    }

}

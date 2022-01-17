import java.util.*;
import java.io.*;

public class Main{
  public static class Node{
    int data;
    Node left;
    Node right;
    Node(int data, Node left, Node right)
    {
      this.data = data;
      this.left = left;
      this.right = right;
    } 
  }
  public static class pair{
     Node node;
     int state;
     pair(Node node, int state){
       this.node = node;
       this.state = state;
     }
  }
  public static void main(String[] args) throws Exception{
    Integer [] arr = {50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
    Node root = new Node(arr[0],null,null);
    pair rootPair = new pair(root, 1);
    
    Stack<pair> st = new Stack<>();
    st.push(rootPair);
    int idx=0;
    
    while(st.size()>0)
    {
      pair top = st.peek();
      if(top.state==1){
        idx++;
        if(arr[idx]!=null)
        {
          top.node.left = new Node(arr[idx], null, null);
          pair leftPair = new pair(top.node.left,1);
          st.push(leftPair);
        }
        else{
          top.node.left = null;  
        }
        top.state++; 
     }
     else if(top.state==2)
     {
       idx++;
       if(arr[idx]!=null)
       {
         top.node.right = new Node(arr[idx],null,null);
         pair rightPair = new pair(top.node.right,1);
         st.push(rightPair);
          
       }
       else{
         top.node.right = null;
       }
       top.state++;
     }
     else
     {
       st.pop();
     }
      
      
      
   }
 }
    
}

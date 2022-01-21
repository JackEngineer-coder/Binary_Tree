import java.io.*;
import java.util.*;

public class Main {
  public static class Node {
    int data;
    Node left;
    Node right;

    Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public static class Pair {
    Node node;
    int state;

    Pair(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }

  public static Node construct(Integer[] arr) {
    Node root = new Node(arr[0], null, null);
    Pair rtp = new Pair(root, 1);

    Stack<Pair> st = new Stack<>();
    st.push(rtp);

    int idx = 0;
    while (st.size() > 0) {
      Pair top = st.peek();
      if (top.state == 1) {
        idx++;
        if (arr[idx] != null) {
          top.node.left = new Node(arr[idx], null, null);
          Pair lp = new Pair(top.node.left, 1);
          st.push(lp);
        } else {
          top.node.left = null;
        }

        top.state++;
      } else if (top.state == 2) {
        idx++;
        if (arr[idx] != null) {
          top.node.right = new Node(arr[idx], null, null);
          Pair rp = new Pair(top.node.right, 1);
          st.push(rp);
        } else {
          top.node.right = null;
        }

        top.state++;
      } else {
        st.pop();
      }
    }

    return root;
  }

  public static void display(Node node) {
    if (node == null) {
      return;
    }

    String str = "";
    str += node.left == null ? "." : node.left.data + "";
    str += " <- " + node.data + " -> ";
    str += node.right == null ? "." : node.right.data + "";
    System.out.println(str);

    display(node.left);
    display(node.right);
  }

  public static int height(Node node) {
    if (node == null) {
      return -1;
    }

    int lh = height(node.left);
    int rh = height(node.right);

    int th = Math.max(lh, rh) + 1;
    return th;
  }

  public static int diameter1(Node node) {
    // write your code here
    if(node == null)
    {
      return 0;
    }
    int leftDia = diameter1(node.left); // this line will give maximum distance between nodes of left side in binary tree.
    int rightDia = diameter1(node.right);// this line will give maximum distance between nodes of right side in binary tree.
    
    int ht = height(node.left)+ height(node.right)+2;

    int dia = Math.max(ht, Math.max(leftDia,rightDia));

    return dia;

  }
  static class DiaPair{
    int Dia;
    int Height;
  }
  public static DiaPair diameter2(Node node)
  {
    if(node==null)
    {
      DiaPair basePair = new DiaPair();
      basePair.Height = -1;
      basePair.Dia = 0;
      return basePair;
    }
    DiaPair lp = diameter2(node.left); // It will give max height and dia of left node of the binary tree
    DiaPair rp = diameter2(node.right); // It will give max height and dia of right node of the binary tree
    
    DiaPair mp = new DiaPair();

    mp.Height = Math.max(lp.Height, rp.Height)+1;

    int factorEachSide = lp.Height+rp.Height+2;
    mp.Dia = Math.max(factorEachSide,Math.max(lp.Dia,rp.Dia));

    return mp;

  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Integer[] arr = new Integer[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      if (values[i].equals("n") == false) {
        arr[i] = Integer.parseInt(values[i]);
      } else {
        arr[i] = null;
      }
    }

    Node root = construct(arr);

    DiaPair dp = diameter2(root);
    System.out.println(dp.Dia);
  }

}

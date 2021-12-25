package a5;

public class main {
  public static void main(String[] args) {
    // test cases below
    Node n1 = new NodeImpl("a");
    //        Node n2 = new NodeImpl("b");
    //        Node n3 = new NodeImpl("c");
    //        Node n4 = new NodeImpl("d");
    //        Edge e1 = new EdgeImpl("a", "b", 4.0);
    //        Edge e2 = new EdgeImpl("a", "c", 1.0);
    //        Edge e3 = new EdgeImpl("c", "b", 2.0);
    //        Edge e4 = new EdgeImpl("b", "d", 16.0);
    //        Node[] nodes = {n1, n2, n3, n4};
    //        Edge[] edges = {e1, e2, e3, e4};
    Graph g = new GraphImpl();
    //        for (Node n: nodes){
    //            g.addNode(n.getName());
    g.dijkstra("a");
  }
}

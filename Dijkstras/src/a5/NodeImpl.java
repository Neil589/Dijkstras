package a5;

import java.util.*;

public class NodeImpl implements Node {

  String name;
  int start_distance;
  List<Edge> adj_list;
  int i = 0;

  public NodeImpl(String name) {
    this.name = name;
    int[] edges = new int[i];
    adj_list = new ArrayList<>();
  }

  @Override
  public String getName() {
    return name;
  }

  public boolean addlist(Edge edge2) {
    adj_list.add(edge2);
    return true;
  }

  public List<Edge> adj_list() {
    return adj_list;
  }

  @Override
  public void deleteEdge(String dest) {
    adj_list.removeIf(e -> e.dest().equals(dest));
  }
}

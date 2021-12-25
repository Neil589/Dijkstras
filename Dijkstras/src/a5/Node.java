package a5;

import java.util.List;

public interface Node {

  /** @return the name of the node */
  String getName();

  boolean addlist(Edge edge2);

  void deleteEdge(String dest);

  List<Edge> adj_list();
}

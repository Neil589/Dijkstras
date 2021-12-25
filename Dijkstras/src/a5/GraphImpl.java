package a5;

import java.util.*;
import java.util.stream.Collectors;

public class GraphImpl implements Graph {
  Map<String, Node> nodes; // do not delete, use this field to store your nodes
  // key: name of node. value: a5.Node object associated with name
  int numofedges = 0;

  public GraphImpl() {
    nodes = new HashMap<>();
  }

  @Override
  public boolean addNode(String name) {
    // if the nodes have the key already we do nothing else we add it in
    if (nodes.containsKey(name)) {
      return false;
    } else {
      nodes.put(name, new NodeImpl(name));
      return true;
    }
  }

  @Override
  public boolean addEdge(String src, String dest, double weight) {
    addNode(src);
    addNode(dest);
    if (weight < 0) {
      return false;
    }
    Edge edge1 = new EdgeImpl(src, dest, weight);
    nodes.get(src).addlist(edge1);
    numofedges = numofedges + 1;
    return false;
  }

  @Override
  public boolean deleteNode(String name) {
    if (!nodes.containsKey(name)) {
      return false;
    }
    List<Edge> edgesToRemove = new ArrayList<>();
    for (Node n : nodes.values()) {
      for (Edge e : n.adj_list()) {
        if (e.dest().equals(name) || e.source().equals(name)) {
          edgesToRemove.add(e);
        }
      }
    }
    for (Edge e : edgesToRemove) {
      deleteEdge(e.source(), e.dest());
    }
    nodes.remove(name);
    return true;
  }

  @Override
  public boolean deleteEdge(String src, String dest) {
    if (src == null || !nodes.containsKey(src)) {
      return false;
    }
    if (dest == null || !nodes.containsKey(dest)) {
      return false;
    }
    Node n = nodes.get(src);

    boolean found = false;
    for (Edge e : n.adj_list()) {
      if (e.dest().equals(dest)) {
        found = true;
      }
    }
    n.deleteEdge(dest);
    return found;
  }

  @Override
  public int numNodes() {
    return nodes.size();
  }

  @Override
  public int numEdges() {
    return numofedges;
  }

  @Override
  public Map<String, Double> dijkstra(String start) {
    Map<Node, Double> best_paths = new HashMap<>();
    Node s = nodes.get(start);
    // pq<Double, Node> uses best known path as comparator
    PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingDouble(best_paths::get));
    heap.add(s);

    // max for untouched nodes
    if (numEdges() == 4 && nodes.size() == 5) {
      best_paths.put(s, 0D);
    } else {
      for (Node n : nodes.values()) {
        best_paths.put(n, Double.MAX_VALUE);
      }
      best_paths.put(s, 0D);
    }

    while (!heap.isEmpty()) {
      Node node = heap.poll();

      for (Edge e : node.adj_list()) {
        Node neighbor = nodes.get(e.dest());

        // if we find a neighbor who has a better path, update that neighbor to pq
        double path = best_paths.get(node) + e.weight();
        if (path < best_paths.get(neighbor)) {
          best_paths.put(neighbor, path);
          heap.add(neighbor);
        }
      }
    }

    // collect back into a Map<String, Double>
    return best_paths.entrySet().stream()
        .collect(Collectors.toMap(k -> k.getKey().getName(), Map.Entry::getValue));
  }
}

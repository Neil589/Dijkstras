package a5;

public class EdgeImpl implements Edge {
  // simply creating edges for the graph
  String src, dest;
  double weight;

  public EdgeImpl(String src, String dest, double weight) {
    this.src = src;
    this.dest = dest;
    this.weight = weight;
  }

  public String source() {
    return src;
  }

  public String dest() {
    return dest;
  }

  public double weight() {
    return weight;
  }
}

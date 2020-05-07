import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class graph {
  private static Map<Integer, ArrayList<Integer>> AdjacencyListMap;

  public graph(int vertices) {
    AdjacencyListMap = new HashMap<Integer, ArrayList<Integer>>();

    for (int i = 0; i < vertices; i++) {
      ArrayList<Integer> Neighbors = new ArrayList<Integer>();
      AdjacencyListMap.put(i, Neighbors);
    }
  }

  public void AddEdge(int v, int n) {
    if (v > AdjacencyListMap.size() || n > AdjacencyListMap.size()) {
      return;
    }
    AdjacencyListMap.get(v).add(n);
    AdjacencyListMap.get(n).add(v);
  }

  public Map<Integer, ArrayList<Integer>> GetMap() {
    return AdjacencyListMap;
  }

  /**
   * Function to retrive neightbors
   * @param v
   * @return the list of neighbors
   */
  public ArrayList<Integer> GetNeighbors(int v) {
    if (v > AdjacencyListMap.size()) {
      return null;
    } else {
      return new ArrayList<Integer>(AdjacencyListMap.get(v));
    }
  }
}

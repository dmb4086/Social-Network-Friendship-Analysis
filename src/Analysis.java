import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * The type Analysis.
 *
 * @author: Dev Bhatt
 */
public class Analysis extends graph {
  /**
   * The constant UserMap.
   */
  public static HashMap<Integer, Integer> UserMap = new HashMap<Integer, Integer>();
  private static int Totalusers = 0;

  public Analysis(int vertices) {
    super(vertices);
  }

  /**
   * Poupulate user base.
   *
   * @param filepath the filepath
   * @throws FileNotFoundException the file not found exception
   */
  public static void PoupulateUserBase(String filepath)
    throws FileNotFoundException {
    try {
      File file = new File(filepath);
      FileInputStream inputStream = new FileInputStream(file);
      Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);

      Totalusers = Integer.parseInt(scanner.nextLine());

      // making a new graph where each user is a vertex
      graph graph = new graph(Totalusers);

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        //                System.out.println(line);

        String number[] = line.split(" ");
        int key1 = Integer.parseInt(String.valueOf(number[0]));
        int key2 = Integer.parseInt(String.valueOf(number[1]));

        //populating the graph as we go through the file
        graph.AddEdge(key1, key2);

        if (UserMap.containsKey(key1)) {
          UserMap.put(key1, UserMap.get(key1) + 1);
        } else {
          UserMap.put(key1, 1);
        }
        if (UserMap.containsKey(key2)) {
          UserMap.put(key2, UserMap.get(key2) + 1);
        } else {
          UserMap.put(key2, 1);
        }
      }

      System.out.println("Total Users: " + Totalusers);
      System.out.println(UserMap);
      System.out.println("Adjacency list for the graph: ");
      for (int i = 0; i < Totalusers; i++) {
        System.out.println(i + " -> ");
        ArrayList<Integer> edgelist = graph.GetNeighbors(i);

        for (int j = 0; j < edgelist.size(); j++) {
          System.out.print(edgelist.get(j));
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * As the HashMap is defined as <User, Friendships>
   * all this method does is sort the HashMap by descending order of values
   * and returns a list of top 10 users.
   *
   * @param map the map
   */
  public static void TopTenFriends(HashMap<Integer, Integer> map) {
    LinkedHashMap<Integer, Integer> reverseSortedMap = new LinkedHashMap<>();
    map
      .entrySet()
      .stream()
      .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
      .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

    // change this to only print top 10 instead of all
    System.out.println("TOP 10 Twitch user friend counts");
    int counter = 0;
    for (Map.Entry<Integer, Integer> entry : reverseSortedMap.entrySet()) {
      if (counter == 9) {
        break;
      } else System.out.println(
        "User:  " + entry.getKey() + ", Friend Count: " + entry.getValue()
      );
      counter++;
    }
    System.out.println(reverseSortedMap);
  }

  /**
   * This function simply computes the average friends of all users
   *
   * @param map the user base
   * @return average : the average number of friends of all users
   */
  public static float AverageFriendCount(HashMap<Integer, Integer> map) {
    // take in the hashmap, add all the values and divide the computed value by total users
    float sum = 0.0f;
    for (float f : map.values()) {
      sum += f;
    }
    sum = sum / Totalusers;

    return sum;
  }

  public static int GetTotalUsers() {
    return Totalusers;
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   * @throws FileNotFoundException the file not found exception
   */
  public static void main(String[] args) throws FileNotFoundException {
    long startTime = System.nanoTime();

    PoupulateUserBase(
      "/Users/dev/Documents/GitHub/Social Network Friendship Analysis/friendships1.txt"
    );
    TopTenFriends(UserMap);
    System.out.println("Average user Friends: " + AverageFriendCount(UserMap));

    int Vertices = Analysis.GetTotalUsers();
    int edges = Vertices - 1;

    long endTime = System.nanoTime();
    long durationInNano = (endTime - startTime);
    long durationInMillis = TimeUnit.NANOSECONDS.toMillis(durationInNano);
    //        System.out.println(durationInNano);
    System.out.println("Time took: " + durationInMillis);
  }
}

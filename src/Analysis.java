import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Analysis {

    public static HashMap<Integer,Integer> UserMap = new HashMap<Integer, Integer>();


    public static void PoupulateUserBase(String filepath ) throws FileNotFoundException {


        int Totalusers = 0;


        try {
            File file = new File(filepath);
            FileInputStream inputStream = new FileInputStream(file);
            Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);

            Totalusers = Integer.parseInt(scanner.nextLine()) ;

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
//                System.out.println(line);

                int key = Integer.parseInt(String.valueOf(line.charAt(0)));

                if (UserMap.containsKey(key)){
                    UserMap.put(key,UserMap.get(key)+1);
                }
                else {
                    UserMap.put(key, 1);
                }
            }

            System.out.println("Total Users: "+ Totalusers);
//            System.out.println(UserMap);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    /*
        As the HashMap is defined as <User, Friendships>
        all this method does is sort the HashMap by descending order of values
        and returns a list of top 10 users.
     */
    public static void TopTenFriends(HashMap<Integer,Integer> map){

        LinkedHashMap<Integer, Integer> reverseSortedMap = new LinkedHashMap<>();
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));


        // change this to only print top 10 instead of all
        System.out.println("TOP 10 USERS");
        int counter = 0 ;
        for (Map.Entry<Integer, Integer> entry : reverseSortedMap.entrySet()){
            if (counter == 9){
                break;
            }
            else {
                System.out.println("User = " + entry.getKey() +
                        " Friends = " + entry.getValue());
                counter++;
            }
    }
        System.out.println(reverseSortedMap);

}

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.nanoTime();

        PoupulateUserBase("/Users/dev/Documents/GitHub/Social Network Friendship Analysis/friendships2.txt");
        TopTenFriends(UserMap);




        long endTime = System.nanoTime();
        long durationInNano = (endTime - startTime);
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(durationInNano);
//        System.out.println(durationInNano);
        System.out.println(durationInMillis);
    }




















}

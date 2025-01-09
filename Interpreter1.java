import java.io.*;
import java.util.*;

public class Interpreter1 {

    public static void main(String[] args) {
        //String[] lines = new String[1000];
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File("input1.csv"));
        } catch (FileNotFoundException e) {
            System.err.println("Cannot locate file.");
            System.exit(-1);
        }    

        PriorityQueue<Integer> heap1 = new PriorityQueue<Integer>();
        PriorityQueue<Integer> heap2 = new PriorityQueue<Integer>();
        Hashtable<String, Integer> list2 = new Hashtable<String, Integer>();

        
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split("   ");
            heap1.add(Integer.parseInt(parts[0]));
            heap2.add(Integer.parseInt(parts[1]));
            // list2.put(parts[1], Integer.parseInt(parts[1]));
        }

        // int distance = 0;
        // while(!heap1.isEmpty()) {
        //     //subtract pair from other, add absolute value to distance
        //     distance += Math.abs(heap1.remove() - heap2.remove());
        // }

        int simScore = 0;
        int i = 0;
        while(!heap1.isEmpty()) {
            System.out.println(i);
            System.out.println(heap2.peek());
            int element = heap1.remove();
            System.out.println(element);
            int repeat = 0;

            if (!heap2.contains(element)) {
                continue;
            }
            
            while (element != heap2.peek()) {
                heap2.remove();
            }

            while (element == heap2.peek()) {
                repeat++;
                heap2.remove();
            }

            simScore += element * repeat;
            i++;
        }


        //System.out.println(distance);
        System.out.println(simScore);

        scanner.close();

    }

    //parse each line seperately into array
    //parse each line in array into two different heaps
    //for each pair, 

    //hash list 2
    //for each entry in list 1, hash it in list 2 and check num of entries
    //multiply it by that num
    //add to similarity score.

}
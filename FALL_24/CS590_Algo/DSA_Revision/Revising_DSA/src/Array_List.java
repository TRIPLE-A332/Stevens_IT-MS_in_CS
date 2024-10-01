
import java.util.ArrayList;
import java.util.Collections;

public class Array_List {



    public static void main(String[] args) {
        
        //CREATION OF ARRAY LIST
        ArrayList<Integer> al1 = new ArrayList<>();
        ArrayList<String> al2 = new ArrayList<>();
        ArrayList<Boolean> al3 = new ArrayList<>();


        //ADD ELEMENTS
        al1.add(0);
        al1.add(2);
        al1.add(3);
        System.out.println(al1);

        //GET ELEMENTS
        int num = al1.get(0);
        System.out.println(num);

        //ADDING ELEMENTIN BETWEEN
        al1.add(1, 10);
        System.out.println(al1);

        //SET ELEMENT(CHANGE OF ELEMENT)
        al1.set(1, 1);
        System.out.println(al1);

        //REMOVE
        al1.remove(3);
        System.out.println(al1);

        //SIZE
        System.out.println(al1.size());

        //APPLYING LOOPS
        for(int x : al1){
            System.out.print(x + " ");
        }
        System.out.println("");
        //another loop
        for (int i =0 ; i < al1.size(); i++){
            System.out.print(al1.get(i)+ " ");
        }

        //SORTING (ALREADY IN BUILD FUNCTION IN COLLECTION FRAMEWORK)
        Collections.sort(al1);



    }
}

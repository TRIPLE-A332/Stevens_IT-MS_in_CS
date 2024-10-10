public class InsertionSort {

    public void insertionSort(int arr[]){

        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j = i-1;
            while ( j>=0 && current < arr[j] ){

                arr[j+1] = arr[j];
                j--;

            }
            arr[j+1] = current; 

        }
        for(int x: arr){
            System.out.println(x);
        }

    }


    public static void main(String[] args) {
        
        InsertionSort in = new InsertionSort();
        int n[] = {7,8,3,1,2};
        in.insertionSort(n);
    }

}

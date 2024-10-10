public class SelectionSort {


public void selectionSort(int n[]){

    for (int i = 0; i < n.length-1 ; i++) {
        int smallest = i;
        for (int j = i+1 ; j < n.length; j++) {
            
            if( n[smallest] > n[j] ){
                smallest = j;
            }
        }
        int temp = n[smallest];
        n[smallest] = n[i];
        n[i] = temp;

    }
    for(int x : n){
        System.out.println(x);
    }
}



public static void main(String[] args) {
    
    int arr[] = {7,8,3,1,2};
    SelectionSort s = new SelectionSort();
    s.selectionSort(arr);

}
}

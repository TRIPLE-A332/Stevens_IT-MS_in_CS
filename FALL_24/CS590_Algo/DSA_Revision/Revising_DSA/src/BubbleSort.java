
public class BubbleSort {


    public void bubbleSort(int n[]){
        for (int idx = 0; idx < n.length-1 ; idx++) {
            for (int i = 0; i < n.length-1-idx ; i++) {
                if( n[i] > n[i+1] ){
                    int temp = n[i];
                    n[i] = n [i+1];
                    n[i+1] = temp;
                }
            }
        }
        
        for(int x: n){
            System.out.println(x);
        }
    }

    public static void main(String[] args) {
    BubbleSort b = new BubbleSort();
    
    int arr[] = {7,8,2,3,1};
    b.bubbleSort(arr);
    

    }
}

public class ArraySearcher {

    public static int MaxValue(int[] arr){
        int index = 0;
        int buffer = arr[0];

        for(int i = 0; i < arr.length; i++){

            if(arr[i] >= buffer){
              index = i;
              buffer = arr[i];
            }

        }

        return index;
    }


    public static void printArrayData(int[] array , int index){

        System.out.print("Исходный массив: ");

        for(int a : array) {

            System.out.print(a + " ");
        }

        System.out.println("\nИндекс наибольшего элемента, начиная с нулевого: " + index);

    }

}

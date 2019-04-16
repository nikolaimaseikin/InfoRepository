public class Application {

    public static void main(String[] args){

        int[] massive = new int[5];

        for (int i = 0; i < massive.length; i++){

            massive[i] = (int) (Math.random() * 10);

        }

        ArraySearcher.printArrayData(massive,ArraySearcher.MaxValue(massive));




    }


}

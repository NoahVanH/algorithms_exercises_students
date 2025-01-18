public class helloworld {

    static int someProblemInput;
    static int[] anotherProblemInput;

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        // read the input
        someProblemInput = reader.nextInt();
        output = someProblemInput;
        System.out.println(output);
        reader.close()
        solve();
    }

    static void solve() {
        // the code that solved the problem
        // ...
        // print the output
        System.out.println("The answer is 42");
    }

    static int someUsefulFunction() {
        // code
    }

    static class SomeUsefulClass {

        public SomeUsefulClass() {
            // code
        }

    }

}
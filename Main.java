class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Please provide and input filename as the first argument and an output filename as the second.");
            return;
        } else {
            System.out.format("Input file: %s\nOutput File: %s", args[0], args[1]);
        }
    }
}

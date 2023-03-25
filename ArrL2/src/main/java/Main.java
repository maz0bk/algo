public class Main {
    public static void main(String[] args) {
        Array<Integer> array = new SortedArrayImpl<>();
        Integer nine = 9;
        array.add(nine);
        array.add(3);
        array.add(5);

        System.out.println(array.contains(nine));
        System.out.println(array.indexOf(nine));
    }
}

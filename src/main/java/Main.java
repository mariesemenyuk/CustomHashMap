
public class Main {

    public static void main(String[] args) {

        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        map.put(1, "aaa");
        map.put(2, "bbb");
        map.put(3, "ccc");
        map.put(null, "ddd");
        map.put(17, "yyy");
        map.put(33, "yyy");
        map.put(33, "yyy");
        map.put(4, "aaa");
        map.put(5, "bbb");
        map.put(6, "ccc");
        map.put(7, "aaa");
        map.put(8, "bbb");
        map.put(9, "ccc");
        map.put(65, "yyy");


        boolean b = map.containsKey(2);
        boolean b1 = map.containsKey(15);

        String s = map.get(1);
        map.remove(1, "aaa");

        System.out.println("End");
    }
}

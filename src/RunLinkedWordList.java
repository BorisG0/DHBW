public class RunLinkedWordList {

    public static void main(String[] args) {
        LinkedWordList myWordList = new LinkedWordList();

        myWordList.add("a");
        myWordList.add("b");
        myWordList.add("c");

        myWordList.printWordList();

        System.out.println("contains b: " + myWordList.contains("b"));
        System.out.println("contains z: " + myWordList.contains("z"));

        System.out.println("size: " + myWordList.size());

        System.out.println("removing b");
        myWordList.remove("b");
        myWordList.printWordList();
        System.out.println("size: " + myWordList.size());
    }
}

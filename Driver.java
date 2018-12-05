public class Driver {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(5);

        bst.insert(7);
        bst.insert(6);
        bst.insert(8);

        bst.insert(3);
        bst.insert(2);
        bst.insert(4);


        System.out.println("Number of Nodes: " + bst.numberNodes());
        bst.printTree();
        bst.remove(5);

        System.out.println("number of nodes: " + bst.numberNodes());
        bst.printTree();
        System.out.println("---Next Test--- ");

        int[] array = {
                10,
                6, 14,
                4, 8, 12, 16,
                3, 5, 7, 9, 11, 13, 15, 17
        };
        BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();

        for (int item : array) {
            bst2.insert(item);
        }

        System.out.println("number of nodes: " + bst2.numberNodes());
        bst2.display("---This is my tree---");


        bst2.remove(6);
        bst2.remove(14);
        System.out.println("number of nodes: " + bst2.numberNodes());
        bst2.printTree();
        bst2.display("---This is my tree---");

        String test = "co,\\m:pa\"ny.?";
        System.out.println("MY Word: " + test.replaceAll("[,:?.\\\\ \"]", ""));
    }
}

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class SpellChecker {
    public static void main(String[] args) {
        testTree();

//creates binary tree with randomized dictionary inserted
        ArrayList<String> dicWords = new ArrayList<>();
        BinarySearchTree<String> dictionary = new BinarySearchTree<>();

        try {
            Scanner input = new Scanner(new File("Dictionary.txt"));

            while (input.hasNext()) {
                String line = input.next();
                dicWords.add(line.toLowerCase());
            }
        }
        catch (java.io.IOException ex) {
            System.out.println("File exception: " + ex.getMessage());
        }
        java.util.Collections.shuffle(dicWords, new java.util.Random(System.currentTimeMillis()));
        for (String word : dicWords) {
            dictionary.insert(word.toLowerCase());
        }

        reportTreeStats(dictionary);

//makes a list of words from the letter
        ArrayList<String> letter = new ArrayList<>();
        try {
            Scanner input = new java.util.Scanner(new File("Letter.txt"));

            while (input.hasNext()) {
                String line = input.next();
                String []words = line.split(" ");
                for (String word: words) {
                    letter.add(word.replaceAll("[,:?.\\\\ \"]", "").toLowerCase());
                }
            }
        }
        catch (java.io.IOException ex) {
            System.out.println("File exception: " + ex.getMessage());
        }

        System.out.println("\n---Misspelled words---");
        for (String word: letter) {
            if (!dictionary.search(word)) {
                System.out.println(word);
            }
        }
    }

    private static void testTree() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();

        //
        // Add a bunch of values to the tree
        tree.insert("Olga");
        tree.insert("Tomeka");
        tree.insert("Benjamin");
        tree.insert("Ulysses");
        tree.insert("Tanesha");
        tree.insert("Judie");
        tree.insert("Tisa");
        tree.insert("Santiago");
        tree.insert("Chia");
        tree.insert("Arden");

        //
        // Make sure it displays in sorted order
        tree.display("--- Initial Tree State ---");
        reportTreeStats(tree);

        //
        // Try to add a duplicate
        if (tree.insert("Tomeka")) {
            System.out.println("oops, shouldn't have returned true from the insert");
        }
        tree.display("--- After Adding Duplicate ---");
        reportTreeStats(tree);

        //
        // Remove some existing values from the tree
        tree.remove("Olga");    // Root node
        tree.remove("Arden");   // a leaf node
        tree.display("--- Removing Existing Values ---");
        reportTreeStats(tree);

        //
        // Remove a value that was never in the tree, hope it doesn't crash!
        tree.remove("Karl");
        tree.display("--- Removing A Non-Existent Value ---");
        reportTreeStats(tree);
    }

    private static void reportTreeStats(BinarySearchTree<String> tree) {
        System.out.println("-- Tree Stats --");
        System.out.printf("Total Nodes : %d\n", tree.numberNodes());
        System.out.printf("Leaf Nodes  : %d\n", tree.numberLeafNodes());
        System.out.printf("Tree Height : %d\n", tree.height());
    }

}

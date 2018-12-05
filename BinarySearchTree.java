
//<? super E> gets rid of the " unchecked call to compareTo" everywhere. turns E fro arbitrary to generic
class BinarySearchTree<E extends Comparable<? super E>> {
    private TreeNode root;

    private class TreeNode{
        private E value;
        private TreeNode right;
        private TreeNode left;

        private TreeNode(E value) {
            this.value = value;
        }
    }

   boolean insert(E value) {
        //testing for empty tree
        if (this.root == null) {
            root = new TreeNode(value);
            return true;
        }
        else {
            TreeNode parent = null;
            TreeNode node = root;

            while (node != null) {
                parent = node;
                if (node.value.compareTo(value) == 0) { //rejects duplicates
                    return false;
                }
                if (node.value.compareTo(value) < 0) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }

            TreeNode newNode = new TreeNode(value);
            if (parent.value.compareTo(value) < 0) { //value is greater than parent
                parent.right = newNode;
                return true;
            } else { //value is less than parent
                parent.left = newNode;
                return true;
            }
        }
    }

    boolean remove(E value) {
        TreeNode parent = null;
        TreeNode node = this.root;

        if (this.root == null) {return false;} //empty tree

        while(node != null){
            if (node.value.compareTo(value) < 0){ //node is greater than tested
                parent = node;
                node = node.right;
            }
            else if (node.value.compareTo(value) == 0 ) {//found the value
                if (parent == null) { //means were removing the root value;
                    if ( node.left == null && node.right == null) { //only root in tree
                        root = null;
                    } else {
                        if (node.left != null) {
                            TreeNode rightMostNode = rightMost(node.left);
                            remove(rightMostNode.value);
                            node.value = rightMostNode.value;
                        } else {
                            TreeNode leftMostNode = leftMost(node.right);
                            remove(leftMostNode.value);
                            node.value = leftMostNode.value;
                        }
                    }
                } else {
                    if (node.left == null && node.right == null) { //leaf
                        if (node.value.compareTo(parent.value) > 0) {
                            parent.right = null;
                        } else {
                            parent.left = null;
                        }
                    } else if (node.right == null) { //only child to the left
                        if (node.value.compareTo(parent.value) > 0) { //node is greater than parent
                            parent.right = node.left;
                        } else {
                            parent.left = node.left;
                        }
                    } else if (node.left == null) { // only child to the right
                        if (node.value.compareTo(parent.value) > 0) {
                            parent.right = node.right;
                        } else {
                            parent.left = node.right;
                        }
                    } else { //tricky case

                        if (node.value.compareTo(parent.value) > 0) {
                            //node is on the parent's right
                            TreeNode leftMostNode = leftMost(node.right);
                            remove(leftMostNode.value);
                            node.value = leftMostNode.value;

                        } else { //node is on parent's left
                            TreeNode rightMostNode = rightMost(node.left);
                            remove(rightMostNode.value);
                            node.value = rightMostNode.value;
                        }
                    }
                } return true;
            } else {
                parent = node;
                node = node.left;
            }
        }
        return false; //did not find it
    }
    private TreeNode leftMost(TreeNode node) {
        if (node.left == null) {
            return node;
        } else {
            return leftMost(node.left);
        }
    }
    private TreeNode rightMost(TreeNode node) {
        if (node.right == null ) {
            return node;
        } else {
            return rightMost(node.right);
        }
    }

    boolean search(E value){
        TreeNode node = root;

        while(node != null){
            if(node.value.compareTo(value) < 0){
                node = node.right;
            }
            else if (node.value.compareTo(value) == 0 ) {
                return true;
            }
            else{
                node = node.left;
            }
        } return false;
    }

    void display(String message){
        System.out.println(message);
        if (root != null) {
            print(root);
        }
    }
    private void print(TreeNode node) {
        if (node.left != null) {
            print(node.left);
        }
        System.out.println(node.value);
        if (node.right != null) {
            print(node.right);
        }
    }

    int numberNodes(){
        //empty tree
        if(root == null) {
            return 0;
        } else {
            return 1+ findNodes(root.right)+ findNodes(root.left);
        }
    }
    private int findNodes(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + findNodes(node.left) + findNodes(node.right);
            }
    }

    int numberLeafNodes(){
        if (root == null) { //nothing
            return 0;
        } else if (root.left == null && root.right == null) { //only root
            return 1;
        } else {
            return findLeaves(root.left) + findLeaves(root.right);
        }
    }
    private int findLeaves(TreeNode node) {
        if(node == null) {
            return 0;
        }
        if(node.left == null && node.right == null) {
            return 1;
        } else {
            return findLeaves(node.left) + findLeaves(node.right);
        }
    }

    int height(){
        TreeNode node = root;
        if( root == null) {
            return 0;
        } else if ( node.left == null && node.right == null) {
            return 0;
        } else if (node.right == null) {
            return 1 + getEdges(node.left);
        } else if (node.left == null) {
            return 1 + getEdges(node.right);
        } else {
            return 1 + java.lang.Math.max(getEdges(node.left), getEdges(node.right));

        }
    }
    private int getEdges(TreeNode node) {
        if (node == null) {return 0;} //no node
        else if (node.right == null && node.left == null) { //leaf
            return 0;
        } else if( node.right == null) { //no right branch
            return 1 + getEdges(node.left);
        } else if ( node.left == null) { // no left branch
            return 1 + getEdges(node.right);
        } else { //get deepest of the two branches
            return 1 + java.lang.Math.max(getEdges(node.left), getEdges(node.right));
            }
    }

    void printTree() {
        TreeNode node = root;
        printNodes(node);
    }
    private void printNodes(TreeNode node) {
        if (node != null) {
            System.out.println(node.value);
            printNodes(node.left);
            printNodes(node.right);

        }
    }
}

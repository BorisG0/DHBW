public class BinaryTree <T>{

    private Node<T> root = null;
    private int size = 0;

    public boolean add(T data){
        Node<T> newNode = new Node<T>(data);

        if(root == null) {
            root = newNode;
            size++;
            return true;
        }

        return add(newNode, root);

    }

    private boolean add(Node<T> newNode, Node<T> currentNode){
        int compareToResult = ((Comparable)currentNode.getData()).compareTo(newNode.getData());

        if(compareToResult > 0){ //left side

            if(currentNode.getLeftNode() != null){
                return add(newNode, currentNode.getLeftNode());
            }else{
                currentNode.setLeftNode(newNode);
                size++;
                return true;
            }

        }else if(compareToResult < 0){ //right side

            if(currentNode.getRightNode() != null){
                return add(newNode, currentNode.getRightNode());
            }else{
                currentNode.setRightNode(newNode);
                size++;
                return true;
            }

        }else{ //identical
            return false;
        }

    }

    public void printInOrder(){ //left current right
        if(root == null){
            System.out.println("Tree is empty");
            return;
        }
        printInOrder(root);

    }

    public void printInOrder(Node<T> node){
        if(node.getLeftNode() != null){
            printInOrder(node.getLeftNode());
        }

        System.out.print(node.getData() + ", ");

        if(node.getRightNode() != null){
            printInOrder(node.getRightNode());
        }
    }



    public void printPreOrder(){ //current left right
        if(root == null){
            System.out.println("Tree is empty");
            return;
        }
        printPreOrder(root);

    }

    public void printPreOrder(Node<T> node){
        System.out.print(node.getData() + ", ");

        if(node.getLeftNode() != null){
            printPreOrder(node.getLeftNode());
        }

        if(node.getRightNode() != null){
            printPreOrder(node.getRightNode());
        }
    }



    public void printPostOrder(){ // left right current
        if(root == null){
            System.out.println("Tree is empty");
            return;
        }
        printPostOrder(root);

    }

    public void printPostOrder(Node<T> node){


        if(node.getLeftNode() != null){
            printPostOrder(node.getLeftNode());
        }

        if(node.getRightNode() != null){
            printPostOrder(node.getRightNode());
        }

        System.out.print(node.getData() + ", ");
    }




    private class Node<T> {
        private final T data;
        private Node<T> leftNode;
        private Node<T> rightNode;

        Node(T data){
            this.data = data;
            this.leftNode = null;
            this.rightNode = null;
        }

        public T getData(){
            return data;
        }

        public Node<T> getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node<T> leftNode) {
            this.leftNode = leftNode;
        }

        public Node<T> getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node<T> rightNode) {
            this.rightNode = rightNode;
        }
    }

    public static void main(String[] args) {
        BinaryTree<Integer> myTree = new BinaryTree<Integer>();

        System.out.println("adding 50" + myTree.add(50));
        System.out.println("adding 100" + myTree.add(100));
        System.out.println("adding 100" + myTree.add(100));
        System.out.println("adding 70" + myTree.add(70));
        System.out.println("adding 30" + myTree.add(30));
        System.out.println("adding 20" + myTree.add(20));
        System.out.println("adding 40" + myTree.add(40));
        System.out.println("adding 10" + myTree.add(10));
        System.out.println("adding 80" + myTree.add(80));
        System.out.println("adding 90" + myTree.add(90));
        System.out.println("adding 60" + myTree.add(60));

        System.out.println("size: " + myTree.size);

        System.out.println("InOrder");
        myTree.printInOrder();

        System.out.println("\nPreOrder");
        myTree.printPreOrder();

        System.out.println("\nPostOrder");
        myTree.printPostOrder();


    }
}

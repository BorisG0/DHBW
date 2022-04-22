public class BinaryIntTree {
    private class Node{
        int value;
        Node left;
        Node right;

        Node(int value){
            this.value = value;
        }
    }

    Node root;

    public BinaryIntTree(){
        root = null;
    }

    public boolean add(int value){
        if(root == null){
            root = new Node(value);
            return true;
        }

        return addAtNode(value, root);
    }

    private boolean addAtNode(int value, Node node){
        if(value == node.value){
            return false;
        }

        if(value < node.value){
            if(node.left == null){
                node.left = new Node(value);
                return true;
            }else{
                return addAtNode(value, node.left);
            }
        }else{
            if(node.right == null){
                node.right = new Node(value);
                return true;
            }else{
                return addAtNode(value, node.right);
            }
        }
    }

    public static void main(String[] args) {
        BinaryIntTree myTree = new BinaryIntTree();

        System.out.println("adding 5" + myTree.add(5));
        System.out.println("adding 6" + myTree.add(6));
        System.out.println("adding 1" + myTree.add(1));
        System.out.println("adding 3" + myTree.add(3));
        System.out.println("adding 5" + myTree.add(5));
        System.out.println("adding 10" + myTree.add(10));
    }
}

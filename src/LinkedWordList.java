public class LinkedWordList {

    private WordNode firstWordNode = null;
    private int size = 0;

    public void add(String word){
        this.size++;
        WordNode newWordNode = new WordNode(word);

        if(firstWordNode == null){
            // Liste ist leer
            firstWordNode = newWordNode;
            return;
        }

        WordNode currentWordNode = firstWordNode;
        while(currentWordNode.getNextWordNode() != null){
            currentWordNode = currentWordNode.getNextWordNode();
        }

        currentWordNode.setNextWordNode(newWordNode);
    }

    public boolean contains(String word){
        WordNode currentNode = firstWordNode;
        while(currentNode != null){
            if(currentNode.word.equals(word)){
                return true;
            }
            currentNode = currentNode.nextWordNode;
        }

        return false;
    }

    public int size(){

        return this.size;
    }

    public void printWordList(){
        String allWords = "";

        WordNode currentNode = firstWordNode;
        while(currentNode != null){
            allWords += currentNode.word;
            currentNode = currentNode.nextWordNode;
        }

        System.out.println(allWords);

    }

    public boolean remove(String word){


        if(firstWordNode.word.equals(word)){
            this.firstWordNode = firstWordNode.nextWordNode;
            this.size--;
            return true;
        }

        WordNode currentNode = firstWordNode.nextWordNode;
        WordNode previousNode = firstWordNode;

        while(currentNode != null){
            if(currentNode.word.equals(word)){
                previousNode.setNextWordNode(currentNode.nextWordNode);
                this.size--;
                return true;
            }
            previousNode = currentNode;
            currentNode = currentNode.nextWordNode;
        }
        return false;
    }



    private class WordNode{
        private String word;
        private WordNode nextWordNode;

        public WordNode(String word){
            this.word = word;
            this.nextWordNode = null;
        }

        public void setNextWordNode(WordNode nextWordNode){
            this.nextWordNode = nextWordNode;
        }

        public WordNode getNextWordNode(){
            return this.nextWordNode;
        }

        public String getWord(){
            return this.word;
        }
    }

}
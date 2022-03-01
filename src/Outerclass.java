public class Outerclass {

    public static interface InnerInterface{
        void print(String message);
    }

    public static class InnerTopLevelClass{
        void print(String message){
            System.out.println(InnerTopLevelClass.class.getName()
                    + " - Innere Top Level Klasse sagt: "
                    + message);
        }
    }

    public class InnerElementClass{
        void print(String message){
            System.out.println(this.getClass().getName() +
                    " - Innere Element Klasse sagt: " +
                    message);
        }
    }

    void printFromInnerLocalClass(String message){
        class InnerLocalClass{
            void print(String message){
                System.out.println(this.getClass().getName() +
                        " - Innere Lokale Klasse sagt: " +
                        message);
            }
        }

        InnerLocalClass innerLocalClass = new InnerLocalClass();
        innerLocalClass.print(message);
    }

    void printFromInnerAnonymousClass(String message){
        InnerInterface myInnerAnonymousClass = new InnerInterface() {
            @Override
            public void print(String message) {
                System.out.println(this.getClass().getName() +
                        " - Innere Anonyme Klasse sagt: " +
                        message);
            }
        };

        myInnerAnonymousClass.print(message);
    }

    public static void main(String[] args) {
        String message = "Hello World!";

        InnerTopLevelClass innerTopLevelClass = new InnerTopLevelClass();
        innerTopLevelClass.print(message);

        Outerclass outerclass = new Outerclass();

        Outerclass.InnerElementClass innerElementClass = outerclass.new InnerElementClass();
        innerElementClass.print(message);

        outerclass.printFromInnerLocalClass(message);

        outerclass.printFromInnerAnonymousClass(message);

    }
}

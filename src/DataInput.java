public class DataInput {
    public static void main(String[] args) {
        System.out.println("Geben Sie einen Text ein:");

        byte[] input = new byte[255];

        try{
            System.in.read(input, 0, input.length);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println(new String(input));
    }
}

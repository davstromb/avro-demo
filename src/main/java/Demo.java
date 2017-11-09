import java.io.File;
import java.io.IOException;
import avro.*;

public class Demo {


    public static void main(String[] args) throws IOException {
        System.out.println("\n");
        new Demo();
    }

    public Demo() throws IOException {

        // CREATE AN OBJECT
        User user = User.newBuilder()
                .setName("David")
                .setFavouriteColor("Yellow")
                .setFavouriteNumber(22)
                .build();

        System.out.println("My user: " + user);


        // CREATE OBJECT WITH REFLECTION




        // SERIALIZE TO DISK




        // DESERIALIZE FROM DISK



        // SCHEMA EVOLUTION


    }

    private File getFile(final String filename) {
        return new File(Thread
                .currentThread()
                .getContextClassLoader()
                .getResource("data/" + filename)
                .getPath());
    }


}



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




        // CREATE OBJECT WITH REFLECTION




        // SERIALIZE TO DISK




        // DESERIALIZE FROM DISK



        // SCHEMA EVOLUTION


    }

    private File getFile() {
        return new File(Thread
                .currentThread()
                .getContextClassLoader()
                .getResource("data/users.avro")
                .getPath());
    }


}



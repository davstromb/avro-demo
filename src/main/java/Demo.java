import java.io.File;
import java.io.IOException;
import avro.*;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.avro.reflect.ReflectData;

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
        Schema schema = ReflectData.get().getSchema(DifferentUser.class);
        GenericRecord genericRecord = new GenericRecordBuilder(schema)
                .set("name", "David")
                .set("favourite_color", "Yellow")
                .build();

        System.out.println();
        System.out.println("My different user: " + genericRecord);
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



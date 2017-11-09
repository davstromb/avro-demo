import java.io.File;
import java.io.IOException;
import avro.*;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;


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
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<>(userDatumWriter);
        dataFileWriter.create(user.getSchema(), getFile());
        dataFileWriter.append(user);
        dataFileWriter.flush();
        dataFileWriter.close();

        System.out.println();
        // DESERIALIZE FROM DISK
        DatumReader<User> userDatumReader = new SpecificDatumReader<>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<User>(getFile(), userDatumReader);
        user = null;
        while(dataFileReader.hasNext()) {
            user = dataFileReader.next(user);
            System.out.println("Found a user i file: " + user);
        }

        System.out.println();
        // SCHEMA EVOLUTION
        DatumReader<GenericRecord> genericDatumReader = new GenericDatumReader<GenericRecord>(schema);
        DataFileReader<GenericRecord> genericFileReader
                = new DataFileReader<GenericRecord>(getFile(), genericDatumReader);
        GenericRecord record = null;
        while(genericFileReader.hasNext()) {
            record = genericFileReader.next(record);
            System.out.println("Found a generic user i file: " + record);
        }

    }

    private static File getFile() {
        return new File(Thread
                .currentThread()
                .getContextClassLoader()
                .getResource("data/users.avro")
                .getPath());
    }




}



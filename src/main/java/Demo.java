import avro.user.User;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class Demo {


    public static void main(String[] args) throws IOException {
        System.out.println("\nData Serialization w/ Apache Avro - Demo (Lol)\n\n");
        new Demo();
    }

    public Demo() throws IOException {

        // SERIALIZE
//        User marieDyer = User.newBuilder()
//                .setName("Marie Dyer")
//                .setFavouriteNumber(8)
//                .build();


        User user = new User();
        user.setName("Marie Dyer");
        user.put("name", "Marie Dyer");
        user.put("favourite_color", "Blue");
        user.setFavouriteNumber(8);

        // SERIALIZE TO DISK
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
        dataFileWriter.create(user.getSchema(), new File("users_lulz.avro"));
        dataFileWriter.append(user);
        dataFileWriter.flush();
        dataFileWriter.close();

        // DESERIALIZE




        System.out.println(user);
    }


}



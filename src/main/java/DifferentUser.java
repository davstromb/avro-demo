import org.apache.avro.reflect.AvroName;

public class DifferentUser {

    public String name;

    @AvroName("favourite_color")
    public String favouriteColor;
}

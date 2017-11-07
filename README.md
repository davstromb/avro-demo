# Avro Demo

You need to have _Maven_ and _Java 8_ installed.

### 1. Generate classes
```bash
$ mvn clean generate-sources
```

### 2. Create an object
```java
User user = User.newBuilder()
        .setName("Marie")
        .setFavouriteColor("Green")
        .setFavouriteNumber(7)
        .build();

user.put("favourite_color", "Blue");
user.setFavouriteNumber(8);
```        
### 3. Create an object with reflection

```java
Schema schema = ReflectData.get().getSchema(DifferentUser.class);
GenericRecord gr = new GenericRecordBuilder(schema)
        .set("name", "David")
        .set("age", "27")
        .build();  
```                      

### 4. Serialize to disk
```java      
DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
dataFileWriter.create(user.getSchema(), getFile("users.avro"));
dataFileWriter.append(user);
dataFileWriter.flush();
dataFileWriter.close();
```  

### 5. Deserialize from disk
```java
DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
DataFileReader<User> dataFileReader = new DataFileReader<User>(getFile("users.avro"), userDatumReader);
User userRead = null;
while (dataFileReader.hasNext()) {
    userRead = dataFileReader.next(userRead);
    System.out.println("Reading :-> " + userRead);
}    
```    

### 6. Schema Evolution
```java
DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(getFile("users.avro"), datumReader);
GenericRecord tmpGenericUser = null;
while (dataFileReader.hasNext()) {
        tmpGenericUser = dataFileReader.next(tmpUser);
        System.out.println("Found generic user: " + tmpGenericUser);
}
```

### 7. Run
`$ mvn -q exec:java -Dexec.mainClass=Demo `

### 8. References
 * [Apache Avro Documentation](https://avro.apache.org/docs/current/)
 * [Schema Evolution](https://docs.oracle.com/cd/E26161_02/html/GettingStartedGuide/schemaevolution.html)
 * [Apache Avro - More than just a serialization framework](https://www.slideshare.net/ChicagoHUG/avro-chug-20120416)

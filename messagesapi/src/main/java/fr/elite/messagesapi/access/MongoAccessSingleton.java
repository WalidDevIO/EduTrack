package fr.elite.messagesapi.access;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

import fr.elite.messagesapi.pojo.Message;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;

public class MongoAccessSingleton {

    private static MongoCollection<Message> db = null;

    public static MongoCollection<Message> getDatabase() {
        if (db == null) {
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
            String dbHost = System.getenv("DB_HOST");
            if(dbHost == null) dbHost = "mongodb";
            ConnectionString connectionString = new ConnectionString("mongodb://" + dbHost);
            MongoClient mongoClient = MongoClients.create(connectionString);
            String dbName = System.getenv("DB_NAME");
            if(dbName == null) dbName = "main";
            db = mongoClient.getDatabase(dbName).withCodecRegistry(pojoCodecRegistry).getCollection("messages", Message.class);
        }
        return db;
    }

}

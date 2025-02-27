import { MongoClient } from "mongodb";
import dotenv from "dotenv";

dotenv.config();

const client = new MongoClient(process.env.MONGO_URI || "mongodb://mongodb:27017");

let db;

async function initDB() {
    if (!db) {
        await client.connect();
        console.log("MongoDB connecté !");
        db = client.db("core");
    }
}

export { db, initDB };
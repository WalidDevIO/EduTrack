import { db } from "../db/mongo.js";

//ID en séquence pour mongo
export async function getNextSequence(name) {
    const counters = db.collection("counters");

    const sequenceDocument = await counters.findOneAndUpdate(
        { _id: name },
        { $inc: { sequence_value: 1 } },
        { returnDocument: "after", upsert: true }
    );

    return sequenceDocument.sequence_value;
}
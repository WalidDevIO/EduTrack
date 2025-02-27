import { db } from "../db/mongo.js";
import { getNextSequence } from "./sequence.js";

export const getUEs = async () => {
    return await db.collection("ues").find().toArray();
};

export const getUEById = async (id) => {
    return await db.collection("ues").findOne({ _id: id });
};

export const createUE = async (ue) => {
    const finalUE = { ...ue, _id: await getNextSequence("ues") };
    return await db.collection("ues").insertOne(finalUE);
};

export const updateUE = async (ue) => {
    return await db.collection("ues").updateOne(
        { _id: ue._id },
        { $set: ue }
    );
};

export const deleteUEById = async (id) => {
    return await db.collection("ues").deleteOne({ _id: id });
};

export const deleteUE = async (ue) => {
    return await deleteUEById(ue._id);
};
 
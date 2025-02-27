import { db, initDB } from "../db/mongo.js"
import { getNextSequence } from "./sequence.js"

export const getFormations = async () => {
    return await db.collection("formations").find().toArray()
}

export const getFormationById = async (id) => {
    return await db.collection("formations").findOne({_id: id})
}

export const createFormation = async (formation) => {
    const finalFormation = {...formation, _id: await getNextSequence('formations')}
    return await db.collection("formations").insertOne(finalFormation)
}

export const updateFormation = async (formation) => {
    return await db.collection("formations").updateOne(
        { _id: formation._id },
        { $set: formation }
    )
}

export const deleteFormationById = async (id) => {
    return await db.collection("formations").deleteOne({_id: id})
}

export const deleteFormation = async (formation) => {
    return await deleteFormationById(formation._id)
}

if(process.argv.some(s => s === "test")) {
    await initDB()
    console.log(await getFormations())

    const { insertedId } = await createFormation({
        name: "M1 TIIL",
        responsable: "Eric Cariou",
        mail: "cariou@univ-brest.fr",
        ues: [1, 2, 3]
    })

    console.log(`ID inséré: ${insertedId}`)
    const formation = await getFormationById(insertedId);

    console.log(formation)

    console.log(await updateFormation({
        ...formation,
        name: "M1 TIIL-A"
    }))

    console.log(await getFormations())
}
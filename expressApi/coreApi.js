import express from 'express';
import { registerStudent } from './actions/registerStudent.js';
import { sendMessage } from './actions/sendMessage.js';

const app = express();

app.listen(3000, () => {
    console.log("App listening on port 3000")
})

app.use(express.json())

app.post('/registerStudent', async (req, res) => {
    const { student, password } = req.body
    const result = await registerStudent(student, password)
    if (result) {
        await sendMessage("Votre compte a bien été créé", result)
        res.status(201).send({
            number: result
        })
    }
    else result.status(400).send({
        detail: "Erreur lors de la création de l'étudiant"
    })
})
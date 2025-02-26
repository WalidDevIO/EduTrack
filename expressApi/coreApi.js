import express from 'express';
import { registerStudent } from './actions/registerStudent';
import { sendMessage } from './actions/sendMessage';

const app = express();

app.listen(3000, () => {
    console.log("App listening")
})

app.use(express.json())

app.post('/registerStudent', async (req, res) => {
    const { student, password } = req.body
    const res = registerStudent(student, password)
    if(res) {
        await sendMessage("Votre compte a bien été créé", res)
        res.status(201).send()
    }
    else res.status(400).send({
        detail: "Erreur lors de la création de l'étudiant"
    })
})
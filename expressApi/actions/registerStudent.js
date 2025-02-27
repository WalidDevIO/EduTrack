import { studentApi, accessApi } from '../apis.js'
import { sendMessage } from './sendMessage.js'

export const registerStudent = async (req, res) => {

    const { student, password } = req.body
    
    const createdStudent = await fetch(`${studentApi}`, {
        method: "POST",
        body: JSON.stringify(student),
        headers: {
            "Content-Type": "application/json; charset=UTF-8"
        }
    })
        .then(r => r.json())
        .then(data => data.id)
        .catch(() => false)

    if(!createdStudent) return false

    const result = await fetch(`${accessApi}/register`, {
        method: "POST",
        body: JSON.stringify({
            username: 'e'+createdStudent,
            password,
            student: createdStudent
        }),
        headers: {
            "Content-Type": "application/json; charset=UTF-8"
        }
    }).then(r => r.ok ? createdStudent : false)

    if (result) {
        await sendMessage("Votre compte a bien été créé", result)
        res.status(201).send({
            number: result
        })
    }
    else result.status(400).send({
        detail: "Erreur lors de la création de l'étudiant"
    })
}
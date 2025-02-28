import { messageApi } from "../../apis.js"
import { loggedRoute } from "../protection/loggedRoute.js"

export async function getMessages(req ,res) {
    const logged = await loggedRoute(req, res)
    if(!logged) return

    const result = await fetch(`${messageApi}/student/${logged.studentNumber}`).then(r => r.ok ? r.json() : false)

    if(result === false) {
        res.status(500).send({
            detail: "Erreur interne"
        })
    } else {
        res.send(result)
    }
}

export async function readMessage(req, res) {
    const logged = await loggedRoute(req, res)
    if(!logged) return;

    const message = req.body

    if(logged.studentNumber !== message.student) {
        res.status(400).send({
            detail: "Vous ne pouvez pas lire un message qui ne vous appartient pas!"
        })
    }

    const newMessage = {
        ...message,
        readed: true
    }

    const result = await fetch(`${messageApi}/${newMessage.id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify(newMessage)
    }).then(r => r.ok)

    if(result) {
        res.send({
            detail: "Opération réussie"
        })
    } else {
        res.status(500).send({
            detail: "Errur interne"
        })
    }
}

export async function deleteMessage(req, res) {
    const logged = await loggedRoute(req, res)
    if (!logged) return;

    const messageId = req.params.id

    const message = await fetch(`${messageApi}/${messageId}`).then(r => r.ok ? r.json() : null)

    if (!message || logged.studentNumber !== message.student) {
        res.status(400).send({
            detail: "Vous ne pouvez pas supprimer un message qui ne vous appartient pas!"
        })
    }

    const result = await fetch(`${messageApi}/${messageId}`, {
        method: "DELETE"
    }).then(r => r.ok)

    if (result) {
        res.send({
            detail: "Opération réussie"
        })
    } else {
        res.status(500).send({
            detail: "Errur interne"
        })
    }
}
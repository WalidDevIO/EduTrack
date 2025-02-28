import express from 'express'
import { adminRoute, loggedRoute } from '../routes/protection/loggedRoute.js'
import { accessApi } from '../apis.js'

const router = express.Router()

router.put('/reset-password/:username', async (req, res) => {
    const logged = await adminRoute(req, res)
    if(!logged) return

    const response = await fetch(`${accessApi}/reset-password/${req.params.username}`, {
        method: "PUT",
        body: req.body.password,
        headers: {
            "Content-Type": "application/json; charset=UTF-8"
        }
    }).then(r => r.ok)

    if(response) {
        res.send({
            detail: "Opération réussie"
        })
    } else {
        res.status(500).send({
            detail: "Erreur interne"
        })
    }
})

router.delete('/unregister', async(req, res) => {
    const logged = await loggedRoute(req, res)
    if (!logged) return

    const response = await fetch(`${accessApi}/unregister`, {
        method: "DELETE",
        headers: {
            "Authorization": req.headers.authorization
        }
    }).then(r => r.ok)

    if (response) {
        res.send({
            detail: "Opération réussie"
        })
    } else {
        res.status(500).send({
            detail: "Erreur interne"
        })
    }
})

router.delete('/logout', async (req, res) => {
    const logged = await loggedRoute(req, res)
    if(!logged) return

    const response = await fetch(`${accessApi}/logout`, {
        method: "DELETE",
        headers: {
            "Authorization": req.headers.authorization
        }
    }).then(r => r.ok)

    if (response) {
        res.send({
            detail: "Opération réussie"
        })
    } else {
        res.status(500).send({
            detail: "Erreur interne"
        })
    }
})

router.post('/login', async (req, res) => {
    const response = await fetch(`${accessApi}/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify(req.body)
    }).then(r => r.ok ? r.json() : null).then(data => data)

    if(response) {
        res.send(response)
    } else {
        res.status(500).send({
            detail: "Erreur interne"
        })
    }
})


export const authRouter = router;
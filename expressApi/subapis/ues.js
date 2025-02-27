import express from 'express'
import { adminRoute, loggedRoute } from '../routes/protection/loggedRoute.js';
import { createUE, deleteUEById, getUEById, getUEs, updateUE } from '../actions/ues.js';

const router = express.Router()

router.get('/', async (req, res) => {
    const logged = await loggedRoute(req, res)
    if(!logged) return
    res.send(await getUEs())
});

router.post('/', async (req, res) => {
    const admin = await adminRoute(req, res)
    if(!admin) return
    const ue = req.body
    const { insertedId } = await createUE(ue)
    res.status(201).send(await getUEById(insertedId))
})

router.put('/:id', async (req, res) => {
    const admin = await adminRoute(req, res)
    if(!admin) return
    const ue = req.body
    ue._id = parseInt(req.params.id)
    await updateUE(ue)
    res.send(await getUEById(ue._id))
})

router.get('/:id', async (req, res) => {
    const logged = await loggedRoute(req, res)
    if (!logged) return
    const ue = await getUEById(parseInt(req.params.id))
    if(ue) {
        res.send(ue)
    } else {
        res.status(404).send({
            detail: "L'UE n'existe pas"
        })
    }
})

router.delete('/:id', async (req, res) => {
    const admin = await adminRoute(req, res)
    if (!admin) return
    await deleteUEById(parseInt(req.params.id))
    res.status(204).send()
})

export const uesRouter = router;
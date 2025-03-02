import express from 'express'
import { adminRoute, loggedRoute } from '../routes/protection/loggedRoute.js';
import { createFormation, deleteFormationById, getFormationById, getFormations, updateFormation } from '../actions/formations.js';
import { accept, deny } from '../routes/formations/accept_deny.js';
import { getStudents } from '../routes/formations/students.js';
import { groupsAttribution } from '../routes/formations/groupsAttribution.js';
import { getUes } from '../routes/formations/getUes.js';

const router = express.Router()

router.get('/', async (req, res) => {
    const logged = await loggedRoute(req, res)
    if(!logged) return
    res.send(await getFormations())
});

router.post('/', async (req, res) => {
    const admin = await adminRoute(req, res)
    if(!admin) return
    const formation = req.body
    const { insertedId } = await createFormation(formation)
    res.status(201).send(await getFormationById(insertedId))
})

router.put('/:id', async (req, res) => {
    const admin = await adminRoute(req, res)
    if(!admin) return
    const formation = req.body
    formation._id = parseInt(req.params.id)
    await updateFormation(formation)
    res.send(await getFormationById(formation._id))
})

router.get('/:id', async (req, res) => {
    const logged = await loggedRoute(req, res)
    if (!logged) return
    const formation = await getFormationById(parseInt(req.params.id))
    if(formation) {
        res.send(formation)
    } else {
        res.status(404).send({
            detail: "La formation n'existe pas"
        })
    }
})

router.delete('/:id', async (req, res) => {
    const admin = await adminRoute(req, res)
    if (!admin) return
    await deleteFormationById(parseInt(req.params.id))
    res.status(204).send()
})

router.post('/:id/accept/:student', accept)

router.post('/:id/deny/:student', deny)

router.get('/:id/students', getStudents)

router.post('/:id/attribute-groups', groupsAttribution)

router.get('/:id/ues', getUes)

export const formationsRouter = router;
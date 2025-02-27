import { getFormationById } from "../../actions/formations.js";
import { loggedRoute } from "../protection/loggedRoute.js";
import { getUEById } from "../../actions/ues.js"

export async function getUes(req, res) {
    const logged = loggedRoute(req, res)
    if(!logged)return

    const formationId = parseInt(req.params.id)
    const formation = await getFormationById(formationId)

    if(!formation) {
        res.status(400).send({
            detail: "La formation n'existe pas"
        })
    }

    const ues = []
    for(const ueId of formation.ues ?? []) {
        ues.push(await getUEById(ueId))
    }

    res.send(ues)
}
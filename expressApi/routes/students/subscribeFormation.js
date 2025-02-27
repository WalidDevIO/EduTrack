import { getFormationById } from "../../actions/formations.js"
import { studentApi } from "../../apis.js"
import { loggedRoute } from "../protection/loggedRoute.js"

export const subscribeFormation = async (req, res) => {
    const logged = await loggedRoute(req, res)
    if (!logged) return

    const formationId = parseInt(req.params.id)

    const formation = await getFormationById(formationId)
    if (!formation) {
        res.status(400).send({
            detail: `La formation d'ID ${formationId} n'existe pas!`
        })
        return
    }

    const student = await fetch(`${studentApi}/${logged.studentNumber}`).then(r => r.json())

    const result = await fetch(`${studentApi}/${logged.studentNumber}`, {
        method: "PUT",
        body: JSON.stringify({
            ...student,
            academicYearId: formationId
        }),
        headers: {
            "Content-Type": "application/json; charset=UTF-8"
        }
    }).then(r => r.ok)

    if (result) {
        res.send({
            ...student,
            academicYearId: formationId
        })
    } else {
        res.status(500).send()
    }

}
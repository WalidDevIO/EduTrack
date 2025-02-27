import { adminRoute } from "../protection/loggedRoute.js"
import { getFormationById } from "../../actions/formations.js"
import { studentApi } from "../../apis.js"
import { sendMessage } from "../../actions/sendMessage.js"

export const accept = async (req, res) => {
    await treat(req, res, true)
}

export const deny = async (req, res) => {
    await treat(req, res, false)
}

const treat = async (req, res, status) => {
    const logged = await adminRoute(req, res)
    if (!logged) return

    const formationId = parseInt(req.params.id)
    const studentNumber = parseInt(req.params.student)

    const formation = await getFormationById(formationId)
    if (!formation) {
        res.status(400).send({
            detail: `La formation d'ID ${formationId} n'existe pas!`
        })
        return
    }

    const student = await fetch(`${studentApi}/${studentNumber}`).then(r => r.json())
    
    const result = await fetch(`${studentApi}/${studentNumber}`, {
        method: "PUT",
        body: JSON.stringify(status ? {
            ...student,
            academicYearRegistered: status,
            academicYearId: formationId
        }: {
            ...student,
            academicYearRegistered: status,
            academicYearId: null
        }),
        headers: {
            "Content-Type": "application/json; charset=UTF-8"
        }
    }).then(r => r.ok)

    if (result) {
        res.send(status ? {
            ...student,
            academicYearRegistered: status,
            academicYearId: formationId
        } : {
            ...student,
            academicYearRegistered: status,
            academicYearId: null
        })
        await sendMessage(`Vous avez été ${status ? "accepté" : "refusé"} dans la formation ${formation.nom}.`, student.id)
    } else {
        res.status(500).send()
    }
}
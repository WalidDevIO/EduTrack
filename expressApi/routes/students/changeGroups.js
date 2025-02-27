import { studentApi } from "../../apis.js"
import { adminRoute } from "../protection/loggedRoute.js"
import { getFormationById } from "../../actions/formations.js"
import { sendMessage } from "../../actions/sendMessage.js"

export async function changeGroups(req, res) {
    const logged = await adminRoute(req, res)
    if(!logged) return

    const { td, tp } = req.body

    if(!td || !tp) {
        res.status(400).send({
            detail: "Vous devez spécifier un groupe de TP et de TD obligatoirement !"
        })
        return
    }

    const studentId = parseInt(req.params.id)
    const student = await fetch(`${studentApi}/${studentId}`).then(r => r.ok ? r.json() : null)
    if (!student || !student.academicYearId || !student.academicYearRegistered) {
        res.status(400).send({
            detail: "L'étudiant n'existe pas ou n'est pas inscrit à une formation"
        })
        return
    }

    const formationId = parseInt(student.academicYearId)
    const studentsFormation = await fetch(`${studentApi}`).then(r => r.json()).then(data => data.filter(student => student.academicYearId === formationId))
    const studentsGroups = studentsFormation.map(student => [student.dw, student.pw])
    const formation = await getFormationById(formationId)
    if(!formation) {
        res.status(400).send({
            detail: "La formation de l'étudiant n'existe pas"
        })
        return
    }

    const maxTd = Math.max(...studentsGroups.map(e => e[0]))
    const maxTp = Math.max(...studentsGroups.map(e => e[1]))

    if(td < 1 || td > maxTd) {
        res.status(400).send({
            detail: "Groupe de TD invalide"
        })
        return
    }

    if (tp < 1 || tp > maxTp) {
        res.status(400).send({
            detail: "Groupe de TP invalide"
        })
        return
    }

    if(studentsFormation.map(student => student.pw === tp).length >= formation.tp) {
        res.status(400).send({
            detail: "Ce groupe de TP est plein"
        })
        return
    }

    if (studentsFormation.map(student => student.dw === td).length >= formation.td) {
        res.status(400).send({
            detail: "Ce groupe de TD est plein"
        })
        return
    }

    const result = await fetch(`${studentApi}/${studentId}`, {
        method: "PUT",
        body: JSON.stringify({
            ...student,
            pw: tp,
            dw: td
        }),
        headers: {
            "Content-Type": "application/json; charset=UTF-8"
        }
    }).then(r => r.ok)

    if (result) {
        res.send({
            ...student,
            pw: tp,
            dw: td
        })
        await sendMessage(`Vous faites maintenant partie du groupe de TD ${td} et du groupe de TP ${tp}.`)
    } else {
        res.status(500).send()
    }
}
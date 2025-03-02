import { getFormationById } from "../../actions/formations.js";
import { adminRoute } from "../protection/loggedRoute.js";
import { studentApi } from "../../apis.js";
import { sendMessage } from "../../actions/sendMessage.js"

export async function groupsAttribution(req ,res) {
    const logged = await adminRoute(req, res)
    if(!logged) return

    const formationId = parseInt(req.params.id)
    const formation = await getFormationById(formationId)
    if(!formation) {
        res.status(400).send({
            detail: "La formation n'existe pas"
        })
        return
    }
    const students = await fetch(`${studentApi}`).then(r => r.json()).then(data => data.filter(student => student.academicYearId === formationId))

    const nbTp = Math.ceil(students.length / formation.tp)
    const nbTd = Math.ceil(students.length / formation.td)

    let tp = 1;
    let td = 1;

    students.forEach(async student => {
        const newStudent = {
            ...student,
            dw: (td++)%nbTd + 1,
            pw: (tp++) % nbTp + 1
        }
        await updateStudent(newStudent)
    })

    res.send({
        detail: "Opération réussie"
    })
}

async function updateStudent(student) {
    await fetch(`${studentApi}/${student.id}`, {
        method: "PUT",
        body: JSON.stringify(student),
        headers: {
            "Content-Type": "application/json; charset=UTF-8"
        }
    })
    sendMessage(`Vous faites maintenant partie du groupe de TD ${student.dw} et du groupe de TP ${student.pw}.`, student.id)
}
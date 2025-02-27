import { getFormationById } from "../../actions/formations.js";
import { studentApi } from "../../apis.js";
import { loggedRoute } from "../protection/loggedRoute.js"

export const studentInfo = async (req, res) => {
    const answer = await loggedRoute(req, res)
    if(!answer) return;

    if(!answer.studentNumber) {
        res.status(400).send({
            detail: "Vous êtes un administrateur"
        })
        return
    }

    const student = await fetch(`${studentApi}/${answer.studentNumber}`).then(r => r.ok ? r.json() : null)
    if(student == null) {
        res.status(500).send()
        return
    }

    const formation = await getFormationById(student.academicYearId ?? -1)
    
    res.send({
        student,
        formation
    })
}
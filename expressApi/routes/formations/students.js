import { studentApi } from "../../apis.js";
import { adminRoute } from "../protection/loggedRoute.js";

export async function getStudents(req, res) {
    const logged = await adminRoute(req, res)
    if(!logged) return

    const formationId = parseInt(req.params.id)

    const students = await fetch(`${studentApi}`).then(r => r.json()).then(data => data.filter(student => student.academicYearId === formationId))

    res.send(students)
}
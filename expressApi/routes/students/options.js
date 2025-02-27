import { getUEById } from "../../actions/ues.js"
import { studentApi } from "../../apis.js"
import { loggedRoute } from "../protection/loggedRoute.js"

export const subscribe = async (req, res) => {
    await treat(req, res, true)
}

export const unsubscribe = async (req, res) => {
    await treat(req, res, false)
}

const treat = async (req, res, add) => {
    //Vérifier l'authentification
    const logged = await loggedRoute(req, res)
    if(!logged) return

    //Récupérer tout les étudiants (ici on pourrait filtrer par promotion mais comme on a pas le temps et qu'il n'y aura pas bcp de données normalement osef)
    const students = await fetch(`${studentApi}`).then(r => r.json())
    //On récupère l'etudiant concerné
    const student = students.find(student => student.id === logged.studentNumber)
    //Si on ne l'a pas trouvé c'est que c'est l'admin
    if(!student) {
        res.status(400).send({
            detail: "Vous êtes l'administrateur"
        })
        return
    }

    //On récupère l'UE
    const ueId = parseInt(req.params.id)
    const ue = await getUEById(ueId)

    //On check que c'est bien une option
    if(!ue.option) {
        res.status(400).send({
            detail: "L'UE n'est pas optionnelle"
        })
        return
    }

    if(add && student.coursesId?.includes(ueId)) {
        res.status(400).send({
            detail: "Vous êtes déjà inscrit à cette UE"
        })
        return
    }

    //Ignorer cette opération si l'étudiant compte unsubscribe à l'option (Vérifie qu'il reste de la place dans l'option)
    const count = add ? students.filter(student => student.coursesId?.includes(ueId)).length : 0
    if(add && ue.capacite !== -1 && count > ue.capacite) {
        res.status(400).send({
            detail: "L'UE n'a plus de place pour de nouveaux inscrits"
        })
        return
    }

    //On prépare la nouvelle version de l'étudiant (Si ajout => ancien tableau d'UE + la nouvelle sinon ancien tableau sur lequel on filtre les UE différente de celle courante)
    const newStudent = {
        ...student,
        coursesId: add ? [...student.coursesId, ueId] : student.coursesId.filter(ue => ue !== ueId)
    }

    //On le met à jour cote Etudiant API
    const result = await fetch(`${studentApi}/${student.id}`, {
        method: "PUT",
        body: JSON.stringify(newStudent),
        headers: {
            "Content-Type": "application/json; charset=UTF-8"
        }
    }).then(r => r.ok)

    if(result) {
        //Opération réussie
        res.send({
            detail: "Opération réussie"
        })
    } else {
        //Erreur dans l'API Etudiant
        res.status(500).send({
            detail: "Erreur interne"
        })
    }
}
import express from 'express';

const app = express();

app.listen(3000, () => {
    console.log("App listening")
})

app.use(express.json())

app.get('/infosEtu', async (req, res) => {

    const authorizationHeader = req.headers.authorization
    if(!authorizationHeader) {
        res.status(403).send()
    }

    const authRes = await fetch('authapi/me', {
        headers: {
            "Authorization": authorizationHeader
        }
    })
    .then(r => r.json())
    .then(json => {
        if(json.ok) return json.studentNumber
        else return null
    })

    if(!authRes){
        res.status(403).send()
    }

    const infosEtudiant = fetch('apibaptiste/etudiants/' + authRes)
    .then(r => r.json())

    const infosPromo = fetch('apianneee/annee/' + infosEtudiant.academicYear)
    .then(r => r.json())


    res.send({...infosEtudiant, ...infosPromo}, 200)
})
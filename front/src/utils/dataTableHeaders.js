export const adminStudentHeaders = (formations) => [
    {
        key: "id",
        title: "Numéro étudiant"
    },
    {
        key: "surname",
        title: "Identité",
        value: ({ surname, firstname }) => `${surname.toUpperCase()} ${firstname}`
    },
    {
        key: "academicYearId",
        title: "Formation",
        value: (item) => formations.find(f => f._id === item.academicYearId)?.nom ?? "Aucun formation choisie"
    },
    {
        key: "academicYearRegistered",
        title: "Accepté",
        value: ({ academicYearRegistered }) => academicYearRegistered ? "Oui" : "Non"
    },
    {
        key: "actions",
        title: "Actions",
        width: '20%'
    }
]

export const adminFormationsHeaders = [
    {
        key :'nom',
        title: 'Nom'
    },
    {
        key: 'td',
        title: 'Taille max. TD'
    },
    {
        key: 'tp',
        title: 'Taille max. TP'
    },
    {
        key: 'options',
        title: "Nombre min. d'options"
    },
    {
        key: 'responsable',
        title: 'Responsable'
    },
    {
        key: "actions",
        title: "Actions",
        width: '10%'
    }
]

export const adminUesHeaders = [
    {
        key: 'nom',
        title: 'Nom'
    },
    {
        key: 'responsable',
        title: 'Responsable'
    },
    {
        key: 'option',
        title: 'Optionnelle ?'
    },
    {
        key: "actions",
        title: "Actions",
        width: '10%'
    }
]
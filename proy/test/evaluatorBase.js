//Evaluador extraido de mi viejo proyecto de curriculas
//Notese que en este evaluador no existen los planes
//Tampoco existen multiples carreras y facultades
//Le pedi a chatgpt que lo emprolije y agregue ejemplos

function getSubjectsInGroup(groupId, subjects) {
    const groupSubjects = [];
    for (const subject of subjects) {
        if (subject.SubjectGroups.includes(groupId)) {
            groupSubjects.push(subject);
        }
    }
    return groupSubjects;
}

function evaluateRequirement(requirement, user) {
    if (requirement == undefined) {
        return true;
    } else if (requirement.startsWith("Exam(")) {
        const id = requirement.substring(5, requirement.length - 1);
        return user.Exams.includes(id);
    } else if (requirement.startsWith("Course(")) {
        const id = requirement.substring(7, requirement.length - 1);
        return user.courses.includes(id);
    } else if (requirement.startsWith("Enrolled(")) {
        const id = requirement.substring(8, requirement.length - 1);
        return user.Enrolls.includes(id);
    } else if (requirement.startsWith("NOT(")) {
        const innerRequirement = requirement.substring(4, requirement.length - 1);
        return !evaluateRequirement(innerRequirement, user);
    } else if (requirement.startsWith("NOf(")) {
        const startIndex = requirement.indexOf("[");
        const endIndex = requirement.indexOf("]");
        const n = parseInt(requirement.substring(4, startIndex - 1));
        const innerRequirements = requirement
            .substring(startIndex + 1, endIndex)
            .split(",")
            .map((r) => r.trim());

        let trueCount = 0;
        for (const innerReq of innerRequirements) {
            if (evaluateRequirement(innerReq, user)) {
                trueCount++;
                if (trueCount >= n) {
                    return true;
                }
            }
        }
        return false;
    } else if (requirement.startsWith("CredOnGroup(")) {
        const startIndex = requirement.indexOf("(") + 1;
        const endIndex = requirement.indexOf(")");
        const params = requirement.substring(startIndex, endIndex).split(",");
        const groupId = params[0];
        const amount = parseInt(params[1]);

        const groupSubjects = getSubjectsInGroup(groupId, subjects);

        // Recursively get all child groups and their subjects
        function getChildGroupSubjects(group) {
            let childSubjects = [];
            if (group.childs) {
                for (const child of group.childs) {
                    childSubjects = [
                        ...childSubjects,
                        ...getSubjectsInGroup(child.id, subjects),
                        ...getChildGroupSubjects(child),
                    ];
                }
            }
            return childSubjects;
        }

        const childGroupSubjects = getChildGroupSubjects(
            subjectGroups.find((group) => group.id === groupId)
        );

        // Combine subjects from the current group and child groups
        const allGroupSubjects = [...groupSubjects, ...childGroupSubjects];

        // Filter subjects that are in the user's Exams
        const userExamSubjects = allGroupSubjects.filter((subject) =>
            user.Exams.includes(subject.Id)
        );

        // Calculate the sum of credits for exam subjects
        const sumCredits = userExamSubjects.reduce(
            (totalCredits, subject) => totalCredits + subject.Credits,
            0
        );

        return sumCredits >= amount;
    } else {
        return false; // Invalid requirement
    }
}

// Example user data, subject groups, and subjects
const user = {
    courses: ["83"],
    Exams: ["67", "68"],
    Enrolls: [],
};

const subjectGroups = [
    {
        id: "1",
        name: "example",
        childs: [
            {
                name: "mates",
                id: "2",
            },
        ],
    },
    {
        id: "3",
        name: "example2",
        childs: [
            {
                name: "fisica",
                id: "4",
            },
        ],
    },
];

const subjects = [
    {
        Id: "67",
        Name: "subject example",
        SubjectGroups: ["2"],
        Credits: 3,
    },
    {
        Id: "68",
        Name: "subject example2",
        SubjectGroups: ["2"],
        Credits: 8,
        Requeriments: "NOf(2,[course(83), exam(67), NOT(Enrolled(92))])",
    },
    {
        Id: "92",
        Name: "subject example3",
        SubjectGroups: ["3"],
        Credits: 8,
        Requeriments: "NOT(Enrolled(83))",
    },
    {
        Id: "83",
        Name: "subject example4",
        SubjectGroups: ["4"],
        Credits: 8,
        Requeriments: "CredOnGroup(1, 7)",
    },
];

const isRequirementsMet = evaluateRequirement(
    subjects[3].Requeriments,
    user
);
console.log(`Subject requirements met: ${isRequirementsMet}`);
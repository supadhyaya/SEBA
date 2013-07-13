var dummy = {};

dummy.universities = [

    {
        value: null,
        name: "Choose University",
        domain: null
    },
    {
        value: 1,
        name: "Technical University Munich",
        domain: "tum.de"
    },
    {
        value: 2,
        name: "Ludwig Maximilians University Munich",
        domain: "uni-muenchen.de"
    },
    {
        value: 3,
        name: "University of Augsburg",
        domain: "uni-augsburg.de"
    }

];

dummy.loginanswer = {
    error: false,
    message: "Wrong username/password"
};

dummy.registeranswer = {
    error: true,
    message: "Confirmation email has been sent to you."
};

dummy.mycourses = [
    {
        name: "Course 1",
        id: 1,
        src: "www"
    },
    {
        name: "Course 2",
        id: 2,
        src: "www"
    },
    {
        name: "Course 3",
        id: 3,
        src: "www"
    },
    {
        name: "Course 4",
        id: 4,
        src: "www"
    },
    {
        name: "Course 5",
        id: 5,
        src: "www"
    }
];

dummy.questions = [
    {
        id: 1,
        question: "Q 1",
        answer: null
    },
    {
        id: 2,
        question: "Q 2",
        answer: null
    },
    {
        id: 3,
        question: "Q 3",
        answer: null
    },
    {
        id: 4,
        question: "Q 4",
        answer: null
    },
    {
        id: 5,
        question: "Q 5",
        answer: null
    }
];

dummy.course = {

    id: 1232,
    name: "Course Name",
    attendeesNr: 30,
    averageGrade: 4,
    questions: dummy.questions

};

dummy.serachresults = [
    {
        cid: 123,
        uid: 1,
        uname: "Technical University Munich",
        cname: "Software Engineering for Business Apps - Web Applications Development",
        ccode: "IN2033",
        ugrade: 4.5,
        cgrade: 4.5
    },
    {
        cid: 124,
        uid: 1,
        uname: "Technical University Munich",
        cname: "Protein Prediction 1",
        ccode: "IN2001",
        ugrade: 4.5,
        cgrade: 4.0
    },
    {
        cid: 125,
        uid: 1,
        uname: "Technical University Munich",
        cname: "Distributed Applications",
        ccode: "IN2254",
        ugrade: 4.5,
        cgrade: 4.4
    }
];
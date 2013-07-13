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
       cid: 1,
       qid: 1,
       question: "The sequence of topics covered in the course was well thought out and logical?",
       avalue: null
   },
   {
       cid: 1,
       qid: 2,
       question: "Objectives of the course were clearly stated from the start?",
       avalue: null
   },
   {
       cid: 1,
       qid: 3,
       question: "Lectures reviewed many topics covered that were covered in other courses before?",
       avalue: null
   },
   {
       cid: 1,
       qid: 4,
       question: "The logic of instructor was easy to understand and follow?",
       avalue: null
   },
   {
       cid: 1,
       qid: 5,
       question: "Possibilities of practical application were adequately presented?",
       avalue: null
   },
   {
       cid: 1,
       qid: 6,
       question: "The relationship of course content to other research was clearly indicated?",
       avalue: null
   },
   {
       cid: 1,
       qid: 7,
       question: "The projected timeframe for covering the course material was appropriate for me?",
       avalue: null
   },
   {
       cid: 1,
       qid: 8,
       question: "Examples of course subject matter were clear and illustrative?",
       avalue: null
   },
   {
       cid: 1,
       qid: 9,
       question: "The course homepage was useful?",
       avalue: null
   },
   {
       cid: 1,
       qid: 10,
       question: "Presentation methods were well-designed and easy to understand?",
       avalue: null
   },
   {
       cid: 1,
       qid: 11,
       question: "The instructor clearly communicated and presented course content?",
       avalue: null
   },
   {
       cid: 1,
       qid: 12,
       question: "The instructor was well prepared?",
       avalue: null
   },
   {
       cid: 1,
       qid: 13,
       question: "The instyructor showed interest in student's success?",
       avalue: null
   },
   {
       cid: 1,
       qid: 14,
       question: "The instructor was available outside the class?",
       avalue: null
   },
   {
       cid: 1,
       qid: 15,
       question: "The instructor took interest and responded to student questions and comments?",
       avalue: null
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
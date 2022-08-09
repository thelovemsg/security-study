// function printQuiz(questions){
//     questions.forEach(question=>{
//         console.log(question.description)
//         switch (question.type){
//             case 'boolean':
//                 console.log('1. true')
//                 console.log('2. false')
//                 break;
//             case 'multipleChoice':
//                 question.options.forEach((option, index) => {
//                     console.log(`${index+1}. ${option}`)
//                 })
//                 break;
//             case 'text':
//                 console.log('Answer: __________________')
//                 break;
//         }
//         console.log('')
//     })
// }

// const questions=[
//     {
//         type: 'boolean',
//         description: 'This Test is useful'
//     },
//     {
//         type: 'multipleChoice',
//         description: 'what is your favorite language?',
//         options: ['CSS', 'HTML',' JS', 'Python']
//     },
//     {
//         type: 'text',
//         description: 'describe your favorite JS feature.'
//     }
// ]

// printQuiz(questions)

class BooleanQuestion{
    constructor(description){
        this.description = description
    }

    printQuestionChoices() {
        console.log('1. True');
        console.log('2. false');
    }
}

class MultipleChoiceQuestion{
    constructor(description, options){
        this.description = description
        this.options = options
    }

    printQuestionChoices(){
        this.options.forEach((option, index) => {
            console.log(`${index+1}. ${option}`)
        })
    }
}

class TextQuestion{
    constructor(description){
        this.description = description
    }

    printTextQestions(){
        console.log('Answer: __________________')
    }
}

class RangeQuestion{
    constructor(description){
        this.description = description
    }

    printRangeQuestions(){
        console.log('Maximum: __________________')
        console.log('Minumum: __________________')
    }
}

function printQuiz(questions){
    questions.forEach(question=>{
        console.log(question.description)
        question.printQuestionChoices()
        console.log('')
    })
}

const questions=[
    new BooleanQuestion('This test is useful'),
    new MultipleChoiceQuestion(
        'what is your favorite language?',
        ['CSS', 'HTML', 'Java', 'Js', 'Python']
    ),
    new TextQuestion('Describe your favorite JS feature.'),
    new RangeQuestion('what is the speed limit in your city'),
]
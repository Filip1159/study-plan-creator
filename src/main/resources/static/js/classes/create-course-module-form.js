const addedCoursesInput = document.querySelector("#coursesInModule")

const lectureEctsInput = document.querySelector('#lectureEcts')
const exercisesEctsInput = document.querySelector('#exercisesEcts')
const labEctsInput = document.querySelector('#labEcts')
const projectEctsInput = document.querySelector('#projectEcts')
const seminaryEctsInput = document.querySelector('#seminaryEcts')

const lectureCnpsInput = document.querySelector('#lectureCnps')
const exercisesCnpsInput = document.querySelector('#exercisesCnps')
const labCnpsInput = document.querySelector('#labCnps')
const projectCnpsInput = document.querySelector('#projectCnps')
const seminaryCnpsInput = document.querySelector('#seminaryCnps')

const lectureZzuInput = document.querySelector('#lectureZzu')
const exercisesZzuInput = document.querySelector('#exercisesZzu')
const labZzuInput = document.querySelector('#labZzu')
const projectZzuInput = document.querySelector('#projectZzu')
const seminaryZzuInput = document.querySelector('#seminaryZzu')

window.addEventListener('load', () => {
    const spanWithIds = document.querySelector('#spanWithIds')
    const coursesInModuleInput = document.querySelector('#coursesInModule')
    const courseIds = spanWithIds.textContent.split(',')
    courseIds.forEach(id => {
        const icon = document.querySelector(`#deleteIcon${id}`)
        icon.addEventListener('click', () => removeAddedCourse(id))
    })
    coursesInModuleInput.value = spanWithIds.textContent
    spanWithIds.parentNode.removeChild(spanWithIds)
})

const removeCourseInModuleErrors = () => {
    const errorSpan = document.querySelector('#courseInModuleErrors')
    errorSpan.parentNode.removeChild(errorSpan)
}

const renderAddedCourseTableRow = courseData => {
    const tr = renderTableRowForModuleForm(courseData)
    tr.id = `addedCourse${courseData.id}`
    return tr
}

const renderFoundCourseTableRow = courseData => {
    const tr = renderTableRowForModuleForm(courseData)
    tr.classList.add('modal__table__foundCourseTr')
    tr.id = `foundCourse${courseData.id}`
    return tr
}

const renderTableRowForModuleForm = courseData => {
    const tr = document.createElement('tr')
    tr.classList.add('fancyTable__tbody__tr')
    const td = document.createElement('td')
    td.classList.add('fancyTable__cell')
    td.classList.add(`createModuleForm__addedCourses__table__cell--name`)
    td.innerText = courseData.name
    tr.appendChild(td)
    return tr
}

const renderEmptyAddedCoursesTable = () => {
    tableWrapper.innerHTML =
        `<table class="createClassForm__addedCourses__table fancyTable">
            <thead class="fancyTable__thead">
            <tr>
                <th class="fancyTable__cell createModuleForm__addedCourses__table__cell--name">Nazwa</th>
            </tr>
            </thead>
            <tbody class="fancyTable__tbody createClassForm__addedCourses__tbody">
            </tbody>
        </table>
        <div class="createClassForm__addedCourses__buttonsColumn"></div>`
    addedCoursesTbody = document.querySelector(".createClassForm__addedCourses__tbody")
    deleteIconsColumn = document.querySelector(".createClassForm__addedCourses__buttonsColumn")
}

const renderEmptyFoundCoursesTable = () => {
    try {
        modal.removeChild(document.querySelector('.modal__noResultsSpan'))
    } catch (e) {}
    const table = document.createElement('table')
    table.classList.add('fancyTable')
    table.classList.add('modal__table')
    table.innerHTML = `
        <thead class="fancyTable__thead">
        <tr>
            <th class="fancyTable__cell createModuleForm__addedCourses__table__cell--name">Nazwa</th>
        </tr>
        </thead>
        <tbody class="fancyTable__tbody modal__tbody">
        </tbody>`
    modal.appendChild(table)
    tbody = document.querySelector(".modal__tbody")
}

const renderNoAddedCoursesSpan = () => {
    tableWrapper.innerHTML =
        `<span class="createClassForm__noAddedCoursesSpan">Brak kursów w module</span>`
}

const coursesQuery = async e => {
    const name = e.target.value
    const wayOfCrediting = wayOfCreditingInput.value
    const type = typeInput.value
    const area = areaInput.value
    const learningEffects = learningEffectsInput2.value

    const lectureEcts = lectureEctsInput.value
    const exercisesEcts = exercisesEctsInput.value
    const labEcts = labEctsInput.value
    const projectEcts = projectEctsInput.value
    const seminaryEcts = seminaryEctsInput.value

    const lectureCnps = lectureCnpsInput.value
    const exercisesCnps = exercisesCnpsInput.value
    const labCnps = labCnpsInput.value
    const projectCnps = projectCnpsInput.value
    const seminaryCnps = seminaryCnpsInput.value

    const lectureZzu = lectureZzuInput.value
    const exercisesZzu = exercisesZzuInput.value
    const labZzu = labZzuInput.value
    const projectZzu = projectZzuInput.value
    const seminaryZzu = seminaryZzuInput.value

    const res = await fetch(
        `/classes/query/MODULE?name=${name}&wayOfCrediting=${wayOfCrediting}&type=${type}&area=${area}&learningEffects=${learningEffects}`
           + `&lectureEcts=${lectureEcts}&exercisesEcts=${exercisesEcts}&labEcts=${labEcts}&projectEcts=${projectEcts}&seminaryEcts=${seminaryEcts}`
           + `&lectureCnps=${lectureCnps}&exercisesCnps=${exercisesCnps}&labCnps=${labCnps}&projectCnps=${projectCnps}&seminaryCnps=${seminaryCnps}`
           + `&lectureZzu=${lectureZzu}&exercisesZzu=${exercisesZzu}&labZzu=${labZzu}&projectZzu=${projectZzu}&seminaryZzu=${seminaryZzu}`)
    let data = await res.json()
    const currentlyAddedCourses = addedCoursesInput.value.split(',').map(Number)
    data = data.filter(foundClass => !currentlyAddedCourses.includes(foundClass.id))
    if (data.length === 0) renderNoResultsSpan()
    else {
        if (document.querySelector('.modal__noResultsSpan')) renderEmptyFoundCoursesTable()
        tbody.innerHTML = ''
        for (let item of data) {
            const tr = renderFoundCourseTableRow(item)
            tr.addEventListener('click', () => {
                if (!addedCoursesInput.value) renderEmptyAddedCoursesTable()
                addedCoursesTbody.appendChild(renderAddedCourseTableRow(item))
                deleteIconsColumn.appendChild(renderDeleteIcon(item.id))
                addedCoursesInput.value += `${addedCoursesInput.value === '' ? '' : ','}${item.id}`
                setBaseInputsEnabled(false)
                const foundCourseToRemove = document.querySelector(`#foundCourse${item.id}`)
                tbody.removeChild(foundCourseToRemove)
                removeCourseInModuleErrors()
            })
            tbody.appendChild(tr)
        }
    }
}

searchCoursesInput.addEventListener("input", coursesQuery)

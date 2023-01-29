const addedCoursesInput = document.querySelector("#coursesInGroup")

const renderAddedCourseTableRow = courseData => {
    const tr = renderCellsInTableRowForGroupForm(courseData)
    tr.id = `addedCourse${courseData.id}`
    return tr
}

const renderFoundCourseTableRow = courseData => {
    const tr = renderCellsInTableRowForGroupForm(courseData)
    tr.classList.add('modal__table__foundCourseTr')
    tr.id = `foundCourse${courseData.id}`
    return tr
}

const renderCellsInTableRowForGroupForm = courseData => {
    const tr = document.createElement('tr')
    tr.classList.add('fancyTable__tbody__tr')
    const itemFields = ['name', 'ECTS', 'CNPS', 'ZZU', 'courseType']
    for (let field of itemFields) {
        const td = document.createElement('td')
        td.classList.add('fancyTable__cell')
        td.classList.add(`createClassForm__addedCourses__table__cell--${field}`)
        td.innerText = courseData[field]
        tr.appendChild(td)
    }
    return tr
}

const coursesQuery = async e => {
    const name = e.target.value
    const wayOfCrediting = wayOfCreditingInput.value
    const type = typeInput.value
    const area = areaInput.value
    const learningEffects = learningEffectsInput2.value
    const res = await fetch(
        `/classes/query/GROUP?name=${name}&wayOfCrediting=${wayOfCrediting}&type=${type}&area=${area}&learningEffects=${learningEffects}`)
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
                removeCourseInGroupErrors()
            })
            tbody.appendChild(tr)
        }
    }
}

searchCoursesInput.addEventListener("input", coursesQuery)

window.addEventListener('load', () => {
    const spanWithIds = document.querySelector('#spanWithIds')
    const coursesInGroupInput = document.querySelector('#coursesInGroup')
    const courseIds = spanWithIds.textContent.split(',')
    courseIds.forEach(id => {
        const icon = document.querySelector(`#deleteIcon${id}`)
        icon.addEventListener('click', () => removeAddedCourse(id))
    })
    coursesInGroupInput.value = spanWithIds.textContent
    spanWithIds.parentNode.removeChild(spanWithIds)
})

const removeCourseInGroupErrors = () => {
    const errorSpan = document.querySelector('#courseInGroupErrors')
    errorSpan.parentNode.removeChild(errorSpan)
}

const renderEmptyAddedCoursesTable = () => {
    tableWrapper.innerHTML =
        `<table class="createClassForm__addedCourses__table fancyTable">
            <thead class="fancyTable__thead">
            <tr>
                <th class="fancyTable__cell createGroupForm__addedCourses__table__cell--name">Nazwa</th>
                <th class="fancyTable__cell createGroupForm__addedCourses__table__cell--ECTS">ECTS</th>
                <th class="fancyTable__cell createGroupForm__addedCourses__table__cell--CNPS">CNPS</th>
                <th class="fancyTable__cell createGroupForm__addedCourses__table__cell--ZZU">ZZU</th>
                <th class="fancyTable__cell createGroupForm__addedCourses__table__cell--courseType">Typ</th>
            </tr>
            </thead>
            <tbody class="fancyTable__tbody createClassForm__addedCourses__tbody">
            </tbody>
        </table>
        <div class="createClassForm__addedCourses__buttonsColumn"></div>`
    addedCoursesTbody = document.querySelector(".createClassForm__addedCourses__tbody")
    deleteIconsColumn = document.querySelector(".createClassForm__addedCourses__buttonsColumn")
}

const renderNoAddedCoursesSpan = () => {
    tableWrapper.innerHTML =
        `<span class="createClassForm__noAddedCoursesSpan">Brak kursów w grupie</span>`
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
            <th class="fancyTable__cell createGroupForm__addedCourses__table__cell--name">Nazwa</th>
            <th class="fancyTable__cell createGroupForm__addedCourses__table__cell--ECTS">ECTS</th>
            <th class="fancyTable__cell createGroupForm__addedCourses__table__cell--CNPS">CNPS</th>
            <th class="fancyTable__cell createGroupForm__addedCourses__table__cell--ZZU">ZZU</th>
            <th class="fancyTable__cell createGroupForm__addedCourses__table__cell--courseType">Typ</th>
        </tr>
        </thead>
        <tbody class="fancyTable__tbody modal__tbody">
        </tbody>`
    modal.appendChild(table)
    tbody = document.querySelector(".modal__tbody")
}

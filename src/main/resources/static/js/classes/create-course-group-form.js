const form = document.querySelector(".createClassForm")
const addCourseButton = document.querySelector(".createGroupForm__addedCourses__button")
let addedCoursesTbody = document.querySelector(".createGroupForm__addedCourses__tbody")
const groupModal = document.querySelector(".groupModal")
let searchCoursesInput = document.querySelector(".groupModal__searchBar__input")
const modalCloseButton = document.querySelector(".groupModal__closeButton")
const nameInput = document.querySelector("#name")
const ectsInput = document.querySelector("#ects")
const cnpsInput = document.querySelector("#cnps")
const zzuInput = document.querySelector("#zzu")
const wayOfCreditingInput = document.querySelector("#wayOfCrediting")
const typeInput = document.querySelector("#type")
const areaInput = document.querySelector("#area")
const learningEffectsInput2 = document.querySelector("#learningEffects")
let tbody = document.querySelector(".groupModal__tbody")
const coursesInGroupInput = document.querySelector("#coursesInGroup")
let deleteIconsColumn = document.querySelector(".createGroupForm__addedCourses__buttonsColumn")
const tableWrapper = document.querySelector(".createGroupForm__addedCourses__tableWrapper")

console.log(nameInput.value)
console.log(ectsInput.value)
console.log(wayOfCreditingInput.value)
console.log(learningEffectsInput2.value)

const areAllInputsFilled = () => {
    return nameInput.value !== '' && ectsInput.value >= 0 && cnpsInput.value > 0 && zzuInput.value > 0
        && wayOfCreditingInput.value && typeInput.value && areaInput.value !== '' && learningEffectsInput2.value !== ''
}

[nameInput, ectsInput, cnpsInput, zzuInput, wayOfCreditingInput, typeInput, areaInput, learningEffectsInput2].forEach(
    input => input.addEventListener('input', () => addCourseButton.disabled = !areAllInputsFilled())
)

const setBaseInputsEnabled = enabled => {
    [nameInput, ectsInput, cnpsInput, zzuInput, wayOfCreditingInput, typeInput, areaInput, learningEffectsSelect].forEach(
        input => input.readOnly = !enabled
    )
}

// form?.addEventListener("submit", e => {
//     // e.preventDefault()
//     const formData = new FormData(form)
//     for (const value of formData.values()) {
//         console.log(value);
//     }
// })

addCourseButton.addEventListener("click", e => {
    e.preventDefault()
    groupModal.classList.remove("groupModal--hidden")
    searchCoursesInput.focus()
})

const renderAddedCourseTableRow = courseData => {
    const tr = document.createElement('tr')
    tr.classList.add('fancyTable__tbody__tr')
    tr.id = `courseAddedToGroup${courseData.id}`
    const itemFields = ['name', 'ECTS', 'CNPS', 'ZZU', 'courseType']
    for (let field of itemFields) {
        const td = document.createElement('td')
        td.classList.add('fancyTable__cell')
        td.classList.add(`createGroupForm__addedCourses__table__cell--${field}`)
        td.innerText = courseData[field]
        tr.appendChild(td)
    }
    return tr
}

const renderFoundCourseTableRow = courseData => {
    const tr = document.createElement('tr')
    tr.classList.add('fancyTable__tbody__tr')
    tr.classList.add('groupModal__table__foundCourseTr')
    const itemFields = ['name', 'ECTS', 'CNPS', 'ZZU', 'courseType']
    for (let field of itemFields) {
        const td = document.createElement('td')
        td.classList.add('fancyTable__cell')
        td.classList.add(`createGroupForm__addedCourses__table__cell--${field}`)
        td.innerText = courseData[field]
        tr.appendChild(td)
    }
    return tr
}

const renderDeleteIcon = itemIdToDelete => {
    const img = document.createElement('img')
    img.classList.add('fancyTable__closeButtonDiv__icon')
    img.id = `deleteIcon${itemIdToDelete}`
    img.src = '/img/close-icon.png'
    img.addEventListener('click', () => {
        const trToDelete = document.querySelector(`#courseAddedToGroup${itemIdToDelete}`)
        const deleteIconToDelete = document.querySelector(`#deleteIcon${itemIdToDelete}`)
        addedCoursesTbody.removeChild(trToDelete)
        deleteIconsColumn.removeChild(deleteIconToDelete)
        coursesInGroupInput.value = coursesInGroupInput.value.replace(`${itemIdToDelete}`, '')
        coursesInGroupInput.value = coursesInGroupInput.value.replace(',,', ',')
        coursesInGroupInput.value = coursesInGroupInput.value.replace(/^,/, '')
        if (!coursesInGroupInput.value) renderNoAddedCoursesSpan()
    })
    return img
}

const coursesQuery = async e => {
    console.log('input')
    const name = e.target.value
    const wayOfCrediting = wayOfCreditingInput.value
    const type = typeInput.value
    const area = areaInput.value
    const learningEffects = learningEffectsInput2.value
    const res = await fetch(
        `/classes?name=${name}&wayOfCrediting=${wayOfCrediting}&type=${type}&area=${area}&learningEffects=${learningEffects}`)
    const data = await res.json()
    console.log(data)
    if (data.length === 0) renderNoResultsSpan()
    else {
        if (document.querySelector('.groupModal__noResultsSpan')) renderEmptyFoundCoursesTable()
        tbody.innerHTML = ''
        for (let item of data) {
            const tr = renderFoundCourseTableRow(item)
            tr.addEventListener('click', () => {
                if (!coursesInGroupInput.value) renderEmptyAddedCoursesTable()
                addedCoursesTbody.appendChild(renderAddedCourseTableRow(item))
                deleteIconsColumn.appendChild(renderDeleteIcon(item.id))
                coursesInGroupInput.value += `${coursesInGroupInput.value === '' ? '' : ','}${item.id}`
                setBaseInputsEnabled(false)
            })
            tbody.appendChild(tr)
        }
    }
}

searchCoursesInput.addEventListener("input", coursesQuery)

modalCloseButton.addEventListener('click', () => {
    groupModal.classList.add('groupModal--hidden')
})

const renderEmptyAddedCoursesTable = () => {
    tableWrapper.innerHTML =
        `<table class="createGroupForm__addedCourses__table fancyTable">
            <thead class="fancyTable__thead">
            <tr>
                <th class="fancyTable__cell createGroupForm__addedCourses__table__cell--name">Nazwa</th>
                <th class="fancyTable__cell createGroupForm__addedCourses__table__cell--ECTS">ECTS</th>
                <th class="fancyTable__cell createGroupForm__addedCourses__table__cell--CNPS">CNPS</th>
                <th class="fancyTable__cell createGroupForm__addedCourses__table__cell--ZZU">ZZU</th>
                <th class="fancyTable__cell createGroupForm__addedCourses__table__cell--courseType">Typ</th>
            </tr>
            </thead>
            <tbody class="fancyTable__tbody createGroupForm__addedCourses__tbody">
            </tbody>
        </table>
        <div class="createGroupForm__addedCourses__buttonsColumn"></div>`
    addedCoursesTbody = document.querySelector(".createGroupForm__addedCourses__tbody")
    deleteIconsColumn = document.querySelector(".createGroupForm__addedCourses__buttonsColumn")
}

const renderNoAddedCoursesSpan = () => {
    tableWrapper.innerHTML =
        `<span class="createGroupForm__noAddedCoursesSpan">Brak kursów w grupie</span>`
}

const renderEmptyFoundCoursesTable = () => {
    try {
        groupModal.removeChild(document.querySelector('.groupModal__noResultsSpan'))
    } catch (e) {}
    const table = document.createElement('table')
    table.classList.add('fancyTable')
    table.classList.add('groupModal__table')
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
        <tbody class="fancyTable__tbody groupModal__tbody">
        </tbody>`
    groupModal.appendChild(table)
    tbody = document.querySelector(".groupModal__tbody")
}

const renderNoResultsSpan = () => {
    groupModal.removeChild(document.querySelector('.groupModal__table'))
    const span = document.createElement('span')
    span.classList.add('groupModal__noResultsSpan')
    span.innerText = 'Brak wyników'
    groupModal.appendChild(span)
}

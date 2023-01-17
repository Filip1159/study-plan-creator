const form = document.querySelector(".createClassForm")
const addCourseButton = document.querySelector(".createGroupForm__addedCourses__button")
const addedCoursesTbody = document.querySelector(".createGroupForm__addedCourses__tbody")
const groupModal = document.querySelector(".groupModal")
const searchCoursesInput = document.querySelector(".groupModal__searchBar__input")
const modalCloseButton = document.querySelector(".groupModal__closeButton")
const nameInput = document.querySelector("#name")
const ectsInput = document.querySelector("#ects")
const cnpsInput = document.querySelector("#cnps")
const zzuInput = document.querySelector("#zzu")
const wayOfCreditingInput = document.querySelector("#wayOfCrediting")
const typeInput = document.querySelector("#type")
const areaInput = document.querySelector("#area")
const learningEffectsInput2 = document.querySelector("#learningEffects")
const tbody = document.querySelector(".groupModal__tbody")
const coursesInGroupInput = document.querySelector("#coursesInGroup")
const deleteIconsColumn = document.querySelector(".createGroupForm__addedCourses__buttonsColumn")

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
})

const renderAddedCourseTableRow = courseData => {
    const tr = document.createElement('tr')
    tr.classList.add('fancyTable__tbody__tr')
    tr.id = `courseAddedToGroup${courseData.id}`
    const itemFields = ['name', 'ECTS', 'CNPS', 'ZZU', 'courseType']
    for (let field of itemFields) {
        const td = document.createElement('td')
        td.classList.add('fancyTable__cell')
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
    })
    return img
}

searchCoursesInput.addEventListener("input", async e => {
    const name = e.target.value
    const wayOfCrediting = wayOfCreditingInput.value
    const type = typeInput.value
    const area = areaInput.value
    const learningEffects = learningEffectsInput2.value
    const res = await fetch(
        `/classes?name=${name}&wayOfCrediting=${wayOfCrediting}&type=${type}&area=${area}&learningEffects=${learningEffects}`)
    const data = await res.json()
    console.log(data)
    tbody.innerHTML = ''
    for (let item of data) {
        const tr = renderAddedCourseTableRow(item)
        tr.addEventListener('click', () => {
            addedCoursesTbody.appendChild(tr)
            deleteIconsColumn.appendChild(renderDeleteIcon(item.id))
            groupModal.classList.add('groupModal--hidden')
            coursesInGroupInput.value += `${coursesInGroupInput.value === '' ? '' : ','}${item.id}`
            setBaseInputsEnabled(false)
        })
        tbody.appendChild(tr)
    }
})

modalCloseButton.addEventListener('click', () => {
    groupModal.classList.add('groupModal--hidden')
})

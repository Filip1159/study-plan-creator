const form = document.querySelector(".createClassForm")
const addCourseButton = document.querySelector(".createClassForm__addedCourses__button")
let addedCoursesTbody = document.querySelector(".createClassForm__addedCourses__tbody")
const modal = document.querySelector(".modal")
let searchCoursesInput = document.querySelector(".modal__searchBar__input")
const modalCloseButton = document.querySelector(".modal__closeButton")
const nameInput = document.querySelector("#name")
const ectsInput = document.querySelector("#ects")
const cnpsInput = document.querySelector("#cnps")
const zzuInput = document.querySelector("#zzu")
const wayOfCreditingInput = document.querySelector("#wayOfCrediting")
const typeInput = document.querySelector("#type")
const areaInput = document.querySelector("#area")
const learningEffectsInput2 = document.querySelector("#learningEffects")

let tbody = document.querySelector(".modal__tbody")
let deleteIconsColumn = document.querySelector(".createClassForm__addedCourses__buttonsColumn")
const tableWrapper = document.querySelector(".createClassForm__addedCourses__tableWrapper")

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

const removeAddedCourse = itemIdToDelete => {
    const trToDelete = document.querySelector(`#addedCourse${itemIdToDelete}`)
    const deleteIconToDelete = document.querySelector(`#deleteIcon${itemIdToDelete}`)
    addedCoursesTbody.removeChild(trToDelete)
    deleteIconsColumn.removeChild(deleteIconToDelete)
    addedCoursesInput.value = addedCoursesInput.value.replace(`${itemIdToDelete}`, '')
    addedCoursesInput.value = addedCoursesInput.value.replace(',,', ',')
    addedCoursesInput.value = addedCoursesInput.value.replace(/^,/, '')
    if (!addedCoursesInput.value) renderNoAddedCoursesSpan()
    removeCourseInGroupErrors()
}

const renderDeleteIcon = itemIdToDelete => {
    const img = document.createElement('img')
    img.classList.add('fancyTable__closeButtonDiv__icon')
    img.id = `deleteIcon${itemIdToDelete}`
    img.src = '/img/close-icon.png'
    img.addEventListener('click', () => removeAddedCourse(itemIdToDelete))
    return img
}

addCourseButton.addEventListener("click", e => {
    e.preventDefault()
    modal.classList.remove("modal--hidden")
    searchCoursesInput.focus()
})

modalCloseButton.addEventListener('click', () => {
    modal.classList.add('modal--hidden')
})

const renderNoResultsSpan = () => {
    modal.removeChild(document.querySelector('.modal__table'))
    const span = document.createElement('span')
    span.classList.add('modal__noResultsSpan')
    span.innerText = 'Brak wyników'
    modal.appendChild(span)
}

const form = document.querySelector(".createClassForm")
const addCourseButton = document.querySelector(".createGroupForm__addedCourses__button")
const addedCoursesTbody = document.querySelector(".createGroupForm__addedCourses__tbody")
const groupModal = document.querySelector(".groupModal")
const coursesInput = document.querySelector("#courses-input")
const searchCoursesInput = document.querySelector(".groupModal__searchBar__input")
const modalCloseButton = document.querySelector(".groupModal__closeButton")
const wayOfCreditingInput = document.querySelector("#wayOfCrediting")
const typeInput = document.querySelector("#type")
const areaInput = document.querySelector("#area")
const learningEffectsInput2 = document.querySelector("#learningEffects")
const tbody = document.querySelector(".groupModal__tbody")

form?.addEventListener("submit", e => {
    e.preventDefault()
    coursesInput.value = "hahahaha"
    const formData = new FormData(form)
    for (const value of formData.values()) {
        console.log(value);
    }
    form.reset()
})

addCourseButton.addEventListener("click", e => {
    e.preventDefault()
    groupModal.classList.remove("groupModal--hidden")
})

const renderAddedCourseTableRow = courseData => {
    const tr = document.createElement('tr')
    tr.classList.add('fancyTable__tbody__tr')
    const itemFields = ['name', 'ECTS', 'CNPS', 'ZZU', 'courseType']
    for (let field of itemFields) {
        const td = document.createElement('td')
        td.classList.add('fancyTable__cell')
        td.innerText = courseData[field]
        tr.appendChild(td)
    }
    return tr
}

searchCoursesInput.addEventListener("input", async e => {
    const name = e.target.value
    const wayOfCrediting = wayOfCreditingInput.value
    const type = typeInput.value
    const area = areaInput.value
    const learningEffects = learningEffectsInput2.value.slice(0, -1)
    const res = await fetch(
        `/classes?name=${name}&wayOfCrediting=${wayOfCrediting}&type=${type}&area=${area}&learningEffects=${learningEffects}`)
    const data = await res.json()
    console.log(data)
    tbody.innerHTML = ''
    for (let item of data) {
        const tr = renderAddedCourseTableRow(item)
        tr.addEventListener('click', () => {
            addedCoursesTbody.appendChild(tr)
            groupModal.classList.add('groupModal--hidden')
        })
        tbody.appendChild(tr)
    }
})

modalCloseButton.addEventListener('click', () => {
    groupModal.classList.add('groupModal--hidden')
})

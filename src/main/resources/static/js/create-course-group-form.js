const form = document.querySelector(".createClassForm")
const addCourseButton = document.querySelector(".createGroupForm__addedCourses__button")
const groupModal = document.querySelector(".groupModal")
const coursesInput = document.querySelector("#courses-input")
const searchCoursesInput = document.querySelector(".groupModal__searchBar__input")
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
    for (let item of data) {
        const tr = document.createElement('tr')
        tr.classList.add('fancyTable__tbody__tr')
        const itemFields = ['name', 'ects', 'cnps', 'zzu', 'courseType']
        for (let field of itemFields) {
            const td = document.createElement('td')
            td.classList.add('fancyTable__cell')
            td.innerText = item[field]
            tr.appendChild(td)
        }
        tbody.appendChild(tr)
    }
})
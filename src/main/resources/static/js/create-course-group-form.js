const form = document.querySelector(".createClassForm")
const addCourseButton = document.querySelector("#add-course-button")
const searchCourses = document.querySelector(".search-courses")
const coursesInput = document.querySelector("#courses-input")

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
    searchCourses.classList.add("search-courses__visible")
})

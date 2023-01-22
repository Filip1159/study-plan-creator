const semesterForm = document.querySelector("#semester-create-form")
const semesterAddButton = document.querySelector("#semester-add-button")
const semesterFormCloseButton = document.querySelector("#semester-create-form-close")
const planFaculty = document.querySelector("#plan-faculty ")
const facultySelect = document.querySelector("#plan-modify-faculty")
const facultySelectOptions = document.querySelector("#faculty-select")
const modifyButton = document.querySelector("#modify-button")
const facultyModifyInput = document.querySelector("#faculty-modify-input")
const submitFacultyModification = document.querySelector("#submit-faculty")

semesterAddButton.addEventListener("click", function (){
    semesterForm.style.display = 'block';
})

semesterFormCloseButton.addEventListener("click", function (){
    semesterForm.style.display = 'none';
})

modifyButton.addEventListener("click", function (){
    planFaculty.style.display = 'none';
    facultySelect.style.display = 'block';
    submitFacultyModification.style.display = 'inline';
})

facultySelectOptions.addEventListener("change", function (){
    console.log(facultySelectOptions.value)
    facultyModifyInput.value = facultySelectOptions.value
})
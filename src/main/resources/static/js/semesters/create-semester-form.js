const semesterForm = document.querySelector("#semester-create-form")
const semesterAddButton = document.querySelector("#semester-add-button")
const semesterFormCloseButton = document.querySelector("#semester-create-form-close")

semesterAddButton.addEventListener("click", function (){
    semesterForm.style.display = 'block';
})

semesterFormCloseButton.addEventListener("click", function (){
    semesterForm.style.display = 'none';
})

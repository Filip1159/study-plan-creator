

var tabBody = document.getElementById("semester-create-form")

    var row = document.createElement("tr");
    var name = document.createElement("td");
    var price = document.createElement("td");
    var category = document.createElement("td");
    var picture = document.createElement("td");
    var button = document.createElement("td");

    name.appendChild(document.createTextNode());
    price.appendChild(document.createTextNode());
    category.appendChild(document.createTextNode());

    button.appendChild(document.createElement("button"));

    row.appendChild(name);
    row.appendChild(price);
    row.appendChild(category);
    row.appendChild(picture);
    row.appendChild(button);
    tabBody.appendChild(row);





const semesterAddButton = document.querySelector("#semester-add-button")
const semesterFormCloseButton = document.querySelector("#semester-create-form-close")
const planFaculty = document.querySelector("#plan-faculty ")
const facultySelect = document.querySelector("#plan-modify-faculty")
const facultySelectOptions = document.querySelector("#faculty-select")
const modifyButton = document.querySelector("#modify-button")
const facultyModifyInput = document.querySelector("#faculty-modify-input")
const submitFacultyModification = document.querySelector("#submit-faculty")

semesterAddButton.addEventListener("click", function (){
    tabBody.style.display = 'block';
})

semesterFormCloseButton.addEventListener("click", function (){
    tabBody.style.display = 'none';
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


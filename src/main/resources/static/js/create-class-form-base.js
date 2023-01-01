const categoryInput = document.querySelector("#category")

categoryInput.addEventListener("change", () => {
    window.location.href = `create-class-form?category=${categoryInput.value}`
})

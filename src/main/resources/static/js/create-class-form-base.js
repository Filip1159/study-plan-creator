const categoryInput = document.querySelector("#category")
const learningEffectsSelect = document.querySelector("#learningEffectsSelect")
const learningEffectsContainer = document.querySelector(".createClassForm__learningEffects")
const learningEffectsInput = document.querySelector("#learningEffects")

const selectedLearningEffects = []

categoryInput.addEventListener("change", () => {
    window.location.href = `create-class-form?category=${categoryInput.value}`
})

learningEffectsSelect.addEventListener("change", () => {
    const selectedEffectId = learningEffectsSelect.value
    console.log(selectedLearningEffects)
    if (selectedLearningEffects.includes(selectedEffectId)) {
        const existingBadge = document.querySelector(`#learningEffectBadge${selectedEffectId}`)
        learningEffectsContainer.removeChild(existingBadge)
        learningEffectsSelect.selectedOptions[0].classList.remove("createClassForm__learningEffectOption--selected")
        learningEffectsInput.value = learningEffectsInput.value.replace(`${selectedEffectId},`, '')
        selectedLearningEffects.filter(id => id !== selectedEffectId)
    } else {
        const badge = document.createElement("div")
        badge.classList.add("createClassForm__learningEffects__badge")
        badge.id = `learningEffectBadge${selectedEffectId}`
        badge.innerText = learningEffectsSelect.selectedOptions[0].text
        learningEffectsSelect.selectedOptions[0].classList.add("createClassForm__learningEffectOption--selected")
        learningEffectsContainer.appendChild(badge)
        learningEffectsInput.value += `${selectedEffectId},`
        selectedLearningEffects.push(selectedEffectId)
    }
    console.log(learningEffectsInput.value)
    learningEffectsSelect.value = 'default'
})

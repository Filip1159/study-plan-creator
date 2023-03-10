const categoryInput = document.querySelector("#category")
const learningEffectsSelect = document.querySelector("#learningEffectsSelect")
const learningEffectsContainer = document.querySelector(".createClassForm__learningEffects")
const learningEffectsInput = document.querySelector("#learningEffects")

categoryInput.addEventListener("change", () => {
    window.location.href = `/classes/create?category=${categoryInput.value}`
})

learningEffectsSelect.addEventListener("change", () => {
    const selectedEffectId = learningEffectsSelect.value
    if (learningEffectsInput.value.split(',').map(Number).includes(selectedEffectId)) {
        const existingBadge = document.querySelector(`#learningEffectBadge${selectedEffectId}`)
        learningEffectsContainer.removeChild(existingBadge)
        learningEffectsSelect.selectedOptions[0].classList.remove("createClassForm__learningEffectOption--selected")
        learningEffectsInput.value = learningEffectsInput.value.replace(`${selectedEffectId}`, '')
        learningEffectsInput.value = learningEffectsInput.value.replace(',,', ',')
        learningEffectsInput.value = learningEffectsInput.value.replace(/^,/, '')
        learningEffectsInput.value = learningEffectsInput.value.replace(/,$/, '')
    } else {
        const badge = document.createElement("div")
        badge.classList.add("createClassForm__learningEffects__badge")
        badge.id = `learningEffectBadge${selectedEffectId}`
        badge.innerText = learningEffectsSelect.selectedOptions[0].text
        learningEffectsSelect.selectedOptions[0].classList.add("createClassForm__learningEffectOption--selected")
        learningEffectsContainer.appendChild(badge)
        learningEffectsInput.value += `${learningEffectsInput.value === '' ? '' : ','}${selectedEffectId}`
    }
    learningEffectsInput.dispatchEvent(new Event('input'))
    console.log(learningEffectsInput.value)
    learningEffectsSelect.value = 'default'
})

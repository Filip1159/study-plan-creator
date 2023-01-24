const search_input = document.querySelector("#search-learning-effect-input")
const search_button = document.querySelector("#search-learning-effect-button")
const learning_effects = document.querySelector("#learningEffects")

search_button.addEventListener("click", () => {
    const search_string = search_input.value.toUpperCase();
    const learning_effects_list = learning_effects.getElementsByClassName('learningEffectItem')

    for (var i = 0; i < learning_effects_list.length; i++) {
        var learningEffectSymbol = learning_effects_list[i].getElementsByClassName('learningEffectItem-symbol')[0].innerHTML
        if (learningEffectSymbol.toUpperCase().includes(search_string))
            learning_effects_list[i].style.display = 'flex';
        else
            learning_effects_list[i].style.display = 'none';
    }

})

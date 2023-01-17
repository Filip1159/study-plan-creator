const facultySelect = document.querySelector('#facultySelect')
const authorInput = document.querySelector('#authorInput')
const fieldInput = document.querySelector('#fieldInput')
const levelSelect = document.querySelector('#levelSelect')
const yearSelect = document.querySelector('#yearSelect')

const updateUrlWithParam = (paramName, paramValue) => {
    let currentUrl = window.location.pathname + window.location.search
    console.log(currentUrl)
    const paramToRegex = new Map([
        ['facultyId', /[&|?]facultyId=\d+/],
        ['author', /[&|?]author=[\w%20]+/],
        ['field', /[&|?]field=\w+/],
        ['level', /[&|?]level=\w+/],
        ['academicYear', /[&|?]academicYear=[0-9/]+/]
    ])
    currentUrl = currentUrl.replace(paramToRegex.get(paramName), '')
    console.log(currentUrl)
    currentUrl = currentUrl === '/plans' ? `/plans?${paramName}=${paramValue}` : `${currentUrl}&${paramName}=${paramValue}`
    console.log(currentUrl)
    return currentUrl.replace('/plans&', '/plans?')
}

facultySelect.addEventListener('change', e => {
    window.location.href = updateUrlWithParam('facultyId', e.target.value)
})

authorInput.addEventListener('change', e => {
    window.location.href = updateUrlWithParam('author', e.target.value)
})

fieldInput.addEventListener('change', e => {
    window.location.href = updateUrlWithParam('field', e.target.value)
})

levelSelect.addEventListener('change', e => {
    window.location.href = updateUrlWithParam('level', e.target.value)
})

yearSelect.addEventListener('change', e => {
    window.location.href = updateUrlWithParam('academicYear', e.target.value)
})

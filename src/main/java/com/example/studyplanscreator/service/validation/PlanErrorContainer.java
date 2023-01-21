package com.example.studyplanscreator.service.validation;

public class PlanErrorContainer {
    private boolean valid;
    private String nameError;
    private String fieldError;
    private String academicYearError;

    private String notUniqueError;

    public PlanErrorContainer(){
        setValid(true);
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
        setValid(false);
    }

    public String getFieldError() {
        return fieldError;
    }

    public void setFieldError(String fieldError) {
        this.fieldError = fieldError;
        setValid(false);
    }

    public String getAcademicYearError() {
        return academicYearError;
    }

    public void setAcademicYearError(String academicYearError) {
        this.academicYearError = academicYearError;
        setValid(false);
    }

    public String getNotUniqueError() {
        return notUniqueError;
    }

    public void setNotUniqueError(String notUniqueError) {
        this.notUniqueError = notUniqueError;
        setValid(false);
    }
}

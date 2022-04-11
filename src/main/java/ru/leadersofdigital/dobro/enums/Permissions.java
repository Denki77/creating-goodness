package ru.leadersofdigital.dobro.enums;

public enum Permissions {
    ROLE_ABSOLUTE("role_absolute"),
    ROLE_ADD_PUPIL_ROLE_PROFILE("role_add_pupil_profile"), // разрешаем роли добавлять профиль воспитанника
    ROLE_GET_PUPIL_ROLE_PROFILE("role_get_pupil_profile"), // разрешаем роли получать профиль воспитанника
    ROLE_EDIT_PUPIL_PROFILE("role_edit_pupil_profile"), // разрешаем роли редактировать профиль воспитанника
    ROLE_EDIT_PROFILE("role_edit_profile"); // разрешаем роли редактировать свой профиль

    private final String code;

    Permissions(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
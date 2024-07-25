@echo off

rem 입력한 파라미터
set BUILD_PARAM=%1

rem 빌드할 모듈명
set MODULE_NAME=app

rem 결정된 gradle task
set BUILD_OPTION=

rem 전체 빌드
if "%BUILD_PARAM%" == "" (
    echo Build %MODULE_NAME% jar...
    set BUILD_OPTION=makeJar
    goto build
)

rem 클린
if "%BUILD_PARAM%" == "clean" (
    echo Build all clean...
    gradlew clean
    goto end
)

:build
if "%BUILD_OPTION%" == "" (
    rem gradle task 결정되지 않았으므로 빌드 못 함
    echo Not found build option parameter "%BUILD_PARAM%"
) else (
    rem 결정된 gradle task로 빌드시작
    echo gradlew %MODULE_NAME%:%BUILD_OPTION%
    gradlew %MODULE_NAME%:%BUILD_OPTION%
)

:EOF
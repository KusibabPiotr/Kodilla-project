call runcrud
if "%ERRORLEVEL%" == "0" goto runchrome
echo.
echo There were errors while trying to run "runcrud" script
goto fail

:runchrome
start chrome http://localhost:8080/crud/v1/task/getTasks
goto success

:fail
echo Script failed

:success
echo Script successfully completed
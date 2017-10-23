call runcrud run
if "%ERRORLEVEL%" == "0" goto showtasks
echo.
echo runcrud.bat has errors - breaking work
goto fail

:showtasks
start http://localhost:8080/crud/v1/tasks
if "%ERRORLEVEL%" == "0" goto end
echo There were errors with browser.
goto fail

:fail
echo.
echo There were errors.

:end
echo.
echo Work is finished.

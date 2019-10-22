@echo off

set SolutionDir=%1
set TargetDir=%2

echo "Build angular frontend"
rmdir /S /Q "%TargetDir%\Scripts\AngularApp\"
mkdir "%TargetDir%\Scripts\AngularApp"
cd "%SolutionDir%\..\frontend-angular8"
call ng build --prod
IF %ERRORLEVEL% EQU 0 (
    echo "Copying angular files"
    cd "%SolutionDir%\..\frontend-angular8\dist"
    xcopy /e /y /i "*.*" "%TargetDir%\Scripts\AngularApp\"
    del "%TargetDir%\Scripts\AngularApp\index.html"
    echo "angular frontend builded and copied"
) ELSE (
    echo "angular frontend not builded for errors"
)

echo "Build react frontend"
rmdir /S /Q "%TargetDir%\Scripts\ReactApp"
mkdir "%TargetDir%\Scripts\ReactApp"
cd "%SolutionDir%\..\frontend-react16"
call npm run build
IF %ERRORLEVEL% EQU 0 (
    echo "Copying react files"
    cd "%SolutionDir%\..\frontend-react16\dist"
    xcopy /e /y /i "*.*" "%TargetDir%\Scripts\ReactApp\"
    del "%TargetDir%\Scripts\ReactApp\index.html"
    echo "react frontend builded and copied"
) ELSE (
    echo "react frontend not builded for errors"
)

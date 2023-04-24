@echo off

echo "=============REMOVING OLD FILES=============}"

del gen-signed.apk classes.dex tcdc.zip

c:\android\sdk\build-tools\34.0.0-rc3\aapt.exe package -m -f -M AndroidManifest.xml -S res\ -J java\ -I c:\android\sdk\platforms\android-33\android.jar

echo "=============COMPILING JAVA FILES=============}"

for /f "delims=" %%i in ('dir /B /S java\*.java') do javac -proc:none -source 7 -target 7 -Xlint:-options -cp c:\android\sdk\platforms\android-33\android.jar -d obj\ -sourcepath java\ %%i

echo "=============GENERATING APK=============}"

c:\android\sdk\build-tools\34.0.0-rc3\aapt.exe package -M AndroidManifest.xml -S res\ -F gen.apk -f --debug-mode -I c:\android\sdk\platforms\android-33\android.jar

tar.exe acf tcdc.zip -C obj\in\ris\tcdc\ *

call c:\android\sdk\build-tools\34.0.0-rc3\d8.bat --classpath c:\android\sdk\platforms\android-33\android.jar tcdc.zip

c:\android\sdk\build-tools\34.0.0-rc3\aapt.exe add gen.apk classes.dex

echo "=============SIGNING APK=============}"

call c:\android\sdk\build-tools\34.0.0-rc3\apksigner sign --ks riskeystore.ks --ks-pass pass:asdfzxcv --in gen.apk --out gen-signed.apk

del gen.apk gen-signed.apk.idsig tcdc.zip
echo "=============REMOVING OLD FILES=============}"

rm -f gen-signed.apk classes.dex

aapt package -m -f -M AndroidManifest.xml -S res/ -J java/ -I ../platform-SDK/android-33.jar

#aapt2 compile --dir res/ -o compiled-res/

#aapt2 link -o gen.apk -I ../platform-SDK/android-33.jar -A assets/ --java java/ --manifest AndroidManifest.xml -R compiled-res/*.flat -v

echo "=============COMPILING JAVA FILES=============}"

ecj-24 -d obj/ -sourcepath java/ $(find java/ -type f -name *.java)

echo "=============GENERATING APK=============}"

aapt package -M AndroidManifest.xml -S res/ -F gen.apk -f --debug-mode

dx --dex --output=classes.dex obj/

aapt add gen.apk classes.dex

#echo "=============ADDING NATIVE BINARY=============}"

#aapt add gen.apk lib/arm64-v8a/*

echo "=============SIGNING APK=============}"

apksigner sign --ks riskeystore.ks --ks-pass pass:asdfzxcv --in gen.apk --out gen-signed.apk

rm -rf gen.apk gen-signed.apk.idsig

echo "=============installing APK=============}"

pm install gen-signed.apk
#ecj-24 --module-path libs/androidx/ --add-modules core,appcompat -log log.xml -verbose
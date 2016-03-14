# LMK.JAVAIO
LMK.JAVAIO is a JAVA library that can support LeMaker Guitar. 

usage

    git clone https://github.com/LeMaker/JAVA.lib.git
    cd your-android.git/android
    source build/envsetup.sh
    lunch 
    cp  -rf JAVA.lib/hardware  system/
    cd system/hardware/
    mm -j8  
    cd system/hardware/jni/
    mm -j8  

these commands will make out lmkhardwarejni.so in your-android.git/android/out/system/lib/ directory, then adb push it to /system/lib/ on Guitar.
    

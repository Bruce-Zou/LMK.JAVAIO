# JAVA.lib
JAVA.lib is a JAVA library that can support LeMaker Guitar. 

usage£º
    git clone https://github.com/LeMaker/JAVA.lib.git
    cd /android.git/android
    source build/envsetup.sh
    lunch 
    cp  -rf JAVA.lib/hardware  system/
    cd system/hardware/
    mm -j8
    cd system/hardware/jni/
    mm -j8  

these command will make out lmkhardwarejni.so, then adb push it to /system/lib/.
    

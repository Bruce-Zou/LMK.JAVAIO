# LMK.JAVAIO
LMK.JAVAIO is a JAVA library that can support LeMaker Guitar. We need copy the library code to android code and compile it .

Usage：
    
    cd your_workspace/
    
    //Download the Guitar Android code from https://github.com/LeMaker/android-actions.git, then it will have
    //a android-actions directory in your workspace, if you have downloaded the android code, please ignore.
    git clone https://github.com/LeMaker/android-actions.git  
    
    //Download the Guitar JAVA library from https://github.com/LeMaker/JAVA.lib.git, then it will have a 
    //JAVA.lib directory in your workspace
    git clone https://github.com/LeMaker/LMK.JAVAIO.git
    
    //Configuration environment
    cd android-actions/android/
    source build/envsetup.sh
    lunch 
    
    //cp the JAVA library code into android code (in android-actions/android/system/ directory)
    cp  -rf LMK.JAVAIO/hardware  system/
    
    //compile the JAVA library.
    cd system/hardware/
    mm -j8  
    cd system/hardware/jni/
    mm -j8  

these commands will make out lmkhardwarejni.so in android-actions/android/out/system/lib/ directory, then adb push it to /system/lib/ on Guitar.
    

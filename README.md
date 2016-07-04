# LMK.JAVAIO
LMK.JAVAIO is a JAVA GPIO library which supports LeMaker Guitar board. If we want to use it in your Android system, we need copy the library code to android source code tree and compile it .
    
## Usage：
### Download necessary source code
Download the Guitar Android source code into your workspace:
    
    cd your_workspace/
    git clone https://github.com/LeMaker/android-actions.git ` 
    
Download the Guitar JAVA GPIO library into your workspace:

    git clone https://github.com/LeMaker/LMK.JAVAIO.git
    
### Configuration environment

    cd android-actions/android/
    source build/envsetup.sh
    lunch 
    
Copy the JAVA GPIO library code into android source code (in **android-actions/android/system/** directory)
    
    cp -rf ../../LMK.JAVAIO/hardware  system/
    
### Compiling
Compile the JAVA GPIO library:

    cd system/hardware/
    mm -j8  
    cd system/hardware/jni/
    mm -j8  

### Push to Guitar on-board system
After that we will make out **lmkhardwarejni.so** in **android-actions/android/out/target/product/lemaker_guitar_bbb/system/lib/** directory, then we can use adb tool to push it to Guitar on-board Android system directory **/system/lib/**.

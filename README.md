# GeneralAndroid
通用Android

## gradle使用：

一、Project下的build.gradle文件下添加

allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
}

二、Module下的build.gradle文件下添加

1.1.4之前

dependencies {
    
    implementation 'com.github.Lvluffy:GeneralAndroid:1.1.4'
          
}

1.1.5之后

dependencies {
    
    implementation 'com.github.Lvluffy.GeneralAndroid:componentlib:1.1.5'
       
    implementation 'com.github.Lvluffy.GeneralAndroid:recyclerviewlib:1.1.5'
             
}

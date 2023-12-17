# <p align="center"> Ghibli  📺 </p>


This application is developed for watching movies or series. You can discover new shows, add them to your watchlist, and enjoy your movie!

<!-- Screenshots -->
## 📸 Screenshots
<p align="center">
  <img src="https://github.com/selincengiz41/ghibli/assets/60012262/23e726f9-7fa3-4df7-957b-7bbb4639dd44" width="130" height="300"/> 
  <img src="https://github.com/selincengiz41/ghibli/assets/60012262/552e3b50-cb07-40c8-a57f-c49e3443996e" width="130" height="300"/> 
  <img src="https://github.com/selincengiz41/ghibli/assets/60012262/92de9c31-edab-4b17-b35d-ff6fb32dc238" width="130" height="300"/>
  <img src="https://github.com/selincengiz41/ghibli/assets/60012262/dfa4ec6a-f0c5-4725-b601-04c2fbbb1c98" width="130" height="300"/>
  <img src="https://github.com/selincengiz41/ghibli/assets/60012262/da77eb44-a79d-488c-82fc-d04dafc6124b" width="130" height="300"/>
  <img src="https://github.com/selincengiz41/ghibli/assets/60012262/b49b76e7-e46a-4fc3-ab44-f55bc767c4c9" width="130" height="300"/>
  <img src="https://github.com/selincengiz41/ghibli/assets/60012262/ec71984d-b8bd-4109-89c2-4ff70bac0a9a" width="130" height="300"/>
  <img src="https://github.com/selincengiz41/ghibli/assets/60012262/1b85e344-ae76-4f31-b3ed-be522ffb9422" width="300" height="130"/>
 




</p>


## 📽 Video 

- https://drive.google.com/file/d/17vlPxRm7cLb-evNkxNribdqwqXLnX3tu/view?usp=sharing

<br>

## :point_down: Structures 
- MVVM
- Firebase 
- Navigation
- Hilt
- Coroutines
- Retrofit
- Room 
- Data Binding 
- Glide
- SDP/SSP Library
- Motion Layout
- YouTubePlayerView


## :pencil2: Dependency
```
    dependencies {

 
    // SSP-SDP library
    implementation 'com.intuit.ssp:ssp-android:1.1.0'
    implementation 'com.intuit.sdp:sdp-android:1.1.0'

    // Navigation
    implementation 'androidx.navigation:navigation-fragment-ktx:2.6.0'
    implementation 'androidx.navigation:navigation-ui-ktx:2.6.0'

    //Retrofit
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"

    //Glide
    implementation "com.github.bumptech.glide:glide:4.15.1"

    //Roundable Layout
    implementation 'com.github.zladnrms:RoundableLayout:1.1.4'

    //Lottie
    implementation 'com.airbnb.android:lottie:6.1.0'

    //Hilt
    implementation 'com.google.dagger:hilt-android:2.47'
    kapt 'com.google.dagger:hilt-compiler:2.47'


    //Chucker
    implementation("com.github.chuckerteam.chucker:library:4.0.0")

    //Room
    implementation("androidx.room:room-runtime:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")
    kapt "androidx.room:room-compiler:2.5.2"

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //Firebase
    implementation platform('com.google.firebase:firebase-bom:32.6.0')
    // Add the dependency for the Firebase Authentication library
    implementation("com.google.firebase:firebase-auth-ktx")

    //Youtube player
    implementation 'com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0'




}
```

app build.gradle:

```
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-parcelize'
    id 'androidx.navigation.safeargs'
    //For the annotations
    id 'kotlin-kapt'
    id 'dagger.hilt.android.plugin'
    id 'com.google.gms.google-services'
}

buildFeatures {
      dataBinding = true
 }
```
project build.gradle:

```
plugins {
    id 'com.android.application' version '8.0.2' apply false
    id 'com.android.library' version '8.0.2' apply false
    id 'org.jetbrains.kotlin.android' version '1.8.20' apply false
    id 'com.google.gms.google-services' version '4.4.0' apply false
    id 'androidx.navigation.safeargs.kotlin' version '2.5.1' apply false
    id 'com.google.dagger.hilt.android' version "2.44" apply false
}
```

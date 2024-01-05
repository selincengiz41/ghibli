# <p align="center"> Ghibli  📺 </p>


This application is developed for watching movies or series. You can discover new shows, add them to your watchlist, and enjoy your movie!

<!-- Screenshots -->
## 📸 Screenshots
<p align="center">
  <img src="https://github.com/selincengiz41/ghibli/assets/60012262/0229c3b4-a4b4-41a5-beba-fba473e8b5d5" width="130" height="300"/>
  <img src="https://github.com/selincengiz41/ghibli/assets/60012262/b3d1392d-4cbe-4260-ac46-f36a2b9d2eab" width="130" height="300"/>
  <img src="https://github.com/selincengiz41/ghibli/assets/60012262/bc4274e0-1b56-4698-bd63-184c3a0c80c6" width="130" height="300"/>
  <img src="https://github.com/selincengiz41/ghibli/assets/60012262/50d29bdd-796b-4adc-adcf-a105d535c9bc" width="130" height="300"/>
  <img src="https://github.com/selincengiz41/ghibli/assets/60012262/3a1285cd-9a6f-4a04-b34d-bc7838f46fb9" width="130" height="300"/> 
  <img src="https://github.com/selincengiz41/ghibli/assets/60012262/226c44c7-b949-44a9-8962-c7dcd4763a03" width="130" height="300"/>
  <img src="https://github.com/selincengiz41/ghibli/assets/60012262/e622fab5-76b3-4f3a-831f-fe9e0a092f37" width="130" height="300"/> 
  <img src="https://github.com/selincengiz41/ghibli/assets/60012262/9aa33e25-e939-43e0-8a57-0d36faae9315" width="130" height="300"/>
  <img src="https://github.com/selincengiz41/ghibli/assets/60012262/52d08348-f639-4c95-b528-a6f705fae775" width="130" height="300"/>
 
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

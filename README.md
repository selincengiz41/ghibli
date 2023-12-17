# <p align="center"> Ghibli </p>


This application is developed for watching movies or series. You can discover new shows, add them to your watchlist, and enjoy your movie!

<!-- Screenshots -->
## 📸 Screenshots
<p align="center">
  <img src="https://github.com/selincengiz41/dinefeast/assets/60012262/c9fc98a6-cfac-4a67-a8bd-68b2c9225cee" width="275" height="550"/> 
  <img src="https://github.com/selincengiz41/dinefeast/assets/60012262/7b83cdd5-bbf0-47c3-ba45-ecdc17bd5d23" width="275" height="550"/> 
  <img src="https://github.com/selincengiz41/dinefeast/assets/60012262/7cbe312f-447c-4229-88ce-3bef19b9d8ce" width="275" height="550"/>
  <img src="https://github.com/selincengiz41/dinefeast/assets/60012262/89679e93-3e58-4a12-87fc-5c55c87cc1dc" width="275" height="550"/>
  <img src="https://github.com/selincengiz41/dinefeast/assets/60012262/894801b6-9af5-432f-a9bd-ef9374487f8a" width="275" height="550"/>
  <img src="https://github.com/selincengiz41/dinefeast/assets/60012262/5419113f-0e96-4af8-8e41-5555b10d04c5" width="275" height="550"/>
  <img src="https://github.com/selincengiz41/dinefeast/assets/60012262/87e5b1d5-2bd2-4fb7-841e-9ab820ac882d" width="275" height="550"/>
  <img src="https://github.com/selincengiz41/dinefeast/assets/60012262/0fa18db2-e27c-4268-9a89-a62e2c219246" width="275" height="550"/>
  <img src="https://github.com/selincengiz41/dinefeast/assets/60012262/fed67277-086d-4b75-827b-5009107d82af" width="275" height="550"/>
  <img src="https://github.com/selincengiz41/dinefeast/assets/60012262/f5c187fe-21c8-40db-bbcb-e2a8b23510df" width="275" height="550"/> 
  <img src="https://github.com/selincengiz41/dinefeast/assets/60012262/614a72ed-f873-4c1f-9f79-55e54e6b61be" width="275" height="550"/> 
  
  

</p>


## 📽 Video 
- https://drive.google.com/file/d/10elKAIumdHEyEe2YANDUpfP6X_p_iLSx/view?usp=drivesdk

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

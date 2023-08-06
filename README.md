# Todo_Android_App_using_DataBinding_And_ViewBinding_with_Room_MVVM_Approach

This project is made just to Practice on dataBinding & viewBinding concept, along with ROOM Daatbase & MVVM Architecture.

Also include actions like (SWIPE to delete / update note list).

===    additional dependencies used in project   ===

implementation ("androidx.room:room-runtime:2.5.2")

    annotationProcessor ("androidx.room:room-compiler:2.5.2")

  // ViewModel  
    
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    
  // ViewModel utilities for Compose
  
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    
  // LiveData

    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    
  // Annotation processor
  
      implementation ("androidx.lifecycle:lifecycle-compiler:2.5.1")
      
//dependency is used to align the versions of all Kotlin components in a project. This is useful to avoid class duplication and other problems that can occur when different Kotlin components are using different versions of the Kotlin standard library.

      implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))

//Provides extensions to the Lifecycle library, including the ViewModelProviders and LiveData classes.

      implementation ("androidx.lifecycle:lifecycle-viewmodel:2.4.0")

      implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")

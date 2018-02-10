# And-Limpid-EditText
And Limpid EditText

### GIT Command
```git_command
git init
git remote add origin https://github.com/rzrasel/And-Library-Mine-View.git
git remote -v
git fetch && git checkout And-Limpid-EditText
git add .
git commit -m "Add Readme & Git Commit File"
git pull
git push --all
```

### Installation
Maven Repositories Installation

### Add Maven Repositories
```maven_repositories
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

### Add Android Dependencies
```android_dependencies
dependencies {
    compile 'com.github.rzrasel:com.rz.limpidedittext:V-201712.0.1'
}
```

### Add Android .AAR File
```android_repositories
allprojects {
   repositories {
      jcenter()
      flatDir {
        dirs 'libs'
      }
   }
}
```
```android_dependencies
dependencies {
    compile(name:'limpidedittext', ext:'aar')
}
```
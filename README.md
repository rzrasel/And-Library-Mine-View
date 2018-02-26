# And-Library-Mine-View
And Library Mine View

### GIT Command
```git_command
git init
git remote add origin https://github.com/rzrasel/And-Library-Mine-View.git
git remote -v
git fetch && git checkout master
git add .
git commit -m "Add Readme & Git Commit File"
git pull
git push --all

Reduce git repository size
git reflog expire --all --expire=now
git gc --prune=now --aggressive

Garbage collecting dead data
git count-objects -v
git reflog expire --expire=now --all
git gc --prune=now
git push --all --force
git push --tags --force
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

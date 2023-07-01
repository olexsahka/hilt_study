package com.example.hilttest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton


/** // TODO HILT NAMED **/

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing1())
        println(someClass.doAThing2())

    }
}

class SomeClass
@Inject
constructor(
    @Impl1 private val someImpl1: SomeInterface,
    @Impl2 private val someImpl2: SomeInterface){
    fun doAThing1(): String{
        return "Look I got: ${someImpl1.getAThing()}"
    }
    fun doAThing2(): String{
        return "Look I got: ${someImpl2.getAThing()}"
    }
}

class SomeInterfaceImpl1
@Inject
constructor(): SomeInterface{
    override fun getAThing() : String{
        return "A Thing1 "
    }
}

class SomeInterfaceImpl2
@Inject
constructor(): SomeInterface{
    override fun getAThing() : String{
        return "A Thing2 "
    }
}

interface  SomeInterface{
    fun getAThing(): String
}



/**
всех сценария, более простой
анотация привязки интерфейсов и стороних либ
Не абстрактный класс
 **/
@InstallIn(SingletonComponent ::class) //указать класс компонента
@Module
class MyModule{

    @Impl1
    @Singleton
    @Provides
    fun provideSomeInterface1() :SomeInterface{ //  интерфейс
        return SomeInterfaceImpl1()  // Реализация интерфайса
    }


    @Impl2
    @Singleton
    @Provides
    fun provideSomeInterface2() :SomeInterface{ //  интерфейс
        return SomeInterfaceImpl2()  // Реализация интерфайса
    }
}
/** TODO СОЗДАЕМ АНОТАЦИЯ ДЛЯ РАЗЛИЧИЯ РЕАЛИЗАЦИЙ ИНТЕРФЕЙСОВ**/
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl2











// -------------------------------------------------------------------
/** // TODO HILT CONSTRUCTOR INJECTION PROBLEMS **/

/** // TODO HILT MODULES  | BINDS | PROVIDES **/
//@AndroidEntryPoint
//class MainActivity : AppCompatActivity() {
//
//    @Inject
//    lateinit var someClass: SomeClass
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        println(someClass.doAThing())
//    }
//}
//
//class SomeClass
//@Inject
//constructor(
//    private val someImpl: SomeInterface,
//    private val gson : Gson
//    /** // TODO HILT CONSTRUCTOR INJECTION PROBLEMS **/
////    private val someInterfaceImpl: SomeInterface // так не работает, мы не знаем как создовать объекты интерфе
////    private val gson : Gson // так не работает, он не знает
//){
//    fun doAThing(): String{
//        return "Look I got: ${someImpl.getAThing()}" // так не работа
//    }
//}
//
//class SomeInterfaceImpl
//@Inject
//constructor(
//    private val someDependency : String
//): SomeInterface{
//    override fun getAThing() : String{
//        return "A Thing $someDependency"
//    }
//}
//interface  SomeInterface{
//    fun getAThing(): String
//}
//
//
//
///** // TODO HILT MODULES  | BINDS | PROVIDES **/
///**
//        Не во всех сценария, более сложный
//        анотация привязки интерфейсов
//**/
////@InstallIn(ActivityComponent ::class) //указать класс компонента
////@Module
////abstract  class MyModule{
////
////    @ActivityScoped // нужно указать жизненый цикл взависимости от компонента
////    @Binds
////    abstract fun bindSomeDependency(
////        someImpl : SomeInterfaceImpl
////    ): SomeInterface
//
////     НЕ РАБОТАЕТ СО СТОРОНИМИ БИБЛИОТЕКАМИ
////    @ActivityScoped
////    @Binds
////    abstract fun bindGson(
////        someGson : Gson
////    ): Gson
////}
///**
//        всех сценария, более простой
//        анотация привязки интерфейсов и стороних либ
//        Не абстрактный класс
// **/
//@InstallIn(SingletonComponent ::class) //указать класс компонента
//@Module
//class MyModule{
//
//    @Singleton
//    @Provides
//    fun provideSomeString() :String{
//        return "someString"
//    }
//
//    @Singleton
//    @Provides
//    fun provideSomeInterface(someString: String) :SomeInterface{ //  интерфейс
//        return SomeInterfaceImpl(someString)  // Реализация интерфайса
//    }
//
//    @Singleton
//    @Provides
//    fun provideGson() :Gson = Gson()
//}



// --------------------------------------------------------


/**
 *  TODO FIELDS INJECTION |
 *  TODO CONSTRUCTOR INJECTION |
 * TODO  SCOPING **/
//@AndroidEntryPoint // получаем контекст от application
//class MainActivity : AppCompatActivity() {
//
//    // field injection
//    @Inject lateinit var  someClassA: SomeClassA // обязательная поздняя инициализация
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        println(someClassA.doITA())
//        println(someClassA.doSomeOther())
//
//
//
//    }
//}


//@AndroidEntryPoint
// class MyFragment: Fragment(){
//
//     @Inject lateinit var someClassA: SomeClassA
// }
//
//
////@Singleton //  экземлпяр класса будет жив пока работает приложение
//@ActivityScoped //  экземлпяр класса будет жив пока работает активити
//class SomeClassA
//@Inject constructor(private val  someClassB: SomeClassB){  // constructor injection
//    fun doITA():String = "Look it A"
//    fun doSomeOther() : String  = someClassB.doITB()
//}
//
//class SomeClassB
//@Inject constructor(){
//    fun doITB():String = "Look it B"
//
//}
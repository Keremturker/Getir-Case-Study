package com.kturker.contract

import javax.inject.Qualifier

annotation class Dispatchers {

    @Qualifier
    @MustBeDocumented
    @Retention(AnnotationRetention.BINARY)
    annotation class Main

    @Qualifier
    @MustBeDocumented
    @Retention(AnnotationRetention.BINARY)
    annotation class IO

    @Qualifier
    @MustBeDocumented
    @Retention(AnnotationRetention.BINARY)
    annotation class Default

    @Qualifier
    @MustBeDocumented
    @Retention(AnnotationRetention.BINARY)
    annotation class Unconfined
}

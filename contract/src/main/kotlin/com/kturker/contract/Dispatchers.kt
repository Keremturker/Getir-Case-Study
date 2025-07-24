package com.kturker.contract

import javax.inject.Qualifier

annotation class Dispatchers {

    @Qualifier
    @MustBeDocumented
    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
    @Retention(AnnotationRetention.BINARY)
    annotation class Main

    @Qualifier
    @MustBeDocumented
    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
    @Retention(AnnotationRetention.BINARY)
    annotation class IO

    @Qualifier
    @MustBeDocumented
    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
    @Retention(AnnotationRetention.BINARY)
    annotation class Default

    @Qualifier
    @MustBeDocumented
    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
    @Retention(AnnotationRetention.BINARY)
    annotation class Unconfined
}

package di
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.named
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.withOptions
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import repository.ClassA
import repository.ClassB
import repository.SampleInterface

val shareModule = module {

    factory { "String Injected" }

    singleOf(::ClassA) withOptions {
        createdAtStart()
        named("A")
        bind<SampleInterface>()

    }

   factory {
        ClassB(sampleInterface =  get(qualifier = qualifier("A")))
    }
/*
    factory {
        ClassB(sampleInterface = get(qualifier = qualifier("ABC")))
    }*/

}
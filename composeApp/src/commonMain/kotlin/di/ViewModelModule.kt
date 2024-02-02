package di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import presentation.CharactersViewModel

val viewModelModule = module {

    singleOf(::CharactersViewModel)

}
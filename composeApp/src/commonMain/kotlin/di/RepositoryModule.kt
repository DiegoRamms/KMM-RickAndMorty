package di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import repository.RickMortyRepositoryImp


val repositoryModule = module {

    singleOf(::RickMortyRepositoryImp){
        bind<RickMortyRepository>()
    }

}
package di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import data.repository.RickMortyRepositoryImp
import domain.RickMortyRepository


val repositoryModule = module {

    singleOf(::RickMortyRepositoryImp){
        bind<RickMortyRepository>()
    }

}
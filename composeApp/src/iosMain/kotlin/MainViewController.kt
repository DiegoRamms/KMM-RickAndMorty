import androidx.compose.ui.window.ComposeUIViewController
import di.dataSourceModule
import di.networkModule
import di.repositoryModule
import di.viewModelModule
import org.koin.core.context.startKoin
import presentation.App

fun MainViewController() = ComposeUIViewController { App() }

fun initKoin() {
    startKoin {
        modules(networkModule, repositoryModule, dataSourceModule, viewModelModule)
    }
}

import androidx.compose.ui.window.ComposeUIViewController
import di.dataSourceModule
import di.networkModule
import di.repositoryModule
import di.shareModule
import di.viewModelModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }

fun initKoin() {
    startKoin {
        modules(shareModule, networkModule, repositoryModule, dataSourceModule, viewModelModule)
    }
}

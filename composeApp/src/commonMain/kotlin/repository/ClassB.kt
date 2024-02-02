package repository

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ClassB(private val sampleInterface: SampleInterface): KoinComponent {
    fun getValue() = sampleInterface.getValue()
}
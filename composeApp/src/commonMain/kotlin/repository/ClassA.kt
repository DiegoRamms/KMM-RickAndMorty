package repository

class ClassA(private val nameString: String): SampleInterface {

    override fun getValue(): String = nameString

}